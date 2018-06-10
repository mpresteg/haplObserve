/**
 * create a list sorted by haplotype
 * List element1 => paternal allele
 * List element2 => maternal allele
 */
package workshop.haplotype.family.ngs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kazutoyo
 * @version April 27 2016
 *
 */
public class IdenticalHLACountCheck {
	private List<String> list;

	/**
	 * typeList1: paternal list
	 * typeList2: maternal list
	 */
	public IdenticalHLACountCheck(List<String> typeList1, List<String> typeList2) {
		// TODO Auto-generated constructor stub
		int count1 = typeList1.size();	// check how many elements are in each list
		int count2 = typeList2.size();
		list = new ArrayList<String>();
		
		if (count1 ==1) {	// one paternal match
			list.addAll(typeList1);
			switch(count2) {
			case 1: 		// one maternal match, this is normal case		
				list.addAll(typeList2);
				break;
			case 2:	// two maternal match, remove allele that is identical to paternal allele
				list.addAll(removeElement(typeList1, typeList2));
				break;
			default:
				list.add("P1GoodP2NoMatch");	// NoMatch signal was added here
				break;				
			}
		}
		else if (count1 == 2) {	// two paternal match
			switch(count2) {
			case 1:	// remove allele that is identical to maternal allele			
				list.addAll(removeElement(typeList2, typeList1));	
				list.addAll(typeList2);
				break;
			case 2:
				list.addAll(typeList1);	// typeList1 & typeList2 identical
				break;
			default:
				list.add("P1BothMatch");
				list.add("P1BothP2NoMatch");	// FAM-13
				break;				
			}			
		}
		else {	// no paternal HLA match, FAM-13
			list.add("P1NoMatch");
			switch(count2) {	// maternal cases
			case 1: 				// one match (most of the case)
				list.addAll(typeList2);
				break;
			case 2:	// no paternal type, but 2 HLA type match from maternal, FAM-13
				list.add("P1NoMatchP2Both");
				break;
			default:	// no maternal match
				list.add("P1P2NoMatch");	
				break;				
			}
		}
		
	}
	
	public List<String> removeElement(List<String> list1, List<String> list2) {		
		for (String str: list1) {
			list2.remove(str);
		}		
		return list2;
	}
	
	public List<String> getList() {
		return list;
	}


}
