/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * HW 9
 * Main class
 * Obtains execution time of different algorithms
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter 1, 2, or 3 for the appropriate algorithm: ");
		int algorithm = scan.nextInt();
		System.out.print("Enter 1 for no output. Enter 2 for output. ");
		int output = scan.nextInt();
		System.out.print("Find all the prime numbers less than: ");
		int n = -1;
		
		if (algorithm == 1 && output == 1) { // algorithm 1 without output
			n = scan.nextInt();
			
			long begin = System.currentTimeMillis();
			int number = 2;
			while (number <= n) {
				for (int divisor = 2; divisor <= (int)(Math.sqrt(number)); divisor++) {
					if (number % divisor == 0)
						break;
				}
				number++;
			}
			long end = System.currentTimeMillis();
			System.out.println("Algorithm 1: Execution time for n = " + n + " without output is " + (end-begin) + " milliseconds.");
		}
		else if (algorithm == 1 && output == 2) { // algorithm 1 with output
			n = scan.nextInt();
			
			long begin = System.currentTimeMillis();
			int number = 2;
			while (number <= n) {
				boolean isPrime = true;
				for (int divisor = 2; divisor <= (int)(Math.sqrt(number)); divisor++) {
					if (number % divisor == 0) {
						isPrime = false;
						break;
					}
				}
				if (isPrime)
					System.out.println(number);
				number++;
			}
			long end = System.currentTimeMillis();
			System.out.println("Algorithm 1: Execution time for n = " + n + " with output is " + (end-begin) + " milliseconds.");
		}
		else if (algorithm == 2 && output == 1) { // algorithm 2 without output
			n = scan.nextInt();
			long begin = System.currentTimeMillis();
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			int number = 2;
			int squareRoot = 1;
			while (number <= n) {
				boolean isPrime = true;
				if (squareRoot * squareRoot < number)
					squareRoot++;
				for (int k=0; k<list.size() && list.get(k)<=squareRoot; k++) {
					if (number % list.get(k) == 0)
					{
						isPrime = false;
						break;
					}
				}
				if (isPrime) {
					list.add(number);
				}
				number++;
			}
			long end = System.currentTimeMillis();
			System.out.println("Algorithm 2: Execution time for n = " + n + " without output is " + (end-begin) + " milliseconds.");
			
			
		}
		else if (algorithm == 2 && output == 2) { // algorithm 2 with output
			n = scan.nextInt();
			
			long begin = System.currentTimeMillis();
			ArrayList<Integer> list = new ArrayList<Integer>();
			int number = 2;
			int squareRoot = 1;
			while (number <= n) {
				boolean isPrime = true;
				if (squareRoot * squareRoot < number)
					squareRoot++;
				for (int k=0; k<list.size() && list.get(k)<=squareRoot; k++) {
					if (number % list.get(k) == 0) {
						isPrime = false;
						break;
					}
				}
				if (isPrime) {
					list.add(number);
					System.out.println(number);
				}
				number++;
			}
			long end = System.currentTimeMillis();
			System.out.println("Algorithm 2: Execution time for n = " + n + " with output is " + (end-begin) + " milliseconds.");
		}
		else if (algorithm == 3 && output == 1) { // algorithm 3 without output
			n = scan.nextInt();
			
			long begin = System.currentTimeMillis();
			boolean[] primes = new boolean[n+1];
			for (int i=0; i<primes.length; i++)
				primes[i] = true;
			for (int k=2; k<=n/k; k++) {
				if (primes[k]) {
					for (int i=k; i<=n/k; i++) {
						primes[k*i] = false;
					}
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("Algorithm 3: Execution time for n = " + n + " without output is " + (end-begin) + " milliseconds.");
		}
		else { // algorithm 3 with output
			n = scan.nextInt();
			
			long begin = System.currentTimeMillis();
			boolean[] primes = new boolean[n+1];
			for (int i=0; i<primes.length; i++)
				primes[i] = true;
			for (int k=2; k<=n/k; k++) {
				if (primes[k]) {
					for (int i=k; i<=n/k; i++) {
						primes[k*i] = false;
					}
				}
			}
			for (int i=2; i<primes.length; i++) {
				if(primes[i])
					System.out.println(i);
			}
			long end = System.currentTimeMillis();
			System.out.println("Algorithm 3: Execution time for n = " + n + " with output is " + (end-begin) + " milliseconds.");
		}
		
		scan.close();

	}
}
