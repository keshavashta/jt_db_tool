package modelProvider;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import util.SelectedCourt;

public class ChangeCourtDialogModelProvider {

	public ChangeCourtDialogModelProvider() {
		courts = new ArrayList<String>();
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
	private String selectedCourt;

	public List<String> getCourts() {
		return this.courts;
	}

	public String getSelectedCourt() {
		return selectedCourt;
	}

	public void setSelectedCourt(String selectedCourt) {
		propertyChangeSupport.firePropertyChange("selectedCourt",
				this.selectedCourt, this.selectedCourt = selectedCourt);
		
	}
	
	public void changeCourt(){
		SelectedCourt.getInstance().setSelectedCourt(getSelectedCourt());
	}

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
}
