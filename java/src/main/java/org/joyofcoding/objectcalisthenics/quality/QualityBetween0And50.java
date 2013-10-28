package org.joyofcoding.objectcalisthenics.quality;

public class QualityBetween0And50 {
    private static final int QUALITY_FLOOR = 0;
    private static final int QUALITY_CEILING = 50;
    private int quality;

    public QualityBetween0And50(int quality) {
        this.quality = quality;
    }

    public int getQuality() {
        return quality;
    }

    public void increase() {
        increaseBy(1);
    }

    public void decrease() {
        decreaseBy(1);
    }

    public void dropToZero() {
        this.quality = QUALITY_FLOOR;
    }

    public void decreaseBy(int amount) {
        quality -= amount;
        if (quality < QUALITY_FLOOR)
            quality = QUALITY_FLOOR;
    }

    public void increaseBy(int amount) {
        quality += amount;
        if (quality > QUALITY_CEILING)
            quality = QUALITY_CEILING;

    }
}