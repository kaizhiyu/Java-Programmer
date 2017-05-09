1.回文字符串:
	1.1.描述:给定一个字符串，首尾两端没有空格，判断它是否为回文字符串
		回文字符串，就是正过来读、反过来读都一样的字符串
		"abcdcba"是回文字符串，"abcdeba"不是回文字符串。
		额外要求：时间复杂度为 O(N)，空间复杂度为 O(1)
	1.2.实现:
		public class PalidromString {
			public boolean isPalidrom(String str) {
				if (str == null || str.length() < 1) {
					return false;
				}
				for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
					if(str.charAt(i) != str.charAt(j)){
						return false;
					}
				}
				return true;
			}
		}

2.加强版回文字符串:
	2.1.描述:给定字符串，判断它是否为回文字符串
		只考虑字母和数字
		忽略大小写
		时间复杂度为O(N)，空间复杂度为O(1)
	2.2.实现:一直往中间移动指针，直到出现alpha
		public class PalindromStongString {
			public boolean isAlpha(char c) {
				if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')) {
					return true;
				} else {
					return false;
				}
			}
			public boolean isPalindromString(String s) {
				if (s == null || s.length() < 1) {
					return false;
				}
				s = s.toLowerCase();
				for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
					while (i < j && !isAlpha(s.charAt(i))) {
						i++;
					}
					while (i < j && !isAlpha(s.charAt(j))) {
						j--;
					}
					if (s.charAt(i) != s.charAt(j)) {
						return false;
					}
				}
				return true;
			}
			@Test
			public void test(){
				System.out.println(isPalindromString("A man, a plan, a canal: Panama"));
				System.out.println(isPalindromString("race a car"));
			}
		}

3.回文数字:
	3.1.描述:已知一个整数，判断它是否回文。如：1245421是回文，而12342不是回文。
		(1).要求空间复杂度必须为O(1)，不能使用数组、集合、字符串等等
		(2).边界情况：
			负数不回文
			单个数字肯定回文
	3.2.实现:
		/**
		 * 回文数字： 不能使用数组、集合、字符串
		 */
		public class PalindromNumber {
			/**
			 * 获取数字 的位数
			 */
			public int wei(int x) {
				int count = 0;
				while (x > 0) {
					x /= 10;
					count++;
				}
				return count;
			}

			/**
			 * 整数 m 的 n 次幂
			 */
			public int pow(int m, int n) {
				int mul = 1;
				for (int i = 1; i <= n; i++) {
					mul *= m;
				}
				return mul;
			}
			public boolean isPalindrom(int x) {
				if (x < 0) {
					return false;
				} else if (x < 10) {
					return true;
				} else {
					int wei = wei(x),
						t = (int) Math.pow(10, wei - 1),
						n = x,
						half = wei / 2;
					for(int i = 0;i < half; i++){
						if(x / t % 10 == n % 10){
							t /= 10;
							n /= 10;
						}else{
							return false;
						}
					}
					return true;
				}
			}
			
			@Test
			public void test(){
				System.out.println(isPalindrom(1234321));
			}
		}

4.最长回文子串:
	4.1.描述:给定字符串，求它的最长回文子串（LPS）。如：
			LPS(ghlabcbatyi)=abcba, LPS(ghlabccbatyi)=abccba			
	4.2.暴力求解法:
		(1).思路:穷举思路：取出所有的子串，判断它是否回文，并返回最长的子串
		(2).实现:时间复杂度 O(N^3), 空间复杂度 O(1)
			/**
			 * 最长回文子串
			 */
			public class LongPalindromString {
				public boolean isPalindrom(String s, int start, int end) {
					for (int i = start, j = end; i < j; i++, j--) {
						if (s.charAt(i) != s.charAt(j)) {
							return false;
						}
					}
					return true;
				}
				public String longestPalindromString(String s) {
					if (s == null || s.length() <= 1) {
						return s;
					} else {
						int n = s.length(), max = 0, from = 0, to = 1;
						for (int i = 0; i < n; i++) {
							for (int j = i; j < n; j++) {
								if(isPalindrom(s, i, j)){
									if(j - i + 1 >= max){
										max = j -i + 1;
										from = i;
										to = j;
									}
								}
							}
						}
						return s.substring(from, to+1);
					}
				}
			}
	4.3.中心扩展法:时间复杂度 O(N^2), 空间复杂度 O(1)
		(1).思路:
			从中间往两边读是一样的！
		(2).实现:
			public String longestPalindromString02(String s){
				int maxLeft = 0,
					maxRight = 0,
					max = 1,
					n = s.length();
				for(int i=0;i<n;i++){
					/**
					 * 偶数的最长回文子串
					 */
					int start = i,
						end = i + 1,
						len = 0,
						// left, right防止越界
						left = start,
						right = end;
					while(start >= 0 && end < n){
						if(s.charAt(start) == s.charAt(end)){
							len = len + 2;
							left = start;
							right = end;
							start--;
							end++;
						}else{
							break;
						}
					}
					if(len > max){
						maxLeft = left;
						maxRight = right;
						max = len;
					}
					/**
					 * 奇数最长回文子串
					 */
					start = i - 1;
					end = i + 1;
					len = 1;
					left = start;
					right = end;
					while(start >= 0 && end < n){
						if(s.charAt(start) == s.charAt(end)){
							len = len + 2;
							left = start;
							right = end;
							start--;
							end++;
						}else{
							break;
						}
					}
					if(len > max){
						maxLeft = left;
						maxRight = right;
						max = len;
					}
				}
				return s.substring(maxLeft, maxRight+1);
			}
	4.4.动态规划法:如果看到"最"和"子"两个关键字,就要条件反射地往动态规划的方向去思考

	4.5.Manacher 算法:可以在 O(N)时间内解决最长回文子串



















































