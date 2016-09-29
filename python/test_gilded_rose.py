# -*- coding: utf-8 -*-
#
# ToDo: 1 test is missing: test_backstage_pass_golden_copy
#
import pytest
from gilded_rose import Item, GildedRose

names = ["+5 Dexterity Vest",
              "Aged Brie",
              "Elixir of the Mongoose",
              "Sulfuras, Hand of Ragnaros",
              "Backstage passes to a TAFKAL80ETC concert",
              "Conjured Mana Cake"]

@pytest.fixture
def items():
    stock  = [Item(names[0], 10, 20)]
    stock += [Item(names[1], 2, 0)]
    stock += [Item(names[2], 5, 7)]
    stock += [Item(names[3], 0, 80)]
    stock += [Item(names[4], 15, 20)]
    stock += [Item(names[5], 3, 6)]
    return stock


def test_after_one_day(items):

    gilded_rose = GildedRose(items)
    repeat_update_quality(1, gilded_rose)

    expected_name = names
    actual_name = [x.name for x in items]                 
    assert expected_name == actual_name
    
    expected_quality = [19, 1, 6, 80, 21, 5]
    actual_quality = [x.quality for x in items]
    assert actual_quality == expected_quality

    expected_sell_in = [9, 1, 4, 0, 14, 2]
    actual_sell_in = [x.sell_in for x in items]
    assert actual_sell_in == expected_sell_in
    
    
def test_after_three_days(items):

    gilded_rose = GildedRose(items)
    repeat_update_quality(3, gilded_rose)

    expected_name = names
    actual_name = [x.name for x in items]                 
    assert actual_name == expected_name
    
    expected_quality = [17, 4, 4, 80, 23, 3]
    actual_quality = [x.quality for x in items]
    assert actual_quality == expected_quality

    expected_sell_in = [7, -1, 2, 0, 12, 0]
    actual_sell_in = [x.sell_in for x in items]
    assert actual_sell_in == expected_sell_in 
    

def test_after_a_shitload_of_days(items):

    gilded_rose = GildedRose(items)
    repeat_update_quality(500, gilded_rose)
    
    expected_name = names
    actual_name = [x.name for x in items]                 
    assert actual_name == expected_name
    
    expected_quality = [0, 50, 0, 80, 0, 0]
    actual_quality = [x.quality for x in items]
    assert actual_quality == expected_quality

    expected_sell_in = [-490, -498, -495, 0, -485, -497]
    actual_sell_in = [x.sell_in for x in items]
    assert actual_sell_in == expected_sell_in  
         

def repeat_update_quality(times, gilded_rose):
    for _ in range (times):
        gilded_rose.update_quality()
    
