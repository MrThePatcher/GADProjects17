package blatt02;
/**
 * Die Klasse BinSea stellt Methoden bereit, in sortierten Feldern binär
 * nach Wertebereichen zu suchen.
 */
class BinSea {
  /**
   * Diese Methode sucht nach einem Wert in einem einem sortierten Feld. Sie
   * soll dabei dazu verwendet werden können, den ersten bzw. letzten Index in
   * einem Intervall zu finden. Wird kein passender Index gefunden, wird
   * -1 zurückgegeben.
   * 
   * Beispiel:
   * 
   *             0 <-----------------------> 5
   * sortedData: [-10, 33, 50, 99, 123, 4242 ]
   * value: 80             ^   ^
   *                       |   |
   *                       |   `- Ergebnis (3) für ersten Index im Intervall, da 99
   *                       |      als erster Wert im Feld größer als 80 ist
   *                       |
   *                       `- Ergebnis (2) für letzten Index im Intervall, da 50
   *                          als letzter Wert kleiner als 80 ist
   * 
   * @param sortedData das sortierte Feld, in dem gesucht wird
   * @param value der Wert der Intervallbegrenzung, der dem gesucht wird
   * @param lower true für untere Intervallbegrenzung, false für obere Intervallbegrenzung
   * @return der passende Index, -1 wenn dieser nicht gefunden werden kann
   */
	// zu private zurueck andaern bitte
  private static int search (int[] sortedData, int value, boolean lower) {
    /**
     * Todo: Aufgabe a)
     * Eingabe : n: Anzahl der (sortierten) Zahlen A[0],...,A[n−1]: Zahlenfolge x: gesuchte Zahl Ausgabe : Index der gesuchten Zahl
     *  L = 0; r = n−1; 
     *  while (L≤r) do m =lower(r + L)/2; 
     *  if A[m] == x then return m; 
     *  if A[m] < x then L = m +1; 
     *  else r = m−1; return−1
     */
	  int numE=0;
	  int r=sortedData.length-1;
	  int m=0;
	  while(numE<=r){
		  // mitte wird gefunden
		  m=(r+numE)/2;
		  if(sortedData[m]==value){
			  return m;
		  }
		  if(sortedData[m]<value){
			  numE=m+1;
		  }else{
			  r=m-1;
		  }
	  }
	  /**
	   * Problem: momentan suche ich nach speziellem Wert!
	   * Jetzt sollte boolean ins spiel kommen.
	   */
	  if(lower){
		  int x;
		  try{
		  x=value-sortedData[m-1];
		  }catch(Exception e){
			  x=value-sortedData[m];
		  }
		  x=Math.abs(x);
		  for(int j=0;j<3;j++){
			  int posNow=m-1+j;
			  if(posNow<sortedData.length&&posNow>=0){
				  if(sortedData[posNow]<value){
					  int newdiff=Math.abs(value-sortedData[posNow]);
					  x=Math.min(x,newdiff);
				  }
			  }
		  }
		  try{
		  if((x+sortedData[m-1])==value){
			  return m-1;
		  }}catch(Exception e){}
		  try{
		  if((x+sortedData[m])==value){
			  return m;
		  }}catch(Exception e){}
		  try{
		  if((x+sortedData[m+1])==value){
			  return m+1;
		  }}catch(Exception e){}
		  
	  }else{
		  int x;
		  try{
		  x=sortedData[m+1]-value;
		  }catch(Exception e){
			  x=sortedData[m]-value;
		  }
		  x=Math.abs(x);
		  for(int j=0;j<3;j++){
			  int posNow=m-1+j;
			  if(posNow<sortedData.length&&posNow>=0){
				  if(sortedData[posNow]>value){
					  int newdiff=Math.abs(sortedData[posNow]-value);
					  x=Math.min(x,newdiff);
				  }
			  }
		  }
		  try{
		  if((x+value)==sortedData[m-1]){
			  return m-1;
		  }}catch(Exception e){}
		  try{
		  if((x+value)==sortedData[m]){
			  return m;
		  }}catch(Exception e){}
		  try{
		  if((x+value)==sortedData[m+1]){
			  return m+1;
		  }}catch(Exception e){}
		  
	  }
	  return -1;
  }

  /**
   * Diese Methode sucht ein Intervall von Indices eines sortierten Feldes, deren Werte
   * in einem Wertebereich liegen. Es soll dabei binäre Suche verwendet werden.
   * 
   * Beispiel:
   *                     0 <-----------------------> 5
   * sortedData:         [-10, 33, 50, 99, 123, 4242 ]
   * valueRange: [80;700]              ^   ^
   *                                   |   |
   *                                   |   `- Ergebnis (4) für obere Intervallbegrenzung,
   *                                   |      da 123 als letzter Wert kleiner oder gleich 700 ist
   *                                   |
   *                                   `- Ergebnis (3) für untere Intervallbegrenzung,
   *                                      da 99 als erster Wert größer oder gleich 80 ist
   * 
   * @param sortedData das sortierte Feld, in dem gesucht wird
   * @param valueRange der Wertebereich, zu dem Indices gesucht werden
   */
  public static Interval search (int[] sortedData, Interval valueRange) {
    /**
     * Todo: Aufgabe b)
     */
	  int indizi1=0,indizi2=0;
	  Interval output;
	  
	  
	  if(valueRange.toString().equals("[]")){
		  //arbeite an equals methode
		  output=new EmptyInterval();
	  }else{
		  indizi1=search(sortedData,valueRange.getFrom(),true);
		  if(indizi1==-1){
			 indizi1= search(sortedData,valueRange.getFrom(),false);
		  }
		  indizi2=search(sortedData,valueRange.getTo(),false);
		  if(indizi2==-1){
			indizi2=  search(sortedData,valueRange.getTo(),true);
		  }
		  output=new NonEmptyInterval(indizi1,indizi2);
	  }
	  return output;
  }

}
