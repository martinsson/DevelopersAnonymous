package org.joyofcoding.objectcalisthenics.items;

public class BackstagePass extends NormalItem {

    public BackstagePass(int sellIn, int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    public void update() {
        decreaseSellIn();
        if (isPassedDate()) {
            quality.dropToZero();
            
        } else if (daysBeforeConcertIsLessThan(5)) {
            quality.increaseBy(3);
            
        }else if (daysBeforeConcertIsLessThan(10)) {
            quality.increaseBy(2);
            
        } else {
            quality.increaseBy(1);
            
        }

    }

    private boolean daysBeforeConcertIsLessThan(int days) {
        return getSellIn() < days;
    }

}
