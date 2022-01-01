import java.util.ArrayList;
import java.util.List;

public class Node {
	int id;
	List<Node> connected = new ArrayList<>();
	boolean visited = false;
	
	public Node(int id) {
		this.id = id;
	}
}
