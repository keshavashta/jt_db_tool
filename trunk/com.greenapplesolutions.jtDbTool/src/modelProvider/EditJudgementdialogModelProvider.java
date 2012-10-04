package modelProvider;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import readWriteDatabase.ReadJudgement;
import readWriteDatabase.UpdateJudgement;
import util.SelectedCourt;
import util.Util;

import com.greenapplesolutions.dbloader.domain.Citation;
import com.greenapplesolutions.dbloader.domain.HeadnoteAndHeld;
import com.greenapplesolutions.dbloader.domain.Judgement;

public class EditJudgementdialogModelProvider {
	private List<String> journals;

	public EditJudgementdialogModelProvider(String keycode) {
		this.keycode = keycode;
		getJudgementFromdatabase();
		journals = new ArrayList<String>();
		journals.add("");
		journals.add("AIR");
		journals.add("JT");
		journals.add("SCC");
		journals.add("SCR");
		setJudgement();

	}

	private String keycode;
	private String citation1_volume;
	private String citation2_volume;
	private String citation3_volume;
	private String citation4_volume;
	private String citation5_volume;
	private String citation6_volume;
	private String citation1_page;
	private String citation2_page;
	private String citation3_page;
	private String citation4_page;
	private String citation5_page;
	private String citation6_page;
	private String citation1_year;
	private String citation2_year;
	private String citation3_year;
	private String citation4_year;
	private String citation5_year;
	private String citation6_year;
	private String citation1_journal;
	private String citation2_journal;
	private String citation3_journal;
	private String citation4_journal;
	private String citation5_journal;
	private String citation6_journal;
	private String appellant;
	private String respondant;
	private String caseNumber;
	private String advocates;
	private String judges;
	private Date caseDate;
	private String judgementText;
	private String held1;
	private String held2;
	private String held3;
	private String held4;
	private String headnote1;
	private String headnote2;
	private String headnote3;
	private String headnote4;
	private String casesReferred;
	private Judgement judgement;

	public Judgement getJudgement() {
		return this.judgement;
	}

	private void setJudgement() {
		
		if (!Util.isStringNullOrEmpty(judgement.Appellant))
			setAppellant(judgement.Appellant.trim());
		if (!Util.isStringNullOrEmpty(getJudgement().Respondant))
			setRespondant(getJudgement().Respondant.trim());
		if (!Util.isStringNullOrEmpty(getJudgement().CaseNumber))
			setCaseNumber(judgement.CaseNumber.trim());
		if (!Util.isStringNullOrEmpty(judgement.Judges))
			setJudges(judgement.Judges.trim());
		if (judgement.CaseDate != null)
			setCaseDate(judgement.CaseDate);
		if (!Util.isStringNullOrEmpty(judgement.FullText))
			setJudgementText(judgement.FullText);
		if (!Util.isStringNullOrEmpty(judgement.CasesReferred))
			setCasesReferred(judgement.CasesReferred.trim());
		if (!Util.isStringNullOrEmpty(judgement.Advocates))
			setAdvocates(judgement.Advocates.trim());
		
		for (int index = 0; index < judgement.Citations.size(); ++index) {
			setCitation(index, judgement.Citations.get(index));
		}
		for (int index = 0; index < judgement.headnotesAndHelds.size(); ++index) {
			setHeadnoteAndHeld(index, judgement.headnotesAndHelds.get(index));
		}
	}

	private void setCitation(int index, Citation citation) {
		switch (index) {
		case 0: {
			setCitation1_journal(citation.Journal);
			setCitation1_page("" + citation.Page);
			setCitation1_volume(citation.Volume);
			setCitation1_year("" + citation.Year);
		}
			break;
		case 1: {
			setCitation2_journal(citation.Journal);
			setCitation2_page("" + citation.Page);
			setCitation2_volume(citation.Volume);
			setCitation2_year("" + citation.Year);
		}
			break;
		case 2: {
			setCitation3_journal(citation.Journal);
			setCitation3_page("" + citation.Page);
			setCitation3_volume(citation.Volume);
			setCitation3_year("" + citation.Year);
		}
			break;
		case 3: {
			setCitation4_journal(citation.Journal);
			setCitation4_page("" + citation.Page);
			setCitation4_volume(citation.Volume);
			setCitation4_year("" + citation.Year);
		}
			break;
		case 4: {
			setCitation5_journal(citation.Journal);
			setCitation5_page("" + citation.Page);
			setCitation5_volume(citation.Volume);
			setCitation5_year("" + citation.Year);
		}
			break;
		case 5: {
			setCitation6_journal(citation.Journal);
			setCitation6_page("" + citation.Page);
			setCitation6_volume(citation.Volume);
			setCitation6_year("" + citation.Year);
		}
			break;
		default:
			break;
		}
	}

