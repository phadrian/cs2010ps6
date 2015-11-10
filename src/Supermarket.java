//Copy paste this Java Template and save it as "Supermarket.java"
import java.util.*;
import java.io.*;

//write your matric number here: A0124123Y
//write your name here: Adrian Pheh
//write list of collaborators here: Code from Lab session
//year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class Supermarket {
	private int N; // number of items in the supermarket. V = N+1 
	private int K; // the number of items that Steven has to buy
	private int[] shoppingList; // indices of items that Steven has to buy
	private int[][] T; // the complete weighted graph that measures the direct walking time to go from one point to another point in seconds
	private int[][] memo;

	// if needed, declare a private data structure here that
	// is accessible to all methods in this class
	// --------------------------------------------



	public Supermarket() {
		// Write necessary code during construction
		//
		// write your answer here



	}

	int Query() {
		int ans = 0;

		// You have to report the quickest shopping time that is measured
		// since Steven enters the supermarket (vertex 0),
		// completes the task of buying K items in that supermarket as ordered by Grace,
		// then, reaches the cashier of the supermarket (back to vertex 0).
		//
		// write your answer here
		init();
		ans = TSP(0, 1);
		//displayMemo();

		return ans;
	}

	// You can add extra function if needed
	// --------------------------------------------
	private void init() {
		N++;
		memo = new int[N][1 << N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(memo[i], -1);
		}
	}
	
	private int TSP(int u, int m) {
		// If all vertices have been visited
		if (m == (1 << N) - 1) {
			return T[u][0];
		}
		// If the value has been computed before
		if (memo[u][m] != -1) {
			return memo[u][m];
		}
		
		// General case
		memo[u][m] = Integer.MAX_VALUE;
		for (int v = 0; v < N; v++) {
			if (((1 << v) & m) == 0) {
				memo[u][m] = Math.min(
						memo[u][m], 
						T[u][v] + TSP(v, (1 << v) | m));
			}
		}
		return memo[u][m];
	}
	
	private void displayMemo() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < memo[i].length; j++) {
				System.out.print(memo[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	void run() throws Exception {
		// do not alter this method to standardize the I/O speed (this is already very fast)
		IntegerScanner sc = new IntegerScanner(System.in);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

		int TC = sc.nextInt(); // there will be several test cases
		while (TC-- > 0) {
			// read the information of the complete graph with N+1 vertices
			N = sc.nextInt(); K = sc.nextInt(); // K is the number of items to be bought
			
			shoppingList = new int[K];
			for (int i = 0; i < K; i++)
				shoppingList[i] = sc.nextInt();

			T = new int[N+1][N+1];
			for (int i = 0; i <= N; i++)
				for (int j = 0; j <= N; j++)
					T[i][j] = sc.nextInt();

			pw.println(Query());
		}

		pw.close();
	}

	public static void main(String[] args) throws Exception {
		// do not alter this method
		Supermarket ps6 = new Supermarket();
		ps6.run();
	}
}



class IntegerScanner { // coded by Ian Leow, using any other I/O method is not recommended
	BufferedInputStream bis;
	IntegerScanner(InputStream is) {
		bis = new BufferedInputStream(is, 1000000);
	}

	public int nextInt() {
		int result = 0;
		try {
			int cur = bis.read();
			if (cur == -1)
				return -1;

			while ((cur < 48 || cur > 57) && cur != 45) {
				cur = bis.read();
			}

			boolean negate = false;
			if (cur == 45) {
				negate = true;
				cur = bis.read();
			}

			while (cur >= 48 && cur <= 57) {
				result = result * 10 + (cur - 48);
				cur = bis.read();
			}

			if (negate) {
				return -result;
			}
			return result;
		}
		catch (IOException ioe) {
			return -1;
		}
	}
}