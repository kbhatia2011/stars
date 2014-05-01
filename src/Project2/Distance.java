package Project2;

import java.util.Comparator;

/*
 * Distances are temporary storages. They are used for to make
 * output easier at the very end. 
 */

public class Distance<K extends point>{
	
	K output;
	double distance;
	
	public Distance(K thisone, double d){
		output = thisone;
		distance = d;
	}
	
	public double getDistance(){
		return this.distance;
	}
	
	public K getStar(){
		return this.output;
	}
	
	@SuppressWarnings("rawtypes")
	public static Comparator<Distance<?>> DistanceComparator = new Comparator<Distance<?>>() {

		public int compare(Distance d1, Distance d2) {
			if(d1.getDistance() == d2.getDistance())
				return 0;
			else if(d1.getDistance()>d2.getDistance())
				return -1;
			else
				return 1;
		}
	

	};
	
	
	public static Comparator<Distance<?>> DistanceComparator2 = new Comparator<Distance<?>>() {

		public int compare(Distance<?> d1, Distance<?> d2) {
			if(d1.getDistance() == d2.getDistance())
				return 0;
			else if(d1.getDistance()>d2.getDistance())
				return 1;
			else
				return -1;
		}
	

	};

}
