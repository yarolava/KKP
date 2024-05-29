import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TracingHandler implements InvocationHandler {
    private Object target;

    public TracingHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print("Method: " + method.getName() + "(");
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i]);
            if (i < args.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(")");

        Object result = method.invoke(target, args);

        System.out.println("Result: " + result);

        return result;
    }
}
