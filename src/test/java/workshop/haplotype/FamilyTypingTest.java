/**
 * 
 */
package workshop.haplotype;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import workshop.haplotype.family.ngs.NormalizePhaseAmbiguity;
import workshop.haplotype.family.ngs.PhaseAmbiguityResolver;
import workshop.haplotype.family.organize.OrganizeChildHapTypeDRBlinkage;
import workshop.haplotype.family.organize.OrganizeChildHapTypeValidated;
import workshop.haplotype.family.organize.OrganizeFamilyBySamplePhaseResolved;
import workshop.haplotype.family.organize.OrganizeFamilyBySampleUnresolvedPhaseAmbiguity;
import workshop.haplotype.family.organize.OrganizeFamilyTrioHapType;
import workshop.haplotype.family.organize.OrganizeParentHapType;
import workshop.haplotype.family.organize.OrganizeParentHapTypeDRBlinkage;
import workshop.haplotype.organize.ChooseChildPairWiseList;
import workshop.haplotype.organize.CreatePairWiseCombination;
import workshop.haplotype.organize.file.ChooseElement;
import workshop.haplotype.organize.file.ChooseElementHapType;

/**
 * @author kazu
 * @version October 13 2017
 *
 */
public class FamilyTypingTest extends TestCase {

	public static final String SUPPLEMENTAL_TEST_FILE = "SupplementalTable1.csv";
	public static final ChooseElement ce;
	
	static {
		ce = new ChooseElementHapType(SUPPLEMENTAL_TEST_FILE);
	}

	@Test
	public void testChoosePairWiseList() {
		OrganizeChildHapTypeDRBlinkage of = new OrganizeChildHapTypeDRBlinkage(ce);
		String locus1 = 
//				"HLA-DPA1";
				"HLA-B";
		
		String locus2 = 
//				"HLA-DPB1";
				"HLA-C";
		
		ChooseChildPairWiseList cpwt = new ChooseChildPairWiseList(locus1, locus2, of);
		for (String child : of.getChildList())	{
			System.out.println(child);
			for (String parent : cpwt.getChildParentPairWise().get(child).keySet()) {
				System.out.println(parent);
				for (List<String> clist : cpwt.getChildParentPairWise().get(child).get(parent).keySet()) {
					System.out.println("Child: " + child + ": " + clist);
					for (List<String> plist : cpwt.getChildParentPairWise().get(child).get(parent).get(clist)) {
						System.out.println("Parent: " + parent + ": " + plist);
					}
				}
			}
			System.out.println();
		}
		System.out.println();
			
		for (String parent : cpwt.getParentPairWiseList().keySet()) {
			System.out.println(parent);
			for (List<String> list : cpwt.getParentPairWiseList().get(parent)) {
				System.out.println(list);
			}
		}
		
		for (String child : cpwt.getChildPairWiseList().keySet()) {
			System.out.println(child);
			for (List<String> list : cpwt.getChildPairWiseList().get(child)) {
				System.out.println(list);
			}
		}
		
	}
	
	@Test
	public void testCreatePairWiseCombination(String[] args) {		
		OrganizeFamilyBySamplePhaseResolved of = new OrganizeFamilyBySamplePhaseResolved(ce);
		String locus1 = 
//				"HLA-DPA1";
//				"HLA-DRB1";
				"HLA-B";
		String locus2 = 
//				"HLA-DPB1";
//				"HLA-DQB1";
				"HLA-C";
		
		for (String sample : of.getSampleList()) {
			System.out.println(sample + ": " + of.getSampleRelation().get(sample));
			
			CreatePairWiseCombination pw = 
					new CreatePairWiseCombination(locus1, locus2, of.getHlaTypeBySample().get(sample));
			
			for (int index = 0 ; index < pw.getPairWiseCombination().size(); index++) {
				int counter = 0;
				for (List<String> list : pw.getPairWiseCombination().get(index)) {
					int count = 0;
					for (String allele : list) {
						if (count != 0) {
							System.out.print("~");
						}
						System.out.print(allele);
						count++;
					}
					
					if (counter == 0) {
						System.out.print("+");
					}
					counter++;
				}
				System.out.println();
			}
		}

	}
	
	@Test
	public void testNormalizePhaseAmbiguity() {
		String str = 
		"HLA-DRB1*03:01:01:01+HLA-DRB1*12:01:01:01|HLA-DRB1*03:01:01:01+HLA-DRB1*12:01:01:03|"
		+ "HLA-DRB1*03:01:01:01+HLA-DRB1*12:10|HLA-DRB1*03:01:01:02+HLA-DRB1*12:01:01:01|"
		+ "HLA-DRB1*03:01:01:02+HLA-DRB1*12:01:01:03|HLA-DRB1*03:01:01:01+HLA-DRB1*12:10";
		NormalizePhaseAmbiguity nmp = new NormalizePhaseAmbiguity(str);
		System.out.println(nmp.getGlString());
		System.out.println(nmp.getResolved());

	}
	
