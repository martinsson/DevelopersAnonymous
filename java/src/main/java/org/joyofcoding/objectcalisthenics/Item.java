package org.joyofcoding.objectcalisthenics;

public class Item {
    private String name;
    private int sellIn;
    protected Quality quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.quality = new Quality(quality);
        this.sellIn = sellIn;
    }

    void update() {
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
        return getSellIn() < 0;
    }

}