	private HeadnoteAndHeld getEmptyHeadnoteAndHeld() {
		HeadnoteAndHeld hh = new HeadnoteAndHeld();
		hh.Keycode = keycode;
		hh.Headnote = "";
		hh.Held = "";
		return hh;
	}

	// private Citation getEmptyCitation(){
	// Citation cit=new Citation();
	//
	// }
	private List<HeadnoteAndHeld> getHeadnoteAndHeld() {
		List<HeadnoteAndHeld> hhList = new ArrayList<HeadnoteAndHeld>();

		if (!Util.isStringNullOrEmpty(getHeadnote1())
				|| !Util.isStringNullOrEmpty(getHeld1())) {
			HeadnoteAndHeld hh = getEmptyHeadnoteAndHeld();
			if (!Util.isStringNullOrEmpty(getHeadnote1()))
				hh.Headnote = getHeadnote1();
			if (!Util.isStringNullOrEmpty(getHeld1()))
				hh.Held = getHeld1();
			hhList.add(hh);
		}
		if (!Util.isStringNullOrEmpty(getHeadnote2())
				|| !Util.isStringNullOrEmpty(getHeld2())) {
			HeadnoteAndHeld hh = getEmptyHeadnoteAndHeld();
			if (!Util.isStringNullOrEmpty(getHeadnote2()))
				hh.Headnote = getHeadnote2();
			if (!Util.isStringNullOrEmpty(getHeld2()))
				hh.Held = getHeld2();
			hhList.add(hh);
		}
		if (!Util.isStringNullOrEmpty(getHeadnote3())
				|| !Util.isStringNullOrEmpty(getHeld3())) {
			HeadnoteAndHeld hh = getEmptyHeadnoteAndHeld();
			if (!Util.isStringNullOrEmpty(getHeadnote3()))
				hh.Headnote = getHeadnote3();
			if (!Util.isStringNullOrEmpty(getHeld3()))
				hh.Held = getHeld3();
			hhList.add(hh);
		}
		if (!Util.isStringNullOrEmpty(getHeadnote4())
				|| !Util.isStringNullOrEmpty(getHeld4())) {
			HeadnoteAndHeld hh = getEmptyHeadnoteAndHeld();
			if (!Util.isStringNullOrEmpty(getHeadnote4()))
				hh.Headnote = getHeadnote4();
			if (!Util.isStringNullOrEmpty(getHeld4()))
				hh.Held = getHeld4();
			hhList.add(hh);
		}
		return hhList;
	}

