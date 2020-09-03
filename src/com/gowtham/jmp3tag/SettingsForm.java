package com.gowtham.jmp3tag;

/**
 *
 * @author Gowtham
 */

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class SettingsForm implements CommandListener {

    private Form f;
    private ChoiceGroup overwrite;
    private ChoiceGroup sortEntries;
    private ChoiceGroup showTicker;
    private MIDlet m;
    private Displayable previousDisplay;
    private Command saveCommand;
    private Command cancelCommand;
    private SettingsEvent event;

    public SettingsForm(MIDlet midlet) {
        m = midlet;
        f = new Form( "Settings" );

        overwrite = new ChoiceGroup( "Overwrite existing files?", ChoiceGroup.EXCLUSIVE );
        overwrite.append( "No", null );
        overwrite.append( "Yes", null);
        overwrite.setSelectedIndex(0, true);

        sortEntries = new ChoiceGroup("Sort entries in file browser?", ChoiceGroup.EXCLUSIVE );
        sortEntries.append( "No", null);
        sortEntries.append( "Yes", null);
        sortEntries.setSelectedIndex(0, true);

        showTicker = new ChoiceGroup("Show status ticker?", ChoiceGroup.EXCLUSIVE );
        showTicker.append( "No", null);
        showTicker.append( "Yes", null);
        showTicker.setSelectedIndex(1, true);

        saveCommand = new Command( "OK", Command.OK, 0 );
        cancelCommand = new Command( "Cancel", Command.CANCEL, 1 );
        f.addCommand(saveCommand);
        f.addCommand(cancelCommand);

        f.append(overwrite);
        f.append(sortEntries);
        f.append(showTicker);
        
        f.setCommandListener(this);
    }

    public void setListener(SettingsEvent e)
    {
        event = e;
    }

    public String canShowTickerString()
    {
        return showTicker.getString( showTicker.getSelectedIndex() );
    }
    public boolean canShowTicker()
    {
        if( canShowTickerString().equalsIgnoreCase("No") )
            return false;

        return true;
    }
    public void setShowTicker(String s)
    {
        if( s.equalsIgnoreCase("No") )
            showTicker.setSelectedIndex( 0, true );
        else
            showTicker.setSelectedIndex( 1, true );
    }
    public boolean canOverwrite()
    {
        String overwriteStr = overwrite.getString( overwrite.getSelectedIndex() );
        if( overwriteStr.equalsIgnoreCase("No") )
            return false;
        return true;
    }
    public String canOverwriteString()
    {
        return overwrite.getString( overwrite.getSelectedIndex() );
    }
    public void setOvewrite( String s )
    {
        if( s.equalsIgnoreCase("No") )
            overwrite.setSelectedIndex( 0, true );
        else
            overwrite.setSelectedIndex( 1, true );
    }

    public String canSortString()
    {
        return sortEntries.getString( sortEntries.getSelectedIndex() );
    }
    public boolean canSort()
    {
        if( canSortString().equalsIgnoreCase( "Yes") )
            return true;

        return false;
    }
    public void setSort(String s)
    {
        if( s.equalsIgnoreCase("No") )
            sortEntries.setSelectedIndex( 0, true);
        else
            sortEntries.setSelectedIndex( 1, true);
    }
    public void show()
    {
        previousDisplay = Display.getDisplay(m).getCurrent();
        Display.getDisplay(m).setCurrent(f);
    }

    public void commandAction( Command c, Displayable d )
    {
        if( c == saveCommand )
        {
            event.settingsSaved();
            Display.getDisplay(m).setCurrent(previousDisplay);
        }
        else if( c == cancelCommand )
        {
            Display.getDisplay(m).setCurrent(previousDisplay);
        }
    }
}