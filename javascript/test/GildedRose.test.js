var chai = require("chai"),
    assert = chai.assert,
    GildedRose = require('../src/GildedRose.js'),
    Item = require('../src/Item.js'),
    MersenneTwister = require('./lib/mersenne-twister.js');

chai.should();

suite('GildedRose', function () {

    var gildedRose,
        items

    setup(function () {
        gildedRose = new GildedRose();
        items = gildedRose.makeItems();
    });

    test('after one day', function () {
        gildedRose.updateQuality(items)
        // assert.equal(items[0], ...)
    });

});
