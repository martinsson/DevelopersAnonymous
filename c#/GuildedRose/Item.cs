using System;

public class Item {
	private String name;
	private int sellIn;
	private int quality;

	public Item(string name, int sellIn, int quality) {
		this.name = name;
		this.quality = quality;
		this.sellIn = sellIn;
	}

    public int SellIn
    {
        get { return sellIn; }
        set { sellIn = value; }
    }

    public int Quality
    {
        get { return quality; }
        set { quality = value; }
    }
    
    public string Name
    {
        get { return name; }
    }
}