	private List<Citation> getCitations() {
		List<Citation> citationList = new ArrayList<Citation>();
		if (!Util.isStringNullOrEmpty(getCitation1_journal())) {
			Citation citation = new Citation();
			citation.Journal = getCitation1_journal();
			citation.keycode = keycode;
			if (!Util.isStringNullOrEmpty(getCitation1_page()))
				citation.Page = Integer.parseInt(getCitation1_page().trim());
			else
				citation.Page = 0;
			if (!Util.isStringNullOrEmpty(getCitation1_year()))
				citation.Year = Integer.parseInt(getCitation1_year().trim());
			else
				citation.Year = 0;
			if (!Util.isStringNullOrEmpty(getCitation1_volume()))
				citation.Volume = getCitation1_volume().trim();
			else
				citation.Volume = "0";
			citationList.add(citation);
		}
		if (!Util.isStringNullOrEmpty(getCitation2_journal())) {
			Citation citation = new Citation();
			citation.Journal = getCitation2_journal();
			citation.keycode = keycode;
			if (!Util.isStringNullOrEmpty(getCitation2_page()))
				citation.Page = Integer.parseInt(getCitation2_page().trim());
			else
				citation.Page = 0;
			if (!Util.isStringNullOrEmpty(getCitation2_year()))
				citation.Year = Integer.parseInt(getCitation2_year().trim());
			else
				citation.Year = 0;
			if (!Util.isStringNullOrEmpty(getCitation2_volume()))
				citation.Volume = getCitation2_volume().trim();
			else
				citation.Volume = "0";
			citationList.add(citation);
		}
		if (!Util.isStringNullOrEmpty(getCitation3_journal())) {
			Citation citation = new Citation();
			citation.Journal = getCitation3_journal();
			citation.keycode = keycode;
			if (!Util.isStringNullOrEmpty(getCitation3_page()))
				citation.Page = Integer.parseInt(getCitation3_page().trim());
			else
				citation.Page = 0;
			if (!Util.isStringNullOrEmpty(getCitation3_year()))
				citation.Year = Integer.parseInt(getCitation3_year().trim());
			else
				citation.Year = 0;
			if (!Util.isStringNullOrEmpty(getCitation3_volume()))
				citation.Volume = getCitation3_volume().trim();
			else
				citation.Volume = "0";
			citationList.add(citation);
		}
		if (!Util.isStringNullOrEmpty(getCitation4_journal())) {
			Citation citation = new Citation();
			citation.Journal = getCitation4_journal();
			citation.keycode = keycode;
			if (!Util.isStringNullOrEmpty(getCitation4_page()))
				citation.Page = Integer.parseInt(getCitation4_page().trim());
			else
				citation.Page = 0;
			if (!Util.isStringNullOrEmpty(getCitation4_year()))
				citation.Year = Integer.parseInt(getCitation4_year().trim());
			else
				citation.Year = 0;
			if (!Util.isStringNullOrEmpty(getCitation4_volume()))
				citation.Volume = getCitation4_volume().trim();
			else
				citation.Volume = "0";
			citationList.add(citation);
		}
		if (!Util.isStringNullOrEmpty(getCitation5_journal())) {
			Citation citation = new Citation();
			citation.Journal = getCitation5_journal();
			citation.keycode = keycode;
			if (!Util.isStringNullOrEmpty(getCitation5_page()))
				citation.Page = Integer.parseInt(getCitation5_page().trim());
			else
				citation.Page = 0;
			if (!Util.isStringNullOrEmpty(getCitation5_year()))
				citation.Year = Integer.parseInt(getCitation5_year().trim());
			else
				citation.Year = 0;
			if (!Util.isStringNullOrEmpty(getCitation5_volume()))
				citation.Volume = getCitation5_volume().trim();
			else
				citation.Volume = "0";
			citationList.add(citation);
		}
		if (!Util.isStringNullOrEmpty(getCitation6_journal())) {
			Citation citation = new Citation();
			citation.Journal = getCitation6_journal();
			citation.keycode = keycode;
			if (!Util.isStringNullOrEmpty(getCitation6_page()))
				citation.Page = Integer.parseInt(getCitation6_page().trim());
			else
				citation.Page = 0;
			if (!Util.isStringNullOrEmpty(getCitation6_year()))
				citation.Year = Integer.parseInt(getCitation6_year().trim());
			else
				citation.Year = 0;
			if (!Util.isStringNullOrEmpty(getCitation6_volume()))
				citation.Volume = getCitation6_volume().trim();
			else
				citation.Volume = "0";
			citationList.add(citation);
		}
		return citationList;
	}

	private Judgement getEmptyJudgement() {
		Judgement j = new Judgement();
		j.Appellant = "";
		j.Respondant = "";
		j.Bench = 0;
		j.Advocates = "";
		Calendar cal = Calendar.getInstance();
		cal.set(1111, 10, 11);
		j.CaseDate = cal.getTime();
		j.CaseNumber = "";
		j.Court = "";
		j.CasesReferred = "";
		j.Citations = new ArrayList<Citation>();
		j.FullText = "";
		j.headnotesAndHelds = new ArrayList<HeadnoteAndHeld>();
		j.Judges = "";
		return j;

	}

