package Project2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Reader {

	FileSystem files;
	Path doc;

	public Reader(String file){

		files = FileSystems.getDefault();
		doc = files.getPath(file);

	}

	public ArrayList<String[]> reading(){

		ArrayList<String> rawdata = new ArrayList<String>();
		ArrayList<String[]> toreturn;
		String curr;
		BufferedReader BR = null;

		try {
			BR = Files.newBufferedReader(doc, Charset.defaultCharset());
		} catch (IOException e) {
			System.out.println("ERROR: the file you inputed is ERRORFULL.");
		}
		try{
			while((curr = BR.readLine())!= null){
				rawdata.add(curr);

			}
			if(rawdata.size()==0){
				System.out.println("ERROR: This file contains no data!");
				return null;
			}
			BR.close();
			toreturn = new ArrayList<String[]>();
			for(String i: rawdata){
				String[] temp = i.split(",");
				toreturn.add(temp);

			}
			return toreturn;
		}
		catch(IOException e){
			System.out.println("ERROR: the file you inputed is ERRORFULL.");
			return null;
		}
	}

}
