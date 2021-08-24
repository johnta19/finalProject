package ui.DAO;

import lombok.Builder;

@Builder
public class Item implements ItemDAO {
    private String name;
    private String price;
    private String quantity;

    /*public Item(String name, String price, String quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }*/

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPrice() {
        return price;
    }

    @Override
    public String getQuantity() {
        return quantity;
    }
}
