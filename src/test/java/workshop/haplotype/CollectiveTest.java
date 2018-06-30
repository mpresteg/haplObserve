/**
 * 
 */
package workshop.haplotype;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import workshop.haplotype.collective.CombineGLFile;
import workshop.haplotype.collective.ExtractFamily;
import workshop.haplotype.collective.FamSamRelationCountry;
import workshop.haplotype.collective.FamSamRelationGenotype;
import workshop.haplotype.organize.NonRedundantList;

/**
 * @author kazu
 * @version June 13 2018
 *
 */
public class CollectiveTest extends TestCase {

	@Test
	public void testCollective() {
		CombineGLFile combined = new CombineGLFile();
//		CombineGLFile combined = new CombineGLFile("src/test/resources/");
		System.out.println("CombineGLFile");
		for (String str : combined.getCombined()) {
			System.out.println(str);
		}		
		System.out.println(combined.getHeader());
		System.out.println();
		
		ExtractFamily ef = new ExtractFamily(combined);
		System.out.println("ExtractFamily");
		for (String family : ef.getFamilyList().getList()) {
			System.out.println(family);
		}
		System.out.println();
		
		
		FamSamRelationGenotype fsrg = new FamSamRelationGenotype(combined);
		System.out.println("FamSamRelationGenotype");
		for (List<String> line : fsrg.getFamSamRelationGenotype()) {
			System.out.println(line);
		}
		System.out.println();
		
		FamSamRelationCountry fsrc = new FamSamRelationCountry(combined);
		System.out.println("FamSamRelationCountry");
		for (List<String> line : fsrc.getFamSamCountry()) {
			System.out.println(line);
		}
		
		for (String country : fsrc.getCountryList().getList()) {
			System.out.println(country);
		}
		System.out.println();
	}
	
	@Test
	public void testNonRedundantList() {
		System.out.println("NonRedundantList");
		NonRedundantList non2 = new NonRedundantList();
		String [] list2 = {"", "DRB1", "DRB1", "DRB3", "DRB4", "DRB4", "DRB5"};
		for (String str : list2) {
			non2.addNonRedundantList(str);
		}
		for (String str : non2.getList()) {
			System.out.println(str);
		}
		System.out.println();
	}

}
