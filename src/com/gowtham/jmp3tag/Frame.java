package com.gowtham.jmp3tag;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gowtham
 */
public class Frame {
    private final int headerSize = 10;
    private String id;
    private int size;
    private byte[] flags;
    private String data;
    private byte[] bytes;
    private boolean isEditableFrame;

    public byte[] getBytes()
    {
        return bytes;
    }
    public Frame( String aId, byte aFlags[], String aData, byte[] aBytes, boolean aIsEditableFrame )
    {
        id = aId;
        flags = aFlags;
        
        isEditableFrame = aIsEditableFrame;

        if( isEditableFrame )
        {
            data = aData;
            size = data.length();
        }
        else
        {
            bytes = aBytes;
            size = bytes.length;
        }        
    }
    public int getFrameSize()
    {
        return size;
    }
    public String getFrameID()
    {
        return id;
    }
    public void setData( String aData )
    {
        data = aData;
        size = data.length();
    }
    public byte[] serialize()
    {
        // 2 because of 2 extra characters during serializing, 3 BOM (1,FF,FE) and null char
        byte []buffer = new byte[ size + headerSize + 2 ];
        int index = 0;
        int i = 0;
        
        // Serialize the frame ID
        for( ; i < id.length(); i++)
        {
            buffer[index] = (byte)id.charAt(i);
            index++;
        }

        // Serialize the frame size
        i = 0;
        System.out.println("\tSize (before BOM and null addition, 2 extra)" + size);
        int number = size + 2;
        for( ; i <= 3; i++ )
        {

            byte thisByte = (byte)( number & ( 0xFF ) );
            buffer[index+3-i] = thisByte;
            System.out.println( thisByte);
            number = number >> 8;
        }
        index += 4; // Increment index by 4 places, size has now been stored

        // Serialize the frame flags
        i = 1;
        for( ; i>=0; i-- )
        {
            buffer[index] = flags[i];
            index++;
        }

        // Finally, add the frame data
        i = 0;
        if( isEditableFrame )
        {
            // BOM - Byte order mark
            // buffer[index] = (byte)0x01;           index++;
            // buffer[index] = (byte)0xFF;           index++;
            // buffer[index] = (byte)0xFE;           index++;

            buffer[index] = (byte)0;                 index++;
            for( ; i < data.length(); i++ )
            {
                buffer[index] = (byte)data.charAt(i);
                index++;
            }
            // Null character at the end
            buffer[index] = 0x00;                 index++;
        }
        else
        {
            for( ; i < bytes.length; i++ )
            {
                buffer[index] = bytes[i];
                index++;
            }
        }

        // Debug
        System.out.println( "Serializing frame " + id);
        for( int j = 0; j < buffer.length; j++ )
        {
            System.out.print(buffer[j] + " ");
        }
        System.out.println("\n");
        // All done, now return the buffer
        return buffer;
    }

    public static int calculateFrameSize( byte data[], int min, int max )
    {
        int size = 0;

        for( int i = min; i <= max; i++ )
        {
            System.out.println( "Byte : " + data[i]);
            int thisByte = data[i];
            if( thisByte < 0 )
            {
                thisByte = 256 + thisByte;
                System.out.println( "Corrected byte " + thisByte);
            }
            if( i > min )
            {
                size = size << 8;
            }
            size += thisByte;
        }
        if( size < 0 )
        {
            System.out.println( "Negative " + size);
        }
        return size;
    }

    // Check if a frame is valid.
    public static boolean isValidFrame( String frame )
    {
        for( int pos = 0; pos < 4; pos++ )
        {
            char letter = frame.charAt(pos);
            if( ( (  'A' <= letter ) && ( letter <= 'Z' ) ) ||
                ( (  '0' <= letter ) && ( letter <= '9' ) )
              )
            {
                // Fine;
            }
            else
            {
                return false;
            }

        }

        return true;
    }
}
