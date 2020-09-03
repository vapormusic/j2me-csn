package javazoom.jlme.player;

import java.io.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.media.*;
import javazoom.jlme.decoder.*;


/*
 * MyPlayerMIDlet
 *
 */

public class MyPlayerMIDlet extends MIDlet implements CommandListener, ItemStateListener, PlayerListener {


//--------------------------------------------------------------------------------------------
// Forms
//--------------------------------------------------------------------------------------------

   private Display display;
   
   private List              selectFilesForm =new List("Open a Mp3 file (WavToMp3://)",Choice.IMPLICIT);
   private Form              playerForm =new Form("Mp3 Decoder");
   private Alert             alert =new Alert("Wait Please...");
 

//--------------------------------------------------------------------------------------------
// Commands
//--------------------------------------------------------------------------------------------

    private Command               	exitCommand = new Command("Exit",Command.EXIT,1);
    private Command               	selectCommand = new Command("Ok",Command.OK,1);

    private Command               	stopCommand = new Command("Stop",Command.ITEM,3);
    private Command               	playCommand = new Command("Play",Command.ITEM,2);
    private Command               	bufCommand = new Command("Buffering",Command.ITEM,2);
    private Command               	prefetchCommand = new Command("Prefetch",Command.ITEM,1);
    private Command               	prefetchBufCommand = new Command("Prefetch/Buffering",Command.ITEM,1);
    private Command               	prefetchPlayCommand = new Command("Prefetch/Play",Command.ITEM,1);
    private Command               	backCommand = new Command("Back",Command.BACK,1);
    
    private ChoiceGroup 			commentsChoice=new ChoiceGroup("Comments : ",Choice.POPUP);
 	private	StringItem 				detailsText=new StringItem("Details\n",null);
	private	TextField 				bufferText=new TextField("Buffer Size (byte)",null,15,TextField.NUMERIC);
	private StringItem				aboutText=new StringItem("About Mp3Decoder\n","Mp3Decoder decodes any MP3 files encoded in MPG LayerIII. It converts the file into a pcm stream thanks to JavaLayer and adds a WAV Header in order to create a well-defined WAV stream. Then, it uses the Datasource & WAV Player from MMAPI for respectively transfering and playing.");

//--------------------------------------------------------------------------------------------
// Needed for decoding and playing (Datasource Mp3ToWav & Payer WAV)
//--------------------------------------------------------------------------------------------

    private Player player;  
    private Mp3ToWav dataSource;
    private InputStream stream;
	private String file;

//--------------------------------------------------------------------------------------------
// Constructor,StartApp,DestroyApp
//--------------------------------------------------------------------------------------------
          
    public MyPlayerMIDlet() {
		super();
		// don't do anything in the constructor
    }
    

    public void startApp() {
		
      	/*
    	 * selectFilesForm
    	 */
 		selectFilesForm.append("test.mp3",null); // add some audio files ...

		selectFilesForm.addCommand(exitCommand); // add the commands
		selectFilesForm.addCommand(selectCommand); // add the commands
		selectFilesForm.setCommandListener(this); // add the listener

      	/*
    	 * PlayerForm
    	 */
 		commentsChoice.append("Yes",null);
 		commentsChoice.append("No",null);
	
 		playerForm.append(commentsChoice);
 		playerForm.append(detailsText);
 		playerForm.append(aboutText);
 		
		playerForm.addCommand(backCommand); // add the commands
		playerForm.addCommand(stopCommand); 
		playerForm.addCommand(playCommand); 
		playerForm.addCommand(prefetchCommand); 
		playerForm.setCommandListener(this); // add the listenrs
		playerForm.setItemStateListener(this);
	 
		/*
		 * Display
		 */
		display  = Display.getDisplay(this);
		display.setCurrent(selectFilesForm); 
   }



    public void pauseApp() {
	}

    public void destroyApp(boolean unconditional) {

	}
 
  	public void createPlayer() throws Exception {
  		
		// Create the DataSource called Mp3ToWav in order to interface JavaLayerDecoder and MMAPI Players 
 		dataSource = new Mp3ToWav("/" + file,true);
		setComments();
		bufferText.setString(Integer.toString(dataSource.getBufferSize()));
		
		// Connect the dataSource to the Source file --> DataSource ready for buffering the wav decoded stream
		dataSource.connect();
		
		// Open the file
		try {
			// Create a new player from the Mp3ToWav DataSource
		    player = Manager.createPlayer(dataSource); 
		    player.addPlayerListener(this); 
		  }
		catch (Exception e) {
		    System.out.println("Cannot open the file with the Mp3ToWav datasource");
	   		e.printStackTrace();
	   		return;
		}
  	}

