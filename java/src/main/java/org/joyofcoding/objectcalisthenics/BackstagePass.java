package org.joyofcoding.objectcalisthenics;

public class BackstagePass extends Item {

    public BackstagePass(int sellIn, int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    void update() {
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
