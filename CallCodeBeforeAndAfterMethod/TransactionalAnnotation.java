import java.lang.reflect.Method;
public class TransactionalAnnotation implements ExecuteWire{

    @Override
    public void before(Object proxy, Method m, Object[] args) {
        System.out.println("ok before");
    }

    @Override
    public void after(Object proxy, Method m, Object[] args) {
        System.out.println("ok after");
    }
}
