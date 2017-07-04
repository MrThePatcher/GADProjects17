package blatt08;

import java.util.ArrayList;

public class BinomialTreeNode {
	private int elem;
	// private int rank;
	private ArrayList<BinomialTreeNode> children;

	public BinomialTreeNode(int key) {
		this.elem = key;
		// this.rank=0;
		this.children = new ArrayList<BinomialTreeNode>();
	}

	/**
	 * Ermittelt das minimale Element im Teilbaum.
	 *
	 * @return das minimale Element
	 */
	public int min() {
		return elem;
	}

	/**
	 * Gibt den Rang des Teilbaumes zurück.
	 *
	 * @return der Rang des Teilbaumes
	 */
	public int rank() {
		return children.size();
	}

	/**
	 * Entfernt den minimalen Knoten (= Wurzel) und gibt eine Menge von
	 * Teilbäumen zurück, in die der aktuelle Baum zerfällt, wenn man den
	 * Knoten entfernt.
	 *
	 * @return die Menge von Teilbäumen
	 */
	public BinomialTreeNode[] deleteMin() {
		BinomialTreeNode[] outp = (BinomialTreeNode[]) children.toArray();
		return outp;
	}

	/**
	 * Diese Methode vereint zwei Bäume des gleichen Ranges.
	 *
	 * @param a
	 *            der erste Baum
	 * @param b
	 *            der zweite Baum
	 * @return denjenigen der beiden Bäume, an den der andere angehängt wurde
	 */
	public static BinomialTreeNode merge(BinomialTreeNode a, BinomialTreeNode b) {
		BinomialTreeNode smaller;
		BinomialTreeNode bigger;
		if (a.rank() == b.rank()) {

			if (a.min() > b.min()) {
				smaller = b;
				bigger = a;
			} else {
				smaller = a;
				bigger = b;
			}
		} else {
			throw new RuntimeException();
		}
		smaller.children.add(bigger);
		return smaller;
	}
}
