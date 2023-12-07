public class HashTable {
	private LinkedList[] hashArray;
	public HashTable(){
		hashArray = new LinkedList[257];
		for(int i = 0; i < 257; i++){
			hashArray[i] = new LinkedList();
		}
	}//end HashTable constructor
	public void insert(String country, long population, long area){
		int hashValue = hash(country);
		hashArray[hashValue].insert(new Node(country, population, area));
	}//end insert method
	public int find(String country) {
		int hashValue = hash(country);
		Node result = hashArray[hashValue].findNode(country);
		if(result == null){
			System.out.println(country + " is not in hash table");
			return -1;
		}
		else if(result.name.equals(country)){
			System.out.println(country + " is found at index " + hashValue + " with population density of " + result.population / result.area);
			return hashValue;
		}
		else{
			System.out.println(country + " is not in hash table");
			return -1;
		}
	}//end find method
	public void delete(String country){
		int hashValue = hash(country);
		boolean nodeDeleted = hashArray[hashValue].delete(country);
		if(nodeDeleted){
			System.out.println(country + " is deleted from hash table");
		}
		else{
			System.out.println(country + " is not a country");
		}
	}
	public void display(){
		for(int i = 0; i < hashArray.length; i++){
			if(hashArray[i].first == null){
				System.out.print(i + ".\tEmpty");
				System.out.println();
			}
			else{
				System.out.print(i + ".");
				hashArray[i].printList();
			}
		}
	}
	public void printEmptyAndCollidedCells(){
		int emptyCells = 0;
		int collidedCells = 0;
		for(int i = 0; i < hashArray.length; i++){
			if(hashArray[i].first == null){
				emptyCells++;
			}
			else if(hashArray[i].first != hashArray[i].last){
				collidedCells++;
			}
		}
		System.out.printf("There are %d empty cells and %d colllisions in the hash table\n", emptyCells, collidedCells);
	}
	private int hash(String countryName){
		String[] nameArray = countryName.split("");
		long hashValue = 0;
		for(int i = 0; i < nameArray.length; i++){
			hashValue += (int) nameArray[i].charAt(0);
		}
		hashValue = hashValue % 257;
		return (int) hashValue;
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
			System.out.printf("\t%-38s%-18d", this.name, (this.population / this.area));
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
			else{
			last.nextNode = newNode;
			last = newNode;
			}
		}//end insert method
		public Node findNode(String searchName) {
			Node current = first;
			do {
				if(current == null){
					return current;
				}
				else if(current.name.equals(searchName)){
					return current;
				}
				else{
					current = current.nextNode;
				}
			} while (current != null);
			return current;
		}
		public boolean delete(String searchName) {
			boolean nodeDeleted = false;
			Node nodeToDelete = findNode(searchName);
			if(nodeToDelete == null){
				return nodeDeleted;
			}
			else if(nodeToDelete == first){
				first = first.nextNode;
				nodeDeleted = true;
				return nodeDeleted;
			}
			else if(nodeToDelete == last){
				Node secondToLastNode = first;
				while(secondToLastNode.nextNode != last){
					secondToLastNode = secondToLastNode.nextNode;
				} 
				secondToLastNode.nextNode = null;
				nodeDeleted = true;
				return nodeDeleted;
			}
			else{
				Node nodeBeforeDeleteNode = first;
				while(nodeBeforeDeleteNode.nextNode != nodeToDelete){
					nodeBeforeDeleteNode = nodeBeforeDeleteNode.nextNode;
				}
				nodeBeforeDeleteNode.nextNode = nodeToDelete.nextNode;
				nodeDeleted = true;
				return nodeDeleted;
			}
		}
		public void printList(){
			Node printNode = first;
			while(printNode != null){
				printNode.printNode();
				printNode = printNode.nextNode;
			}
		}
	}//end Inner LinkedList class
}//end HashTable class