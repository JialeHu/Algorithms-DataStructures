package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import my.data_structures.Pair;

public class TwoSAT {

    public static void main(String[] args) throws FileNotFoundException {
	
	/*
	 * [# variables = #clauses]
	 * [literal 1 with variable number] [literal 2 with variable number]
	 * "-" denotes "not"
	 */
	
	System.out.println("2sat1:");
	File file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/2sat1.txt"));
	Scanner sc = new Scanner(file);
	System.out.println(sc.nextLine());
	
	List<Pair<Integer, Integer>> clauses = new ArrayList<>();
	while (sc.hasNextInt()) {
	    int x = sc.nextInt();
	    int y = sc.nextInt();
	    clauses.add(new Pair<Integer, Integer>(x, y));
	}
	sc.close();
	
	System.out.println(clauses.size());
	System.out.println(papadimitriou(clauses));
	
	System.out.println("----------");
	
	System.out.println("2sat2:");
	file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/2sat2.txt"));
	sc = new Scanner(file);
	System.out.println(sc.nextLine());
	
	clauses = new ArrayList<>();
	while (sc.hasNextInt()) {
	    int x = sc.nextInt();
	    int y = sc.nextInt();
	    clauses.add(new Pair<Integer, Integer>(x, y));
	}
	sc.close();
	
	System.out.println(clauses.size());
	System.out.println(papadimitriou(clauses));
	
	System.out.println("----------");
	
	System.out.println("2sat3:");
	file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/2sat3.txt"));
	sc = new Scanner(file);
	System.out.println(sc.nextLine());
	
	clauses = new ArrayList<>();
	while (sc.hasNextInt()) {
	    int x = sc.nextInt();
	    int y = sc.nextInt();
	    clauses.add(new Pair<Integer, Integer>(x, y));
	}
	sc.close();
	
	System.out.println(clauses.size());
	System.out.println(papadimitriou(clauses));
	
	System.out.println("----------");
	
	System.out.println("2sat4:");
	file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/2sat4.txt"));
	sc = new Scanner(file);
	System.out.println(sc.nextLine());
	
	clauses = new ArrayList<>();
	while (sc.hasNextInt()) {
	    int x = sc.nextInt();
	    int y = sc.nextInt();
	    clauses.add(new Pair<Integer, Integer>(x, y));
	}
	sc.close();
	
	System.out.println(clauses.size());
	System.out.println(papadimitriou(clauses));
	
	System.out.println("----------");
	
	System.out.println("2sat5:");
	file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/2sat5.txt"));
	sc = new Scanner(file);
	System.out.println(sc.nextLine());
	
	clauses = new ArrayList<>();
	while (sc.hasNextInt()) {
	    int x = sc.nextInt();
	    int y = sc.nextInt();
	    clauses.add(new Pair<Integer, Integer>(x, y));
	}
	sc.close();
	
	System.out.println(clauses.size());
	System.out.println(papadimitriou(clauses));
	
	System.out.println("----------");
	
	System.out.println("2sat6:");
	file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/2sat6.txt"));
	sc = new Scanner(file);
	System.out.println(sc.nextLine());
	
	clauses = new ArrayList<>();
	while (sc.hasNextInt()) {
	    int x = sc.nextInt();
	    int y = sc.nextInt();
	    clauses.add(new Pair<Integer, Integer>(x, y));
	}
	sc.close();
	
	System.out.println(clauses.size());
	System.out.println(papadimitriou(clauses));

    }
    
    
    public static boolean papadimitriou(List<Pair<Integer, Integer>> clauses) {
	
	int n = clauses.size();
	
	Random rand = new Random();
	boolean[] bools = new boolean[n];
	
	// Run log_2(n) times
	for (int i = 0; i < (int)(Math.log(n) / Math.log(2)); i++) {
	    
	    System.out.println(i);
	    
	    // Random boolean assignment
	    for (int b = 0; b < n; b++) {
		bools[b] = rand.nextBoolean();
	    }
	    
	    // Run 2n^2 times
	    for (long j = 0; j < (long) 2*n*n; j++) {
		Pair<Integer, Integer> unsatClause = checkClauses(clauses, bools);
		
		if (unsatClause == null) {
		    // Satisfiable
		    return true;
		} else {
		    // Flip a boolean randomly
		    if (rand.nextBoolean()) {
			int idx = Math.abs(unsatClause.get1()) - 1;
			bools[idx] = !bools[idx];
		    } else {
			int idx = Math.abs(unsatClause.get2()) - 1;
			bools[idx] = !bools[idx];
		    }
		}
	    }
	}
	
	return false;
    }
    
    /**
     * @param clauses of variables
     * @param bools assignments of clauses
     * @return {@code null) if {@code clauses} with assignments {@code bools} are satisfiable, 
     * else return the first unsatisfiable pair
     */
    private static Pair<Integer, Integer> checkClauses(List<Pair<Integer, Integer>> clauses, boolean[] bools) {
	
	boolean isSat =  clauses.parallelStream().anyMatch(clause -> {
	    int c1 = clause.get1();
	    int c2 = clause.get2();
	    boolean b1 = (c1 > 0) ? bools[c1-1] : !bools[-c1-1];
	    boolean b2 = (c2 > 0) ? bools[c2-1] : !bools[-c2-1];
	    if (b1 && b2) {
		unsatClause = clause;
		return true; // Satisfiable
	    }
	    else return false; // Unsatisfiable
	});
	
	if (isSat) return unsatClause;
	else return null;
	
	
//	for (Pair<Integer, Integer> clause : clauses) {
//	    int c1 = clause.get1();
//	    int c2 = clause.get2();
//	    boolean b1 = (c1 > 0) ? bools[c1-1] : !bools[-c1-1];
//	    boolean b2 = (c2 > 0) ? bools[c2-1] : !bools[-c2-1];
//	    if (!(b1 && b2)) return clause; // Unsatisfiable
//	}
//	return null; // Satisfiable
    }
    private static Pair<Integer, Integer> unsatClause = null;

}
