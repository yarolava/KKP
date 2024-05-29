import java.io.*;
import java.util.Scanner;

public class Task2_1 {
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		String path = "data.ser";

		Author author = new Author("Steven King", "02-05-1968");
		Book book = new Book("SomeBook", 1999, author);
		Reader reader = new Reader("Tom Hammil", 23, book);
		Rent rent = new Rent(reader, book, author);
		
		while(true) {
		System.out.print("\n1 Serialize\n2 Deserialize\n0 Quit\n");
		System.out.print("Choose the action: ");
		int mbar = in.nextInt();
		switch (mbar) {
		case 1:
			try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(path))) {
				ous.writeObject(rent);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.print("\nSome IOexception\n");
			}
			System.out.print("\n\n-Objects were serialized-\n");
			break;

		case 2:
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
				Rent newRent;
				newRent = (Rent) ois.readObject();
				System.out.print("\nObject was deserialized\n");
				System.out.print(newRent.toString());
			} catch (IOException | ClassNotFoundException n) {
				System.out.print("\nSome IOexception\n");
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
