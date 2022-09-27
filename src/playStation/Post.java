package playStation;

import java.util.ArrayList;

public class Post {
    private int postNumber;
    private String console;
    private String screen;
    private String available;

    ArrayList <String> notAvailableTime=new ArrayList <String>() ;

    public Post(String screen,String console,int postNumber,String available) {

        this.postNumber=postNumber;

        this.console=console;

        this.screen=screen;

        this.available=available;

    }
    public int getPostNumber(){
        return postNumber;
    }
    public void setPostNumber(int postNumber){
        this.postNumber=postNumber;
    }

    public String getConsole() {
        return console;
    }

    public String getScreen() {
        return screen;
    }

    public String getAvailable() {
        return available;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public ArrayList<String> getNotAvailableTime() {
        return notAvailableTime;
    }

    public void setNotAvailableTime(String notAvailableTime)
    {
        this.notAvailableTime.add(notAvailableTime);
    }

    @Override
    public String toString() {
        return "console name: " + this.getConsole() + " | screen name: " + this.getScreen() + "Post number: "+this.getPostNumber() ;
    }


}
