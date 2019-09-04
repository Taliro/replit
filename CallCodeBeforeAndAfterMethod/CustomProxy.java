import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CustomProxy<T> implements InvocationHandler {

    private T obj;

    public static <T> T newInstance(T obj) {
        return (T) java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new CustomProxy<>(obj));
    }

    private CustomProxy(T obj) {
        this.obj = obj;
    }

     @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        try {
            List<IExecuteWire> executeWires = new ArrayList<>();
            for (Annotation annotation : m.getAnnotations()) {
                if(annotation.annotationType().getName().toLowerCase().equals("override")) continue;
                Class<?> cls;
                try {
                    cls = Class.forName(String.format("%sAnnotation", annotation.annotationType().getSimpleName()));
                } catch (ClassNotFoundException e) {
                    System.err.println(String.format("%sAnnotation not found", annotation.annotationType().getSimpleName()));
                    continue;
                }
                ExecuteWire executeWire = (ExecuteWire) cls.newInstance();
                executeWires.add(executeWire);
                executeWire.before(proxy, m, args);
            }
            result = m.invoke(obj, args);
            for (IExecuteWire executeWire : executeWires) {
                executeWire.after(proxy, m, args);
            }
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        }
        return result;
    }
}
