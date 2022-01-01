import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph2DArray {
	private  int[][] table = null;
	private boolean[] visited = null;
	private int visitedCount = 0;
	
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
	
}
