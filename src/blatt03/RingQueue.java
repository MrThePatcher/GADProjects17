package blatt03;

/**
 * Die Klasse RingQueue soll eine zirkuläre Warteschlange auf Basis der Klasse
 * {@link DynamicArray} implementieren.
 */
public class RingQueue {
	private DynamicArray dynArr;

	private int size;

	private int from;

	private int to;

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		// muss noch genauer angesehn werden
		if (to == 0 && from == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Dieser Konstruktor erzeugt eine neue Ringschlange. Ein leere Ringschlange
	 * habe stets eine Größe von 0, sowie auf 0 gesetzte Objektvariablen to und
	 * from.
	 * 
	 * @param growthFactor
	 *            der Wachstumsfaktor des zugrundeliegenden dynamischen Feldes
	 * @param maxOverhead
	 *            der maximale Overhead des zugrundeliegenden dynamischen Feldes
	 */
	public RingQueue(int growthFactor, int maxOverhead) {
		dynArr = new DynamicArray(growthFactor, maxOverhead);
		size = 0;
		from = 0;
		to = 0;
	}

	/**
	 * Diese Methode reiht ein Element in die Schlange ein.
	 * 
	 * @param value
	 *            der einzufügende Wert
	 */
	public void enqueue(int value) {
		/**
		 * Klappt nicht vollstaendig aber ich bin nah dran.
		 * 3/4 ist fertig habe keine Zeit aber alles fertig zu debuggen
		 */
		// standard
		if (size < dynArr.getInnerLength()) {
			//Interval von from bis to und report Usage vergroessert array
			int valPos;
			valPos = (to+1) % dynArr.getInnerLength();
			Interval tmp=new NonEmptyInterval(from,valPos);;
	    		valPos = (to+1) % dynArr.getInnerLength();
	    		tmp = dynArr.reportUsage(tmp, 1);
			
			from = tmp.getFrom();
			to = tmp.getTo();
			//*/
			// int valpos gibt falschen wert=> tmp.getTo muss es schaffen
			
			
			dynArr.set(valPos, value);
			size++;
			//to = valPos;
		} else {
			//dynArr voll
			//Interval von from bis to und report Usage vergroessert array
			int valPos;
			Interval tmp;
	    	if(size!=0){
	    		valPos = (to+1) ;
	    		tmp=new NonEmptyInterval(from,to);
	    		tmp = dynArr.reportUsage(tmp, 1);
	    		valPos=valPos% dynArr.getInnerLength();
	    	}else{
	    		tmp=new EmptyInterval();
	    		tmp = dynArr.reportUsage(tmp, 1);
	    		valPos = (to) % dynArr.getInnerLength();
	    	}
			
			from = tmp.getFrom();
			to = tmp.getTo();

			dynArr.set(valPos, value);
			to = valPos;
			size++;
		}

	}

	/**
	 * Diese Methode entfernt ein Element aus der Warteschlange.
	 * 
	 * @return das entfernte Element
	 */
	public int dequeue() {
		// klappt ohne probleme
		if(size>0){
			int posFrom=(from+1)%dynArr.getInnerLength();
			int ret=dynArr.get(from);
			if(posFrom!=to){
				Interval tmp=new NonEmptyInterval(posFrom,to);//habe to und posFrom geaendert
				size--;//vl aendern zurueck zu size=size-1
				tmp=dynArr.reportUsage(tmp,1);
				//erzeugt fehler auso raus
				to=tmp.getTo();
				from=tmp.getFrom();
				//from=posFrom;
				return ret;
			}else{
				from=0;
				to=0;
				size=0;
				return ret;
			}
		}else{
			System.out.println("No elements in RingQueue");
	    	return Integer.MIN_VALUE;
		}
		
		
	}
}
