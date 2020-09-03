/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;


import com.gowtham.jmp3tag.Tag;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.HttpsConnection;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VolumeControl;
import javax.microedition.midlet.MIDlet;




public class Midlet extends MIDlet implements CommandListener, PlayerListener {

  private Command exitCommand = new Command("Exit", Command.EXIT, 1);

  private Command sendCommand = new Command("Search", Command.OK, 1);

  private Command backCommand = new Command("Upload", Command.OK, 1);
  
  private Command help = new Command("Pause", Command.HELP, 2);
  
  private Command help2 = new Command("Resume", Command.HELP, 3);
  
  private Command search = new Command("Search", Command.OK, 2);
  

  private Display display;

  private String defaultURL = "mirrors";

  private Form mainForm, resultForm;

  private TextField URL ;
  
  private String text;

  private StringItem resultItem;
  
  private Image scaledimage ;
  
  
  /// JSon Objects
  private String musicid = "";
  private String playingmusicid = "";
  private String musicurl = "";
  private String imageurl = "";
  private String title = "";
  private String album = "";
  private String artist = "";
  
  private Gauge gauge;
  private Image image;
  
  private boolean imageappend = false;
  Player player;
  FileConnection fileCon;
  ImageItem artimage;
  StringItem titleframe;
  StringItem artistframe;
  StringItem albumframe;
 

  public Midlet() {
    display = Display.getDisplay(this);
  }

  public void startApp() {
    mainForm = new Form("CSN Player");
    URL = new TextField(null, defaultURL, 250, TextField.ANY);
    mainForm.append(URL);
    mainForm.addCommand(sendCommand);
    mainForm.addCommand(exitCommand);
    mainForm.setCommandListener(this);
            if( gauge == null )
        {
            gauge = new Gauge("Loading", false, 100, 0);
            gauge.setLabel("Loading");
        }

        mainForm.append(gauge);
        try{
        //    downloadfile("http://data.chiasenhac.com/data/cover/116/115704.jpg",System.getProperty("fileconn.dir.private") + "/cache.jpg");
    //  FileConnection fileCon = (FileConnection) Connector.open(System.getProperty("fileconn.dir.private") + "/cache.jpg");      
     // InputStream cx = fileCon.openInputStream();
      
      
      //  image = Image.createImage(cx);
      //  cx.close();
       // fileCon.close();
       // fileCon = null; 
      //  cx= null;
        
  
  
  }catch(Exception e){e.printStackTrace();}
    display.setCurrent(mainForm);
    
   // try{  String supportedTypes[] = Manager.getSupportedContentTypes(null);
   //     for (int i = 0; i < supportedTypes.length; i++) {
   //  if (supportedTypes[i].startsWith("audio")) {
   //     System.out.println("Device supports " + supportedTypes[i]);
 //    }
    //    }} catch(Exception xcxz){}
    
  //  getrequest();

  }

  public void pauseApp() {
  }

  public void destroyApp(boolean unconditional) {
      
  }
  
  	public void playerUpdate(Player p, String event, Object eventData) {
	
	}

  public void commandAction(Command c, Displayable s) {
    if (c == sendCommand) {
            try{player.stop();
            player.deallocate();
            player.close();
            player =null;
            fileCon = null;
            image = null;
            }catch(Exception xc){} 
            text = URL.getString();
              mainForm = new Form("CSN Player");
              URL = new TextField(null, text, 250, TextField.ANY);
    mainForm.append(URL);
    mainForm.addCommand(sendCommand);
    mainForm.addCommand(exitCommand);
    mainForm.addCommand(help);
    mainForm.addCommand(help2);
    
    mainForm.setCommandListener(this);
            if( gauge == null )
        {   gauge = null;
            gauge = new Gauge("Loading", false, 100, 0);
            gauge.setLabel( "Loading");
        }

       try{ mainForm.append(gauge);} catch (Exception gh){}
        searchandplay();
         display.setCurrent(mainForm);
      //   artimage.setImage(scale(image,128,128));
  //    String result = "";
   //   resultItem = new StringItem(null, result);
   //   resultForm = new Form("Result");
   //   String URLString = URL.getString();

   //   try {
   //     result = requestUsingGET(URLString);
    //  } catch (Exception e) {
    //    result = "Falied";
    //  }
//
   //   resultItem.setText(result);
   //   resultForm.append(resultItem);
    //  resultForm.addCommand(backCommand);
   //  resultForm.setCommandListener(this);
   //   display.setCurrent(resultForm);
    } else if (c == backCommand) {
      URL.setString(defaultURL);
      display.setCurrent(mainForm);
    } else if (c == help) {
       try{ player.stop();
       }catch(Exception xcx){} 
    } else if (c == help2) {
       try{ player.start();
       }catch(Exception xcx){} 
    }
    else {
      destroyApp(false);
      notifyDestroyed();
    }
    
  }

