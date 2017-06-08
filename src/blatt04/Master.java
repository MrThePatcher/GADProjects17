package blatt04;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Master implements Runnable {
  private Socket client;
  // neue attribute;
  static HashString toUse;
  static Socket[]sockets;

  public Master (Socket client) {
    this.client = client;
  }

  @Override public void run () {
    IRequest request;
    try {
      ObjectInputStream clientIn = new ObjectInputStream(client.getInputStream());
      request = (IRequest) clientIn.readObject();
    }
    catch (ClassNotFoundException e) {
      System.out.println("Error communicating with client!");
      try {
        client.close();
      }
      catch (IOException e1) {
      }
      return;
    }
    catch (IOException e) {
      System.out.println("Error communicating with client!");
      try {
        client.close();
      }
      catch (IOException e1) {
      }
      return;
    }
    System.out.println("Received: " + request);
    IResponse response = null;
    int hashVal= toUse.hash(request.getKey());
    /**
     * Auslesen des Requests okay
     * Welchen Store verwenden ? Woran unterscheide ich das??
     * Ich weiss ich muss irgendeine Fallunterscheidung machen aber in den Aufgaben steh nicht welche
     */
    Socket store =sockets[hashVal];
    
    // *****************************************
    // TODO: Lesen Sie hier den request aus, und entscheiden über die Hash Funktion des Keys,
    // welcher Store verwendet werden soll. Beachten Sie hier, dass der selbe Store evtl. von
    // verschiedenen Clients gleichzeitig angefragt werden kann (Stichwort "synchronized").
    // *****************************************

    // Socket store = ???


    synchronized (store) {
      try {
        ObjectOutputStream storeOut = new ObjectOutputStream(store.getOutputStream());
        storeOut.writeObject(request);
        storeOut.flush();

        ObjectInputStream storeIn = new ObjectInputStream(store.getInputStream());
        response = (IResponse) storeIn.readObject();
        System.out.println(response);
      }
      catch (ClassNotFoundException e) {
        System.out.println("Error communicating with store => exiting!");
        System.exit(1);
      }
      catch (IOException e) {
        System.out.println("Error communicating with store => exiting!");
        System.exit(1);
      }
    }
    try {
      ObjectOutputStream clientOut = new ObjectOutputStream(client.getOutputStream());
      clientOut.writeObject(response);
      clientOut.flush();
    }
    catch (IOException e) {
      System.out.println("Error communicating with client!");
    }
    finally {
      try {
        client.close();
      }
      catch (IOException e) {
      }
    }
  }
  private Socket chooseSocket(int hashPos){
	  /**
	     * Welchen Store verwenden ? Woran unterscheide ich das??
	     * Ich weiss ich muss irgendeine Fallunterscheidung machen aber in den Aufgaben steh nicht welche
	     * habe jetzt da StoreCount=2 ist einfach mal mod 2 gemacht weil das logisch erscheint
	     */
	  if(hashPos%2==0){
		  return sockets[0];
	  }else{
		  return sockets[1];
	  }
  }

  public static void main (String[] args) throws IOException {
    // Configuration
    int storeCount = 2;
    int storeServerPort = 5000;
    int clientServerPort = 5555;

    // *****************************************
    // TODO: Bereiten Sie hier die Sockets und die Hash Funktion für die Anzahl der Stores vor.
    // *****************************************
    toUse=new HashString(storeCount);// hashfunktion fuer die Anzahl der Stores
    sockets=new Socket[storeCount];// hier speichere ich dann die sockets

    ServerSocket storeServer = new ServerSocket(storeServerPort);
    try {
      for (int i = 0; i < storeCount; i++) {
        System.out.println("Waiting for store " + (i+1) + " of " + storeCount + "...");
        sockets[i]=storeServer.accept();// die akzeptierten Sockets wuredn gespeichert
        // TODO: Speichern Sie die akzeptierte Verbindung hier ab.

        // Socket store = storeServer.accept();
        // ...
        
      }
    }
    finally {
      storeServer.close();
    }
    System.out.println("All stores connected, waiting for client requests.");

    ServerSocket server = new ServerSocket(clientServerPort);
    try {
      while (true) {
        Socket client = server.accept();
        new Thread(new Master(client)).start();
      }
    }
    finally {
      server.close();
    }
  }
}
