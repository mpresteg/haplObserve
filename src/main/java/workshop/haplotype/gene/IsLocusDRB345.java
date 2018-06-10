/**
 * This program tests whether provided locus/type is DRB345 or not
 * See GLstring class
 */
package workshop.haplotype.gene;

/**
 * @author kazutoyo
 * @version December 18 2015
 *
 */
public class IsLocusDRB345 {
	private boolean test;

	/**
	 * 
	 */
	public IsLocusDRB345(String locus) {
		// TODO Auto-generated constructor stub
		test = true;
		if (!((locus.contains("DRB3")) || (locus.contains("DRB4")) 
				|| (locus.contains("DRB5")))) {	// if locus is NOT DRB345
			test = false;
		}
		
	}
	
	public boolean getTestResult() {		
		return test;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IsLocusDRB345 test1 = new IsLocusDRB345("HLA-A");
		System.out.println(test1.getTestResult());
		IsLocusDRB345 test2 = new IsLocusDRB345("HLA-DRB1");
		System.out.println(test2.getTestResult());
		IsLocusDRB345 test3 = new IsLocusDRB345("HLA-DRB4");
		System.out.println(test3.getTestResult());

	}

}
