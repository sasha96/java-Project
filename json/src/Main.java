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
        list.add(new Cat(111, "murchuk1", true));

        MethodsJson.writeJson(listTest);
        MethodsJson.writeJson(catTest);
        MethodsJson.writeJson(list); //невийшов
        // [{age=110, name='murchuk', play=true},{age=110, name='murchuk', play=true}]невийшов невийшов невийшов невийшов

        MethodsJson.readJson("{\"age\":110,\"name\":\"murchuk\",\"play\":true}", Class.forName("Cat"));
        MethodsJson.readJson("[\"cat\",\"dog\",\"1234\"]", Class.forName("java.util.ArrayList"));


    }
}