"use strict";

(function () {
    var gildedRose = new GildedRose();
    gildedRose.updateQuality(gildedRose.makeItems());
})();

function GildedRose() {
}

GildedRose.prototype = {

    makeItems: function () {
        var items = [ ];
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));
        return items;
    },

    updateQuality: function (list) {
        var items = list;
        for (i = 0; i < items.size(); i++) {
            if (!items[i].getName() === "Aged Brie"
                    && !items[i].getName()
                             === "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].getQuality() > 0) {
                    if (!items[i].getName()
                             === "Sulfuras, Hand of Ragnaros") {
                        items[i].setQuality(items[i].getQuality() - 1);
                    }
                }
            } else {
                if (items[i].getQuality() < 50) {
                    items[i].setQuality(items[i].getQuality() + 1);

                    if (items[i].getName()
                             === "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getSellIn() < 11) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(
                                        items[i].getQuality() + 1);
                            }
                        }

                        if (items[i].getSellIn() < 6) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(
                                        items[i].getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (!items[i].getName() === "Sulfuras, Hand of Ragnaros") {
                items[i].setSellIn(items[i].getSellIn() - 1);
            }

            if (items[i].getSellIn() < 0) {
                if (!items[i].getName() === "Aged Brie") {
                    if (!items[i].getName()
                             === "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getQuality() > 0) {
                            if (!items[i].getName()
                                     === "Sulfuras, Hand of Ragnaros") {
                                items[i].setQuality(
                                        items[i].getQuality() - 1);
                            }
                        }
                    } else {
                        items[i].setQuality(
                                items[i].getQuality()
                                        - items[i].getQuality());
                    }
                } else {
                    if (items[i].getQuality() < 50) {
                        items[i].setQuality(items[i].getQuality() + 1);
                    }
                }
            }
        }
    }
}
