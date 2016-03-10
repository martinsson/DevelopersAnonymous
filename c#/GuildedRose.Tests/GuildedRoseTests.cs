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
            gildedRose.UpdateQuality(items);
            //Assert.That(items[0])....
        }

    }
}
