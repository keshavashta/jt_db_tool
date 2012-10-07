package modelProvider;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import readWriteDatabase.WriteJudgement;

import util.JTLogger;
import util.SelectedCourt;
import util.Util;

import com.greenapplesolutions.dbloader.domain.Citation;
import com.greenapplesolutions.dbloader.domain.HeadnoteAndHeld;
import com.greenapplesolutions.dbloader.domain.Judgement;
import com.ibm.icu.text.SimpleDateFormat;

import editorInput.JudgmentEditorInput;
import editors.JudgmentsEditor;

public class FileLoaderModelProvider {
	private String filePath;

	private Pattern citationPattern = Pattern.compile(
			"^Jt\\s{0,4}\\d{4}\\s{0,4}\\(\\d{0,4}\\)\\s{0,4}\\d{0,4}",
			Pattern.CASE_INSENSITIVE);
	private boolean isCitationProcessed;
	private boolean isPartyProcessed;
	private boolean isDateProcessed;
	private boolean isAdvocateProcessed;
	private boolean isHeadnoteProcessed;
	private boolean isCasesReferredProcessed;
	private Pattern yearPattern = Pattern.compile("\\s\\d[^\\(]*");
	private Pattern volumePattern = Pattern.compile("\\(\\d+\\)");
	private Pattern pagePattern = Pattern.compile("\\d+$");
	private Pattern partyPattern = Pattern.compile(".*v\\..*");
	private Pattern casesTeferred = Pattern.compile("Cases Referred:",
			Pattern.CASE_INSENSITIVE);
	private Pattern datePattern = Pattern.compile(
			"d.*\\d+\\s{0,4}\\.\\s{0,4}\\d+\\s{0,4}\\.\\s{0,4}\\d+",
			Pattern.CASE_INSENSITIVE);
	private Pattern extractDate = Pattern
			.compile("\\d+\\s{0,4}\\.\\s{0,4}\\d+\\s{0,4}\\.\\s{0,4}\\d+");
	private Pattern advocatesPattern = Pattern.compile("^Appearances");
	private List<String> courtsList;

	public FileLoaderModelProvider() {
		courtsList = SelectedCourt.getInstance().getCourts();
		setSelectedCourt(SelectedCourt.getInstance().getSelectedCourt());
	}

	public List<String> getCourts() {
		return this.courtsList;
	}

	private String selectedCourt;

	private String readFile() {
		String fileText = "";
		try {

			byte[] b = null;
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			b = new byte[(int) file.length()];
			fis.read(b);
			fileText = new String(b);
		} catch (Exception e) {
			JTLogger.getInstance().setError(
					"Error in Reading File, due to " + e.getMessage());
		}
		return fileText;

	}

	private int processCitation(String[] textArray, int index,
			Judgement judgment) {
		isCitationProcessed = true;
		Citation citation = new Citation();
		citation.Page = 0;
		citation.Volume = "";
		citation.keycode = judgment.Keycode;
		citation.Year = 0;
		citation.Journal = "JT";
		Matcher yearMatcher = yearPattern.matcher(textArray[index]);
		if (yearMatcher.find()) {

			citation.Year = Integer.parseInt(yearMatcher.group().trim());
		}
		Matcher volMatcher = volumePattern.matcher(textArray[index]);
		if (volMatcher.find())
			citation.Volume = volMatcher.group().replace("(", "")
					.replace(")", "");
		Matcher pageMatcher = pagePattern.matcher(textArray[index].trim());
		if (pageMatcher.find())
			citation.Page = Integer.parseInt(pageMatcher.group());

		List<Citation> citations = new ArrayList<Citation>();
		citations.add(citation);
		judgment.Citations = citations;
		return index;

	}

	private void reset() {
		isCitationProcessed = false;
		isAdvocateProcessed = false;
		isDateProcessed = false;
		isPartyProcessed = false;
		isHeadnoteProcessed = false;
		isCasesReferredProcessed = false;
	}

	private Judgement getEmptyJudgement() {
		Judgement j = new Judgement();
		j.Appellant = "";
		j.Respondant = "";
		j.Keycode = RandomStringUtils.randomAlphanumeric(45).toUpperCase();
		j.Bench = 0;
		j.Advocates = "";
		Calendar cal = Calendar.getInstance();
		cal.set(1111, 10, 11);
		j.CaseDate = cal.getTime();
		j.CreatedDate = new Date();
		j.ModifiedDate = new Date();
		j.CaseNumber = "";
		j.Court = selectedCourt;
		j.Citations = new ArrayList<Citation>();
		j.FullText = "";
		j.headnotesAndHelds = new ArrayList<HeadnoteAndHeld>();
		j.Judges = "";
		j.CasesReferred = "";
		return j;

	}

