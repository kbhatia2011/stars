package Project2;

public class Node<K extends point> {
	
	K local;
	KDTree<K> left;
	KDTree<K> right;
	
	
	public Node(K point){
		this.local = point;
		this.left = null;
		this.right = null;
	}
	
	
	
	public boolean is_end(){
		return ((left == null) && (right == null));
	}
	
	
	
	

}
