package org.joyofcoding.objectcalisthenics;

public class AgedBrie extends Item {

    public AgedBrie(int sellIn, int quality) {
        super("Aged Brie", sellIn, quality);
    }

    void update() {
        quality.increase();
        decreaseSellIn();
        if (isPassedDate()) {
            quality.increase();
        }
    }

}
