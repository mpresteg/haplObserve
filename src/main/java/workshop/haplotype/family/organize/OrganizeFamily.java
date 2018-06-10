/**
 * Capture elements
 */
package workshop.haplotype.family.organize;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import workshop.haplotype.gene.IsLocusDRB345;
import workshop.haplotype.organize.file.ChooseElement;
import workshop.haplotype.sirona.RemoveNotation;

/**
 * @author Kazutoyo Osoegawa
 * @version May 31 2018
 *
 */
public class OrganizeFamily extends OrganizeBySample {

	/**
	 * @param ce
	 */
	public OrganizeFamily(ChooseElement ce) {
		super(ce);
		// TODO Auto-generated constructor stub
		Set<String> sampleSet = new HashSet<String>();	// use set
		for (List<String> tmpList : ce.getChosenElement()) {
			List<String> linkedList = new LinkedList<String>();
			for (String str : tmpList) {
				linkedList.add(str);
			}
			String sampleID = linkedList.remove(0);	// extract sample ID, first column
			if (!sampleSet.contains(sampleID)) {	// remove redundancy
				sampleList.add(sampleID);
				sampleSet.add(sampleID);
			}	
			String relation = linkedList.remove(0);	// extract relationship, second column
			sampleRelation.put(sampleID, relation);		
			if (relation.equals("child")) {
				childList.add(sampleID);	// populate childList	
			}	// parentList later
			
			Map<String, String> map = new HashMap<String, String>();	
			for (String gene : hlaGene.getGeneList()) {	// go through hla gene
				IsLocusDRB345 drb345 = new IsLocusDRB345(gene);	// check DRB345
				
				// ADD NT HERE for dropout
				int index = 0;
				for (String type : linkedList) {	
					RemoveNotation rn1 = new RemoveNotation(type);	// optional, notation removed
					String convertedType = rn1.getFixed();
					if (!rn1.getFixed().contains(gene)) {
						continue;
					}
					else {	// type contains gene
						index++;
						// fix homozygous allele issue => reported only once
						if ((!rn1.getFixed().contains("+")) && (!drb345.getTestResult())) {
							String tmpType = "+" + convertedType;
							convertedType += tmpType;
						}
						map.put(gene, convertedType);															
					}

				}
				if ((!drb345.getTestResult()) && (index == 0)) {	// empty
					map.put(gene, gene + "*NT+" + gene + "*NT");
				}
			}	// end of gene
			if (!map.isEmpty()) {
				sampleGeneOrgGLType.put(sampleID, map);
			}			
		}
		// order father, mother
		String father = "";
		String mother = "";
		for (String sample : sampleRelation.keySet()) {
			if (sampleRelation.get(sample).equals("father")) {
				father = sample;
			}
			else if (sampleRelation.get(sample).equals("mother")) {
				mother = sample;
			}
		}
		parentList.add(father);	// populate parentList
		parentList.add(mother);	// populate parentList
	}

}
