package Project2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import com.aliasi.util.BoundedPriorityQueue;

/*
 * This is the KDTree - it is generic and holds anything
 * that is a subclass of point.
 */
public class KDTree<K extends point>{

	int depth;
	Node<K> root;

	public KDTree(){
		this.depth = 0;
		this.root = null;
	}

	public Node<K> getRoot(){
		return this.root;
	}

	public List<K> sortIndex(List<K> stars, int index){

		if(index == 0){
			Collections.sort(stars, K.xComparator);
		}
		else if(index == 1){
			Collections.sort(stars, K.yComparator);
		}
		else if(index == 2){
			Collections.sort(stars, K.zComparator);
		}
		return stars;
	}

	/*
	 * makes a tree recursively
	 * input: a list of stars
	 * output: nothing, but makes a tree.
	 * This program finds the median based on the current
	 * working index (dependent on the depth) at each level 
	 * of the tree. It stores the data at the nodes on each level. 
	 */
	public void makeTree(List<K> stars){
		if(!stars.isEmpty()){
			int index = depth % 3;
			List<K> newlist = sortIndex(stars, index);

			int length = newlist.size();
			K ourstar = stars.get(length/2);
			List<K> left =  stars.subList(0, (length/2));
			List<K> right = stars.subList((length/2)+1, length);
			this.root = new Node<K>(ourstar);
			root.left = new KDTree<K>();
			root.left.depth = depth+1;
			root.left.makeTree(left);
			root.right = new KDTree<K>();
			root.right.depth = depth+1;
			root.right.makeTree(right);
		}
	}
	
	/*
	 * Input: k- number of neighbors to be found, a Bounded Priority Queue for sorting at 
	 * each recursion, a point p - that we want to compare all the possible outputs to, and
	 * r - the starting node (usually the root).
	 * Output: returns a bounded priority queue with the k neighbors that are closest to p.
	 */

	public BoundedPriorityQueue<Distance<K>> nearestNeigbhors(int k, point p, BoundedPriorityQueue<Distance<K>> queue, Node<K> r){
		Node<K> curr = r;
		boolean searchedleft = false;
		if(r == null){
			return queue;
		}

		else{
			double dist = p.distance(curr.local);
			Distance<K> d = new Distance<K>(curr.local, dist);
			queue.offer(d);
			if(p.getindex(depth)< curr.local.getindex(depth)){
				searchedleft = true;
				nearestNeigbhors(k, p, queue, curr.left.root);
			}
			else{
				searchedleft = false;
				nearestNeigbhors(k, p, queue, curr.right.root);
			}
		}
		double tempdist = Math.abs(curr.local.getindex(depth) - p.getindex(depth));
		if((queue.size()<k)||(tempdist< queue.peek().distance)){
			if(searchedleft){
				nearestNeigbhors(k, p, queue, curr.right.root);
			}
			else{
				nearestNeigbhors(k, p, queue, curr.left.root);
			}
		}
		return queue;

	}
	
	/*
	 * takes the results from nearestNeigbhors and puts them in an arraylist;
	 */
	public ArrayList<Distance<K>> KnearestNeigbhors(int k, point p, boolean isname){

		ArrayList<Distance<K>> toreturn = new ArrayList<Distance<K>>();
		BoundedPriorityQueue<Distance<K>> togive;
		int ktogive = 0;
		if(isname){
			togive = new BoundedPriorityQueue<Distance<K>>(Distance.DistanceComparator, k+1);
			ktogive = k+2;
		}
		else{
			togive = new BoundedPriorityQueue<Distance<K>>(Distance.DistanceComparator, k);
			ktogive = k+1;
		}
		BoundedPriorityQueue<Distance<K>> result = this.nearestNeigbhors(ktogive, p, togive, this.root);

		for(Distance<K> m: result){
			toreturn.add(m);
		}
		return toreturn;

	}

	/*
	 * finds the nearest neighbors in a given radius. 
	 * runs recursively
	 * Input: a point p, an arraylist to add to, and a radius. 
	 */
	public ArrayList<Distance<K>> RnearestNeigbhors(point p, ArrayList<Distance<K>> list, double radius){
		if(root == null){
			return new ArrayList<>();
		}

		ArrayList<Distance<K>> toreturn = root.left.RnearestNeigbhors(p, list, radius);
		if(p.distance(root.local)<=radius){
			double dist = p.distance(root.local);
			Distance<K> thisdist = new Distance<K>(root.local, dist);
			toreturn.add(thisdist);
		}
		double abs = Math.abs(root.local.getindex(depth)-p.getindex(depth));
		if(radius>=abs || radius == (double) 0){
			toreturn.addAll(root.right.RnearestNeigbhors(p, list, radius));
		}
		return toreturn;
	}
	
	/*
	 * takes information from RnearestNeigbhors and returns it in arraylist form
	 * without duplicates. 
	 */
	public ArrayList<Distance<K>> RadiusMethod(point p, double radius){
		ArrayList<Distance<K>> whatever = new ArrayList<Distance<K>>();
		ArrayList<Distance<K>> results = RnearestNeigbhors(p, whatever, radius);
		whatever.clear();
		HashMap<Distance<K>, String> temp = new HashMap<Distance<K>, String>();
		if(results.size() != 0){
			for(Distance<K> m: results){
				temp.put(m, "");
			}

			for(Distance<K> m: temp.keySet()){
				whatever.add(m);
			}
		}
		temp.clear();
		results.clear();
		return whatever;
	}





}
