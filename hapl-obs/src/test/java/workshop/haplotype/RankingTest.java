/**
 * need to run HaplObserveExtendedTest to generate FAM_Haplotype_Summary_GL_String file
 * before testing
 */
package workshop.haplotype;

import org.junit.Test;

import workshop.haplotype.frequency.ms.SampleHap;
import workshop.haplotype.frequency.ranking.OrderbyRanking;
import workshop.haplotype.frequency.ranking.SampleHapCount;

/**
 * @author kazu
 * @version June 21 2018
 *
 */
public class RankingTest {
	private static final String collective = "src/test/resources/";
	private static final String target = "HLA-C,HLA-B";
	private static String input = collective + "FAM_Haplotype_Summary_GL_String_TEST.csv";
	

	@Test
	public void testSampleHap() {
		
		System.out.println("SampleHap");
		SampleHap sh = new SampleHap(input, target);
		for (int index = 0; index < sh.getHaplotypes().getList().size(); index++) {
			System.out.println(index + ": " + sh.getHaplotypes().getList().get(index));
		}
		System.out.println();
	}
	
	@Test
	public void testSampleHapCount() {
		System.out.println("SampleHapCount");
		SampleHapCount shc = new SampleHapCount(input, target);
		int index = 0;
		for (String hap : shc.getHapCountRank().keySet()) {
			System.out.println(index + ": " + hap + " => " + shc.getHapCountRank().get(hap).get(0) + "\t" +
					shc.getHapCountRank().get(hap).get(1));
			index++;
		}
		System.out.println();
		
		index = 0;
		System.out.println("OrderbyRanking");
		OrderbyRanking or = new OrderbyRanking(shc);
		for (String hap : or.getRankedHapList()) {
			System.out.println(index + ": " + hap + " => "  + shc.getHapCountRank().get(hap).get(0) + "\t" 
					+ shc.getHapCountRank().get(hap).get(1));
			index++;
		}
		System.out.println();

			
	}

}
