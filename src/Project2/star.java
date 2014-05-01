package Project2;


public class star extends point{
	
	String name;
	int id;
	
	public star(String Name, int ID, double X, double Y, double Z){
		super(X,Y,Z);
		this.id = ID;
		this.name = Name;
	}
	
	public star(int ID, double X, double Y, double Z){
		super(X,Y,Z);
		this.id = ID;
		name = "";
	}
	
	public String getName(){
		return this.name;
	}

	public int getId(){
		return this.id;
	}
	
	public String toString(){
		String toreturn = "ID: " + this.id +"\n";
		toreturn = toreturn + "Name: " + this.name + "\n";
		toreturn = toreturn + super.toString();
		return toreturn;
	}
	
	public String toStringID(){
		String toreturn = "";
		return toreturn + this.id;
	}

}
