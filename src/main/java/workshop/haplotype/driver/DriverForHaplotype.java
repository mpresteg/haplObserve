/**
 * This driver is to run HaplObser for single family
 * Input file is table format 
 */
package workshop.haplotype.driver;

import workshop.haplotype.write.GenerateHaplotypeLog;
import workshop.haplotype.write.GenerateSampleHaplotype;
import workshop.haplotype.write.GenerateSampleSingleAlleleHaplotype;
import workshop.haplotype.write.GenerateSampleTwoFieldHaplotype;

/**
 * @author kazu
 * @version October 2 2017
 *
 */
public class DriverForHaplotype {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GenerateSampleHaplotype(args[0], args[1]);
		new GenerateSampleSingleAlleleHaplotype (args[0], args[2]);
		new GenerateHaplotypeLog(args[0], args[3]);
		new GenerateSampleTwoFieldHaplotype(args[0], args[4]);

	}

}
