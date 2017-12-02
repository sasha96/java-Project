import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException, InstantiationException {
        Cat cat = new Cat(110, "murchuk", true);
        Class clazz = Class.forName("Cat");
        List list = new ArrayList();
        list.add("cat");
        list.add("dog");
        list.add("1234");
        String s = "\"{\"age\":1000,\"name\":\"vasa\",\"play\":\"true\"}\"";
        String s2 = "{\"Petro\",\"Ivan\",32}";
        Method.readJson(s2);
        Method.readJson(s, clazz);
        Method.writeJson(cat, clazz);
        Method.writeJson(list);
    }

}