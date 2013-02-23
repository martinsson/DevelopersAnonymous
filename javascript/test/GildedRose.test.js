var chai = require("chai"),
    assert = chai.assert,
    GildedRose = require('../src/GildedRose.js'),
    Item = require('../src/Item.js');

chai.should();
chai.use(require('chai-things'));

suite('GildedRose', function () {

    var gildedRose, items;

    setup(function () {
        gildedRose = new GildedRose();
        items = gildedRose.makeItems();
    });

    test('after one day', function () {
        repeatUpdateQuality(1);
        assertItemsCollectionHasExpectedItems([
            {sellIn: 9,  quality: 19, name: "+5 Dexterity Vest"},
            {sellIn: 1,  quality: 1,  name: "Aged Brie"},
            {sellIn: 4,  quality: 6,  name: "Elixir of the Mongoose"},
            {sellIn: 0,  quality: 80, name: "Sulfuras, Hand of Ragnaros"},
            {sellIn: 14, quality: 21, name: "Backstage passes to a TAFKAL80ETC concert"},
            {sellIn: 2,  quality: 5,  name: "Conjured Mana Cake"}
        ]);
    });

    function repeatUpdateQuality(times) {
        for (var i = 0 ; i < times ; i++) {
            gildedRose.updateQuality(items);
        }
    }

    function assertItemsCollectionHasExpectedItems(expected_items) {
        var item, expected;
        for (var i = 0, n = expected_items.length ; i < n ; ++i) {
            expected = expected_items[i];
            item     = findItemByName(expected.name);
            assertExpectedItemProperties(item, expected);
        }
    }

    function findItemByName(name) {
        var found_item = false;
        for (var i = 0, n = items.length ; found_item === false && i < n ; ++i) {
            if (items[i].getName() === name) {
                found_item = items[i];
            }
        }
        return found_item;
    }

    function assertExpectedItemProperties(found_item, expected_item) {
        var message = 'item "' + expected_item.name + '"';
        assert(found_item, message + ' is missing');
        if (found_item) {
            assert(found_item.getQuality() === expected_item.quality,
                message + ' has quality = ' + found_item.getQuality() + ', expeted ' + expected_item.quality
            );
            assert(found_item.getSellIn() === expected_item.sellIn,
                message + ' has quality = ' + found_item.getSellIn() + ', expeted ' + expected_item.sellIn
            );
        }
    }
});
