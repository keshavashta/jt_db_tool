package com.greenapplesolutions.dbloader.domain;

import java.util.ArrayList;
import java.util.List;

public final class Fields {
	// The fields starting with U are those fields which are added but are not
	// analyzed they are added just for providing auto-complete functionality

	public static String Court = "COURT";
	public static String Judges = "Judges";
	public static String Advocates = "Advocates";
	public static String Appellant = "Appellant";
	public static String Respondant = "Respondent";
	public static String CaseDate = "Date";
	public static String CaseNumber = "CaseNo";
	public static String Subject = "Subject";
	public static String Headnote = "Headnote";
	public static String CHeadnote = "CHeadnote";
	public static String CHeld = "CHeld";
	public static String FullText = "Judgement";
	public static String CFullText = "CJudgement";
	public static String Keycode = "Keycode";
	public static String Bench = "Bench";
	public static String Citations = "FullEquivicit";
	public static String ReferredKeyCode = "ReferredCase";
	public static String ReferredStatus = "status";
	public static String Held="Held";
	
	public static String UAppellant = "UAppellant";
	public static String URespondant = "URespondent";
	public static String UJudge = "UJudge";
	public static String UAdvocate = "UAdvocate";
	public static String UCourt = "UCourt";
	public static String Parties = "Parties";

	// citation
	public static String Journal = "Journal";
	public static String Year = "Year";
	public static String Volume = "Volume";
	public static String Page = "Page";
	public static String UJournal = "UJournal";
	public static String UVolume = "UVolume";

	// act
	public static String ActName = "actreferred";
	public static String ClauseName = "TypeNo";
	public static String UActName = "UActName";
	public static String UClauseName = "UClauseName";
	public static String ActYear = "ActYear";

	public static String DocumentType = "DocumentType";

	public static String SearchParam = "SearchParam";
	// Bookmarks
	public static String Folder = "Folder";
	public static String Party = "Party";
	public static String UFolder = "UFolder";
	public static String UParty = "UParty";
	// Dictionary
	public static String Keyword = "Keyword";
	public static String Description = "Description";

	public static List<String> getAllFields() {
		List<String> fields = new ArrayList<String>();
		fields.add(Court);
		fields.add(Judges);
		fields.add(Advocates);
		fields.add(Appellant);
		fields.add(Respondant);
		fields.add(CaseDate);
		fields.add(CaseNumber);
		fields.add(Subject);
		fields.add(Headnote);
		fields.add(CHeadnote);
		fields.add(FullText);
		fields.add(CFullText);
		fields.add(Keycode);
		fields.add(Bench);
		fields.add(Citations);
		fields.add(Parties);
		return fields;
	}

	public static List<String> getReferredJudgementFields() {
		List<String> fields = new ArrayList<String>();
		fields.add(Keycode);
		fields.add(ReferredKeyCode);
		fields.add(ReferredStatus);
		return fields;
	}

	public static List<String> getCitationFields() {
		List<String> fields = new ArrayList<String>();
		fields.add(Journal);
		fields.add(Year);
		fields.add(Volume);
		fields.add(Keycode);
		fields.add(Page);
		return fields;
	}

	public static List<String> getActsFields() {
		List<String> fields = new ArrayList<String>();
		fields.add(ActName);
		fields.add(ClauseName);
		fields.add(Keycode);
		return fields;
	}

}
