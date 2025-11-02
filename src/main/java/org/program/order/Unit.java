package org.program.order;

public enum Unit {
    KG ("kg"),
    PIECE ("piece"),
    M2 ("m2"),
    M ("m"),
    L ("l");

    private String name;
    Unit(String name) {
        this.name = name;
    }
    public String getName() { return name; }
}