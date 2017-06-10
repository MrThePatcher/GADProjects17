package blatt05;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.LongStream;

/**
 * Die Klasse {@link DoubleHashString} kann dazu verwendet werden, Strings zu
 * hashen.
 */
public class DoubleHashString implements DoubleHashable<String> {
	private ArrayList<Long> a;

	private Random random;

	private int w;

	private int size;

	/**
	 * Dieser Konstruktor initialisiert ein {@link DoubleHashString} Objekt für
	 * einen gegebenen Maximalwert (size - 1) der gehashten Werte.
	 * 
	 * @param size
	 *            die Größe der Hashtabelle
	 */
	public DoubleHashString(int size) {
		random = new Random();
		this.a = new ArrayList<>();
		this.size = size;
		// 2er-Logarithmus berechnen
		for (size >>= 1; size > 0; size >>= 1)
			this.w++;
		// assert(this.w <= 30);
	}

	private int extract(int compIndex, byte[] bytes) {
		int fromByte = (w * compIndex) / 8;
		int dw = 0;
		// Zunächst extrahieren wir bis zu 4 Bytes vom String
		for (int i = 0; i + fromByte < bytes.length && i < 8; i++)
			dw |= (int) bytes[i + fromByte] << (24 - 8 * i);
		int preceedingBits = (w * compIndex) % 8;
		// Nun schieben wir den Ausschnitt ganz nach vorne
		dw >>= (32 - preceedingBits - w);
		// Schließlich entfernen wir störende Bits vor den Daten
		dw &= (1 << w) - 1;
		return dw;
	}

	/**
	 * Diese Methode berechnet h(key) für einen String.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	public long hash(String key) {
		byte[] bytes = key.getBytes();
		synchronized (a) {
			this.a=new ArrayList<>();
			while ((a.size() * w + 7) / 8 < bytes.length)
				a.add((long) random.nextInt(size));
		}
		long hash = LongStream.range(0, key.length()).map(i -> extract((int) i, bytes) * a.get((int) i)).sum() % size;
		return hash;

	}

	/**
	 * Diese Methode berechnet h'(key) für einen String.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	public long hashTick(String key) {
		byte[] bytes = key.getBytes();
		synchronized (a) {
			this.a=new ArrayList<>();
			while ((a.size() * w + 7) / 8 < bytes.length)
				a.add((long) random.nextInt(size));
		}
		long hash = LongStream.range(0, key.length()).map(i -> extract((int) i, bytes) * a.get((int) i)).sum() % size;
		return hash;
	}
}
