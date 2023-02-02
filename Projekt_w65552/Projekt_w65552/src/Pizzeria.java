import java.sql.*;
import java.util.*;

public class Pizzeria {
    public static Connection conn;

    public static void main(String[] args) throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/pizzeria", "root", "");
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą danych: " + e.getMessage());
        }


        Scanner sc = new Scanner(System.in);
        int opcja = 0;
        do {
            System.out.println("\n---- Pizzeria ----");
            System.out.println("1. Wyświetl menu");
            System.out.println("2. Dodaj zamówienie");
            System.out.println("3. Wyświetl zamówienia");
            System.out.println("4. Rozlicz paragon");
            System.out.println("5. Stwórz własną pizzę");
            System.out.println("6. Załóż kartę stałego klienta");
            System.out.println("7. Pracownicy");
            System.out.println("0. Zakończ");
            System.out.print("Wybierz opcję: ");
            opcja = sc.nextInt();

            switch (opcja) {
                case 1:
                    wyswietlMenu wyswietlMenu = new wyswietlMenu(conn);
                    wyswietlMenu.wyswietlMenu();
                    break;
                case 2:

                    System.out.print("Podaj numer pizzy: ");
                    int id_pizzy = (int) sc.nextInt();
                    System.out.print("Podaj ilosc: ");
                    int ilosc = (int) sc.nextInt();
                    System.out.print("Podaj cenę pizzy: ");
                    int cena = sc.nextInt();
                    Zamowienie zamowienie = new Zamowienie(id_pizzy, ilosc, cena);
                    zamowienie.dodajZamowienie();
                    break;
                case 3:
                    Zamowienia zamowienia = new Zamowienia(conn);
                    zamowienia.wyswietlZamowienia();
                    break;
                case 4:
                    RozliczenieParagonu RozliczenieParagonu = new RozliczenieParagonu();
                    RozliczenieParagonu.rozliczParagon();
                    break;
                case 5:
                    StworzPizza StworzPizza = new StworzPizza();
                    StworzPizza.stworzPizze();
                    break;
                case 6:
                    KartaKlienta KartaKlienta = new  KartaKlienta();
                    KartaKlienta.DodajKarte();
                    break;
                case 7:
                    System.out.println("7. Pracownicy: ");
                    System.out.println("1. Dodaj pracownika");
                    System.out.println("2. Usuń pracownika");
                    System.out.print("Wybierz opcję: ");
                    Scanner input = new Scanner(System.in);
                    int wyborPracownika = input.nextInt();
                    switch (wyborPracownika) {
                        case 1:
                            DodajPracownika dodajPracownika = new DodajPracownika(conn);
                            dodajPracownika.dodajPracownika();
                            break;
                        case 2:
                            UsunPracownika usunPracownika = new UsunPracownika(conn);
                            usunPracownika.usunPracownika();
                            break;
                        default:
                            System.out.println("Nieznana opcja");
                            break;
                    }
                    break;

                case 0:
                    System.out.print("Do widzenia!");
            }
        } while (opcja != 0);
    }

}





