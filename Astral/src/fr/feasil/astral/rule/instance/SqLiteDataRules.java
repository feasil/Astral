package fr.feasil.astral.rule.instance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.feasil.astral.rule.DataRules;
import fr.feasil.astral.theme.ruleengine.ActionDispatcher;
import fr.feasil.astral.theme.ruleengine.Expression;
import fr.feasil.astral.theme.ruleengine.ExpressionParser;
import fr.feasil.astral.theme.ruleengine.Rule;


public class SqLiteDataRules extends DataRules {
	private final static String URL_START = "jdbc:sqlite:";
	
	private String sqLiteFile;
	private List<SqlLiteRule> rules = new ArrayList<>();
	
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
			
			sql = "SELECT id, expression, argument FROM data;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				rules.add(new SqlLiteRule(rs.getInt("id"), addRule(ExpressionParser.fromString(rs.getString("expression")), rs.getString("argument"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (conn != null) conn.close(); } catch (SQLException ex) { }
		}
	}
	@Override
	public void insertRule(Expression expression, Object argument) {
		Connection conn = null;
		try {
			// create a connection to the database
			conn = DriverManager.getConnection(URL_START + sqLiteFile);
			
			String sql = "INSERT INTO data(expression, argument) VALUES(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, expression.getExpressionValue());
            pstmt.setString(2, argument.toString());
            int id = pstmt.executeUpdate();
			
			rules.add(new SqlLiteRule(id, addRule(expression, argument.toString())));
			
			fireReloaded();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (conn != null) conn.close(); } catch (SQLException ex) { }
		}
	}
	
	@Override
	public void insertRules(Map<Expression, Object> newRules) {
		Connection conn = null;
		try {
			// create a connection to the database
			conn = DriverManager.getConnection(URL_START + sqLiteFile);
			
			String sql = "INSERT INTO data(expression, argument) VALUES(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			for ( Expression e : newRules.keySet() ) {
				pstmt.setString(1, e.getExpressionValue());
	            pstmt.setString(2, newRules.get(e).toString());
	            int id = pstmt.executeUpdate();
	            
	            rules.add(new SqlLiteRule(id, addRule(e, newRules.get(e).toString())));
			}
			
			fireReloaded();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (conn != null) conn.close(); } catch (SQLException ex) { }
		}
	}
	
	
	
	@Override
	public DataRules newInstance(ActionDispatcher dispatcher) {
		return new SqLiteDataRules(dispatcher, sqLiteFile);
	}
	
//	@Override
//	public void reload() {//TODO a n'appeler qu'en interne post insert/update/delete ?
//		clearRules();
//		loadData();
//		fireReloaded();
//	}
	
	
	
	
	
	
	private class SqlLiteRule {
		private int id;
		private Rule rule;
		
		public SqlLiteRule(int id, Rule rule) {
			this.id = id;
			this.rule = rule;
		}
		
		public int getId() {
			return id;
		}
		public Rule getRule() {
			return rule;
		}
	}
}
