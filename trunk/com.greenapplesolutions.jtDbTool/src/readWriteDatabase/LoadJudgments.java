package readWriteDatabase;

import indexer.CaseIndexer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.Util;

import com.greenapplesolutions.dbloader.domain.Citation;
import com.greenapplesolutions.dbloader.domain.Fields;
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
	}

	public void readDataBase() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(connectionString);
			statement = connect.createStatement();

			resultSet = statement.executeQuery("select * from " + databaseName
					+ ".citation");
			readEquivicitAndactsReferredTable(resultSet);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	private List<Statue> getStatueList(String keycode) throws Exception {
		List<Statue> statues = new ArrayList<Statue>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(connectionString);
			statement = connect.createStatement();

			resultSet = statement.executeQuery("select * from " + databaseName
					+ ".actsreferred where " + Fields.Keycode + "=" + keycode);

			while (resultSet.next()) {
				Statue statue = new Statue();
				statue.ActName = resultSet.getString(Fields.ActName) == null ? ""
						: resultSet.getString(Fields.ActName);

				statue.ClauseName = resultSet.getString(Fields.ClauseName) == null ? ""
						: resultSet.getString(Fields.ClauseName);

				statues.add(statue);

			}

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		return statues;
	}

	private List<Citation> getCitationList(String keycode) throws Exception {
		List<Citation> citations = new ArrayList<Citation>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(connectionString);
			statement = connect.createStatement();

			resultSet = statement.executeQuery("select * from " + databaseName
					+ ".equivicit where " + Fields.Keycode + "=" + keycode);

			while (resultSet.next()) {
				Citation citation = new Citation();
				citation.Journal = resultSet.getString(Fields.Journal) == null ? ""
						: resultSet.getString(Fields.Journal);

				citation.Volume = resultSet.getString(Fields.Volume) == null ? ""
						: resultSet.getString(Fields.Volume);

				citation.Year = Integer.parseInt(resultSet.getString(
						Fields.Year).toString());
				citation.Page = Integer.parseInt(resultSet.getString(
						Fields.Page).toString());
				citations.add(citation);

			}

		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		return citations;
	}

	private void readEquivicitAndactsReferredTable(ResultSet resultSet) {
		List<Judgement> judgements = new ArrayList<Judgement>();
		int count = 0;
		LuceneConfig config = LuceneConfig.INSTANCE();
		config.setIndexPath(directoryPath);
		CaseIndexer caseIndexer = new CaseIndexer();
		try {
			while (resultSet.next()) {
				Judgement j = new Judgement();
				j.Advocates = resultSet.getString(Fields.Advocates) == null ? ""
						: resultSet.getString(Fields.Advocates);
				j.Appellant = resultSet.getString(Fields.Appellant) == null ? ""
						: resultSet.getString(Fields.Appellant);

				try {
					j.Bench = Integer.parseInt(resultSet
							.getString(Fields.Bench).toString());
				} catch (Exception ex) {
					// LELogger.INSTANCE().setError(ex.getMessage());
					j.Bench = 0;
				}
				j.CaseDate = (Date) resultSet.getDate(Fields.CaseDate);

				j.CaseNumber = resultSet.getString(Fields.CaseNumber) == null ? ""
						: resultSet.getString(Fields.CaseNumber);

				j.Court = resultSet.getString(Fields.Court) == null ? ""
						: resultSet.getString(Fields.Court);
				j.FullText = resultSet.getString(Fields.FullText) == null ? ""
						: resultSet.getString(Fields.FullText);
				
				j.Judges = resultSet.getString(Fields.Judges) == null ? ""
						: resultSet.getString(Fields.Judges);
				j.Keycode = resultSet.getString(Fields.Keycode) == null ? ""
						: resultSet.getString(Fields.Keycode);
				j.Respondant = resultSet.getString(Fields.Respondant) == null ? ""
						: resultSet.getString(Fields.Respondant);
				j.Subject = "";
				try {
					j.Citations = getCitationList(j.Keycode);
				} catch (Exception e) {

				}
				try {
					j.Statues = getStatueList(j.Keycode);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				judgements.add(j);
				++count;

				if (judgements.size() >= 1500) {

					caseIndexer.indexJudgements(judgements);
					System.out.println("Total " + count + " added");
					judgements.clear();
					// if (count >= 5000)
					// break;
				}
			}

			if (judgements.size() > 0) {
				caseIndexer.indexJudgements(judgements);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		}
	}
}
