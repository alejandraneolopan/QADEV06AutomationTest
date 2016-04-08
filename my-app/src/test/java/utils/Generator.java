package utils;

import java.util.Random;
import java.util.UUID;

public class Generator {
	
	 Random rand;
	 
	 public Generator()
	 {
		 rand= new Random();
	 }
	 
	 public int getRandNumber(int limit)
	 {
		 return rand.nextInt(limit);
	 }
	 public int getRandNumber(int min, int max)
	 {
		 return rand.nextInt(max - min + 1) + min;
	 }
	 public String getRandString()
	 {
		 return UUID.randomUUID().toString();
	 }

}
