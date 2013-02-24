package org.agilegrenoble.objectcalisthenics

import java.util.Random
import GildedRoseTest._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.scalatest.BeforeAndAfter
import org.scalatest.matchers.Matcher
import org.scalatest.matchers.MatchResult
import scala.math.Ordered

object GildedRoseTest {

  private val Max_Backstage_Sellin = 30
  private val Max_Quality = 50
  private val Expected_Item_Names = List(
    "+5 Dexterity Vest",
    "Aged Brie",
    "Elixir of the Mongoose",
    "Sulfuras, Hand of Ragnaros",
    "Backstage passes to a TAFKAL80ETC concert",
    "Conjured Mana Cake")

}

class GildedRoseTest extends FlatSpec with ShouldMatchers with BeforeAndAfter {

  private var gildedRose: GildedRose = _

  private var rand: Random = new Random(3456789)

  before {
    gildedRose = new GildedRose()
  }

  "GildedRose" should "look a certain way after day one" in {
    repeatUpdateQuality(1)
    val items = gildedRose.items
    val itemNames = items.map(_.name)
    itemNames should containOnly(Expected_Item_Names)

    val qualities = items.map(_.quality)
    qualities should containOnly(List(19, 1, 6, 80, 21, 5))

    val sellIns = items.map(_.sellIn)
    sellIns should containOnly(List(9, 1, 4, 0, 14, 2))
  }

  it should "look a certain way after three days" in {
    repeatUpdateQuality(3)
    val items = gildedRose.items
    val itemNames = items.map(_.name)
    itemNames should containOnly(Expected_Item_Names)

    val qualities = items.map(_.quality)
    qualities should containOnly(List(17, 4, 4, 80, 23, 3))

    val sellIns = items.map(_.sellIn)
    sellIns should containOnly(List(7, -1, 2, 0, 12, 0))
  }

  it should "look a certain way after a shitload of days" in {
    repeatUpdateQuality(500)
    val items = gildedRose.items
    val itemNames = items.map(_.name)
    itemNames should containOnly(Expected_Item_Names)

    val qualities = items.map(_.quality)
    qualities should have length (6)
    qualities should containOnly(List(0, 50, 0, 80, 0, 0))

    val sellIns = items.map(_.sellIn)
    sellIns should containOnly(List(-490, -498, -495, 0, -485, -497))
  }

  it should "have repeated value of containsOnlybackstage passes" in {
	val newItems=aBunchOfBackstagePasses().toList
    gildedRose.items=newItems
    repeatUpdateQuality(11)

	val items=gildedRose.items
    val qualities = items.map(_.quality)
    println(qualities)
    qualities should containOnly(List(30, 48, 45, 0, 11, 0, 0, 0, 36, 15, 33, 50, 50, 27, 0, 26, 42,
      50, 0, 50, 50, 0, 29, 0, 0, 36, 50, 41, 50, 0, 49, 25, 0, 12, 0, 50, 0, 0, 0, 43, 0, 50, 23, 27,
      33, 0, 0, 37, 0, 43, 0, 0, 45, 50, 22, 43, 0, 30, 14, 44, 50, 0, 17, 0, 17, 50, 16, 50, 19, 44,
      0, 0, 37, 34, 0, 0, 0, 50, 0, 29, 40, 50, 50, 47, 0, 0, 47, 0, 26, 11, 26, 16, 0, 50, 0, 0, 0,
      35, 0, 50))
    val sellIns = items.map(_.sellIn)
    sellIns should containOnly(List(1, 18, -7, 6, 8, 16, -8, 6, 4, -9, 4, -2, -9, 8, 14, 7, 9, -9, 12,
      2, -9, 12, -7, 5, -6, -1, -11, 2, -4, 9, 4, 15, 13, -4, -4, 12, -7, 9, -4, -7, 4, 0, 11, 6, -8,
      3, 17, 12, 9, -4, 13, -5, 10, 4, 14, 6, 14, 1, -5, -3, 15, 11, -2, -6, -2, 2, -11, 17, 10, 18,
      2, 14, -8, -5, 1, -1, 0, 14, 7, 9, -3, 2, -9, -1, -6, 5, -5, 8, -5, 11, 3, 17, 17, 13, 5, 11, 16,
      -3, 6, 11))
  }

  private def repeatUpdateQuality(times: Int) = for (i <- 0 until times) gildedRose.updateQuality()

  private def aBunchOfBackstagePasses() = (0 until 100).map(_ => aRandomBackstagePass)

  private def randomSellIn() = rand.nextInt(Max_Backstage_Sellin)

  private def randomQuality() = rand.nextInt(Max_Quality)

  private def aRandomBackstagePass() = Item("Backstage passes to a TAFKAL80ETC concert", randomSellIn(), randomQuality())

  def containOnly[A](right: Seq[A]) =
    new Matcher[Seq[A]] {
      def apply(left: Seq[A]) = {
        val extra = left diff right
        val missing = right diff left
        val errorMessage = "The collections did not contain the same elements." +
          (if (extra.length > 0) "It contained the extra elements :" + extra.toList.mkString(",") + "." else "") +
          (if (missing.length > 0) "It is missing the elements :" + missing.toList.mkString(",") + "." else "")
        MatchResult(
          extra == Nil && missing == Nil,
          errorMessage,
          "The collections contained the same elements")
      }
    }

}
