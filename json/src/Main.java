import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        List<String> listTest = new ArrayList<>();
        listTest.add("cat");
        listTest.add("dog");
        listTest.add("1234");
        Cat catTest = new Cat(110, "murchuk", true);
        List<Cat> list = new ArrayList<>();
        list.add(new Cat(110, "murchuk", true));
        list.add(new Cat(1113, "vasa1", true));
        list.add(new Cat(1113, "vasa1", true));
        list.add(new Cat(1113, "vasa1", true));
        List e = new ArrayList();
//        MethodsJson.writeJson(listTest);
    MethodsJson.writeJson(list);
    //    MethodsJson.writeJson(catTest);

    }
}