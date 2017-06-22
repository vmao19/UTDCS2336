/*
 * Victor Mao (vtm160030)
 * Austin Prochaska (ajp161530)
 * CS 2336.003
 * In class assignment 7
 * Checks if graph is bipartite
 */

import java.util.Scanner;
import java.io.*;

public class InClassSevenMain {

	public static void main(String[] args) throws FileNotFoundException {
		// read in graph1.txt
		Scanner file1 = new Scanner(new File("graph1.txt"));
		
		int num1 = Integer.parseInt(file1.nextLine()); // number of vertices in graph1
		int[][] matrix1 = new int[num1][num1]; // adjacency matrix of graph1
		
		// populate adjacency matrix
		for (int i=0; i<num1; i++) {
			String[] vertices1 = file1.nextLine().split(" ");
			int x=-1, y=-1;
			for (int j=0; j<vertices1.length; j++) {
				if (j==0)
					x = Integer.parseInt(vertices1[0]);
				else {
					y = Integer.parseInt(vertices1[j]);
					matrix1[x-1][y-1] = 1; // 1 if there is an edge between vertices x and y
				}
			}
		}
		file1.close();
		
		// initialize array that marks colors of nodes
		int[] colors1 = new int[num1];
		for (int i=0; i<colors1.length; i++)
			colors1[i] = -1; // set all nodes to -1 (colorless)
		
		Graph graph1 = new Graph(colors1, matrix1);
		if (graph1.isBipartite())
			System.out.println("graph1 is a bipartite graph.");
		else
			System.out.println("graph1 is NOT a bipartite graph.");
		
		
		// read in graph2.txt
				Scanner file2 = new Scanner(new File("graph2.txt"));
				
				int num2 = Integer.parseInt(file2.nextLine()); // number of vertices in graph2
				int[][] matrix2 = new int[num2][num2]; // adjacency matrix for graph2
				
				// populate adjacency matrix
				for (int i=0; i<num2; i++) {
					String[] vertices2 = file2.nextLine().split(" ");
					int x=-1, y=-1;
					for (int j=0; j<vertices2.length; j++) {
						if (j==0)
							x = Integer.parseInt(vertices2[0]);
						else {
							y = Integer.parseInt(vertices2[j]);
							matrix2[x-1][y-1] = 1; // 1 if there is an edge between vertices x and y
						}
					}
				}
				file2.close();
				
				// initialize array that marks colors of nodes
				int[] colors2 = new int[num2];
				for (int i=0; i<colors2.length; i++)
					colors2[i] = -1; // set all nodes to -1 (colorless)
				
				Graph graph2 = new Graph(colors2, matrix2);
				if (graph2.isBipartite())
					System.out.println("graph2 is a bipartite graph.");
				else
					System.out.println("graph2 is NOT a bipartite graph.");
	}
}
