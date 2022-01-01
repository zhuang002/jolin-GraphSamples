import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GraphHash {
	private HashMap<Integer, List<Integer>> dic = new HashMap<>();
	private boolean[] visited = null;
	private int visitedCount = 0;

	public static GraphHash load(String fname) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		GraphHash graph = new GraphHash();
		
		Scanner sc = new Scanner(new File(fname));
		
		// initialization
		int n = sc.nextInt();
		for (int i=0;i<n;i++) {
			graph.dic.put(i, new ArrayList());
		}
		
		
		int paths = sc.nextInt();
		for (int i=0;i<paths;i++) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			graph.dic.get(n1).add(n2);
			graph.dic.get(n2).add(n1);
		}
		return graph;
	}

	public boolean isConnected() {
		// TODO Auto-generated method stub
		int start = 0;
		
		visited = new boolean[dic.size()];
		for(int i=0;i<dic.size();i++) {
			visited[i] = false;
		}
		
		visit(start);
		
		return visitedCount == dic.size();
	}

	private void visit(int point) {
		// TODO Auto-generated method stub
		visited[point] = true;
		visitedCount++;
		ArrayList<Integer> unvisitedNeighbour = getUnvisitedNeighbour(point);
		for(Integer i:unvisitedNeighbour) {
			visit(i);
		}
	}

	private ArrayList<Integer> getUnvisitedNeighbour(int point) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<>();
		List<Integer> l = dic.get(point);
		for(int i=0;i<l.size();i++) {
			if(visited[l.get(i)] == false) {
				list.add(i);
			}
		}
		
		return list;
	}
	
	
}
