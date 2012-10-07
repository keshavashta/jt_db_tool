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

import modelProvider.IndexProgressbarDialogModelProvider;

import util.JTLogger;
import util.Util;

import com.greenapplesolutions.dbloader.domain.Citation;
import com.greenapplesolutions.dbloader.domain.Fields;
import com.greenapplesolutions.dbloader.domain.HeadnoteAndHeld;
import com.greenapplesolutions.dbloader.domain.Judgement;
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
	private Connection citationConnection;

	public boolean connectToDatabse() {
		String connectionString = "";

		connectionString = "jdbc:mysql://" + hostName + "/" + databaseName
				+ "?" + "user=" + userName;
		if (this.password != null)
			connectionString += "&password=" + password;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(connectionString);
			getCitationConnection();
		} catch (ClassNotFoundException e) {
			
			return false;
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	private Connection getCitationConnection() {
		String connectionString = "";

		connectionString = "jdbc:mysql://" + hostName + "/" + databaseName
				+ "?" + "user=" + userName;
		if (this.password != null)
			connectionString += "&password=" + password;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			citationConnection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			JTLogger.getInstance().setError(
					"Error in connecting with db while iterating citations or headnotes"
							+ e.getMessage());

		} catch (SQLException e) {
			JTLogger.getInstance().setError(
					"Error in connecting with db while iterating citations or headnotes"
							+ e.getMessage());
		}
		return citationConnection;
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

	public void indexJudgements(IndexProgressbarDialogModelProvider ipdmInstance) {
		JTLogger.getInstance().setInfo("Starting Time" + new Date());
		int count = 0;
		try {
			Statement st = connect.createStatement();
			ResultSet res = st
					.executeQuery("SELECT COUNT(*) FROM judgements where is_verified = true");

			while (res.next()) {
				count = res.getInt(1);
			}
		} catch (SQLException e1) {
			JTLogger.getInstance().setError(
					"Error in getting total number of judgements from database : "
							+ databaseName);
		}
		Calendar instance = Calendar.getInstance();
		instance.set(1111, 10, 11);
		Date invalidDate = instance.getTime();
		ipdmInstance.setLogMessage(ipdmInstance.getLogMessage()
				+ "\n Judgements found in " + databaseName + count);
		LuceneConfig config = LuceneConfig.INSTANCE();

		config.setIndexPath(directoryPath);
		CaseIndexer caseIndexer = new CaseIndexer();

		String query = "select Keycode,Date,Advocates,Appellant,Respondent,Judges ,COURT,CasesReferred,CaseNo,Judgement from "
				+ databaseName
				+ ".judgements where is_verified = true order by Date";
		int judgementsLeft = count;
		// List<Judgement> judgementList = new ArrayList<Judgement>();
		List<Judgement> judgementList = new ArrayList<Judgement>();
		try {
			statement = connect.createStatement(
					java.sql.ResultSet.TYPE_FORWARD_ONLY,
					java.sql.ResultSet.CONCUR_READ_ONLY,
					ResultSet.FETCH_FORWARD);
			statement.setFetchSize(Integer.MIN_VALUE);
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				try {
					Judgement judgement = new Judgement();
					judgement.Advocates = resultSet.getString(Fields.Advocates) == null ? ""
							: resultSet.getString(Fields.Advocates);
					judgement.CasesReferred = resultSet
							.getString(Fields.CasesReferred) == null ? ""
							: resultSet.getString(Fields.CasesReferred);
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

					judgement.Court = resultSet.getString(Fields.Court) == null ? ""
							: resultSet.getString(Fields.Court);
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
					if (judgementList.size() > 199) {
						judgementsLeft = judgementsLeft - judgementList.size();

						ipdmInstance.setLogMessage(ipdmInstance.getLogMessage()
								+ "\n Judgements left to read is: "
								+ judgementsLeft);
						JTLogger.getInstance().setInfo(
								"Memory before index utilized is  :"
										+ Runtime.getRuntime().totalMemory());
						caseIndexer.indexJudgements(judgementList);
						JTLogger.getInstance().setInfo(
								"Memory before clear utilized is  :"
										+ Runtime.getRuntime().totalMemory());
						judgementList.clear();
						JTLogger.getInstance().setInfo(
								"Memory utilized is  :"
										+ Runtime.getRuntime().totalMemory());
						System.gc();
						JTLogger.getInstance().setInfo(
								"Memory utilized is after gc  :"
										+ Runtime.getRuntime().totalMemory());
					}

				} catch (Exception e) {
					JTLogger.getInstance().setError(
							"Error in indexing judgements due to "
									+ e.getMessage());
				}
			}

			if (judgementList.size() > 0) {
				JTLogger.getInstance().setInfo(
						"Memory before index utilized is  :"
								+ Runtime.getRuntime().totalMemory());
				judgementsLeft = judgementsLeft - judgementList.size();
				ipdmInstance.setLogMessage(ipdmInstance.getLogMessage()
						+ "\n Judgements left to read is: " + judgementsLeft);
				caseIndexer.indexJudgements(judgementList);
				JTLogger.getInstance().setInfo(
						"Memory before clear utilized is  :"
								+ Runtime.getRuntime().totalMemory());
				judgementList.clear();
				JTLogger.getInstance().setInfo(
						"Memory utilized is  :"
								+ Runtime.getRuntime().totalMemory());
				System.gc();
				JTLogger.getInstance().setInfo(
						"Memory utilized is  after gc:"
								+ Runtime.getRuntime().totalMemory());
			}
		} catch (Exception e) {
			JTLogger.getInstance().setError(
					"Error in indexing judgements(outer loop) ,due to "
							+ e.getMessage());
		}
		JTLogger.getInstance().setInfo("End time:" + new Date());
		close();
		closeCitationConnection();
	}

	public void getCount() {

		int count = 0;
		try {
			Statement st = connect.createStatement();
			ResultSet res = st
					.executeQuery("SELECT COUNT(*) FROM test where cdate>2012-10-01");

			while (res.next()) {
				count = res.getInt(1);
			}
			System.out.println(count);
		} catch (SQLException e1) {
			JTLogger.getInstance().setError(
					"Error in getting total number of judgements from database : "
							+ databaseName);
		}
	}

	public void indexPartialJudgements(
			IndexProgressbarDialogModelProvider ipdmInstance, String fromDate) {
		System.gc();
		int count = 0;
		try {
			Statement st = connect.createStatement();
			ResultSet res = st
					.executeQuery("SELECT COUNT(*) FROM judgements where is_verified = true and ModifiedDate >= "
							+ util.Util.wrapQuotes(fromDate));

			while (res.next()) {
				count = res.getInt(1);
			}
		} catch (SQLException e1) {
			JTLogger.getInstance().setError(
					"Error in getting total number of judgements from database : "
							+ databaseName);
		}
		Calendar instance = Calendar.getInstance();
		instance.set(1111, 10, 11);
		Date invalidDate = instance.getTime();
		ipdmInstance.setLogMessage(ipdmInstance.getLogMessage()
				+ "\n Judgements found in " + databaseName + count);
		LuceneConfig config = LuceneConfig.INSTANCE();

		config.setIndexPath(directoryPath);
		CaseIndexer caseIndexer = new CaseIndexer();

		String query = "select Keycode,Date,Advocates,Appellant,Respondent,Judges ,COURT,CasesReferred,CaseNo,Judgement from "
				+ databaseName
				+ ".judgements where is_verified = true and ModifiedDate>="
				+ util.Util.wrapQuotes(fromDate) + "order by Date";
		int judgementsLeft = count;
		// List<Judgement> judgementList = new ArrayList<Judgement>();
		List<Judgement> judgementList = new ArrayList<Judgement>();
		try {
			statement = connect.createStatement(
					java.sql.ResultSet.TYPE_FORWARD_ONLY,
					java.sql.ResultSet.CONCUR_READ_ONLY,
					ResultSet.FETCH_FORWARD);
			statement.setFetchSize(Integer.MIN_VALUE);
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				try {
					Judgement judgement = new Judgement();
					judgement.Advocates = resultSet.getString(Fields.Advocates) == null ? ""
							: resultSet.getString(Fields.Advocates);
					judgement.CasesReferred = resultSet
							.getString(Fields.CasesReferred) == null ? ""
							: resultSet.getString(Fields.CasesReferred);
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

					judgement.Court = resultSet.getString(Fields.Court) == null ? ""
							: resultSet.getString(Fields.Court);
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
					if (judgementList.size() > 199) {
						judgementsLeft = judgementsLeft - judgementList.size();

						ipdmInstance.setLogMessage(ipdmInstance.getLogMessage()
								+ "\n Judgements left to read is: "
								+ judgementsLeft);
						JTLogger.getInstance().setInfo(
								"Memory before index utilized is  :"
										+ Runtime.getRuntime().totalMemory());
						caseIndexer.indexJudgements(judgementList);
						JTLogger.getInstance().setInfo(
								"Memory before clear utilized is  :"
										+ Runtime.getRuntime().totalMemory());
						judgementList.clear();
						JTLogger.getInstance().setInfo(
								"Memory utilized is  :"
										+ Runtime.getRuntime().totalMemory());
						System.gc();
						JTLogger.getInstance().setInfo(
								"Memory utilized is after gc  :"
										+ Runtime.getRuntime().totalMemory());
					}

				} catch (Exception e) {
					JTLogger.getInstance().setError(
							"Error in indexing judgements due to "
									+ e.getMessage());
				}
			}
			resultSet.close();
			if (judgementList.size() > 0) {
				JTLogger.getInstance().setInfo(
						"Memory before index utilized is  :"
								+ Runtime.getRuntime().totalMemory());
				judgementsLeft = judgementsLeft - judgementList.size();
				ipdmInstance.setLogMessage(ipdmInstance.getLogMessage()
						+ "\n Judgements left to read is: " + judgementsLeft);
				caseIndexer.indexJudgements(judgementList);
				JTLogger.getInstance().setInfo(
						"Memory before clear utilized is  :"
								+ Runtime.getRuntime().totalMemory());
				judgementList.clear();
				JTLogger.getInstance().setInfo(
						"Memory utilized is  :"
								+ Runtime.getRuntime().totalMemory());
				System.gc();
				JTLogger.getInstance().setInfo(
						"Memory utilized is  after gc:"
								+ Runtime.getRuntime().totalMemory());
			}
		} catch (Exception e) {
			JTLogger.getInstance().setError(
					"Error in indexing judgements(outer loop) ,due to "
							+ e.getMessage());
		}
		close();
	}

	private List<Citation> getCitations(String keycode) {
		String query = "select Journal,Year,Volume,Page from " + databaseName
				+ ".citations where Keycode = " + Util.wrapQuotes(keycode);
		List<Citation> citationList = new ArrayList<Citation>();
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connect = citationConnection;
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
					j.keycode = keycode;
					citationList.add(j);

				} catch (Exception e) {
					JTLogger.getInstance().setError(
							"Error in retrieving single citation where keycode is "
									+ keycode + ", due to " + e.getMessage());
				}
			}
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (Exception e) {
				JTLogger.getInstance().setError(
						"Error in closing connection" + ", due to "
								+ e.getMessage());
			}

		} catch (Exception e) {
			JTLogger.getInstance().setError(
					"Error in retrieving citations where keycode is " + keycode
							+ ", due to " + e.getMessage());
		}
		return citationList;
	}

	private List<HeadnoteAndHeld> getHeadnoteAndHelds(String keycode) {
		String query = "select held,headnote from " + databaseName
				+ ".headnotes where Keycode = " + Util.wrapQuotes(keycode);
		List<HeadnoteAndHeld> headnoteList = new ArrayList<HeadnoteAndHeld>();
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connect = citationConnection;
			statement = connect.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				try {
					HeadnoteAndHeld hh = new HeadnoteAndHeld();
					hh.Headnote = resultSet.getString(Fields.Headnote) == null ? ""
							: resultSet.getString(Fields.Headnote);
					hh.Held = resultSet.getString(Fields.Held) == null ? ""
							: resultSet.getString(Fields.Held);
					hh.Keycode = keycode;

					headnoteList.add(hh);

				} catch (Exception e) {
					JTLogger.getInstance().setError(
							"Error in retrieving single Headnote and held where keycode is "
									+ keycode + ", due to " + e.getMessage());
				}
			}
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}

			} catch (Exception e) {
				JTLogger.getInstance().setError(
						"Error in closing connection" + ", due to "
								+ e.getMessage());
			}
		} catch (Exception e) {
			JTLogger.getInstance().setError(
					"Error in retrieving Headnotes where keycode is " + keycode
							+ ", due to " + e.getMessage());
		}
		return headnoteList;
	}

	private void closeCitationConnection() {
		if (citationConnection != null)
			try {
				citationConnection.close();
			} catch (SQLException e) {
				JTLogger.getInstance().setError(
						"Error in closing connection" + ", due to "
								+ e.getMessage());
			}
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
			JTLogger.getInstance().setError(
					"Error in closing connection" + ", due to "
							+ e.getMessage());
		}
	}
}
