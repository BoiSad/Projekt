import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class KartaKlienta {
    public static void DodajKarte() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj imię:");
        String imie = sc.nextLine();

        System.out.println("Podaj nazwisko:");
        String nazwisko = sc.nextLine();

        System.out.println("Podaj miasto:");
        String miasto = sc.nextLine();

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Pizzeria", "root", "");

            Statement statement = connection.createStatement();

            String insertQuery = "INSERT INTO klienci (imie, nazwisko, miasto) "
                    + "VALUES ('" + imie + "', '" + nazwisko + "', '" + miasto + "')";

            int wynik = statement.executeUpdate(insertQuery);

            if (wynik == 1) {
                System.out.println("Założono kartę stałego klienta!");
            } else {
                System.out.println("Błąd podczas zakładania karty stałego klienta.");
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą danych: " + e.getMessage());
        }
    }
}
