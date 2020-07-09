package my.algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Scheduling {

    public static void main(String[] args) throws FileNotFoundException {
	// Load Test File
	File file = new File(new File("").getAbsolutePath().concat("/src/my/algorithms/testcases/jobs.txt"));
	Scanner sc = new Scanner(file);
	int len = sc.nextInt();
	Job[] jobs = new Job[len];
	for (int i = 0; i < len; i++) {
	    int w = sc.nextInt();
	    int l = sc.nextInt();
	    jobs[i] = new Job(w, l);
	}
	sc.close();
	System.out.println(Arrays.toString(jobs));

	// Schedule by Decreasing order of weight - length
	Job[] jobs1 = Arrays.copyOf(jobs, len);
	sortByDiff(jobs1);
	System.out.println(Arrays.toString(jobs1));
	System.out.println(completionTime(jobs1));
	System.out.println(weightedCompletionTime(jobs1));

	// Schedule by Decreasing order of weight / length
	Job[] jobs2 = Arrays.copyOf(jobs, len);
	sortByRatio(jobs2);
	System.out.println(Arrays.toString(jobs2));
	System.out.println(completionTime(jobs2));
	System.out.println(weightedCompletionTime(jobs2));

    }

    private static class Job {
	int weight;
	int length;

	public Job(int weight, int length) {
	    this.weight = weight;
	    this.length = length;
	}

	@Override
	public String toString() {
	    return weight + " " + length;
	}
    }

    public static void sortByDiff(Job[] jobs) {
	Arrays.sort(jobs, (j1, j2) -> {
	    int diff1 = j1.weight - j1.length;
	    int diff2 = j2.weight - j2.length;
	    if (diff1 == diff2) {
		if (j1.weight > j2.weight) {
		    return -1;
		} else {
		    return 1;
		}
	    }
	    return diff2 - diff1;
	});
    }

    public static void sortByRatio(Job[] jobs) {
	Arrays.sort(jobs, (j1, j2) -> {
	    double ratio1 = (double) j1.weight / j1.length;
	    double ratio2 = (double) j2.weight / j2.length;
	    if (ratio1 > ratio2) {
		return -1;
	    } else if (ratio1 < ratio2) {
		return 1;
	    }
	    return 0;
	});
    }

    public static long completionTime(Job[] jobs) {
	long completionTime = 0L;
	for (Job job : jobs)
	    completionTime += job.length;
	return completionTime;
    }

    public static long weightedCompletionTime(Job[] jobs) {
	long completionTime = 0L;
	long sum = 0L;

	for (Job job : jobs) {
	    completionTime += (long) job.length;
	    sum += ((long) job.weight) * completionTime;
	}
	return sum;
    }

}
