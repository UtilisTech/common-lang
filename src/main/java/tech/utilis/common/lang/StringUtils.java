package tech.utilis.common.lang;

import java.util.Arrays;

/**
 * StringUtils provides utility functions for processing strings and characters
 * 
 * @author Eugene Dementiev
 */
public class StringUtils {
	/**
	 * Calculates the number of common n-grams in the haystack (case sensitive).
	 * <br>
	 * Needle may be longer than the haystack, but in that case the number of common n-grams will be limited by the haystack size.
	 * <br>
	 * The n-grams are not unique.
	 * 
	 * @param needle the needle string
	 * @param haystack the haystack string
	 * @param nGramLength length of n-grams
	 * 
	 * @return the number of common n-grams in the haystack
	 */
	public static int countCommonNGrams(String needle, String haystack, int nGramLength){
		int overlaps = 0;
		
		if (needle.length() < nGramLength || haystack.length() < nGramLength){
			return 0;
		}
		
		if (needle.equals(haystack)){
			return maxCommonNGrams(needle.length(), haystack.length(), nGramLength);
		}
		
		for (int i = 0; i < needle.length() - nGramLength + 1; i++){
			if (haystack.contains(needle.substring(i, i+nGramLength))){
				overlaps++;
			}
		}
		
		return overlaps;
	}
	
	/**
	 * Calculates maximum possible number of common n-grams given length of haystack and needle.
	 * <br>
	 * Needle may be longer than the haystack, but in that case the max number of common n-grams will be limited by the haystack size.
	 * <br>
	 * The n-grams are not unique.
	 * 
	 * @param lengthNeedle length of the needle string
	 * @param lengthHaystack length of the haystack string
	 * @param lengthNGram length of the n-gram
	 * 
	 * @return maximum number of common n-grams between given strings or 0 if needle or haystack length is zero
	 * 
	 * @throws IllegalArgumentException if n-gram length less than 2, or needle or haystack length is negative
	 */
	public static int maxCommonNGrams(int lengthNeedle, int lengthHaystack, int lengthNGram){
		
		if (lengthNGram < 2){
			throw new IllegalArgumentException("N-gram length can't be less than 2");
		}
		
		if (lengthNeedle < 0 || lengthHaystack < 0){
			throw new IllegalArgumentException("Needle or haystack lengths can't be negative");
		}
		
		if (lengthNeedle == 0 || lengthHaystack == 0){
			return 0;
		}
		
		if (lengthHaystack >= lengthNeedle){
			return lengthNeedle + 1 - lengthNGram;
		}
		return lengthHaystack + 1 - lengthNGram;
	}
	
	/**
	 * Splits the given string into n-grams of given length.
	 * 
	 * @param string string to split
	 * @param lengthNGram n-gram length
	 * 
	 * @return array of n-grams
	 * 
	 * @throws IllegalArgumentException if string is shorter than n-gram or n-gram length is less than 2
	 */
	public static String[] toNGramArray(String string, int lengthNGram){
		
		if (lengthNGram < 2){
			throw new IllegalArgumentException("N-gram can't be shorter than 2: " + lengthNGram);
		}
		
		if (string.length() < lengthNGram){
			throw new IllegalArgumentException("String is shorter than n-gram length: " + string.length() + " < " + lengthNGram);
		}
		
		String[] nGrams = new String[string.length() + 1 - lengthNGram];
		
		for (int i = 0; i < nGrams.length; i++){
			nGrams[i] = string.substring(i, i + lengthNGram);
		}
		
		return nGrams;
	}
	
	
	private static final int[] VISIBLE_SPACES = new int[]{
		127, 128, 129, 141, 142, 143, 144, 149, 157, 158, 160
	};
	
	/**
	 * Checks whether the given character is a white space or an invisible character.
	 * <br>
	 * Specifically, if its corresponding int value is less then 33 or any from this list:
	 * <br>
	 * 127, 128, 129, 141, 142, 143, 144, 149, 157, 158, 160,
	 * <br>
	 * it will return true.
	 * <br>
	 * Will also return true if matches [\\p{Z}\\s\\p{javaSpaceChar}\\p{Zs}].
	 * 
	 * @param ch character to check for white space
	 * 
	 * @return true if c is a visible white space
	 */
	public static boolean isVisibleWhiteSpace(char ch){
		
		if (ch <= 32){
			return true;
		}
		
		int character = (int)ch;
		
		if (Arrays.binarySearch(VISIBLE_SPACES, character) >= 0){
			return true;
		}
		
		if (String.valueOf(ch).matches("[\\p{Z}\\s\\p{javaSpaceChar}\\p{Zs}]")){
			return true;
		}
		
		return false;
	}

}
