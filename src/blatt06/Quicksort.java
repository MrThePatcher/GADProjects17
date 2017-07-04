package blatt06;
public class Quicksort implements SortingBase {
	public void sort(int[] numbers) {
		quickSort(numbers,0,(numbers.length-1));
	}
	public void quickSort(int[]a,int l,int r){
		//angelehnt an Vorlesung 
		if (l < r) {
		int p = a[r]; // Pivot
		int i = l- 1; 
		int j = r;
		do {// spalte Elemente in a[l,...,r - 1] nach Pivot p
		do { i++ ;} while (a[i] < p);
		do { j--;} while ((j >=l)&&( a[j] > p));
		if (i < j) swap(a, i, j);
		} while (i < j);
		swap (a,i, r); // Pivot an richtige Stelle
		quickSort(a, l, i - 1);
		quickSort(a, i + 1, r);
		}
	}
	private void swap(int[]table,int first,int second){
		int tmp=table[first];
		table[first]=table[second];
		table[second]=tmp;
		
	}

	public String getName() {
		return "Quicksort";
	}
}
