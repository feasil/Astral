package fr.feasil.astral.rule.instance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.feasil.astral.rule.DataRules;
import fr.feasil.astral.theme.ruleengine.ActionDispatcher;
import fr.feasil.astral.theme.ruleengine.ExpressionParser;


public class SqLiteDataRules extends DataRules {
	private final static String URL_START = "jdbc:sqlite:";
	
	private String sqLiteFile;
	
	public SqLiteDataRules(ActionDispatcher dispatcher, String sqLiteFile) {
		super(dispatcher);
		this.sqLiteFile = sqLiteFile;
		
		loadData();
	}
	
	private void loadData() {
		Connection conn = null;
		try {
			// create a connection to the database
			conn = DriverManager.getConnection(URL_START + sqLiteFile);
			Statement stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS data (\n"
						+ "	id integer PRIMARY KEY AUTOINCREMENT,\n"
						+ "	expression text NOT NULL,\n"
						+ "	argument text\n"
						+ ");";
			stmt.execute(sql);
			
			sql = "SELECT expression, argument FROM data;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				addRule(ExpressionParser.fromString(rs.getString("expression")), rs.getString("argument"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (conn != null) conn.close(); } catch (SQLException ex) { }
		}
	}
}
