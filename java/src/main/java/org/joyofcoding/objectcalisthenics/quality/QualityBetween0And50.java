package org.joyofcoding.objectcalisthenics.quality;

public class QualityBetween0And50 {
    private int quality;

    public QualityBetween0And50(int quality) {
        this.quality = quality;
    }

    public int getQuality() {
        return quality;
    }

    public void increase() {
        if (getQuality() < 50) {
            quality++;
        }
    }

    public void decrease() {
        if (getQuality() > 0) {
            quality--;
        }
    }

    public void dropToZero() {
        this.quality = 0;
    }
}