package com.gowtham.jmp3tag;

/*
 * Tag.java
 *
 * Created on December 25, 2009, 3:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.lcdui.*;
import java.util.*; 
import javax.microedition.io.file.*;
import javax.microedition.io.*;


/**
 * ID3 structure:
 *
 *
Header:
 
ID3v2/file identifier   "ID3" 
ID3v2 version           $03 00
ID3v2 flags             %abc00000
ID3v2 size              4 * %0xxxxxxx

a - Unsynchronisation
b - Extended header
c - Experimental indicator
 */

/**
 *
 * @author Gowtham
 */
public class Tag {

    // MP3 file path
    private String file;

    // Real tags
    private String title;
    private String album;
    private String artist;
    private String year;
    private String comment;
    private String composer;

    // Does tag exist?
    private boolean tagExists;

    // Does extended header exist?
    private boolean extendedHeaderExists;

    // Size of the whole tag
    private int tagSize;

    // Original tag size
    private long originalTagSize;

    // ID3 header
    private byte[] header;

    // ID3 extended header
    private byte[] extendedHeader;

    // Actual tag data
    private byte[] tagData;

    // Frames
    private Vector frames;
    
    
    // Music file size
    private int mp3size;

    // Tag size as a 4 byte buffer, 7th bit always 0
    private byte [] tagSizeBuffer;

    // Padding in the tag
    private int padding;
    
    /** Creates a new instance of Tag */
    public Tag( String thisFile ) {
        file = thisFile;
        tagExists = false;  // Assume tag does not exist
        header = new byte[10]; // Header is 10 bytes long
        extendedHeader = new byte[10]; // Extended header is utmost 10 bytes long
        frames = new Vector();
        tagSizeBuffer = new byte[4];
        padding = 0;
    }

    public void deleteAPICFrame()
    {
        deleteFrame( "APIC" );
    }
    public String getTitile()
    {
        return title;
    }
    public String getAlbum()
    {
        return album;
    }
    public String getArtist()
    {
        return artist;
    }
    public String getYear()
    {
        return year;
    }
    public String getComment()
    {
        return comment;
    }
    public String getComposer()
    {
        return composer;
    }
    public boolean tagExists()
    {
        return tagExists;
    }
    public boolean Read() throws Exception {

        FileConnection fc = (FileConnection) Connector.open( file, Connector.READ);
        if (!fc.exists()) {
            throw new IOException("File " + file + " does not exist!");
        }
        mp3size = (int) fc.fileSize();
       
        InputStream is = fc.openInputStream();

        int bytesRead = is.read(header, 0, 3);

        if( bytesRead < 3 )
        {
            throw new IOException( "Less than 3 bytes read in the first call!");
        }
        String first = new String(header);
        first = first.trim();

        System.out.println("First text = " + first);


        if (first.compareTo("ID3") == 0) {
            // TAG found!
            tagExists = true;

            // Read the header
            is.read(header, 3, 7);
            System.out.println("Header = " + new String(header));

            // Compute the tag size
            tagSize = calculateTagSize(header, 6, 9);
            System.out.println("Tag size = " + tagSize);

            // Extended header present?
            if ((header[5] & 0x40) != 0) {
                extendedHeaderExists = true;
            } else {
                extendedHeaderExists = false;
            }
            System.out.println("Extended header = " + extendedHeaderExists);

            if( extendedHeaderExists )
            {
                // TODO: special handling for padding?
                is.read( extendedHeader, 0, 10);
            }
            int bytes2Read = tagSize;
            originalTagSize = bytes2Read + 10;


            // Allocate memory for the tag data
            tagData = new byte[bytes2Read];

            // Read it...
            is.read(tagData, 0, bytes2Read);

            populateTags(tagData, bytes2Read);

            System.out.println("Title = [" + title + "]");
            System.out.println("Album = [" + album + "]");
            System.out.println("Artist = [" + artist + "]");

        }

        is.close();
        fc.close();


        return true;
    }
    
    public int calculateTagSize( byte data[], int min, int max )
    {
        int size = 0;
        
        for( int i = min; i <= max; i++ )
        {
            int thisByte = data[i];
            thisByte = thisByte & 0x7F; // Mask the 7th bit (MSB)
            if( i > min )
            {
                size = size << 7;
            }
            size += thisByte;
        }
        
        return size;
    }

