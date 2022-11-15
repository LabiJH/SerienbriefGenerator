
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.Console;
import java.util.ArrayList;

/**
 * FahrzeugImport, Importiert Fahrzeuge aus der Fahrzeuge.xml in die SQL Datenbank.
 * XML muss im gleichen Verzeichnis liegen wo das Programm ausgeführt wird.
 * @author: Labinot Jaha
 * @version: 1.0
 */

public class FahrzeugImport {
    public static void main(String[] args) {
        Console console = System.console();
        ArrayList<Fahrzeug> fahrzeugArrayList = new ArrayList<>(); // Liste, um die Fahrzeug Objekte zu sammeln

        /* Check ob von der Konsole aus gestartet wird */
        if(console != null){
            try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("Fahrzeuge.xml");     // Link der XML Datei
            NodeList nodes = document.getElementsByTagName("Fahrzeug");  // Nodeliste mit den Fahrzeugen

                for (int i = 0; i < nodes.getLength(); i++) {
                    Fahrzeug fahrzeug = new Fahrzeug();
                    // Inhalt der Fahrzeug Nodes in die einzelnen Variablen schreiben
                    fahrzeug.setTyp(nodes.item(i).getChildNodes().item(1).getTextContent());
                    fahrzeug.setHersteller(nodes.item(i).getChildNodes().item(3).getTextContent());
                    fahrzeug.setBezeichnung(nodes.item(i).getChildNodes().item(5).getTextContent());
                    fahrzeug.setLeistung(Integer.parseInt(nodes.item(i).getChildNodes().item(7).getTextContent()));
                    fahrzeug.setPreis(Float.parseFloat(nodes.item(i).getChildNodes().item(9).getTextContent()));
                    fahrzeugArrayList.add(fahrzeug);
                }
                SQLDatabaseConnection.insertAutoIntoDB(fahrzeugArrayList);

             }catch (Exception ex) {
                System.out.println(ex);
            }
        } else{
            System.out.println("Bitte starten Sie das Programm über die Konsole!");
        }
    }
}

