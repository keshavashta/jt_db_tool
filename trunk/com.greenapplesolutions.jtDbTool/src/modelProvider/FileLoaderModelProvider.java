package modelProvider;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.Util;

import com.greenapplesolutions.dbloader.domain.Citation;
import com.greenapplesolutions.dbloader.domain.Judgement;

public class FileLoaderModelProvider {
	private String filePath;
	private Pattern citationPattern = Pattern.compile(
			"Jt\\s{0,4}\\d{4}\\s{0,4}\\(\\d{0,4}\\)\\s{0,4}\\d{0,4}",
			Pattern.CASE_INSENSITIVE);
	private boolean isCitationProcessed;
	private boolean isPartyProcessed;
	private boolean isDateProcessed;
	private Pattern yearPattern = Pattern.compile("\\s\\d[^\\(]*");
	private Pattern volumePattern = Pattern.compile("\\(\\d+\\)");
	private Pattern pagePattern = Pattern.compile("\\d+$");
	private Pattern partyPattern = Pattern.compile(".*v\\..*");
	private Pattern datePattern = Pattern.compile(
			"d.*\\d+\\s{0,4}\\.\\s{0,4}\\d+\\s{0,4}\\.\\s{0,4}\\d+",
			Pattern.CASE_INSENSITIVE);
	private Pattern extractDate = Pattern
			.compile("\\d+\\s{0,4}\\.\\s{0,4}\\d+\\s{0,4}\\.\\s{0,4}\\d+");

	public FileLoaderModelProvider() {
		filePath = "JT 2012 (6) SC 1";
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
			citation.Volume = volMatcher.group();
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
	}

	private Judgement extractJudgment(String text) {
		Judgement judgment = new Judgement();
		reset();
		String textArray[] = text.split("\n");

		for (int index = 0; index < textArray.length; ++index) {
			Matcher citationMatcher = citationPattern.matcher(textArray[index]);
			Matcher partyMatcher = partyPattern.matcher(textArray[index]);
			Matcher dateMatcher = datePattern.matcher(textArray[index]);
			if (citationMatcher.find() && !isCitationProcessed)
				index = processCitation(textArray, index, judgment);
			else if (partyMatcher.find() && !isPartyProcessed) {
				index = processParty(textArray, index, judgment);
			} else if (dateMatcher.find() && !isDateProcessed)
				index = processDate(textArray, index, judgment);

		}
		return null;
	}

	private int processDate(String textArray[], int index, Judgement judgment) {
		return 0;
	}

	private int processParty(String[] textArray, int index, Judgement judgement) {
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
		String[] judgementTextArray = text.split("**********");
		for (String judgementString : judgementTextArray) {
			Judgement judgement = extractJudgment(judgementString);
			if (judgement != null)
				judgments.add(judgement);
		}
		return judgments;
	}

}
