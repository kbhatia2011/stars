package Project2;

public class starsmain {
	public static void main(String[] args){
		//if no files are inputed
		if(args.length==0){
			System.out.println("ERROR: you didn't input any information!");
		}
		else{
		//read the file!
		Processor thisone = new Processor(args[0]);
		thisone.gotime();

		}
	}

}
