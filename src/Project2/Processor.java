package Project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import com.aliasi.util.BoundedPriorityQueue;

public class Processor {

	String filename;
	HashMap<String, star> nameCatalog = new HashMap<String, star>();
	KDTree<star> ourtree = null;

	public Processor(String Phile){
		this.filename = Phile;
	}
	
	/*
	 * Input: nothing
	 * Output: nada
	 * creates a new reader, and takes the data from the reader's
	 * reading and makes a star from each String[] in the arraylist.
	 * The stars are then put in a list. 
	 * Lastly, the stars are added to a tree which is stored locally.
	 */
	public void DirtyWork(){
		Reader enter = new Reader(filename);
		ArrayList<star> forinput = new ArrayList<star>();

		ArrayList<String[]> rawdata = enter.reading();
		if(rawdata == null){
			System.out.println("ERROR: your file had zilch in it. Not cool.");
			return;
		}

		int Aidee;
		double ex;
		double why;
		double zee;

		for(String[] x: rawdata){
			try{
				Aidee = Integer.parseInt(x[0]);
				ex = Double.parseDouble(x[2]); 
				why = Double.parseDouble(x[3]); 
				zee = Double.parseDouble(x[4]); 

				String nombre = x[1];

				star thisstar = new star(nombre, Aidee, ex, why, zee);
				forinput.add(thisstar);
				if(nombre != ""){
					nameCatalog.put(nombre, thisstar);
				}

			}
			catch(NumberFormatException n){
				continue;
			}

		}

		KDTree<star> thistree = new KDTree<star>();
		thistree.makeTree(forinput);

		ourtree = thistree;

	}
	/*
	 * This is the function that deals with user input.
	 * This program doesn't output or take any input
	 * It runs in a while loop until certain things
	 * are done (ex: enter is pressed twice);
	 */

	public void gotime(){
		DirtyWork();
		Scanner interaction = new Scanner(System.in);
		ArrayList<Distance<star>> output;
		String input;
		String[] specificInput = null;
		String nombre;
		point togive;
		boolean cont;
		boolean nombreexists;

		boolean gotime = true;

		while(gotime){
			output = null;
			nombre = "";
			cont = true;
			specificInput = null;
			nombreexists = false;
			input = interaction.nextLine();
			input = input.trim();
			togive = null;
			int total = 0;
			if(input.equals("")){
				gotime = false;
				break;

			}
			String[] tempinput;
			tempinput = input.split("\"");

			if(tempinput.length==1 || tempinput.length ==2){
				if(tempinput.length ==1){
					specificInput = input.split("\\s+");
				}
				else if(tempinput.length == 2){
					String[] temptempinput = tempinput[0].split("\\s+");
					if(temptempinput.length==2){
						specificInput = new String[3];
						specificInput[0] = temptempinput[0];
						specificInput[1] = temptempinput[1];
						specificInput[2] = "\""+tempinput[1]+"\"";
					}
					else{
						System.err.println("ERROR: Argument inputed incorrectly");
						cont = false;
						continue;
					}

				}
				else{
					System.err.println("ERROR: the input argument is wrong!");
					cont = false;
					continue;
				}
				if(cont){
					//there can only be two types of arguments (ones with command, radius/neighbors and 3 points or a name) 
					if(specificInput.length == 3 || specificInput.length == 5){
						//if it's a name
						if(specificInput.length == 3){
							//check if the last word is in quotes;
							String starname = specificInput[2];
							if(starname.length()>2 && (starname.charAt(0)==('"'))&&(starname.charAt((starname.length()-1))==('"'))){
								starname = starname.replace("\"", "");
								if(this.nameCatalog.containsKey(starname)){
									nombreexists = true;
									nombre = starname;
									togive  = nameCatalog.get(nombre);
								}
								else{
									System.out.println("ERROR: this star doesn't exist!");
									cont = false;
									continue;
								}

							}
							else{
								System.out.println("ERROR: The star name must be surrounded by quotes");
								cont = false;
								continue;
							}

						}

						else if(specificInput.length == 5){
							try{
								double tempx = Double.parseDouble(specificInput[2]);
								double tempy = Double.parseDouble(specificInput[3]);
								double tempz = Double.parseDouble(specificInput[4]);

								togive = new point(tempx, tempy, tempz);
							}
							catch(NumberFormatException e){
								System.out.println("ERROR: please input the location coordinates as decimal points! Thanks!");
								cont = false;
								continue;
							}
						}
					}
					else{
						System.out.println("ERROR: Wrong number of arguments inputed. Try again!");
						cont = false;
						continue;
					}
					if(cont){
						if((specificInput[0].equals("neighbors")) || (specificInput[0].equals("radius"))){

							if(specificInput[0].equals("neighbors")){
								try{
									int nums = Integer.parseInt(specificInput[1]);
									total = nums;
									if(nums>=0){
										if(nums != 0){

											output = this.ourtree.KnearestNeigbhors(nums, togive, nombreexists);
											if(nombreexists){
												total = total + 1;
											}
											if(output.size()<total){
												total = output.size();
											}

										}
										else{
											System.out.println("");
											continue;
										}
									}
									else{
										System.out.println("ERROR: You cannot have a negative number of neighbors to find...try again");
									}

								}
								catch(NumberFormatException e){
									System.out.println("ERROR: please input an integer for the number of neighbors argument - thanx!");
									continue;
								}
							}

							else if(specificInput[0].equals("radius")){
								try{
									int nums = Integer.parseInt(specificInput[1]);
									if(nums>=0){
										output = this.ourtree.RadiusMethod(togive, nums);
										total = output.size();

									}
									else{
										System.out.println("ERROR: the radius can't be a negative number.");
									}

								}
								catch(NumberFormatException e){
									System.out.println("ERROR: please input an integer for the radius argument! thanx!");
									continue;
								}
							}

							if(output.size()!= 0 || output != null){
								ArrayList<Distance<star>> tempoutput = new ArrayList<Distance<star>>();
								for(Distance<star> i: output){
									String name = i.getStar().getName();
									
									if(nombreexists&&(nombre.equals(name))){
										total= total-1;
									}
									else{
										tempoutput.add(i);
									}
								}
								Collections.sort(tempoutput, Distance.DistanceComparator2);
								for(int i = 0; i< total; i++){
									Distance<star> m = tempoutput.get(i);
									System.out.println(m.getStar().getId());
								}
								tempoutput.clear();
								output.clear();
							}
							System.out.println("");
						

						}
						else{
							System.out.println("ERROR: that is an invalid command! 'neighbor' and 'radius' are the only valid commands");
							continue;
						}
					}
				}





				else{
					System.out.println("ERROR: You have inputed an incorrect number of arguments");
					continue;
				}
			}
			else{
				System.out.println("ERROR: You have inputed an incorrect number of arguments");
				continue;
			}

		}


		interaction.close();
	}
}
