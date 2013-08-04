import java.util.ArrayList;
import java.util.List;

public class PokerHandChecker {

	public static void main(String[] args) {
		System.out.println(test(args));
	}
	
	public static String test(String[] args) {
		String[] strs = args[0].split(" ");
		int[] nums = new int[strs.length];
		char[] suits = new char[strs.length];
		
		for(int i = 0; i < strs.length; i++) {
			nums[i] = convert(strs[i].charAt(0));
			suits[i] = strs[i].charAt(1);
		}
		
		PokerHandChecker checker = new PokerHandChecker();
		String result = checker.execute(suits, nums);
		
		return result;
	}

	public static int convert(char c) {
		if (c == 'A') {
			return 1;
		} else if ('2' <= c && c <= '9') {
			return c - 0x30;
		} else if (c == 'T') {
			return 10;
		} else if (c == 'J') {
			return 11;
		} else if (c == 'Q') {
			return 12;
		} else if (c == 'K') {
			return 13;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String execute(char[] suits, int[] nums) {
		String result;

		// ルール判定
		boolean isOnePair = checkOnePair(nums);
		boolean isTwoPair = checkTwoPair(nums);
		boolean isThreeCard = checkThreeCard(nums);
		boolean isFourCard = checkFourCard(nums);
		boolean isFlush = checkSuit(suits);
		boolean isStraight = checkStraight(nums);

		// 役のチェック
		if (isStraight && isFlush) {
			result = "straight flush¥n";
		} else if (isFourCard) {
			result = "four of a kind¥n";
		} else if (isThreeCard && isOnePair) {
			result = "full house¥n";
		} else if (isFlush) {
			result = "flush¥n";
		} else if (isStraight) {
			result = "straight¥n";
		} else if (isThreeCard) {
			result = "three of a kind¥n";
		} else if (isTwoPair) {
			result = "two pair¥n";
		} else if (isOnePair) {
			result = "one pair¥n";
		} else {
			result = "high cards¥n";
		}

		return result;
	}

	/**
	 * スートが同じかどうかをチェックする
	 * 
	 * @return スートが全部同じであればtrue, そうでなければfalse
	 */
	public boolean checkSuit(char[] cards) {
		// 探索対象
		char t = cards[0];

		for (char c : cards) {
			if (t != c) {
				return false;
			}
		}

		return true;
	}

	/**
	 * ストレートかどうかをチェックする
	 * 
	 * @param cards
	 *            カード
	 * @return ストレートであればtrue, そうでなければfalse
	 */
	public boolean checkStraight(int[] cards) {
		// 大きさ13のboolean配列に、数字があるところにtrueを入れる
		boolean[] flag = new boolean[14];
		for (int n : cards) {
			flag[n] = true;
		}

		// 通常のストレートのチェック
		int cardCount = 0;
		for (int i = 0; i < 14; i++) {
			if (flag[i] == true) {
				cardCount++;
				if (cardCount == 5) {
					return true;
				}
			} else if (cardCount > 0) {
				cardCount = 0;
			}
		}

		// 10, J, Q, K, Aのストレートのチェック
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);

		for (int n : cards) {
			if (list.contains(n)) {
				list.remove((Object) n);
			} else {
				return false;
			}
		}

		return true;
	}

	public boolean checkOnePair(int[] cards) {
		return checkCardNumber(cards, 2) == 1;
	}

	public boolean checkTwoPair(int[] cards) {
		return checkCardNumber(cards, 2) == 2;
	}

	public boolean checkFourCard(int[] cards) {
		return checkCardNumber(cards, 4) == 1;
	}

	public boolean checkThreeCard(int[] cards) {
		return checkCardNumber(cards, 3) == 1;
	}

	/**
	 * カードの数を調べる
	 * 
	 * @param cards
	 *            手札
	 * @param target
	 *            探索したい数(2ペアなら2, スリーカードなら3)
	 * @return 探索したい数
	 */
	private int checkCardNumber(int[] cards, int target) {
		int result = 0;
		int[] numMap = new int[14];

		// それぞれカードが何枚あるかを調べる
		for (int n : cards) {
			numMap[n]++;
		}

		// 指定された枚数のカードがあるか
		for (int n : numMap) {
			if (n == target) {
				result++;
			}
		}

		return result;
	}
}
