package com.gowtham.jmp3tag;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gowtham
 */
public class APICTag
{
    private byte []bytes;
    private int offset;
    private String mimeType;
    private String description;
    private int pictureType;
    private int textEncoding;
    private int imageStartIndex;
    private int imageLength;
    private String path;

    private Logger logger;
    
    public APICTag(String aPath) {
        
        path = aPath;
        
    }
    
    public void setLogger(Logger aLogger) {
        logger = aLogger;
    }
    public byte[] toByteArray() throws IOException {
        FileConnection fc = (FileConnection) Connector.open( path, Connector.READ);
        if (!fc.exists()) {
            throw new IOException("File " + path + " does not exist!");
        }
        InputStream is = fc.openInputStream();
        
        /*
         *  <Header for 'Attached picture', ID: "APIC"> 
            Text encoding   $xx
            MIME type       <text string> $00
            Picture type    $xx
            Description     <text string according to encoding> $00 (00)
            Picture data    <binary data>
         */
        
        char []mimeTypeArray = getMimeTypeForImage().toCharArray();
        
        byte[] array = new byte[1 + (mimeTypeArray.length + 1) + 1 + 1 + (int)fc.fileSize()];
        int i = 0;
        array[i++] = 0; // Text encoding
        
        // MIME type
        for(int j=0; j<mimeTypeArray.length; j++, i++)
            array[i] = (byte)mimeTypeArray[j];
        array[i++] = 0x0;
        
        array[i++] = 0x03; // Picture type
        
        // Image description
        array[i++] = 0;
        
        // Store the offset
        offset = i;
        
        int data = -1;
        while( (data = is.read()) != -1 ) {
            array[i++] = (byte)data;
        }
        
        is.close();
        fc.close();
        
        return array;
    }
    
    public int getImageOffset() {
        return offset;
    }
    
    public APICTag(byte []data)
    {
        /*
        Text encoding   $xx
        MIME type       <text string> $00
        Picture type    $xx
        Description     <text string according to encoding> $00 (00)
        Picture data    <binary data>
        */
        int index = 0;

        textEncoding = (int)data[index];
        index++;

        int mimeLength = 0;
        int tmp = index;
        while( data[tmp] != 0 )
        {
            mimeLength++;
            tmp++;
        }
        mimeType = new String( data, index, mimeLength );
        index += ( mimeLength + 1 );

        pictureType = (int)data[index];
        index++;

        int descLength = 0;
        tmp = index;
        while( data[tmp] != 0 )
        {
            descLength++;
            tmp++;
        }
        description = new String( data, index, descLength );
        index += ( descLength + 1 );

        imageStartIndex = index;
        imageLength = ( data.length - imageStartIndex );
    }

    public String getMimeType()
    {
        return mimeType;
    }

    public String getDescription()
    {
        return description;
    }

    public int getImageStartIndex()
    {
        return imageStartIndex;
    }
    public int getImageLength()
    {
        return imageLength;
    }

    private String getMimeTypeForImage() {
        int index = path.lastIndexOf('.');
        String extn = path.substring(index+1);
        if( extn.equalsIgnoreCase("jpg") || extn.equalsIgnoreCase("jpeg"))
            return  "image/jpeg";
        if( extn.equalsIgnoreCase("png"))
            return "image/png";
        if( extn.equalsIgnoreCase("bmp"))
            return "image/bmp";
        if( extn.equalsIgnoreCase("gif"))
            return "image/gif";
        
        // This should never happen
        return "";        
    }

}
