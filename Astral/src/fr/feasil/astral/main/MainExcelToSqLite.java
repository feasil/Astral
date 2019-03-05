package fr.feasil.astral.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.feasil.astral.theme.ruleengine.expression.Operation;
import fr.feasil.astral.theme.ruleengine.expression.OperationAnd;
import fr.feasil.astral.theme.ruleengine.expression.OperationEn;
import fr.feasil.astral.theme.ruleengine.expression.OperationNot;
import fr.feasil.astral.theme.ruleengine.expression.Operations;
import fr.feasil.utils.xlsloader.ExcelFile;
import fr.feasil.utils.xlsloader.ExcelSheet;

public class MainExcelToSqLite {
	private final static String URL_START = "jdbc:sqlite:";
	
	private ExcelFile file;
	private String dbFileName;
	
	public static void main(String[] args) {
		
		Operations operations = Operations.INSTANCE;
		// register new operations with the previously created container
		Operation ot = new OperationAnd();
		operations.registerOperation(ot, "and"); operations.registerOperation(ot, "AND");
		operations.registerOperation(ot, "et"); operations.registerOperation(ot, "ET");
		ot = new OperationEn();
		operations.registerOperation(ot, "en"); operations.registerOperation(ot, "EN");
		ot = new OperationNot();
		operations.registerOperation(ot, "not"); operations.registerOperation(ot, "NOT");
		operations.registerOperation(ot, "non"); operations.registerOperation(ot, "NON");
		
		new MainExcelToSqLite("in/DataTest.xlsx", "in/DataAstral.db");
	}
	
	public MainExcelToSqLite(String excelFileName, String dbFileName) {
		this.file = ExcelFile.load(excelFileName);
		this.dbFileName = dbFileName;
		
		loadData();
	}
	
	
	private void loadData() {
		
		Connection conn = null;
		try {
			// create a connection to the database
			conn = DriverManager.getConnection(URL_START + dbFileName);
			Statement stmt = conn.createStatement();
//			String sql = "CREATE TABLE IF NOT EXISTS profil (\n"
//						+ "	id integer PRIMARY KEY AUTOINCREMENT,\n"
//						+ "	nom text NOT NULL,\n"
//						+ "	genre text\n"
//						+ "	theme text\n"
//						+ ");";
			String sql = "CREATE TABLE IF NOT EXISTS data (\n"
						+ "	id integer PRIMARY KEY AUTOINCREMENT,\n"
						+ "	expression text NOT NULL,\n"
						+ "	argument text\n"
						+ ");";
			stmt.execute(sql);
			
			sql = "INSERT INTO data(expression, argument) VALUES(?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			for (ExcelSheet sheet : file.getListSheets() ) {
				Operation op = null;
				if (  sheet.getHeader().length > 0 && sheet.getHeader()[0] != null )
					op = Operations.INSTANCE.getOperation(sheet.getHeader()[0]);
				if ( op == null)
					op = Operations.INSTANCE.getOperation("EN");
				String operationSymbol = " " + op.getSymbol() + " ";
				
				List<String> headers = new ArrayList<>();
				headers.add(null);
				for ( int i = 1 ; i < sheet.getHeader().length ; i++ )
					headers.add(sheet.getHeader()[i].replaceAll(" ", ""));
				
				for ( Object[] ligne : sheet.getBody() )
				{
					String rowName = ligne[0].toString().replaceAll(" ", "");
					for ( int i = 1 ; i < ligne.length ; i++ )
						if ( ligne[i] != null ) {
							pstmt.setString(1, headers.get(i) + operationSymbol + rowName);
				            pstmt.setString(2, ligne[i].toString());
				            pstmt.executeUpdate();
							
//							addRule(ExpressionParser.fromString(headers.get(i) + operationSymbol + rowName), ligne[i].toString());
						}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (conn != null) conn.close(); } catch (SQLException ex) { }
		}
	}
}
