package com.gowtham.jmp3tag;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Enumeration;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.io.*;
import javax.microedition.io.file.*;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.io.file.FileConnection;

/**
 *
 * @author Gowtham
 */
public class FileBrowser extends Form implements CommandListener, ItemStateListener
{
    private List fileBrowser;
    private boolean sortEntries;
    private String currentRoot;
    //private String extension;
    private Hashtable extnTable;
    private boolean isDirectory;
    private Command selectCommand;
    private MIDlet m;
    private Displayable next;
    private Command cancelCommand;
    private Command backCommand;
    private boolean cancelled;
    private String selection;
    private FileBrowserEvent fbEvent;

    private Image musicImage;
    private Image folderImage;
    private Image upImage;


    public FileBrowser( String title, FileBrowserEvent aEvent, String aRoot, String []aExtn, boolean dirBrowsing, MIDlet aMidlet )
    {
        super( title );
        sortEntries = false;
        fbEvent = aEvent;
        currentRoot = aRoot;
        //extension = aExtn;
        isDirectory = dirBrowsing;
        m = aMidlet;

        if( aExtn != null ) {
            extnTable = new Hashtable(aExtn.length);
            for(int i=0; i<aExtn.length; ++i)
                extnTable.put(aExtn[i].toUpperCase(),"");
        }
        
        cancelCommand = new Command( "Cancel", Command.SCREEN, 0 );
        backCommand = new Command("Cancel", Command.BACK, 1);
        
        if( ! isDirectory )
        {
            fileBrowser = new List( title, List.IMPLICIT );
        }
        else
        {
            fileBrowser = new List( title, List.IMPLICIT );
            selectCommand = new Command( "Select", Command.SCREEN, 0 );
            fileBrowser.addCommand( selectCommand );
        }
        //fileBrowser.addCommand(cancelCommand);
        fileBrowser.addCommand(backCommand);
        fileBrowser.setCommandListener(this);
        


        new Thread(
            new Runnable() {
                public void run() {
                       getDirContents();
                }
            }
        ).start();

    }

