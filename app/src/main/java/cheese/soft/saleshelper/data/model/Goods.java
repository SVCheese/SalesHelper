package cheese.soft.saleshelper.data.model;

public class Goods {

    private int id;
    private int inner_code;
    private int group_id;
    private String name;
    private int quantity;
    private double purchase_price;
    private double price;

    public Goods() {

    }

    public Goods(int id, int inner_code, int group_id, String name, int quantity, double purchase_price, double price) {
        this.id = id;
        this.inner_code = inner_code;
        this.group_id = group_id;
        this.name = name;
        this.quantity = quantity;
        this.purchase_price = purchase_price;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInner_code() {
        return inner_code;
    }

    public void setInner_code(int inner_code) {
        this.inner_code = inner_code;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
