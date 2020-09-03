/**
 * 
 * Mp3ToWav DataSource
 * 
 * JavaLayerME and MMAPI
 *
 */

package javazoom.jlme.decoder;


import java.io.*;
import javax.microedition.media.*;
import javax.microedition.media.protocol.*;
import java.io.IOException;


/* Audio datasource for converting mp3 to wav
 */
 
public class Mp3ToWav extends DataSource implements SourceStream, Runnable {

	// DataSource fields
    String locator = null;
    boolean connected = false;
    String contentType = null;

    // Comments field
    private boolean comments;
    
    // Defaults
    private int sampleRate = 44100;
    private int sampleSize = 16;
    private int channels = 2;
    private int endian = 0; // 0 for any, 1 for "little", 2 for "big"
    private int signed = 1; // 0 for any, 1 for "signed", 2 for "unsigned"

	// Productor
	Thread prodThread;
	public boolean stop=true;
	private byte[] fifo;
	private Object lock;
	private int getAskFor=0;
	private boolean locked=false;
	private int f_g=0;
	private int f_p=0;
	private int f_size=0;
	private int FIFO_SIZE = 5000000;
	
	// Decoder JavaLayer
    private Decoder decoder;     
	private Header header; 
  	private BitStream bitstream;	
	private int dataLength=0;
   	private int lengthFrame;
	private byte[] bArray;
	private String file;
	private Object lock2;
	private boolean locked2=false;
   
 
    // The WAVe header
    private byte [] hdr = new byte[44];
    private int hdrSize;
    private int hdrOfs;
    private static final String[] headerComments= {"Chunk RIFF","Chunk RIFF","Chunk RIFF","Chunk RIFF","Check Size (Length unbounded)","Check Size (Length unbounded)","Check Size (Length unbounded)","Check Size (Length unbounded)","FCC Type - WAVE","FCC Type - WAVE","FCC Type - WAVE","FCC Type - WAVE","Chunk Format - fmt","Chunk Format - fmt","Chunk Format - fmt","Chunk Format - fmt","Check Size","Check Size","Check Size","Check Size","1 means PCM","1 means PCM","Nb of Channels","Nb of Channels","Frequence byte/sec","Frequence byte/sec","Frequence byte/sec","Frequence byte/sec","Frequence byte/sec","Frequence byte/sec","Frequence byte/sec","Frequence byte/sec","Quantification - bytes","Quantification - bytes","Quantification - bits","Quantification - bits","Chunk Data","Chunk Data","Chunk Data","Chunk Data","Check Size","Check Size","Check Size","Check Size"};


    /****************************************************************
     * Runnable implementation
     ****************************************************************/

