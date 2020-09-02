/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.media.Manager;
import javax.media.Player;
import javax.net.ssl.HttpsURLConnection;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.apache.commons.io.IOUtils;


/**
 *
 * @author SongAnh
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        String trackname = "";
        String artistname = "";
        CsnDownload xv1 = new CsnDownload();
        try{
// Using Scanner for Getting Input from User 
        Scanner in = new Scanner(System.in); 
        System.out.print("Track :"); 
        trackname = in.nextLine(); 
      ///  System.out.println("You entered string "+trackname); 
        
        System.out.print("Artist :");
        artistname = in.nextLine(); 
      ///  System.out.println("You entered integer "+artistname); 
  

        
        } catch (Exception xcc){}
        String finalmusicurl = "";
 ConsoleHandler handler = new ConsoleHandler();
    handler.setLevel(Level.ALL);
    Logger log = LogManager.getLogManager().getLogger("");
    log.addHandler(handler);
    log.setLevel(Level.ALL);

 try { 
 
//// get track id
URL url = new URL("https://chiasenhac.vn/api/search?q="+trackname+"&row=1");
HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
con.setSSLSocketFactory(new TSLSocketConnectionFactory()); 
InputStream in = con.getInputStream(); 
BufferedReader br = new BufferedReader(new InputStreamReader(in));
StringBuilder sb = new StringBuilder(); 
String line = br.readLine();
while (line != null) { sb.append(line); line = br.readLine(); } 
System.out.println(sb.toString());
String finalx = sb.toString();

          Gson gson = new Gson();
     
        Type type = new TypeToken<Csnsearch>() {
        }.getType();
        Csnsearch xv = gson.fromJson(finalx, type);
        int id2 = xv.getData().getMusic().getData().get(0).getMusicId();
System.out.println(xv.getData().getMusic().getData().get(0).getMusicId());  

//// get download link
URL urlnew = new URL("http://old.chiasenhac.vn/api/listen.php?code=csn22052018&return=json&m=" + id2);
HttpURLConnection connew = (HttpURLConnection)urlnew.openConnection();
InputStream innew = connew.getInputStream(); 
String finalx1 = IOUtils.toString(innew, "UTF-8"); 
Gson gson1 = new Gson();
Type type1 = new TypeToken<CsnDownload>() {}.getType();
 xv1 = gson1.fromJson(finalx1, type1);
  System.out.println(xv1.getMusicInfo().getFileUrl());        
  finalmusicurl = xv1.getMusicInfo().getFileUrl();



    } catch (Exception e2) {
        
        System.out.println("bruh: " + e2 );
        e2.printStackTrace();
    }

   try{
    InputStream is = new URL(finalmusicurl).openStream();
    BufferedInputStream bis = new BufferedInputStream( is );
    AdvancedPlayer player = new AdvancedPlayer(bis);
    clearConsole(); 
    System.out.println(xv1.getMusicInfo().getMusicTitle() );
    System.out.println(xv1.getMusicInfo().getMusicArtist() );
    System.out.println();
    String lyricsraw = xv1.getMusicInfo().getMusicLyric();
    System.out.println( java.net.URLDecoder.decode((lyricsraw.replace("<span style=\"color:#ffffff\">", "")).replace("<span style=\"color:#65abbc\">", ""),"UTF-8").replace("</span><br />", "\n"));
    player.play();
        } 
   catch (Exception ex) {
    ex.printStackTrace();
        }


    }
public final static void clearConsole()
{
    try
    {
        final String os = System.getProperty("os.name");

        if (os.contains("Windows"))
        {
            Runtime.getRuntime().exec("cls");
        }
        else
        {
            Runtime.getRuntime().exec("clear");
        }
    }
    catch (final Exception e)
    {
        //  Handle any exceptions.
    }
}}

