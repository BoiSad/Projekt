
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DodajPracownika {
    private Connection conn;

    public DodajPracownika(Connection connection) {
        this.conn = connection;
    }

    public void dodajPracownika() {
        Scanner input = new Scanner(System.in);

        System.out.print("Podaj imię Pracownika: ");
        String imie = input.nextLine();

        System.out.print("Podaj nazwisko Pracownika: ");
        String nazwisko = input.nextLine();

        System.out.print("Podaj miasto: ");
        String miasto = input.nextLine();

        System.out.print("Podaj pensje: ");
        int pensja = input.nextInt();

        String query = "INSERT INTO pracownicy (imie, nazwisko, miasto, pensja) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, imie);
            statement.setString(2, nazwisko);
            statement.setString(3, miasto);
            statement.setInt(4, pensja);
            statement.executeUpdate();
            System.out.print("Pomyślnie dodano pracownika: ");
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas dodawania klienta do bazy danych");
            e.printStackTrace();
        }
    }


}