    /**
     * Main process loop driving the media flow.
     */
    public void run() {
		boolean endOfFile=false;
		int toCopy=0;
		int lap=0;


		// First transfer the header
		for (int i=0; i<hdr.length;i++) {
			push(hdr[i]);	
			if (comments) System.out.println("-- Mp3Decoder Comments -- WRITING -- WAV header = " + hdr[i] + " - " + headerComments[i]);
		}
		
	    if (comments) System.out.println("-- Mp3Decoder Comments -- DataSource connected and ready for streaming");

			
		while(!endOfFile && connected) {
			if (stop) {		
			if (comments) System.out.println("-- Mp3Decoder Comments -------> Synchro : DataSource locked");
				locked2=true;
				synchronized( lock2 ){
	    			// here is where you'd check obj's state
			     	while (locked2) {		
					    try {
						lock2.wait();
					    }
					    catch( InterruptedException e ){
							if (comments) e.printStackTrace();
					    }
					}
				}
			if (comments) System.out.println("-- Mp3Decoder Comments -------> Synchro : DataSource unlocked ");
			}
			
			lap++;
	      try
		      {
		      	
		      	SampleBuffer output = (SampleBuffer)decoder.decodeFrame();
		      	lengthFrame = output.size();
	     		if (lengthFrame <= 0) {
					if (comments) System.out.println("-- Mp3Decoder Comments -- Mp3ToWav DataSource -- End Of File");
	     			endOfFile=true;break;
     			}

		      	bArray=output.getBuffer();
		 		
				if (f_size+lengthFrame<FIFO_SIZE) {
					if (f_p+lengthFrame<FIFO_SIZE) {
						// copy en un bloc
					   	System.arraycopy(bArray, 0,
					     fifo, f_p,
					     lengthFrame);}
					else {
						// copy en deux blocs
						// 1er bloc : FIFO_SIZE-f_p bytes copied
					   	System.arraycopy(bArray, 0,
					     fifo, f_p,
					     FIFO_SIZE-f_p);
						// 2eme bloc : lengthFrame-(FIFO_SIZE-f_p) bytes copied from the byte FIFO_SIZE-f_p   
					   	System.arraycopy(bArray, FIFO_SIZE-f_p,
					     fifo, 0,
					     lengthFrame-FIFO_SIZE+f_p);
					}    
					f_p=(f_p+lengthFrame)%FIFO_SIZE;
					f_size=f_size+lengthFrame;
					if (f_size>getAskFor && locked==true) {
						getAskFor=0;
						locked=false;
					   	synchronized(lock){
    						if (comments) System.out.println("-- Mp3Decoder Comments -------> Synchro : Notify buffer ready for reading");
							lock.notifyAll(); 
						}
					}
				}
				else {
					// buffer full
					if (comments) System.out.println ("-- Mp3Decoder Comments -- CANT WRITE ANYMORE DATA -- Buffer Full -----");
				}
			
				
		 		if (comments) System.out.println("-- Mp3Decoder Comments -- WRITING -- lap "+lap+" -- buffering "+ f_size*100/FIFO_SIZE + " % -- "  + f_size + "/" + FIFO_SIZE + " --"  );
		      	bitstream.closeFrame();
				header = bitstream.readFrame();
			} catch (Exception e) {
		 		System.out.println("Exception in thread prodthread");
			}
		
			    	
		}
 		if (comments) System.out.println("-- Mp3Decoder Comments -- End of the thread used by Mp3ToWav for writing in the buffer");   	

	}
    
    /****************************************************************
     * DataSource implementation
     ****************************************************************/

    public Mp3ToWav(String l, boolean c) {
    	super(l);
    	locator=l;
    	comments=c;
		// Captures audio and wraps it into WAV content type
		contentType = "audio/x-wav";
		fifo=new byte[FIFO_SIZE];
		lock=new Object();
		lock2=new Object();
    }

 
    public void setLocator(String ml) {
	locator = ml;
    }

    public String getLocator() {
	return locator;
    }

    public String getContentType() {
	if (!connected)
	    return null;
	return contentType;
    }

    public void setContentType(String type) {
	contentType = type;
    }

    public Control[] getControls() {
	return new Control[0];
    }

    public Control getControl(String controlType) {
	return null;
    }


    /**
     * Parse the protocol part of the locator string.
     */
    static public String getProtocol(String loc) {
	String proto = "";
	int idx = loc.indexOf(':');

	if( idx != -1) {
	    proto = loc.substring(0,idx);
	}

	return proto;
    }

   /* return the numeric value for a property */
    private int tryParam(String tok, String prop, int def) {
	if (tok.startsWith(prop)) {
	    tok = tok.substring(prop.length(), tok.length());
	    
	    if (prop.equals("signed=")) {
		if (tok.equals("signed"))
		    return 1; // SIGNED
		else if (tok.equals("unsigned"))
		    return 2; // UNSIGNED
	    } else if (prop.equals("endian=")) {
		if (tok.equals("little"))
		    return 1; // LITTLE_ENDIAN
		else if (tok.equals("big"))
		    return 2; // BIG_ENDIAN
	    } else {
		try {
		    return Integer.parseInt(tok);
		} catch (Exception e) {
		}
	    }
	}
	return def;
    }

    /* Parse the URL, extract and validate the properties */
    private boolean parseLocator() {
		String loc = getLocator();
	
		if (comments) System.out.println("-- Mp3Decoder Comments -- Parse Locator : " + loc);
	
		if (!loc.endsWith(".mp3"))
		    return false;
		
		return true;
    }

