import java.lang.reflect.Method;

public interface IExecuteWire {

    void before(Object proxy, Method m, Object[] args);

    void after(Object proxy, Method m, Object[] args);

}
