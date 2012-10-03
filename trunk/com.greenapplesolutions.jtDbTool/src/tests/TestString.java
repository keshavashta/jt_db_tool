package tests;

import java.util.List;

import modelProvider.FileLoaderModelProvider;

import org.apache.commons.lang.RandomStringUtils;

import com.greenapplesolutions.dbloader.domain.Judgement;

public class TestString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FileLoaderModelProvider f = new FileLoaderModelProvider(
				"C:\\Users\\KESHAV\\Downloads\\JT 2012 (6) 1 (1).txt");
		List<Judgement> ju = f.extractJudgments();
		System.out.println(ju.size());
	}
}
