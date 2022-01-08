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

	public boolean hasRing() {
		// TODO Auto-generated method stub
		for (Node node:this.nodes) {
			node.visited = false;
		}
		
		Node start = nodes.get(0);
		
		return checkRing(null, start);
		
		
	}

	private boolean checkRing(Node parent, Node node) {
		// TODO Auto-generated method stub
		node.visited = true;
		
		List<Node> connectedNodes = getConnectedNodesExceptParent(parent, node);
		if (connectedNodes == null) return true;
		
		for (Node nd: connectedNodes) {
			boolean hasRing = checkRing(node, nd);
			if (hasRing) {
				return true;
			}
		}
		
		return false;
		
	}

	private List<Node> getConnectedNodesExceptParent(Node parent, Node node) {
		// TODO Auto-generated method stub
		List<Node> retNodes = new ArrayList<>();
		for (Node subNode:node.connected) {
			if (parent == subNode) continue;
			if (subNode.visited) return null;
			retNodes.add(subNode);
		}
		
		return retNodes;
	}

	public String getMinDistance(int i, int j) {
		// TODO Auto-generated method stub
		Node start = this.nodes.get(i);
		Node target = this.nodes.get(j);
		
		
		return null;
	}
	
}
