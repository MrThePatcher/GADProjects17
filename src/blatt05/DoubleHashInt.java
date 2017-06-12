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
		//Vorbild aus Blatt 04;
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
		/*
		 * Ist aus Loesung Blatt04 Hashstring
		 */
		int fromByte = (w * compIndex) / 8;
		int dw = 0;
		for (int i = 0; i + fromByte < bytes.length && i < 8; i++)
			dw |= (int) bytes[i + fromByte] << (24 - 8 * i);
		int preceedingBits = (w * compIndex) % 8;
		dw >>= (32 - preceedingBits - w);
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
		/*
		 * Ist aus Loesung Blatt04 Hashstring
		 */
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
		/*
		 * Ist aus Loesung Blatt04 Hashstring
		 */
		byte[] bytes = intToByte(key);
		synchronized (a) {
			this.a = new ArrayList<>();
			while ((a.size() * w + 7) / 8 < bytes.length) {
				a.add((long) random.nextInt(size));
			}
		}
		/*
		 * anpassen von modulo und es muss mit i multipliziert werden aber 'i' mul kommt dann wsl ausserhalb dieser klasse
		 */
		long hash = LongStream.range(0, (bytes.length * 4)).map(i -> extract((int) i, bytes) * a.get((int) i)).sum() % (size-1);
		return hash;
	}

}
