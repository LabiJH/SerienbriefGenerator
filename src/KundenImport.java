import java.io.Console;
/*******************************************
 * KundenImport, lässt Kunden aus der Kunden Datenbank auslesen,
 * oder einen neuen anlegen.
 * @author: Labinot Jaha
 * @version: 1.0
 ******************************************/
public class KundenImport {

    public static void main(String[] args) {
        Console console = System.console();
        String userResponse;
        String regex = "^[a-zA-Z\\\\s]+";
        String hausRegex = "([\\d]+)([a-zA-z]?)";
        String plzRegex = "^[0-9]{5}$";

        if (console != null) {
            while (true) {
                Kunde kunde = new Kunde();
                System.out.println("Herzlich Willkommen bei der Kunden Erfassung.");
                System.out.println("Möchten Sie alle Kunden aus der Datenbank angezeigt haben (1), oder einen neuen Kunden anlegen?(2)");
                System.out.println("Bitte tippen Sie 1 oder 2, und drücken dann Enter / Return.");
                userResponse = console.readLine();
                System.out.println(userResponse);

                if (userResponse.equals("1")) {
                    SQLDatabaseConnection.returnKunden();
                    console.readLine();

                } else if (userResponse.equals("2")) {

                    while (!userResponse.matches(regex)) {
                        System.out.println("Bitte geben sie den Familiennamen des Kunden ein");
                        userResponse = console.readLine();
                        if (userResponse.matches(regex)) {
                            kunde.setName(userResponse);
                        } else {
                            System.out.println("Keine gültige Eingabe!");
                        }
                    }

                    do {
                        System.out.println("Bitte geben sie den Vornamen des Kunden ein");
                        userResponse = console.readLine();
                        if (userResponse.matches(regex)) {
                            kunde.setVorname(userResponse);
                        } else {
                            System.out.println("Keine gültige Eingabe!");
                        }
                    } while (!userResponse.matches(regex));

                    do {
                        System.out.println("Bitte geben sie die Strasse des Kunden ein");
                        userResponse = console.readLine();
                        if (userResponse.matches(regex)) {
                            kunde.setStrasse(userResponse);
                        } else {
                            System.out.println("Keine gültige Eingabe!");
                        }
                    } while (!userResponse.matches(regex));

                    do {
                        System.out.println("Bitte geben sie die Hausnummer des Kunden ein");
                        userResponse = console.readLine();
                        if (userResponse.matches(hausRegex)) {
                            kunde.setHausnummer(userResponse);
                        } else {
                            System.out.println("Keine gültige Eingabe!");
                        }
                    } while (!userResponse.matches(hausRegex));

                    do {
                        System.out.println("Bitte geben sie die Postleitzahl des Kunden ein");
                        userResponse = console.readLine();
                        if (userResponse.matches(plzRegex)) {
                            kunde.setPlz(Integer.parseInt((userResponse)));
                        } else {
                            System.out.println("Keine gültige Eingabe!");
                        }
                    } while (!userResponse.matches(plzRegex));

                    do {
                        System.out.println("Bitte geben sie den Ort des Kunden ein");
                        userResponse = console.readLine();
                        if (userResponse.matches(regex)) {
                            kunde.setOrt(userResponse);
                        } else {
                            System.out.println("Keine gültige Eingabe!");
                        }
                    } while (!userResponse.matches(regex));
                    SQLDatabaseConnection.insertKundeIntoDB(kunde);
                    System.out.println("Kunde wurde erfolgreich angelegt.");
                    System.out.println();
                } else {
                    System.out.println("Keine gültige Auswahl, bitte versuchen Sie es erneut.");
                }
            }
        } else {
            System.out.println("Bitte starten Sie das Programm von der Konsole aus.");
        }
    }
}
