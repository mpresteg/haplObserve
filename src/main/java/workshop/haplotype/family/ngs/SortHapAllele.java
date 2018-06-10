/**
 * Sort allele1 & allele2 corresponding to child allele
 */
package workshop.haplotype.family.ngs;

import java.util.LinkedList;
import java.util.List;

import workshop.haplotype.family.organize.OrganizeBySample;
import workshop.haplotype.organize.NonRedundantList;

/**
 * @author Kazutoyo Osoegawa
 * @version September 29 2017
 *
 */
public class SortHapAllele {
	private String firstAllele;
	private String secondAllele;
	private NonRedundantList  firstAlleleList;

	/**
	 * 
	 */
	public SortHapAllele(OrganizeBySample os, String gene, String parent, String childType) {
		// TODO Auto-generated constructor stub
		firstAllele = "";
		firstAlleleList = new NonRedundantList();
		secondAllele = "";
		firstAllele = extractFirst(os, gene, parent, childType);
		secondAllele = extractSecondAllele(os, gene, parent,firstAllele);		
	}
	
	public String getFirstAllele() {
		return firstAllele;
	}
	
	public NonRedundantList getFirstAlleleList() {
		return firstAlleleList;
	}
	
	public String getSceondAllele() {
		return secondAllele;
	}
	
	public String extractFirst(OrganizeBySample os, String gene, String parent, String childType) {
		String type = "";		// return type
		if (os.getHlaTypeBySample().get(parent).containsKey(gene)) {
			for (String parentType : os.getHlaTypeBySample().get(parent).get(gene)) {	// go through parent type
				if (parentType.contains(childType)) {	// parent type contains child type
					type = parentType;	// there is only one childType
					firstAlleleList.addNonRedundantList(parentType);
				}			
				else if (childType.contains(parentType)) {
					type = childType;
					firstAlleleList.addNonRedundantList(childType);
				}
			}
		}	
		return type;
	}

	
	public String extractSecondAllele(OrganizeBySample os, String gene, String parent, String firstAllele) {
		String secondAllele = "";
		if (os.getHlaTypeBySample().get(parent).containsKey(gene)) {
			List<String> linkedList = new LinkedList<String>();
			for (String parentType : os.getHlaTypeBySample().get(parent).get(gene)) {
				linkedList.add(parentType);
			}
			
			linkedList.remove(firstAllele);
			for (String str : linkedList) {
				secondAllele += str;
			}
			
		}	
		if (secondAllele.length() == 0) {
			secondAllele = "Allele2Mismatch";
		}
		return secondAllele;
	}	

}
