/**
 * File: dHash class; builds dHash using Python library
 * needs: python installed and pip installed (built-in for
 * Python 2 >= 2.7.9 and Python 3 >= 3.4)
 * installs Pillow and dhash into python packages
 * Author: Ryan Sellar
 * Date: Dec. 03 2017
 */
 
import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

class DHash{
	
	private ProcessBuilder pb;
	private Process p;
	
	public DHash(){
		//installs Pillow and dhash
		try {
			pb = new ProcessBuilder("pip", "install", "Pillow");
			p = pb.start();
			
			//Not necessary to install dhash if dHash.py is in directory
			//pb.command("pip", "install", "dhash");
			//p = pb.start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//returns the hash as a BigInteger
	public BigInteger getHash(String fileLocation){
		
		try {			
			pb.command("python", "dhash.py", fileLocation);
			p = pb.start();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String line = in.readLine();	//reads in hex string
			
			// the hex string is too large for even a long so we use BigInteger
			BigInteger hash = new BigInteger(line.substring(0, 32), 16);
			
			return hash;
			
		} catch (Exception e){
			System.out.println(e);
		}
		return null;
	}
	
	//test function
	/* enter path of image file to get the dHash
	   if anything that is not an image is read-in, it prints 
	   out null pointer exception and returns null */
  	public static void main(String args[]){
		DHash newDHash = new DHash();
		System.out.println(newDHash.getHash(args[0]));
	}
	
	
}