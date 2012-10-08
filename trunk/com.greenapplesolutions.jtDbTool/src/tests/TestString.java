package tests;

import java.util.List;

import modelProvider.FileLoaderModelProvider;

import org.apache.commons.lang.RandomStringUtils;

import readWriteDatabase.WriteJudgement;

import com.greenapplesolutions.jtdbtool.domain.Judgement;

public class TestString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 300; ++i) {
			FileLoaderModelProvider f = new FileLoaderModelProvider();
			f.setFilePath("C:\\Users\\KESHAV\\Downloads\\JT 2012 (6) 1 (1).txt");
			List<Judgement> ju = f.extractJudgments();
			WriteJudgement ins = new WriteJudgement("jt_uttarakhand",
					"localhost", "root", "");
			if (ins.connectToDatabse()) {
				ins.insertJudgements(ju);
			}
			System.out.println("***********************"+i+"*****************************");
		}
	}
}
