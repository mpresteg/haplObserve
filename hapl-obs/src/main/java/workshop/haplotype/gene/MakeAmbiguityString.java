/**
 * To make ambiguity string from list
 */
package workshop.haplotype.gene;

import java.util.List;

/**
 * @author Kazutoyo Osoegawa
 * @version May 18 2016
 *
 */
public class MakeAmbiguityString {
	private String ambiguity;

	/**
	 * 
	 */
	public MakeAmbiguityString(List<String> list) {
		// TODO Auto-generated constructor stub
		ambiguity = "";

		int count = 0;
		for (String str : list) {
			if (count > 0) {
				ambiguity += "/";		// change to ambiguity string
			}
			ambiguity += str; 
			count++;
		}
	}
	
	public String getAmbiguity() {
		return ambiguity;
	}

}
