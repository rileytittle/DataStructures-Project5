public class HashTable {
	private LinkedList[] hashArray;
	public HashTable(){
		hashArray = new LinkedList[257];
		for(int i = 0; i < 257; i++){
			hashArray[i] = new LinkedList();
		}
	}//end HashTable constructor
	public void insert(String country, long population, long area){
		String[] nameArray = country.split("");
		long hashValue = 0;
		for(int i = 0; i < nameArray.length; i++){
			hashValue += (int) nameArray[i].charAt(0);
		}
		hashValue = hashValue % 257;
		hashValue = (int) hashValue;
		hashArray[(int)hashValue].insert(new Node(country, population, area));
	}
	private class Node {
		String name;
		long population;
		long area;
		Node nextNode;
		public Node(String name, long population, long area) {
			this.name = name;
			this.population = population;
			this.area = area;
			this.nextNode = null;
		}//end Node constructor
		public void printNode() {
			// print a node’s country name and its population density = total pop / total area
			System.out.printf("%-38s%-18.4f", this.name, (this.population / this.area));
			System.out.println();
		}//end printNode
	}//end Inner Node class
	private class LinkedList {
		public Node first;
		public Node last;
		public LinkedList(){
			first = null;
			last = null;
		}//end LinkedList constructor
		public void insert(Node newNode){
			if(first == null){
				first = newNode;
				last = newNode;
			}
			last.nextNode = newNode;
			last = newNode;
		}//end insert method
		public Node findNode(String searchName) {
			Node current = first;
			do {
				if(current.name.equals(searchName)){
					return current;
				}
				else{
					current = current.nextNode;
				}
			} while (current != null);
			return current;
		}
		public void delete(String searchName) {
			Node nodeToDelete = findNode(searchName);
			if(nodeToDelete == first){
				first = first.nextNode;
			}
			else if(nodeToDelete == last){
				Node secondToLastNode = first;
				while(secondToLastNode.nextNode != last){
					secondToLastNode = secondToLastNode.nextNode;
				} 
				secondToLastNode.nextNode = null;
			}
			else{
				Node nodeBeforeDeleteNode = first;
				while(nodeBeforeDeleteNode.nextNode != nodeToDelete){
					nodeBeforeDeleteNode = nodeBeforeDeleteNode.nextNode;
				}
				nodeBeforeDeleteNode.nextNode = nodeToDelete.nextNode;
			}
		}
	}//end Inner LinkedList class
}//end HashTable class