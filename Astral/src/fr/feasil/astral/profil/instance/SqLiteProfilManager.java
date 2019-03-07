package fr.feasil.astral.profil.instance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.feasil.astral.profil.Genre;
import fr.feasil.astral.profil.ProfilManager;
import fr.feasil.astral.theme.instance.TextTheme;

public class SqLiteProfilManager implements ProfilManager<SqLiteProfil> {
	private final static String URL_START = "jdbc:sqlite:";
	private final static Map<String, SqLiteProfilManager> managers = new HashMap<>();
	
	public static SqLiteProfilManager getManager(String sqLiteFileName) {
		if ( !managers.containsKey(sqLiteFileName) )
			managers.put(sqLiteFileName, new SqLiteProfilManager(sqLiteFileName));
		return managers.get(sqLiteFileName);
	}
	
	
	private String sqLiteFile;
	private List<SqLiteProfil> allProfils = null;
	
	private SqLiteProfilManager(String sqLiteFile) {
		this.sqLiteFile = sqLiteFile;
		init();
	}
	
	@Override
	public SqLiteProfil newEmptyProfil() {
		SqLiteProfil profil = new SqLiteProfil();
		return profil;
	}
	
	@Override
	public List<SqLiteProfil> getAllProfils() {
		if ( allProfils == null ) {
			allProfils = new ArrayList<>();
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(URL_START + sqLiteFile);
				Statement stmt = conn.createStatement();
				
				String sql = "SELECT id, nom, genre, theme, datecreation FROM profil;";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					allProfils.add(new SqLiteProfil(rs.getInt("id"), 
													rs.getString("nom"), 
													Genre.getGenre(rs.getString("genre")), 
													new TextTheme(rs.getString("theme")), 
													rs.getLong("datecreation")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try { if (conn != null) conn.close(); } catch (SQLException ex) { }
			}
		}
		return allProfils;
	}
	
	
	@Override
	public void saveProfil(SqLiteProfil profil) {
		
		if ( profil == null )
			throw new IllegalArgumentException("Le profil doit être non null !");
		if ( profil.getNom() == null )
			throw new IllegalArgumentException("Le nom du Profil doit être renseigné !");
		if ( !(profil.getTheme() instanceof TextTheme) )
			throw new IllegalArgumentException("Le Theme doit être textuel !");
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL_START + sqLiteFile);
			if ( !profil.isRegistered() ) {//Insert
				String sql = "INSERT INTO profil (nom, genre, theme, datecreation) VALUES (?, ?, ?, ?);";
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, profil.getNom());
				stmt.setString(2, profil.getGenre().name());
				stmt.setString(3, ((TextTheme) profil.getTheme()).getTextContent());
				stmt.setLong(4, profil.getDateCreation());
				int id = stmt.executeUpdate();
				
				profil.setRegistered(true);
				profil.setId(id);
				
				allProfils.add(profil);
			}
			else {//Update
				String sql = "UPDATE profil SET nom=?, genre=?, theme=?, datecreation=? where id=?;";
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, profil.getNom());
				stmt.setString(2, profil.getGenre().name());
				stmt.setString(3, ((TextTheme) profil.getTheme()).getTextContent());
				stmt.setLong(4, profil.getDateCreation());
				stmt.setInt(5, profil.getId());
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (conn != null) conn.close(); } catch (SQLException ex) { }
		}
	}
	
	
	private void init() {
		Connection conn = null;
		try {
			// create a connection to the database
			conn = DriverManager.getConnection(URL_START + sqLiteFile);
			Statement stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS profil (\n"
						+ "	id integer PRIMARY KEY AUTOINCREMENT,\n"
						+ "	nom text NOT NULL,\n"
						+ "	genre text,\n"
						+ "	theme text,\n"
						+ " datecreation integer\n"
						+ ");";
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if (conn != null) conn.close(); } catch (SQLException ex) { }
		}
	}
	
	
}
