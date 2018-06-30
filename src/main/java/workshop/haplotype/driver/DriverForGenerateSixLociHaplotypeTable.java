/**
 * This is to generate
 * 1. family haplotype for six HLA loci
 * 2. calculate haplotype frequency per ethnic group or country
 */
package workshop.haplotype.driver;

import workshop.haplotype.frequency.write.GenerateSixLociHaplotypeTable;

/**
 * @author kazu
 * @version June 22 2018
 *
 */
public class DriverForGenerateSixLociHaplotypeTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GenerateSixLociHaplotypeTable(args[0]);

	}

}
