import java.sql.*;
import java.util.Scanner;

public class RozliczenieParagonu {
    private static final String URL = "jdbc:mysql://localhost/pizzeria";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void rozliczParagon() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wybierz ID zamówienia, które chcesz rozliczyć:");
        int idZamowienia = input.nextInt();

        String selectSql = "SELECT z.cena FROM zamowienia z WHERE z.id_zamowienia = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement selectStmt = connection.prepareStatement(selectSql)) {
            selectStmt.setInt(1, idZamowienia);
            ResultSet resultSet = selectStmt.executeQuery();
            if (resultSet.next()) {
                int cena = resultSet.getInt("cena");
                System.out.println("Cena rachunku wynosi: " + cena + "zł");
                System.out.println("Czy rachunek został opłacony? [T/N]");
                char odp = input.next().charAt(0);
                if (odp == 'T' || odp == 't') {
                    String deleteSql = "DELETE FROM zamowienia WHERE id_zamowienia = ?";
                    try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
                        deleteStmt.setInt(1, idZamowienia);
                        int deleted = deleteStmt.executeUpdate();
                        if (deleted == 1) {
                            System.out.println("Zamówienie zostało zapłacone.");
                        } else {
                            System.out.println("Wystąpił błąd podczas usuwania zamówienia.");
                        }
                    }
                } else {
                    System.out.println("Zamówienie nie zostało usunięte.");
                }
            } else {
                System.out.println("Nie znaleziono zamówienia o podanym ID.");
            }
        } catch (SQLException e) {
            System.out.println("Wystąpił błąd podczas pobierania ceny rachunku: " + e.getMessage());
        }
    }
}