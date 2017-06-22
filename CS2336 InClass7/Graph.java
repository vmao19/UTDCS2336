/*
 * Victor Mao (vtm160030)
 * Austin Prochaska (ajp161530)
 * CS 2336.003
 * In class assignment 7
 * Graph object class
 */

import java.util.ArrayList;

public class Graph {
	// private variables
	private int[] colors; // stores vertices
	private int[][] adjacency; // stores edges
	
	// constructor
	public Graph(int[] c, int[][] a) {
		colors = c;
		adjacency = a;
	}
	
	// returns if graph is bipartite
	public boolean isBipartite() {
		ArrayList<Integer> bfs = new ArrayList<Integer>();
		bfs.add(0); // add starting vertex into queue
		colors[0] = 1; // mark vertex color1
		while (!bfs.isEmpty()) {
			int currentVertex = bfs.remove(0); // dequeue node
			int currentColor = colors[currentVertex]; // color of current node
			for (int i=0; i<adjacency.length; i++) { // for each neighbor
				if (adjacency[currentVertex][i] == 1) {
					if (colors[i] == -1) {	// if neighbor colorless
						if (currentColor == 1) { 
							colors[i] = 2; // mark color of neighbor opposite color of current node
							bfs.add(i); // add neighbor to queue
						}
						else { // currentColor == 2
							colors[i] = 1; // mark color of neighbor opposite color of current node
							bfs.add(i);	// add neighbor to queue
						}
					}
					else if (colors[i] == currentColor) // If neighbor colored and neighbor color == current node color 
						return false; // graph is not bipartite and return false
				}
			}
		}
		return true; // else if loop finishes then the graph is bipartite, return true
	}
}
