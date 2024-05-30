package factorial.client;

import java.io.Serializable;
import java.math.BigInteger;

import factorial.interfaces.Executable;

public class Factorial implements  Serializable, Executable {

	private static final long serialVersionUID = -1L;
	private int number;
	
	public Factorial(int number) {
		this.number=(number);
	}

	public Object execute() {
	 return  factorial(number);
	}

	public static BigInteger factorial(int n) {
		BigInteger res = BigInteger.valueOf(1);
		for (int i = 2; i <= n; i++){
			res = res.multiply(BigInteger.valueOf(i));
		}
		return res;
	}

}
