package blatt06;

public class Mergesort implements SortingBase {
	public void sort(int[] numbers) {
		// TODOs
		/**
		 * if (l == r) return; // nur ein Element m = (r + l)/2; // Mitte
		 * mergeSort(a, l, m); // linken Teil sortieren mergeSort(a, m + 1,
		 * r);// rechten Teil sortieren j = l; k = m + 1; // verschmelzen for (i
		 * =0; i <=r - l; i++) if (j > m) { b[i] = a[k]; k++; } // linker Teil
		 * leer else if (k > r) { b[i] = a[j]; j++; } // rechter Teil leer else
		 * if (a[j] <= a[k]) { b[i] = a[j]; j++; } else {b[i] = a[k]; k++; }
		 * for(i = 0; i < r -l; i++) a[l + i] = b[i]; // zurueckkopieren
		 */
		int l, r, m;
		l = 0;
		r = numbers.length - 1;
		mergeSort(numbers,l,r);

	}

	private void mergeSort(int[] a, int l, int r) {
		// stark an VL angelehnt
		int[] b = new int[r-l+1];// laenge stimmt nicht vl?
		if (l == r)
			return; // nur ein Element
		int m, j, k;
		m = (r + l) / 2; // Mitte
		mergeSort(a, l, m); // linken Teil sortieren
		mergeSort(a, m + 1, r);// rechten Teil sortieren
		j = l;
		k = m + 1; // verschmelzen
		for (int i = 0; i <= r - l; i++)
			if (j > m) {
				b[i] = a[k];
				k++;
			} // linker Teil leer
			else if (k > r) {
				b[i] = a[j];
				j++;
			} // rechter Teil leer
			else if (a[j] <= a[k]) {
				b[i] = a[j];
				j++;
			} else {
				b[i] = a[k];
				k++;
			}
		for (int i = 0; i <= r - l; i++) {
			a[l + i] = b[i];
		} // zurueckkopieren
	}

	public String getName() {
		return "Mergesort";
	}
}
