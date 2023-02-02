import java.sql.*;
import java.util.Scanner;

public class StworzPizza {
    public static void stworzPizze() {
        Scanner sc = new Scanner(System.in);
        int cena_pizzy = 16;
        System.out.println("Wybierz składniki wybierając odpowiedni numer (sos i ser stanowią bazę):");
        System.out.println("1. Pieczarki (+30 groszy)");
        System.out.println("2. Szynka (+60 groszy)");
        System.out.println("3. Salami (+60 groszy)");
        System.out.println("4. Papryka (+30 groszy)");
        System.out.println("5. Cebula (+30 groszy)");
        System.out.println("6. Ananas (+30 groszy)");
        System.out.println("7. Oliwki (+30 groszy)");
        System.out.println("8. Ser (+30 groszy)");
        System.out.println("9. Kiełbasa (+60 groszy)");
        System.out.println("10. Szynka parmeńska (+60 groszy)");
        System.out.println("Aby zakończyć dodawanie składników wpisz 0");

        int wybor = sc.nextInt();
        while (wybor != 0) {
            System.out.println("Podaj ilość:");
            int ilosc_skladnikow = sc.nextInt();
            switch (wybor) {
                case 1:
                    cena_pizzy += ilosc_skladnikow * 0.30;
                    break;
                case 2:
                case 3:
                    cena_pizzy += ilosc_skladnikow * 0.60;
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    cena_pizzy += ilosc_skladnikow * 30;
                    break;
                case 9:
                case 10:
                    cena_pizzy += ilosc_skladnikow * 60;
                    break;
                default:
                    System.out.println("Nieznany składnik");
            }
            System.out.println("Wybierz kolejny składnik lub 0 aby zakończyć:");
            wybor = sc.nextInt();
        }
        System.out.println("Cena twojej pizzy wynosi: " + cena_pizzy + " zł");
        while (true) {
            System.out.println("Czy chcesz zamówić swoją pizze? T/N");
            String decyzja = sc.nextLine();

            if (decyzja.equalsIgnoreCase("T")) {
                System.out.println("Podaj nazwę swojej pizzy:");
                String nazwaPizzy = sc.nextLine();

                try {
                    Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/Pizzeria", "root", "");

                    Statement statement = connection.createStatement();

                    String insertQuery = "INSERT INTO menu (nazwa_pizzy, cena) "
                            + "VALUES ('" + nazwaPizzy + "', '" + cena_pizzy + "')";

                    int wynik = statement.executeUpdate(insertQuery);

                    if (wynik == 1) {
                        System.out.println("Dodano pizze!");
                        ResultSet resultSet = statement.executeQuery("SELECT id_pizzy FROM menu ORDER BY id_pizzy DESC LIMIT 1");
                        if (resultSet.next()) {
                            int idPizzy = resultSet.getInt("id_pizzy");
                            System.out.println("Dodana pizza ma id: " + idPizzy + " Możesz ją zamówić wybierając jej id w punkcie drugim");
                        }
                    } else {
                        System.out.println("Błąd podczas dodawania pizzy do menu.");
                    }

                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Błąd połączenia z bazą danych: " + e.getMessage());
                }
                break;
            } else if (decyzja.equalsIgnoreCase("N")) {
                System.out.println("Nie dodałeś pizzy do naszego menu");
                break;
            } else {
                System.out.println("Niepoprawna odpowiedź. Spróbuj ponownie.");
            }
        }
    }
}
