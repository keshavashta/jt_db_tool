package modelProvider;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import readWriteDatabase.WriteJudgement;

import util.SelectedCourt;

import com.greenapplesolutions.dbloader.domain.Judgement;

public class JudgmentsEditorModelProvider {

	public JudgmentsEditorModelProvider(List<Judgement> judgements,
			boolean isFileLoaded) {

		this.judgements = judgements;
		if (!isFileLoaded)
			setSelectedCourtLabel("Results of "
					+ SelectedCourt.getInstance().getSelectedCourt());
		else
			setSelectedCourtLabel("Number of judgements loaded : "
					+ judgements.size());
	}

	private List<Judgement> judgements;
	private String selectedCourtLabel;

	public List<Judgement> getJudgements() {
		return this.judgements;
	}

	public void setJudgements(List<Judgement> judgements) {
		this.judgements = judgements;
	}

	public String getSelectedCourtLabel() {
		return selectedCourtLabel;
	}

	public void setSelectedCourtLabel(String selectedCourtLabel) {
		propertyChangeSupport.firePropertyChange("selectedCourtLabel",
				this.selectedCourtLabel,
				this.selectedCourtLabel = selectedCourtLabel);
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

	public boolean deleteJudgement(String keycode) {
		WriteJudgement ujInstance = new WriteJudgement(
				getJudgements().get(0).Court.trim().toUpperCase(), "localhost",
				"root", "");
		boolean isDeleted = false;
		if (ujInstance.connectToDatabse())
			isDeleted = ujInstance.deleteJudgement(keycode);
		return isDeleted;
	}

}
