import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

public class Book implements Externalizable{

	private String name;
	private int year;
	Author author;
	
	public Book() {};
	
	public Book(String name, int year, Author author) {
		this.name=name;
		this.year=year;
		this.author=author;
	};
	
	
	//getters
	
	public String getName() {return this.name;}
	
	public int getYear() {return this.year;}
	
	public Author getAuthor() {return this.author;}
	
	
	
	//setters
	
	public void setName(String name) {this.name=name;}
	
	public void setYear(int year) {this.year=year;}
	
	public void setAuthor(Author author) {this.author=author;}
	
	
	
	@Override
	public String toString() {
		return "\n  Name: "+this.name+"\n  Year: "+year;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name);
		out.writeInt(year);
		out.writeObject(author);
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.name=in.readUTF();
		this.year=in.readInt();
		this.author=(Author) in.readObject();
		
	}
}
