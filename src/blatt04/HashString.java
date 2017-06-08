package blatt04;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Die Klasse {@link HashString} kann dazu verwendet werden,
 * Strings zu hashen.
 */
public class HashString {
  private int size;
  //ArrayList<ArrayList<String>> hashfield;

  /**
   * Dieser Konstruktor initialisiert ein {@link HashString}
   * Objekt für einen gegebenen Maximalwert (size - 1) der gehashten
   * Werte.
   * 
   * @param size die Größe der Hashtabelle
   */
  public HashString (int size) {
    this.size=size;
   // hashfield=new ArrayList<ArrayList<String>>(size);
  }
  public boolean isPrim(int value) {
      if (value <= 16) {
          return (value == 2 || value == 3 || value == 5 || value == 7 || value == 11 || value == 13);
      }
      if (value % 2 == 0 || value % 3 == 0 || value % 5 == 0 || value % 7 == 0) {
          return false;
      }
      for (int i = 10; i * i <= value; i += 10) {
          if (value % (i+1) == 0) {  // 11, 21, 31, 41, 51, ...
              return false;
          }
          if (value % (i+3) == 0) {  // 13, 23, 33, 43, 53, ...
              return false;
          }
          if (value % (i+7) == 0) {  // 17, 27, 37, 47, 57, ...
              return false;
          }
          if (value % (i+9) == 0) {  // 19, 29, 39, 49, 59, ...
              return false;
          }
      }
      return true;
  }
  private int StringToInt(String inp){
	  int out=0;
	  for(int i=0;i<inp.length();i++){
		  out+=inp.charAt(i);
	  }
	  return out;
  }
  private int StringToByteInt(String inp){
	  int out=0;
	  byte[] numbers=inp.getBytes();
	  
	  for(int i=0;i<numbers.length;i++){
		  out=out+numbers[i];
	  }
	  return out;
  }
  private int[] makeVectorA(int length,int max){
	  int[]out=new int[length];
	  Random ran=new Random();
	  for(int i=0;i<length;i++){
		  out[i]=ran.nextInt(max);
	  }
	  return out;
  }
  private int skalarMult(int[]x,int[]a,int top){
	  int out=0;
	  for(int i=0;i<top;i++){
		  out+=(x[i]*a[i]);
	  }
	  return out;
  }
  
  /**
   * Diese Methode berechnet den Hashwert für einen String.
   * 
   * @param key der Schlüssel, der gehasht werden sollen
   * @return der Hashwert des Schlüssels
   */
  public synchronized int hash (String key) {
    double w=Math.log(size)/Math.log(2);// log2
    int k=(int) Math.ceil(size/w);//auffuellen der vektorren
    int []things=new int[k];
    for(int i=1;i<=k;i++){
    	int mul=i-1;
    	String tmp=key.substring(mul*(int)w,i*(int)w);//laenge nachberechnen
    	things[mul]=StringToByteInt(tmp);
    	
    	//Ein Array mit den Teilwoertern. nun muss das noch in Zahlen umgewandelt werden
    	/*for(int j=0;j<things[mul].length();j++){
    		int fac1=(int)Math.pow(10.0,(double)j);
    		byte[]value =tmp.getBytes();
    	}*/
    }
    //things=x
    int[]a=makeVectorA(k,size);//trivial
    int sol=skalarMult(things,a,k);//trivial
    sol=sol%size;
    return sol;
    
    
  }
}
