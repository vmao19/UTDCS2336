/*
 * Victor Mao (vtm160030)
 * CS 2336.003
 * HW 8
 * Main class
 * Checks if all vertices in the file are connected with a BFS traversal
 */

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("vertices.txt"));
		
		int num = Integer.parseInt(scan.nextLine()); // number of vertices
		int[][] matrix = new int[num][num]; // adjacency matrix
		
		// populate adjacency matrix
		for (int i=0; i<num; i++) {
			String[] vertices = scan.nextLine().split(" ");
			int x=-1, y=-1;
			for (int j=0; j<vertices.length; j++) {
				if (j==0)
					x = Integer.parseInt(vertices[0]);
				else {
					y = Integer.parseInt(vertices[j]);
					matrix[x][y] = 1; // 1 if there is an edge between vertices x and y
				}
			}
		}
		scan.close();
		
		/*
		// print adjacency matrix
		for (int i=0; i<matrix.length; i++) {
			System.out.print(matrix[i][0]);
			for (int j=1; j<matrix[i].length; j++) {
				System.out.print(", " + matrix[i][j]);
			}
			System.out.println();
		}
		*/
		
		// initialize visited array
		boolean[] visited = new boolean[num];
		ArrayList<Integer> bfs = new ArrayList<Integer>();
		for (int i=0; i<matrix[0].length; i++) {
			if (matrix[0][i] == 1) {
				bfs.add(i);
				visited[i] = true;
			}
		}
		
		// bfs search through adjacency matrix
		while(!bfs.isEmpty()) {
			int x = bfs.remove(0);
			for (int y=0; y<matrix[x].length; y++) {
				if (matrix[x][y] == 1 && visited[y] == false) {
					bfs.add(y);
					visited[y] = true;
				}
			}
		}		
		
		// check if all nodes are visited
		boolean allVisited = true;
		for (int i=0; i<visited.length; i++) {
			if (visited[i] == false) {
				allVisited = false;
				break;
			}
		}
		
		// print results
		if (allVisited)
			System.out.println("All the vertices in the graph are connected.");
		else
			System.out.println("All the vertices in the graph are NOT connected.");
	}
}