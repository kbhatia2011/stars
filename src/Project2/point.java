package Project2;

import java.util.Comparator;

public class point{

	double x;
	double y;
	double z;
	
	public point(double X, double Y, double Z){
		this.x = X;
		this.y = Y;
		this.z = Z;
	}
	


	public double getindex(int depth) {
		int index = depth % 3;
		double toreturn = 0;
		
		if(index == 0){
			toreturn = this.x;
		}
		else if(index == 1){
			toreturn = this.y;
		}
		else if(index == 2){
			toreturn = this.z;
		}
		
		return toreturn;
	}
	
/*
 * Input: a point
 * Output: a double indicating the distance from the point to 
 * this object.
 */
	public double distance(Object othapoint) {
		double xdifsq = Math.pow((((point) othapoint).getx()-this.getx()), 2);
		double ydifsq = Math.pow((((point) othapoint).gety()-this.gety()), 2);
		double zdifsq = Math.pow((((point) othapoint).getz()-this.getz()), 2);
		double distance = Math.sqrt(xdifsq+ydifsq+zdifsq);
		return distance;
	}

	public double getx() {
		return this.x;
	}


	public double gety() {
		return this.y;
	}


	public double getz() {
		return this.z;
	}
	
	/*
	 * The following cocmparators are for X, Y, and Z comparison.
	 */
	
	public static Comparator<point> xComparator = new Comparator<point>() {

	
		public int compare(point s1, point s2) {
			
			if(s1.getx() == s2.getx())
				return 0;
			else if(s1.getx()>s2.getx())
				return -1;
			else
				return 1;
		}


	};
	
	public static Comparator<point> yComparator = new Comparator<point>() {

	
		public int compare(point s1, point s2) {
			if(s1.gety() == s2.gety())
				return 0;
			else if(s1.gety()>s2.gety())
				return -1;
			else
				return 1;
		}


	};
	
	public static Comparator<point> zComparator = new Comparator<point>() {

	
		public int compare(point s1, point s2) {
			if(s1.getz() == s2.getz())
				return 0;
			else if(s1.getz()>s2.getz())
				return -1;
			else
				return 1;
		}


	};
	
	public String toString(){
		String toreturn = "Point: ("+ this.getx()+","+this.gety()+","+this.getz()+")";
		return toreturn;
	}

}
