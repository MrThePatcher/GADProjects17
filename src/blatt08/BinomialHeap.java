package blatt08;

import java.util.ArrayList;
import java.util.Optional;

public class BinomialHeap {
	private BinomialTreeNode min;
	ArrayList<BinomialTreeNode> heapChildren;

	/**
	 * Dieser Konstruktor baut einen leeren Haufen.
	 */
	public BinomialHeap() {
		this.heapChildren = new ArrayList<BinomialTreeNode>();
		min = null;
	}

	/**
	 * Diese Methode ermittelt das minimale Element im binomialen Haufen. Wenn
	 * es dieses nicht gibt (bei leerem Haufen), soll eine RuntimeException
	 * geworfen werden.
	 *
	 * @return das minimale Element
	 */
	public int min() {
		if (this.min != null) {
			return this.min.min();
		} else {
			return Integer.MIN_VALUE;
		}
	}

	/**
	 * Diese Methode fügt einen Schlüssel in den Haufen ein.
	 * 
	 * @param key
	 *            der einzufügende Schlüssel
	 */
	public void insert(int key) {
		BinomialTreeNode node = new BinomialTreeNode(key);
		ArrayList<BinomialTreeNode> inp=new ArrayList<BinomialTreeNode>();
		inp.add(node);
	    merge(inp,false);
	}

	private void merge(ArrayList<BinomialTreeNode> tops, boolean ignoreMin) {
		BinomialTreeNode minimum = null, carry = null;
		ArrayList<BinomialTreeNode> tmpTrees = new ArrayList<BinomialTreeNode>();
		int rank = 0;
		int treeIndex = 0;
		int headsIndex = 0;
		while (true) {
			BinomialTreeNode heapPointer = null;
			int minRank = 0;
			/*
			 * 1.Suche in Haufen einen Baum mit Rang rank 
			 * 
			 * 2.Suche in heads baum mit rang rank 
			 * 
			 * 3. aktualisiere minrank
			 */
			while (treeIndex < heapChildren.size()) {
				BinomialTreeNode chosen = heapChildren.get(treeIndex);
				 if(ignoreMin && (chosen == this.min)) {
				 treeIndex++;
				 continue;
				 }
				minRank = chosen.rank();
				if (chosen.rank() == rank) {
					heapPointer = chosen;
					treeIndex++;
				}
				break;
			}
			//2.
			BinomialTreeNode topPointer = null;
			if (headsIndex < tops.size()) {
				BinomialTreeNode tree = tops.get(headsIndex);
				if (tree.rank() < minRank)
					//3.
					minRank = tree.rank();
				if (tree.rank() == rank) {
					topPointer = tree;
					headsIndex++;
				}
			}
			if (heapPointer == null && topPointer == null && carry == null) {
				if (treeIndex >= heapChildren.size() && headsIndex >= tops.size()) {
					// kein Baum in haufen oder heads
					break;

				} else {
					// rang aktualisieren
					rank = minRank;
					continue;
				}
			} else
				// berechnung von naechstem Baum und Carry Baum
				rank++;

			ArrayList<BinomialTreeNode> result = add(heapPointer, topPointer, carry);
			BinomialTreeNode x = result.get(0);
			/*
			 * Minimum Aktualisieren
			 */
			carry = result.get(1);
			if (x != null) {
				if (minimum == null || x.min() < minimum.min())
					minimum = x;
				tmpTrees.add(x);
			}
		}
		this.heapChildren = tmpTrees;
		this.min = minimum;
	}

	private ArrayList<BinomialTreeNode> add(BinomialTreeNode heapPointer, BinomialTreeNode topPointer,
			BinomialTreeNode carry) {
		ArrayList<BinomialTreeNode> nodes = new ArrayList<BinomialTreeNode>(3);
		ArrayList<BinomialTreeNode> outp = new ArrayList<BinomialTreeNode>(3);
		for (int i = 0; i <3; i++) {
			nodes.set(i, null);
		}

		int count = 0;
		if (heapPointer != null) {
			nodes.set(count++, heapPointer);
		}
		if (topPointer != null) {
			nodes.set(count++, topPointer);
		}
		if (carry != null) {
			nodes.set(count++, carry);
		}

		if (count <= 1) {
			outp.add(nodes.get(0));
			return outp;
		} else {
			assert (nodes.get(0).rank() == nodes.get(1).rank());
			outp.add(nodes.get(2));
			BinomialTreeNode tmp = BinomialTreeNode.merge(nodes.get(0), nodes.get(1));
			outp.add(tmp);
			return outp;
		}
	}

	/**
	 * Diese Methode entfernt das minimale Element aus dem binomialen Haufen und
	 * gibt es zurück.
	 *
	 * @return das minimale Element
	 */
	public int deleteMin() {
	    int min = min();
	    ArrayList<BinomialTreeNode> inp=new ArrayList<>();
	    BinomialTreeNode children [] = this.min.deleteMin();
	    for(int i =0;i<children.length;i++){
	    	inp.add(children[i]);
	    }
	    
	    merge(inp,true);
	    return min;
	}
}
