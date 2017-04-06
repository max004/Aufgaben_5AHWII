import java.sql.*;
import java.util.ArrayList;

public class DBManager {

	static Connection c;

	//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	//		
	//		DBManager dbManager = new DBManager();
	//		
	//		dbManager.readData("strom_verbrauch");
	//		
	//	}


	public DBManager() throws ClassNotFoundException, SQLException  {

		try{
			//  mySql
			//			Class.forName("com.mysql.jdbc.Driver");
			//			c = DriverManager.getConnection("jdbc:mysql://localhost/VersicherungsDB", "root", "");

			//sqlite
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:sprit.db");

			System.out.println("Verbindung zur Datenbank erfolgreich hergestellt!");

		}catch(Exception e){

			e.printStackTrace();
			System.out.println("!!Es kann keine Verbindung zur Datenbank hergestellt werden!!");

		}

	}

	public long getLastDate() throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		long utst = 0;

		sql = "SELECT datum FROM sprit_data ORDER BY datum DESC LIMIT(1)";
		stmt = c.createStatement();
		rs = stmt.executeQuery(sql);

		while(rs.next()){

			utst = rs.getLong("datum");

		}

		System.out.println("-- Auslesen erfolgreich --");

		return utst;

	}

	public ArrayList<Spritpreis> getAvgSprit(ArrayList<Long> days) throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Spritpreis> avg = new ArrayList<Spritpreis>();
		int tankeNr;

		for(int i = 0; i<days.size()-1; i++){

			sql = "SELECT AVG(value), tankenr FROM sprit_data WHERE datum >= " + days.get(i) + " AND datum < " + days.get(i+1) + " GROUP BY tankenr";
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){

				avg.add(new Spritpreis(rs.getInt("tankenr"), rs.getDouble("AVG(value)"), days.get(i))); 

			}
		}

		System.out.println("-- Auslesen erfolgreich --");

		return avg;

	}
	
	
	public ArrayList<Spritpreis> getMinSprit(ArrayList<Long> days) throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Spritpreis> min = new ArrayList<Spritpreis>();
		int tankeNr;

		for(int i = 0; i<days.size()-1; i++){

			sql = "SELECT MIN(value), tankenr FROM sprit_data WHERE datum >= " + days.get(i) + " AND datum < " + days.get(i+1);
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){

				min.add(new Spritpreis(rs.getInt("tankenr"), rs.getDouble("MIN(value)"), days.get(i))); 

			}
		}

		System.out.println("-- Auslesen erfolgreich --");

		return min;

	}



	public int getAnzTankstellen() throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		int anzahl = 0;

		sql = "SELECT COUNT(tankenr) FROM (SELECT tankenr FROM sprit_data GROUP BY tankenr)";
		stmt = c.createStatement();
		rs = stmt.executeQuery(sql);
			
		while(rs.next()){

			anzahl = rs.getInt("count(tankenr)");

		}

		System.out.println("-- Auslesen erfolgreich --");

		return anzahl;

	}
	
	public String getTankstellenName(int id) throws SQLException{

		String  sql = null;
		Statement stmt = null;
		ResultSet rs = null;
		String name = "";

		sql = "SELECT tankeName FROM tankstelle WHERE tankeNr = " + id;
		stmt = c.createStatement();
		rs = stmt.executeQuery(sql);
			
		while(rs.next()){

			name = rs.getString("tankeName");

		}

		System.out.println("-- Auslesen erfolgreich --");

		return name;

	}

}
