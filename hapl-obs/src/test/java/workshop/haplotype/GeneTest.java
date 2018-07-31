package workshop.haplotype;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import workshop.haplotype.frequency.ranking.HapTarget;
import workshop.haplotype.gene.DRBgenes;
import workshop.haplotype.gene.ExpectedDRB345Count;
import workshop.haplotype.gene.HLAgene;
import workshop.haplotype.gene.IsLocusDRB345;

/**
 * @author kazu
 * @version June 19 2018
 *
 */
public class GeneTest extends TestCase {

	@Test
	public void testDRBgenes() {
		System.out.println("DRB1genes");
		DRBgenes drb = new DRBgenes();
		for (String drb345 : drb.getDrb0345List()) {
			System.out.println(drb345);
			for (String drb1 : drb.getDrb1MapList().get(drb345)) {
				System.out.println(drb1);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	@Test
	public void testExpectedDRB345Count() {
		System.out.println("ExpectedDRB345Count");
		List<String> list = new ArrayList<String>();
		list.add("HLA-DRB1*07:01:01:01");
		list.add("HLA-DRB1*04:04:01");
		ExpectedDRB345Count exp = new ExpectedDRB345Count(list);
		System.out.println(exp.getDrb345Count());
		for (String drb1Type : list) {
			System.out.println(drb1Type);
			System.out.println(exp.getExpectedCombination().get(drb1Type));
			System.out.println();
		}
		System.out.println();
		
		List<String> list1 = new ArrayList<String>();	
		list1.add("HLA-DRB1*04:04:01");
		list1.add("HLA-DRB1*08:02:01");
		ExpectedDRB345Count exp1 = new ExpectedDRB345Count(list1);
		System.out.println(exp1.getDrb345Count());
		for (String drb1Type : list1) {
			System.out.println(drb1Type);
			System.out.println(exp1.getExpectedCombination().get(drb1Type));
			System.out.println();
		}
		System.out.println();
	}
	
	
	@Test
	public void testHLAgene() {
		System.out.println("HLAgene");
		HLAgene test = new HLAgene();
		for (String gene : test.getGeneList()) {
			System.out.println(gene);
		}
		System.out.println();
	}
	
	@Test
	public void testIsLocusDRB345() {
		System.out.println("IsLocusDRB345");
		IsLocusDRB345 test1 = new IsLocusDRB345("HLA-A");
		System.out.println(test1.getTestResult());
		IsLocusDRB345 test2 = new IsLocusDRB345("HLA-DRB1");
		System.out.println(test2.getTestResult());
		IsLocusDRB345 test3 = new IsLocusDRB345("HLA-DRB4");
		System.out.println(test3.getTestResult());
		System.out.println();
	}
	
	@Test
	public void testHapTarget() {
		System.out.println("HapTarget");
		HapTarget ht = new HapTarget();
		for (String target : ht.getHapTargetList()) {
			System.out.println(target);
		}
		
		for (String name : ht.getNameList()) {
			System.out.println(name);
		}
		System.out.println();
	}

}
