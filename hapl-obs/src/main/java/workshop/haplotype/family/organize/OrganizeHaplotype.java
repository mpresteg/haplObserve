/**
 * 
 * 
 */
package workshop.haplotype.family.organize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import workshop.haplotype.gene.OrderedHLAgene;
import workshop.haplotype.organize.CreateHaplotypeGLstring;
import workshop.haplotype.organize.file.ChooseElement;

/**
 * @author kazutoyo
 * @version April 2 2018
 *
 */
public class OrganizeHaplotype extends OrganizeFamilyTrioHapType {
	// child outer Map key
	// sample inner Map key
	// List haplotypes
	protected Map<String, Map<String, List<String>>> haplotypeMapList;
	protected Map<String, Map<String, List<String>>> singleAlleleHaplotypeMapList;	// added
	protected Map<String, Map<String, List<String>>> twoFieldHaplotypeMapList;	// April 2 2018

	/**
	 * @param ce
	 */
	public OrganizeHaplotype(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		haplotypeMapList = new HashMap<String, Map<String, List<String>>>();
		singleAlleleHaplotypeMapList = new HashMap<String, Map<String, List<String>>>();
		twoFieldHaplotypeMapList = new HashMap<String, Map<String, List<String>>>();
		OrderedHLAgene orderedGene = new OrderedHLAgene();		
		
		for (String child : this.getChildList()) {
			Map<String, List<String>> tmpMap = new HashMap<String, List<String>>();
			Map<String, List<String>> singleMap = new HashMap<String, List<String>>();
			Map<String, List<String>> twoFieldMap = new HashMap<String, List<String>>();
			
			List<String> tmpList1 = new ArrayList<String>();
			List<String> tmpList3 = new ArrayList<String>();
			List<String> tmpList5 = new ArrayList<String>();
			for (int index = 0; index < 2; index ++) {	// problem for DRB345
				CreateHaplotypeGLstring ch = new CreateHaplotypeGLstring(this, orderedGene, child, child, index);
				tmpList1.add(ch.getHapList().get(0));
				if (ch.getHapList().get(1).length() > 0) {
					tmpList3.add(ch.getHapList().get(1));
					tmpList5.add(ch.getHapList().get(2));
				}
								
			}
			tmpMap.put(child, tmpList1);
			if (!tmpList3.isEmpty()) {
				singleMap.put(child, tmpList3);	
			}
			if (!tmpList5.isEmpty()) {
				twoFieldMap.put(child, tmpList5);
			}
						
			for (String parent : this.getParentList()) {
				List<String> tmpList2 = new ArrayList<String>();
				List<String> tmpList4 = new ArrayList<String>();
				List<String> tmpList6 = new ArrayList<String>();
				
				for (int index = 0; index < 2; index ++) {
					CreateHaplotypeGLstring ch = new CreateHaplotypeGLstring(this, orderedGene, child, parent, index);
					tmpList2.add(ch.getHapList().get(0));
					if (ch.getHapList().get(1).length() > 0) {
						tmpList4.add(ch.getHapList().get(1));
						tmpList6.add(ch.getHapList().get(2));
					}
			
				}
				tmpMap.put(parent, tmpList2);
				if (!tmpList4.isEmpty()) {
					singleMap.put(parent, tmpList4);
				}
				if (!tmpList6.isEmpty()) {
					twoFieldMap.put(parent, tmpList6);
				}
				
			}
			haplotypeMapList.put(child, tmpMap);
			if (!singleMap.isEmpty()) {
				singleAlleleHaplotypeMapList.put(child, singleMap);
			}	
			if (!twoFieldMap.isEmpty()) {
				twoFieldHaplotypeMapList.put(child, twoFieldMap);
			}
		}
	}
	
	public Map<String, Map<String, List<String>>> getHaplotypeMapList() {
		return haplotypeMapList;
	}
	
	public Map<String, Map<String, List<String>>> getSingleAlleleHaplotypeMapList() {
		return singleAlleleHaplotypeMapList;
	}
	
	public Map<String, Map<String, List<String>>> getTwoFieldHaplotypeMapList() {
		return twoFieldHaplotypeMapList;
	}

}
