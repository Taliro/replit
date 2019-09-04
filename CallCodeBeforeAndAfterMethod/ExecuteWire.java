import java.lang.reflect.Method;

public interface ExecuteWire {

    void before(Object proxy, Method m, Object[] args);

    void after(Object proxy, Method m, Object[] args);

}
