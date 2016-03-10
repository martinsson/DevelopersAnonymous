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

}

class GildedRoseTest extends FlatSpec with ShouldMatchers with BeforeAndAfter {

  private var gildedRose: GildedRose = _

  before {
    gildedRose = new GildedRose()
  }

  "GildedRose" should "look a certain way after day one" in {
    gildedRose.updateQuality()
    val items = gildedRose.items
    //items[0] should equal(...)
  }

}
