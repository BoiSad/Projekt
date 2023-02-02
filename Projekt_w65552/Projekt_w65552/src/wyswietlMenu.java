import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class wyswietlMenu {
    private Connection conn;

    public wyswietlMenu(Connection conn) {
        this.conn = conn;
    }

    public void wyswietlMenu() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM menu")) {
            while (rs.next()) {
                System.out.println(rs.getString("id_pizzy") + ". " + rs.getString("nazwa_pizzy") + " - " + rs.getDouble("cena") + " zł");
            }
        } catch (SQLException e) {
            System.out.println("Błąd podczas wyświetlania menu: " + e.getMessage());
        }
    }
}