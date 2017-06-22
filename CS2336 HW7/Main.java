/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Homework 7
 * Main class testing the HashTable class
 */

public class Main {
	public static void main(String[] args) {
		HashTable ht = new HashTable(); // create hashtable
		ht.printHashTable();
		
		// populate hashtable
		ht.addValue(11);
		ht.printHashTable();
		ht.addValue(22);
		ht.printHashTable();
		ht.addValue(57);
		ht.printHashTable();
		ht.addValue(29);
		ht.printHashTable();
		ht.addValue(69);
		ht.printHashTable();
		ht.addValue(73);
		ht.printHashTable();
		ht.addValue(174);
		ht.printHashTable();
		ht.addValue(197);
		ht.printHashTable();
		ht.addValue(942);
		ht.printHashTable();
		ht.addValue(582);
		ht.printHashTable();
		ht.addValue(763);
		ht.printHashTable();
		ht.addValue(375);
		ht.printHashTable();
	}
}