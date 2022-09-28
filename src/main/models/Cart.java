package src.main.models;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cart {
    ArrayList<Item> items;

    public Cart() {
        this.items = new ArrayList<Item>();
    }

    public Item getItem(int index) {
        return new Item(this.items.get(index));
    }

    public void setItem(int index, Item item) {
        this.items.set(index, new Item(item));
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < this.items.size(); i++) {
            temp += this.items.get(i).toString();
            temp += "\n";
        }
        return temp;
    }

    public boolean add(Item item) {
        if (items.contains(item)) {
            return false;
        }
        items.add(new Item(item));
        return true;
    }

    public void remove(String name) {
        if(items.isEmpty()){
            throw new IllegalStateException("The cart is empty");
        }
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                items.remove(i);
            }
        }
    }

    public boolean contains(Item item) {
        return items.contains(item);
    }

    public double getSubtotal() {
        double subtotal = 0;

        for (int i = 0; i < items.size(); i++) {
            subtotal += items.get(i).getPrice();
        }
        return subtotal;
    }

    public double getTax(double subtotal) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(subtotal * 0.13));

    }

    public double getTotal(double subtotal, double tax) {
        return subtotal + tax;
    }

    public String checkout() {
        if(items.isEmpty()){
            throw new IllegalStateException("The cart is empty");
        }
        return "\tRECEIPT\n\n" +
                "\tSubtotal: $" + getSubtotal() + "\n" +
                "\tTax: $" + getTax(getSubtotal()) + "\n" +
                "\tTotal: $" + getTotal(getSubtotal(), getTax(getSubtotal())) + "\n";
    }

    public void clear() {
        
        items.clear();
    }

}
