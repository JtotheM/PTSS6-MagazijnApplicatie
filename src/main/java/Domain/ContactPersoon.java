package Domain;

/**
 * ContactPersoon created by @author RY Jin on Jun 20, 2015
 */
public class ContactPersoon {

    private String naam;
//    private String AchterNaam;
    private String telefoon;

    public ContactPersoon(String naam, String telefoon) {
        this.naam = naam;
//        this.AchterNaam = achternaam;
        this.telefoon = telefoon;
    }
    
    public ContactPersoon(){
        
    }

    public void setNaam(String Naam) {
        this.naam = Naam;
    }

//    public void setAchterNaam(String AchterNaam) {
//        this.AchterNaam = AchterNaam;
//    }

    public void setTelefoon(String Telefoon) {
        this.telefoon = Telefoon;
    }

    public String getNaam() {
        return this.naam;
    }

//    public String getAchterNaam() {
//        return this.AchterNaam;
//    }

    public String getTelefoon() {
        return this.telefoon;
    }
}