	private Judgement extractJudgment(String text) {
		if (Util.isStringNullOrEmpty(text))
			return null;
		Judgement judgment = getEmptyJudgement();
		reset();
		String textArray[] = text.split("\n");

		for (int index = 0; index < textArray.length; ++index) {
			String headnote = textArray[index].trim();
			Matcher citationMatcher = citationPattern.matcher(textArray[index]
					.trim());
			Matcher partyMatcher = partyPattern.matcher(textArray[index]);
			Matcher dateMatcher = datePattern.matcher(textArray[index]);
			Matcher advMatcher = advocatesPattern.matcher(textArray[index]
					.trim());
			Matcher casesReferredMAtcher = casesTeferred
					.matcher(textArray[index]);

			if (citationMatcher.find() && !isCitationProcessed) {
				index = processCitation(textArray, index, judgment);
				continue;
			}
			if (partyMatcher.find() && !isPartyProcessed) {
				index = processParty(textArray, index, judgment);
				continue;
			}
			if (dateMatcher.find() && !isDateProcessed) {
				index = processDate(textArray, index, judgment);
				continue;
			}
			if (textArray[index].trim().toUpperCase().equals(headnote)
					&& !isHeadnoteProcessed && isCitationProcessed) {
				index = processHeadnote(textArray, index, judgment);
				continue;
			}
			if (casesReferredMAtcher.find() && !isCasesReferredProcessed) {
				index = processCasesReferred(textArray, index, judgment);
				continue;
			}
			if (advMatcher.find() && !isAdvocateProcessed) {
				index = processAdvocates(textArray, index, judgment);
				continue;
			}
			if (textArray[index].trim().endsWith("J.")
					|| textArray[index].trim().startsWith("ORDER")) {
				processJudgement(textArray, index, judgment);
				break;
			}
		}
		return judgment;
	}

	private void processJudgement(String[] textArray, int index,
			Judgement judgment) {
		for (; index < textArray.length; ++index)
			judgment.FullText += textArray[index] + "\n";
		judgment.FullText = judgment.FullText.trim();

	}

	private int processAdvocates(String[] textArray, int index,
			Judgement judgment) {
		isAdvocateProcessed = true;
		String adv = "";

		while (true && index < textArray.length - 2) {
			String headnote = textArray[index].trim();
			if (textArray[index].trim().endsWith("J.")
					|| textArray[index].trim().startsWith("ORDER")
					|| headnote.equals(textArray[index].trim().toUpperCase())) {
				--index;
				break;
			}
			adv += textArray[index].trim();
			++index;
		}
		judgment.Advocates = adv.replaceAll("(?i)Appearances", "");
		return index;
	}

	private int processCasesReferred(String[] textArray, int index,
			Judgement judgment) {
		isCasesReferredProcessed = true;

		String casesReferred = "";
		while (true && index < textArray.length - 2) {

			if (textArray[index].trim().endsWith("J.")
					|| textArray[index].trim().startsWith("ORDER")) {
				--index;
				break;
			}
			casesReferred += textArray[index].trim() + "\n";
			++index;
		}
		judgment.CasesReferred = casesReferred.replaceFirst(
				"(?i)cases referre(d:|(d))", "").trim();

		return index;
	}

	private int processHeadnote(String[] textArray, int index,
			Judgement judgment) {
		isHeadnoteProcessed = true;
		HeadnoteAndHeld hh = new HeadnoteAndHeld();
		List<HeadnoteAndHeld> hhList = new ArrayList<HeadnoteAndHeld>();
		hh.Keycode = judgment.Keycode;
		hh.Held = "";
		String headnote = "";
		while (true && index < textArray.length - 2) {
			Matcher mat = casesTeferred.matcher(textArray[index]);
			if (textArray[index].trim().endsWith("J.")
					|| textArray[index].trim().startsWith("ORDER")
					|| mat.find()) {
				--index;
				break;
			}
			headnote += textArray[index].trim() + "\n";
			++index;
		}
		hh.Headnote = headnote;
		if (!Util.isStringNullOrEmpty(headnote))
			hhList.add(hh);
		judgment.headnotesAndHelds = hhList;
		return index;
	}