	public boolean updateJudgement() {
		Judgement judgement = getEmptyJudgement();
		judgement.Keycode = keycode;
		if (!Util.isStringNullOrEmpty(getAppellant()))
			judgement.Appellant = getAppellant();
		if (!Util.isStringNullOrEmpty(getCaseNumber()))
			judgement.CaseNumber = getCaseNumber();
		if (!Util.isStringNullOrEmpty(getRespondant()))
			judgement.Respondant = getRespondant();
		if (!Util.isStringNullOrEmpty(getJudges()))
			judgement.Judges = getJudges();
		if (!Util.isStringNullOrEmpty(getAdvocates()))
			judgement.Advocates = getAdvocates();
		if (!Util.isStringNullOrEmpty(getCasesReferred()))
			judgement.CasesReferred = getCasesReferred();
		if (!Util.isStringNullOrEmpty(getJudgementText()))
			judgement.FullText = getJudgementText();
		if (getCaseDate() != null)
			judgement.CaseDate = getCaseDate();
		judgement.Citations = getCitations();
		judgement.headnotesAndHelds = getHeadnoteAndHeld();
		UpdateJudgement upInstance = new UpdateJudgement(SelectedCourt
				.getInstance().getSelectedDatabaseName(), "localhost", "root",
				"");
		if (upInstance.connectToDatabse()) {
			upInstance.deleteJudgement(keycode);
			upInstance.updateJudgement(judgement);
			return true;
		} else
			return false;
	}

	private void setHeadnoteAndHeld(int index, HeadnoteAndHeld headnoteAndHeld) {
		switch (index) {
		case 0: {
			setHeadnote1(headnoteAndHeld.Headnote);
			setHeld1(headnoteAndHeld.Held);
		}
			break;
		case 1: {
			setHeadnote2(headnoteAndHeld.Headnote);
			setHeld2(headnoteAndHeld.Held);
		}
			break;
		case 2: {
			setHeadnote3(headnoteAndHeld.Headnote);
			setHeld3(headnoteAndHeld.Held);
		}
			break;
		case 3: {
			setHeadnote4(headnoteAndHeld.Headnote);
			setHeld4(headnoteAndHeld.Held);
		}
			break;
		default:
			break;
		}
	}

	private void getJudgementFromdatabase() {
		ReadJudgement ins = new ReadJudgement(SelectedCourt.getInstance()
				.getSelectedDatabaseName(), "localhost", "root", "");
		if (ins.connectToDatabse()) {
			this.judgement = ins.getJudgement(keycode);
		}

	}

	public List<String> getJournals() {
		return journals;
	}

	public void setJournals(List<String> journals) {
		this.journals = journals;
	}

	public String getCitation1_volume() {
		return citation1_volume;
	}

	public void setCitation1_volume(String citation1_volume) {
		propertyChangeSupport
				.firePropertyChange("citation1_volume", this.citation1_volume,
						this.citation1_volume = citation1_volume);
	}

	public String getCitation2_volume() {
		return citation2_volume;
	}

	public void setCitation2_volume(String citation2_volume) {
		propertyChangeSupport
				.firePropertyChange("citation2_volume", this.citation2_volume,
						this.citation2_volume = citation2_volume);
	}

	public String getCitation3_volume() {
		return citation3_volume;
	}

	public void setCitation3_volume(String citation3_volume) {
		propertyChangeSupport
				.firePropertyChange("citation3_volume", this.citation3_volume,
						this.citation3_volume = citation3_volume);
	}

	public String getCitation4_volume() {
		return citation4_volume;
	}

	public void setCitation4_volume(String citation4_volume) {
		propertyChangeSupport
				.firePropertyChange("citation4_volume", this.citation4_volume,
						this.citation4_volume = citation4_volume);
	}

	public String getCitation5_volume() {
		return citation5_volume;
	}

	public void setCitation5_volume(String citation5_volume) {
		propertyChangeSupport
				.firePropertyChange("citation5_volume", this.citation5_volume,
						this.citation5_volume = citation5_volume);
	}

	public String getCitation6_volume() {
		return citation6_volume;
	}

	public void setCitation6_volume(String citation6_volume) {
		propertyChangeSupport
				.firePropertyChange("citation6_volume", this.citation6_volume,
						this.citation6_volume = citation6_volume);
	}

	public String getCitation1_page() {
		return citation1_page;
	}

	public void setCitation1_page(String citation1_page) {
		propertyChangeSupport.firePropertyChange("citation1_page",
				this.citation1_page, this.citation1_page = citation1_page);
	}

	public String getCitation2_page() {
		return citation2_page;
	}

