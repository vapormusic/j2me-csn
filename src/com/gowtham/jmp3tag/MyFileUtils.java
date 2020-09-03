package com.gowtham.jmp3tag;

/*
 * MyFileUtils.java
 *
 * Created on December 12, 2009, 11:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Gowtham
 */

import java.util.*;
import java.io.*;
import javax.microedition.io.*;
import javax.microedition.io.file.*;
import javax.microedition.lcdui.*;

public class MyFileUtils {
    
    MyFileUtilsInvoker invoker;
    
    /**
     * Creates a new instance of MyFileUtils
     */
    public MyFileUtils( MyFileUtilsInvoker invoker ) {
        this.invoker = invoker;
    }
    
    public synchronized void getDirectoryContents( String currentDir ) {
        Enumeration currentDirEnum = null;
        FileConnection fc = null;
        boolean isRoot = false;
        
        try {
            if( currentDir.equals( "/" ) ) {
                currentDirEnum = FileSystemRegistry.listRoots();
                isRoot = true;
            } else {
                fc = (FileConnection) Connector.open( "file://" + currentDir );
                if( fc != null ) {
                    currentDirEnum = fc.list();
                    //while( currentDirEnum.hasMoreElements() )
                    //{
                    //    MyFileUtils.debug( "New element: " + currentDirEnum.nextElement() );
                    //}
                }
            }
        } catch( Exception e ) {
            System.out.println( e.toString());
        }
        
        finally {
            try {
                if( fc != null ) {
                    fc.close();
                }
            } catch( Exception e ) {
                System.out.println( e.toString());
            }
            
            fc = null;
            invoker.updateDirectoryList( currentDirEnum, isRoot );
        }
    }
    
    public static void debug( String str )
    {
        System.out.println( str );
    }
}