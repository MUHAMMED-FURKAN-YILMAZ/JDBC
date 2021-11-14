package techproed.JdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcQuery02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	
		Class.forName("oracle.jdbc.driver.OracleDriver");
        				
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","hr","hr");

		Statement st = con.createStatement();
		
		/*
		 
		 Ornek1: Bolumler tablosundaki tum kayitlari listeleyen bir sorgu yaziniz.
		 
		 */
			
		ResultSet tablo = st.executeQuery("SELECT * FROM bolumler");
				
		while(tablo.next()) {
			
			System.out.printf("%-7d%-15s%-10s%n", tablo.getInt(1),tablo.getString(2),tablo.getString(3));
		}
		
		/*ornek2  SATIS VE MUHASEBE bolumlerinde calisan personelin
		 * isimlerini ve maaslarini, maas ters sirali olarak listeleyin*/
		
		ResultSet tablo2 = st.executeQuery("SELECT personel_isim, maas "
				+ "FROM personel3 "
				+ "WHERE bolum_id IN (10,30)"
				+ "ORDER BY maas DESC ");
		

		System.out.println("=============================");
		
		while(tablo2.next()) {
			
			System.out.printf("%-15s%-7d%n", tablo2.getString("personel_isim"),tablo2.getInt("maas"));
		}
		
		/*  ORNEK3: Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini
		  ve maaslarini, bolum ve maas sirali listeleyiniz. NOT: calisani olmasa
		  bile bolum ismi gosterilmelidir.   */
		
		ResultSet tablo3 = st.executeQuery("SELECT personel_isim, bolum_isim, maas "
				+ "FROM bolumler b "
				+ "LEFT JOIN personel3 p "
				+ "ON p.bolum_id = b.bolum_id "
				+ "ORDER BY bolum_isim, maas");

		System.out.println("==========================================");
		String a = "Personel Isim";
		String b = "Bolum";
		String c = "Maas";
		
		System.out.printf("%-17s%-15s%-7s%n",a,b,c);
		System.out.println("==========================================");
		
		while (tablo3.next()) {
			
			
			System.out.printf("%-17s%-15s%-7d%n", tablo3.getString("personel_isim"), tablo3.getString("bolum_isim"), tablo3.getInt("maas"));
			
		}
		
		
		
		/*=======================================================================
		  ORNEK4: Maasi en yuksek 10 kisinin bolumunu,adini ve maasini listeyiniz        ===> Surum yuzunden hata veriyor. Kodda bir problem yok!
		========================================================================*/
		
		
//		ResultSet tablo4 = st.executeQuery("SELECT bolum_isim, personel_isim, maas "
//				+ "FROM personel3 p "
//				+ "FULL JOIN bolumler b "
//				+ "ON p.bolum_id = b.bolum_id "
//				+ "ORDER BY maas DESC "
//				+ "FETCH NEXT 10 ROWS ONLY ");
//		
//		System.out.printf("%-17s%-15s%-7s%n",b,a,c);
//		System.out.println("==========================================");
//		
//		while (tablo4.next()) {
//			
//			System.out.printf("%-17s%-15s%-7d%n", tablo3.getString("bolum_isim"), tablo3.getString("personel_isim"), tablo3.getInt("maas"));
//			
//		}
		
		con.close();
		st.close();
		tablo.close();
		tablo2.close();
		tablo3.close();
//		tablo4.close();
		
		
		
		
		
		
	}
	 
	

}
