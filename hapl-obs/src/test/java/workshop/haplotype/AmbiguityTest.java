/**
 * 
 */
package workshop.haplotype;

import org.junit.Test;

import workshop.haplotype.ambiguity.AmbiguityList;
import workshop.haplotype.ambiguity.CompressAmbiguity;
import workshop.haplotype.ambiguity.ExtractTwoField;
import workshop.haplotype.ambiguity.FixAmbiguityNumericalOrder;
import workshop.haplotype.ambiguity.PrioritizePhaseAmbiguity;
import workshop.haplotype.sirona.RemoveNotation;

/**
 * @author kazu
 *
 */
public class AmbiguityTest {

	@Test
	public void testAmbiguityList() {
		System.out.println("AmbiguityList");
		AmbiguityList amList = new AmbiguityList();
		for (String str : amList.getAmbiguityList()) {
			System.out.println(str);
		}
		
		System.out.println();
		amList.convertToAmbiguityString("HLA-DRB1*15:01:01:01");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-DRB3*02:02:01:02v1");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-A*01:01:01:01");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-DPB1*13:01:01");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-DPB1*02:01:19");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-DRB1*03:01:01:01");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-DQA1*01:02:01:01/HLA-DQA1*01:02:01:03");
		System.out.println(amList.getConverted());
		
		amList.convertToAmbiguityString("HLA-DPB1*04:01:01:01/HLA-DPB1*04:01:01:02");
		System.out.println(amList.getConverted());	
		System.out.println();
	}
	
	
	@Test
	public void testCompressAmbiguity() {
		System.out.println("CompressAmbiguity");
		String str1 = "HLA-DPB1*02:01:02+HLA-DPB1*13:01:01";
		String str2 = "HLA-DPB1*02:01:02+HLA-DPB1*107:01";
		CompressAmbiguity ca = new CompressAmbiguity(str1, str2);
		System.out.println(ca.getGlString());
		System.out.println(ca.getResolved());
		
		str1 = "HLA-DPB1*04:01:01:01+HLA-DPB1*04:02:01:02";
		str2 = "HLA-DPB1*105:01+HLA-DPB1*126:01";
		CompressAmbiguity ca1 = new CompressAmbiguity(str1, str2);
		System.out.println(ca1.getGlString());
		System.out.println(ca1.getResolved());
				
		str1 = "HLA-DQB1*02:01:01+HLA-DQB1*05:01:01:02";
		str2 = "HLA-DQB1*02:01:01+HLA-DQB1*05:01:01:03";
		CompressAmbiguity ca2 = new CompressAmbiguity(str1, str2);
		System.out.println(ca2.getGlString());
		System.out.println(ca2.getResolved());
		
		str1 = "HLA-DRB4*01:03:01:01+HLA-DRB4*01:03:01:02N";
		str2 = "HLA-DRB4*01:03:01:01+HLA-DRB4*01:03:02";		
		CompressAmbiguity ca3 = new CompressAmbiguity(str1, str2);
		System.out.println(ca3.getGlString());
		System.out.println(ca3.getResolved());
		
		str1 = "HLA-DRB4*01:03:01:01+HLA-DRB4*01:03:01:02N/HLA-DRB4*01:03:02";
		str2 = "HLA-DRB4*01:03:01:03+HLA-DRB4*01:03:01:02N/HLA-DRB4*01:03:02";		
		CompressAmbiguity ca4 = new CompressAmbiguity(str1, str2);
		System.out.println(ca4.getGlString());
		System.out.println(ca4.getResolved());
		
		str1 = "HLA-DRB1*11:01:01:01+HLA-DRB1*15:01:01:03";
		str2 = "HLA-DRB1*11:01:01:01+HLA-DRB1*15:01:01:01";		
		CompressAmbiguity ca5 = new CompressAmbiguity(str1, str2);
		System.out.println(ca5.getGlString());
		System.out.println(ca5.getResolved());
		
		str1 = "HLA-DRB1*11:01:01:01+HLA-DRB1*15:01:01:01/HLA-DRB1*15:01:01:03";
		str2 = "HLA-DRB1*11:01:01:01+HLA-DRB1*15:01:01:02";		
		CompressAmbiguity ca6 = new CompressAmbiguity(str1, str2);
		System.out.println(ca6.getGlString());
		System.out.println(ca6.getResolved());
		
		str1 = "HLA-DRB1*03:01:01:01/HLA-DRB1*03:01:01:02+HLA-DRB1*12:01:01:01/HLA-DRB1*12:01:01:03/HLA-DRB1*12:10";
		str2 = "HLA-DRB1*03:01:01:02+HLA-DRB1*12:01:01:01/HLA-DRB1*12:01:01:03";		
		CompressAmbiguity ca7 = new CompressAmbiguity(str1, str2);
		System.out.println(ca7.getGlString());
		System.out.println(ca7.getResolved());
		System.out.println();
	}
	
	@Test
	public void testPrioritizePhaseAmbiguity() {
		System.out.println("PrioritizePhaseAmbiguity");
		String str1 = "HLA-DPB1*04:01:01:01+HLA-DPB1*04:02:01:02|HLA-DPB1*105:01+HLA-DPB1*126:01";
		PrioritizePhaseAmbiguity ppa1 = new PrioritizePhaseAmbiguity(str1);
		System.out.println(ppa1.getOrderedAmbiguity());
		
		String str2 = "HLA-DPB1*105:01+HLA-DPB1*126:01|HLA-DPB1*04:01:01:01+HLA-DPB1*04:02:01:02";
		PrioritizePhaseAmbiguity ppa2 = new PrioritizePhaseAmbiguity(str2);
		System.out.println(ppa2.getOrderedAmbiguity());
		
		String str3 = "HLA-DPB1*126:01+HLA-DPB1*105:01|HLA-DPB1*04:02:01:02+HLA-DPB1*04:01:01:01";
		PrioritizePhaseAmbiguity ppa3 = new PrioritizePhaseAmbiguity(str3);
		System.out.println(ppa3.getOrderedAmbiguity());
		
		String str4 = "HLA-DPB1*05:01:01+HLA-DPB1*107:01/HLA-DPB1*13:01:01|HLA-DPB1*135:01+HLA-DPB1*519:01";
		PrioritizePhaseAmbiguity ppa4 = new PrioritizePhaseAmbiguity(str4);
		System.out.println(ppa4.getOrderedAmbiguity());
		
		String str5 = "HLA-DPB1*03:01:01+HLA-DPB1*04:01:01:01|HLA-DPB1*03:01:01+HLA-DPB1*04:01:01:02|HLA-DPB1*124:01+HLA-DPB1*350:01";
		PrioritizePhaseAmbiguity ppa5 = new PrioritizePhaseAmbiguity(str5);
		System.out.println(ppa5.getOrderedAmbiguity());
		System.out.println();
	}
	
	
	@Test	
	public void testExtractTwoField() {
		System.out.println("ExtractTwoFiled");
		ExtractTwoField extracted = new ExtractTwoField("HLA-DRB1*04:07:01/HLA-DRB1*04:92");
		for (String str : extracted.getExtractedTwoFieldType().getList()) {
			System.out.println(str);
			
		}
		System.out.println();
		
		ExtractTwoField extracted1 = new ExtractTwoField("HLA-DQB1*03:03:02:01/HLA-DQB1*03:03:02:02/HLA-DQB1*03:03:02:03");
		for (String str : extracted1.getExtractedTwoFieldType().getList()) {
			System.out.println(str);			
		}
		System.out.println();
		
		ExtractTwoField extracted2 = new ExtractTwoField("HLA-DRB4*01:03:01:01/HLA-DRB4*01:03:01:02N/HLA-DRB4*01:03:01:03");
		for (String str : extracted2.getExtractedTwoFieldType().getList()) {
			System.out.println(str);			
		}
		System.out.println();
		ExtractTwoField extracted3 = new ExtractTwoField("HLA-DRB4*01:03:01:01/HLA-DRB4*01:03:01:03");
		for (String str : extracted3.getExtractedTwoFieldType().getList()) {
			System.out.println(str);			
		}
		System.out.println();
		ExtractTwoField extracted4 = new ExtractTwoField("HLA-DRB4*01:03:01:02N");
		for (String str : extracted4.getExtractedTwoFieldType().getList()) {
			System.out.println(str);			
		}
		System.out.println();
		ExtractTwoField extracted5 = new ExtractTwoField("HLA-DPB1*13:01:01/HLA-DPB1*107:01");
		for (String str : extracted5.getExtractedTwoFieldType().getList()) {
			System.out.println(str);			
		}
		System.out.println();
		ExtractTwoField extracted6 = new ExtractTwoField("HLA-DPB1*107:01/HLA-DPB1*13:01:01");
		for (String str : extracted6.getExtractedTwoFieldType().getList()) {
			System.out.println(str);			
		}
		System.out.println();
	}
	
	@Test
	public void testRemoveNotation() {
		System.out.println("RemoveNotation");
		String ambiguity1 = "HLA-DPB1*04:02:01:02/HLA-DPB1*105:01/HLA-DPB1*105:01i1";
		RemoveNotation rn1 = new RemoveNotation(ambiguity1);
		System.out.println(rn1.getFixed());
		System.out.println(rn1.getXkept());
		
		String ambiguity2 = "HLA-DPB1*13:01:01e1/HLA-DPB1*107:01";
		RemoveNotation rn2 = new RemoveNotation(ambiguity2);
		System.out.println(rn2.getFixed());
		System.out.println(rn2.getXkept());
		
		String fake = "HLA-DPB1*13:01:01/HLA-DPB1*13:01:01e1/HLA-DPB1*133:01/HLA-DPB1*04:02:01:02/HLA-DPB1*105:01/HLA-DPB1*105:01i1";
		RemoveNotation rn3 = new RemoveNotation(fake);
		System.out.println(rn3.getFixed());
		System.out.println(rn3.getXkept());
		
		String test = "HLA-B*14:02:01:01+HLA-B*35:02:01e1";
		RemoveNotation rn4 = new RemoveNotation(test);
		System.out.println(rn4.getFixed());
		System.out.println(rn4.getXkept());
		
		String test5 = "HLA-B*14:02:01:01+HLA-B*35:02:01x1";
		RemoveNotation rn5 = new RemoveNotation(test5);
		System.out.println(rn5.getFixed());
		System.out.println(rn5.getXkept());
		
		String test6 = "HLA-DPB1*04:01:01:01+HLA-DPB1*04:02:01:02|HLA-DPB1*105:01+HLA-DPB1*126:01|HLA-DPB1*105:01i1+HLA-DPB1*126:01";
		RemoveNotation rn6 = new RemoveNotation(test6);
		System.out.println(rn6.getFixed());
		System.out.println(rn6.getXkept());
		System.out.println();		
	}
	
	@Test
	public void testFixAmbiguityNumericalOrder() {
		System.out.println("FixAmbiguityNumericalOrder");
		String str = "HLA-DQB1*05:01:01:02/HLA-DQB1*05:01:01:01/HLA-DQB1*05:01:01:03";
		FixAmbiguityNumericalOrder fano = new FixAmbiguityNumericalOrder(str);
		System.out.println(fano.getFixedGl());
		System.out.println();
	}


}
