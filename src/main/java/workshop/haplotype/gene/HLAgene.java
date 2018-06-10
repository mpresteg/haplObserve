/**
 * This class list all HLA genes
 */
package workshop.haplotype.gene;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kazu
 * @version July 2 2015
 *
 */
public class HLAgene {
	protected List<String> geneList;
	final private String [] gene = { "HLA-A", "HLA-B", "HLA-C", "HLA-DPA1", "HLA-DPB1",
			"HLA-DQA1", "HLA-DQB1", "HLA-DRB1", "HLA-DRB3", "HLA-DRB4", "HLA-DRB5"	};
	
	public HLAgene() {
		geneList = new ArrayList<String>();
		for (int index = 0; index < gene.length; index++) {
			geneList.add(gene[index]);
		}
	}
	
	public List<String> getGeneList() {
		return geneList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HLAgene test = new HLAgene();
		for (String gene : test.getGeneList()) {
			System.out.println(gene);
		}

	}

}
