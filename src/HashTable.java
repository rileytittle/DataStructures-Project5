/**
 * The HashTable class is a hash table implemented with separate chaining to 
 * deal with collisions. The separate chaining is done with locally defined 
 * LinkedList class, which is a singly-linked, double ended linked list. 
 * The HashTable class has multiple methods that allow you to insert, find, delete, 
 * and display information about the Nodes in the hashtable which store information about countries.  
 * 
 * @author Riley Tittle
 * @version 12.9.2023
 */
public class HashTable {
	private LinkedList[] hashArray;
	/**
	 * HashTable is the constructor for the HashTable class. It takes no
	 * arguments. It creates an empty LinkedList in each index of the hashArray.
	 */
	public HashTable(){
		hashArray = new LinkedList[257];
		for(int i = 0; i < 257; i++){
			hashArray[i] = new LinkedList();
		}
	}//end HashTable constructor
	/**
	 * insert method takes in the name, population, and area of a country to 
	 * add it to the hashtable. 
	 * @param country the name of the country being added to the hashtable
	 * @param population the population of the country being added to the hashtable
	 * @param area the area of the country being added to the hashtable
	 */
	public void insert(String country, long population, long area){
		int hashValue = hash(country);
		hashArray[hashValue].insert(new Node(country, population, area));
	}//end insert method
	/**
	 * find method takes in the name of the country being sought for, 
	 * calls the hash method on the name, and then searches the appropriate
	 * index for the country. It returns the index if found, -1 otherwise.
	 * @param country the name of the country being sought for
	 * @return the index of the country being searched for, -1 otherwise
	 */
	public int find(String country) {
		int hashValue = hash(country);
		Node result = hashArray[hashValue].findNode(country);
		if(result == null){
			System.out.println();
			System.out.println(country + " is not in hash table");
			return -1;
		}
		else if(result.name.equals(country)){
			System.out.printf("\n%s is found at index %d with population density of %.4f\n\n", country, hashValue, (double)result.population / (double)result.area);
			return hashValue;
		}
		else{
			System.out.println();
			System.out.println(country + " is not in hash table");
			return -1;
		}
	}//end find method
	/**
	 * delete method takes the name of the country you want to delete from the hashtable,
	 * calls the hash method on it, then searches for and deletes the country from the hashtable
	 * @param country the name of the country to delete from the hashtable
	 */
	public void delete(String country){
		int hashValue = hash(country);
		boolean nodeDeleted = hashArray[hashValue].delete(country);
		if(nodeDeleted){
			System.out.println();
			System.out.println(country + " is deleted from hash table\n");
		}
		else{
			System.out.println();
			System.out.println(country + " is not a country\n");
		}
	}
	/**
	 * display method prints out the information of each index of the hashtable. If
	 * there is no information present at an index, it will print "Empty"
	 */
	public void display(){
		System.out.printf("\n\t%-38s%-18s\n", "Country Name", "Population Density");
		System.out.print("----------------------------------------------------------------\n");
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
		System.out.println();
	}//end display method
	/**
	 * printEmptyAndCollidedCells prints out a statement telling how many empty and collided cells are 
	 * currently in the hash table
	 */
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
		System.out.println();
		System.out.printf("There are %d empty cells and %d colllisions in the hash table\n", emptyCells, collidedCells);
		System.out.println();
	}
	/**
	 * hash method takes the name of the country you want to hash, and creates a hash
	 * value based on the name. It sums up the Unicode values of each character in the name then 
	 * modulus's the sum by 257. The method returns the hash value as an integer.
	 * @param countryName the name of the country to hash
	 * @return the hash value of the country you are hashing
	 */
	private int hash(String countryName){
		String[] nameArray = countryName.split("");
		long hashValue = 0;
		for(int i = 0; i < nameArray.length; i++){
			hashValue += (int) nameArray[i].charAt(0);
		}
		hashValue = hashValue % 257;
		return (int) hashValue;
	}
	/**
	 * The Node class is an inner class that is used to store information about individual countries.
	 * It is used as the Link in the locally defined LinkedList class as well. 
	 * 
	 * @author Riley Tittle
	 * @version 12.9.2023
	 */
	private class Node {
		String name;
		long population;
		long area;
		Node nextNode;
		/**
		 * Node is the constructor for the Node class. It takes the name, population, and area of
		 * the country you are storing in the Node.
		 * @param name the name of the country you are storing 
		 * @param population the population of the country you are storing
		 * @param area the area of the country you are storing
		 */
		public Node(String name, long population, long area) {
			this.name = name;
			this.population = population;
			this.area = area;
			this.nextNode = null;
		}//end Node constructor
		/**
		 * printNode method prints out the population density and name of the country in the Node
		 */
		public void printNode() {
			// print a node’s country name and its population density = total pop / total area
			System.out.printf("\t%-38s%-18.4f", this.name, ((double)this.population / (double)this.area));
			System.out.println();
		}//end printNode
	}//end Inner Node class
	/**
	 * LinkedList class is a user-defined, singly linked, doubly-ended linked list used
	 * to create the separate chaining in the hash table. The Linked list has methods that allow you to 
	 * insert, find, delete, and print Nodes in the LinkedList.
	 * @author Riley Tittle
	 * @version 12.9.2023
	 */
	private class LinkedList {
		public Node first;
		public Node last;
		/**
		 * LinkedList is the constructor for the LinkedList class
		 */
		public LinkedList(){
			first = null;
			last = null;
		}//end LinkedList constructor
		/**
		 * insert method inserts a new Node into the linked list at the end of the list.
		 * @param newNode the node being inserted into the linked list
		 */
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
		/**
		 * findNode method takes in the name of the country being stored in the Node you
		 * are trying to find. It performs a linear search to find the desired Node.
		 * @param searchName the name of the country being searched for
		 * @return if found, the Node you are searching for, otherwise null.
		 */
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
		/**
		 * delete method takes in the name of the country being stored in the Node you
		 * want to delete, searches for the node, and then if it is found, deletes it from the LinkedList.
		 * @param searchName the name of the country stored in the node you want to delete.
		 * @return true if the node is deleted, false otherwise.
		 */
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
		/**
		 * printList prints out the LinkedList from first to last.
		 */
		public void printList(){
			Node printNode = first;
			while(printNode != null){
				printNode.printNode();
				printNode = printNode.nextNode;
			}
		}
	}//end Inner LinkedList class
}//end HashTable class