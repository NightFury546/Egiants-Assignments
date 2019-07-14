import java.io.*;
import java.util.*;

public class WordCounter {

	public static void main(String[] args) throws IOException{
		
		// create input stream and scanner 
		FileInputStream fin =  new FileInputStream("readWords.txt");
		Scanner fileInput = new Scanner(fin);
		
		// Create the array list 
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Integer> count = new ArrayList<Integer>();
		
		// Read Through File and find the words
		while(fileInput.hasNext()) {
			// get the next word
			String nextWord = fileInput.next();
			
			// determine if the word is in the list ot not
			if(words.contains(nextWord)) {
				int index =  words.indexOf(nextWord);
				count.set(index, count.get(index)+1);
			}
			
			else {
				words.add(nextWord);
				count.add(1);
			}
		}
		
		// close  
		fileInput.close();
		fin.close();
		
		// print the values 
		
		for(int i =0; i< words.size();i++) {
			System.out.println(words.get(i) + " occured " + count.get(i) +  " Times");
		}
	}
}
