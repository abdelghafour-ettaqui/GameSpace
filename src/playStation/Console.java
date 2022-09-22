package playStation;

public class Console {

    private String name;
    private int quantity;

    private int available;

    public Console(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.available = this.quantity;
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
}
