import java.io.*;
import java.util.Scanner;

public class Task2_3 {
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		String path = "data.ser";

		Author author = new Author("Steven King", "02-05-1968");
		Book book = new Book("SomeBook", 1999, author);
		Reader reader = new Reader("Tom Hammil", 23, book);
		Rent rent = new Rent(reader, book, author);


		while(true) {
		System.out.print("\n1 Externalize\n2 Deexternalize\n0 Quit\n");
		System.out.print("Choose the action: ");
		int mbar = in.nextInt();
		switch (mbar) {
		case 1:
			try {
				FileOutputStream fos = new FileOutputStream(path);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				rent.writeExternal(oos);
				
				oos.flush();
				oos.close();
				fos.close();
				System.out.print("\nObjects were externalized\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		case 2:
		
			try {
				FileInputStream fis = new FileInputStream(path);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Rent newRent = new Rent();
				newRent.readExternal(ois);
				
				ois.close();
				fis.close();
				System.out.print(newRent.toString());
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		

			break;
		case 0:
			System.out.print("\n---You quit from program---\n");
			return;
		default:
			System.out.print("\nYou entered wrong option\n");
			}
		}

	}
}
