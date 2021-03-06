package blatt03;

/**
 * Die Klasse DynamicStack soll einen Stapel auf
 * Basis der Klasse {@link DynamicArray} implementieren.
 */
public class DynamicStack {
  private DynamicArray dynArr;
  
  /**
   * Dieses Feld speichert die Anzahl der Elemente auf dem Stapel.
   */
  private int length;
  
  public int getLength() {
    return length;
  }
  
  public DynamicStack (int growthFactor, int maxOverhead) {
    dynArr = new DynamicArray(growthFactor, maxOverhead);
    length = 0;
  }
  
  /**
   * Diese Methode legt ein Element auf den Stapel.
   * 
   * @param value das Element, das auf den Stapel gelegt werden soll
   */
  public void pushBack (int value) {
	 // int pos=length%(dynArr.getInnerLength());
	  if(length<dynArr.getInnerLength()){
		  dynArr.set(length, value);
		  length++;
	  }
	  else if (length==dynArr.getInnerLength()){
		  Interval tmp;
	    	if(length!=0){
	    		tmp=new NonEmptyInterval(0, length-1);
	    	}else{
	    		tmp=new EmptyInterval();
	    	}
		  tmp=dynArr.reportUsage(tmp, 1);
		  dynArr.set(length, value);
		  length++;
		  
	  }else{
		  //muss hier was rein ? muss ich pos ueberhaupt nutzen?
	  }
  }

  /**
   * Diese Methode nimmt ein Element vom Stapel.
   * @return das entfernte Element
   */
  public int popBack () {
    if(length>0){
    	int elem=dynArr.get(length-1);//hier ein -1 hin um richtiges elem zu bekommen
    	length--;
    	
    	Interval tmp;
    	if(length!=0){
    		tmp=new NonEmptyInterval(0, length-1);//hier wsl auch -1
    	}else{
    		tmp=new EmptyInterval();
    	}
    	dynArr.reportUsage(tmp,0);
    	return elem;
    }else{
    	System.out.println("No elements on Stack");
    	return Integer.MIN_VALUE;
    }
  }
}