    public void connect() throws IOException {
	if (!parseLocator())
	    throw new IOException("Invalid locator");

		// open the file and create the header
		// throw an exception if the file has not been opened

		// Open the file
		try {
		    if (comments) System.out.println("-- Mp3Decoder Comments -- Create decoder and connect the dataSource (Push the wav header)");
			InputStream in=null;
			in = getClass().getResourceAsStream(getLocator());
		   	bitstream = new BitStream(in);	 
		   	
	    	header = bitstream.readFrame();
    		decoder = new Decoder(header,bitstream);
      		if (comments) System.out.println("-- Mp3Decoder Comments -- WAV Stream mode : Frequency: "+decoder.getOutputFrequency() + ",Channels: " + decoder.getOutputChannels());
    		System.gc();
		  }
		catch (Exception e) {
			if (comments) System.out.println("Couldn't open audio input");
	   		throw new IOException("Couldn't open audio input");
		}   	

		
		// Generate the WAV header
		createHeader(sampleRate, sampleSize, channels);
		connected = true;
    
    	// Start the playback loop.
		prodThread = new Thread(this);
		prodThread.start();
    }

    public void disconnect() {
		//stop();
		if (comments) System.out.println("-- Mp3Decoder Comments -- Mp3ToWav DataSource -- Disconnected");   	


		locked2=false;
	   	synchronized(lock2){
			if (comments) System.out.println("-- Mp3Decoder Comments -------> Synchro : Notify DataSource");
			lock2.notifyAll(); 
		}

		connected = false;
		
		fifo=null;
		System.gc();
    }

    public void start() throws IOException {
 		if (comments) System.out.println("-- Mp3Decoder Comments -- Mp3ToWav DataSource -- Started");   	
 		stopProd(false);
		locked2=false;
	   	synchronized(lock2){
			if (comments) System.out.println("-- Mp3Decoder Comments -------> Synchro : Notify DataSource");
			lock2.notifyAll(); 
		}

    }

    public void stop() {
 		if (comments) System.out.println("-- Mp3Decoder Comments -- Mp3ToWav DataSource -- Stopped");   	
		locked=false;
	   	synchronized(lock){
			if (comments) System.out.println("-- Mp3Decoder Comments -------> Synchro : Notify Buffer");
			lock.notifyAll(); 
		}

	 	stopProd(true);
    }

    public SourceStream[] getStreams() {
	return new SourceStream[] { this };
    }

    
    /****************************************************************
     * SourceStream implementation
     ****************************************************************/

    public ContentDescriptor getContentDescriptor() {
	return new ContentDescriptor(getContentType());
    }

    public long getContentLength() {
	return -1;
    }

    public int read(byte[] buffer, int offset, int length) throws IOException {
		if (comments) System.out.println("-- Mp3Decoder Comments -- ASK FOR READING -- offset =>"  + offset + ", length asked =>"  + length  );
 
    
    	int totalCopy=length;

		while (f_size<=totalCopy && connected) {
			// waiting
			getAskFor=totalCopy;
			locked=true;
			if (comments) System.out.println("-- Mp3Decoder Comments -------> Synchro : Buffer Locked  (empty)");	
				wait_get();
			if (comments) System.out.println("-- Mp3Decoder Comments -------> Synchro : Buffer Unlocked  (ready for reading)");
		}
		//if (f_size<=totalCopy) return 0;
		
		
			if (comments) System.out.println("-- Mp3Decoder Comments -- READING -- " + totalCopy + " -- f_size : " + f_size);
			
			if (f_g+totalCopy<FIFO_SIZE) {
				// copy en un bloc
			   	System.arraycopy(fifo, f_g,
			     buffer, offset,
			     totalCopy);}
			else {
				// copy en deux blocs
				// 1er bloc : FIFO_SIZE-f_g bytes copiés
			   	System.arraycopy(fifo, f_g,
			     buffer, offset,
			    FIFO_SIZE-f_g);
				// 2eme bloc : totalCopy-(FIFO_SIZE-f_g) bytes copiés à partir de l'octet FIFO_SIZE-f_g   
			   	System.arraycopy(fifo, 0,
			     buffer, offset+FIFO_SIZE-f_g,
			     totalCopy-FIFO_SIZE+f_g);
			}    
			f_g=(f_g+totalCopy)%FIFO_SIZE;
			f_size=f_size-totalCopy;

		return totalCopy;
    }

