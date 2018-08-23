/**
 * This class was added to break up codes
 */
package workshop.haplotype.ambiguity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kazu
 * @version September 25 2017
 *
 */
public abstract class AmbiguityListAbstract {
	protected List<List<String>> alleleList;
	protected List<String> ambiguityList;
	protected String converted;

	/**
	 * 
	 */
	public AmbiguityListAbstract() {
		// TODO Auto-generated constructor stub
		ambiguityList = new ArrayList<String>();
		alleleList = new ArrayList<List<String>>();
		converted = "";
		String [] dpb1_1301 = {"HLA-DPB1*13:01:01", "HLA-DPB1*107:01"};
		alleleList.add(addList(dpb1_1301));		
		String [] dpb1_0401 = {"HLA-DPB1*04:01:01:01", "HLA-DPB1*04:01:01:02"};
		alleleList.add(addList(dpb1_0401));		
//		String [] dpb1_020102 = {"HLA-DPB1*02:01:02"};	// removed "HLA-DPB1*02:01:19"
//		alleleList.add(addList(dpb1_020102));	
		
		String [] dqa1_0101 = {"HLA-DQA1*01:01:01:02", "HLA-DQA1*01:01:01:03"};	// need to add HLA-DQA1*01:01:01:01
		alleleList.add(addList(dqa1_0101));
		String [] dqa1_01020101 = {"HLA-DQA1*01:02:01:01", "HLA-DQA1*01:02:01:03", "HLA-DQA1*01:02:01:05"};
		alleleList.add(addList(dqa1_01020101));		
		String [] dqa1_01020104 = {"HLA-DQA1*01:02:01:04", "HLA-DQA1*01:02:01:06", "HLA-DQA1*01:02:01:07"};
		alleleList.add(addList(dqa1_01020104));		
		String [] dqa1_010301 = {"HLA-DQA1*01:03:01:03", "HLA-DQA1*01:03:01:04"};
		alleleList.add(addList(dqa1_010301));		
		String [] dqa1_01030102 = {"HLA-DQA1*01:03:01:02", "HLA-DQA1*01:03:01:06"};	// added later
		alleleList.add(addList(dqa1_01030102));
		String [] dqa1_0201 = {"HLA-DQA1*02:01:01:01", "HLA-DQA1*02:01:01:02"};	// added April 27 2017
		alleleList.add(addList(dqa1_0201));		
		String [] dqa1_0104 = {"HLA-DQA1*01:04:01:01", "HLA-DQA1*01:04:01:02", "HLA-DQA1*01:04:01:04"}; // added HLA-DQA1*01:04:01:04
		alleleList.add(addList(dqa1_0104));		
		String [] dqa1_05050101 = {"HLA-DQA1*05:05:01:01", "HLA-DQA1*05:05:01:02", "HLA-DQA1*05:05:01:04"}; // added HLA-DQA1*05:05:01:04
		alleleList.add(addList(dqa1_05050101));	// added on May 4 2017
		String [] dqa1_05050105 = {"HLA-DQA1*05:05:01:03", "HLA-DQA1*05:05:01:05", "HLA-DQA1*05:05:01:06"};	// added HLA-DQA1*05:05:01:03
		alleleList.add(addList(dqa1_05050105));
		
		String [] dqb1_0303 = {"HLA-DQB1*03:03:02:02", "HLA-DQB1*03:03:02:03"};
		alleleList.add(addList(dqb1_0303));		
		String [] dqb1_0503 = {"HLA-DQB1*05:03:01:01", "HLA-DQB1*05:03:01:02"};
		alleleList.add(addList(dqb1_0503));
		
		String [] drb1_0301 = {"HLA-DRB1*03:01:01:01", "HLA-DRB1*03:01:01:02"};
		alleleList.add(addList(drb1_0301));
		String [] drb1_0701 = {"HLA-DRB1*07:01:01:01", "HLA-DRB1*07:01:01:02"};
		alleleList.add(addList(drb1_0701));
		String [] drb1_1301 = {"HLA-DRB1*13:01:01:01", "HLA-DRB1*13:01:01:02"};
		alleleList.add(addList(drb1_1301));
		String [] drb1_1501 = {"HLA-DRB1*15:01:01:01", "HLA-DRB1*15:01:01:02", "HLA-DRB1*15:01:01:03"};
		alleleList.add(addList(drb1_1501));
		String [] drb4_0103 = {"HLA-DRB4*01:03:01:01", "HLA-DRB4*01:03:01:03"};
		alleleList.add(addList(drb4_0103));
		String [] drb3_010102 = {"HLA-DRB3*01:01:02:01", "HLA-DRB3*01:01:02:01e1",
				"HLA-DRB3*01:01:02:01v3", "HLA-DRB3*01:01:02:01v4"};
		alleleList.add(addList(drb3_010102));
	}
	
	public List<String> addList(String [] typeList) {
		List<String> list = new ArrayList<String>();
		for (String type : typeList) {
			list.add(type);			
		}
		return list;
	}
	
	public List<String> getAmbiguityList() {
		return ambiguityList;
	}
	
	public String getConverted() {
		return converted;
	}
	
	public List<List<String>> getAlleleList() {
		return alleleList;
	}
	
	public abstract List<String> convertToAmbiguityList(List<List<String>> ambiguityList);
	
	public abstract void convertToAmbiguityString(String type);

}
