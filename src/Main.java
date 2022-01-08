import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Graph2DArray graph1 = Graph2DArray.load("graph1.dat");
		System.out.println(graph1.isConnected());
		
		GraphNodes graph2 = GraphNodes.load("graph1.dat");
		System.out.println(graph2.isConnected());
		
		/*GraphHash graph3 = GraphHash.load("graph1.dat");
		System.out.println(graph3.isConnected());*/
		
		GraphNodes graph2 = GraphNodes.load("graph2.dat");
		System.out.println("Has ring:"+graph2.hasRing());
		
		graph1 = Graph2DArray.load2("graph3.dat");
		System.out.println("The minimum distance from 1 to 15 is: " + graph1.getMinDistance(1,15));
		
	}

}
