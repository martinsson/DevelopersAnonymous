package org.agilegrenoble.objectcalisthenics

import java.util.ArrayList
import java.util.List
import GildedRose._
//remove if not needed
import scala.collection.JavaConversions._

object GildedRose {

  def main(args: Array[String]) {
    val gildedRose = new GildedRose()
    gildedRose.updateQuality(gildedRose.makeItems())
  }
}

class GildedRose {

  def makeItems(): List[Item] = {
    val items = new ArrayList[Item]()
    items.add(new Item("+5 Dexterity Vest", 10, 20))
    items.add(new Item("Aged Brie", 2, 0))
    items.add(new Item("Elixir of the Mongoose", 5, 7))
    items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80))
    items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20))
    items.add(new Item("Conjured Mana Cake", 3, 6))
    items
  }

  def updateQuality(list: List[Item]) {
    val items = list
    for (i <- 0 until items.size) {
      if (items.get(i).getName != "Aged Brie" && 
        items.get(i).getName != "Backstage passes to a TAFKAL80ETC concert") {
        if (items.get(i).getQuality > 0) {
          if (items.get(i).getName != "Sulfuras, Hand of Ragnaros") {
            items.get(i).setQuality(items.get(i).getQuality - 1)
          }
        }
      } else {
        if (items.get(i).getQuality < 50) {
          items.get(i).setQuality(items.get(i).getQuality + 1)
          if (items.get(i).getName == "Backstage passes to a TAFKAL80ETC concert") {
            if (items.get(i).getSellIn < 11) {
              if (items.get(i).getQuality < 50) {
                items.get(i).setQuality(items.get(i).getQuality + 1)
              }
            }
            if (items.get(i).getSellIn < 6) {
              if (items.get(i).getQuality < 50) {
                items.get(i).setQuality(items.get(i).getQuality + 1)
              }
            }
          }
        }
      }
      if (items.get(i).getName != "Sulfuras, Hand of Ragnaros") {
        items.get(i).setSellIn(items.get(i).getSellIn - 1)
      }
      if (items.get(i).getSellIn < 0) {
        if (items.get(i).getName != "Aged Brie") {
          if (items.get(i).getName != "Backstage passes to a TAFKAL80ETC concert") {
            if (items.get(i).getQuality > 0) {
              if (items.get(i).getName != "Sulfuras, Hand of Ragnaros") {
                items.get(i).setQuality(items.get(i).getQuality - 1)
              }
            }
          } else {
            items.get(i).setQuality(items.get(i).getQuality - items.get(i).getQuality)
          }
        } else {
          if (items.get(i).getQuality < 50) {
            items.get(i).setQuality(items.get(i).getQuality + 1)
          }
        }
      }
    }
  }
}
