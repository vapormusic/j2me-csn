/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lolirem.csnj2me;


import com.gowtham.jmp3tag.Tag;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javaf.security.SecureRandom;

import java.util.Enumeration;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.HttpsConnection;
import javax.microedition.io.SocketConnection;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Graphics;
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
import net.wstech2.me.httpsclient.HttpsConnectionImpl;
import org.bouncycastle.crypto.tls.AlwaysValidVerifyer;

import org.bouncycastle.crypto.tls.CertificateVerifyer;

import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsProtocolHandler;
import org.json.me.JSONArray;
import org.json.me.JSONObject;










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
  
  Canvas dummyCanvas = new Canvas() {

     protected void paint(Graphics g) {
          }

   
  };

    // get the dimensions of the screen:

   int width = dummyCanvas.getWidth ();
   int height = dummyCanvas.getHeight();

  
  
  /// JSon Objects
  private String musicid = "";
  private String playingmusicid = "";
  private String musicurl = "";
  private String imageurl = "";
  private String title = "";
  private String album = "";
  private String artist = "";
  private String lyric = "";
  
  private Gauge gauge;
  private Image image;
  
  private boolean imageappend = false;
  Player player;
  FileConnection fileCon;
  ImageItem artimage;
  StringItem titleframe;
  StringItem artistframe;
  StringItem albumframe;
  StringItem lyricframe;
  String location = "file:///root1/";
  
  InputStream imageis;
 

  public Midlet() {    
      String location1 = System.getProperty("fileconn.dir.memorycard");
      String location2 = System.getProperty("fileconn.dir.private");
             if(location1 !=null ) {location = location1;} else 
             if(location2 !=null ) {location = location2;}
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
        try{FileConnection fileCon2 = (FileConnection) Connector.open(location +"csnmusic/");
            if(!fileCon2.exists()){fileCon2.mkdir();}
        }catch(Exception xcx){}
        
        try{
        //    downloadfile("http://data.chiasenhac.com/data/cover/116/115704.jpg","location + "cache.jpg");
    //  FileConnection fileCon = (FileConnection) Connector.open( location + "cache.jpg");      
     // InputStream cx = fileCon.openInputStream();
      
      
      //  image = Image.createImage(cx);
      //  cx.close();
       // fileCon.close();
       // fileCon = null; 
      //  cx= null;
        
  
  
  }catch(Exception e){e.printStackTrace();}
    display.setCurrent(mainForm);
    //System.out.println(removeDiacriticVN("triệu"));
    
 try{  String supportedTypes[] = Manager.getSupportedContentTypes(null);
     for (int i = 0; i < supportedTypes.length; i++) {
 if (supportedTypes[i].startsWith("audio")) {
  System.out.println("Device supports " + supportedTypes[i]);
   }
    }} catch(Exception xcxz){}
    
  //  getrequest();

  }

  public void pauseApp() {
  }

  public void destroyApp(boolean unconditional) {
      try{player.stop();
            player.deallocate();
            player.close();
            player =null;
            imageis.close();
            imageis = null;
            fileCon.close();
            fileCon = null;
            image = null;
            artimage=null;
            scaledimage = null;
            mainForm.deleteAll();
            }catch(Exception xc){}
      
      deletefilesinfolder( location +"csnmusic/");
      
  }
  
  	public void playerUpdate(Player p, String event, Object eventData) {
	
	}

  public void commandAction(Command c, Displayable s) {
     
    if (c == sendCommand) {
            try{player.stop();
            player.deallocate();
            player.close();
            player =null;
            imageis.close();
            imageis = null;
            fileCon.close();
            fileCon = null;
            image = null;
            artimage=null;
            }catch(Exception xc){} 
            text = URL.getString();
            mainForm.deleteAll();
            mainForm = null;
            System.gc();
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
        hpc.close();
        dis.close();
        System.out.println("bruh: " + e2 );
        e2.printStackTrace();
    }

    return content;
  }
  
  private String requestUsingPOST(String musicid) throws Exception {
     
    HttpsConnection hpc = null;
    InputStream dis = null;
    InputStream is;
    boolean newline = false;
    String content = "";
    try {
       HttpConnection connection = (HttpConnection)Connector.open("http://old.chiasenhac.vn/api/listen.php?code=csn22052018&return=json&m="+musicid);    
     connection.setRequestMethod("POST");  

     
     
     
System.out.println("Response Message:  "
			+ connection.getResponseMessage());
String response = getResponse(connection.openInputStream()); 
System.out.println("HTTPS (HttpsConnectionImpl) "
	+ "request returned the following CONTENT:" + response);


     JSONObject json = new JSONObject(response);
     JSONObject music = json.getJSONObject("music_info");
     title = music.getString("music_title");

    return response;  

    }catch(Exception d){
        
    d.printStackTrace();
         HttpsConnectionImpl preconnection = new HttpsConnectionImpl(
					"chiasenhac.vn", 
					443,
					"/api/listen_info_music?music_id="+musicid+"&type=music");
     preconnection.setAllowUntrustedCertificates(true);
     
     preconnection.setRequestMethod("POST");
   
     HttpsConnection connection = preconnection;
     
     
     
System.out.println("Response Message:  "
			+ connection.getResponseMessage());
String response = getResponse(connection.openInputStream()); 
System.out.println("HTTPS (HttpsConnectionImpl) "
	+ "request returned the following CONTENT:" + response);


     

    return response;
    
    
    }
  //  return "";
  }
  
 private String getResponse(InputStream in) throws IOException {

	StringBuffer retval = new StringBuffer();
	byte[] content = new byte[5];

	int read = 0;
	while ((read = in.read(content)) != -1) {
		// this is for testing purposes only
		// an adequate solution should handle charsets here
		retval.append(new String(content, 0, read));

	}

	return retval.toString();
}
//  
//  private void getrequest(){
//       try {
//           		//	ServerSocketConnection ssc = (ServerSocketConnection) Connector.open("socket://:8888");
//			//SocketConnection clientsc = (SocketConnection) ssc.acceptAndOpen();
//			
//		//	SocketConnection telexsc = (SocketConnection) Connector.open("socket://notblocked.telex.cc:443");
//		//	TlsProtocolHandler tls = new TlsProtocolHandler(telexsc.openInputStream(), telexsc.openOutputStream());
//			
//		//	TlsClient tlsClient = new LegacyTlsClient(new AlwaysValidVerifyer());
//			//tls.connect(tlsClient);
//           String defaultURL = "http://search.chiasenhac.vn/api/search.php?code=csn22052018&s=something+comforting&row=1";
//            HttpConnection hpc = (HttpConnection) Connector.open(defaultURL);
//
//          //  TlsProtocolHandler tls = new TlsProtocolHandler(hpc.openDataInputStream(),hpc.openOutputStream());
//      //  DataInputStream in = hpc.openDataInputStream(); 
//      //  TlsClient tlsClient = new LegacyTlsClient(new AlwaysValidVerifyer());
//	//tls.connect(tlsClient);
//       
//        //  BufferedReader br = new BufferedReader(new InputStreamReader(in));
//     //   StringBuilder sb = new StringBuilder(); 
////String line = br.readLine();
////while (line != null) { sb.append(line); line = br.readLine(); } 
//        
//        int character;
//    boolean newline = false;
//    String content = "";
//      try{
//    InputStream   dis = hpc.openInputStream();
//      
//     
//      while ((character = dis.read()) != -1) {
//			if ((char) character == '\\') {
//				newline = true;
//				continue;
//			} else {
//				if ((char) character == 'n' && newline) {
//					content += "\n";
//					newline = false;
//				} else if (newline) {
//					content += "\\" + (char) character;
//					newline = false;
//				} else {
//					content += (char) character;
//					newline = false;
//				}
//			}
//		}
//      
//
//      
////      if (hpc != null)
////        hpc.close();
////      if (dis != null)
////        dis.close();
//  //    System.out.println("hum:" + content);
//    } catch (Exception e2) {
//        
//        System.out.println("bruh: " + e2 );
//        e2.printStackTrace();}
//        String musicid = getBetweenStrings(content, "\"music_id\":\"","\",\"cat_id\":\"");
//        System.out.println(content.indexOf("\"music_id\":\"")); 
//        System.out.println(musicid); 
//       }catch(Exception io){io.printStackTrace();}
//       
       
       
       
       
  //    return "";
  //}
           public String readLine(InputStreamReader reader) throws IOException {
        StringBuffer line = new StringBuffer();
        int c = reader.read();

        while (c != -1 && c != '\n') {
            line.append((char)c);
            c = reader.read();
        }

        if (line.length() == 0 && c == -1) {
            return null;
        }

        return line.toString();
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
    private void playMusic(String yah){
        if(!playingmusicid.equals(musicid)){
            playingmusicid = musicid;
      try{ System.out.println(location);
          
     
                 //     player = Manager.createPlayer(fileCon.openInputStream(), "audio/mpeg");
      if(yah == "y"){
            fileCon = (FileConnection) Connector.open( location +"csnmusic"+ "/cache2.mp3");
     if(!fileCon.exists()) {
         downloadfile(musicurl, location +"csnmusic"+ "/cache2.mp3");
          fileCon = (FileConnection) Connector.open( location +"csnmusic"+ "/cache2.mp3");
         removeTag();}
     
       fileCon = (FileConnection) Connector.open( location +"csnmusic"+ "/cache2.mp3");
        player = Manager.createPlayer(fileCon.openInputStream(),"audio/mpeg");
        System.out.println("duh");
      }else{
     try{ player = Manager.createPlayer(musicurl); } catch(Exception u){
         System.out.println("fast load failed");

         fileCon = (FileConnection) Connector.open( location +"csnmusic"+ "/cache2.mp3");
                 if(!fileCon.exists())   downloadfile(musicurl, location +"csnmusic"+ "/cache2.mp3");
          FileConnection fileCon2 = (FileConnection) Connector.open( location +"csnmusic"+ "/cache2.mp3");         
        player = Manager.createPlayer(fileCon2.openInputStream(),"audio/mpeg");}}
        try{
                     //   Player player = Manager.createPlayer(is, "audio/mpeg");
                        player.addPlayerListener(this);
                        player.realize();
                        VolumeControl vc = (VolumeControl) player.getControl("VolumeControl");
                        if(vc != null) {
                        vc.setLevel(100);
                            }
                        player.prefetch();
                        player.start();
                        
        }
        
        catch(MediaException mex){
            musicid = "";
        mex.printStackTrace();
        player.deallocate();
        player.close();
        player = null;
        removeTag();
        playMusic("y");
        };
       
      }catch(Exception df){df.printStackTrace();}}}
    private void removeTag(){
       try{
    Tag tag = new Tag( location +"csnmusic"+ "/cache2.mp3");
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
//                  HttpsConnectionImpl preconnection = new HttpsConnectionImpl(
//					musicurl.substring("http://".length(),  musicurl.indexOf("chiasenhac.com")+ "chiasenhac.com".length()), 
//					443,
//					musicurl.substring(musicurl.indexOf("chiasenhac.com") + "chiasenhac.com".length() ,musicurl.length()));
//               System.out.println(musicurl.substring("http://".length(),  musicurl.indexOf("chiasenhac.com")+ "chiasenhac.com".length()));
//               System.out.println(musicurl.substring(musicurl.indexOf("chiasenhac.com") + "chiasenhac.com".length() ,musicurl.length()));
//     preconnection.setAllowUntrustedCertificates(true);
//     
//     preconnection.setRequestMethod("GET");
//          HttpsConnection conn = preconnection;
                  
                  HttpConnection conn = (HttpConnection)Connector.open(musicurl);
                    if (conn.getResponseCode() == HttpConnection.HTTP_OK) {
                        InputStream is = conn.openInputStream();
                        fileCon = null;
     fileCon = (FileConnection) Connector.open(savelocation);
     //    FileConnection fileConn = (FileConnection) Connector.open("file:///root1/cache.mp3");
    try{
        
//        fileCon.mkdir();
        if (!fileCon.exists()) {
                fileCon.create();
                System.out.println("file created: " + savelocation);
            } else {
try{            fileCon.delete();
            fileCon.create();
            System.out.println("file already exist!");} catch (Exception xxc){};
        }
         DataOutputStream dos = new DataOutputStream(fileCon.openOutputStream());
           byte[] buf = new byte[128];
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
    }catch(Exception sdsda){sdsda.printStackTrace();}
     }
    
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
              
    String searchre = requestUsingGET("http://search.chiasenhac.vn/api/search.php?code=csn22052018&s="+ removeDiacriticVN(text.replace(' ', '+'))+"&row=1");
    musicid = getBetweenStrings(searchre , "\"music_id\":\"","\",\"cat_id\":\"");
    System.out.println("musicid :" + musicid);
  //  System.out.println("musicid :" + Normalizer.normalize("triệu",Normalizer.NFKC));
    /// get music url
    String searchre2 = requestUsingPOST(musicid);
    String musicurlraw ="";
    String imageurlraw ="";
    String lyricraw ="";
    JSONObject json;
    JSONObject data; 
    JSONObject music;
    try{
     json = new JSONObject(searchre2);
     data = json.getJSONObject("data");
     music = data.getJSONObject("music");
    JSONArray urls = music.getJSONArray("file_urls");
    for (int i = 0; i < urls.length(); i++){
        
    if((urls.get(i).toString()).indexOf("128kbps") > -1){
        JSONObject json2 = new JSONObject(urls.get(i).toString());
        musicurl = "http"+json2.getString("url").substring(5);
            imageurl = "http"+music.getString("cover_image").substring(5);
    title = music.getString("music_title");
        break;
    }

    }
    }catch(Exception e){
     json = new JSONObject(searchre2);
     music = json.getJSONObject("music_info");
     musicurl = music.getString("file_url");
     imageurl = music.getString("music_img");
     title = music.getString("music_title");
    }
    

    lyricraw = music.getString("music_lyric");
    System.out.println(URLDecoder(lyricraw));
    lyric = URLDecoder(lyricraw);
    
    
    
    
    artist = music.getString("music_artist");
    album = music.getString("music_album");
    System.out.println("musicurl :" + musicurl);
     System.out.println("imageurl :" + imageurl);
         try{
             image = null;
          //  try{ artimage.setImage(Image.createImage(new byte[1],0,1));}catch (Exception xcxa){}
             fileCon =null;
           
   

     
      titleframe = new StringItem("", "\n"+ unescapeJava(title) + "\n");
      artistframe = new StringItem("",unescapeJava(artist) + "\n");
      albumframe = new StringItem("",unescapeJava(album)+ "\n");
      lyricframe = new StringItem("","Lyrics:"+"\n"+lyric+ "\n");
             try{ downloadfile(imageurl, location  +"csnmusic"+ "/"+musicid+".jpg"); 
    fileCon = (FileConnection) Connector.open(location +"csnmusic"+ "/"+musicid+".jpg");  
     try{ imageis.close(); } catch (Exception d){}
      imageis = fileCon.openInputStream();
    image = Image.createImage(imageis);
 int imagesize = width -40;
      scaledimage = scale(image,imagesize,imagesize);
      try{ imageis.close(); } catch (Exception d){}
      artimage = new ImageItem(null, scaledimage , ImageItem.LAYOUT_DEFAULT, null);
       
        
         fileCon.close();
           mainForm.append(artimage);
} catch(Exception u){u.printStackTrace()
        ;try{fileCon.close();}catch(Exception du){}}
            
        mainForm.append(titleframe);
        
        mainForm.append(artistframe);
        mainForm.append(albumframe);
        
        mainForm.append(lyricframe);

       
        
        
        
 
  
  }catch(Exception e){e.printStackTrace();}
         
         playMusic("");
        
    

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
   private static final String tab00c0 = "AAAAAAACEEEEIIII" +
    "DNOOOOO\u00d7\u00d8UUUUYI\u00df" +
    "aaaaaaaceeeeiiii" +
    "\u00f0nooooo\u00f7\u00f8uuuuy\u00fey" +
    "AaAaAaCcCcCcCcDd" +
    "DdEeEeEeEeEeGgGg" +
    "GgGgHhHhIiIiIiIi" +
    "IiJjJjKkkLlLlLlL" +
    "lLlNnNnNnnNnOoOo" +
    "OoOoRrRrRrSsSsSs" +
    "SsTtTtTtUuUuUuUu" +
    "UuUuWwYyYZzZzZzF";

   public static String removeDiacritic(String source) {
    char[] vysl = new char[source.length()];
    char one;
    for (int i = 0; i < source.length(); i++) {
        one = source.charAt(i);
        if (one >= '\u00c0' && one <= '\u017f') {
            one = tab00c0.charAt((int) one - '\u00c0');
        }
        vysl[i] = one;
    }
    return new String(vysl);
}
   
  
   
    public static String removeDiacriticVN(String source) {
           String vdc = "ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ";
           String vdclower = "ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵ";
           String vdc3 = "adeiouy";
           char[] vdc2 = vdc.toCharArray();
           char[] vdc2l = vdclower.toCharArray();
    char[] vysl = new char[source.length()];
    char one;
    for (int i = 0; i < source.length(); i++) {
        one = source.charAt(i);
        
        for (int u = 0; u < vdc2.length; u++) {
            if (one == vdc2[u] || one == vdc2l[u] ){
                if(u<16){one='a';}
                else if (u<17){one='d';}
                else if (u<29){one='e';}
                else if (u<34){one='i';}
                else if (u<51){one='o';}
                else if (u<62){one='u';}
                else {one='y';}
                break;
            }
    }
        vysl[i] = one;
    }
    return new String(vysl);
}
    private void deletefilesinfolder(String folder) {
   try{ FileConnection fconn = (FileConnection) Connector.open(folder);
    Enumeration en = fconn.list();
    while (en.hasMoreElements()) {
    String name = (String) en.nextElement();
    System.out.println(folder + name);
    FileConnection tmp = (FileConnection) Connector.open(
        folder +"/"+ name);
   try{ tmp.delete();}catch(IOException cvcx){}
    tmp.close();
    }

    }catch(Exception cv){cv.printStackTrace();}}
    
    private String URLDecoder(String str){
      String u;  
    u = unescapeJava(str);
    u = replace(u,"[NOSUB]", "");
    u = replace(u,"[/NOSUB]", "");
    u = replace(u,"<span style=\"color:#ffffff\">", "");
    u = replace(u,"<span style=\"color:#65abbc\">", "");
    u = replace(u,"</span><br />", "\n");
    u = replace(u,"</span>", "");
    u = replace(u,"&#039;", "'");
    return u;
    }
    
    private  String replace( String str, String pattern, String replace ) 
{
    int s = 0;
    int e = 0;
    StringBuffer result = new StringBuffer();

    while ( (e = str.indexOf( pattern, s ) ) >= 0 ) 
    {
        result.append(str.substring( s, e ) );
        result.append( replace );
        s = e+pattern.length();
    }
    result.append( str.substring( s ) );
    return result.toString();
}   
    
}


