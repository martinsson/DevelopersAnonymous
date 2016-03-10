package org.joyofcoding.objectcalisthenics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joyofcoding.objectcalisthenics.assertions.ItemsAssert;
import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {
    private GildedRose gildedRose;
    private List<Item> items;

    @Before
    public void setup() {
        gildedRose = new GildedRose();
        items = gildedRose.makeItems();
    }
    
    @Test public void 
    after_one_day() throws Exception {
        gildedRose.updateQuality(items);
        // Assertions.assertThat(items[0])...
    }

}
