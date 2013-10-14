package org.joyofcoding.objectcalisthenics.items;


public class Conjured extends NormalItem {

    public Conjured(String name, int sellIn, int quality) {
        super("Conjured " + name, sellIn, quality);
    }

    @Override
    public void update() {
        quality.decrease();
        decreaseSellIn();
        if (isPassedDate()) {
            quality.decreaseBy(2);
        }
    }
}
