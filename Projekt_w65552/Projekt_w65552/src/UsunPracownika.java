import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UsunPracownika {
    private Connection conn;

    public UsunPracownika(Connection connection) {
        this.conn = connection;
    }
    public void usunPracownika() {
        Scanner input = new Scanner(System.in);

        System.out.print("Podaj imię pracownika do usunięcia: ");
        String imie = input.nextLine();

        System.out.print("Podaj nazwisko pracownika do usunięcia: ");
        String nazwisko = input.nextLine();

        String query = "DELETE FROM pracownicy WHERE imie = ? AND nazwisko = ?";


        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, imie);
            statement.setString(2, nazwisko);
            statement.executeUpdate();
            System.out.print("Pomyślnie usunięto pracownika: ");
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas usuwania pracownika z bazy danych");
            e.printStackTrace();
        }
    }
}
