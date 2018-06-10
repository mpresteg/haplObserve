/**
 * HLA genes are organized along chromosome position
 */
package workshop.haplotype.gene;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kazu
 * @version May 10 2016
 *
 */
public class OrderedHLAgene {
	private List<String> orderedGeneList;
//	final private String [] orderedGene = { "HLA-DPB1", "HLA-DPA1", "HLA-DQB1", 
//			"HLA-DQA1", "HLA-DRB1", "HLA-DRB345", "HLA-B", "HLA-C", "HLA-A"};
	final private String [] orderedGene = {"HLA-A", "HLA-C", "HLA-B", "HLA-DRB345", "HLA-DRB1", 
			"HLA-DQA1", "HLA-DQB1", "HLA-DPA1", "HLA-DPB1" };

	/**
	 * 
	 */
	public OrderedHLAgene() {
		// TODO Auto-generated constructor stub
		orderedGeneList = new ArrayList<String>();
		for (int index = 0; index < orderedGene.length; index++) {
			orderedGeneList.add(orderedGene[index]);
		}
		
	}
	
	public List<String> getOrderedGeneList() {
		return orderedGeneList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrderedHLAgene test = new OrderedHLAgene();
		
		for (String gene : test.getOrderedGeneList()) {
			System.out.println(gene);
		}

	}

}
