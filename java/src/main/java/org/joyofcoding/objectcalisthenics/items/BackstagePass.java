package org.joyofcoding.objectcalisthenics.items;

public class BackstagePass extends NormalItem {

    public BackstagePass(int sellIn, int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    public void update() {
        decreaseSellIn();
        quality.increase();

        if (getSellIn() < 10) {
            quality.increase();
        }

        if (getSellIn() < 5) {
            quality.increase();
        }
        if (isPassedDate()) {
            quality.dropToZero();
        }

    }

}