	@Test
	public void testOrganizeChildHapType() {			
		OrganizeChildHapTypeValidated ochv = new OrganizeChildHapTypeValidated(ce);
		for (String child : ochv.getChildList()) {
			System.out.println("Child: " + child);
			for (String gene : ochv.getGeneListDRB345Combined().getList()) {
				if (ochv.getValidatedMap().get(child).containsKey(gene)) {
					System.out.println(gene);					
					if (ochv.getChildHapTypeDRLinkageMap().get(child).containsKey(gene)) {
						for (String type : ochv.getChildHapTypeDRLinkageMap().get(child).get(gene)) {
							System.out.println(type);
						}
					}					
					System.out.println(ochv.getValidatedMap().get(child).get(gene));
				}				
			}
			System.out.println();
		}
	}
	
	@Test
	public void testOrganizeFamilyTrioHapType() {
		OrganizeFamilyTrioHapType ofth = new OrganizeFamilyTrioHapType(ce);
		
		for (String child : ofth.getChildList()) {
			System.out.println("Child: " + child);
			for (String gene : ofth.getGeneListDRB345Combined().getList()) {
				if (ofth.getFamilyTrioHapType().get(child).get(child).containsKey(gene)) {
					int count = 0;
					for (String type : ofth.getFamilyTrioHapType().get(child).get(child).get(gene)) {
						if (type.length() == 0) {
							System.out.println("EMPTY");
						}
						else {
							System.out.println(type);
						}
						
						System.out.println(count);
						count++;
					}	
				}
			}
			System.out.println();
			
			for (String parent : ofth.getParentList()) {
				System.out.println("Parent: " + parent);
				for (String gene : ofth.getGeneListDRB345Combined().getList()) {
					if (ofth.getFamilyTrioHapType().get(child).get(parent).containsKey(gene)) {
						int count = 0;
						for (String type : ofth.getFamilyTrioHapType().get(child).get(parent).get(gene)) {
							if (type.length() == 0) {
								System.out.println("EMPTY");
							}
							else {
								System.out.println(type);
							}
							
							System.out.println(count);
							count++;
						}	
						
					}
				}
				System.out.println();
			}				
		}
	}
	
	@Test
	public void testOrganizeParentHapType() {			
		OrganizeParentHapTypeDRBlinkage opl = new OrganizeParentHapTypeDRBlinkage(ce);
		System.out.println("\nLinkage");
		for (String child : opl.getChildList()) {
			System.out.println("Child: " + child);
			for (String parent : opl.getParentList()) {
				System.out.println("Parent: " + parent);
				for (String gene : opl.getGeneListDRB345Combined().getList()) {
					if (opl.getParentHapTypeMapDRLinkageMap().get(child).get(parent).containsKey(gene)) {
						for (String type : opl.getParentHapTypeMapDRLinkageMap().get(child).get(parent).get(gene)) {
							System.out.println(type);
						}						
					}
				}
				System.out.println();
			}
			System.out.println();
		}
		
		OrganizeParentHapType op = new OrganizeParentHapType(ce);
		System.out.println("Haplotype");
		for (String child : op.getChildList()) {
			System.out.println("Child: " + child);
			for (String parent : op.getParentList()) {
				System.out.println("Parent: " + parent);
				for (String gene : op.getGeneListDRB345Combined().getList()) {
					if (op.getParentHapTypeMap().get(child).get(parent).containsKey(gene)) {
						for (String type : op.getParentHapTypeMap().get(child).get(parent).get(gene)) {
							System.out.println(type);
						}						
					}
				}
				System.out.println();
			}
			System.out.println();
		}

	}
	
	public void testResolvePhaseAmbiguity() {				
		OrganizeFamilyBySampleUnresolvedPhaseAmbiguity ofbsupa = new OrganizeFamilyBySampleUnresolvedPhaseAmbiguity(ce);
						
		for (String gene : ofbsupa.getPahseAmbiguityGeneList().getList()) {
			PhaseAmbiguityResolver rpat = new PhaseAmbiguityResolver(ofbsupa.getChildList(), 
					ofbsupa.getParentList(), ofbsupa.getSampleGeneListList(), gene);
			
			for (String child : ofbsupa.getChildList()) {
				System.out.println(child);
				System.out.println(gene);
				for (List<String> cList : rpat.getChildParentList().get(child).keySet()) {
					System.out.println(cList);
					for (String parent : rpat.getChildParentList().get(child).get(cList).keySet()) {
						System.out.println(parent);
						System.out.println(rpat.getChildParentList().get(child).get(cList).get(parent));
					}
				}
				System.out.println();
			}
		}
		
	}
}
