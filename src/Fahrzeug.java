/**
 * Die Klasse Fahrzeug repräsentiert die Fahrzeuge des Fahrzeughauses
 */
public class Fahrzeug{
    private String typ;
    private String bezeichnung;
    private String hersteller;
    private int leistung;
    private float preis;

    /**
     * Standard Contructor
     */
    public Fahrzeug(){

    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public int getLeistung() {
        return leistung;
    }

    public void setLeistung(int leistung) {
        this.leistung = leistung;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }
}
