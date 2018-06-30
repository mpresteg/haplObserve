/**
 * This is to generate
 * 1. family haplotype for full HLA locus
 * 2. calculate haplotype frequency per ethnic group or country
 */
package workshop.haplotype.driver;

import workshop.haplotype.frequency.write.GenerateFullHaplotypeFrequencyTable;

/**
 * @author kazu
 *
 */
public class DriverForGenerateFullHaplotypeFrequencyTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GenerateFullHaplotypeFrequencyTable(args[0]);

	}

}
