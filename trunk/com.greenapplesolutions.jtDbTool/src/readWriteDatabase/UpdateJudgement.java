package readWriteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import util.JTLogger;
import util.SelectedCourt;
import util.Util;

import com.greenapplesolutions.dbloader.domain.Citation;
import com.greenapplesolutions.dbloader.domain.HeadnoteAndHeld;
import com.greenapplesolutions.dbloader.domain.Judgement;

public class UpdateJudgement {
	private Connection connect = null;
	private Statement statement = null;
	private String connectionString;
	private String databaseName;
	private String hostName;
	private String userName;
	private String password;
	private int count;

	public UpdateJudgement(String databaseName, String hostName,
			String userName, String password) {
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
			return false;
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public void deleteJudgement(String keycode) {
		try {
			Statement st = connect.createStatement();
			st.addBatch("DELETE FROM judgements where Keycode="
					+ Util.wrapQuotes(keycode));
			st.addBatch("DELETE FROM citations where Keycode="
					+ Util.wrapQuotes(keycode));
			st.addBatch("DELETE FROM headnotes where Keycode="
					+ Util.wrapQuotes(keycode));
			st.executeBatch();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertJudgements(List<Judgement> judgements) {
		for (Judgement j : judgements)
			insertJudgement(j);
	}

	public void insertJudgement(Judgement j) {

		try {

			PreparedStatement pst = null;
			String query = "insert into judgements values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst = connect.prepareStatement(query);
			pst.setString(1, null);
			pst.setString(2, j.Keycode);
			pst.setString(3, SelectedCourt.getInstance().getSelectedCourt());
			pst.setString(4, j.Judges);
			pst.setInt(5, j.Bench);
			pst.setString(6, j.CaseNumber);
			pst.setString(7, j.Appellant);
			pst.setString(8, j.Respondant);
			pst.setDate(9, new java.sql.Date(j.CaseDate.getTime()));
			pst.setString(10, j.Advocates);
			pst.setString(11, j.CasesReferred);
			pst.setString(12, j.FullText);
			pst.setBoolean(13, false);

			System.out.println("dumping row with keycode " + j.Keycode);
			try {
				pst.executeUpdate();
				pst.close();
				dumpToCitations(j.Citations);
				dumpToHeadnotes(j.headnotesAndHelds);
			} catch (Exception e) {
				JTLogger.getInstance().setError(
						"Exception in dumping judgement " + j.Keycode
								+ "where appellant is " + j.Appellant
								+ "due to " + e.getMessage() + "\n\n");

			}

		} catch (Exception e) {
			System.out.println("error in dumping judgment" + e.getMessage());
			JTLogger.getInstance().setError(
					"Exception in dumping judgement " + j.Keycode
							+ "where appellant is " + j.Appellant + "due to "
							+ e.getMessage() + "\n\n");
		}

	}

	public void reviewJudgement(Judgement j) {

		try {

			PreparedStatement pst = null;
			String query = "insert into judgements values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst = connect.prepareStatement(query);
			pst.setString(1, null);
			pst.setString(2, j.Keycode);
			pst.setString(3, SelectedCourt.getInstance().getSelectedCourt());
			pst.setString(4, j.Judges);
			pst.setInt(5, j.Bench);
			pst.setString(6, j.CaseNumber);
			pst.setString(7, j.Appellant);
			pst.setString(8, j.Respondant);
			pst.setDate(9, new java.sql.Date(j.CaseDate.getTime()));
			pst.setString(10, j.Advocates);
			pst.setString(11, j.CasesReferred);
			pst.setString(12, j.FullText);
			pst.setBoolean(13, true);

			System.out.println("dumping row with keycode " + j.Keycode);
			try {
				pst.executeUpdate();
				pst.close();
				dumpToCitations(j.Citations);
				dumpToHeadnotes(j.headnotesAndHelds);
			} catch (Exception e) {
				JTLogger.getInstance().setError(
						"Exception in dumping judgement " + j.Keycode
								+ "where appellant is " + j.Appellant
								+ "due to " + e.getMessage() + "\n\n");

			}

		} catch (Exception e) {
			System.out.println("error in dumping judgment" + e.getMessage());
			JTLogger.getInstance().setError(
					"Exception in dumping judgement " + j.Keycode
							+ "where appellant is " + j.Appellant + "due to "
							+ e.getMessage() + "\n\n");
		}

	}
	public void updateJudgement(Judgement j) {

		try {

			PreparedStatement pst = null;
			String query = "insert into judgements values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst = connect.prepareStatement(query);
			pst.setString(1, null);
			pst.setString(2, j.Keycode);
			pst.setString(3, SelectedCourt.getInstance().getSelectedCourt());
			pst.setString(4, j.Judges);
			pst.setInt(5, j.Bench);
			pst.setString(6, j.CaseNumber);
			pst.setString(7, j.Appellant);
			pst.setString(8, j.Respondant);
			pst.setDate(9, new java.sql.Date(j.CaseDate.getTime()));
			pst.setString(10, j.Advocates);
			pst.setString(11, j.CasesReferred);
			pst.setString(12, j.FullText);
			pst.setBoolean(13, false);

			System.out.println("dumping row with keycode " + j.Keycode);
			try {
				pst.executeUpdate();
				pst.close();
				dumpToCitations(j.Citations);
				dumpToHeadnotes(j.headnotesAndHelds);
			} catch (Exception e) {
				JTLogger.getInstance().setError(
						"Exception in dumping judgement " + j.Keycode
								+ "where appellant is " + j.Appellant
								+ "due to " + e.getMessage() + "\n\n");

			}

		} catch (Exception e) {
			System.out.println("error in dumping judgment" + e.getMessage());
			JTLogger.getInstance().setError(
					"Exception in dumping judgement " + j.Keycode
							+ "where appellant is " + j.Appellant + "due to "
							+ e.getMessage() + "\n\n");
		}

	}
	private void dumpToCitations(List<Citation> citations) {

		try {

			PreparedStatement pst = null;
			String query = "insert into citations values(?,?,?,?,?,?)";
			pst = connect.prepareStatement(query);
			for (Citation citation : citations) {
				pst.setString(1, null);
				pst.setString(2, citation.keycode);
				pst.setString(3, citation.Journal);
				pst.setInt(4, citation.Year);
				pst.setString(5, citation.Volume);
				pst.setInt(6, citation.Page);
				System.out.println("dumping citation with keycode "
						+ citation.keycode);
				try {
					pst.executeUpdate();
				} catch (Exception e) {
					JTLogger.getInstance().setError(
							"Exception in dumping equivicit "
									+ citation.keycode
									+ "where journal is and page is "
									+ citation.Journal + " and "
									+ citation.Page + "due to "
									+ e.getMessage() + "\n\n");

				}

			}
			pst.close();
		} catch (Exception e) {
			System.out.println("error connecting equivicit table"
					+ e.getMessage());

			JTLogger.getInstance().setError(
					"error connecting equivicit table" + e.getMessage());

		}

	}

	private void dumpToHeadnotes(List<HeadnoteAndHeld> headnoteAndHelds) {

		try {

			PreparedStatement pst = null;
			String query = "insert into headnotes values(?,?,?,?)";
			pst = connect.prepareStatement(query);
			for (HeadnoteAndHeld hh : headnoteAndHelds) {
				pst.setString(1, null);
				pst.setString(2, hh.Keycode);
				pst.setString(3, hh.Headnote);

				pst.setString(4, hh.Held);
				System.out.println("dumping citation with keycode "
						+ hh.Keycode);
				try {
					pst.executeUpdate();
				} catch (Exception e) {
					JTLogger.getInstance().setError(
							"Exception in dumping equivicit " + "due to "
									+ e.getMessage() + "\n\n");

				}

			}
			pst.close();
		} catch (Exception e) {
			System.out.println("error connecting equivicit table"
					+ e.getMessage());

			JTLogger.getInstance().setError(
					"error connecting headnotes table" + e.getMessage());

		}

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

		}
	}
}
