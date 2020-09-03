package com.gowtham.jmp3tag;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gowtham
 */
public class Logger implements CommandListener {

    private Displayable next;
    private Form form;
    private Command dismissCmd;
    private Command clearCmd;
    private MIDlet midlet;
    
    public void log( String msg ) {
        form.append(msg);
        form.append("\n");
    }

    public void log(int i) {
        log(Integer.toString(i));
    }
    public void clear() {
        form.deleteAll();
    }
    
    public Logger( String title, MIDlet aMidlet ) {
        form = new Form(title);
        dismissCmd = new Command("Dismiss", Command.OK, 0);
        clearCmd = new Command("Clear",Command.SCREEN,1);
        form.addCommand(dismissCmd);
        form.addCommand(clearCmd);
        form.setCommandListener(this);
        midlet = aMidlet;
    }
    
    public void show(Displayable aNext) {
        next = aNext;
        Display.getDisplay(midlet).setCurrent(form);
    }

    public void commandAction(Command c, Displayable d) {
        if( d == form ) {
            if( c == dismissCmd )
                Display.getDisplay(midlet).setCurrent(next);
            else if( c == clearCmd )
                clear();
        }
    }
    
}
