package blatt04;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

  public static void main (String[] args) throws Exception {
    // Configuration
    String masterHost = "127.0.0.1";
    int masterClientPort = 5555;

    System.out.println("Examples: read key");
    System.out.println("          store key 42");
    System.out.println("Please type your request:");

    Scanner scanner = new Scanner(System.in);
    String line = scanner.nextLine();
    scanner.close();
    String[] lineParts = line.split(" ");
    IRequest request;
    /**
     * Hier wird nach dem schluesselwort key gesucht und dann wird das naechste feld genommen
     */
    int pos=0;
    boolean isStoreReq=false;
    for(int i=0;i<lineParts.length;i++){
    	
    	// fehlerueberpruefung fehlt noch
    	if(lineParts[i].equals("key")){
    		pos=i;
    		break;
    	}
    	if(lineParts[i].toLowerCase().equals("store")){
    		isStoreReq=true;
    	}
    	
    }
    if(isStoreReq){
    	int storeVal=stringToInt(lineParts[pos+1]);
    	request=new StoreRequest(lineParts[pos],storeVal);
    }else{
    	request=new ReadRequest(lineParts[pos]);
    }
    
    /**
     * 
     */


    // *****************************************
    // TODO: Werten Sie das Array lineParts aus und interpretieren die Eingabe entsprechend.
    // Eine passende Anfrage sollte danach in der Variable request gespeichert sein.
    // *****************************************



    System.out.println("Sending: " + request);
    Socket master = new Socket(masterHost, masterClientPort);
    try {
      ObjectOutputStream out = new ObjectOutputStream(master.getOutputStream());
      out.writeObject(request);
      out.flush();

      ObjectInputStream in = new ObjectInputStream(master.getInputStream());
      IResponse response = (IResponse) in.readObject();
      ResponseVisitor rv = new ResponseVisitor();
      rv.__( (readResponse) -> {
        SerializableOptional<Integer> result = readResponse.getValue();
        if(result.isPresent())
          System.out.println("Read response with value " + result.get() + ".");
        else
          System.out.println("Read response: Unknown key!");
      }, (storeResponse) -> {
        System.out.println("Store successful!");
      });
      response.accept(rv);
    }
    finally {
      master.close();
    }

  }
  private static int stringToInt(String inp){
	  int out=0;
	  //input sollte eine zahl sein
	  for(int i=0;i<inp.length();i++){
		  int mul=inp.length()-i-1;
		  out+=(inp.charAt(i)-48)*(mul*10);
		  
	  }
	  //out sollte bei "42" laenge 2 haben als erstes 4 bekommen
	  //das mit 10 mulitplizieren usw.
	  return out;
  }
}
