import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Cat cat = new Cat(110, "murchuk", true);
        Cat cat2 = new Cat(112, "aaa", false);
        Cat cat3 = new Cat(113, "vase", true);

        List<? super Object> list2 = new ArrayList<>();
        list2.add("cat");
        list2.add("dog");
        list2.add(1234);
        List<? super Object> list = new ArrayList<>();
        list.add(cat);
        list.add(cat2);
        list.add(cat3);

        String s = "{\"age\":110,\"name\":\"murchuk\",\"play\":true}";
        String test = "{\"cat\",\"dog\",1234}";

        Class clazzList = Class.forName("java.util.ArrayList");
        Class clazz2 =Class.forName("Cat");


//        MethodsJson.writeJson(cat);
//        MethodsJson.writeJson(list2);
        MethodsJson.readJson(s,clazz2);
      //  MethodsJson.readJson(test,clazzList);


    }
}