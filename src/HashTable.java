
public class HashTable {

	private class Node {
		String name;
		long population;
		long area;
		Node nextNode;
		public Node(String name, long population, long area) {
			this.name = name;
			this.population = population;
			this.area = area;
		}//end Node constructor
		public void printNode() {
			// print a node’s country name and its population density
		}//end printNode
	}//end Inner Node class
	
}//end HashTable class
