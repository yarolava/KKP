import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Externalizable;

public class Author implements Externalizable{
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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name);
		out.writeUTF(birth);
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.name=in.readUTF();
		this.birth=in.readUTF();
		
	}
}
