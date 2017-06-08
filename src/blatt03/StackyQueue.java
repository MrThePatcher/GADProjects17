package blatt03;


/**
 * Die Klasse StackyQueue soll eine Warteschlange auf
 * Basis der Klasse {@link DynamicStack} implementieren. Es
 * soll ausschließlich die Klasse {@link DynamicStack} zur
 * Datenspeicherung verwendet werden.
 */
public class StackyQueue {
	private DynamicStack dynStack;

	private int size;
	private int growthFactor;
	private int maxOverhead;

  /**
   * Diese Methode ermittelt die Länge der Warteschlange.
   * @return die Länge der Warteschlange
   */
  public int getLength() {
	  return size;
    
  }
  
  /**
   * Dieser Konstruktor initialisiert eine neue Schlange.
   * 
   * @param growthFactor
   * @param maxOverhead
   */
  public StackyQueue (int growthFactor, int maxOverhead) {
	  dynStack = new DynamicStack(growthFactor, maxOverhead);
		size = 0;
		this.growthFactor=growthFactor;
		this.maxOverhead=maxOverhead;
  }
  
  /**
   * Diese Methode reiht ein Element in die Schlange ein.
   * 
   * @param value der einzufügende Wert
   */
  public void enqueue (int value) {
    dynStack.pushBack(value);
    size++;
  }
  
  /**
   * Diese Methode entfernt ein Element aus der Warteschlange.
   * 
   * @return das entfernte Element
   */
  public int dequeue () {
	  //theorie super praktisch irgendwas falsch.
	  if(size<0){
	  DynamicStack container=new DynamicStack(growthFactor,maxOverhead);
	  /**
	   * DynStack kopiern bis nur noch unterstes Element da ist.
	   * Dieses unterste Element poppen und speichern
	   * kopierten Stack wieder zurueck kopieren wegen reihenfolge
	   */
	  while(dynStack.getLength()>1){
		  int elem=dynStack.popBack();
		  container.pushBack(elem);
	  }
	  int ret=dynStack.popBack();
	  while(container.getLength()>0){
		  int elem=container.popBack();
		  container.pushBack(elem);
	  }
	  size--;
	  return ret;
	  }else{
		  System.out.println("No elements in StackyQueue");
	    	return Integer.MIN_VALUE;
	  }
			  
  }
}
