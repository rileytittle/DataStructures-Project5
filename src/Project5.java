import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Project5 {
	public static void main(String[] args) {
		System.out.print("COP3530 Project 5\nHash Tables\nEnter the file name: ");
		Scanner in = new Scanner(System.in);
		String fileName = in.nextLine();
		FileReader fileReader;
		Scanner fileScanner;
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
		
		
	}//end main method
}//end Project5 class
