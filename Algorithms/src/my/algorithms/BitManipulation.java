package my.algorithms;

public class BitManipulation {

    public static void main(String[] args) {

    }

    public static int HammingDistance(int n1, int n2) {
	int diff = n1 ^ n2;
	int count = 0;
	while (diff > 0) {
	    count += diff & 1;
	    diff >>= 1;
	}
	return count;
    }

}
