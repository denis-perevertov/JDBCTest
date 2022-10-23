package entity;

public class Order {

    private int order_id;
    private int user_id;
    private String description;
    private double total_price;

    public Order() {
    }

    public int getId() {
        return order_id;
    }

    public void setId(int order_id) {
        this.order_id = order_id;
    }

    public int getUser() {
        return user_id;
    }

    public void setUser(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalPrice() {
        return total_price;
    }

    public void setTotalPrice(double total_price) {
        this.total_price = total_price;
    }
}
