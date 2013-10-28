package org.joyofcoding.objectcalisthenics.items;

public class AgedBrie extends NormalItem {

    public void update() {
        quality.increase();
        decreaseSellIn();
        if (isPassedDate()) {
            quality.increase();
        }
    }

    public AgedBrie(int sellIn, int quality) {
        super("Aged Brie", sellIn, quality);
    }
    
}
