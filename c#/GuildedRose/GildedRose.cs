using System;
using System.Collections.Generic;

public class GildedRose
{

    //public static void Main(String[] args) {
    //    GildedRose gildedRose = new GildedRose();
    //    gildedRose.UpdateQuality(gildedRose.makeItems());
    //}

    public List<Item> makeItems()
    {
        List<Item> items = new List<Item>();
        items.Add(new Item("+5 Dexterity Vest", 10, 20));
        items.Add(new Item("Aged Brie", 2, 0));
        items.Add(new Item("Elixir of the Mongoose", 5, 7));
        items.Add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.Add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.Add(new Item("Conjured Mana Cake", 3, 6));
        return items;
    }

    public void UpdateQuality(List<Item> list)
    {
        List<Item> items = list;
        foreach (var item in items)
        {
            if (item.Name != "Aged Brie" && item.Name != "Backstage passes to a TAFKAL80ETC concert")
            {
                if (item.Quality > 0)
                {
                    if (item.Name != "Sulfuras, Hand of Ragnaros")
                    {
                        item.Quality = item.Quality - 1;
                    }
                }
            }
            else
            {
                if (item.Quality < 50)
                {
                    item.Quality = item.Quality + 1;

                    if (item.Name.Equals("Backstage passes to a TAFKAL80ETC concert"))
                    {
                        if (item.SellIn < 11)
                        {
                            if (item.Quality < 50)
                            {
                                item.Quality = item.Quality + 1;
                            }
                        }

                        if (item.SellIn < 6)
                        {
                            if (item.Quality < 50)
                            {
                                item.Quality = item.Quality + 1;
                            }
                        }
                    }
                }
            }

            if (!item.Name.Equals("Sulfuras, Hand of Ragnaros"))
            {
                item.SellIn = item.SellIn - 1;
            }

            if (item.SellIn < 0)
            {
                if (item.Name != "Aged Brie")
                {
                    if (item.Name != "Backstage passes to a TAFKAL80ETC concert")
                    {
                        if (item.Quality > 0)
                        {
                            if (item.Name != "Sulfuras, Hand of Ragnaros")
                            {
                                item.Quality = item.Quality - 1;
                            }
                        }
                    }
                    else
                    {
                        item.Quality = item.Quality - item.Quality; 
                    }
                }
                else
                {
                    if (item.Quality < 50)
                    {
                        item.Quality = item.Quality + 1;
                    }
                }
            }
        }
    }

}