	public void setCitation2_page(String citation2_page) {
		propertyChangeSupport.firePropertyChange("citation2_page",
				this.citation2_page, this.citation2_page = citation2_page);
	}

	public String getCitation3_page() {
		return citation3_page;
	}

	public void setCitation3_page(String citation3_page) {
		propertyChangeSupport.firePropertyChange("citation3_page",
				this.citation3_page, this.citation3_page = citation3_page);
	}

	public String getCitation4_page() {
		return citation4_page;
	}

	public void setCitation4_page(String citation4_page) {
		propertyChangeSupport.firePropertyChange("citation4_page",
				this.citation4_page, this.citation4_page = citation4_page);
	}

	public String getCitation5_page() {
		return citation5_page;
	}

	public void setCitation5_page(String citation5_page) {
		propertyChangeSupport.firePropertyChange("citation5_page",
				this.citation5_page, this.citation5_page = citation5_page);
	}

	public String getCitation1_year() {
		return citation1_year;
	}

	public void setCitation1_year(String citation1_year) {
		propertyChangeSupport.firePropertyChange("citation1_year",
				this.citation1_year, this.citation1_year = citation1_year);
	}

	public String getCitation6_page() {
		return citation6_page;
	}

	public void setCitation6_page(String citation6_page) {
		propertyChangeSupport.firePropertyChange("citation6_page",
				this.citation6_page, this.citation6_page = citation6_page);
	}

	public String getCitation2_year() {
		return citation2_year;
	}

	public void setCitation2_year(String citation2_year) {
		propertyChangeSupport.firePropertyChange("citation2_year",
				this.citation2_year, this.citation2_year = citation2_year);
	}

	public String getCitation3_year() {
		return citation3_year;
	}

	public void setCitation3_year(String citation3_year) {
		propertyChangeSupport.firePropertyChange("citation3_year",
				this.citation3_year, this.citation3_year = citation3_year);
	}

	public String getCitation4_year() {
		return citation4_year;
	}

	public void setCitation4_year(String citation4_year) {
		propertyChangeSupport.firePropertyChange("citation4_year",
				this.citation4_year, this.citation4_year = citation4_year);
	}

	public String getCitation5_year() {
		return citation5_year;
	}

	public void setCitation5_year(String citation5_year) {
		propertyChangeSupport.firePropertyChange("citation5_year",
				this.citation5_year, this.citation5_year = citation5_year);
	}

	public String getCitation6_year() {
		return citation6_year;
	}

