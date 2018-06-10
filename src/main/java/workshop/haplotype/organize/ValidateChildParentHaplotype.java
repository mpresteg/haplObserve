/**
 * Each child haplotye should be found in parent haplotype
 * This class was added to validate child haplotype against parent haplotype
 * If this is invalid, then we have to fix HLA typing
 */
package workshop.haplotype.organize;

import java.util.HashMap;
import java.util.Map;

import workshop.haplotype.family.organize.OrganizeHaplotype;
import workshop.haplotype.organize.file.ChooseElement;
import workshop.haplotype.organize.file.ChooseElementHapType;

/**
 * @author kazu
 * @version April 25 2017
 *
 */
public class ValidateChildParentHaplotype {
	// Key1: child
	// Key2: haplotype
	// Value: boolean
	private Map<String, Map<String, Boolean>> childHapValid;
	private boolean validation;	// test whether invalid data exists or not

	/**
	 * 
	 */
	public ValidateChildParentHaplotype(OrganizeHaplotype oh) {
		// TODO Auto-generated constructor stub
		childHapValid = new HashMap<String, Map<String, Boolean>>();
		validation = true;
		
		for (String child : oh.getChildList()) {	// go through child list
			Map<String, Boolean> hapTest = new HashMap<String, Boolean>();
			
			for (String childHap : oh.getHaplotypeMapList().get(child).get(child)) {	// go through child Hap
				int matchCount = 0;	// count number of matches
				boolean test = true;	// default true
				for (String parent : oh.getParentList()) {	// go through parent list
					for (String parentHap : oh.getHaplotypeMapList().get(child).get(parent)) {	// go through parent Hap
						if (childHap.equals(parentHap)) {
							matchCount++;
						}
					}
				}
				if (matchCount == 0) {	// if no match found
					test = false;
					validation = false;
				}
				hapTest.put(childHap, test);
			}
			
			childHapValid.put(child, hapTest);
		}
	}
	
	public Map<String, Map<String, Boolean>> getChildHapValid() {
		return childHapValid;
	}
	
	public boolean getValidation() {
		return validation;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = 
//				"Family56_phase.csv";
				"Family101_test.csv";
//				"Family101.csv";
		ChooseElement ce = new ChooseElementHapType(file);
		OrganizeHaplotype oh = new OrganizeHaplotype(ce);
		ValidateChildParentHaplotype vch = new ValidateChildParentHaplotype(oh);
		
		System.out.println("Validation: " + vch.getValidation() + "\n");
		
		for (String child : oh.getChildList()) {	// go through child list
			for (String hap : vch.getChildHapValid().get(child).keySet()) {
				System.out.println(hap);
				System.out.println(vch.getChildHapValid().get(child).get(hap));
			}
		}

	}

}
