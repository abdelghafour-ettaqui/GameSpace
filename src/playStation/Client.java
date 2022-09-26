package playStation;

public class Client {
    private String firstname;
    private String lastname;
    private String duration;
    private String gameName;
    private String startingTime;
    private int postNum;
    private double  amountPaid;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Client (String firstname, String lastname,String duration ,String gameName,String startingTime,int postNum,double amountPaid){

        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setAmountPaid(amountPaid);
        this.setDuration(duration);
        this.setGameName(gameName);
        this.setPostNum(postNum);
        this.setStartingTime(startingTime);

    }

    @Override
    public String toString() {
        return "firstname: " + this.getFirstname() + " | lastname: " + this.getLastname();
    }
}
