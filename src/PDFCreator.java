import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.util.ArrayList;
/**
 * PDFCreator, erstellt Serienbriefe im .pdf Format mit den Kunden und Fahrzeug Daten aus der Datenbank.
 * Dokumente werden im selben Ordner wo das Programm ausgeführt wird im Titelformat Vorname_Nachname.pdf gespeichert.
 * @author: Labinot Jaha
 * @version: 1.0
 */
public class PDFCreator {

    public static void main(String[] args) {

        ArrayList<Kunde> kundenList = SQLDatabaseConnection.getKundenList();
        ArrayList<Fahrzeug> fahrzeugList = SQLDatabaseConnection.getFahrzeugList();

        for (int i = 0; i < kundenList.size(); i++) {
            PDFont helveticaBold = PDType1Font.HELVETICA_BOLD;
            PDFont helvetica = PDType1Font.HELVETICA;
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            System.out.println("Creating " + kundenList.get(i).getName() + ".pdf");
            try {
                document.addPage(page);
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                contentStream.beginText();
                contentStream.setFont(helveticaBold, 12);
                contentStream.newLineAtOffset(40, 750);
                contentStream.showText(kundenList.get(i).getVorname() + " " + kundenList.get(i).getName());
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText(kundenList.get(i).getStrasse() + " " + kundenList.get(i).getHausnummer());
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText(kundenList.get(i).getPlz() + " " + kundenList.get(i).getOrt());
                contentStream.newLineAtOffset(0, -80);
                contentStream.setFont(helvetica, 12);
                contentStream.showText("Sehr geehrte/er " + kundenList.get(i).getVorname() + " " + kundenList.get(i).getName() + ",");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Wir möchten Sie auf unsere derzeitige Flotte an Fahrzeugen aufmerksam machen.");
                contentStream.setFont(helveticaBold, 12);
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Fahrzeugtyp");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Bezeichnung");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Hersteller");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Leistung");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Verkaufspreis");
                contentStream.setFont(helvetica, 12);

                for (int j = 0; j < fahrzeugList.size(); j++){
                    contentStream.newLineAtOffset(-400, -20);
                    contentStream.showText(fahrzeugList.get(j).getTyp());
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(fahrzeugList.get(j).getBezeichnung());
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(fahrzeugList.get(j).getHersteller());
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(fahrzeugList.get(j).getLeistung() + "kW");
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText(fahrzeugList.get(j).getPreis() + "€");
                }
                contentStream.newLineAtOffset(-400, -40);
                contentStream.showText("Wir freuen uns auf Ihren nächsten Besuch.");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Mit freundlichen Grüßen,");
                contentStream.newLineAtOffset(0, -10);
                contentStream.showText("Ihr Autohaus Team.");

                contentStream.endText();
                contentStream.close();          // IMPORTANT!!! Always close.
                document.save(kundenList.get(i).getVorname() + "_" + kundenList.get(i).getName() + ".pdf");
                document.close();

            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}

