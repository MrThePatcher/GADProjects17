package blatt05;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.LongStream;

/**
 * Die Klasse {@link DoubleHashInt} kann dazu verwendet werden, Integer zu
 * hashen.
 */
public class DoubleHashInt implements DoubleHashable<Integer> {
	private ArrayList<Long> a;

	private Random random;

	private int w;

	private int size;

	/**
	 * Dieser Konstruktor initialisiert ein {@link DoubleHashInt} Objekt für
	 * einen gegebenen Maximalwert (size - 1) der gehashten Werte.
	 * 
	 * @param size
	 *            die Größe der Hashtabelle
	 */
	public DoubleHashInt(int size) {
		random = new Random();
		this.a = new ArrayList<>();
		this.size = size;
		// 2er-Logarithmus berechnen
		for (size >>= 1; size > 0; size >>= 1) {
			this.w++;
		}
	}

	private byte[] intToByte(int foo) {
		byte[] bytes = ByteBuffer.allocate(4).putInt(foo).array();
		return bytes;
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
	 * Diese Methode berechnet h(key) für einen Integer.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	@Override
	public long hash(Integer key) {
		byte[] bytes = intToByte(key);
		synchronized (a) {
			while ((a.size() * w + 7) / 8 < bytes.length) {
				a.add((long) random.nextInt(size));
			}
		}
		long hash = LongStream.range(0, (bytes.length * 4)).map(i -> extract((int) i, bytes) * a.get((int) i)).sum()
				% size;
		return hash;
	}

	/**
	 * Diese Methode berechnet h'(key) für einen Integer.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	@Override
	public long hashTick(Integer key) {
		byte[] bytes = intToByte(key);
		synchronized (a) {
			this.a = new ArrayList<>();
			while ((a.size() * w + 7) / 8 < bytes.length) {
				a.add((long) random.nextInt(size));
			}
		}
		long hash = LongStream.range(0, (bytes.length * 4)).map(i -> extract((int) i, bytes) * a.get((int) i)).sum()
				% size;
		return hash;
	}

}
