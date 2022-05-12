package exam4;

public class Tent {
	
	protected int numPeople;
	protected double width;
	protected double length;
	protected double height;
	
	public Tent(int numPeople, double width, double length, double height) {
		this.numPeople = numPeople;
		this.width = width;
		this.length = length;
		this.height = height;
	}

	@Override
	public String toString() {
		return String.format("Tent [numPeople= %d ,width= %.2f, length= %.2f, height= %.2f, area= %.2f]", numPeople, width,
				length, height, getArea());
	}
	
	public double getArea()
	{
		return width*length;
	}

}
