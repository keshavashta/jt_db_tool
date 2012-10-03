package tests;

import java.util.List;

import modelProvider.FileLoaderModelProvider;

import org.apache.commons.lang.RandomStringUtils;

import readWriteDatabase.UpdateJudgement;

import com.greenapplesolutions.dbloader.domain.Judgement;

public class TestString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FileLoaderModelProvider f = new FileLoaderModelProvider();
		f.setFilePath("C:\\Users\\saxena.arunesh\\Downloads\\keshav\\JT 2012 (6) 1.txt");
		List<Judgement> ju = f.extractJudgments();
		UpdateJudgement ins = new UpdateJudgement("jt_uttrakhand", "localhost",
				"root", "");
		if (ins.connectToDatabse()) {
			ins.insertJudgements(ju);
		}
	}
}
