/**
 * 
 */
package workshop.haplotype.ambiguity;

/**
 * @author kazu
 *
 */
public class ConvertToSingleAllele {
	private String singleAllele;
	/**
	 * 
	 */
	public ConvertToSingleAllele(String ambiguity) {
		// TODO Auto-generated constructor stub
		singleAllele = "";
		String [] list = ambiguity.split("/");
		singleAllele = list[0];	// first allele => lowest digit allele
	}
	
	public String getSingleAllele() {
		return singleAllele;
	}

}
