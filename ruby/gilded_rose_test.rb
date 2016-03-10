require 'test/unit'
require_relative 'gilded_rose'


class GildedRoseTest <  Test::Unit::TestCase

  attr_reader :gilded_rose, :items, :random

  def setup
    @gilded_rose = GildedRose.new
    @items = gilded_rose.make_items
  end

  def test_after_one_day
    gilded_rose.update_quality(items)
    # assert_equal(items[0])
  end


end

