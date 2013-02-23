using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;

namespace GuildedRose.Tests
{
    [TestFixture]
    public class GuildedRoseTests
    {

        private const int MAX_BACKSTAGE_SELLIN = 30;
        private const int MAX_QUALITY = 50;
        private GildedRose gildedRose;
        private List<Item> items;
        private Random rand = new Random(3456789);

        [SetUp]
        public void setup()
        {
            gildedRose = new GildedRose();
            items = gildedRose.makeItems();
        }
        [Test]
        public void after_one_day()
        {
            RepeatUpdateQuality(1);
            var itemNames = items.Select(i => i.Name);
            Assert.That(itemNames.ContainsOnly(new string[]{
                    "+5 Dexterity Vest",
                    "Aged Brie",
                    "Elixir of the Mongoose",
                    "Sulfuras, Hand of Ragnaros",
                    "Backstage passes to a TAFKAL80ETC concert",
                    "Conjured Mana Cake"}));
            var qualities = items.Select(i => i.Quality);
            Assert.That(qualities.ContainsOnly(new int[] { 19, 1, 6, 80, 21, 5 }));
            var sellIns = items.Select(i => i.SellIn);
            Assert.That(sellIns.ContainsOnly(new int[] { 9, 1, 4, 0, 14, 2 }));
        }

        [Test]
        public void After_three_days()
        {
            RepeatUpdateQuality(3);
            var itemNames = items.Select(i => i.Name);
            Assert.That(itemNames.ContainsOnly(new string[]{
                    "+5 Dexterity Vest", 
                    "Aged Brie", 
                    "Elixir of the Mongoose", 
                    "Sulfuras, Hand of Ragnaros", 
                    "Backstage passes to a TAFKAL80ETC concert", 
                    "Conjured Mana Cake"}));
            var qualities = items.Select(i => i.Quality);
            Assert.That(qualities.ContainsOnly(new int[] { 17, 4, 4, 80, 23, 3 }));
            var sellIns = items.Select(i => i.SellIn);
            Assert.That(sellIns.ContainsOnly(new int[] { 7, -1, 2, 0, 12, 0 }));
        }
        [Test]
        public void after_a_shitload_of_days()
        {
            RepeatUpdateQuality(500);

            var itemNames = items.Select(i => i.Name);
            Assert.That(itemNames.ContainsOnly(new string[]{"+5 Dexterity Vest", 
                                            "Aged Brie", 
                                            "Elixir of the Mongoose", 
                                            "Sulfuras, Hand of Ragnaros", 
                                            "Backstage passes to a TAFKAL80ETC concert", 
                                            "Conjured Mana Cake"}));
            var qualities = items.Select(i => i.Quality);
            Assert.That(qualities.ContainsOnly(new int[] { 0, 50, 0, 80, 0, 0 }));
            var sellIns = items.Select(i => i.SellIn);
            Assert.That(sellIns.ContainsOnly(new int[] { -490, -498, -495, 0, -485, -497 }));
        }

        [Test]
        public void Backstage_pass_golden_copy()
        {
            items = ABunchOfBackstagePasses();
            RepeatUpdateQuality(11);
            var qualities = items.Select(i => i.Quality);
            Assert.That(qualities.ContainsOnly(new int[]{ 30, 48, 45, 0, 11, 0, 0, 0, 36, 15, 33, 50, 50, 27, 0, 26, 42, 50, 0, 50, 50, 0, 29, 0, 0, 36, 50, 41, 50,
                0, 49, 25, 0, 12, 0, 50, 0, 0, 0, 43, 0, 50, 23, 27, 33, 0, 0, 37, 0, 43, 0, 0, 45, 50, 22, 43, 0, 30, 14, 44, 50, 0, 17, 0, 17, 50,
                16, 50, 19, 44, 0, 0, 37, 34, 0, 0, 0, 50, 0, 29, 40, 50, 50, 47, 0, 0, 47, 0, 26, 11, 26, 16, 0, 50, 0, 0, 0, 35, 0, 50}));
            var sellIns = items.Select(i => i.SellIn);
            Assert.That(sellIns.ContainsOnly(new int[]{1, 18, -7, 6, 8, 16, -8, 6, 4, -9, 4, -2, -9, 8, 14, 7, 9, -9, 12, 2, -9, 12, -7, 5, -6, -1, -11, 2, -4, 9,
                4, 15, 13, -4, -4, 12, -7, 9, -4, -7, 4, 0, 11, 6, -8, 3, 17, 12, 9, -4, 13, -5, 10, 4, 14, 6, 14, 1, -5, -3, 15, 11, -2, -6, -2, 2,
                -11, 17, 10, 18, 2, 14, -8, -5, 1, -1, 0, 14, 7, 9, -3, 2, -9, -1, -6, 5, -5, 8, -5, 11, 3, 17, 17, 13, 5, 11, 16, -3, 6, 11}));
        }

        private void RepeatUpdateQuality(int times)
        {
            for (int i = 0; i < times; i++)
            {
                gildedRose.UpdateQuality(items);
            }
        }

        private List<Item> ABunchOfBackstagePasses()
        {
            List<Item> listOfPasses = new List<Item>();
            for (int i = 0; i < 100; i++)
            {
                listOfPasses.Add(ARandomBackstagePass());
            }
            return listOfPasses;
        }

        private int RandomSellIn()
        {
            return rand.Next(MAX_BACKSTAGE_SELLIN);
        }

        private int RandomQuality()
        {
            return rand.Next(MAX_QUALITY);
        }

        private Item ARandomBackstagePass()
        {
            int quality = RandomQuality();
            int sellIn = RandomSellIn();
            return new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
        }


    }
}
