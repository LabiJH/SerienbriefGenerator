/**
 * SQLDatabaseConnection, Klasse für das handling der SQL Anfragen. Input + Output von
 * Fahrzeug sowie Kunden Daten.
 * @author: Labinot Jaha
 * @version: 1.0
*/
import java.sql.*;
import java.util.ArrayList;

public class SQLDatabaseConnection {
    /**
     * @param url Verbindungsvariable zur SQL Datenbank, enthaelt JDBC Treiber, DNS Adresse, Port und DB Name
     * @param username Benutzername für den DB Login
     * @param password Passwort für den DB Login
     */
    public static String url = "jdbc:mysql://localhost:3306/Fahrzeughaus";
    public static String username = "root";
    public static String password = "";   // TODO: Ersetzen, sobald keine Testdatenbank

    /**
     * Methode um die Fahrzeuge in die DB zu schreiben
     * @param objectList Liste mit den Fahrzeug Objekten
     */
    public static void insertAutoIntoDB(ArrayList objectList){
        ArrayList<Fahrzeug> flist = objectList;

        for(int i = 0; i < flist.size(); i++){
            /* Query um die Fahrzeuge in die DB zu schreiben */
            String query = "INSERT INTO fahrzeuge (Fahrzeugtyp, Bezeichnung, Hersteller, Leistung, Verkaufspreis) VALUES "
                    + "('"+ flist.get(i).getTyp() +"', '"+ flist.get(i).getBezeichnung() +"', '"+ flist.get(i).getHersteller() +
                    "',"+ flist.get(i).getLeistung() + " ,"+ flist.get(i).getPreis() +");";
            try {
                Connection con = DriverManager.getConnection(url, username,password); // Connection Object für die DB
                Statement statement = con.createStatement();            // Statement FÜR das Connection Object
                statement.execute(query);               // Ausführen der Query
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }
    /**
     * Methode um die Kunden aus der DB auszulesen
     * Ergebnis wird in die Konsole geschrieben.
     */
    public static void returnKunden(){
        String query = "SELECT * FROM kunden";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int columns = rs.getMetaData().getColumnCount(); // Anzahl der Zeilen

            for (int i = 1; i <= columns; i++){
                System.out.printf("%-15s", rs.getMetaData().getColumnLabel(i));
            }
            /* .next gibt zurück, ob noch ein Datensatz folgt */
            System.out.println();
            System.out.println("------------------------------------------------------------------");
            while(rs.next()){
                for (int i = 1; i <= columns; i++ ){
                    System.out.printf("%-15s", rs.getString(i));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Methode um die Kunden in die DB zu schreiben
     * @param kunde Kunden Object welches eingefügt wird
     */
    public static void insertKundeIntoDB(Kunde kunde){
        System.out.println(kunde.getPlz());
        String query = "INSERT INTO kunden (Name, Vorname, Strasse, Hausnummer, PLZ, Ort) VALUES "
                + "('"+ kunde.getName() +"', '"+ kunde.getVorname() +"', '"+ kunde.getStrasse() +"', '"+ kunde.getHausnummer() + "',"
                + kunde.getPlz() +", '"+ kunde.getOrt() +"');";
        try {
            Connection con = DriverManager.getConnection(url, username,password); // Connection Object für die DB
            Statement statement = con.createStatement();            // Statement FÜR das Connection Object
            statement.execute(query);           // Ausführen der Query
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    /**
     * Methode um alle Kunden aus der DB zu lesen für die PDF Erstellung
     * @return Arrayliste mit allen Kunden Objekten aus der DB
     */
    public static ArrayList<Kunde> getKundenList(){
        String query = "SELECT * FROM kunden";
        ArrayList<Kunde> kundenList = new ArrayList<Kunde>();
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int columns = rs.getMetaData().getColumnCount(); // Anzahl der Zeilen

            /* .next gibt zurück, ob noch ein Datensatz folgt */
            while(rs.next()){
                Kunde kunde = new Kunde();
                kunde.setName(rs.getString("Name"));
                kunde.setVorname(rs.getString("Vorname"));
                kunde.setStrasse(rs.getString("Strasse"));
                kunde.setHausnummer(rs.getString("Hausnummer"));
                kunde.setPlz(Integer.parseInt(rs.getString("PLZ")));
                kunde.setOrt(rs.getString("Ort"));
                kundenList.add(kunde);
            }
            return kundenList;      // Return die fertige Liste
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Methode um alle Fahrzeuge aus der DB zu lesen für die PDF Erstellung
     * @return Arrayliste mit allen Fahrzeug Objekten aus der DB
     */
    public static ArrayList<Fahrzeug> getFahrzeugList(){
        String query = "SELECT * FROM fahrzeuge";
        ArrayList<Fahrzeug> fahrzeuglist = new ArrayList<Fahrzeug>();
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            int columns = rs.getMetaData().getColumnCount(); // Anzahl der Zeilen
            /* .next gibt zurück, ob noch ein Datensatz folgt */

            while(rs.next()){
                Fahrzeug fahrzeug = new Fahrzeug();
                fahrzeug.setTyp(rs.getString("Fahrzeugtyp"));
                fahrzeug.setBezeichnung(rs.getString("Bezeichnung"));
                fahrzeug.setHersteller(rs.getString("Hersteller"));
                fahrzeug.setLeistung(rs.getInt("Leistung"));
                fahrzeug.setPreis(rs.getInt("Verkaufspreis"));
                fahrzeuglist.add(fahrzeug);
            }
            return fahrzeuglist;      // Return die fertige Liste
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}