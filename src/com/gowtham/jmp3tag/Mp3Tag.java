package com.gowtham.jmp3tag;

 /*
 * Mp3Tag.java
 *
 * Created on December 20, 2009, 9:05 PM
 */

import java.io.IOException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Gowtham
 */
public class Mp3Tag extends MIDlet implements CommandListener, 
        FileBrowserEvent, Runnable, SettingsEvent {

    private Gauge gauge;
    private Form progressForm;
    private Form tagEditor;
    private Command cmdBrowseFile;
    private Command cmdSaveTags;
    private Command cmdExit;
    private Command cmdAbout;
    private TextField textFieldPath;
    private TextField textFieldTitle;
    private TextField textFieldAlbum;
    private TextField textFieldArtist;
    private TextField textFieldYear;
    private TextField textFieldComposer;
    private Command cmdHelp;
    private Command cmdCancel;
    private Ticker ticker;
    private Tag tag;
    private FileBrowser fileBrowser;
    private FileBrowser imageBrowser;
    private Command replaceArtCommand;
    private Form imageForm = null;
    private Command showImageCmd = null;
    private Command backFromImageFormCmd = null;
    private Logger logger;
    private Command showLogCommand;

    private SettingsForm settingsForm;
    private Preferences pref;
    private Command configureSettingsCmd = null;
    private Command deleteArtCmd = null;

    /** Creates a new instance of Mp3Tag */
    public Mp3Tag()
    {
        logger = new Logger("Logger", this);
        initialize();
        ticker = new Ticker("Select Browse to load a MP3 file");
        tagEditor.setTicker(ticker);

        if( progressForm == null )
        {
            progressForm = new Form( "Saving...");
        }
        if( gauge == null )
        {
            gauge = new Gauge("Progress", false, 100, 0);
            gauge.setLabel( "Progress");
        }

        progressForm.append(gauge);
        

        showLogCommand = new Command("View Log",Command.SCREEN,10);

        if( fileBrowser == null )
            fileBrowser = new FileBrowser( "MP3 browser", this, "/", new String[] {"mp3"}, false, this );
        
        if( imageBrowser == null )
            imageBrowser = new FileBrowser( "Image browser", this, "/", new String[] {"jpg", "png", "bmp", "gif"}, false, this);
        
        if( imageForm == null )
            imageForm = new Form( "Album Art" );
        
        replaceArtCommand = new Command("Replace", Command.SCREEN, 0);
        imageForm.addCommand(replaceArtCommand);
        
        showImageCmd = new Command( "Album Art...", "Album Art...", Command.SCREEN, 100 );
        backFromImageFormCmd = new Command( "Back", "Back", Command.SCREEN, 0 );
        deleteArtCmd = new Command( "Delete", "Delete Image", Command.SCREEN, 1 );
        imageForm.addCommand(backFromImageFormCmd);
        imageForm.setCommandListener(this);
        tagEditor.addCommand(showImageCmd);
        tagEditor.addCommand(showLogCommand);

        settingsForm = new SettingsForm(this);
        settingsForm.setListener(this);

        printf( "Preferences loading");
        try
        {
            pref = new Preferences("settings");
            String overwrite = pref.get("overwrite");
            printf( "Overwrite mode read: " + overwrite );
            if( overwrite != null )
            {
                settingsForm.setOvewrite(overwrite);
            }
            String sort = pref.get( "sort" );
            if( sort != null )
            {
                printf( "Sort mode read: "+ sort);
                settingsForm.setSort(sort);
            }
            String showTicker = pref.get("showticker");
            if( showTicker != null )
            {
                printf( "Show ticker read: " + showTicker );
                settingsForm.setShowTicker(showTicker);
                if( showTicker.equalsIgnoreCase("No"))
                    tagEditor.setTicker(null);
            }
        }
        catch(Exception e) { e.printStackTrace();}

        configureSettingsCmd = new Command( "Settings...", "Settings...", Command.SCREEN, 10 );
        tagEditor.addCommand( configureSettingsCmd );
        
        setupImages();
    }
    
    public void populateTextBoxes( Tag tag )
    {
        try
        {
            textFieldAlbum.setString(tag.getAlbum());
            textFieldArtist.setString(tag.getArtist());
            textFieldTitle.setString(tag.getTitile());
            textFieldYear.setString(tag.getYear());
            textFieldComposer.setString( tag.getComposer());
        }
        catch( Exception e)
        {
            ticker.setString(e.toString());
        }

    }
    public void commandAction(Command command, Displayable displayable) {
    
        if (displayable == tagEditor)
        {
            if (command == cmdBrowseFile)
            {
                fileBrowser.show( getDisplay().getCurrent() );                
            }
            else if (command == cmdExit)
            {
                exitMIDlet();                
            }
            else if (command == cmdAbout)
            {
                info( "JMp3Tag v0.8 by Gowtham, built on 3 May 2011\n\nEmail: gowthamgowtham@gmail.com", "About");
            }
            else if (command == cmdSaveTags)
            {
                // Thread start
                new Thread( new Runnable() {
                               public void run() {
                                   handleSave();

                                    }
                            } ).start();
                
            }
            else if (command == cmdHelp)
            {
                info( "This program helps to edit the tags in MP3 files. Your phone should have "
                    + "support for JSR-75 API. To minimize the number of confirmation dialogs, "
                    + "grant the 'Read user data' and 'Write user data' permissions to this "
                    + "application.",
                    "Help"
                        );
            }
            else if (command == showImageCmd )
            {
                loadImage();
                Display.getDisplay(this).setCurrent(imageForm);
            }
            else if( command == configureSettingsCmd )
            {
                settingsForm.show();
            }
            else if( command == showLogCommand ) {
                logger.show(tagEditor);
            }
        }
        else if ( displayable == imageForm )
        {
            if( command == backFromImageFormCmd )
            {
                Display.getDisplay(this).setCurrent(tagEditor);
            }
            else if( command == deleteArtCmd )
            {
                // Note that we display main form
                tag.deleteAPICFrame();
                ticker.setString("Remember to select Save to remove the art");
                Display.getDisplay(this).setCurrent(tagEditor);
            }
            else if( command == replaceArtCommand ) {
                logger.log("Showing image browser");
                imageBrowser.show(imageForm);
            }
        }
    
    }

    /** This method initializes UI of the application.
     */
    private void initialize() {
        
        getDisplay().setCurrent(get_tagEditor());
        // Insert post-init code here
        //if( System.getProperty( "microedition.io.file.FileConnection.version" ) == null )
        //{
        //    Alert alert = new Alert( "Error", "JSR 75 not available in your phone", null, AlertType.ERROR );
        //    getDisplay().setCurrent( alert );
        //
        //    exitMIDlet();
        //}
        //else
        //{
        //    Alert alert = new Alert( "Info", "JSR 75 available in your phone", null, AlertType.INFO );
        //    getDisplay().setCurrent( alert );
        //
        //}
    }
    
    /**
     * This method should return an instance of the display.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }
    
    /**
     * This method should exit the midlet.
     */
    public void exitMIDlet() {
        getDisplay().setCurrent(null);
        destroyApp(true);
        notifyDestroyed();
    }

    /** This method returns instance for tagEditor component and should be called instead of accessing tagEditor field directly.
     * @return Instance for tagEditor component
     */
    public Form get_tagEditor() {
        if (tagEditor == null) {
            // Insert pre-init code here
            tagEditor = new Form("JMp3Tag", new Item[] {
                get_textFieldPath(),
                get_textFieldTitle(),
                get_textFieldAlbum(),
                get_textFieldArtist(),
                get_textFieldYear(),
                get_textFieldComposer()
            });
            tagEditor.addCommand(get_cmdBrowseFile());
            tagEditor.addCommand(get_cmdSaveTags());
            tagEditor.addCommand(get_cmdExit());
            tagEditor.addCommand(get_cmdAbout());
            tagEditor.addCommand(get_cmdHelp());
            tagEditor.setCommandListener(this);
            // Insert post-init code here
        }
        return tagEditor;
    }

    /** This method returns instance for cmdBrowseFile component and should be called instead of accessing cmdBrowseFile field directly.
     * @return Instance for cmdBrowseFile component
     */
    public Command get_cmdBrowseFile() {
        if (cmdBrowseFile == null) {
            // Insert pre-init code here
            cmdBrowseFile = new Command("Browse", Command.SCREEN, 1);
            // Insert post-init code here
        }
        return cmdBrowseFile;
    }

    /** This method returns instance for cmdSaveTags component and should be called instead of accessing cmdSaveTags field directly.
     * @return Instance for cmdSaveTags component
     */
    public Command get_cmdSaveTags() {
        if (cmdSaveTags == null) {
            // Insert pre-init code here
            cmdSaveTags = new Command("Save", Command.SCREEN, 1);
            // Insert post-init code here
        }
        return cmdSaveTags;
    }

    /** This method returns instance for cmdExit component and should be called instead of accessing cmdExit field directly.
     * @return Instance for cmdExit component
     */
    public Command get_cmdExit() {
        if (cmdExit == null) {
            // Insert pre-init code here
            cmdExit = new Command("Exit", Command.EXIT, 1);
            // Insert post-init code here
        }
        return cmdExit;
    }

    /** This method returns instance for cmdAbout component and should be called instead of accessing cmdAbout field directly.
     * @return Instance for cmdAbout component
     */
    public Command get_cmdAbout() {
        if (cmdAbout == null) {
            // Insert pre-init code here
            cmdAbout = new Command("About", Command.SCREEN, 1);
            // Insert post-init code here
        }
        return cmdAbout;
    }

    /** This method returns instance for textFieldPath component and should be called instead of accessing textFieldPath field directly.
     * @return Instance for textFieldPath component
     */
    public TextField get_textFieldPath() {
        if (textFieldPath == null) {
            // Insert pre-init code here
            textFieldPath = new TextField("MP3 file", "", 1000, TextField.ANY | TextField.UNEDITABLE );
            // Insert post-init code here
        }
        return textFieldPath;
    }

    /** This method returns instance for textFieldTitle component and should be called instead of accessing textFieldTitle field directly.
     * @return Instance for textFieldTitle component
     */
    public TextField get_textFieldTitle() {
        if (textFieldTitle == null) {
            // Insert pre-init code here
            textFieldTitle = new TextField("Title", null, 120, TextField.ANY);
            // Insert post-init code here
        }
        return textFieldTitle;
    }

    /** This method returns instance for textFieldAlbum component and should be called instead of accessing textFieldAlbum field directly.
     * @return Instance for textFieldAlbum component
     */
    public TextField get_textFieldAlbum() {
        if (textFieldAlbum == null) {
            // Insert pre-init code here
            textFieldAlbum = new TextField("Album", null, 120, TextField.ANY);
            // Insert post-init code here
        }
        return textFieldAlbum;
    }

    /** This method returns instance for textFieldArtist component and should be called instead of accessing textFieldArtist field directly.
     * @return Instance for textFieldArtist component
     */
    public TextField get_textFieldArtist() {
        if (textFieldArtist == null) {
            // Insert pre-init code here
            textFieldArtist = new TextField("Artist", null, 120, TextField.ANY);
            // Insert post-init code here
        }
        return textFieldArtist;
    }

    /** This method returns instance for textFieldYear component and should be called instead of accessing textFieldYear field directly.
     * @return Instance for textFieldYear component
     */
    public TextField get_textFieldYear() {
        if (textFieldYear == null) {
            // Insert pre-init code here
            textFieldYear = new TextField("Year", null, 120, TextField.ANY);
            // Insert post-init code here
        }
        return textFieldYear;
    }

    public TextField get_textFieldComposer() {
        if (textFieldComposer == null) {
            // Insert pre-init code here
            textFieldComposer = new TextField("Composer", null, 120, TextField.ANY);
            // Insert post-init code here
        }
        return textFieldComposer;
    }
    /** This method returns instance for cmdHelp component and should be called instead of accessing cmdHelp field directly.
     * @return Instance for cmdHelp component
     */
    public Command get_cmdHelp() {
        if (cmdHelp == null) {
            // Insert pre-init code here
            cmdHelp = new Command("Help", Command.SCREEN, 1);
            // Insert post-init code here
        }
        return cmdHelp;
    }

    /** This method returns instance for cmdCancel component and should be called instead of accessing cmdCancel field directly.
     * @return Instance for cmdCancel component
     */
    public Command get_cmdCancel() {
        if (cmdCancel == null) {
            // Insert pre-init code here
            cmdCancel = new Command("Cancel", Command.SCREEN, 1);
            // Insert post-init code here
        }
        return cmdCancel;
    }
    
    public void startApp() {
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
        saveSettings();
    }

    
    public void run() {}
    
    public void handleSave() {
        if (tag != null)
        {
            try
            {
                gauge.setValue(0);
                getDisplay().setCurrent(progressForm);
                // Update the tag values from textboxes
                tag.setAlbum(textFieldAlbum.getString());
                tag.setArtist(textFieldArtist.getString());
                tag.setTitile(textFieldTitle.getString());
                tag.setYear(textFieldYear.getString());
                tag.setComposer(textFieldComposer.getString());

                ticker.setString("Saving... Please wait");
                tag.Save(gauge, settingsForm.canOverwrite(),false );

                ticker.setString("Tags saved successfully");
                getDisplay().setCurrent(tagEditor);
            }
            catch (Exception e)
            {
                ticker.setString(e.toString());
                getDisplay().setCurrent(tagEditor);
            }
        } 
        else
        {
            ticker.setString("No MP3 file loaded!");
        }
    }

    public void info( String msg, String title ) {

            //if ( false )
            {
            Alert alert = new Alert( title);
            alert.setType( AlertType.INFO );
            

            alert.setString( msg );
            alert.setTimeout( Alert.FOREVER );
            getDisplay().setCurrent( alert );
            }
    }
    public void debug( String msg ) {
            
            //if ( false ) 
            {
            Alert alert = new Alert( "DEBUG");
            alert.setType( AlertType.INFO );
            
            alert.setString( msg );
            alert.setTimeout( Alert.FOREVER );
            getDisplay().setCurrent( alert );
            }
    }
    public void error( String msg ) {
            Alert alert = new Alert( "ERROR");
            alert.setType( AlertType.ERROR );
            
            alert.setString( msg );
            alert.setTimeout( 3000 );
            getDisplay().setCurrent( alert );
    }

    
    public void handleRead()
    {
        tag = new Tag(textFieldPath.getString());
        try
        {
            tag.Read();
            if (tag.tagExists())
            {
                ticker.setString("Tags loaded");
            } else
            {
                ticker.setString("No tags found");
            }
        }
        catch (Exception e)
        {
            ticker.setString(e.toString());
        }

        populateTextBoxes(tag);
    }

    public void someThingSelected( FileBrowser fb, String path )
    {
        if( fb == fileBrowser )
        {
            textFieldPath.setString(path);
            handleRead();
        }
        if( fb == imageBrowser ) {
            Display.getDisplay(this).setCurrent(imageForm);
            replaceAlbumArt(path);
        }
    }

    private void loadImage()
    {
        imageForm.deleteAll();
        imageForm.removeCommand( deleteArtCmd );

        StringItem error = new StringItem( "Error: ", "");
        StringItem mimeType = new StringItem("Image type: ", "");
        StringItem description = new StringItem("Description: ", "");
        
        if( tag == null )
        {
            imageForm.append(error);
            error.setText( "No MP3 file loaded" );
            return;
        }

        if( ! tag.tagExists() )
        {
            imageForm.append(error);
            error.setText( "No tag exists in the loaded MP3 file" );
            return;
        }

        byte [] imageBytes = tag.getFrame( "APIC" );
        
        if( imageBytes == null )
        {
            imageForm.append(error);
            error.setText( "No album art exists in the loaded MP3 file" );
            return;
        }

        printf( "Image exists" );
        printf( "Size : " + imageBytes.length );

        APICTag apicTag = new APICTag(imageBytes);
        printf( apicTag.getMimeType() );
        printf( apicTag.getDescription() );
        mimeType.setText( apicTag.getMimeType() );
        description.setText( apicTag.getDescription() );
        int start = apicTag.getImageStartIndex();
        printf( "Image length = " + apicTag.getImageLength() );
        printf( "Bytes: " + Integer.toHexString( imageBytes[start] & 0xFF )
                          + Integer.toHexString( imageBytes[start+1] & 0xFF ) );
        Image img = Image.createImage( imageBytes,
                                       start,
                                       apicTag.getImageLength() );
        imageForm.append(mimeType);
        imageForm.append(description);
        imageForm.append(img);
        imageForm.addCommand(deleteArtCmd);
    }

    private void printf( String str )
    {
        System.out.println( str );
    }

    public void settingsSaved()
    {
        String overwrite = settingsForm.canOverwriteString();
        printf( "Overwrite mode: " + overwrite );

        if( overwrite != null )
        {
            pref.put("overwrite", overwrite);
        }

        String sortMode = settingsForm.canSortString();
        printf( "Sort mode: " + sortMode );

        if( sortMode != null )
        {
            pref.put( "sort", sortMode);
            fileBrowser.setSort( settingsForm.canSort() );
        }

        String showTicker = settingsForm.canShowTickerString();
        printf( "Ticker show: "+ showTicker);
        if( showTicker != null )
        {
            pref.put( "showticker", showTicker );
        }
        if( settingsForm.canShowTicker() )
        {
            tagEditor.setTicker(ticker);
        }
        else
        {
            tagEditor.setTicker(null);
        }
    }

    private void saveSettings()
    {
        try
        {
            pref.save();
            printf( "Save ok");
        }
        catch(Exception e)
        {
            error("Error saving settings: " + e.toString());
        }
    }

    /**
     * Replaces album art with this one
     * @param path 
     */
    private void replaceAlbumArt(String path) {
        tag.deleteAPICFrame();
        try {
            APICTag newTag = new APICTag(path);
            newTag.setLogger(logger);
            byte[] imageArray = newTag.toByteArray();
            Frame artFrame = new Frame("APIC", new byte[] { 0x00, 0x00 }, null, imageArray, false);
            tag.addTag(artFrame);
            int offset = newTag.getImageOffset();
            
            Image img = Image.createImage(imageArray, offset, imageArray.length-offset);
            imageForm.deleteAll();
            StringItem sItem = new StringItem("New image:", path);
            imageForm.append(sItem);
            imageForm.append(img);
            ticker.setString("Remember to select Save to edit the art image");
        }
        catch(IOException ioe) {
            logger.log(ioe.toString());
        }
    }

    /**
     * Loads up images and associates them with file browser
     */
    private void setupImages() {
        
        try {
            Image folderImage = Image.createImage("folder.png");
            Image musicImage = Image.createImage("music.png");
            Image upImage = Image.createImage("up.png");
            Image imgImage = Image.createImage("image.png");
            
            fileBrowser.setFileImage(musicImage);
            fileBrowser.setUpImage(upImage);
            fileBrowser.setFolderImage(folderImage);
            
            imageBrowser.setFileImage(imgImage);
            imageBrowser.setUpImage(upImage);
            imageBrowser.setFolderImage(folderImage);
        }
        catch(Exception e) { 
            logger.log(e.toString());
        }
    }
}
