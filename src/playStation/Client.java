package playStation;

public class Client {
    private String firstname;
    private String lastname;

    public String firstname(){
        return this.firstname;
    }
    public void firstname(String firstname){
        this.firstname = firstname;
    }
    public String lastname(){
        return this.lastname;
    }
    public void lastname(String lastname){
        this.lastname = lastname;
    }

    public Client (String firstname, String lastname,String duration ,String gameName,String startingTime,int postNum){
        this.firstname(firstname);
        this.lastname(lastname);
    }

    @Override
    public String toString() {
        return "firstname: " + this.firstname() + " | lastname: " + this.lastname();
    }
}
