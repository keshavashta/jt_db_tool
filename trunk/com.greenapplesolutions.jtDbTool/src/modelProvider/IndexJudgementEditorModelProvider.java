package modelProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.widgets.Button;

public class IndexJudgementEditorModelProvider {

	public IndexJudgementEditorModelProvider() {
		selectedCourt=new ArrayList<String>();
		selectedCourt.add("ALLAHABAD HIGH COURT");
		selectedCourt.add("ANDHRA PRADESH HIGH COURT");
		selectedCourt.add("BOMBAY HIGH COURT");
		selectedCourt.add("CALCUTTA HIGH COURT");
		selectedCourt.add("CHHATTISGARH HIGH COURT");
		selectedCourt.add("DELHI HIGH COURT");
		selectedCourt.add("GAUHATI HIGH COURT");
		selectedCourt.add("GUJARAT HIGH COURT");
		selectedCourt.add("HIMACHAL PRADESH HIGH COURT");
		selectedCourt.add("JAMMU AND KASHMIR HIGH COURT");
		selectedCourt.add("JHARKHAND HIGH COURT");
		selectedCourt.add("KARNATAKA HIGH COURT");
		selectedCourt.add("KERALA HIGH COURT");
		selectedCourt.add("MADHYA PRADESH HIGH COURT");
		selectedCourt.add("MADRAS HIGH COURT");
		selectedCourt.add("ORISSA HIGH COURT");
		selectedCourt.add("PATNA HIGH COURT");
		selectedCourt.add("PUNJAB AND HARYANA HIGH COURT");
		selectedCourt.add("RAJASTHAN HIGH COURT");
		selectedCourt.add("SIKKIM HIGH COURT");
		selectedCourt.add("SUPREME COURT");
		selectedCourt.add("UTTARAKHAND HIGH COURT");
	}

	private List<String> selectedCourt;
	public void changeCourtStatus(Button button){
		String text=button.getText();
		if(button.getSelection())
			selectedCourt.add(text);
		else
			selectedCourt.remove(text);
		Collections.sort(selectedCourt);
	}
	public boolean indexJudgements(){
		return false;
	}
}
