package modelProvider;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import readWriteDatabase.LoadJudgments;
import util.JTLogger;
import util.SelectedCourt;
import util.Util;

import com.greenapplesolutions.lawsearch.desktopapp.customEvents.PropertyChangeEvent;

public class IndexProgressbarDialogModelProvider {
	private String indexLabelMessage;
	private String logMessage = "";
	private List<String> courts;
	private String path;
	public IndexProgressbarDialogModelProvider ipdmInstance;
	private Date fromDate;

	public IndexProgressbarDialogModelProvider(List<String> courts,
			String path, Date fromDate) {
		this.courts = courts;
		this.path = path;
		ipdmInstance = this;
		this.fromDate = fromDate;
		if (fromDate == null)
			indexJudgements();
		else
			partialindexJudgements();
	}

	private void indexJudgements() {
		Runnable worker = new Runnable() {
			@Override
			public synchronized void run() {
				File idxFolder = new File(path + "\\idx");
				if (!idxFolder.isDirectory())
					idxFolder.mkdir();
				File judFolder = new File(path + "\\idx\\j");
				if (!judFolder.isDirectory())
					judFolder.mkdir();
				File dicFolder = new File(path + "\\idx\\d");
				File utilFolder = new File(path + "\\utility files");
				if (!utilFolder.isDirectory())
					utilFolder.mkdir();
				if (!dicFolder.isDirectory())
					dicFolder.mkdir();

				for (int index = 0; index < courts.size(); ++index) {
					LoadJudgments ins = new LoadJudgments(SelectedCourt
							.getInstance().getDatabaseName(courts.get(index)),
							"localhost", "root", "",
							judFolder.getAbsolutePath());
					setIndexLabelMessage("Indexing " + courts.get(index) + " ,"
							+ (courts.size() - (index + 1)) + " courts left.");
					if (ins.connectToDatabse()) {
						setLogMessage((logMessage + "\n Indexing "
								+ courts.get(index) + " judgements").trim());

						ins.indexJudgements(ipdmInstance);
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
				setIndexLabelMessage("Indexing Completed , Please wait while we are moving utility files.");
				try {
					FileUtils.copyDirectory(new File(Util.getRelativePath()
							+ "/utility files"), utilFolder);
					setLogMessage((logMessage + "\n moving utility files"));
				} catch (IOException e) {
					JTLogger.getInstance().setError(
							"Error in moving Files, due to " + e.getMessage());
					setLogMessage((logMessage + "\n Error in moving utility files"));
				}
				setIndexLabelMessage("moving dictionary index files.");
				try {
					FileUtils.copyDirectory(new File(Util.getRelativePath()
							+ "/d"), dicFolder);
					setLogMessage((logMessage + "\n moving dictionary idx files"));
				} catch (IOException e) {
					JTLogger.getInstance().setError(
							"Error in moving dictionary idx files, due to "
									+ e.getMessage());
					setLogMessage((logMessage + "\n Error in moving dictionary idx files"));
				}
				setIndexLabelMessage("Completed");
			}
		};

		Thread workerThread = new Thread(worker);
		try {
			workerThread.join();
		} catch (InterruptedException e) {
			JTLogger.getInstance().setError(
					"Error in joining thread, due to " + e.getMessage());
		}
		workerThread.start();
	}

	private void partialindexJudgements() {
		Runnable worker = new Runnable() {
			@Override
			public synchronized void run() {
				File idxFolder = new File(path + "\\idx");
				if (!idxFolder.isDirectory())
					idxFolder.mkdir();
				File judFolder = new File(path + "\\idx\\j");
				if (!judFolder.isDirectory())
					judFolder.mkdir();
				File dicFolder = new File(path + "\\idx\\d");
				File utilFolder = new File(path + "\\utility files");
				if (!utilFolder.isDirectory())
					utilFolder.mkdir();
				if (!dicFolder.isDirectory())
					dicFolder.mkdir();

				for (int index = 0; index < courts.size(); ++index) {
					LoadJudgments ins = new LoadJudgments(SelectedCourt
							.getInstance().getDatabaseName(courts.get(index)),
							"localhost", "root", "",
							judFolder.getAbsolutePath());
					setIndexLabelMessage("Indexing " + courts.get(index) + " ,"
							+ (courts.size() - (index + 1)) + " courts left.");
					if (ins.connectToDatabse()) {
						setLogMessage((logMessage + "\n Indexing "
								+ courts.get(index) + " judgements").trim());

						ins.indexPartialJudgements(ipdmInstance,Util.getDateInString("yyyy-MM-dd", fromDate));
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
				setIndexLabelMessage("Indexing Completed , Please wait while we are moving utility files.");
				try {
					FileUtils.copyDirectory(new File(Util.getRelativePath()
							+ "/utility files"), utilFolder);
					setLogMessage((logMessage + "\n moving utility files"));
				} catch (IOException e) {
					JTLogger.getInstance().setError(
							"Error in moving Files, due to " + e.getMessage());
					setLogMessage((logMessage + "\n Error in moving utility files"));
				}
				setIndexLabelMessage("moving dictionary index files.");
				try {
					FileUtils.copyDirectory(new File(Util.getRelativePath()
							+ "/d"), dicFolder);
					setLogMessage((logMessage + "\n moving dictionary idx files"));
				} catch (IOException e) {
					JTLogger.getInstance().setError(
							"Error in moving dictionary idx files, due to "
									+ e.getMessage());
					setLogMessage((logMessage + "\n Error in moving dictionary idx files"));
				}
				setIndexLabelMessage("Completed");
			}
		};

		Thread workerThread = new Thread(worker);
		try {
			workerThread.join();
		} catch (InterruptedException e) {
			JTLogger.getInstance().setError(
					"Error in joining thread, due to " + e.getMessage());
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
