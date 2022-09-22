package playStation;

public class Console {

    private String name;
    private int quantity;

    private int available;

    private String availableTime;

    public Console(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.available = this.quantity;
        this.availableTime="9:00";
    }

    public void setName(String name){
        this.name=name;

    }
    public String getName(){
        return this.name;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void setAvailable(int available) {
        this.available = available;
    }
    public int getAvailable(){
        return this.available;
    }
    public void setAvailableTime(String availableTime){
        this.availableTime = availableTime ;
    }
    public String getAvailableTime(){
        return this.availableTime;
    }
    @Override
    public String toString() {
        return "name: " + this.getName() + " | quantity: " + this.getQuantity() + " | available "+this.getName()+": "+this.getAvailable() ;
    }
}
