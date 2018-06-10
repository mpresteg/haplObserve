/**
 * This class was added to deal with MiaFora's know HML bugs
 */
package workshop.haplotype.sirona;

/**
 * @author kazu
 * @version April 26 2017
 *
 */
public class SironaHMLbugFix {
	private String correctedGlstring;

	/**
	 * 
	 */
	public SironaHMLbugFix (String glstring) {
		// TODO Auto-generated constructor stub
		correctedGlstring = glstring;		
		String dr15 = "HLA-DRB1*15:01:01:01+HLA-DRB1*15:01:01:03";
		String dpb1_0201= "HLA-DPB1*02:01:02+HLA-DPB1*04:01:01:01|HLA-DPB1*02:01:02+HLA-DPB1*04:01";
		
		//add DPB1 bug		
		if (glstring.contains(dr15)) {
			correctedGlstring = "HLA-DRB1*15:01:01:01+HLA-DRB1*15:01:01:01|"
					+ "HLA-DRB1*15:01:01:01+HLA-DRB1*15:01:01:02|"
					+ "HLA-DRB1*15:01:01:01+HLA-DRB1*15:01:01:03|"
					+ "HLA-DRB1*15:01:01:02+HLA-DRB1*15:01:01:02|"
					+ "HLA-DRB1*15:01:01:02+HLA-DRB1*15:01:01:03|"
					+ "HLA-DRB1*15:01:01:03+HLA-DRB1*15:01:01:03";
		}
		else if (glstring.equals(dpb1_0201)) {
			correctedGlstring = "HLA-DPB1*02:01:02+HLA-DPB1*04:01:01:01";
		}
	}
	
	public String getCorrectedGlString() {
		return correctedGlstring;
	}

}
