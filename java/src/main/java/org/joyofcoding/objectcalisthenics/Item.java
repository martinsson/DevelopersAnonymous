package org.joyofcoding.objectcalisthenics;

public class Item {
    private String name;
    private int sellIn;
    private int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.quality = quality;
        this.sellIn = sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality;
    }

    void update() {
        if (getName().equals("Aged Brie")) {
            increaseQuality();
            decreaseSellIn();
            if (getSellIn() < 0) {
                increaseQuality();
            }
        } else if (getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (getQuality() < 50) {
                increaseQuality();

                if (getSellIn() < 11) {
                    increaseQuality();
                }

                if (getSellIn() < 6) {
                    increaseQuality();
                }
            }
            decreaseSellIn();
            if (getSellIn() < 0) {
                setQuality(getQuality() - getQuality());
            }
        } else if (getName().equals("Sulfuras, Hand of Ragnaros")) {
        } else {
            decreaseQuality();
            decreaseSellIn();
            if (getSellIn() < 0) {
                decreaseQuality();
            }
        }

    }

    private void decreaseSellIn() {
        setSellIn(getSellIn() - 1);
    }

    private void decreaseQuality() {
        if (getQuality() > 0) {
            setQuality(getQuality() - 1);
        }
    }

    private void increaseQuality() {
        if (getQuality() < 50) {
            setQuality(getQuality() + 1);
        }
    }
}
