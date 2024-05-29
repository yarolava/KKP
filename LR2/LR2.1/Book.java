import java.io.Serializable;

public class Book implements Serializable{

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
}
