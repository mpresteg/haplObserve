/**
 * This abstract class was to reduce the code for NormalizePhaseAmbiguity class
 * deal with no phase ambiguity and homozygous
 */
package workshop.haplotype.family.ngs;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import workshop.haplotype.ambiguity.FixAmbiguityNumericalOrder;
import workshop.haplotype.organize.NonRedundantList;
import workshop.haplotype.sirona.SironaHMLbugFix;

/**
 * @author kazu
 * @version October 27 2017
 *
 */
public abstract class NormalizePhaseAmbiguityAbstract {
	protected String glString;
	protected boolean resolved;
	protected NonRedundantList nonGllist;		// to be inherited
	protected boolean homozygous;				// to be inherited

	/**
	 * 
	 */
	public NormalizePhaseAmbiguityAbstract(String type) {
		// TODO Auto-generated constructor stub	
		// add these first line to deal with Siron's HML problem		
		SironaHMLbugFix shb = new SironaHMLbugFix(type);		
		
		nonGllist = new NonRedundantList();	// nonRedundant list
		glString = "";
		resolved = false;
		homozygous = false;
		
		if (!shb.getCorrectedGlString().contains("|")) {	// if no phase ambiguity
			glString = shb.getCorrectedGlString();
			resolved = true;			
		}
		
		else {	// phase ambiguity
			String [] glList = shb.getCorrectedGlString().split("\\|");	// back slash was required		
			for (String gl : glList) {
				nonGllist.addNonRedundantList(gl);	// populate all possibilities
//				System.out.println(gl);
			}
			
			NonRedundantList element = new NonRedundantList();
			for (String gl : nonGllist.getList()) {
				String [] alleleList = gl.split("\\+");	// separate	
				Arrays.sort(alleleList);

				Set<String> typeSet = new TreeSet<String>();	// to test homozygous
				for (String allele : alleleList) {	
					typeSet.add(allele);
					element.addNonRedundantList(allele);
				}
				if (typeSet.size() == 1) {		// both same alleles => homozygous
					homozygous = true;	// only one incident is OK
				}	
			}
						
			if (homozygous) {	// if homozygous => convert genotype ambiguity to allele ambiguity
				String tmp = "";
				glString = "";
				int count = 0;
				for (String str : element.getList()) {
					if (count != 0) {
						tmp += "/";
					}
					tmp += str;
					count++;
				}
				FixAmbiguityNumericalOrder fano = new FixAmbiguityNumericalOrder(tmp);
				glString = fano.getFixedGl() + "+" + fano.getFixedGl();
			}
		}
		
	}
	
	public String getGlString () {
		return glString;
	}
	
	public boolean getResolved() {
		return resolved;
	}	
	
}
