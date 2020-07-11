package my.algorithms;

import my.data_structures.Pair;

public class SequenceAlignment {

    public static void main(String[] args) {
	
	String str1 = "AGGTCA";
	String str2 = "AGCA";
	System.out.println("Penalty: " + stringAlignmentPenalty(str1, str2));
	Pair<String, String> pair = stringAlignment(str1, str2);
	System.out.println("[" + pair.get1() + "]");
	System.out.println("[" + pair.get2() + "]");
    }
    
    public static int stringAlignmentPenalty(String str1, String str2) {
	int[][] dp = stringAlignment_dp(str1, str2);
	int r = dp.length - 1;
	int c = dp[r].length - 1;
	return dp[r][c];
    }
    
    public static Pair<String, String> stringAlignment(String str1, String str2) {
	int[][] dp = stringAlignment_dp(str1, str2);
	int r = dp.length - 1;
	int c = dp[r].length - 1;
	char[] ch1 = str1.toCharArray();
	char[] ch2 = str2.toCharArray();
	StringBuilder out1 = new StringBuilder();
	StringBuilder out2 = new StringBuilder();
	
	// Reconstruct from DP
	while (r > 0 && c > 0) {
	    if (dp[r][c] == dp[r-1][c-1] + ((ch1[r-1] == ch2[c-1]) ? 0 : pStr)) { // Case 1
		out1.append(ch1[r-1]);
		out2.append(ch2[c-1]);
		r--;
		c--;
	    } else if (dp[r-1][c] < dp[r][c-1]) { // Case 2
		out1.append(ch1[r-1]);
		out2.append(' ');
		r--;
	    } else if (dp[r-1][c] > dp[r][c-1]) { // Case 3
		out1.append(' ');
		out2.append(ch2[c-1]);
		c--;
	    } else if (r > c) { // Case 2 & 3 tie-breaking
		out1.append(ch1[r-1]);
		out2.append(' ');
		r--;
	    } else { // Case 2 & 3 tie-breaking
		out1.append(' ');
		out2.append(ch2[c-1]);
		c--;
	    }
	}
	// Match rest characters with gaps
	while (r > 0) {
	    out1.append(ch1[r-1]);
	    out2.append(' ');
	    r--;
	}
	while (c > 0) {
	    out1.append(' ');
	    out2.append(ch2[c-1]);
	    c--;
	}
	// Reverse to original order
	out1.reverse();
	out2.reverse();
	return new Pair<String, String>(out1.toString(), out2.toString());
    }
    
    // Penalties
    private static int pGap = 1;
    private static int pStr = 1;

    private static int[][] stringAlignment_dp(String str1, String str2) {
	// Initialize DP memory
	int len1 = str1.length();
	int len2 = str2.length();
	int[][] dp = new int[len1+1][len2+1];
	for (int r = 0; r < len1; r++) dp[r][0] = r * pGap;
	for (int c = 0; c < len2; c++) dp[0][c] = c * pGap;
	// Build solution
	for (int r = 1; r <= len1; r++) {
	    for (int c = 1; c <= len2; c++) {
		int gap = Math.min(dp[r-1][c] + pGap, dp[r][c-1] + pGap);
		int str = dp[r-1][c-1] + ((str1.charAt(r-1) == str2.charAt(c-1)) ? 0 : pStr);
		// Minimum of 3 cases
		dp[r][c] = Math.min(gap, str);
	    }
	}
	return dp;
    }
    
}
