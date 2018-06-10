/**
 * Four hapotypes should be identified from parental haplotypes
 * 2 from paternal and 2 from maternal
 * 
 */
package workshop.haplotype.organize;

import java.util.HashMap;
import java.util.Map;

import workshop.haplotype.family.organize.OrganizeHaplotype;

/**
 * @author kazutoyo
 * @version April 26 2017
 *
 */
public class ValidateHaploType {
	// When one of the parent haplotype is invalid,
	// childen's haplotype is invalid
	private boolean childValidation;
	// evaluate which parent had potential recombination
	private Map<String, Boolean> parentValidation;
	private NonRedundantList nonredundantParentHap;	// capture all haplotypes from parents
	private Map<String, Boolean> parentHomozygous;

	/**
	 * 
	 */
	public ValidateHaploType(OrganizeHaplotype oh) {
		// TODO Auto-generated constructor stub
		nonredundantParentHap = new NonRedundantList();	// nonredundant list 
		
		childValidation = false;	// false for default
		int validatedCount = 0;		
		
		parentValidation = new HashMap<String, Boolean>();			
		parentHomozygous = new HashMap<String, Boolean>();
		
		for (String parent : oh.getParentList()) {
			boolean homozygous = false;
			NonRedundantList nrdl = new NonRedundantList();	// nonredundant list
			boolean validated = false;	// false for default		
			int childCount = 0;

			for (String child : oh.getChildList()) {	// go through child list
				childCount++;
				for (String parentHap : oh.getHaplotypeMapList().get(child).get(parent)) {	// go through parent Hap
					nrdl.addNonRedundantList(parentHap);
					nonredundantParentHap.addNonRedundantList(parentHap);
				}							
			}
			if (nrdl.getList().size() == 1) {	// homozygous
				System.err.println("WARNING: " + parent + ": Homozygous Haplotype");
				validated = true;	// homozygous validation true
				homozygous = true;
			}
			
			else if (nrdl.getList().size() == 2) {	// 4 distinct haplotypes
				validated = true;
			}
			
			if (childCount == 1) {
				validated = false;
			}
			
			if (validated) {
				validatedCount++;
			}
			
			parentValidation.put(parent, validated);	
			parentHomozygous.put(parent, homozygous);
		}
		
		if (validatedCount == 2) {
			childValidation = true;
		}
		
	}
	
	public boolean getChildValidation() {
		return childValidation;
	}	
	
	public Map<String, Boolean> getParentValidation() {
		return parentValidation;
	}
	
	public NonRedundantList getParentHap() {
		return nonredundantParentHap;
	}
	
	public Map<String, Boolean> getParentHomozygous() {
		return parentHomozygous;
	}


}
