import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
  
import javax.bluetooth.*;
import javax.microedition.io.*;

  
/**
* Class that implements an SPP Server which accepts single line of
* message from an SPP client and sends a single line of response to the client.
*/
public class SimpleSPPServer {
    //start server
    private void startServer() throws IOException{
        StreamConnection conn = null;
        DataInputStream inStream=null;
        BufferedReader bReader=null;
    	
        //Create a UUID for SPP
        UUID uuid = new UUID("1101", true);
        //Create the servicve url
        String connectionString = "btspp://localhost:" + uuid +";name=Sample SPP Server";
        StreamConnection connection=null;
        
        //open server url
        StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier)Connector.open( connectionString );
        //Wait for client connection
        System.out.println("\nServer Started. Waiting for clients to connect...");
        
        
        	connection=streamConnNotifier.acceptAndOpen();
        RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
        System.out.println("Remote device address: "+dev.getBluetoothAddress());
        System.out.println("Remote device name: "+dev.getFriendlyName(true));
        
        //read string from spp client
         inStream=connection.openDataInputStream();
        bReader=new BufferedReader(new InputStreamReader(inStream));
        String lineRead=bReader.readLine();
        System.out.println(lineRead);
       }
       
        
        //send response to spp client
        
  
        
        
  
  
    public static void main(String[] args) throws IOException {
        
        //display local device address and name
        LocalDevice localDevice = LocalDevice.getLocalDevice();
        System.out.println("Address: "+localDevice.getBluetoothAddress());
        System.out.println("Name: "+localDevice.getFriendlyName());
        
        SimpleSPPServer sampleSPPServer=new SimpleSPPServer();
       while(true){
        sampleSPPServer.startServer();}
        
    }
}