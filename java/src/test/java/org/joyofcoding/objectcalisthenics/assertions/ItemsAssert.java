package org.joyofcoding.objectcalisthenics.assertions;

import static org.fest.assertions.api.Assertions.extractProperty;

import org.fest.assertions.api.AbstractIterableAssert;
import org.fest.assertions.api.Assertions;
import org.joyofcoding.objectcalisthenics.Item;

public class ItemsAssert extends
		AbstractIterableAssert<ItemsAssert, Iterable<Item>, Item> {

	
	protected ItemsAssert(Iterable<Item> actual) {
		super(actual, ItemsAssert.class);
	}

	public static ItemsAssert assertThat(Iterable<Item> actual) {
		return new ItemsAssert(actual);
	}

	public ItemsAssert containsOnlyItemNames(String... names) {
		isNotNull();

		Iterable<String> actualItemNames = extractProperty("name", String.class)
				.from(actual);
		
		Assertions
				.assertThat(actualItemNames)
				.containsOnly(names);

		return this;
	}
	
	public ItemsAssert containsOnlyItemQualities(Integer... qualities) {
		isNotNull();
		
		Iterable<Integer> actualItemQualities = extractProperty("quality", Integer.class)
				.from(actual);
        Assertions.assertThat(actualItemQualities).containsOnly(qualities);
        
		return this;
	}
	
	public ItemsAssert containsOnlyItemSellIns(Integer... sellIns) {
		isNotNull();
		
		Iterable<Integer> actualItemSellIns = extractProperty("sellIn", Integer.class)
				.from(actual);
		Assertions.assertThat(actualItemSellIns).containsOnly(sellIns);
		
		return this;
	}

}
