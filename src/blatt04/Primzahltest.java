package blatt04;
/**
 * Das Programm liest eine ganze Zahl ein und
 * prueft, ob die eingegebene Zahl eine Primzahl ist.
 */
class Primzahltest { 
  public static void main(String args[]) 
  {  
    // Anforderung der Primzahl
    System.out.println("Das Programm prueft, ob eine eingegebene Zahl eine Primzahl ist.");
    System.out.println("Bitte eine Zahl eingeben und ENTER: ");
    int zahl = 0;
    // Information des Nutzers
    System.out.println("Ist "+zahl+" eine Primzahl?");
    // Ist diese Zahl eine natuerliche Zahl groesser 1?
    if(zahl<=1) {
        System.out.println("Die Zahl "+zahl+" ist nicht groesser 1, also keine Primzahl!" );
	return; // In diesem Fall keine Weiterarbeit!
    }
    // ist die gegebene Zahl gerade?
    if (zahl%2==0) { // Zahl ist gerade!
      System.out.println("Die Zahl " +zahl+" ist gerade!" );
      return;
    }
    System.out.println("Die Zahl 2 teilt nicht "+zahl );
    int i = 3;
    while( i*i < zahl & zahl % i !=0) {
        System.out.println("Die Zahl "+i+" teilt nicht "+zahl );
      i=i+2;
    }
    // Welch Bedingung fuehrte zum Abbruch?
     if( i*i>zahl) { // Keinen Teiler von zahl gefunden
       System.out.println("Die "+zahl+" ist eine Primzahl!" );
     } else { // i ist Teiler
       System.out.println("Die Zahl "+i+" teilt "+zahl+"!" );
    }
  }

} // Ende Primzahltest

