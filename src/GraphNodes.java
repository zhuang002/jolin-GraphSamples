import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class GraphNodes {
	List<Node> nodes = new ArrayList<>();
	int visitCount = 0;
	
	public static GraphNodes load(String fname) throws FileNotFoundException {
		// TODO Auto-generated method stub
		GraphNodes graph = new GraphNodes();
		Scanner sc = new Scanner(new File(fname));
		
		// initialization
		int n = sc.nextInt();
		for (int i=0;i<n;i++) {
			Node node= new Node(i);
			graph.nodes.add(node);
		}
		
		int paths = sc.nextInt();
		for (int i=0;i<paths;i++) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			Node node1 = graph.nodes.get(n1);
			Node node2 = graph.nodes.get(n2);
			node1.connected.add(node2);
			node2.connected.add(node1);
		}
		
		return graph;
	}

	public boolean isConnected() { //use BFS
		// TODO Auto-generated method stub
		// reset status;
		for (Node node:this.nodes) {
			node.visited = false;
		}
		visitCount = 0;
		
		ArrayList<Node> current = new ArrayList();
		current.add(nodes.get(0));
		
		ArrayList<Node> next = new ArrayList<>();
		
		while (!current.isEmpty()) {
			for (Node node:current) {
				node.visited = true;
				visitCount++;
				next.addAll(getUnvisitedNeighbours(node));
			}
			
			current = next;
			next = new ArrayList<>();
		}
		
		
		return visitCount == this.nodes.size();
	}

	private List<Node> getUnvisitedNeighbours(Node node) {
		// TODO Auto-generated method stub
		ArrayList<Node> retList = new ArrayList<>();
		for (Node n:node.connected) {
			if (!n.visited)
				retList.add(n);
		}
		
		return retList;
	}
	
}
