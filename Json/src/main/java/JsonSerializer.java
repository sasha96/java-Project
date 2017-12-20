
public interface JsonSerializer {
    String write(Object obj) throws IllegalAccessException;
    Object read(String string, Class clazz) throws Exception;
    Object read(String string, String stringObject) throws Exception;
}
