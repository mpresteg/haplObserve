/**
 * This was developed to convert phase ambiguity format to allele ambiguity format
 * HLA-DPB1*04:01:01:01+HLA-DPB1*13:01:01|HLA-DPB1*04:01:01:01+HLA-DPB1*107:01|HLA-DPB1*133:01+HLA-DPB1*350:01
 * HLA-DPB1*04:01:01:01+HLA-DPB1*13:01:01/HLA-DPB1*107:01|HLA-DPB1*133:01+HLA-DPB1*350:01
 */
package workshop.haplotype.family.ngs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import workshop.haplotype.ambiguity.CompressAmbiguity;

/**
 * @author kazu
 * @version February 3 2017
 *
 */
public class NormalizePhaseAmbiguity extends NormalizePhaseAmbiguityAbstract {

	/**
	 * @param type
	 */
	public NormalizePhaseAmbiguity(String type) {
		super(type);
		// TODO Auto-generated constructor stub
		
		if ((!resolved) && (!homozygous)) {
			List<String> tmpList = new LinkedList<String>();
			tmpList.addAll(nonGllist.getList());	// add all glstring
			
			int index = 1;		// keep track of the size of tmpList
			int testCount = 0;	// keep track of the size of compressed
			List<String> compressed = new ArrayList<String>();
			while (index != testCount) {	
				compressed.clear();
				index = tmpList.size();			// check size
//				System.out.println(index);
				compressed.addAll(goThroughAmb(tmpList));
				testCount = compressed.size();
				tmpList.clear();
				tmpList.addAll(compressed);			
			}		
			
			Collections.sort(compressed);			
			int count = 0;
			type = "";
			for (String gl : compressed) {
				if (count != 0) {
					glString += "|";
				}
				glString += gl;	// notation removed glString
				count++;
			}

		}
		if (!glString.contains("|")) {	// no phase ambiguity
			resolved = true;
		}		
	}
	
	public List<String> goThroughAmb(List<String> tmpList) {
		List<String> compressed = new ArrayList<String>();
		boolean nomore = false;
		if (tmpList.size() < 2) {	// empty or 1
			nomore = true;
			compressed.addAll(tmpList);
		}
		while (!nomore) {	// 2 or more
			String str1 = tmpList.remove(0);		// remove first element			
			int removedSize = tmpList.size();		// check the size of tmpList after removing first element
			
			if (!tmpList.isEmpty()) {
				boolean test = false;
				for (int count = 0; count < removedSize; count++) {
					// convert phase ambiguity to allele ambiguity
					CompressAmbiguity ca = new CompressAmbiguity(str1, tmpList.get(count));				
					if (ca.getResolved()) {		// successfully converted to allele ambiguity format
						compressed.add(ca.getGlString());
//						System.out.println(ca.getGlString());
						tmpList.remove(count);
						test = true;
						break;
					}
				}				
				if (!test) {
					compressed.add(str1);	// nothing found, leave it as it is, put it back
				}			
			}
			
			if (tmpList.size() == 0) {
				nomore = true;
			}
			else if (tmpList.size() == 1) {
				compressed.addAll(tmpList);
				nomore = true;
			}
		}		
		return compressed;
	}

}
