import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class Info {
	private static Class<?>cls;
	
	
	public Info() {
		Info.cls=String.class;
	};
	
	
	public Info(String str) {
		try {
			Info.cls=Class.forName(str);
		} catch (ClassNotFoundException e) {
			System.out.println("There's no such a class");
		}
	}
	
	
	public Info(Class<?> cls) {
		Info.cls=cls;
	}
	
	
	//Methods
	public static void getPackages() {
		System.out.println("Package:");
		System.out.println(cls.getPackage());
	}
	
	public static void getModifiers() {
		System.out.println("\nModifiers:");
		int mod = cls.getModifiers();
		String mods = Modifier.toString(mod);
		System.out.println(mods);
	}
	
	public static void getName() {
		System.out.print("\nName:");
		System.out.println(cls.getSimpleName());
	}
	
	public static void getSuperClass() {
		System.out.println("\nSuper class:");
		System.out.println(cls.getSuperclass());
	}
	
	public static void getInterfaces() {
		System.out.println("\nInterfaces:");
		Class<?>[] interf = cls.getInterfaces();
		for (int i = 0; i < interf.length; i++)
			System.out.println(interf[i]);
		Class<?> sCls = cls;
		while (sCls.getSuperclass() != null) {
			sCls = sCls.getSuperclass();
			Class<?>[] sInterf = sCls.getInterfaces();
			for (int i = 0; i < sInterf.length; i++)
				System.out.println(sInterf[i]);
		}
	}
		
		public static void getFields() {
			System.out.println("\nFields:");
			Field[] flds = cls.getDeclaredFields();
			for (int i = 0; i < flds.length; i++)
				System.out.println(flds[i]);
		}
		
		public static void getConstructors() {
			System.out.println("\nConstructors:");
			Constructor[] cnst = cls.getDeclaredConstructors();
			for (int i = 0; i < cnst.length; i++)
				System.out.println(cnst[i]);
		}
		
		public static void getDecMethods() {
			System.out.println("\nMethods:");
			Method[] mthd = cls.getDeclaredMethods();
			for (int i = 0; i < mthd.length; i++)
				System.out.println(mthd[i]);
		}
		
//		public static void getMethods() {
//			System.out.println("\nMethods:");
//			Method[] mthd = cls.getMethods();
//			for (int i = 0; i < mthd.length; i++)
//				System.out.println(mthd[i]);
//		}
		
		
	public static void info() {
		getPackages();
		getModifiers();
		getName();
		getSuperClass();
		getInterfaces();
		getFields();
		getConstructors();
		getDecMethods();	
	}
	
	
	public String someM(int n, String w) {
		String res="";
		for(int i=0; i<n; i++) {
			if(i%5==0)
				res+="\n";
			res+=(" "+w+" ");
		}
		return res;

	}
}
