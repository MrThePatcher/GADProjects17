package blatt05;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Die Klasse DoubleHashTable implementiert eine Hashtabelle, die doppeltes
 * Hashing verwendet.
 *
 * @param <K>
 *            der Typ der Schlüssel, die in der Hashtabelle gespeichert werden
 * @param <V>
 *            der Typ der Werte, die in der Hashtabelle gespeichert werden
 */
public class DoubleHashTable<K, V> {
	private V[] vArray;
	private int elements = 0;
	private int size;
	int colissions;
	int maxRehashes;
	DoubleHashable<K> hashable;

	/**
	 * Diese Methode implementiert h(x, i).
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @param i
	 *            der Index, der angibt, der wievielte Hash für den gegebenen
	 *            Schlüssel berechnet werden soll
	 * @return der generierte Hash
	 */
	private int hash(K key, int i) {
		// h(x,i)=h(x)+i*h'(x)
		int outp = (int) ((hashable.hash(key) + i * hashable.hashTick(key)) % size);
		return outp;
	}

	/**
	 * Dieser Konstruktor initialisiert die Hashtabelle.
	 * 
	 * @param primeSize
	 *            die Größe 'm' der Hashtabelle; es kann davon ausgegangen
	 *            werden, dass es sich um eine Primzahl handelt.
	 * @param hashableFactory
	 *            Fabrik, die aus einer Größe ein DoubleHashable<K>-Objekt
	 *            erzeugt.
	 */
	@SuppressWarnings("unchecked")
	public DoubleHashTable(int primeSize, HashableFactory<K> hashableFactory) {
		this.size = primeSize;
		this.hashable = hashableFactory.create(size);
		// irgendeine Speicherform fuer sowas muss her. Wenn das klappt sollte alles klappen
		//wieso ist hier Nullpointer exception?
		final V[] tmp = (V[]) Array.newInstance(vArray.getClass(), size);
		this.vArray=tmp;
		this.colissions = 0;
		this.maxRehashes = 0;

	}

	/**
	 * Diese Methode fügt entsprechend des doppelten Hashens ein Element in die
	 * Hashtabelle ein.
	 * 
	 * @param k
	 *            der Schlüssel des Elements, das eingefügt wird
	 * @param v
	 *            der Wert des Elements, das eingefügt wird
	 * @return 'true' falls das einfügen erfolgreich war, 'false' falls die
	 *         Hashtabelle voll ist.
	 */
	public boolean insert(K k, V v) {
		int position = -1;
		if (k instanceof Integer) {
			if (vArrayFull(position)) {// ist die Hashtabelle voll?
				for (int i = 0; i < vArray.length; i++) {
					position = hash(k, i);
					if (isFree(position)) {// ist der Platz schon besetzt?
						vArray[position] = v;
						elements++;
						break;
					} else {
						colissions++;
						// nix nochmal schleife
					}
					if (i > this.maxRehashes) {
						this.maxRehashes = i;
					}
				}
			} else {
				return false;
			}
			return true;
		} else {
			System.out.println("Es der Key muss ein String oder ein Integer sein.");
			return false;
		}
	}

	private boolean vArrayFull(int pos) {
		if (elements == size) {
			return false;
		} else {
			return true;
		}
	}

	private boolean isFree(int pos) {
		if (vArray[pos] != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Diese Methode sucht ein Element anhand seines Schlüssels in der
	 * Hashtabelle und gibt den zugehörigen Wert zurück, falls der Schlüssel
	 * gefunden wurde.
	 * 
	 * @param k
	 *            der Schlüssel des Elements, nach dem gesucht wird
	 * @return der Wert des zugehörigen Elements, sonfern es gefunden wurde
	 */
	public Optional<V> find(K k) {
		// was hier getan werden muss ist also nur den Key zu hashen und
		// zurueckzugeben?
		Optional<V> outp=null;
		int position = -1;
		for (int i = 0; i < size; i++) {
			// h(x,i)=h(x)+i*h'(x)
			position = hash(k,i);
			V item = vArray[position];
			if(getkey(item)==k){
				outp = (Optional<V>) item;
				break;
			}else{
			}
		}
		
		return outp;

	}

	private K getkey(V v) {
		// wenn es eine zuordnung gaebe dann berechnet man hier aus value halt den key
		// in diesen faellen gibt es pro value nur einen key aber ein key kann ja mehreren value zugeordnet sein
		return null;
	}

	/**
	 * Diese Methode ermittelt die Anzahl der Kollisionen, also die Anzahl der
	 * Elemente, nicht an der 'optimalen' Position in die Hashtabelle eingefügt
	 * werden konnten. Die optimale Position ist diejenige Position, die der
	 * erste Aufruf der Hashfunktion (i = 0) bestimmt.
	 * 
	 * @return die Anzahl der Kollisionen
	 */
	public int collisions() {
		return colissions;
	}

	/**
	 * Diese Methode berechnet die maximale Anzahl von Aufrufen der
	 * Hashfunktion, die nötig waren, um ein einziges Element in die Hashtabelle
	 * einzufügen.
	 * 
	 * @return die berechnete Maximalzahl von Aufrufen
	 */
	public int maxRehashes() {
		return this.maxRehashes;
	}
}
