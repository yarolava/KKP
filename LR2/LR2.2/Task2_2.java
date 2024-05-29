import java.io.*;
import java.util.Scanner;

public class Task2_2 {
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		String path = "data2.ser";
		File file = new File(path);

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

			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream dout = new DataOutputStream(baos);

				dout.writeUTF(author.getName());
				dout.writeUTF(author.getBirth());

				dout.writeUTF(book.getName());
				dout.writeInt(book.getYear());

				dout.writeUTF(reader.getName());
				dout.writeInt(reader.getAge());

				byte[] barr = baos.toByteArray();

				FileOutputStream fos = new FileOutputStream(path);
				fos.write(barr);
				fos.close();
				System.out.print("\n-OBjects's value were converted to byte array-\n");

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case 2:
			try {
				FileInputStream fis = new FileInputStream(path);
				byte[] nbarr = new byte[(int) (file.length())];
				fis.read(nbarr);
				fis.close();

				ByteArrayInputStream bais = new ByteArrayInputStream(nbarr);
				DataInputStream dis = new DataInputStream(bais);

				Author newAuthor = new Author();
				newAuthor.setName(dis.readUTF());
				newAuthor.setBirth(dis.readUTF());

				Book newBook = new Book();
				newBook.setName(dis.readUTF());
				newBook.setYear(dis.readInt());

				Reader newReader = new Reader();
				newReader.setName(dis.readUTF());
				newReader.setAge(dis.readInt());

				Rent newRent = new Rent(newReader, newBook, newAuthor);

				System.out.print(newRent.toString());

			} catch (IOException e) {
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
