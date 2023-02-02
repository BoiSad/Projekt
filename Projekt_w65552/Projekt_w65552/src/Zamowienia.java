import java.sql.*;
import java.util.Scanner;

public class Zamowienia {
    private Connection conn;

    public Zamowienia(Connection conn) {
        this.conn = conn;
    }

    public void wyswietlZamowienia() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM zamowienia inner join menu on zamowienia.id_pizzy = menu.id_pizzy" )){
            while (rs.next()) {
                System.out.println(rs.getString("id_zamowienia") + ". " + rs.getString("data_zamówienia")+ ". " + rs.getString("nazwa_pizzy") + " - ilość : " +
                        rs.getString("ilosc")+ " - " + rs.getDouble("cena") + " zł");
            }
        } catch (SQLException e) {
            System.out.println("Błąd podczas wyświetlania zamówień: " + e.getMessage());
        }
    }
}