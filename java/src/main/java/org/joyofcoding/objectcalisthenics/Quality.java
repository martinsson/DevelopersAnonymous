package org.joyofcoding.objectcalisthenics;

public class Quality {
    private int quality;

    public Quality(int quality) {
        this.quality = quality;
    }

    public int getQuality() {
        return quality;
    }

    void increase() {
        if (getQuality() < 50) {
            quality++;
        }
    }

    void decrease() {
        if (getQuality() > 0) {
            quality--;
        }
    }

    void dropToZero() {
        this.quality = 0;
    }
}