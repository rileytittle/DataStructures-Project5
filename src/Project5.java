import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
/**
 * COP 3530: Project 5 - Hash Tables
 * <p>
 * Project5 class is the main class for the project 5 program. The
 * class reads in user input from the console to operate the program. The
 * user supplies the file to be read in, and then chooses menu options to
 * perform certain manipulations on the data being stored. 
 * 
 * @author Riley Tittle
 * @version 12.9.2023
 */
public class Project5 {
	public static void main(String[] args) {
		System.out.print("COP3530 Project 5\nHash Tables\nEnter the file name: ");
		Scanner in = new Scanner(System.in);
		String fileName = in.nextLine();
		FileReader fileReader = null;
		Scanner fileScanner = null;
		boolean fileNotFound = false;
		do {
			try {
				fileReader = new FileReader(fileName);
				fileScanner = new Scanner(fileReader);
				fileNotFound = false;
			} catch (FileNotFoundException e) {
				System.out.print("File not found. Try again: ");
				fileName = in.nextLine();
				fileNotFound = true;
			}//end catch
		} while (fileNotFound);//end do while
		HashTable theHashTable = new HashTable();
		fileScanner.nextLine();//discard the headers
		while(fileScanner.hasNextLine()){
			String currentLine = fileScanner.nextLine();
			String[] lineItems = currentLine.split(",");
			String countryName = lineItems[0];
			long countryPop = Long.parseLong(lineItems[2]);
			long countryArea = Long.parseLong(lineItems[4]);
			theHashTable.insert(countryName, countryPop, countryArea);
		}
		System.out.println("\nThere were 128 country records read into the hash table.\n");
		
		boolean continueProgram = true;
		do {
			System.out.println("1) Print hash table");
			System.out.println("2) Delete a country of a given name");
			System.out.println("3) Insert a country of its name, population, and area");
			System.out.println("4) Search and print a country and its population density for a given name");
			System.out.println("5) Print numbers of empty cells and collided cells");
			System.out.println("6) Exit");
			System.out.print("Enter your choice: ");
			String menuChoice = "";
			boolean invalidChoice = false;
			do {
				try {
					menuChoice = in.nextLine();
					if(Integer.parseInt(menuChoice) > 6 || Integer.parseInt(menuChoice) < 1) {
						System.out.print("Invalid choice, enter 1-6: ");
						invalidChoice = true;
					}
					else {
						invalidChoice = false;
					}
				}
				catch(NumberFormatException e) {
					System.out.print("Invalid Choice, enter 1-6: ");
					invalidChoice = true;
				}
			}while(invalidChoice);
			
			switch(Integer.parseInt(menuChoice)){
			case 1:
				theHashTable.display();
				break;
			case 2:
				System.out.print("Enter country name: ");
				String countryToDelete = in.nextLine();
				theHashTable.delete(countryToDelete);
				break;
			case 3:
				String nameToInsert = "";
				long popToInsert = 0;
				long areaToInsert = 0;
				String input = "";
				System.out.print("Enter country name: ");
				nameToInsert = in.nextLine();
				System.out.print("Enter country population: ");
				boolean invalidInput = false;
				do {
					input = in.nextLine();
					try {
						popToInsert = Long.parseLong(input);
						invalidInput = false;
					} catch (NumberFormatException e) {
						System.out.print("Invalid population. Enter a number: ");
						invalidInput = true;
					}
				}while(invalidInput);
				System.out.print("Enter country area: ");
				do {
					input = in.nextLine();
					try {
						areaToInsert = Long.parseLong(input);
						invalidInput = false;
					} catch (NumberFormatException e) {
						System.out.print("Invalid area. Enter a number: ");
						invalidInput = true;
					}
				}while(invalidInput);
				theHashTable.insert(nameToInsert, popToInsert, areaToInsert);
				System.out.println();
				System.out.println(nameToInsert + " is inserted to hash table\n");
				break;
			case 4:
				System.out.print("Enter country name: ");
				String countryToFind = in.nextLine();
				theHashTable.find(countryToFind);
				break;
			case 5:
				theHashTable.printEmptyAndCollidedCells();
				break;
			case 6:
				continueProgram = false;
				break;
			}//end switch
		}while(continueProgram);
		System.out.println("Have a nice day!");
		in.close();
	}//end main method
}//end Project5 class