	private int processDate(String textArray[], int index, Judgement judgment) {
		isDateProcessed = true;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Matcher extractDateMatcher = extractDate.matcher(textArray[index]);
		if (extractDateMatcher.find())
			try {
				judgment.CaseDate = sdf.parse(extractDateMatcher.group()
						.replace(" ", ""));
			} catch (ParseException e) {
				JTLogger.getInstance().setError(
						"Error in processing date in file loader, due to"
								+ e.getMessage());
			}
		return index;
	}

	private int processParty(String[] textArray, int index, Judgement judgement) {
		isPartyProcessed = true;
		String party[] = textArray[index].split("(?i) v\\. ");
		judgement.Appellant = party[0];
		judgement.Respondant = party[1];
		while (true && index < textArray.length - 1) {
			++index;
			if (textArray[index].trim().endsWith("J.")) {
				judgement.Judges = textArray[index].trim()
						.replaceAll("j{1,10}\\.$", "").trim()
						.replaceAll(",$", "").trim();
				break;
			}

			judgement.CaseNumber += textArray[index] + "\n";
		}
		if (!Util.isStringNullOrEmpty(judgement.CaseNumber))
			judgement.CaseNumber = judgement.CaseNumber.trim();
		return index;
	}

	public List<Judgement> extractJudgments() {
		List<Judgement> judgments = new ArrayList<Judgement>();
		String text = readFile();
		String[] judgementTextArray = text.split("\\*{10}");
		for (String judgementString : judgementTextArray) {
			Judgement judgement = extractJudgment(judgementString);
			if (judgement != null && !judgement.equals(getEmptyJudgement()))
				judgments.add(judgement);
		}
		return judgments;
	}

	public String getSelectedCourt() {
		return selectedCourt;
	}

	public void setSelectedCourt(String selectedCourt) {
		propertyChangeSupport.firePropertyChange("selectedCourt",
				this.selectedCourt, this.selectedCourt = selectedCourt);
		SelectedCourt.getInstance().setSelectedCourt(selectedCourt);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		propertyChangeSupport.firePropertyChange("filePath", this.filePath,
				this.filePath = filePath);
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

	private boolean isFileProcessed;

	public boolean getIsFileProcessed() {
		return isFileProcessed;
	}

	public boolean getIsDataInserted() {
		return isDataInserted;
	}

	public boolean getIsResultsDisplayed() {
		return isResultsDisplayed;
	}

	private boolean isDataInserted;
	private boolean isResultsDisplayed;
	IEditorInput input = null;

	public void load() {
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display
				.getDefault().getActiveShell());

		try {
			dialog.run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) {
					monitor.beginTask("Loading Judgments", 3); // begin task
					monitor.setTaskName("Parsing Judgements");
					List<Judgement> judgements = extractJudgments();
					isFileProcessed = true;

					monitor.worked(1);
					monitor.setTaskName("Inserting Judgements");
					WriteJudgement ins = new WriteJudgement(SelectedCourt
							.getInstance().getDatabaseName(selectedCourt),
							"localhost", "root", "");
					if (ins.connectToDatabse()) {
						ins.insertJudgements(judgements);
						isDataInserted = true;
					}
					monitor.worked(2);
					monitor.setTaskName("Displaying Judgements");

					input = new JudgmentEditorInput("Uploaded Judgements",
							judgements, true);

					Display.getDefault().asyncExec(new Runnable() {

						@Override
						public void run() {
							try {
								PlatformUI.getWorkbench()
										.getActiveWorkbenchWindow()
										.getActivePage()
										.openEditor(input, JudgmentsEditor.ID);
								isResultsDisplayed = true;

							} catch (PartInitException e3) {
								JTLogger.getInstance().setError(
										"Error in opening review judgement from file loader , due to "
												+ e3.getMessage());
							}
						}
					});
					monitor.done();

				}
			});
		} catch (InvocationTargetException e1) {
			JTLogger.getInstance().setError(
					"Error in opening review judgement from file loader , due to "
							+ e1.getMessage());
		} catch (InterruptedException e1) {
			JTLogger.getInstance().setError(
					"Error in opening review judgement from file loader , due to "
							+ e1.getMessage());
		}

	}
}