    public void setSort( boolean sort )
    {
        sortEntries = sort;
    }
    public boolean getSort()
    {
        return sortEntries;
    }
    public synchronized void getDirContents()
    {
        FileConnection fc = null;
        Enumeration list = null;

        try
        {
            if( currentRoot.compareTo( "/") == 0 )
            {
                // This is the filesystem root
                list = FileSystemRegistry.listRoots();
            }
            else
            {
                // This is some inner folder
                fc = (FileConnection) Connector.open( "file://" + currentRoot );
                if( fc != null )
                {
                    list = fc.list( "*", false );
                }
            }
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        finally
        {
            if( fc != null )
            {
                try { fc.close(); }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
            }
            updateListBox( list );
        }
    }

    public void updateListBox( Enumeration list )
    {
        if( list == null )
        {
            return;
        }


        fileBrowser.deleteAll();
        Vector dirs = new Vector();
        Vector files = new Vector();

        // Add a .. to navigate up if not in root
        if( currentRoot.compareTo( "/") != 0 )
        {
            fileBrowser.append( "..", upImage );
        }

        // Now, separate out files and dirs
        String basename = null;
        while( list.hasMoreElements() )
        {
            basename = (String) list.nextElement();

            // A directory?
            if( basename.endsWith("/") )
            {
                dirs.addElement(basename);
            }
            // A file
            else
            {
                System.out.println("File " + basename );
                //if( extension.equalsIgnoreCase("*") )
                if( extnTable == null )
                {
                    files.addElement( basename );
                }
                else
                {
                    int dotIndex = basename.lastIndexOf( '.' );
                    if( dotIndex != -1 )
                    {
                        String thisExtn = basename.substring(dotIndex+1);
                        //System.out.println( thisExtn + " " + extension );
                        if( extnTable.containsKey(thisExtn.toUpperCase()))
                            files.addElement( basename );
                    }
                }
            }
        }

        // Sort them, if asked to
        if( sortEntries )
        {
            sortVector(dirs);
            sortVector(files);
        }
        
        // Now, populate these to the listbox. First dirs, then, files
        int i = 0;
        while( i < dirs.size() )
        {
            fileBrowser.append( (String)dirs.elementAt(i), folderImage);
            i++;
        }
        i = 0;
        // When browsing directories, do not add files!
        if( ! isDirectory )
        {
            while( i < files.size() )
            {
                fileBrowser.append( (String)files.elementAt(i), musicImage);
                i++;
            }
        }
        fileBrowser.setTitle("File Browser");
    }

    public void commandAction( Command c, Displayable d)
    {
        String selectedItem = fileBrowser.getString( fileBrowser.getSelectedIndex() );

        if( c == fileBrowser.SELECT_COMMAND )
        {
            if( isDirectory )
            {
                if( selectedItem.equalsIgnoreCase( "..") ||
                    selectedItem.endsWith( "/" ) )
                {
                    traverse( selectedItem );
                }
            }
            else
            {
                // File browser
                if( selectedItem.equalsIgnoreCase( ".." ) ||
                    selectedItem.endsWith( "/" ) )
                {
                    // If a folder, traverse it...
                    traverse( selectedItem );
                }
                else
                {
                    // Otherwise, it is a file. So, we are done choosing a file
                    cancelled = false;
                    selection = "file://" + currentRoot + selectedItem;
                    fbEvent.someThingSelected(this, selection);
                    Display.getDisplay(m).setCurrent(next);
                }
            }
        }
        else if( ( c == cancelCommand ) || ( c == backCommand ) )
        {
            cancelled = true;
            Display.getDisplay(m).setCurrent(next);
        }
        else if( c == selectCommand )
        {
            // File or dir selected
            // Check if selectedItem is '..'. If it is, ignore the select
            // command
            if( selectedItem.equalsIgnoreCase( "..") )
                return;
            cancelled = false;
            selection = "file://" + currentRoot + selectedItem;
            
            Display.getDisplay(m).setCurrent(next);
            fbEvent.someThingSelected(this, selection);
        }
    }

    public void show( Displayable aNext )
    {
        cancelled = true;
        selection = "";
        next = aNext;
        Display.getDisplay( m ).setCurrent( fileBrowser );
    }

    public void traverse( String selectedItem )
    {
        fileBrowser.setTitle( "Loading..." );
        if( selectedItem.equalsIgnoreCase( ".." ) )
        {
            int length = currentRoot.length();
            int index = currentRoot.lastIndexOf( '/', length-2 );
            if( index == -1 )
            {
                // No slash. Means, this is now the root
                currentRoot = "/";
            }
            else
            {
                // Some folder. So, remove the basename and form a new
                // folder name
                currentRoot = currentRoot.substring( 0, index+1 );
            }
        }
        else
        {
            currentRoot = currentRoot + selectedItem;
        }
        // Now, call getDirContents to populate the new set of files
        // and folders. This is in a separate thread for responsiveness
        new Thread(
                new Runnable() {
                    public void run() {
                            getDirContents();
                        }
                }
            ).start();
    }

    public void itemStateChanged( Item aItem )
    {
        if( fileBrowser.getString( fileBrowser.getSelectedIndex() ).equalsIgnoreCase( "..") )
        {
            fileBrowser.removeCommand(selectCommand);
        }
        else
        {
            fileBrowser.addCommand(selectCommand);
        }
    }

    private void sortVector(Vector v)
    {
        String left = null;
        String right = null;
        String leftUpperCase = null;
        String rightUpperCase = null;

        boolean swapped = false;

        do
        {
            swapped = false;
            for( int i = 0; i < v.size() - 1; i++ )
            {
                left = (String)v.elementAt(i);
                right = (String)v.elementAt(i+1);
                leftUpperCase = left.toUpperCase();
                rightUpperCase = right.toUpperCase();
                if( leftUpperCase.compareTo(rightUpperCase) > 0 )
                {
                    v.setElementAt(left, i+1);
                    v.setElementAt(right, i);
                    swapped =  true;
                }
            }
        }while( swapped );
    }
    public Image getFileImage() {
        return musicImage;
    }
    public void setFileImage(Image img) {
        musicImage = img;
    }
    
    public Image getUpImage() {
        return upImage;
    }
    public void setUpImage(Image img) {
        upImage = img;
    }
    
    public Image getFolderImage() {
        return folderImage;
    }
    public void setFolderImage(Image img) {
        folderImage = img;
    }
}
