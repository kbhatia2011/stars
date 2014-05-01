package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import Project2.point;
import Project2.star;

public class PointTesting {

	@Test
	public void test1() {

		star s1 = new star(1, 0, 20, 3);
		star s2 = new star(2, 20, 60, 1);
		star s3 = new star(3, 10, 40, 5);
		star s4 = new star(4, 2, 2, 7);
		star s5 = new star(5, 3, 6, 1);
		star s6 = new star(6, 14, 4, 5);

		List<star> alist = new ArrayList<star>();
		alist.add(s1);
		alist.add(s2);
		alist.add(s3);
		alist.add(s4);
		alist.add(s5);
		alist.add(s6);

		Collections.sort(alist, point.xComparator);
		assertTrue(alist.get(0).equals(s2));
		assertTrue(alist.get(1).equals(s6));

	}
	
	@Test
	public void test2() {

		star s1 = new star(1, 0, 20, 3);
		star s2 = new star(2, 20, 60, 1);
		star s3 = new star(3, 10, 40, 5);
		star s4 = new star(4, 2, 2, 7);
		star s5 = new star(5, 3, 6, 1);
		star s6 = new star(6, 14, 4, 5);

		List<star> alist = new ArrayList<star>();
		alist.add(s1);
		alist.add(s2);
		alist.add(s3);
		alist.add(s4);
		alist.add(s5);
		alist.add(s6);

		Collections.sort(alist, point.yComparator);
		assertTrue(alist.get(0).equals(s2));
		assertTrue(alist.get(1).equals(s3));

	}
	
	@Test
	public void test3Radius() {

		star s1 = new star(1, 0, 20, 3);
		star s2 = new star(2, 20, 60, 1);
		star s3 = new star(3, 10, 40, 5);
		star s4 = new star(4, 2, 2, 7);
		star s5 = new star(5, 3, 6, 11);
		star s6 = new star(6, 14, 4, 5);

		List<star> alist = new ArrayList<star>();
		alist.add(s1);
		alist.add(s2);
		alist.add(s3);
		alist.add(s4);
		alist.add(s5);
		alist.add(s6);

		Collections.sort(alist, point.zComparator);
		assertTrue(alist.get(0).equals(s5));
		assertTrue(alist.get(1).equals(s4));

	
	}
	
	


}
