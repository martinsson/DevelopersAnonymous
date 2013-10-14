package org.joyofcoding.objectcalisthenics.items;

public class Sulfuras_HandOfRagnaros extends NormalItem {

    public Sulfuras_HandOfRagnaros(int sellIn, int quality) {
        super("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }

    public void update() {
        // magic item, doesn't decay
    }

}
