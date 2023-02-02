import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Zamowienie {
    private int id_pizzy;
    private int ilosc;
    private int cena;
    private int cenaZamowienia;
    private Scanner sc;

    public Zamowienie(int id_pizzy, int ilosc, int cena) {
        this.id_pizzy = id_pizzy;
        this.ilosc = ilosc;
        this.cena = cena;
        sc = new Scanner(System.in);

    }

    public void dodajZamowienie() {
        System.out.println("Posiadasz kartę stałego klienta? [T/N]");
        String karta = sc.nextLine();
        cenaZamowienia = cena * ilosc;
        if (karta.equalsIgnoreCase("T")) {
            cenaZamowienia = (int) (cenaZamowienia * 0.8);
        }
        try (PreparedStatement stmt = Pizzeria.conn.prepareStatement("INSERT INTO zamowienia (data_zamówienia,id_pizzy,ilosc,cena) VALUES (?, ?, ?, ?)")) {
            stmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            stmt.setDouble(2,id_pizzy);
            stmt.setDouble(3,ilosc);
            stmt.setDouble(4,cenaZamowienia);
            int liczba_wierszy_zmodyfikowanych = stmt.executeUpdate();
            System.out.println("Dodano " + liczba_wierszy_zmodyfikowanych + " zamowienie.");
        } catch (SQLException e) {
            System.out.println("Błąd podczas dodawania zamówienia: " + e.getMessage());
        }
    }
}