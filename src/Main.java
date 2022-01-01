
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph2DArray graph1 = Graph2DArray.load("graph1.dat");
		System.out.println(graph1.isConnected());
		
		GraphNodes graph2 = GraphNodes.load("graph1.dat");
		System.out.println(graph2.isConnected());
		
		GraphHash graph3 = GraphHash.load("graph1.dat");
		System.out.println(graph3.isConnected());
		
	}

}
