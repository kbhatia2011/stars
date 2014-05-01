package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import Project2.Distance;
import Project2.point;
import Project2.star;

public class DistanceTesting {
	
	@Test
	public void test1() {

		star s1 = new star(1, 0, 20, 3);
		star s2 = new star(2, 20, 60, 1);
		star s3 = new star(3, 10, 40, 5);
		star s4 = new star(4, 2, 2, 7);
		star s5 = new star(5, 3, 6, 1);
		star s6 = new star(6, 14, 4, 5);

		List<Distance<star>> alist = new ArrayList<Distance<star>>();
		alist.add(new Distance<star>(s1, s1.distance(s1)));
		alist.add(new Distance<star>(s2, s1.distance(s2)));
		alist.add(new Distance<star>(s3, s1.distance(s3)));
		alist.add(new Distance<star>(s4, s1.distance(s4)));
		alist.add(new Distance<star>(s5, s1.distance(s5)));
		alist.add(new Distance<star>(s6, s1.distance(s6)));

		Collections.sort(alist, Distance.DistanceComparator2);
		assertTrue(alist.get(0).getStar().equals(s1));
		assertTrue(alist.get(1).getStar().equals(s5));

	}
	

}
