/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.HttpsConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;

public class Midlet extends MIDlet implements CommandListener {

  private Command exitCommand = new Command("Exit", Command.EXIT, 1);

  private Command sendCommand = new Command("Send", Command.OK, 1);

  private Command backCommand = new Command("Upload", Command.OK, 1);

  private Display display;

  private String defaultURL = "https://accounts.spotify.com:443/api/token?grant_type=client_credentials";

  private Form mainForm, resultForm;

  private TextField URL = new TextField(null, defaultURL, 250, TextField.URL);

  private StringItem resultItem;

  public Midlet() {
    display = Display.getDisplay(this);
  }

  public void startApp() {
    mainForm = new Form("Address");
    mainForm.append(URL);
    mainForm.addCommand(sendCommand);
    mainForm.addCommand(exitCommand);
    mainForm.setCommandListener(this);
    display.setCurrent(mainForm);
  }

  public void pauseApp() {
  }

  public void destroyApp(boolean unconditional) {
  }

  public void commandAction(Command c, Displayable s) {
    if (c == sendCommand) {
      String result = "";
      resultItem = new StringItem(null, result);
      resultForm = new Form("Result");
      String URLString = URL.getString();

      try {
        result = requestUsingGET(URLString);
      } catch (Exception e) {
        result = "Falied";
      }

      resultItem.setText(result);
      resultForm.append(resultItem);
      resultForm.addCommand(backCommand);
      resultForm.setCommandListener(this);
      display.setCurrent(resultForm);
    } else if (c == backCommand) {
      URL.setString(defaultURL);
      display.setCurrent(mainForm);
    } else {
      destroyApp(false);
      notifyDestroyed();
    }
  }

  private String requestUsingGET(String URLString) throws Exception {
     
    HttpsConnection hpc = null;
    InputStream dis = null;
    InputStream is;
    boolean newline = false;
    String content = "";
    try {
      hpc = (HttpsConnection) Connector.open(URLString);
      hpc.setRequestProperty("Authorization", "Basic "+Base64DE.encode("a644b4d1273843d89060d0b5677521d1:f90e34201c1b42cdae2b9dbb5f663d07"));
      hpc.setRequestMethod("POST");
      hpc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
      hpc.setRequestProperty("Content-length", "0"); 
      hpc.setRequestProperty("Content-Length", "0");
 
int character;

      
      dis = hpc.openInputStream();
      
     
      while ((character = dis.read()) != -1) {
			if ((char) character == '\\') {
				newline = true;
				continue;
			} else {
				if ((char) character == 'n' && newline) {
					content += "\n";
					newline = false;
				} else if (newline) {
					content += "\\" + (char) character;
					newline = false;
				} else {
					content += (char) character;
					newline = false;
				}
			}
		}
      

      
      if (hpc != null)
        hpc.close();
      if (dis != null)
        dis.close();
      System.out.println("hum:" + content);
    } catch (Exception e2) {
        
        System.out.println("bruh: " + e2 );
        e2.printStackTrace();
    }

    return content;
  }
}
