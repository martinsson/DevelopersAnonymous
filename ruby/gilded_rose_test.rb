require 'test/unit'
require_relative 'gilded_rose'


class GildedRoseTest <  Test::Unit::TestCase

  MAX_BACKSTAGE_SELLIN = 30
  MAX_QUALITY = 50

  attr_reader :gilded_rose, :items, :random

  def setup
    @gilded_rose = GildedRose.new
    @items = gilded_rose.make_items
    @random = Random.new(3456789)
  end

  def test_after_one_day
    repeat_update_quality(1)
    assert_equal(items.map(&:name),
      [ "+5 Dexterity Vest",
        "Aged Brie",
        "Elixir of the Mongoose",
        "Sulfuras, Hand of Ragnaros",
        "Backstage passes to a TAFKAL80ETC concert",
        "Conjured Mana Cake"]
     )
    assert_equal([19, 1, 6, 80, 21, 5], items.map(&:quality))
    assert_equal([9, 1, 4, 0, 14, 2], items.map(&:sell_in))
  end

  def test_after_three_days
    repeat_update_quality(3)
    assert_equal(items.map(&:name),
      [ "+5 Dexterity Vest",
        "Aged Brie",
        "Elixir of the Mongoose",
        "Sulfuras, Hand of Ragnaros",
        "Backstage passes to a TAFKAL80ETC concert",
        "Conjured Mana Cake"]
     )
    assert_equal([17, 4, 4, 80, 23, 3], items.map(&:quality))
    assert_equal([7, -1, 2, 0, 12, 0], items.map(&:sell_in))
  end

  def after_a_shitload_of_days
    repeat_update_quality(500)
    assert_equal(items.map(&:name),
      [ "+5 Dexterity Vest",
        "Aged Brie",
        "Elixir of the Mongoose",
        "Sulfuras, Hand of Ragnaros",
        "Backstage passes to a TAFKAL80ETC concert",
        "Conjured Mana Cake"]
     )
    assert_equal([0, 50, 0, 80, 0, 0], items.map(&:quality))
    assert_equal([-490, -498, -495, 0, -485, -497], items.map(&:sell_in))
  end

  def test_backstage_pass_golden_copy items = a_bunch_of_backstage_passes
    items = a_bunch_of_backstage_passes
    repeat_update_quality(11)

    assert_equal( [5, 40, 49, 16, 6, 28, 42, 38, 24, 48, 24, 18, 7, 49, 6, 49, 19,
                   49, 11, 44, 18, 36, 9, 0, 8, 38, 2, 31, 22, 46, 19, 7, 28, 48,
                   3, 26, 39, 5, 22, 46, 4, 16, 1, 46, 39, 8, 48, 45, 6, 27, 16, 1,
                   24, 42, 45, 42, 6, 25, 29, 9, 1, 2, 1, 37, 2, 31, 25, 13, 30,
                   12, 44, 19, 6, 2, 36, 37, 29, 33, 27, 30, 31, 12, 23, 30, 2, 36,
                   35, 13, 4, 14, 22, 46, 33, 9, 44, 18, 33, 38, 28, 31],
                 items.map(&:quality))

    assert_equal( [23, 17, 14, 28, 28, 8, 4, 8, 26, 1, 17, 5, 23, 22, 23, 9,
                   27, 13, 3, 16, 28, 27, 7, 12, 14, 15, 8, 21, 0, 3, 10, 27,
                   15, 19, 17, 6, 17, 24, 24, 17, 0, 25, 9, 5, 22, 0, 29, 18,
                   23, 17, 22, 23, 11, 10, 18, 25, 5, 17, 14, 9, 15, 0, 6, 28,
                   5, 29, 24, 8, 28, 1, 29, 27, 14, 29, 13, 28, 14, 4, 23, 7,
                   18, 19, 27, 13, 0, 22, 28, 7, 5, 17, 3, 16, 29, 6, 24, 8, 0,
                   29, 23, 11] ,
                 items.map(&:sell_in)) end


  private

  def repeat_update_quality(n)
    n.times do
      gilded_rose.update_quality(items)
    end
  end

  def a_bunch_of_backstage_passes
    (0...100).map do
      a_random_backstage_pass
    end
  end

  def a_random_backstage_pass
    GildedRose::Item.new("Backstage passes to a TAFKAL80ETC concert",
                         random_sell_in, random_quality)
  end

  def random_sell_in
    random.rand(0...MAX_BACKSTAGE_SELLIN)
  end

  def random_quality
    random.rand(0...MAX_QUALITY)
  end

end

