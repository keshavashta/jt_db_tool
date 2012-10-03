package modelProvider;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import util.SelectedCourt;

import com.greenapplesolutions.dbloader.domain.Judgement;

public class JudgmentsEditorModelProvider {

	public JudgmentsEditorModelProvider(List<Judgement> judgements) {
		this.judgements = judgements;
		setSelectedCourtLabel("Results of "
				+ SelectedCourt.getInstance().getSelectedCourt());
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
}
