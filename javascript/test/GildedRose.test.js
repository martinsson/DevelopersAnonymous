var chai = require("chai"),
    assert = chai.assert,
    GildedRose = require('../src/GildedRose.js'),
    Item = require('../src/Item.js'),
    MersenneTwister = require('./lib/mersenne-twister.js');

chai.should();

suite('GildedRose', function () {

    var gildedRose,
        items,
        MAX_BACKSTAGE_SELLIN = 30,
        MAX_QUALITY = 50,
        rand = new MersenneTwister(3456789);

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

    test('after three days', function () {
        repeatUpdateQuality(3);
        assertItemsCollectionHasExpectedItems([
            {sellIn: 7,  quality: 17, name: "+5 Dexterity Vest"},
            {sellIn: -1, quality: 4,  name: "Aged Brie"},
            {sellIn: 2,  quality: 4,  name: "Elixir of the Mongoose"},
            {sellIn: 0,  quality: 80, name: "Sulfuras, Hand of Ragnaros"},
            {sellIn: 12, quality: 23, name: "Backstage passes to a TAFKAL80ETC concert"},
            {sellIn: 0,  quality: 3,  name: "Conjured Mana Cake"}
        ]);
    });


    test('after a shiftload of days', function () {
        repeatUpdateQuality(500);
        assertItemsCollectionHasExpectedItems([
            {sellIn: -490, quality: 0,  name: "+5 Dexterity Vest"},
            {sellIn: -498, quality: 50, name: "Aged Brie"},
            {sellIn: -495, quality: 0,  name: "Elixir of the Mongoose"},
            {sellIn: 0,    quality: 80, name: "Sulfuras, Hand of Ragnaros"},
            {sellIn: -485, quality: 0,  name: "Backstage passes to a TAFKAL80ETC concert"},
            {sellIn: -497, quality: 0,  name: "Conjured Mana Cake"}
        ]);
    });

    test('backstage pass golden copy', function () {
        items = aBunchOfBackstagePasses()
        repeatUpdateQuality(11);
        extractQuality().should.deep.equal([ 47, 50, 0, 0, 21, 45, 50, 0, 0,
            17, 50, 0, 26, 35, 42, 27, 50, 43, 0, 0, 50, 20, 12, 0, 30, 50, 20,
            11, 0, 48, 26, 50, 50, 0, 45, 40, 27, 50, 47, 50, 11, 32, 22, 50,
            50, 19, 50, 29, 44, 0, 20, 48, 50, 0, 37, 50, 29, 48, 36, 50, 50,
            14, 0, 40, 47, 50, 0, 0, 0, 50, 50, 0, 11, 0, 50, 12, 0, 50, 46,
            49, 37, 50, 50, 0, 39, 0, 11, 42, 16, 32, 50, 34, 34, 50, 0, 35,
            30, 0, 50, 50 ]);
        extractSellIn().should.deep.equal([ 7, 1, -4, -6, 3, 3, 16, -1, -4, 8,
            14, -6, 9, 10, 10, 19, 9, 11, -8, -11, 9, 19, 16, -11, 17, 4, 6,
            12, -1, 13, 18, 14, 16, -3, 1, 19, 17, 19, 14, 6, 16, 12, 9, 2, 5,
            13, 10, 15, 14, -2, 10, 8, 18, -8, 12, 18, 12, 18, 10, 5, 5, 11,
            -2, 16, 1, 1, -7, -5, -7, 13, 14, -5, 19, -6, 3, 12, -10, 2, 6, 10,
            15, 5, 0, -3, 19, -6, 12, 1, 14, 7, 1, 11, 4, 0, -6, 4, 0, -8, 15,
            9 ]);
    });

    function extractQuality() {
        var properties = [ ];
        for (var i = 0, n = items.length ; i < n ; ++i) {
            properties.push(items[i].getQuality());
        }
        return properties;
    }

    function extractSellIn() {
        var properties = [ ];
        for (var i = 0, n = items.length ; i < n ; ++i) {
            properties.push(items[i].getSellIn());
        }
        return properties;
    }

    function repeatUpdateQuality(times) {
        for (var i = 0 ; i < times ; i++) {
            gildedRose.updateQuality(items);
        }
    }

    function aBunchOfBackstagePasses() {
        var listOfPasses = [ ];
        for (var i = 0; i < 100; i++) {
            listOfPasses.push(aRandomBackstagePass());
        }
        return listOfPasses;
    }

    function randomSellIn() {
        return randNextInt(MAX_BACKSTAGE_SELLIN);
    }

    function randomQuality() {
        return randNextInt(MAX_QUALITY);
    }

    function randNextInt(max) {
        return Math.floor(rand.random() * (max + 1));
    }

    function aRandomBackstagePass() {
        var quality = randomQuality(),
            sellIn  = randomSellIn();
        return new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
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
                message + ' has quality = ' + found_item.getQuality() + ', expected ' + expected_item.quality
            );
            assert(found_item.getSellIn() === expected_item.sellIn,
                message + ' has quality = ' + found_item.getSellIn() + ', expected ' + expected_item.sellIn
            );
        }
    }
});