    // Cant really seek, throw an IOException.
    public long seek(long where) throws IOException {
	throw new IOException("Cannot seek");
    }

    // Return the bytes read from audio device so far
    public long tell() {
	return 0;
    }

    // Live input, not streaming
    public int getSeekType() {
	return NOT_SEEKABLE;
    }

    public int getTransferSize() {
	// Divide by 8 and multiply by 8 to make it a multiple of 8
	// Overall divide by 8 to make it 125 millisecond chunks
	return 8 * (((sampleSize * sampleRate * channels) / 8) / 64);
    }

     /****************************************************************
     * Create Wave Header
     ****************************************************************/

    // Write an int in LITTLE_ENDIAN
    private void writeInt(int value) {
	hdr[hdrSize++] = (byte)(value & 0xFF);
	hdr[hdrSize++] = (byte)((value >>>  8) & 0xFF);
	hdr[hdrSize++] = (byte)((value >>> 16) & 0xFF);
	hdr[hdrSize++] = (byte)((value >>> 24) & 0xFF);
    }
    
    // Write a short in LITTLE_ENDIAN
    private void writeShort(int value) {
	hdr[hdrSize++] = (byte)((value >> 0) & 0xFF);
	hdr[hdrSize++] = (byte)((value >> 8) & 0xFF);	
    }

    // Write the WAVe header into the hdr array
    private void createHeader(int samplesPerSec,
			        int sampleSizeInBits,
			        int channels) {
	hdrSize = 0;
	int avBytesPerSec = channels * samplesPerSec * (sampleSizeInBits / 8);
	writeInt(0x46464952 /*RIFF*/);
	writeInt(-1);	// Length unbounded
	writeInt(0x45564157 /*WAVE*/);
	writeInt(0x20746D66 /*FMT*/);  // Format Chunk
	writeInt(16);   // size of format chunk
	writeShort(1);  // wFormatTag ==> uncompressed
	writeShort((short) channels);
	writeInt(samplesPerSec);
	writeInt(avBytesPerSec);
	writeShort(channels * sampleSizeInBits / 8); // BlockAlign
	writeShort(sampleSizeInBits);
	writeInt(0x61746164 /*DATA*/); // Data chunk
	writeInt(-1);	// Length unbounded
    }
    
     /****************************************************************
     * FIFO Methods
     ****************************************************************/
     
     private void push(byte b){
     	
     	f_size++;
     	fifo[f_p]=b;
     	f_p=(f_p+1)%FIFO_SIZE;
     	
     }

     private byte get(){
 
     	wait_get();
     	
     	f_size--;
     	//System.out.println("size : " + f_size);
     	int r= f_g;
     	f_g=(f_g+1)%FIFO_SIZE;
     	return fifo[r];
	
     }


	private void wait_get() {
		synchronized( lock ){
    		// here is where you'd check obj's state
	     	while (locked) {		
			    try {
				lock.wait();
			    }
			    catch( InterruptedException e ){
			    }
			}
		}

	}

   /****************************************************************
     * Other Methods
     ****************************************************************/
 
 	public void startBuffering() {
		stopProd(false);

		locked2=false;
	   	synchronized(lock2){
			if (comments) System.out.println("-- Mp3Decoder Comments -------> Synchro : Notify DataSource");
			lock2.notifyAll(); 
		}
	}
	
	
	private void stopProd(boolean s) {
		stop=s;
	}
	
	public void setConnected(boolean b) {
		connected=b;
	}

	public void setComments(boolean b) {
		comments=b;
	}

	public void setBufferSize(int i) {
		FIFO_SIZE=i;
	}

	public int getBufferSize() {
		return FIFO_SIZE;
	}

	public String getDetails() {
		return "Layer(only III supported) : " + decoder.hd.layer_string()+ "\nBitrate index : " + decoder.hd.bitrate_string()  + "\nFrequency : " + decoder.hd.sample_frequency_string() + "\nChannels : " +decoder.hd.mode_string();
	}
}
