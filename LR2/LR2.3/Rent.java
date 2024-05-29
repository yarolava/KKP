import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

public class Rent implements Externalizable{

	private static final long serialVersionUID = 1L;
	private Reader reader;
	private Book book;
	private Author author;
	
	
	public Rent() {};
	
	public Rent(Reader reader, Book book, Author author) {
		this.reader=reader;
		this.book=book;
		this.author=author;
	}
	
	
	//getters

	public Reader getReader() {return this.reader;}
	
	public Book getBook() {return this.book;}
	
	public Author getAuthor() {return this.author;}
	
	//setters
	
	public void setReader(Reader reader) {this.reader=reader;}
	
	public void setBook(Book book) {this.book=book;}
	
	public void setAuthor(Author author) {this.author=author;}

	
	@Override
	public String toString() {
		return "\nReader:" +this.getReader().toString()+"\nBook:"+this.getBook().toString()+"\nAuthor:"+this.getAuthor().toString();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(reader);
		out.writeObject(book);
		out.writeObject(author);
		
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.reader=(Reader)in.readObject();
		this.book=(Book)in.readObject();
		this.author=(Author)in.readObject();
		
	}

}
