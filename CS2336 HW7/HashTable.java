/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * Homework 7
 * HashTable class
 */

public class HashTable {
	// Private Variables
	private int[] table;
	private int numElements;
	private final double LOAD_FACTOR = 0.5;
	
	// Constructors
	public HashTable() {
		table = new int[9];
		numElements = 0;
	}
	public HashTable(int size) {
		table = new int[size];
		numElements = 0;
	}
	
	// print out the hashtable
	public void printHashTable() {
		System.out.println("HashTable Contents: " + getString() + "\n");
	}
	// return the contents of the hashtable in String form
	private String getString() {
		String answer = "[";
		for (int i=0; i<table.length; i++) {
			if (i==0)
				answer += table[i];
			else
				answer += "," + table[i];
		}
		answer += "]";
		return answer;
	}
	
	// add value into the hashtable
	public void addValue(int value) {
		if ((double)numElements/table.length > LOAD_FACTOR) // check if rehashing is needed
			rehash();
		int index = value % table.length; // calculate index in hashtable
		System.out.println("Value [" + value + "] % HashTable Length [" + table.length + "] = Index [" + index + "]");
		if (table[index] == 0) { // check if spot in hashtable is open
			System.out.println("Adding value [" + value + "] at index [" + index + "]");
			table[index] = value;
			numElements++;
		}
		else { // spot in hashtable is taken - use quadratic probing
			System.out.println("Index [" + index + "] already has a value. Using quadratic probing to find a new index.");
			int j=0;
			while (true) {
				int newIndex = index + (j*j);
				if (table[newIndex] == 0) { // check if spot in hashtable is open
					System.out.println("j = [" + j + "], New Index = [" + newIndex + "]");
					System.out.println("Adding value [" + value + "] at index [" + newIndex + "]");
					table[newIndex] = value;
					numElements++;
					break;
				}
				j++;
				if (j > table.length) {
					System.out.println("Could not find a new index for value [" + value + "] - value was not hashed");
					break;
				}
			}
		}
	}
	
	// rehash the hashtable if the load factor is exceeded
	private void rehash() {
		numElements = 0;
		int[] newTable = new int[table.length*2];
		for (int i=0; i<table.length; i++) {
			if (table[i] != 0) { // for every integer in the old table, find a new index and add into new hashtable
				System.out.println("Rehashing index " + i + ": " + table[i]);
				int index = table[i] % newTable.length;
				System.out.println("Value [" + table[i] + "] % HashTable Length [" + newTable.length + "] = Index [" + index + "]");
				if (newTable[index] == 0) { // spot is open, add to new hashtable
					System.out.println("Adding value [" + table[i] + "] at index [" + index + "]\n");
					newTable[index] = table[i];
					numElements++;
				}
				else { // spot is taken, use quadratic probing
					System.out.println("Index [" + index + "] already has a value. Using quadratic probing to find a new index.");
					int j = 0;
					while (true) {
						int newIndex = index + (j*j);
						if (newTable[newIndex] == 0) { // check if spot in hashtable is open
							System.out.println("j = [" + j + "], New Index = [" + newIndex + "]");
							System.out.println("Adding value [" + table[i] + "] at index [" + newIndex + "]\n");
							newTable[newIndex] = table[i];
							numElements++;
							break;
						}
						j++;
						if (j > newTable.length) {
							System.out.println("Could not find a new index for value [" + table[i] + "] - value was not hashed");
							break;
						}
					}
				}
			}
		}
		table = newTable;
		System.out.println("Rehashed table: " + getString() + "\n");
	}
}
