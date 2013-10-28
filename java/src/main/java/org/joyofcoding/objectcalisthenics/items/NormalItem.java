package org.joyofcoding.objectcalisthenics.items;

import org.joyofcoding.objectcalisthenics.quality.QualityBetween0And50;

public class NormalItem {
    private String name;
    private int sellIn;
    protected QualityBetween0And50 quality;

    public NormalItem(String name, int sellIn, int quality) {
        this.name = name;
        this.quality = new QualityBetween0And50(quality);
        this.sellIn = sellIn;
    }

    public void update() {
        quality.decrease();
        decreaseSellIn();
        if (isPassedDate()) {
            quality.decrease();
        }
    }
    
    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality.getQuality();
    }

    protected void decreaseSellIn() {
        sellIn--;
    }

    protected boolean isPassedDate() {
        return sellIn < 0;
    }

}
