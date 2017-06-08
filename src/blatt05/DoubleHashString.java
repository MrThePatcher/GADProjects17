package blatt05;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.LongStream;

/**
 * Die Klasse {@link DoubleHashString} kann dazu verwendet werden,
 * Strings zu hashen.
 */
public class DoubleHashString implements DoubleHashable<String> {
  /*
   * Todo
   */

  /**
   * Dieser Konstruktor initialisiert ein {@link DoubleHashString}
   * Objekt für einen gegebenen Maximalwert (size - 1) der gehashten
   * Werte.
   * 
   * @param size die Größe der Hashtabelle
   */
  public DoubleHashString (int size) {
    /*
     * Todo
     */
  }
  
  /**
   * Diese Methode berechnet h(key) für einen String.
   * 
   * @param key der Schlüssel, der gehasht werden soll
   * @return der Hashwert des Schlüssels
   */
  public long hash (String key) {
    /*
     * Todo
     */
  }
  
  /**
   * Diese Methode berechnet h'(key) für einen String.
   * 
   * @param key der Schlüssel, der gehasht werden soll
   * @return der Hashwert des Schlüssels
   */
  public long hashTick (String key) {
    /*
     * Todo
     */
  }
}
