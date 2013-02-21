package org.agilegrenoble.objectcalisthenics

import java.util.Random
import GildedRoseTest._
import scala.collection.JavaConversions._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar
import org.scalatest.FlatSpec

object GildedRoseTest {

  private val MAX_BACKSTAGE_SELLIN = 30

  private val MAX_QUALITY = 50
}

class GildedRoseTest extends FlatSpec with ShouldMatchers with MockitoSugar with BeforeAndAfter{

  private var gildedRose: GildedRose = _

  private var items: Seq[Item] = _

  private var rand: Random = new Random(3456789)

  def before() {
    gildedRose = new GildedRose()
    items = gildedRose.makeItems()
  }

  
  "GildedRose" should "look a certain way after day one" in {
    repeatUpdateQuality(1)
    val itemNames = items.map(_.name).toList
    containsOnly(itemNames,List("+5 Dexterity Vest", "Aged Brie", "Elixir of the Mongoose", "Sulfuras, Hand of Ragnaros", 
      "Backstage passes to a TAFKAL80ETC concert", "Conjured Mana Cake")) should be(true)
    val qualities = items.map(_.quality).toList
    assertThat(qualities).containsOnly(19, 1, 6, 80, 21, 5)
    val sellIns = extractProperty("sellIn", classOf[Integer]).from(items)
    assertThat(sellIns).containsOnly(9, 1, 4, 0, 14, 2)
  }

  it should "look a certain way after three days" in {
    repeatUpdateQuality(3)
    val itemNames = items.map(_.name).toList
    assertThat(itemNames).containsOnly("+5 Dexterity Vest", "Aged Brie", "Elixir of the Mongoose", "Sulfuras, Hand of Ragnaros", 
      "Backstage passes to a TAFKAL80ETC concert", "Conjured Mana Cake")
    val qualities = items.map(_.quality).toList
    assertThat(qualities).containsOnly(17, 4, 4, 80, 23, 3)
    val sellIns = extractProperty("sellIn", classOf[Integer]).from(items)
    assertThat(sellIns).containsOnly(7, -1, 2, 0, 12, 0)
  }

  it should "look a certain way after a shitload of days" in {
    repeatUpdateQuality(500)
    val itemNames = items.map(_.name).toList
    containsOnly(itemNames,List("+5 Dexterity Vest", "Aged Brie", "Elixir of the Mongoose", "Sulfuras, Hand of Ragnaros", 
      "Backstage passes to a TAFKAL80ETC concert", "Conjured Mana Cake")) should be(true);
    val qualities = items.map(_.quality).toList
    assertThat(qualities).containsOnly(0, 50, 0, 80, 0, 0)
    val sellIns = extractProperty("sellIn", classOf[Integer]).from(items)
    assertThat(sellIns).containsOnly(-490, -498, -495, 0, -485, -497)
  }

  it should "have repeated value of backstage passes" in {
    items = aBunchOfBackstagePasses()
    repeatUpdateQuality(11)
    val qualities = items.map(_.quality).toList
    assertThat(qualities).containsOnly(30, 48, 45, 0, 11, 0, 0, 0, 36, 15, 33, 50, 50, 27, 0, 26, 42, 
      50, 0, 50, 50, 0, 29, 0, 0, 36, 50, 41, 50, 0, 49, 25, 0, 12, 0, 50, 0, 0, 0, 43, 0, 50, 23, 27, 
      33, 0, 0, 37, 0, 43, 0, 0, 45, 50, 22, 43, 0, 30, 14, 44, 50, 0, 17, 0, 17, 50, 16, 50, 19, 44, 
      0, 0, 37, 34, 0, 0, 0, 50, 0, 29, 40, 50, 50, 47, 0, 0, 47, 0, 26, 11, 26, 16, 0, 50, 0, 0, 0, 
      35, 0, 50)
    val sellIns = extractProperty("sellIn", classOf[Integer]).from(items)
    assertThat(sellIns).containsOnly(1, 18, -7, 6, 8, 16, -8, 6, 4, -9, 4, -2, -9, 8, 14, 7, 9, -9, 12, 
      2, -9, 12, -7, 5, -6, -1, -11, 2, -4, 9, 4, 15, 13, -4, -4, 12, -7, 9, -4, -7, 4, 0, 11, 6, -8, 
      3, 17, 12, 9, -4, 13, -5, 10, 4, 14, 6, 14, 1, -5, -3, 15, 11, -2, -6, -2, 2, -11, 17, 10, 18, 
      2, 14, -8, -5, 1, -1, 0, 14, 7, 9, -3, 2, -9, -1, -6, 5, -5, 8, -5, 11, 3, 17, 17, 13, 5, 11, 16, 
      -3, 6, 11)
  }

  private def containsOnly(items1:Seq[String],items2:Seq[String])={
    items1.length==items2.length && (items1 match {
      case Nil => true
      case head::tail => items2.contains(head)&&containsOnly(tail,items2.remove(head))
    })
  }
  
  private def repeatUpdateQuality(times: Int)={
    for (i <- 0 until times) {
      gildedRose.updateQuality(items)
    }
  }

  private def aBunchOfBackstagePasses(): Seq[Item] = (0 until 100).map(x:Int=>aRandomBackstagePass())

  private def randomSellIn(): Int = rand.nextInt(MAX_BACKSTAGE_SELLIN)

  private def randomQuality(): Int = rand.nextInt(MAX_QUALITY)

  private def aRandomBackstagePass(): Item = {
    val quality = randomQuality()
    val sellIn = randomSellIn()
    new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)
  }
}