  private String requestUsingGET(String URLString) throws Exception {
     
    HttpConnection hpc = null;
    InputStream dis = null;
    InputStream is;
    boolean newline = false;
    String content = "";
    try {
      hpc = (HttpConnection) Connector.open(URLString);
  //    hpc.setRequestProperty("Authorization", "Basic "+Base64DE.encode("a644b4d1273843d89060d0b5677521d1:f90e34201c1b42cdae2b9dbb5f663d07"));
  //    hpc.setRequestMethod("POST");
  //    hpc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
  //    hpc.setRequestProperty("Content-length", "0"); 
 //     hpc.setRequestProperty("Content-Length", "0");
 
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
   //   System.out.println("hum:" + content);
    } catch (Exception e2) {
        
        System.out.println("bruh: " + e2 );
        e2.printStackTrace();
    }

    return content;
  }
  
  
  
  private void getrequest(){
       try {
           		//	ServerSocketConnection ssc = (ServerSocketConnection) Connector.open("socket://:8888");
			//SocketConnection clientsc = (SocketConnection) ssc.acceptAndOpen();
			
		//	SocketConnection telexsc = (SocketConnection) Connector.open("socket://notblocked.telex.cc:443");
		//	TlsProtocolHandler tls = new TlsProtocolHandler(telexsc.openInputStream(), telexsc.openOutputStream());
			
		//	TlsClient tlsClient = new LegacyTlsClient(new AlwaysValidVerifyer());
			//tls.connect(tlsClient);
           String defaultURL = "http://search.chiasenhac.vn/api/search.php?code=csn22052018&s=something+comforting&row=1";
            HttpConnection hpc = (HttpConnection) Connector.open(defaultURL);

          //  TlsProtocolHandler tls = new TlsProtocolHandler(hpc.openDataInputStream(),hpc.openOutputStream());
      //  DataInputStream in = hpc.openDataInputStream(); 
      //  TlsClient tlsClient = new LegacyTlsClient(new AlwaysValidVerifyer());
	//tls.connect(tlsClient);
       
        //  BufferedReader br = new BufferedReader(new InputStreamReader(in));
     //   StringBuilder sb = new StringBuilder(); 
//String line = br.readLine();
//while (line != null) { sb.append(line); line = br.readLine(); } 
        
        int character;
InputStream is;
    boolean newline = false;
    String content = "";
      try{
    InputStream   dis = hpc.openInputStream();
      
     
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
  //    System.out.println("hum:" + content);
    } catch (Exception e2) {
        
        System.out.println("bruh: " + e2 );
        e2.printStackTrace();}
        String musicid = getBetweenStrings(content, "\"music_id\":\"","\",\"cat_id\":\"");
        System.out.println(content.indexOf("\"music_id\":\"")); 
        System.out.println(musicid); 
       }catch(Exception io){io.printStackTrace();}
       
       
       
       
       
      
  }
  static String replace(char rep, String replacement, String word) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == rep){
                sb.append(replacement);
            }
            else{
                sb.append(word.charAt(i));
            }
        }
        return sb.toString();
	}
    public static String getBetweenStrings(
    String text,
    String textFrom,
    String textTo) {

    String result = "";

    // Cut the beginning of the text to not occasionally meet a      
    // 'textTo' value in it:
    result =
      text.substring(
        text.indexOf(textFrom) + textFrom.length(),
        text.length());

    // Cut the excessive ending of the text:
    result =
      result.substring(
        0,
        result.indexOf(textTo));

    return result;
  }
    private void playMusic(){
        if(!playingmusicid.equals(musicid)){
            playingmusicid = musicid;
      try{   fileCon = (FileConnection) Connector.open(System.getProperty("fileconn.dir.private") + "/cache2.mp3");
     
                       player = Manager.createPlayer(fileCon.openInputStream(), "audio/mpeg");
        try{
                     //   Player player = Manager.createPlayer(is, "audio/mpeg");
                        player.addPlayerListener(this);
                        player.realize();
                        VolumeControl vc = (VolumeControl) player.getControl("VolumeControl");
                        if(vc != null) {
                        vc.setLevel(100);
                            }
                        player.prefetch();
                        player.start();}
        catch(MediaException mex){
            musicid = "";
        mex.printStackTrace();
        player.deallocate();
        player.close();
        player = null;
        removeTag();
        playMusic();
        };
      }catch(Exception df){df.printStackTrace();}}}
    private void removeTag(){
       try{
    Tag tag = new Tag(System.getProperty("fileconn.dir.private") + "/cache2.mp3");
        try
        {
           tag.Read();
           if (tag.tagExists())
            {
                tag.Save(gauge,true, true );
          //    System.out.println(tag.getTitile());
            //  fileConn.close();
           } else
            {
                
           }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
           
            //     byte[] a = readFully(fileConn.openInputStream());
                 
              //   int x = 0;
              //   int b=3;
             //    for (int i = 6; i <= 9; i++)
              //   {
              //      x += (a[i] << (b * 7));
             //      b--;
              // /  }
               //  byte[] r = new byte[a.length-x];
              //   for (int i = x; i < a.length; i++)
              //   {
             //       r[i-x] = a[i];
             ///    }
             //    InputStream is2 = new ByteArrayInputStream(r);
            //     fileConn.delete();
           //      fileConn.create();
                 
        
                 
       }catch(Exception xcs){}}

    
    public void downloadfile(String musicurl, String savelocation){
      try{
          HttpConnection conn = (HttpConnection) Connector.open(musicurl);
                    if (conn.getResponseCode() == HttpConnection.HTTP_OK) {
                        InputStream is = conn.openInputStream();
                        fileCon = null;
     fileCon = (FileConnection) Connector.open(savelocation);
     //    FileConnection fileConn = (FileConnection) Connector.open("file:///root1/cache.mp3");
    try{
        if (!fileCon.exists()) {
                fileCon.create();
                System.out.println("file created");
            } else {
try{            fileCon.delete();} catch (Exception xxc){};
            fileCon.create();
            System.out.println("file already exist!");
        }
         DataOutputStream dos = new DataOutputStream(fileCon.openOutputStream());
           byte[] buf = new byte[1024];
            int len;
            while((len=is.read(buf))>0){
            dos.write(buf,0,len);
            }
           // dos.flush();
            dos.close();
            
         fileCon.close();
            
    } catch (Exception xgc){xgc.printStackTrace();}
 //   removeTag();

  
                        
                   }
    }catch(Exception sdsda){} }
    
    public Image scale(Image original, int newWidth, int newHeight) {

 int[] rawInput = new int[original.getHeight() * original.getWidth()];
 original.getRGB(rawInput, 0, original.getWidth(), 0, 0, original.getWidth(), original.getHeight());

 int[] rawOutput = new int[newWidth * newHeight];

 // YD compensates for the x loop by subtracting the width back out
 int YD = (original.getHeight() / newHeight) * original.getWidth() - original.getWidth();
 int YR = original.getHeight() % newHeight;
 int XD = original.getWidth() / newWidth;
 int XR = original.getWidth() % newWidth;
 int outOffset = 0;
 int inOffset = 0;

 for (int y = newHeight, YE = 0; y > 0; y--) {
   for (int x = newWidth, XE = 0; x > 0; x--) {
     rawOutput[outOffset++] = rawInput[inOffset];
     inOffset += XD;
     XE += XR;
     if (XE >= newWidth) {
       XE -= newWidth;
       inOffset++;
     }
   }
   inOffset += YD;
   YE += YR;
   if (YE >= newHeight) {
     YE -= newHeight;
     inOffset += original.getWidth();
   }
 }
 rawInput = null;
 return Image.createRGBImage(rawOutput, newWidth, newHeight, true);
}
    
    
    public void searchandplay(){
            try{
        
    //// get music id    
    String searchre = requestUsingGET("http://search.chiasenhac.vn/api/search.php?code=csn22052018&s="+text.replace(' ', '+')+"&row=1");
    musicid = getBetweenStrings(searchre , "\"music_id\":\"","\",\"cat_id\":\"");
    System.out.println("musicid :" + musicid);
    
    /// get music url
    String searchre2 = requestUsingGET("http://old.chiasenhac.vn/api/listen.php?code=csn22052018&return=json&m=" + musicid);
    String musicurlraw ="";
    String imageurlraw ="";
    try{musicurlraw = getBetweenStrings(searchre2 , "\"file_url\":\"","\",\"file_32_url\":\"");}catch(Exception vb)
    {musicurlraw = getBetweenStrings(searchre2 , "\"file_url\":\"",".mp3")+".mp3";}
    try{imageurlraw = getBetweenStrings(searchre2 , "\"music_img\":\"",".jpg")+".jpg";}catch(Exception vb)
    {imageurlraw = getBetweenStrings(searchre2 , "\"music_img\":\"",".png")+".png";}
    musicurl = replace('\\',"",musicurlraw);
    imageurl = replace('\\',"",imageurlraw);
    
    title = getBetweenStrings(searchre2 , "\"music_title\":\"","\",\"music_artist\":\"");
    artist = getBetweenStrings(searchre2 , "\"music_artist\":\"","\",\"music_composer\":\"");
    album = getBetweenStrings(searchre2 , "\"music_album\":\"","\",\"music_production\":\"");
    System.out.println("musicurl :" + musicurl);
     System.out.println("imageurl :" + imageurl);
         try{
             image = null;
          //  try{ artimage.setImage(Image.createImage(new byte[1],0,1));}catch (Exception xcxa){}
             fileCon =null;
            downloadfile(imageurl,System.getProperty("fileconn.dir.private") + "/"+musicid+".jpg");
            
       fileCon = (FileConnection) Connector.open(System.getProperty("fileconn.dir.private") + "/"+musicid+".jpg");      
      InputStream cx = fileCon.openInputStream();
      
      
      image = Image.createImage(cx);
      titleframe = new StringItem("",unescapeJava(title) + "\n");
      artistframe = new StringItem("",unescapeJava(artist) + "\n");
      albumframe = new StringItem("",unescapeJava(album)+ "\n");
      scaledimage = scale(image,128,128);
      artimage = new ImageItem(null, scaledimage , ImageItem.LAYOUT_DEFAULT, null);
       
        mainForm.append(artimage);
        mainForm.append(titleframe);
        mainForm.append(artistframe);
        mainForm.append(albumframe);
        
        
        
        
        
 
  
  }catch(Exception e){e.printStackTrace();}
         downloadfile(musicurl,System.getProperty("fileconn.dir.private") + "/cache2.mp3");
         playMusic();
        
    

    }catch(Exception cxv){cxv.printStackTrace();}
    }
   public static String unescapeJava(String escaped) {
    if(escaped.indexOf("\\u")==-1)
        return escaped;

    String processed="";

    int position=escaped.indexOf("\\u");
    while(position!=-1) {
        if(position!=0)
            processed+=escaped.substring(0,position);
        String token=escaped.substring(position+2,position+6);
        escaped=escaped.substring(position+6);
        processed+=(char)Integer.parseInt(token,16);
        position=escaped.indexOf("\\u");
    }
    processed+=escaped;

    return processed;
} 
}