	public void setCitation6_year(String citation6_year) {
		propertyChangeSupport.firePropertyChange("citation6_year",
				this.citation6_year, this.citation6_year = citation6_year);
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

	public String getCitation1_journal() {
		return citation1_journal;
	}

	public void setCitation1_journal(String citation1_journal) {
		propertyChangeSupport.firePropertyChange("citation1_journal",
				this.citation1_journal,
				this.citation1_journal = citation1_journal);
	}

	public String getCitation2_journal() {
		return citation2_journal;
	}

	public void setCitation2_journal(String citation2_journal) {
		propertyChangeSupport.firePropertyChange("citation2_journal",
				this.citation2_journal,
				this.citation2_journal = citation2_journal);
	}

	public String getCitation3_journal() {
		return citation3_journal;
	}

	public void setCitation3_journal(String citation3_journal) {
		propertyChangeSupport.firePropertyChange("citation3_journal",
				this.citation3_journal,
				this.citation3_journal = citation3_journal);
	}

	public String getCitation4_journal() {
		return citation4_journal;
	}

	public void setCitation4_journal(String citation4_journal) {
		propertyChangeSupport.firePropertyChange("citation4_journal",
				this.citation4_journal,
				this.citation4_journal = citation4_journal);
	}

	public String getCitation5_journal() {
		return citation5_journal;
	}

	public void setCitation5_journal(String citation5_journal) {
		propertyChangeSupport.firePropertyChange("citation5_journal",
				this.citation5_journal,
				this.citation5_journal = citation5_journal);
	}

	public String getCitation6_journal() {
		return citation6_journal;
	}

	public void setCitation6_journal(String citation6_journal) {
		propertyChangeSupport.firePropertyChange("citation6_journal",
				this.citation6_journal,
				this.citation6_journal = citation6_journal);
	}

	public String getAppellant() {
		return appellant;
	}

	public void setAppellant(String appellant) {
		propertyChangeSupport.firePropertyChange("appellant", this.appellant,
				this.appellant = appellant);
	}

	public String getRespondant() {
		return respondant;
	}

	public void setRespondant(String respondant) {
		propertyChangeSupport.firePropertyChange("respondant", this.respondant,
				this.respondant = respondant);
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		propertyChangeSupport.firePropertyChange("caseNumber", this.caseNumber,
				this.caseNumber = caseNumber);
	}

	public String getAdvocates() {
		return advocates;
	}

	public void setAdvocates(String advocates) {
		propertyChangeSupport.firePropertyChange("advocates", this.advocates,
				this.advocates = advocates);
	}

	public String getJudges() {
		return judges;
	}

	public void setJudges(String judges) {
		propertyChangeSupport.firePropertyChange("judges", this.judges,
				this.judges = judges);
	}

	public Date getCaseDate() {
		return caseDate;
	}

	public void setCaseDate(Date caseDate) {
		propertyChangeSupport.firePropertyChange("caseDate", this.caseDate,
				this.caseDate = caseDate);
	}

	public String getJudgementText() {
		return judgementText;
	}

	public void setJudgementText(String judgementText) {
		propertyChangeSupport.firePropertyChange("judgementText",
				this.judgementText, this.judgementText = judgementText);
	}

	public String getHeld1() {
		return held1;
	}

	public void setHeld1(String held1) {
		propertyChangeSupport.firePropertyChange("held1", this.held1,
				this.held1 = held1);
	}

	public String getHeld2() {
		return held2;
	}

	public void setHeld2(String held2) {
		propertyChangeSupport.firePropertyChange("held2", this.held2,
				this.held2 = held2);
	}

	public String getHeld3() {
		return held3;
	}

	public void setHeld3(String held3) {
		propertyChangeSupport.firePropertyChange("held3", this.held3,
				this.held3 = held3);
	}

	public String getHeld4() {
		return held4;
	}

	public void setHeld4(String held4) {
		propertyChangeSupport.firePropertyChange("held4", this.held4,
				this.held4 = held4);
	}

	public String getHeadnote1() {
		return headnote1;
	}

	public void setHeadnote1(String headnote1) {
		propertyChangeSupport.firePropertyChange("headnote1", this.headnote1,
				this.headnote1 = headnote1);
	}

	public String getHeadnote2() {
		return headnote2;
	}

	public void setHeadnote2(String headnote2) {
		propertyChangeSupport.firePropertyChange("headnote2", this.headnote2,
				this.headnote2 = headnote2);
	}

	public String getHeadnote3() {
		return headnote3;
	}

	public void setHeadnote3(String headnote3) {
		propertyChangeSupport.firePropertyChange("headnote3", this.headnote3,
				this.headnote3 = headnote3);
	}

	public String getHeadnote4() {
		return headnote4;
	}

	public void setHeadnote4(String headnote4) {
		propertyChangeSupport.firePropertyChange("headnote4", this.headnote4,
				this.headnote4 = headnote4);
	}

	public String getCasesReferred() {
		return casesReferred;
	}

	public void setCasesReferred(String casesReferred) {
		propertyChangeSupport.firePropertyChange("casesReferred",
				this.casesReferred, this.casesReferred = casesReferred);
	}

	public void clearCitation1() {
		setCitation1_journal("");
		setCitation1_page("");
		setCitation1_volume("");
		setCitation1_year("");

	}

	public void clearCitation2() {
		setCitation2_journal("");
		setCitation2_page("");
		setCitation2_volume("");
		setCitation2_year("");

	}

	public void clearCitation3() {
		setCitation3_journal("");
		setCitation3_page("");
		setCitation3_volume("");
		setCitation3_year("");

	}

	public void clearCitation4() {
		setCitation4_journal("");
		setCitation4_page("");
		setCitation4_volume("");
		setCitation4_year("");
	}

	public void clearCitation5() {
		setCitation5_journal("");
		setCitation5_page("");
		setCitation5_volume("");
		setCitation5_year("");
	}

	public void clearCitation6() {
		setCitation6_journal("");
		setCitation6_page("");
		setCitation6_volume("");
		setCitation6_year("");

	}

}
