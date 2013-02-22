package org.joyofcoding.objectcalisthenics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing GildedRose with Golden Master.
 * <p>
 *  <b>Warning:</b> You must download and install approvalstest in you local or corporate repository. Cf. src/main/lib/README.txt file to install it.
 * </p>  
 * @see <a href="http://craftedsw.blogspot.fr/2012/11/testing-legacy-code-with-golden-master.html#comment-form">Testing legacy code with Golden Master</a> 
 *      by Sandro Mancuso
 * @see <a href="http://approvaltests.sourceforge.net/">Approval Tests</a>
 * 
 */
public class GoldenMasterGildedRoseTest {
	private static final int FIXED_SEED = 100;
	private static final int NUMBER_OF_RANDOM_ITEMS = 2000;
	private static final int MINIMUM = -50;
	private static final int MAXIMUN = 101;

	private String[] itemNames = { "+5 Dexterity Vest", "Aged Brie",
			"Elixir of the Mongoose", "Sulfuras, Hand of Ragnaros",
			"Backstage passes to a TAFKAL80ETC concert", "Conjured Mana Cake" };
	private Random random = new Random(FIXED_SEED);
	private GildedRose gildedRose;

	@Before
	public void initialise() {
		gildedRose = new GildedRose();
	}

	@Test
	// @UseReporter(JunitReporter.class)
	public void should_generate_update_quality_output() throws Exception {
		List<Item> items = generateRandomItems(NUMBER_OF_RANDOM_ITEMS);

		gildedRose.updateQuality(items);
		Approvals.verifyAll("Item", items);
	}

	private List<Item> generateRandomItems(int totalNumberOfRandomItems) {
		List<Item> items = new ArrayList<Item>();
		for (int cnt = 0; cnt < totalNumberOfRandomItems; cnt++) {
			items.add(new Item(itemName(), sellIn(), quality()));
		}
		return items;
	}

	private String itemName() {
		return itemNames[0 + random.nextInt(itemNames.length)];
	}

	private int sellIn() {
		return randomNumberBetween(MINIMUM, MAXIMUN);
	}

	private int quality() {
		return randomNumberBetween(MINIMUM, MAXIMUN);
	}

	private int randomNumberBetween(int minimum, int maximum) {
		return minimum + random.nextInt(maximum);
	}

}
