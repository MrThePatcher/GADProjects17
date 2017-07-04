package blatt07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RadixchenTest {
	public static void main(String[] args) {
		ArrayList<Integer> input = new ArrayList<Integer>();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			// TODO: Eingabe in einer Liste speichern.
			input.add(scanner.nextInt());
		}
		/*
		Random r=new Random();
		for(int i=0;i<70;i++){
			input.add(r.nextInt(100));
		}*/
		Integer[] elements = Arrays.copyOf(input.toArray(), input.size(), Integer[].class);
		//Integer[] elements={9,3,5,7,1};
		RadixSort sort = new RadixSort();
		sort.sort(elements);
		for (int i = 0; i < elements.length; i++) {
			System.out.println(elements[i]);
		}
		// TODO: eingegebene Liste per RadixSort sortieren und zeilenweise
		// ausgeben.
	}
}