    public byte[] getFrameData( byte data[], int min, int max )
    {
        //System.out.println( "getFramedata called with <" + min + "," + max + ">" );
        int bufferSize = (max - min);
        //System.out.println( "bufferSize = " + bufferSize);
        byte buffer[] = new byte[bufferSize+1];

        for( int i = min; i <= max; i++ )
        {
            //System.out.println( "Copied " + data[i] + " to buffer");
try{            buffer[i-min] = data[i];}catch(Exception xc){}
        }

        //System.out.println( "getFrameData returned");
        return buffer;
    }
    public void populateTags( byte[] data, int size )
    {
        // Frame header:
        // Frame ID       $xx xx xx xx (four characters) 
        // Size           $xx xx xx xx
        // Flags          $xx xx

        int i = 0;
        byte frameID[] = new byte[4];
        byte frameSize[] = new byte[4];
        byte frameFlag[] = new byte[2];
        byte frameData[];
        int frameSizeInt;
        System.out.println("file size: " + mp3size);
        while( i < size && i < mp3size/4096 )
        {
            frameID = getFrameData(data, i+0, i+3);
            frameSize = getFrameData(data, i+4, i+7);
            frameFlag = getFrameData(data, i+8, i+9);
            
            String frameIDString = new String( frameID );

            // Skip invalid frames silently
            if( ! Frame.isValidFrame( frameIDString ) )
            {
                System.out.println( "Invalid frame " + frameIDString );
                padding += 10;
                i += 10;

                if( ( size - i ) < 10 || i > mp3size )
                    break;
                continue;
            }

            System.out.println( "Frame " + frameID);
            frameSizeInt = Frame.calculateFrameSize( frameSize, 0, 3 );
            System.out.println( "Size " + frameSizeInt);

            frameData = new byte[frameSizeInt];
            System.out.println( "data size = " + data.length );
            System.out.println( "i+10 = " + ( i + 10 ) );
            System.out.println( "i+10+frameSizeInt-1 = " + ( i+10+frameSizeInt-1 ) );
            frameData = getFrameData( data, i+10, i+10+frameSizeInt-1 );
            //System.out.println( "Framedata [" + frameData + "]" );


            String frameDataString;
            boolean isEditable = isEditableTag(frameIDString);
            if( isEditable )
            {
                frameDataString = myBinary2String( frameData );
                System.out.println( frameIDString + " is editable" );
            }
            else
            {
                frameDataString = new String( frameData );
                System.out.println( frameIDString + " is NOT editable" );
            }
            frameDataString = frameDataString.trim();
            //System.out.println( "FramedataString [" + frameDataString + "]" );

            System.out.println( "FRAME = " + frameIDString + " size " + frameSizeInt + " value " + frameDataString );

            if( frameIDString.equalsIgnoreCase( "TALB" ) )
            {
                album = frameDataString;
            }
            else if ( frameIDString.equalsIgnoreCase( "TCOM") )
            {
                composer = frameDataString;
            }
            else if( frameIDString.equalsIgnoreCase( "TIT2") )
            {
                title = frameDataString;
            }
            else if( frameIDString.equalsIgnoreCase( "TYER" ) )
            {
                year = frameDataString;
            }
            else if( frameIDString.equalsIgnoreCase( "COMM" ) )
            {
                if( frameDataString.startsWith( "eng" ) )
                {
                    frameDataString = frameDataString.substring( frameDataString.indexOf('g') + 1 );
                }
                comment = frameDataString;
            }
            else if( frameIDString.equalsIgnoreCase( "TPE1" ) )
            {
                artist = frameDataString;
            }

            Frame frm = new Frame( frameIDString, frameFlag, frameDataString, frameData, isEditable );
            frames.addElement(frm);
            // Advance to next frame. frameSizeInt will have the size of
            // this frame. Add a 10 to account for frame header's size as well
            i += ( frameSizeInt + 10 );
            

            if( ( size - i ) < 10 )
                break;
            System.out.println( "i = " + i);
        }
    }


