package org.joyofcoding.objectcalisthenics;

import java.util.ArrayList;
import java.util.List;

import org.joyofcoding.objectcalisthenics.items.AgedBrie;
import org.joyofcoding.objectcalisthenics.items.BackstagePass;
import org.joyofcoding.objectcalisthenics.items.NormalItem;
import org.joyofcoding.objectcalisthenics.items.Sulfuras_HandOfRagnaros;

public class GildedRose {
    public static void main(String[] args) {
        GildedRose gildedRose = new GildedRose();
        gildedRose.updateQuality(gildedRose.makeItems());
    }

    public List<NormalItem> makeItems() {
        List<NormalItem> items = new ArrayList<NormalItem>();
        items.add(new NormalItem("+5 Dexterity Vest", 10, 20));
        items.add(new AgedBrie(2, 0));
        items.add(new NormalItem("Elixir of the Mongoose", 5, 7));
        items.add(new Sulfuras_HandOfRagnaros(0, 80));
        items.add(new BackstagePass(15, 20));
        items.add(new NormalItem("Conjured Mana Cake", 3, 6));
        return items;
    }

    public void updateQuality(List<NormalItem> list) {
        List<NormalItem> items = list;
        for (NormalItem item : items) {
            item.update();
        }
    }

}
