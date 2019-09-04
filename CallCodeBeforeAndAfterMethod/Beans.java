import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Beans {

    public static <T> T get(Class<T> clazz) {
        try {
            return (T) constructInjector(clazz, false);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T get(Class<T> clazz, Boolean loaderAnnotation) {
        try {
            return (T) constructInjector(clazz, loaderAnnotation);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static <T> T constructInjector(Class<T> clazz, Boolean loaderAnnotation) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Constructor[] constructors = clazz.getConstructors();
        Class<?>[] parameters = null;
        Object[] objects = null;
        for (Constructor constructor : constructors) {
            Annotation inject;
            if((inject = constructor.getAnnotation(Inject.class)) != null && inject instanceof Inject) {
                parameters = constructor.getParameterTypes();
                objects = new Object[parameters.length];
                for(int i = 0; i < parameters.length - 1; i++) {
                   objects[i] = parameters[i].newInstance();
                }
            }
        }

        if(loaderAnnotation) {
          if(objects == null) return CustomProxy.newInstance(clazz.newInstance());
          return CustomProxy.newInstance(clazz.getDeclaredConstructor(parameters).newInstance(objects));
        } 

        if(objects == null) return clazz.newInstance();
        return clazz.getDeclaredConstructor(parameters).newInstance(objects);
    }
}
