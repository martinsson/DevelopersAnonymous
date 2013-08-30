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
        if (!getName().equals("Aged Brie") && !getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (getQuality() > 0) {
                if (!getName().equals("Sulfuras, Hand of Ragnaros")) {
                    setQuality(getQuality() - 1);
                }
            }
        } else {
            if (getQuality() < 50) {
                setQuality(getQuality() + 1);
    
                if (getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (getSellIn() < 11) {
                        if (getQuality() < 50) {
                            setQuality(getQuality() + 1);
                        }
                    }
    
                    if (getSellIn() < 6) {
                        if (getQuality() < 50) {
                            setQuality(getQuality() + 1);
                        }
                    }
                }
            }
        }
    
        if (!getName().equals("Sulfuras, Hand of Ragnaros")) {
            setSellIn(getSellIn() - 1);
        }
    
        if (getSellIn() < 0) {
            if (!getName().equals("Aged Brie")) {
                if (!getName().equals("Backstage passes to a TAFKAL80ETC concert")) {
                    if (getQuality() > 0) {
                        if (!getName().equals("Sulfuras, Hand of Ragnaros")) {
                            setQuality(getQuality() - 1);
                        }
                    }
                } else {
                    setQuality(getQuality() - getQuality());
                }
            } else {
                if (getQuality() < 50) {
                    setQuality(getQuality() + 1);
                }
            }
        }
    }
}
