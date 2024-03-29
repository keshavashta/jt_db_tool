package readWriteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import util.JTLogger;
import util.Util;

import com.greenapplesolutions.jtdbtool.domain.Citation;
import com.greenapplesolutions.jtdbtool.domain.Fields;
import com.greenapplesolutions.jtdbtool.domain.HeadnoteAndHeld;
import com.greenapplesolutions.jtdbtool.domain.Judgement;

public class ReadJudgement {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String connectionString;
	private String databaseName;
	private String hostName;
	private String userName;
	private String password;
	private int count;

	public ReadJudgement(String databaseName, String hostName, String userName,
			String password) {
		this.databaseName = databaseName;
		this.hostName = hostName;
		this.userName = userName;
		this.password = password;
	}

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
			JTLogger.getInstance().setInfo(
					"Error in connecting with database" + e.getMessage());
			return false;
		} catch (SQLException e) {
			JTLogger.getInstance().setInfo(
					"Error in connecting with database" + e.getMessage());
			return false;
		}
		return true;
	}

	public Judgement getJudgement(String keycode) {

		Judgement judgement = new Judgement();
		judgement.Citations = new ArrayList<Citation>();
		judgement.headnotesAndHelds = new ArrayList<HeadnoteAndHeld>();
		String query = "select Keycode,Date,Advocates,Appellant,CreatedDate,ModifiedDate,CasesReferred,Respondent,Judges,Judgement ,CaseNo from "
				+ databaseName
				+ ".judgements where Keycode = "
				+ Util.wrapQuotes(keycode);

		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				try {

					judgement.Advocates = resultSet.getString(Fields.Advocates) == null ? ""
							: resultSet.getString(Fields.Advocates);
					judgement.Appellant = resultSet.getString(Fields.Appellant) == null ? ""
							: resultSet.getString(Fields.Appellant);
					judgement.CasesReferred = resultSet
							.getString(Fields.CasesReferred) == null ? ""
							: resultSet.getString(Fields.CasesReferred);

					try {
						judgement.CaseDate = (Date) resultSet
								.getDate(Fields.CaseDate);
					} catch (Exception e) {
						judgement.CaseDate = Util.getDefaultDate();
					}
					try {
						judgement.CreatedDate = (Date) resultSet
								.getDate(Fields.CreatedDate);
					} catch (Exception e) {
						judgement.CreatedDate = Util.getDefaultDate();
					}
					try {
						judgement.ModifiedDate = (Date) resultSet
								.getDate(Fields.ModifiedDate);
					} catch (Exception e) {
						judgement.ModifiedDate = Util.getDefaultDate();
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

					judgement.Citations = getCitations(keycode);
					judgement.headnotesAndHelds = getHeadnoteAndHelds(keycode);

				} catch (Exception e) {
					System.out.println("Error in processing indiv"
							+ e.getMessage());
				}
			}
		} catch (Exception e) {
			JTLogger.getInstance()
					.setError(
							"Error in retrieving judgement, where keycode is "
									+ keycode + " and database name is "
									+ databaseName);
		}
		close();
		return judgement;

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
					JTLogger.getInstance().setError(
							"Error in retrieving single citation where keycode is "
									+ keycode + " and database name is "
									+ databaseName + " due to "
									+ e.getMessage());
				}
			}
		} catch (Exception e) {
			JTLogger.getInstance().setError(
					"Error in retrieving citations where keycode is " + keycode
							+ " and database name is " + databaseName
							+ " due to " + e.getMessage());
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
					JTLogger.getInstance().setError(
							"Error in retrieving single headnote and held where keycode is "
									+ keycode + " and database name is "
									+ databaseName + " due to "
									+ e.getMessage());
				}
			}
		} catch (Exception e) {
			JTLogger.getInstance().setError(
					"Error in retrieving headnotes where keycode is " + keycode
							+ " and database name is " + databaseName
							+ " due to " + e.getMessage());
		}
		return headnoteList;
	}

	public List<Judgement> getJudgements() {
		

		String query = "select Keycode,COURT,Date,Advocates,CasesReferred,CreatedDate,ModifiedDate,Appellant,Respondent,Judges ,CaseNo from "
				+ databaseName
				+ ".judgements where Is_Verified=0 order by Date desc";

		// List<Judgement> judgementList = new ArrayList<Judgement>();
		List<Judgement> judgementList = new ArrayList<Judgement>();
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				try {
					Judgement j = new Judgement();
					j.Advocates = resultSet.getString(Fields.Advocates) == null ? ""
							: resultSet.getString(Fields.Advocates);
					j.Appellant = resultSet.getString(Fields.Appellant) == null ? ""
							: resultSet.getString(Fields.Appellant);
					j.CasesReferred = resultSet.getString(Fields.CasesReferred) == null ? ""
							: resultSet.getString(Fields.CasesReferred);

					// try {
					// j.Bench = Integer.parseInt(resultSet.getString(
					// Fields.Bench).toString());
					// } catch (Exception ex) {
					// // LELogger.INSTANCE().setError(ex.getMessage());
					// j.Bench = 0;
					// }
					j.Court = resultSet.getString(Fields.Court) == null ? ""
							: resultSet.getString(Fields.Court);
					try {
						j.CaseDate = (Date) resultSet.getDate(Fields.CaseDate);
					} catch (Exception e) {
						Util.getDefaultDate();
					}
					try {
						j.CreatedDate = (Date) resultSet
								.getDate(Fields.CreatedDate);
					} catch (Exception e) {
						Util.getDefaultDate();
					}
					try {
						j.ModifiedDate = (Date) resultSet
								.getDate(Fields.ModifiedDate);
					} catch (Exception e) {
						Util.getDefaultDate();
					}

					j.CaseNumber = resultSet.getString(Fields.CaseNumber) == null ? ""
							: resultSet.getString(Fields.CaseNumber);

					// j.Court = resultSet.getString(Fields.Court) == null ? ""
					// : resultSet.getString(Fields.Court);
					// j.FullText = resultSet.getString(Fields.FullText) == null
					// ? ""
					// : resultSet.getString(Fields.FullText);
					// j.Headnote = resultSet.getString(Fields.Headnote) == null
					// ? ""
					// : resultSet.getString(Fields.Headnote);
					j.Judges = resultSet.getString(Fields.Judges) == null ? ""
							: resultSet.getString(Fields.Judges);
					j.Keycode = resultSet.getString(Fields.Keycode);
					j.Respondant = resultSet.getString(Fields.Respondant) == null ? ""
							: resultSet.getString(Fields.Respondant);

					judgementList.add(j);

				} catch (Exception e) {
					System.out.println("Error in processing indiv"
							+ e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Error in main" + e.getMessage());
		}
		close();
		return judgementList;
	}

	public void close() {
		try {

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			JTLogger.getInstance().setError("Error in closing connection");
		}
	}
}