	/*
	 * Display some comments about the Mp3 file (name, bitRate, Number of Channels... )
	 */
	private void detailsFile() {
		detailsText.setText("File : " + file + "\n" + dataSource.getDetails());
	}
	  	
	/*
	 * Set the way of displaying the comments
	 */
	private void setComments() {
		if (commentsChoice.getSelectedIndex()==0) {
			dataSource.setComments(true);}
		else {
			dataSource.setComments(false);}				
	}

//--------------------------------------------------------------------------------------------
// Listeners (Commands and Items)
//--------------------------------------------------------------------------------------------
    
	public void playerUpdate(Player p, String event, Object eventData) {
		if (event==PlayerListener.CLOSED) {
				display.setCurrent(selectFilesForm);
		}
	}

	public void itemStateChanged(Item item) {
		if (item== commentsChoice) {
			setComments();
		}	
		if (item== bufferText) {
			dataSource.setBufferSize(Integer.valueOf(bufferText.getString()).intValue());
		}	
	} 

	public void commandAction(Command c,Displayable s) {
		try{
	        if (c == exitCommand) {
	            destroyApp(true);
	            notifyDestroyed();
			} 
			else if (c == prefetchCommand) {
			    // Pretech the file (Transfert the file header)
			    player.prefetch();
			    
			    // modify the available commands
			    playerForm.removeCommand(prefetchCommand);
			    playerForm.removeCommand(prefetchBufCommand);
			    playerForm.removeCommand(prefetchPlayCommand);
			    playerForm.addCommand(playCommand);
			    playerForm.addCommand(bufCommand);
	 		} 
			else if (c == prefetchBufCommand) {
			    // Pretech the file (Transfert the file header)
			    player.prefetch();
			    
			    // Start buffering (Mp3ToWav DataSource - depends on the size of the buffer)
			    dataSource.startBuffering();

			    // modify the available commands
			    playerForm.removeCommand(prefetchCommand);
			    playerForm.removeCommand(prefetchBufCommand);
			    playerForm.removeCommand(prefetchPlayCommand);
			    playerForm.addCommand(playCommand);

	 		} 
			else if (c == prefetchPlayCommand) {
			    // Pretech the file (Transfert the file header)
			    player.prefetch();
			    // Start Playing 
			    player.start();			    

			    // modify the available commands
			    playerForm.removeCommand(prefetchCommand);
			    playerForm.removeCommand(prefetchBufCommand);
			    playerForm.removeCommand(prefetchPlayCommand);
			    playerForm.addCommand(stopCommand);
	 		} 
	       	else if (c == playCommand) {
			    // Start Playing 
		 		player.start();

			    // modify the available commands
			    playerForm.removeCommand(playCommand);
			    playerForm.removeCommand(bufCommand);
			    playerForm.addCommand(stopCommand);
	 		} 
	       	else if (c == stopCommand) {
			    // Stop Playing (Pause) 
		 		player.stop();

			    // modify the available commands
			    playerForm.removeCommand(stopCommand);
			    playerForm.addCommand(playCommand);
			    playerForm.addCommand(bufCommand);
	 		}
	       	else if (c == bufCommand) {
			    // Start buffering (Mp3ToWav DataSource - depends on the size of the buffer)
			    dataSource.startBuffering();
			    
			    // modify the available commands
			    playerForm.removeCommand(stopCommand);
			    playerForm.removeCommand(bufCommand);
			    playerForm.addCommand(playCommand);
	       		
			}
	       	else if (c == selectCommand) {
			    // Create the DataSource and the Player 
				file = selectFilesForm.getString(selectFilesForm.getSelectedIndex());
				createPlayer();
				detailsFile();
			    
			    // modify the available commands
			    playerForm.removeCommand(stopCommand);
			    playerForm.removeCommand(playCommand);
			    playerForm.addCommand(prefetchCommand);
			    playerForm.addCommand(prefetchBufCommand);
			    playerForm.addCommand(prefetchPlayCommand);

				display.setCurrent(playerForm);
	 		}
	    	else if (c == backCommand) {
				display.setCurrent(alert);
	    		dataSource.setConnected(false);
	    		player.close();
	 		}
		}
	 	catch(Exception e){}

	}

}