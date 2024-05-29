import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class Task1_1 {
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {

		Class<?> intCls = Integer.class;
		Class<?> stringCls = String.class;
		Class<?> scanCls = Scanner.class;
		Class<?> intaCls = Integer[].class;
		Class<?> serCls = Serializable.class;
		Class<?> fieldCls = Field.class;
		Class<?> floatCls = Float.class;
		Class<?> dateCls = Date.class;
		Class<?> listCls = List.class;

		while (true) {
			System.out.print("\n\n1 Task 1\n2 Task 2\n3 Task 3\n4 Task 4\n5 Task 5\n0 Quit");
			System.out.print("\nPlease choose the option: ");
			int mbar = in.nextInt();

			switch (mbar) {
			case 1:
				System.out.print("\n1 Enter full name of class\n2 Choose object that exists\n0 Quit\n");
				System.out.print("Please enter the option: ");
				mbar = in.nextInt();
				switch (mbar) {
				case 1:
					System.out.print("Please enter full name of class (example: java.lang.Integer): ");
					in = new Scanner(System.in);
					String res = in.nextLine();
					Info obj = new Info(res);
					Info.info();
					break;
				case 2:
					System.out.print(
							"\n1 Integer\n2 String\n3 Scanner\n4 Integer[]\n5 Serializable\n6 Field\n7 Float\n8 Date\n9 List\n0 Quit\n\n");
					System.out.print("Please choose the class: ");
					mbar = in.nextInt();
					switch (mbar) {
					case 1:
						obj = new Info(intCls);
						Info.info();
						break;
					case 2:
						obj = new Info(stringCls);
						Info.info();
						break;
					case 3:
						obj = new Info(scanCls);
						Info.info();
						break;
					case 4:
						obj = new Info(intaCls);
						Info.info();
						break;
					case 5:
						obj = new Info(serCls);
						Info.info();
						break;
					case 6:
						obj = new Info(fieldCls);
						Info.info();
						break;
					case 7:
						obj = new Info(floatCls);
						Info.info();
						break;
					case 8:
						obj = new Info(dateCls);
						Info.info();
						break;
					case 9:
						obj = new Info(listCls);
						Info.info();
						break;
					case 0:
						break;
					default:
						System.out.print("\nThere's no such an option\n");
					}
					break;
				case 0:
					break;
				default:
					System.out.print("\nThere's no such an option\n");
				}
				break;
			case 2:
				System.out.print("\nPlease enter full class name: ");
				in = new Scanner(System.in);
				String strCls = in.nextLine();
				try {
					Class<?> cls = Class.forName(strCls);
					Info obj = new Info(cls);
					Info.getFields();

					System.out.println("\nMethods:");
					Method[] mthd = cls.getMethods();
					for (int i = 0; i < mthd.length; i++)
						System.out.println(mthd[i]);

					int num = 0;
					List<Method> mthds = new ArrayList<Method>();
					System.out.println("\n\nMethods without parameters:");
					for (int i = 0; i < mthd.length; i++) {
						if (mthd[i].getParameterCount() == 0) {
							num++;
							System.out.println(num + " " + mthd[i]);
							mthds.add((Method) mthd[i]);
						}
					}

					System.out.print("\nPlease enter number of method to call: ");
					in = new Scanner(System.in);
					mbar = in.nextInt();
					try {
						Object newIns = cls.getDeclaredConstructor().newInstance();
						Object res = mthds.get(mbar - 1).invoke(newIns);
						System.out.print("The result: " + res);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						System.out.print("\n\n");
						e.printStackTrace();
						System.out.print("\n\n");
					}
				} catch (ClassNotFoundException e) {
					System.out.print("\nThere's a ClassNotFound exception\n");
				}
				break;
			case 3:
				Info info = new Info();
				Class<?> cls = info.getClass();
				Method method = cls.getMethod("someM", int.class, String.class);
				Class<?> type = method.getReturnType();
				try {
					Object obj = type.getConstructor().newInstance();
					obj = method.invoke(info, 17, "MethodText");

					System.out.println("The method's result:");
					System.out.print(obj);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
				break;

			// --------------------------------
			// --------------------------------
			// --------------------------------
			// --------------------------------
			// --------------------------------
			// --------------------------------

			case 4:
				System.out.print("\n\n1 Create an array\n2 Create a matrix\n0 Quit");
				System.out.print("\nPlease choose the option: ");
				in = new Scanner(System.in);
				mbar = in.nextInt();
				switch (mbar) {
				case 1:
					System.out.print("\n1 Primary type\n2 Linked type\n0 Quit");
					System.out.print("\nPlease choose the option: ");
					mbar = in.nextInt();
					switch (mbar) {
					case 1:
						System.out.print("\nPlease enter the size of array: ");
						int size = in.nextInt();
						System.out.print("1 int\n2 double\n3 boolean\n0 Quit");
						System.out.print("Please choose the type: ");
						mbar = in.nextInt();
						int[] arr = new int[size];
						switch (mbar) {
						case 1:
							for (int i = 0; i < arr.length; i++)
								arr[i] = i;
							break;
						case 2:
							for (int i = 0; i < arr.length; i++)
								arr[i] = i;
							break;
						case 3:
							for (int i = 0; i < arr.length; i++)
								if (i % 2 == 0)
									arr[i] = 1;
								else
									arr[i] = 0;
							break;
						case 0:
							break;
						default:
							System.out.print("\nYou entered wrong option\n");
						}
						// while
						while (true) {
							System.out.print("\n1 Change size of array\n2 Array to string\n0 Quit");
							System.out.print("Please choose the option: ");
							mbar = in.nextInt();
							switch (mbar) {
							case 1:
								arr = changeArrSize(arr);
								System.out.print("\nSize of array was changed\n");
								continue;
							case 2:
								String res = arrToString(arr);
								System.out.print("\n" + res + "\n");
								continue;
							case 0:
								break;
							default:
								System.out.print("You enter wrong option");
							}
							break;
						}
					case 2:
						System.out.print("\nPlease enter the size of array: ");
						size = in.nextInt();
						System.out.print("1 Intger\n2 Double\n3 String\n0 Quit");
						System.out.print("\nPlease choose the type: ");
						mbar = in.nextInt();
						Object[] arrO = new Object[size];
						switch (mbar) {
						case 1:
							for (int i = 0; i < arrO.length; i++)
								arrO[i] = (int) i;
							break;
						case 2:
							for (int i = 0; i < arrO.length; i++)
								arrO[i] = (double) i;
							break;
						case 3:
							for (int i = 0; i < arrO.length; i++)
								if (i % 2 == 0)
									arrO[i] = (String) " 1 ";
							break;
						case 0:
							break;
						default:
							System.out.print("\nYou entered wrong option\n");
						}
						// while
						while (true) {
							System.out.print("\n1 Change size of array\n2 Array to string\n0 Quit");
							System.out.print("\nPlease choose the option: ");
							mbar = in.nextInt();
							switch (mbar) {
							case 1:
								arrO = changeObjArrSize(arrO);
								System.out.print("\nSize of array was changed\n");
								continue;
							case 2:
								String res = objArrToString(arrO);
								System.out.print("\n" + res + "\n");
								continue;
							case 0:
								break;
							default:
								System.out.print("You enter wrong option");
							}

							break;
						}
					case 0:
						break;
					default:
						System.out.print("\nYou entered wrong option\n");

					}

					break;
				case 2:
					System.out.print("\n1 Primary type\n2 Linked type\n0 Quit");
					System.out.print("\nPlease choose the option: ");
					mbar = in.nextInt();
					switch (mbar) {
					case 1:
						System.out.print("\nPlease enter number of string: ");
						int s = in.nextInt();
						System.out.print("\nPlease enter number of columns: ");
						int c = in.nextInt();
						System.out.print("1 int\n2 double\n3 boolean\n0 Quit");
						System.out.print("\nPlease choose the type: ");
						mbar = in.nextInt();
						int[][] matr = new int[s][c];
						switch (mbar) {
						case 1:
							for (int i = 0; i < matr.length; i++)
								for (int j = 0; j < matr[i].length; j++)
									matr[i][j] = (int) i;
							break;
						case 2:
							for (int i = 0; i < matr.length; i++)
								for (int j = 0; j < matr[i].length; j++)
									matr[i][j] = i;
							break;
						case 3:
							for (int i = 0; i < matr.length; i++)
								for (int j = 0; j < matr[i].length; j++)
									if ((i + j) % 2 == 0)
										matr[i][j] = 1;
									else
										matr[i][j] = 0;
							break;
						case 0:
							break;
						default:
							System.out.print("\nYou entered wrong option\n");
						}
						// while
						while (true) {
							System.out.print("\n1 Change size of matrix\n2 Matrix to string\n0 Quit");
							System.out.print("\nPlease choose the option: ");
							mbar = in.nextInt();
							switch (mbar) {
							case 1:
								matr = changeMatrSize(matr);
								System.out.print("\nSize of matrix was changed\n");
								continue;
							case 2:
								String res = matrToString(matr);
								System.out.print("\n" + res + "\n");
								continue;
							case 0:
								break;
							default:
								System.out.print("You enter wrong option");
							}
							break;
						}
					case 2:
						System.out.print("\nPlease enter number of string: ");
						s = in.nextInt();
						System.out.print("\nPlease enter number of columns: ");
						c = in.nextInt();
						System.out.print("1 Integer\n2 Double\n3 String\n0 Quit");
						System.out.print("\nPlease choose the type: ");
						mbar = in.nextInt();
						Object[][] matrO = new Object[s][c];
						switch (mbar) {
						case 1:
							for (int i = 0; i < matrO.length; i++)
								for (int j = 0; j < matrO[i].length; j++)
									matrO[i][j] = (int) i;
							break;
						case 2:
							for (int i = 0; i < matrO.length; i++)
								for (int j = 0; j < matrO[i].length; j++)
									matrO[i][j] = (double) i;
							break;
						case 3:
							for (int i = 0; i < matrO.length; i++)
								for (int j = 0; j < matrO[i].length; j++)
									matrO[i][j] = (String) " 1 ";
							break;
						case 0:
							break;
						default:
							System.out.print("\nYou entered wrong option\n");
						}
						// while
						while (true) {
							System.out.print("\n1 Change size of matrix\n2 Matrix to string\n0 Quit");
							System.out.print("\nPlease choose the option: ");
							mbar = in.nextInt();
							switch (mbar) {
							case 1:
								matrO = changeObjMatrSize(matrO);
								System.out.print("\nSize of matrix was changed\n");
								continue;
							case 2:
								String res = objMatrToString(matrO);
								System.out.print("\n" + res + "\n");
								continue;
							case 0:
								break;
							default:
								System.out.print("You enter wrong option");
							}
							break;
						}
					case 0:
						break;
					default:
						System.out.print("\nYou entered wrong option\n");
					}

					break;
				}
				case 5:
					SomeClass someClass = new SomeClass();

					// Создание прокси-объекта для метода профилирования
					SomeInterface profilingProxy = (SomeInterface) Proxy.newProxyInstance(
							SomeInterface.class.getClassLoader(), new Class[] { SomeInterface.class },
							new ProfilingHandler(someClass));

					// Использование прокси-объекта для метода профилирования
					profilingProxy.calculate(5);

					// Создание прокси-объекта для трассировки метода
					SomeInterface tracingProxy = (SomeInterface) Proxy.newProxyInstance(
							SomeInterface.class.getClassLoader(), new Class[] { SomeInterface.class },
							new TracingHandler(someClass));

					// Использование прокси-объекта для трассировки метода
					tracingProxy.calculate(10);

					break;
				case 0:
					System.out.println("\n---You quit from program---");
					return;
				default:
					System.out.println("You enter wrong option\n");
			}
		}
	}

	public static int[] changeArrSize(int[] arr) {
		System.out.print("Please etner new size of array: ");
		int nSize = in.nextInt();
		arr = Arrays.copyOf(arr, nSize);
		return arr;
	}

	public static Object[] changeObjArrSize(Object[] arrO) {
		System.out.print("Please enter new size of array: ");
		int nSize = in.nextInt();
		arrO = Arrays.copyOf(arrO, nSize);
		return arrO;
	}

	public static int[][] changeMatrSize(int[][] matr) {
		System.out.print("Please enter new number of strings: ");
		int s = in.nextInt();
		System.out.print("Please enter number of columns:");
		int c = in.nextInt();
		int[][] nmatr = new int[s][c];

		for (int i = 0; i < nmatr.length; i++)
			nmatr[i] = Arrays.copyOf(matr[i], nmatr[i].length);

		return nmatr;
	}

	public static Object[][] changeObjMatrSize(Object[][] matr) {
		System.out.print("Please enter new number of strings: ");
		int s = in.nextInt();
		System.out.print("Please enter number of columns:");
		int c = in.nextInt();
		Object[][] nmatr = new Object[s][c];

		for (int i = 0; i < nmatr.length; i++)
			nmatr[i] = Arrays.copyOf(matr[i], nmatr[i].length);

		return nmatr;
	}

	public static String arrToString(int[] arr) {
		String str = Arrays.toString(arr);
		return str;
	}

	public static String objArrToString(Object[] arr) {
		String str = Arrays.toString(arr);
		return str;
	}

	public static String matrToString(int[][] matr) {
		String str = Arrays.deepToString(matr);
		return str;
	}

	public static String objMatrToString(Object[][] matr) {
		String str = Arrays.deepToString(matr);
		return str;
	}
}
