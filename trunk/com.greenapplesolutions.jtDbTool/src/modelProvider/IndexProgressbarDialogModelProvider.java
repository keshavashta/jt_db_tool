package modelProvider;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import readWriteDatabase.LoadJudgments;
import readWriteDatabase.UpdateJudgement;
import util.SelectedCourt;

import com.greenapplesolutions.lawsearch.desktopapp.customEvents.PropertyChangeEvent;

public class IndexProgressbarDialogModelProvider {
	private String indexLabelMessage;
	private String logMessage = "";
	private List<String> courts;
	private String path;

	public IndexProgressbarDialogModelProvider(List<String> courts, String path) {
		this.courts = courts;
		this.path = path;
		indexJudgements();
	}

	private void indexJudgements() {
		Runnable worker = new Runnable() {
			@Override
			public synchronized void run() {

				for (int index = 0; index < courts.size(); ++index) {
					LoadJudgments ins = new LoadJudgments(SelectedCourt
							.getInstance().getDatabaseName(courts.get(index)),
							"localhost", "root", "", path);
					setIndexLabelMessage("Indexing " + courts.get(index) + " ,"
							+ (courts.size() - (index + 1)) + " courts left.");
					if (ins.connectToDatabse()) {
						setLogMessage((logMessage + "\n Indexing "
								+ courts.get(index) + " judgements").trim());

						ins.indexJudgements();
						setLogMessage(logMessage + "\n" + courts.get(index)
								+ " where index is" + index
								+ " Log Message".trim());
					} else
						setLogMessage((logMessage
								+ "\n Error in connecting with database "
								+ SelectedCourt.getInstance().getDatabaseName(
										courts.get(index)) + "where court is  "
								+ courts.get(index) + " judgements").trim());
					fireCustomPropertyChangeEvent(new PropertyChangeEvent(this,
							"totalProgress", index + 1));

				}
				setIndexLabelMessage("Completed");
			}
		};

		Thread workerThread = new Thread(worker);
		try {
			workerThread.join();
		} catch (InterruptedException e) {

		}
		workerThread.start();
	}

	public String getIndexLabelMessage() {
		return indexLabelMessage;
	}

	public void setIndexLabelMessage(String indexLabelMessage) {
		propertyChangeSupport.firePropertyChange("indexLabelMessage",
				this.indexLabelMessage,
				this.indexLabelMessage = indexLabelMessage);
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		propertyChangeSupport.firePropertyChange("logMessage", this.logMessage,
				this.logMessage = logMessage);
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

	protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

	public void addCustomEventListener(
			com.greenapplesolutions.lawsearch.desktopapp.customEvents.PropertyChangeListener listener) {
		listenerList
				.add(com.greenapplesolutions.lawsearch.desktopapp.customEvents.PropertyChangeListener.class,
						listener);
	}

	public void removeCompositeChangeListener(
			com.greenapplesolutions.lawsearch.desktopapp.customEvents.PropertyChangeListener listener) {
		listenerList
				.remove(com.greenapplesolutions.lawsearch.desktopapp.customEvents.PropertyChangeListener.class,
						listener);
	}

	public void fireCustomPropertyChangeEvent(PropertyChangeEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == com.greenapplesolutions.lawsearch.desktopapp.customEvents.PropertyChangeListener.class) {
				((com.greenapplesolutions.lawsearch.desktopapp.customEvents.PropertyChangeListener) listeners[i + 1])
						.propertyChanged(evt);
			}
		}
	}
}
