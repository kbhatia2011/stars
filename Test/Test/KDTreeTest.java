package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import Project2.Distance;
import Project2.KDTree;
import Project2.star;

import com.aliasi.util.BoundedPriorityQueue;

public class KDTreeTest {

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

		KDTree<star> test = new KDTree<star>();

		test.makeTree(alist);

		ArrayList<Distance<star>> queue = new ArrayList<Distance<star>>();
		queue = test.KnearestNeigbhors(1, s1, false);
		assertTrue(queue.size()==1);
		for(Distance<star> i: queue){
			assertTrue(i.getDistance()== 0.0);
		}

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

		KDTree<star> test = new KDTree<star>();

		test.makeTree(alist);

		ArrayList<Distance<star>> queue = new ArrayList<Distance<star>>();
		queue = test.KnearestNeigbhors(2, s1, false);

		assertTrue(queue.size()==2);
		assertTrue(queue.get(0).getStar().getId() == 1);
		assertTrue(queue.get(1).getStar().getId()== 5);

	}
	
	@Test
	public void test3Radius() {

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

		KDTree<star> test = new KDTree<star>();

		test.makeTree(alist);
		
		

		ArrayList<Distance<star>> queue = test.RadiusMethod(s1, 0.0);


		assertTrue(queue.size()==1);
		for(Distance<star> i: queue){
			assertTrue(i.getDistance()== 0.0);
		}

	}
	
	@Test
	public void test4Radius() {

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

		KDTree<star> test = new KDTree<star>();

		test.makeTree(alist);

		ArrayList<Distance<star>> queue = test.RadiusMethod(s1, 20);
		
		Collections.sort(queue, Distance.DistanceComparator2);

		assertTrue(queue.size()==3);
		assertTrue(queue.get(0).getStar().equals(s1));
		assertTrue(queue.get(1).getStar().equals(s5));
		assertTrue(queue.get(2).getStar().equals(s4));
	}

}
