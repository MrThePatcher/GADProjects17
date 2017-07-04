package blatt07;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RadixSort {
	private int key(Integer element, int digit) throws IllegalArgumentException {
		String tmp = element.toString();
		String tmp2;
		//tmp2 = ""+tmp.charAt(tmp.length()-1-digit);
		if(digit<tmp.length()){
			tmp2 = ""+tmp.charAt(tmp.length()-1-digit);
		}else{
			tmp2="0";
		}
		int outp = Integer.valueOf(tmp2);
		return outp;
	}

	private void concatenate(ArrayList<Integer>[] buckets, Integer[] elements) {

		int elemPos = 0;// einfuegeOrt in Elementes
		for (int i = 0; i < buckets.length; i++) {
			 if(buckets[i].size()!=0){
				 for (int j = 0; j < buckets[i].size(); j++){// erhoehe
						elements[elemPos] = buckets[i].get(j);
						elemPos++;
				 }
			 }
			
		}
	}

	private void kSort(Integer[] elements, int digit) {
		// ArrayList<ConcurrentLinkedQueue<Integer>> buckets = new
		// ArrayList<ConcurrentLinkedQueue<Integer>>(10);
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] buckets = (ArrayList<Integer>[]) Array.newInstance(ArrayList.class, 10);
		for (int i = 0; i < 10; i++) {
			// buckets.add(new ConcurrentLinkedQueue<Integer>());
			buckets[i] = new ArrayList<Integer>();
		}
		// Buckets erstellt
		for (int i = 0; i < elements.length; i++) {
			int chosenBucket = key(elements[i], digit);
			buckets[chosenBucket].add(elements[i]);
		}
		// buckets leeren in Feld
		concatenate(buckets, elements);
	}

	public void sort(Integer[] elements) {
			for(int i=0;i<=10;i++){
				kSort(elements,i);
			}
	}
}
