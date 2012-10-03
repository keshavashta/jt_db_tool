package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectedCourt {
	private HashMap<String, String> courtsAndDbMap;

	private SelectedCourt() {
		courtsAndDbMap = new HashMap<String, String>();
		courtsAndDbMap.put("ALLAHABAD HIGH COURT", "jt_allahadbad");
		courtsAndDbMap.put("ANDHRA PRADESH HIGH COURT", "jt_andhra");
		courtsAndDbMap.put("BOMBAY HIGH COURT", "jt_bombay");
		courtsAndDbMap.put("CALCUTTA HIGH COURT", "jt_calcutta");
		courtsAndDbMap.put("CHHATTISGARH HIGH COURT", "jt_chattisgarh");
		courtsAndDbMap.put("DELHI HIGH COURT", "jt_delhi");
		courtsAndDbMap.put("GAUHATI HIGH COURT", "jt_gauhati");
		courtsAndDbMap.put("GUJARAT HIGH COURT", "jt_gujarat");
		courtsAndDbMap.put("HIMACHAL PRADESH HIGH COURT", "jt_himachal");
		courtsAndDbMap.put("JAMMU AND KASHMIR HIGH COURT", "jt_jk");
		courtsAndDbMap.put("JHARKHAND HIGH COURT", "jt_jharkhand");
		courtsAndDbMap.put("KARNATAKA HIGH COURT", "jt_karnatka");
		courtsAndDbMap.put("KERALA HIGH COURT", "jt_kerala");
		courtsAndDbMap.put("MADHYA PRADESH HIGH COURT", "jt_mp");
		courtsAndDbMap.put("MADRAS HIGH COURT", "jt_madras");
		courtsAndDbMap.put("ORISSA HIGH COURT", "jt_orrissa");
		courtsAndDbMap.put("PATNA HIGH COURT", "jt_patna");
		courtsAndDbMap
				.put("PUNJAB AND HARYANA HIGH COURT", "jt_punjab_haryand");
		courtsAndDbMap.put("RAJASTHAN HIGH COURT", "jt_rajasthan");
		courtsAndDbMap.put("SIKKIM HIGH COURT", "jt_sikkim");
		courtsAndDbMap.put("SUPREME COURT", "jt_sc");
		courtsAndDbMap.put("UTTARAKHAND HIGH COURT", "jt_uttarakhand");
		
		
		
			courts=new ArrayList<String>();
			courts.add("ALLAHABAD HIGH COURT");
			courts.add("ANDHRA PRADESH HIGH COURT");
			courts.add("BOMBAY HIGH COURT");
			courts.add("CALCUTTA HIGH COURT");
			courts.add("CHHATTISGARH HIGH COURT");
			courts.add("DELHI HIGH COURT");
			courts.add("GAUHATI HIGH COURT");
			courts.add("GUJARAT HIGH COURT");
			courts.add("HIMACHAL PRADESH HIGH COURT");
			courts.add("JAMMU AND KASHMIR HIGH COURT");
			courts.add("JHARKHAND HIGH COURT");
			courts.add("KARNATAKA HIGH COURT");
			courts.add("KERALA HIGH COURT");
			courts.add("MADHYA PRADESH HIGH COURT");
			courts.add("MADRAS HIGH COURT");
			courts.add("ORISSA HIGH COURT");
			courts.add("PATNA HIGH COURT");
			courts.add("PUNJAB AND HARYANA HIGH COURT");
			courts.add("RAJASTHAN HIGH COURT");
			courts.add("SIKKIM HIGH COURT");
			courts.add("SUPREME COURT");
			courts.add("UTTARAKHAND HIGH COURT");

		

	}
	private List<String> courts;
	public  List<String> getCourts(){
		return courts;
	}
	private static SelectedCourt _instance;
	private String selectedCourt = "SUPREME COURT";

	public static synchronized SelectedCourt getInstance() {
		if (_instance == null) {
			_instance = new SelectedCourt();
			return _instance;
		}
		return _instance;
	}

	public String getSelectedCourt() {
		return this.selectedCourt;
	}

	public void setSelectedCourt(String courtName) {
		this.selectedCourt = courtName;
	}

	public String getSelectedDatabaseName() {
		return courtsAndDbMap.get(selectedCourt);
	}
}
