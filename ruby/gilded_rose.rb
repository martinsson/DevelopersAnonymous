# sic

class GildedRose

  def make_items
		[ Item.new("+5 Dexterity Vest", 10, 20),
      Item.new("Aged Brie", 2, 0),
      Item.new("Elixir of the Mongoose", 5, 7),
      Item.new("Sulfuras, Hand of Ragnaros", 0, 80),
      Item.new("Backstage passes to a TAFKAL80ETC concert", 15, 20),
      Item.new("Conjured Mana Cake", 3, 6)
    ]
  end

  def update_quality(list)
    items = list
    for i in 0...items.length do
      if (not items[i].name == "Aged Brie") &&
         (not items[i].name ==  "Backstage passes to a TAFKAL80ETC concert")

        if items[i].quality > 0
          unless items[i].name == "Sulfuras, Hand of Ragnaros"
            items[i].quality = items[i].quality - 1
          end
        end
      else
        if items[i].quality < 50
          items[i].quality = items[i].quality + 1

          if items[i].name == "Backstage passes to a TAFKAL80ETC concert"
            if items[i].sell_in < 11
              if items[i].quality < 50
                items[i].quality = items[i].quality + 1
              end
            end

            if items[i].sell_in < 6
              if items[i].quality < 50
                items[i].quality = items[i].quality + 1
              end
            end
          end
        end
      end

      if not items[i].name == "Sulfuras, Hand of Ragnaros"
        items[i].sell_in = items[i].sell_in - 1
      end

      if items[i].sell_in < 0
        if not items[i].name == "Aged Brie"
          if not items[i].name == "Backstage passes to a TAFKAL80ETC concert"
            if items[i].quality > 0
              if not items[i].name == "Sulfuras, Hand of Ragnaros"
                items[i].quality = items[i].quality - 1
              end
            end
          else
            items[i].quality = items[i].quality - items[i].quality
          end
        else
          if items[i].quality < 50
            items[i].quality = items[i].quality + 1
          end
        end
      end
    end
  end

  class Item < Struct.new(:name, :sell_in, :quality); end

end



gilded_rose = GildedRose.new
gilded_rose.update_quality(gilded_rose.make_items)
