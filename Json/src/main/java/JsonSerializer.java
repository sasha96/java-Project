import java.lang.reflect.Type;
import java.util.List;

public interface JsonSerializer {
    String write(Object obj) throws IllegalAccessException;
    Object read(String string, Class clazz,String type) throws IllegalAccessException, InstantiationException, ClassNotFoundException;
}
