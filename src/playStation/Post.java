package playStation;

public class Post {
    private int postNumber;
    private String console;
    private String screen;
    private boolean available;
    private String availableTime;

    public Post(String screen,String console,int postNumber) {

        this.postNumber=postNumber;

        this.console=console;

        this.screen=screen;

        this.available=true;

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

    public boolean getAvailable() {
        return available;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    @Override
    public String toString() {
        return "console name: " + this.getConsole() + " | screen name: " + this.getScreen() + "it will be available after: "+this.getAvailableTime() ;
    }


}
