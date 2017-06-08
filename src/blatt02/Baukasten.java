package blatt02;

public class Baukasten {

	public static void main(String[] args) {
		int[] testData={-10, 33, 50, 99, 123,356,1004,3401, 4242 };
		BinSea foo=new BinSea();
		int x=0;
		Interval xoo=new NonEmptyInterval(80,500);
		Interval get;
		get=foo.search(testData, xoo);
		System.out.println(get.toString());

	}

}
