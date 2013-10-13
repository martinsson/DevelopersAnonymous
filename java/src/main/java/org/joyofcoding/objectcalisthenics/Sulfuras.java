package org.joyofcoding.objectcalisthenics;

public class Sulfuras extends Item {

    public Sulfuras(int sellIn, int quality) {
        super("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }

    void update() {
        // magic item, doesn't decay
    }

}
