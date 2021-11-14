package techproed.JdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc1Query01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//Ilgili driver'i yuklemeliyiz. (TV'nin fisini tak)
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
        //Baglanti olustur
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","hr","hr");
		
		//SQL komutlari icin bir Statement nesnesi olustur. (Container) (Kumanda tusuna kanal atama)
		
		Statement st = con.createStatement();
		
		//SQL ifadeleri yazabilir ve calistirabiliriz (Kumandada istedigimiz komuta basma)
		//(personel tablosundaki personle_id'si 7369 olan personelin adini ve maasini sorgula)
		
		ResultSet isim = st.executeQuery("SELECT personel_isim, maas FROM personel3 WHERE personel_id=7369");
		
		// Sonuclari aldik ve isledik 
		
		
		
		while (isim.next()) {
			
			System.out.println(isim.getString("personel_isim") +" "+ isim.getInt("maas"));
			
			
		}
		
		
	}

}
