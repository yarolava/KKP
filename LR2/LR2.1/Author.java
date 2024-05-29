import java.io.Serializable;

public class Author implements Serializable{
	private String name;
	private String birth;
	
	
	public Author() {};
	
	public Author(String name, String birth) {
		this.name=name;
		this.birth=birth;
	};
	
	
	//getters
	
	public String getName() {return this.name;}
	
	public String getBirth() {return this.birth;}
	
	
	//setters
	
	public void setName(String name) {this.name=name;}
	
	public void setBirth(String birth) {this.birth=birth;}
	
	
	@Override
	public String toString() {
		return "\n  Name: "+this.name+"\n  Birth: "+this.birth;
	}
}
