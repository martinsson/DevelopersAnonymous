# -*- coding: utf-8 -*-
#
# ToDo: 1 test is missing: test_backstage_pass_golden_copy
#

import unittest

from gilded_rose import Item, GildedRose

class GildedRoseTest(unittest.TestCase):
    
    def setUp(self):
        self.names = ["+5 Dexterity Vest",
                      "Aged Brie",
                      "Elixir of the Mongoose",
                      "Sulfuras, Hand of Ragnaros",
                      "Backstage passes to a TAFKAL80ETC concert",
                      "Conjured Mana Cake"]
        self.items  = [Item(self.names[0], 10, 20)]
        self.items += [Item(self.names[1], 2, 0)]
        self.items += [Item(self.names[2], 5, 7)]
        self.items += [Item(self.names[3], 0, 80)]
        self.items += [Item(self.names[4], 15, 20)]
        self.items += [Item(self.names[5], 3, 6)]
        self.gilded_rose = GildedRose(self.items)
        
    
    def test_after_one_day(self):

        self.repeat_update_quality(1)

        expected_name = self.names
        actual_name = [x.name for x in self.items]                 
        self.assertListEqual(expected_name, actual_name)
        
        expected_quality = [19, 1, 6, 80, 21, 5]
        actual_quality = [x.quality for x in self.items]
        self.assertListEqual(expected_quality, actual_quality)

        expected_sell_in = [9, 1, 4, 0, 14, 2]
        actual_sell_in = [x.sell_in for x in self.items]
        self.assertListEqual(expected_sell_in, actual_sell_in)
        
        
    def test_after_three_days(self):

        self.repeat_update_quality(3)

        expected_name = self.names
        actual_name = [x.name for x in self.items]                 
        self.assertListEqual(expected_name, actual_name)
        
        expected_quality = [17, 4, 4, 80, 23, 3]
        actual_quality = [x.quality for x in self.items]
        self.assertListEqual(expected_quality, actual_quality)

        expected_sell_in = [7, -1, 2, 0, 12, 0]
        actual_sell_in = [x.sell_in for x in self.items]
        self.assertListEqual(expected_sell_in, actual_sell_in) 
        

    def test_after_a_shitload_of_days(self):

        self.repeat_update_quality(500)
        
        expected_name = self.names
        actual_name = [x.name for x in self.items]                 
        self.assertListEqual(expected_name, actual_name)
        
        expected_quality = [0, 50, 0, 80, 0, 0]
        actual_quality = [x.quality for x in self.items]
        self.assertListEqual(expected_quality, actual_quality)

        expected_sell_in = [-490, -498, -495, 0, -485, -497]
        actual_sell_in = [x.sell_in for x in self.items]
        self.assertListEqual(expected_sell_in, actual_sell_in)  
             

    def repeat_update_quality(self, times):
        for _ in range (times):
            self.gilded_rose.update_quality()
        

if __name__ == '__main__':
    unittest.main()
