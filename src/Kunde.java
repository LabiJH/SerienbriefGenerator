/**
 * Die Klasse Kunde repräsentiert einen Kunden des Fahrzeughauses
 */
public class Kunde {
    private String name;
    private String vorname;
    private String strasse;
    private String hausnummer;
    private Integer plz;
    private String ort;

    /**
     * Standard Constructor
     */
    public Kunde(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVorname(String vorname){
        this.vorname = vorname;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public Integer getPlz() {
        return plz;
    }

    public void setPlz(Integer plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
