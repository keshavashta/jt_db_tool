package tests;

import readWriteDatabase.LoadJudgments;

public class TestDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoadJudgments ljIns = new LoadJudgments("jt_uttarakhand", "localhost",
				"root", "", "");
		ljIns.connectToDatabse();
		ljIns.getCount();
	}

}