    public String myBinary2String( byte data[])
    {        
        int length = data.length;
        for( int k = 0; k < data.length; k++ )
        {
            System.out.println( "Data : " + k + " " + data[k] );
        }
        byte[] newData = new byte[length];
        int j = 0;
        int i = 0;
        /*if( ( data[0] == 0x01 ) && ( data[1] == 0xFF ) && ( data[2] == 0xFE ) )
        {
            System.out.println( "Unicode");
            i = 3;
        }*/
        /* -1 and -2 below are equivalent to 0xff and 0xfe respectively */
        if( ( data[0] == 0x01 || data[0] == 0x00 ) &&
            (
                ( data[1] == -1 && data[2] == -2   ) ||
                ( data[1] == -2 && data[2] == -1   )
            )
          )
        {
            System.out.println( "Unicode");
            i = 3;
        }
        if( data[0] == 0x00 )
        {
            System.out.println( "ISO-8859");
            i = 1;
        }
        for( ; i<length; i++ )
        {
            if( data[i] != 0 )
            {
                newData[j] = data[i];
                j++;
            }
        }

        return new String( newData );
    }

    public void setTitile(String str)
    {
        title = str;
        setFrame( "TIT2", title );
    }
    public void setAlbum(String str)
    {
        album = str;
        setFrame("TALB", album);
    }
    public void setArtist(String str)
    {
        artist = str;
        setFrame( "TPE1", artist);
    }
    public void setYear(String str)
    {
        year = str;
        setFrame( "TYER", year );
    }
    public void setComment(String str)
    {
        byte []empty = new byte[1];
        empty[0] = 0;

        comment = str;
        setFrame( "COMM", "eng" + new String(empty) + comment );
    }
    public void setComposer(String str)
    {
        composer = str;
        setFrame( "TCOM", composer );
    }

    public boolean Save(Gauge gauge, boolean overwrite, boolean cleanTag) throws IOException
    {
        String newFile = file;

        if( overwrite )
        {
            int whereDot = file.indexOf( '.', 0 );

            if( whereDot == -1 )
            {
                throw new IOException( "File name has no . (dot)");
            }
            newFile = file.substring(0, whereDot) + "_edited.mp3";
        }

        // New file connection (output)
        FileConnection connOut = (FileConnection) Connector.open( newFile, Connector.READ_WRITE );
        if( connOut.exists() && ! overwrite )
        {
            throw new IOException( "Tags not saved because file " + newFile + " already exists. Set overwrite mode ON to overwrite existing files" );
        }
        if( ! connOut.exists())
        {
            connOut.create();
        }

        // Old file connection (input)
        FileConnection connIn = (FileConnection) Connector.open( file, Connector.READ_WRITE );

        // Open the streams
        OutputStream os = connOut.openOutputStream();
        InputStream is = connIn.openInputStream();

        // Input size
        long inSize = connIn.fileSize();

        System.out.println( "complieTag started");
        // Complie the new tag
        compileTag();
        
        System.out.println( "complieTag ended");
        
     //   Write header
     //   if( header[3] < 0x03 )
     //   {
      //      header[3] = 0x03;
     //   }
      //  os.write( header, 0, 6 );
        // Write size
     //   os.write( tagSizeBuffer );
        // Write extended header
    //    if( extendedHeaderExists )
    //    {
     //    os.write( extendedHeader );
     //   }
        // Tag data
        int i = 0;
    //    while( i < frames.size() )
   //     {
  //         System.out.println( "Writing frame " + i);
 //           Frame f = (Frame)frames.elementAt(i);
 //           os.write( f.serialize() );
  //          i++;
   //     }

        // Write padding
     //   i = 0;
     //   while( i < padding )
     //   {
     //       os.write( 0 );
    //        i++;
     //   }

        // Done writing all tag data. Now write the audio data
        long toRead = inSize;
       if( tagExists )
       {
           //  If tag exists, advance to audio data
            long skipped = is.skip( originalTagSize );
           if( skipped < originalTagSize )
           {
                long skippedThisTime = is.skip( originalTagSize - skipped );
                skipped = skipped + skippedThisTime;
            }
            toRead -= originalTagSize;
        }

        int read;
        long totalRead = 0;
        boolean first = true;
        int SIZE = 4096;
        do
        {
            byte[] buffer = new byte[SIZE];
            read = is.read( buffer, 0 , SIZE );
            totalRead += read;
            
            int perc = (int)((float)totalRead / (float)inSize * 100);
            System.out.println( "Percentage " + perc);
            gauge.setValue(perc);
            System.out.println( "Read = " + read + " " + "Buffer " + buffer.length + " " + totalRead + "/" + inSize );

            if( read != -1 )
            {
                if( read < SIZE )
                {
                    System.out.println("Read < size " + read + " "  + SIZE);
                    //byte []newBuffer = new byte[read];
                    for( int j = 0; j < read; j++ )
                    {
                        if(cleanTag & j> tagSize/4096){
                        os.write(buffer[j]);}
                        //newBuffer[j] = buffer[j];
                    }
                    // os.write(newBuffer, 0, read);

                }
                else
                {
                    os.write(buffer, 0, read);
                }
            }
        }while( read != -1 );


        is.close();
        
        
        os.close();
        System.out.println( "Output size " + connOut.fileSize());
        
        connIn.delete();
        connIn.close();
        String base = file.substring( file.lastIndexOf('/') + 1);
        System.out.println( "Base = " + base);
        connOut.rename(base);
        connOut.close();

        return true;
    }

