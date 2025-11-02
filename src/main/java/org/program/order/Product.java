package org.program.order;

public class Product {
    private final String name;
    private final Unit unit;
    private double pricePerUnit;

    public Product(String name, Unit unit, double pricePerUnit) {
        this.name = name;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getName() { return name; }
    public Unit getUnit() { return unit; }
    public double getPricePerUnit() { return pricePerUnit; }
}
