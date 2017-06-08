package blatt03;

/**
 * Objekte der Klasse Interval stellen einen Bereich von Zahlen dar. Die
 * Intervallgrenzen sind dabei jeweils eingeschlossen.
 */
abstract class Interval {
	/**
	 * Abfragen der unteren Intervallgrenze
	 * 
	 * @return die untere Grenze
	 */
	public abstract int getFrom();

	/**
	 * Abfragen der oberen Intervallgrenze
	 * 
	 * @return die obere Grenze
	 */
	public abstract int getTo();

	/**
	 * Diese Methode gibt die Größe des Intervalls zurück. Zu beachten ist
	 * dabei, dass falls getFrom() > getTo() gilt, das Intervall die Elemente in
	 * den Teilintervallen [usage.getFrom(); baseSize - 1] und [0;
	 * usage.getTo()] abdeckt.
	 * 
	 * @param baseSize
	 *            die Basisgröße, anhand derer ein Umbruch innerhalb des
	 *            Intervalls festgestellt wird
	 * @return die Menge an abgedeckten Elementen
	 */
	public abstract int getSize(int baseSize);

	/**
	 * Gibt zurück, ob das Intervall leer ist.
	 * 
	 * @return 'true' falls das Intervall leer ist, 'false' sonst
	 */
	public abstract boolean isEmpty();
	//neue Set Methoden
	public abstract void setTo(int updateTo);

	public abstract void setFrom(int updateFrom);
}

/**
 * Objekte der Klasse NonEmptyInterval repräsentieren ein nicht-leeres
 * Intervall.
 */
class NonEmptyInterval extends Interval {
	private int from;

	@Override
	public int getFrom() {
		return from;
	}

	private int to;

	@Override
	public int getTo() {
		return to;
	}

	public NonEmptyInterval(int from, int to) {
		if (from < 0 || to < 0)
			throw new RuntimeException("Negative interval boundaries are invalid!");
		this.from = from;
		this.to = to;
	}

	@Override
	public String toString() {
		return "[" + from + ";" + to + "]";
	}

	@Override
	public int getSize(int baseSize) {
		if (to >= baseSize || from >= baseSize)
			throw new RuntimeException("Invalid interval for this base size!");
		int size;
		if (getFrom() > getTo()) {
			// ich wollte -1bei get From machen (angabe) und +1 bei getTo
			// hab ich also rausgekuerzt
			size = (baseSize + -getFrom()) + getTo()+1;// habe ein +1 entfernt hiner getfrom=> falscher Wert?
														
		} else {
			size = getTo() - getFrom()+1;
		}
		return size;

	}

	@Override
	public boolean isEmpty() {
		return false;
		// soll ich hier noch irgendwelche zahle vergleichen ?
	}
	//neue Set Methoden
	@Override
	public void setTo(int updateTo) {
		this.to = updateTo;
	}

	@Override
	public void setFrom(int updateFrom) {
		this.from = updateFrom;
	}
}

/**
 * Objekte der Klasse EmptyInterval repräsentieren ein leeres Intervall.
 */
class EmptyInterval extends Interval {
	@Override
	public int getFrom() {
		throw new RuntimeException("No lower boundary in empty interval");
	}

	@Override
	public int getTo() {
		throw new RuntimeException("No upper boundary in empty interval");
	}

	public EmptyInterval() {
	}

	@Override
	public String toString() {
		return "[]";
	}

	@Override
	public int getSize(int baseSize) {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}
//neue Set Methoden
	@Override
	public void setTo(int updateTo) {
		System.out.println("No SetTo possible in Empty Interval");
	}

	@Override
	public void setFrom(int updateFrom) {
		System.out.println("No SetFrom possible in Empty Interval");
	}
}
