package tech.utilis.common.lang;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eugene Dementiev
 */
public class StringUtilsTest {
	
	public StringUtilsTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of countCommonNGrams method, of class StringUtils.
	 */
	@Test
	public void testCountCommonNGrams1() {
		System.out.println("countCommonNGrams");
		assertEquals(1, StringUtils.countCommonNGrams("foo", "foo", 3));
		assertEquals(0, StringUtils.countCommonNGrams("a", "a", 3));
		assertEquals(3, StringUtils.countCommonNGrams("abcdq", "abcdef", 2));
		assertEquals(2, StringUtils.countCommonNGrams("ab0cd0", "abcd", 2));
		assertEquals(0, StringUtils.countCommonNGrams("ab0cd0", "abcd", 3));
		assertEquals(0, StringUtils.countCommonNGrams("abcde", "a", 3));
	}

	/**
	 * Test of maxCommonNGrams method, of class StringUtils.
	 */
	@Test
	public void testMaxCommonNGrams1() {
		System.out.println("testMaxCommonNGrams1");
		
		int[][] cases = new int[][]{
			{5, 5, 2, 4},
			{5, 3, 2, 2},
			{5, 10, 2, 4},
			{5, 10, 3, 3},
			{0, 0, 2, 0},
		};
		
		for(int[] tCase: cases){
			assertEquals(tCase[3], StringUtils.maxCommonNGrams(tCase[0], tCase[1], tCase[2]));
		}
	}
	
	/**
	 * Test of maxCommonNGrams method, of class StringUtils.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMaxCommonNGrams2() {
		StringUtils.maxCommonNGrams(-1, 10, 3);
	}
	
	/**
	 * Test of maxCommonNGrams method, of class StringUtils.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMaxCommonNGrams3() {
		StringUtils.maxCommonNGrams(5, -1, 3);
		StringUtils.maxCommonNGrams(10, 10, 1);
	}
	
	/**
	 * Test of maxCommonNGrams method, of class StringUtils.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testMaxCommonNGrams4() {
		StringUtils.maxCommonNGrams(10, 10, 1);
	}

	/**
	 * Test of toNGramArray method, of class StringUtils.
	 */
	@Test
	public void testToNGramArray1() {
		String string = "Lorem";
		String[] nGrams = new String[]{"Lor", "ore", "rem"};
		assertArrayEquals(nGrams, StringUtils.toNGramArray(string, 3));
	}
	
	/**
	 * Test of toNGramArray method, of class StringUtils.
	 */
	@Test
	public void testToNGramArray2() {
		String string = "Lorem";
		String[] nGrams = new String[]{"Lo", "or", "re", "em"};
		assertArrayEquals(nGrams, StringUtils.toNGramArray(string, 2));
	}
	
	/**
	 * Test of toNGramArray method, of class StringUtils.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testToNGramArray3() {
		String string = "Lorem";
		StringUtils.toNGramArray(string, 0);
	}
	
	/**
	 * Test of toNGramArray method, of class StringUtils.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testToNGramArray4() {
		String string = "";
		StringUtils.toNGramArray(string, 5);
	}

	/**
	 * Test of isVisibleWhiteSpace method, of class StringUtils.
	 */
	@Test
	public void testIsVisibleWhiteSpaceValids() {
		
		System.out.println("testIsVisibleWhiteSpaceValids");
		
		char[] vwss = new char[]{
			' ', '	', ' ', 157, 127, ' ', ' ', ' '
		};
		
		for (char ch: vwss){
			System.out.println("["+ch+"]");
			assertTrue(StringUtils.isVisibleWhiteSpace(ch));
		}
	}
	
	/**
	 * Test of isVisibleWhiteSpace method, of class StringUtils.
	 */
	@Test
	public void testIsVisibleWhiteSpaceInvalids() {
		
		System.out.println("testIsVisibleWhiteSpaceInvalids");
		
		char[] nvwss = new char[]{
			'a', 126, '_'
		};
		
		for (char ch: nvwss){
			assertTrue(!StringUtils.isVisibleWhiteSpace(ch));
		}
	}
	
}
