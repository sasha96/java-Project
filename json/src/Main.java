import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Cat cat = new Cat(110, "murchuk", true);
        Class clazz2 = null;
        try {
            clazz2 = Class.forName("Cat");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Class clazz = null;
        try {
            clazz = Class.forName("java.util.List");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List list = new ArrayList();
        list.add("cat");
        list.add("dog");
        list.add(1234);
        String s = "\"{\"age\":1000,\"name\":\"petro\",\"play\":\"true\"}\"";
        String test = "\"\"{\"age\":110,\"name\":\"murchuk\",\"play\":\"true\"}\"\"";
        String s2 = "{\"Petro\",\"Ivan\",32}";
        try {
            MethodsJson.readJson(s2,clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // MethodJson.readJson(test, clazz2);
        MethodsJson.writeJson(list, clazz);
        MethodsJson.writeJson(cat, clazz2);

    }

}