    public void compileTag()
    {
        // If tag is newly added, then create the header etc.
        if( ! tagExists() )
        {
            // ID3
            header[0] = 'I';
            header[1] = 'D';
            header[2] = '3';

            // Version, 0x0300
            header[3] = 0x03;
            header[4] = 0x00;

            // Flags, 0x00
            header[5] = 0;

            // Do not write extended header
            extendedHeaderExists = false;
        }
        else
        {
            // Tag already exists.
        }

        // Size?
        System.out.println( "calculateNewTagSize() started" );
        tagSize = calculateNewTagSize();
        System.out.println( "calculateNewTagSize() done" );

        System.out.println( "getTagSizeBuffer() started");
        tagSizeBuffer = getTagSizeBuffer( tagSize );
        System.out.println( "getTagSizeBuffer() done");

        
    }

    private byte[] getTagSizeBuffer( int size )
    {
        byte []buffer = new byte[4];
        int i = 0;
        System.out.println( "X = " + size);
        for( ; i < 4; i++ )
        {
            byte thisByte = (byte)( size & ( 0x7F ) );
            System.out.println( thisByte);
            buffer[3-i] = thisByte;
            size = size >> 7;
        }

        return buffer;
    }
    private boolean deleteFrame(String id)
    {
        boolean deleted = false;
        int i = 0;

        while( i < frames.size() )
        {
            Frame f = (Frame) frames.elementAt(i);
            if( f.getFrameID().equalsIgnoreCase( id ) )
            {
                deleted = true;
                frames.removeElement(f);
            }
            i++;
        }
        return deleted;
    }
    
    
    
    private int calculateNewTagSize()
    {
        int size = 0;
        int i = 0;

        while( i < frames.size() )
        {
            Frame f = (Frame)frames.elementAt(i);
            // 10 for frame header
            int thisSize = f.getFrameSize() + 10;
            String id = f.getFrameID();
            System.out.println( "Frame " + id + " size = " + thisSize );
            size += thisSize;
            i++;
        }

        System.out.println( "Padding " + padding );
        size += padding;

        System.out.println( "New tagsize = " + size );
        return size;
    }
    private void setFrame( String frameID, String data )
    {
        int i = 0;
        boolean found = false;
        while( i < frames.size() )
        {
            Frame frame = (Frame)frames.elementAt( i );
            if( frame.getFrameID().compareTo( frameID ) == 0 )
            {
                // We found the frame. Edit the data in it
                frame.setData(data);
                found = true;
                break;
            }
            i++;
        }

        // If not found, then, we need to create a new frame
        if( ! found )
        {
            byte[] defaultFlags = new byte[2];
            defaultFlags[0] = 0;
            defaultFlags[1] = 0;

            Frame newFrame = new Frame( frameID, defaultFlags, data, null, true );
            frames.addElement( newFrame ); // Add the new frame
        }
    }

    private boolean isEditableTag(String id)
    {
        Vector editableTags = new Vector();

        editableTags.addElement( new String("TALB") );
        editableTags.addElement( new String("TCOM") );
        editableTags.addElement( new String("COMM") );
        editableTags.addElement( new String("TIT2") );
        editableTags.addElement( new String("TPE1") );
        editableTags.addElement( new String("TYER") );

        int i = 0;
        while( i < editableTags.size() )
        {
            String s = (String)editableTags.elementAt(i);
            if( s.compareTo(id) == 0 )
            {
                return true;
            }
            i++;
        }
        return false;
    }

    public byte[] getFrame( String frameID )
    {
        int i = 0;

        while( i < frames.size() )
        {
            Frame frame = (Frame)frames.elementAt( i );
            if( frame.getFrameID().compareTo( frameID ) == 0 )
            {
                // We found the frame
                return frame.getBytes();
            }
            i++;
        }
        return null;
    }

    public void addTag(Frame frm) {
        frames.addElement(frm);
    }
}