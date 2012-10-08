package modelProvider;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

import dialogs.IndexProgressBarDialog;

public class PartialIndexJudgementEditorModelProvider {

	private String directoryPath;

	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		propertyChangeSupport.firePropertyChange("directoryPath",
				this.directoryPath, this.directoryPath = directoryPath);
	}

	public PartialIndexJudgementEditorModelProvider() {
		selectedCourtList = new ArrayList<String>();
		selectedCourtList.add("ALLAHABAD HIGH COURT");
		selectedCourtList.add("ANDHRA PRADESH HIGH COURT");
		selectedCourtList.add("BOMBAY HIGH COURT");
		selectedCourtList.add("CALCUTTA HIGH COURT");
		selectedCourtList.add("CHHATTISGARH HIGH COURT");
		selectedCourtList.add("DELHI HIGH COURT");
		selectedCourtList.add("GAUHATI HIGH COURT");
		selectedCourtList.add("GUJARAT HIGH COURT");
		selectedCourtList.add("HIMACHAL PRADESH HIGH COURT");
		selectedCourtList.add("JAMMU AND KASHMIR HIGH COURT");
		selectedCourtList.add("JHARKHAND HIGH COURT");
		selectedCourtList.add("KARNATAKA HIGH COURT");
		selectedCourtList.add("KERALA HIGH COURT");
		selectedCourtList.add("MADHYA PRADESH HIGH COURT");
		selectedCourtList.add("MADRAS HIGH COURT");
		selectedCourtList.add("ORISSA HIGH COURT");
		selectedCourtList.add("PATNA HIGH COURT");
		selectedCourtList.add("PUNJAB AND HARYANA HIGH COURT");
		selectedCourtList.add("RAJASTHAN HIGH COURT");
		selectedCourtList.add("SIKKIM HIGH COURT");
		selectedCourtList.add("SUPREME COURT");
		selectedCourtList.add("UTTARAKHAND HIGH COURT");
		setFromDate(new Date());
	}

	private List<String> selectedCourtList;
	private Date fromDate;

	public void changeCourtStatus(Button button) {
		String text = button.getText();
		if (button.getSelection())
			selectedCourtList.add(text);
		else
			selectedCourtList.remove(text);
		Collections.sort(selectedCourtList);
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

	public void indexPartialJudgements() {
		IndexProgressBarDialog dialog = new IndexProgressBarDialog(new Shell(),
				selectedCourtList, directoryPath,fromDate);
		
		dialog.open();
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		propertyChangeSupport.firePropertyChange("fromDate", this.fromDate,
				this.fromDate = fromDate);
	}
}
