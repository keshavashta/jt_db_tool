package com.greenapplesolutions.dbloader.domain;

import java.util.Date;
import java.util.List;

import com.greenapplesolutions.dbloader.domain.Citation;

public class Judgement implements Comparable<Judgement> {

	public String Appellant;
	public String Respondant;
	public List<HeadnoteAndHeld> headnotesAndHelds;
	public String FullText;
	public String Court;
	public String Judges;
	public String Advocates;
	public Date CaseDate;
	public String CaseNumber;
	public String Subject;
	public String Keycode;
	public int Bench;
	public String Parties;
	public List<Statue> Statues;
	public List<Citation> Citations;
	public List<ReferredCase> ReferredCases; // TODO: we'll see this later

	public static String DocumentType = "judgement";

	@Override
	public boolean equals(Object obj) {
		return this.Keycode == ((Judgement) obj).Keycode;
	}

	@Override
	public int compareTo(Judgement o) {
		return this.CaseDate.getTime() > o.CaseDate.getTime() ? 1
				: this.CaseDate.getTime() < o.CaseDate.getTime() ? -1 : 0;
	}
}
