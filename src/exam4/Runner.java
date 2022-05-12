package exam4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Runner {
	
	private final static int NUM_TENTS = 10;
	
	public static List<Tent> createListOfRandomTents()
	{
		List<Tent> tents = new ArrayList<>();
		for (int i=1; i<=NUM_TENTS; i++)
		{
			int randomPeople = (int) (Math.random()*(20) + 1);
			double randomWidth = Math.random()*100 + 10;
			double randomLength = Math.random()*100 + 10;
			double randomHeight = i%3 == 0 ? 200 : (Math.random()*100 + 10);
			tents.add(new Tent(randomPeople, randomWidth, randomLength, randomHeight));
		}
		Collections.shuffle(tents);
		return tents;
	}
	
	public static List<Tent> getSortedTentsWithNumPeopleOrMore(List<Tent> tents, int numPeople)
	{
		List<Tent> newList = tents.stream().filter(t->t.numPeople >= numPeople).collect(Collectors.toList());
		Collections.sort(newList, (t1,t2)->t1.numPeople-t2.numPeople);
		return newList;
	}
	
	public static List<Tent> getSortedByArea(List<Tent> tents)
	{
		List<Tent> sorted = new ArrayList<>(tents);
		Collections.sort(sorted, (t1, t2)->Double.compare(t1.getArea(),t2.getArea()));
		return sorted;
	}
	
	public static boolean isTentMaxHeight(List<Tent> tents, Tent max)
	{
		Tent maxTent = Collections.max(tents, (t1, t2) -> Double.compare(t1.height, t2.height));
		return maxTent.height == max.height;
		
	}
	
	public static Map<Double, List<Tent>> getMapOfTentsByHeight(List<Tent> tents)
	{
		Map<Double,List<Tent>> mapByHeight = new HashMap<>();
		tents.forEach(t->mapByHeight.putIfAbsent(t.height, new ArrayList<Tent>()));
		for (Double height: mapByHeight.keySet())
		{
			List<Tent> currentList = tents.stream().filter(t->t.height == height).collect(Collectors.toList());
			mapByHeight.put(height, currentList);
		}
		return mapByHeight;
	}
	
	public static List<Tent> getTentsInHeightRange(Map<Double, List<Tent>> map, double minHeight, double maxHeight)
	{
		List<Tent> inRange = new ArrayList<>();
		map.keySet().stream().filter(key-> key>= minHeight && key<=maxHeight).forEach(key->inRange.addAll(map.get(key)));
		return inRange;
	}

	public static void main(String[] args) {
		
		List<Tent> tents = createListOfRandomTents();
		tents.forEach(System.out::println);
		
		List<Tent> sorted = getSortedByArea(tents);
		System.out.println("\nTents sorted by area:");
		sorted.forEach(System.out::println);
		
		int numPeople = 5;
		List<Tent> byNumPeople = getSortedTentsWithNumPeopleOrMore(tents, numPeople);
		System.out.println("\nTents with "+numPeople+" people or more, sorted by num of people:");
		byNumPeople.forEach(System.out::println);
		
		tents.forEach(t->System.out.printf("\nIs tent with height %.2f max height? %b", t.height, isTentMaxHeight(tents, t))); 
		
		Map<Double,List<Tent>> mapByHeight = getMapOfTentsByHeight(tents);
		System.out.println("\nMap of tents by height: ");
		for (Double height: mapByHeight.keySet())
		{
			System.out.printf("\nTents with height %.2f ",height);
			System.out.println(mapByHeight.get(height));
		
		}
		
		double minHeight = 50, maxHeight = 100;
		List<Tent> inRange = getTentsInHeightRange(mapByHeight, minHeight, maxHeight);
		System.out.println("\nTents with height in range "+minHeight+" to "+maxHeight);
		inRange.forEach(System.out::println);
	}

}
