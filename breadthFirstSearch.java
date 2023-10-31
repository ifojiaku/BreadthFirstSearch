package Lab12;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class breadthFirstSearch {
	public static void main(String [] args) {
		//graph is created with the number of vertices defined
		Graph g = new Graph (6);
		Queue<Integer> list = new LinkedList<>();
		HashMap<Graph, Integer> hash = new HashMap<Graph, Integer>();
		//the addEdge function takes the index for current node, and index for the node it points to respectively
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(4, 5);
		g.addEdge(1, 3);
		g.addEdge(3, 5);
		//checks to see if a root exists before adding it to the queue to start the depth first search
		if(g.adj[0].peek() != null) {
			list.add(g.adj[0].get(0));
		}	
		bfs(g, hash, 0, list);
	}
	
	
	public static void bfs(Graph g, HashMap<Graph, Integer> hash, int index, Queue<Integer> list) {
		//Exits out the method if the queue is empty, or if there is a duplicate value read
		if(list.peek() == null || hash.containsValue(list.peek())) {
			return;
		}
		int num = list.peek();
		//next value in queue is added to hash map to prevent repeats
		hash.put(g, num);
		System.out.print(list.poll() + " ");
		//iterates for number of vertices that the current vertex points to. The loop mainly enqueues new vertices
		for(int i = 0; i < g.adj[index].size(); i++) {
			//will add the next vertices to the queue if they aren't in the hashmap
			if(!(hash.containsValue(g.adj[index].get(i)))) {
				list.add(g.adj[index].get(i));
			}
		}
		
		if(list.peek() != null) {
			//recursively calls the method for the next vertex in the queue
			bfs(g, hash, list.peek(), list);
		}
	}
	
	static class Graph{
		int V;
		LinkedList<Integer>[] adj;
		Graph(int V){
			this.V = V;
			adj = new LinkedList[V];
			for (int i = 0; i < adj.length;i++) {
				adj[i] = new LinkedList<Integer>();
			}
		}
		void addEdge(int v, int w) {
			adj[v].add(w);
		}
	}
	//Test Case 1: If the graph is empty, then no value is output to show there are no vertices
	//Test Case 2: If the graph loops, then code will ignore traversing indefinitely as ignores vertices added to the hashmap
	
}
