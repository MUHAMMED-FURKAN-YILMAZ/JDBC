package techproed.JdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Jdbc4DMLInsert {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {


		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","hr","hr");

		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM bolumler");
		
		while (rs.next()) {
			
			
			System.out.printf("%-7d%-15s%-10s%n", rs.getInt(1),rs.getString(2),rs.getString(3));
		}
		
		//ORNEK1: Bolumler tablosuna yeni bir kayit (80, 'ARGE', 'ISTANBUL') 
		//ekleyelim ve eklenen kaydi teyit icin sorgulayalim.
		
		//st.execute("INSERT INTO bolumler VALUES (80,'ARGE', 'ISTANBUL')") ;
		
		//System.out.println("Basariyla eklendi");
		
		//st.execute("DELETE FROM bolumler WHERE bolum_id = 80");
		
		//ORNEK2: Bolumler tablosuna birden fazla yeni kayıt ekleyelim.
		
		
	   String sorgular [] = {"INSERT INTO bolumler VALUES(95, 'YEMEKHANE', 'ISTANBUL')",
                             "INSERT INTO bolumler VALUES(85, 'OFIS','ANKARA')",
                             "INSERT INTO bolumler VALUES(75, 'OFIS2', 'VAN')"};
	   
	  
	   
	   
//	   for(String w : sorgular) {
//		   
//		   st.execute(w);                          //--> Teker teker kayitlari gonderiyor
//	   }
	   
	   
	// 2.YONTEM (addBath ve excuteBatch() metotlari ile)
	// ----------------------------------------------------
	// addBatch metodu ile SQL ifadeleri gruplandirilabilir ve exucuteBatch()
	// metodu ile veritabanina bir kere gonderilebilir.
	// excuteBatch() metodu bir int [] dizi dondurur. Bu dizi her bir ifade sonucunda 
	// etkilenen satir sayisini gosterir. 
	   
	   
	   String [] sorgular1 = {"INSERT INTO bolumler VALUES(81, 'YEMEKHANE2', 'MUS')",
               "INSERT INTO bolumler VALUES(82, 'OFIS3','ORDU')",
               "INSERT INTO bolumler VALUES(83, 'OFIS4', 'MUGLA')"};
	   
	   
	   Arrays.stream(sorgular1).forEach(t -> {
		try {
			st.addBatch(t);                     //--> Hepsini bir top haline getiriyor
		} catch (SQLException e) {              //--> Burasi forEach() ile de olur. Oylesine sekil sukul yaptim.
			e.printStackTrace();
		}
	}); 
	   st.executeBatch();                      //--> Sonrasinda tek seferde gonderiyor
	   
	   
	   con.close();
	   rs.close();
	   st.close();
	   
	   
	   
	   
	   
	  
		
		

	}

}
