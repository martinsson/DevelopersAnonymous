package org.joyofcoding.objectcalisthenics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joyofcoding.objectcalisthenics.assertions.ItemsAssert;
import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {
    private static final int MAX_BACKSTAGE_SELLIN = 30;
    private static final int MAX_QUALITY = 50;
    private GildedRose gildedRose;
    private List<Item> items;
    private Random rand = new Random(3456789);

    @Before
    public void setup() {
        gildedRose = new GildedRose();
        items = gildedRose.makeItems();
    }
    
    @Test public void 
    after_one_day() throws Exception {
        repeatUpdateQuality(1);

        ItemsAssert.assertThat(items)
        	 .containsOnlyItemNames("+5 Dexterity Vest", 
                                   "Aged Brie", 
                                   "Elixir of the Mongoose", 
                                   "Sulfuras, Hand of Ragnaros", 
                                   "Backstage passes to a TAFKAL80ETC concert", 
                                   "Conjured Mana Cake")
             .containsOnlyItemQualities(19, 1, 6, 80, 21, 5)
             .containsOnlyItemSellIns(9, 1, 4, 0, 14, 2);
        
    }
    
    @Test public void 
    after_one_day_with_sufuras_having_sellIn_lesser_than_zero_and_quality_greater_than_zero() throws Exception {
    	 items = new ArrayList<Item>();
 		 		items.add(new Item("Sulfuras, Hand of Ragnaros", -1, 1));
    	repeatUpdateQuality(1);
    	
    	ItemsAssert.assertThat(items)
    	.containsOnlyItemNames("Sulfuras, Hand of Ragnaros")
    	.containsOnlyItemQualities(1)
    	.containsOnlyItemSellIns(-1);
    }

    @Test public void 
    after_three_days() throws Exception {
        repeatUpdateQuality(3);

        ItemsAssert.assertThat(items)
    	     .containsOnlyItemNames("+5 Dexterity Vest", 
                                    "Aged Brie", 
                                    "Elixir of the Mongoose", 
                                    "Sulfuras, Hand of Ragnaros", 
                                    "Backstage passes to a TAFKAL80ETC concert", 
                                    "Conjured Mana Cake")
             .containsOnlyItemQualities(17, 4, 4, 80, 23, 3)
             .containsOnlyItemSellIns(7, -1, 2, 0, 12, 0);
    }
    
    @Test public void 
    after_a_shitload_of_days() throws Exception {
         repeatUpdateQuality(500);
         
         ItemsAssert.assertThat(items)
     	     .containsOnlyItemNames("+5 Dexterity Vest", 
                                    "Aged Brie", 
                                    "Elixir of the Mongoose", 
                                    "Sulfuras, Hand of Ragnaros", 
                                    "Backstage passes to a TAFKAL80ETC concert", 
                                    "Conjured Mana Cake")
             .containsOnlyItemQualities(0, 50, 0, 80, 0, 0)
             .containsOnlyItemSellIns(-490, -498, -495, 0, -485, -497);
    }
    
    @Test public void 
    backstage_pass_golden_copy() throws Exception {
        items = aBunchOfBackstagePasses();
        repeatUpdateQuality(11);
        
        ItemsAssert.assertThat(items)
	         .containsOnlyItemQualities(30, 48, 45, 0, 11, 0, 0, 0, 36, 15, 33, 50, 50, 27, 0, 26, 42, 50, 0, 50, 50, 0, 29, 0, 0, 36, 50, 41, 50,
                     0, 49, 25, 0, 12, 0, 50, 0, 0, 0, 43, 0, 50, 23, 27, 33, 0, 0, 37, 0, 43, 0, 0, 45, 50, 22, 43, 0, 30, 14, 44, 50, 0, 17, 0, 17, 50,
                     16, 50, 19, 44, 0, 0, 37, 34, 0, 0, 0, 50, 0, 29, 40, 50, 50, 47, 0, 0, 47, 0, 26, 11, 26, 16, 0, 50, 0, 0, 0, 35, 0, 50)
             .containsOnlyItemSellIns(1, 18, -7, 6, 8, 16, -8, 6, 4, -9, 4, -2, -9, 8, 14, 7, 9, -9, 12, 2, -9, 12, -7, 5, -6, -1, -11, 2, -4, 9,
                     4, 15, 13, -4, -4, 12, -7, 9, -4, -7, 4, 0, 11, 6, -8, 3, 17, 12, 9, -4, 13, -5, 10, 4, 14, 6, 14, 1, -5, -3, 15, 11, -2, -6, -2, 2,
                     -11, 17, 10, 18, 2, 14, -8, -5, 1, -1, 0, 14, 7, 9, -3, 2, -9, -1, -6, 5, -5, 8, -5, 11, 3, 17, 17, 13, 5, 11, 16, -3, 6, 11);
    }

    private void repeatUpdateQuality(int times) {
        for (int i = 0; i < times; i++) {
            gildedRose.updateQuality(items);
        }
    }

    private List<Item> aBunchOfBackstagePasses() {
        List<Item> listOfPasses = new ArrayList<Item>();
        for (int i = 0; i < 100; i++) {
            listOfPasses.add(aRandomBackstagePass());
        }
        return listOfPasses;
    }

    private int randomSellIn() {
        return rand.nextInt(MAX_BACKSTAGE_SELLIN);
    }

    private int randomQuality() {
        return rand.nextInt(MAX_QUALITY);
    }

    private Item aRandomBackstagePass() {
        int quality = randomQuality();
        int sellIn = randomSellIn();
        return new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

}
