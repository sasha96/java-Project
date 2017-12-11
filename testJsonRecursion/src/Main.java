import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> listTest = new ArrayList<>();
        listTest.add("cat");
        listTest.add("dog");
        listTest.add("1234");

        List<Cat> list = new ArrayList<>();
        list.add(new Cat(110, "murchuk", (ArrayList) listTest, true));
        list.add(new Cat(1113, "vasa1", null, true));

        List e = new ArrayList();
        Cat catTest = new Cat(110, "murchuk", (ArrayList) listTest, true);
        catTest.setA((ArrayList) list);

        System.out.println(Test.who(listTest));
        System.out.println(Test.who(catTest));
        System.out.println(Test.who(list));
    }
}