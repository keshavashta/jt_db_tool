package readWriteDatabase;

import indexer.CaseIndexer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import util.Util;

import com.greenapplesolutions.dbloader.domain.Citation;
import com.greenapplesolutions.dbloader.domain.Fields;
import com.greenapplesolutions.dbloader.domain.HeadnoteAndHeld;
import com.greenapplesolutions.dbloader.domain.Judgement;
import com.greenapplesolutions.dbloader.domain.Statue;
import com.greenapplesolutions.lawsearch.config.LuceneConfig;

public class LoadJudgments {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String connectionString;
	private String databaseName;
	private String hostName;
	private String userName;
	private String password;
	private String directoryPath;

	public boolean connectToDatabse() {
		String connectionString = "";

		connectionString = "jdbc:mysql://" + hostName + "/" + databaseName
				+ "?" + "user=" + userName;
		if (this.password != null)
			connectionString += "&password=" + password;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			return false;
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public LoadJudgments(String databaseName, String hostName, String userName,
			String password, String directoryPath) {
		this.databaseName = databaseName;
		this.hostName = hostName;
		this.userName = userName;
		this.password = password;
		connectionString = "jdbc:mysql://" + hostName + "/" + databaseName
				+ "?" + "user=" + userName;
		// Checking password is there or not
		if (!Util.isStringNullOrEmpty(password))
			connectionString += "&password=" + password;
		this.directoryPath = directoryPath;
	}

	public void indexJudgements() {
		Calendar instance = Calendar.getInstance();
		instance.set(1111, 10, 11);
		Date invalidDate = instance.getTime();
		LuceneConfig config = LuceneConfig.INSTANCE();
		config.setIndexPath(directoryPath);
		CaseIndexer caseIndexer = new CaseIndexer();

		String query = "select Keycode,Date,Advocates,Appellant,Respondent,Judges ,CaseNo,Judgement from "
				+ databaseName + ".judgements order by Date";

		// List<Judgement> judgementList = new ArrayList<Judgement>();
		List<Judgement> judgementList = new ArrayList<Judgement>();
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				try {
					Judgement judgement = new Judgement();
					judgement.Advocates = resultSet.getString(Fields.Advocates) == null ? ""
							: resultSet.getString(Fields.Advocates);
					judgement.Appellant = resultSet.getString(Fields.Appellant) == null ? ""
							: resultSet.getString(Fields.Appellant);

					// try {
					// j.Bench = Integer.parseInt(resultSet.getString(
					// Fields.Bench).toString());
					// } catch (Exception ex) {
					// // LELogger.INSTANCE().setError(ex.getMessage());
					// j.Bench = 0;
					// }
					try {
						judgement.CaseDate = (Date) resultSet
								.getDate(Fields.CaseDate);
					} catch (Exception e) {
						judgement.CaseDate = invalidDate;
					}

					judgement.CaseNumber = resultSet
							.getString(Fields.CaseNumber) == null ? ""
							: resultSet.getString(Fields.CaseNumber);

					// j.Court = resultSet.getString(Fields.Court) == null ? ""
					// : resultSet.getString(Fields.Court);
					judgement.FullText = resultSet.getString(Fields.FullText) == null ? ""
							: resultSet.getString(Fields.FullText);
					// j.Headnote = resultSet.getString(Fields.Headnote) == null
					// ? ""
					// : resultSet.getString(Fields.Headnote);
					judgement.Judges = resultSet.getString(Fields.Judges) == null ? ""
							: resultSet.getString(Fields.Judges);
					judgement.Keycode = resultSet.getString(Fields.Keycode);
					judgement.Respondant = resultSet
							.getString(Fields.Respondant) == null ? ""
							: resultSet.getString(Fields.Respondant);

					judgement.Citations = getCitations(judgement.Keycode);
					judgement.headnotesAndHelds = getHeadnoteAndHelds(judgement.Keycode);
					judgementList.add(judgement);
					if (judgementList.size() > 1000) {
						caseIndexer.indexJudgements(judgementList);
						judgementList.clear();
					}

				} catch (Exception e) {
					System.out.println("Error in processing indiv"
							+ e.getMessage());
				}
			}
			if (judgementList.size() > 0) {
				caseIndexer.indexJudgements(judgementList);
				judgementList.clear();
			}
		} catch (Exception e) {
			System.out.println("Error in main" + e.getMessage());
		}
		close();

	}

	private List<Citation> getCitations(String keycode) {
		String query = "select Journal,Year,Volume,Page from " + databaseName
				+ ".citations where Keycode = " + Util.wrapQuotes(keycode);
		List<Citation> citationList = new ArrayList<Citation>();
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				try {
					Citation j = new Citation();
					j.Journal = resultSet.getString(Fields.Journal) == null ? ""
							: resultSet.getString(Fields.Journal);
					j.Volume = resultSet.getString(Fields.Volume) == null ? ""
							: resultSet.getString(Fields.Volume);
					j.Year = Integer
							.parseInt(resultSet.getString(Fields.Year) == null ? "0"
									: resultSet.getString(Fields.Year));
					j.Page = Integer
							.parseInt(resultSet.getString(Fields.Page) == null ? "0"
									: resultSet.getString(Fields.Page));
					citationList.add(j);

				} catch (Exception e) {
					System.out.println("Error in processing indiv"
							+ e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Error in main" + e.getMessage());
		}
		return citationList;
	}

	private List<HeadnoteAndHeld> getHeadnoteAndHelds(String keycode) {
		String query = "select held,headnote from " + databaseName
				+ ".headnotes where Keycode = " + Util.wrapQuotes(keycode);
		List<HeadnoteAndHeld> headnoteList = new ArrayList<HeadnoteAndHeld>();
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				try {
					HeadnoteAndHeld hh = new HeadnoteAndHeld();
					hh.Headnote = resultSet.getString(Fields.Headnote) == null ? ""
							: resultSet.getString(Fields.Headnote);
					hh.Held = resultSet.getString(Fields.Held) == null ? ""
							: resultSet.getString(Fields.Held);

					headnoteList.add(hh);

				} catch (Exception e) {
					System.out.println("Error in processing indiv"
							+ e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Error in main" + e.getMessage());
		}
		return headnoteList;
	}

	private void close() {
		try {

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
