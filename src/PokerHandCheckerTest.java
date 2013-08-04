import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PokerHandCheckerTest {

	/**
	 * フラッシュ
	 */
	@Test
	public void testCheckSuit1() {
		PokerHandChecker checker = new PokerHandChecker();
		char[] cards = new char[] { 'S', 'S', 'S', 'S', 'S' };
		assertTrue(checker.checkSuit(cards));
	}

	/**
	 * フラッシュじゃない
	 */
	@Test
	public void testCheckSuit2() {
		PokerHandChecker checker = new PokerHandChecker();
		char[] cards = new char[] { 'S', 'S', 'S', 'H', 'H' };
		assertTrue(!checker.checkSuit(cards));
	}

	/**
	 * ストレートじゃない
	 */
	@Test
	public void testStraight1() {
		PokerHandChecker checker = new PokerHandChecker();
		int[] cards = new int[] { 1, 2, 5, 6, 7 };
		assertTrue(!checker.checkStraight(cards));
	}

	/**
	 * ストレート
	 */
	@Test
	public void testStraight2() {
		PokerHandChecker checker = new PokerHandChecker();
		int[] cards = new int[] { 1, 2, 3, 4, 5 };
		assertTrue(checker.checkStraight(cards));
	}

	/**
	 * ストレート(10, J, Q, K, A)
	 */
	@Test
	public void testStraight3() {
		PokerHandChecker checker = new PokerHandChecker();
		int[] cards = new int[] { 10, 11, 12, 13, 1 };
		assertTrue(checker.checkStraight(cards));
	}

	/**
	 * スリーカード判定のチェック(正しい)
	 */
	@Test
	public void testCheckThreeCard1() {
		PokerHandChecker checker = new PokerHandChecker();
		int[] cards = new int[] { 1, 2, 4, 4, 4 };
		assertTrue(checker.checkThreeCard(cards));
	}

	/**
	 * スリーカード判定のチェック(ツーペア場合)
	 */
	@Test
	public void testCheckThreeCard2() {
		PokerHandChecker checker = new PokerHandChecker();
		int[] cards = new int[] { 1, 2, 2, 4, 4 };
		assertTrue(!checker.checkThreeCard(cards));
	}

	/**
	 * スリーカード判定のチェック(4カードの場合)
	 */
	@Test
	public void testCheckThreeCard3() {
		PokerHandChecker checker = new PokerHandChecker();
		int[] cards = new int[] { 5, 2, 5, 5, 5 };
		assertTrue(!checker.checkThreeCard(cards));
	}

	/**
	 * ツーペア判定のチェック(正しい)
	 */
	@Test
	public void testCheckTwoPair1() {
		PokerHandChecker checker = new PokerHandChecker();
		int[] cards = new int[] { 2, 2, 3, 4, 4 };
		assertTrue(checker.checkTwoPair(cards));
	}

	/**
	 * ツーペア判定のチェック(スリーカードの場合)
	 */
	@Test
	public void testCheckTwoPair2() {
		PokerHandChecker checker = new PokerHandChecker();
		int[] cards = new int[] { 2, 2, 2, 1, 4 };
		assertTrue(!checker.checkTwoPair(cards));
	}

	/**
	 * スリーカード判定のチェック(4カードの場合)
	 */
	@Test
	public void testCheckTwoPair3() {
		PokerHandChecker checker = new PokerHandChecker();
		int[] cards = new int[] { 5, 2, 5, 5, 5 };
		assertTrue(!checker.checkTwoPair(cards));
	}

	/**
	 * スリーカード判定のチェック(バラバラ)
	 */
	@Test
	public void testCheckTwoPair4() {
		PokerHandChecker checker = new PokerHandChecker();
		int[] cards = new int[] { 5, 4, 6, 7, 8 };
		assertTrue(!checker.checkTwoPair(cards));
	}

	@Test
	public void testMain01() {
		String[] strs = new String[] { "As Ks Qs Js Ts" };
		String result = PokerHandChecker.test(strs);

		assertEquals(result, "straight flush¥n");
	}
	
	@Test
	public void testMain02() {
		String[] strs = new String[] { "7s 7h 7d 7c As" };
		String result = PokerHandChecker.test(strs);

		assertEquals(result, "four of a kind¥n");
	}
	
	@Test
	public void testMain03() {
		String[] strs = new String[] { "Ts Th Td 7c 7d" };
		String result = PokerHandChecker.test(strs);

		assertEquals(result, "full house¥n");
	}
	
	@Test
	public void testMain04() {
		String[] strs = new String[] { "Ad 4d 5d Jd Kd" };
		String result = PokerHandChecker.test(strs);

		assertEquals(result, "flush¥n");
	}
	
	@Test
	public void testMain05() {
		String[] strs = new String[] { "2s 3h 4s 5d 6c" };
		String result = PokerHandChecker.test(strs);

		assertEquals(result, "straight¥n");
	}
	
	@Test
	public void testMain06() {
		String[] strs = new String[] { "9s 9h 9d Ts 3s" };
		String result = PokerHandChecker.test(strs);

		assertEquals(result, "three of a kind¥n");
	}
	
	@Test
	public void testMain07() {
		String[] strs = new String[] { "Ts Th 2c 2h 5d" };
		String result = PokerHandChecker.test(strs);

		assertEquals(result, "two pair¥n");
	}
	
	@Test
	public void testMain08() {
		String[] strs = new String[] { "2s 2d 5c 6d 9c" };
		String result = PokerHandChecker.test(strs);

		assertEquals(result, "one pair¥n");
	}
	
	@Test
	public void testMain09() {
		String[] strs = new String[] { "Ah Jc 5d 4s 9c" };
		String result = PokerHandChecker.test(strs);

		assertEquals(result, "high cards¥n");
	}
}
