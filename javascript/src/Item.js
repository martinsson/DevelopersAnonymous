"use strict";

function Item(name, sellIn, quality) {
    this.name    = name;
    this.quality = quality;
    this.sellIn  = sellIn;
}

Item.prototype = {

    setSellIn: function (sellIn) {
        this.sellIn = sellIn;
    },

    setQuality: function (quality) {
        this.quality = quality;
    },

    getName: function () {
        return this.name;
    },

    getSellIn: function () {
        return this.sellIn;
    },

    getQuality: function () {
        return this.quality;
    }
}
