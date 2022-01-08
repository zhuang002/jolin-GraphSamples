import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph2DArray {
	private  int[][] table = null;
	private boolean[] visited = null;
	private int visitedCount = 0;
	private boolean[] checked =null;
	private int[] minLength=null;
	
	public static Graph2DArray load(String fname) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new File(fname));
		int n = sc.nextInt();
		
		// initialize the table;
		Graph2DArray graph = new Graph2DArray();
		graph.table = new int[n][n];
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				graph.table[i][j]=0;
			}
		}
		
		int paths = sc.nextInt();
		for (int i=0;i<paths;i++) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			graph.table[n1][n2] = 1;
			graph.table[n2][n1] = 1;
		}
		sc.close();
		return graph;
	}

	public boolean isConnected() { // DFS
		// TODO Auto-generated method stub
		int start = 0;
		visited = new boolean[table.length];
		for (int i=0;i<visited.length;i++) {
			visited[i] = false;
		}
		visitedCount = 0;
		
		visit(start);
		
		return visitedCount == table.length;
	}

	private void visit(int node) {
		// TODO Auto-generated method stub
		visited[node] = true;
		
		List<Integer> unvisitedNeighbours = getUnvisitedNeighbours(node);
		
		for (Integer iNode:unvisitedNeighbours) {
			if (!visited[iNode]) {
				visit(iNode);
			}
		}
	}

	private List<Integer> getUnvisitedNeighbours(int node) {
		// TODO Auto-generated method stub
		ArrayList<Integer> retList = new ArrayList<>();
		for (int i=0;i<this.table.length;i++) {
			if (i==node) continue;
			if (this.table[node][i]!=0 && !visited[i]) {
				retList.add(i);
			}
		}
		return retList;
	}

	public static Graph2DArray load2(String string) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new File(fname));
		int n = sc.nextInt();
		
		// initialize the table;
		Graph2DArray graph = new Graph2DArray();
		graph.table = new int[n][n];
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				graph.table[i][j]=0;
			}
		}
		
		int paths = sc.nextInt();
		for (int i=0;i<paths;i++) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			int length = sc.nextInt();
			graph.table[n1][n2] = length;
			graph.table[n2][n1] = length;
		}
		sc.close();
		return graph;
	}

	public int getMinDistance(int start, int target) {
		// TODO Auto-generated method stub
		checked = new boolean[table.length];
		for (int i=0;i<table.length;i++) {
			checked[i] = false;
		}
		
		minLength = new int[table.length];
		for (int i=0;i<table.length;i++) {
			minLength[i] = -1;
		}
		
		minLength[start] = 0;
		
		
		// find smallest length node
		int currentNode = findSmallestUncheckedNode();
		
		while (currentNode>=0) {
			List<Integer> neighbours = findUncheckedNeighbours(currentNode);
			for (int neighbour:neighbours) {
				int length = minLength[currentNode]+table[currentNode][neighbour];
				if (minLength[neighbour] == -1 || minLength[neighbour]<length) {
					minLength[neighbour] = length;
				}
			}
			checked[currentNode] = true;
			currentNode = findSmallestUncheckedNode();
		}
		
		return minLength[target];
	}

	private List<Integer> findUncheckedNeighbours(int currentNode) {
		List<Integer> list = new ArrayList<>();
		for (int i=0;i<table.length;i++) {
			if (i!=currentNode && table[currentNode][i]>0 && !checked[i]) {
				list.add(i);
			}
		}
		return list;
	}

	private int findSmallestUncheckedNode() {
		// TODO Auto-generated method stub
		int min = Integer.MAX_VALUE;
		int minNode = -1;
		
		for (int node=0;node<table.length;node++) {
			if (minLength[node]!=-1 && !checked[node]) {
				if (minLength[node]<min) {
					min = minLength[node];
					minNode = node;
				}
			}
		}
		
		return minNode;
	}
	
}
