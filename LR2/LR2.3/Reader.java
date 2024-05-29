import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

public class Reader implements Externalizable{

	private String name;
	private int age;
	Book book;
	
	
	public Reader() {};
	
	public Reader(String name, int age, Book book) {
		this.name=name;
		this.age=age;
		this.book=book;
	};
	
	
	//getters
	
	public String getName() {return this.name;}
	
	public int getAge() {return this.age;}
	
	public Book getBook() {return this.book;}
	
	
	//setters
	
	public void setName(String name) {this.name=name;}
	
	public void setAge(int age) {this.age=age;}
	
	public void setBook(Book book) {this.book=book;}
	
	
	@Override
	public String toString() {
		return "\n  Name: "+this.name+"\n  Age: "+this.age;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name);
		out.writeInt(age);
		out.writeObject(book);
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.name=in.readUTF();
		this.age=in.readInt();
		this.book=(Book)in.readObject();
		
	}
	
	
	
	
	
	
	
}
