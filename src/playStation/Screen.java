package playStation;

public class Screen {
    private String name;
    private int quantity;

    private int available;

    public Screen(String name, int quantity) {
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
