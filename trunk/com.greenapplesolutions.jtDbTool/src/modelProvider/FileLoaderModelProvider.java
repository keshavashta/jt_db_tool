package modelProvider;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;

import util.Util;

import com.greenapplesolutions.dbloader.domain.Citation;
import com.greenapplesolutions.dbloader.domain.HeadnoteAndHeld;
import com.greenapplesolutions.dbloader.domain.Judgement;
import com.ibm.icu.text.SimpleDateFormat;

public class FileLoaderModelProvider {
	private String filePath;
	private Pattern citationPattern = Pattern.compile(
			"^Jt\\s{0,4}\\d{4}\\s{0,4}\\(\\d{0,4}\\)\\s{0,4}\\d{0,4}",
			Pattern.CASE_INSENSITIVE);
	private boolean isCitationProcessed;
	private boolean isPartyProcessed;
	private boolean isDateProcessed;
	private boolean isAdvocateProcessed;
	private Pattern yearPattern = Pattern.compile("\\s\\d[^\\(]*");
	private Pattern volumePattern = Pattern.compile("\\(\\d+\\)");
	private Pattern pagePattern = Pattern.compile("\\d+$");
	private Pattern partyPattern = Pattern.compile(".*v\\..*");
	private Pattern datePattern = Pattern.compile(
			"d.*\\d+\\s{0,4}\\.\\s{0,4}\\d+\\s{0,4}\\.\\s{0,4}\\d+",
			Pattern.CASE_INSENSITIVE);
	private Pattern extractDate = Pattern
			.compile("\\d+\\s{0,4}\\.\\s{0,4}\\d+\\s{0,4}\\.\\s{0,4}\\d+");
	private Pattern advocatesPattern = Pattern.compile("^Appearances");

	public FileLoaderModelProvider(String filePath) {
		this.filePath = filePath;
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileText;

	}

	private int processCitation(String[] textArray, int index,
			Judgement judgment) {
		isCitationProcessed = true;
		Citation citation = new Citation();
		citation.Page = 0;
		citation.Volume = "";
		citation.keycode = "";
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
		return ++index;

	}

	private void reset() {
		isCitationProcessed = false;
		isAdvocateProcessed = false;
		isDateProcessed = false;
		isPartyProcessed = false;
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
		j.CaseNumber = "";
		j.Court = "";
		j.Citations = new ArrayList<Citation>();
		j.FullText = "";
		j.headnotesAndHelds = new ArrayList<HeadnoteAndHeld>();
		j.Judges = "";
		return j;

	}

	private Judgement extractJudgment(String text) {
		Judgement judgment = getEmptyJudgement();
		reset();
		String textArray[] = text.split("\n");

		for (int index = 0; index < textArray.length; ++index) {
			String headnote = textArray[index].trim();
			Matcher citationMatcher = citationPattern.matcher(textArray[index].trim());
			Matcher partyMatcher = partyPattern.matcher(textArray[index]);
			Matcher dateMatcher = datePattern.matcher(textArray[index]);
			Matcher advMatcher = advocatesPattern.matcher(textArray[index]
					.trim());
			if (citationMatcher.find() && !isCitationProcessed)
				index = processCitation(textArray, index, judgment);
			else if (partyMatcher.find() && !isPartyProcessed) {
				index = processParty(textArray, index, judgment);
			} else if (dateMatcher.find() && !isDateProcessed)
				index = processDate(textArray, index, judgment);
			else if (textArray[index].trim().toUpperCase().equals(headnote))
				index = processHeadnote(textArray, index, judgment);
			else if (advMatcher.find() && !isAdvocateProcessed) {
				index = processAdvocates(textArray, index, judgment);
			} else if (textArray[index].trim().endsWith("J.")
					|| textArray[index].trim().startsWith("ORDER"))
				processJudgement(textArray, index, judgment);
		}
		return judgment;
	}

	private void processJudgement(String[] textArray, int index,
			Judgement judgment) {
		for (; index < textArray.length; ++index)
			judgment.FullText = textArray[index];

	}

	private int processAdvocates(String[] textArray, int index,
			Judgement judgment) {
		isAdvocateProcessed = true;
		String adv = "";

		while (true && index < textArray.length - 2) {
			String headnote = textArray[index].trim();
			if (textArray[index].trim().endsWith("J.")
					|| textArray[index].trim().startsWith("ORDER")
					|| headnote.equals(textArray[index].toUpperCase()))
				break;
			adv += textArray[index].trim();
			++index;
		}
		judgment.Advocates = adv.replaceAll("(?i)Appearances", "");
		return index;
	}

	private int processHeadnote(String[] textArray, int index,
			Judgement judgment) {

		HeadnoteAndHeld hh = new HeadnoteAndHeld();
		List<HeadnoteAndHeld> hhList = new ArrayList<HeadnoteAndHeld>();
		hh.Keycode = judgment.Keycode;
		hh.Held = "";
		String headnote = "";
		while (true && index < textArray.length - 2) {
			if (textArray[index].trim().endsWith("J.")
					|| textArray[index].trim().startsWith("ORDER"))
				break;
			headnote += textArray[index].trim();
			++index;
		}
		hh.Headnote = headnote;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return ++index;
	}

	private int processParty(String[] textArray, int index, Judgement judgement) {
		isPartyProcessed = true;
		String party[] = textArray[index].split("(?i)v\\.");
		judgement.Appellant = party[0];
		judgement.Respondant = party[1];
		while (true && index < textArray.length - 1) {
			++index;
			if (textArray[index].trim().equals("J.")) {
				judgement.Judges = textArray[index].trim()
						.replaceAll("j{1,10}\\.$", "").trim()
						.replaceAll(",$", "").trim();
				break;
			}

			judgement.CaseNumber += textArray[index] + "\n";
		}
		if (!Util.isStringNullOrEmpty(judgement.CaseNumber))
			judgement.CaseNumber = judgement.CaseNumber.trim();
		return ++index;
	}

	public List<Judgement> extractJudgments() {
		List<Judgement> judgments = new ArrayList<Judgement>();
		String text = readFile();
		String[] judgementTextArray = text.split("\\*{10}");
		for (String judgementString : judgementTextArray) {
			Judgement judgement = extractJudgment(judgementString);
			if (judgement != null)
				judgments.add(judgement);
		}
		return judgments;
	}

}
