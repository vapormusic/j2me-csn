package com.gowtham.jmp3tag;

/*
 * MyFileUtilsInvoker.java
 *
 * Created on December 12, 2009, 11:41 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import java.util.Enumeration;
import javax.microedition.lcdui.*;
/**
 *
 * @author Gowtham
 */
public interface MyFileUtilsInvoker {
    

    public void updateDirectoryList(Enumeration CurrentDirEnum, boolean isRoot);
    
}
