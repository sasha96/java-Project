import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyJsonSerializerTest {

    JsonSerializer serializer;

    @Before
    public void setUp() {
        serializer = new MyJsonSerializer();
    }

    @Test
    public void testWriteCat() throws IllegalAccessException {
        Cat cat = new Cat();
        cat.setName("Cat");
        cat.setAge(10);
        cat.setHungry(true);
        List<Cat> list = new ArrayList<>();
        cat.setEnemy(new Dog("Dog1", list));
        String expectedResult = "{\"name\":\"Cat\",\"age\":10,\"isHungry\":true,\"enemy\":{\"nameD\":\"Dog1\",\"enemies\":[]}}";
        String actualResult = serializer.write(cat);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testWriteDog() throws IllegalAccessException {
        Cat cat = new Cat();
        cat.setName("Cat");
        cat.setAge(10);
        cat.setHungry(true);
        cat.setEnemy(null);
        List<Cat> list = new ArrayList<>();
        list.add(cat);
        Dog dog = new Dog("Dog", list);
        String expectedResult = "{\"nameD\":\"Dog\",\"enemies\":[{\"name\":\"Cat\",\"age\":10,\"isHungry\":true,\"enemy\":null}]}";
        String actualResult = serializer.write(dog);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testWriteListOfCats() throws IllegalAccessException {
        Cat cat1 = new Cat();
        cat1.setName("Cat1");
        cat1.setAge(10);
        cat1.setEnemy(new Dog("Dog1", new ArrayList<>()));
        cat1.setHungry(true);
        Cat cat2 = new Cat();
        cat2.setName("Cat2");
        cat2.setAge(5);
        cat2.setHungry(false);
        cat2.setEnemy(new Dog("Dog2", new ArrayList<>()));
        String expectedResult = "[{\"name\":\"Cat1\",\"age\":10,\"isHungry\":true,\"enemy\":{\"nameD\":\"Dog1\",\"enemies\":[]}}" +
                ",{\"name\":\"Cat2\",\"age\":5,\"isHungry\":false,\"enemy\":{\"nameD\":\"Dog2\",\"enemies\":[]}}]";
        String actualResult = serializer.write(Arrays.asList(cat1, cat2));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testReadCat() throws Exception {
        String json =  "{\"nameD\":\"pety\",\"enemies\":[{\"name\":\"Cat1\",\"age\":5,\"isHungry\":false,\"enemy\":null}]";

        List<Cat> list = new ArrayList<>();
        Cat cat1 = new Cat();
        cat1.setName("Cat1");
        cat1.setAge(5);
        cat1.setEnemy(null);
        list.add(cat1);
        Dog expectedDog = new Dog();
        expectedDog.setNameD("pety");
        expectedDog.setEnemies(list);
        Object actualResult = serializer.read(json, Dog.class);
        assertEquals(expectedDog, actualResult);
    }

    @Test
    public void testReadList() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("window");
        list.add("desc");
        list.add("pen");
        String expectedResult = "[\"window\",\"desc\",\"pen\"]";
        Object actualResult =  serializer.read(expectedResult, list.getClass());
        assertEquals(list, actualResult);
    }

    @Test
    public void testReadMouse() throws IllegalAccessException {
        Mouse mouse = new Mouse();
        mouse.setAge(10);
        mouse.setName("mouse");
        String expectedResult = "{\"age\":10,\"name\":\"mouse\"}";
        String actualResult = serializer.write(mouse);
        assertEquals(expectedResult, actualResult);
    }

}