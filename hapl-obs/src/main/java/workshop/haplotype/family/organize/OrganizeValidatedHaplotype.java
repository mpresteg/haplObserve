/**
 * 
 */
package workshop.haplotype.family.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.organize.ValidateChildParentHaplotype;
import workshop.haplotype.organize.ValidateHaploType;
import workshop.haplotype.organize.file.ChooseElement;

/**
 * @author kazutoyo
 * @version April 26 2017
 *
 */
public class OrganizeValidatedHaplotype extends OrganizeHaplotype {
	private Map<String, Boolean> parentValidation;
	private boolean childValidation;
	private boolean ambiguity;
	private Map<String, Map<String, Boolean>> childHapValid;
	private List<String> haplotype;
	private boolean hapValidation;	// test whether invalid data exists or not


	/**
	 * @param ce
	 */
	public OrganizeValidatedHaplotype(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		childValidation = false;
		ambiguity = false;
		parentValidation = new HashMap<String, Boolean>();
		childHapValid = new HashMap<String, Map<String, Boolean>>();
		haplotype = new ArrayList<String>();
		hapValidation = false;

	
		ValidateHaploType validation = new ValidateHaploType(this);		

		haplotype.addAll(validation.getParentHap().getList());
		
		parentValidation.putAll(validation.getParentValidation());		
		childValidation = validation.getChildValidation();		
		
		ValidateChildParentHaplotype vch = new ValidateChildParentHaplotype(this);
		childHapValid.putAll(vch.getChildHapValid());
		hapValidation = vch.getValidation();		// Are haplotypes complete?

	}
	
	public Map<String, Map<String, Boolean>> getChildHapValid() {
		return childHapValid;
	}
	
	public boolean getChildValidation() {
		return childValidation;
	}	
	
	public boolean getAmbigityTestResult() {
		return ambiguity;
	}
	
	public Map<String, Boolean> getParentValidation() {
		return parentValidation;
	}
	
	public List<String> getHaplotypeList() {
		return haplotype;
	}
	
	public boolean getHapValidation() {
		return hapValidation;
	}

}
