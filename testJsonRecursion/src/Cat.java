import java.util.ArrayList;

public class Cat {
    private int age;
    private String name;
    private ArrayList list;
    private boolean play;

    public Cat() {
    }

    public Cat(int age, String name, ArrayList list, boolean play) {
        this.age = age;
        this.name = name;
        this.list = list;
        this.play = play;
    }

    public ArrayList getA() {
        return list;
    }

    public void setA(ArrayList list) {
        this.list = list;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    @Override
    public String toString() {
        return "Cat{" + "age=" + age + ", name='" + name + '\'' + ", play=" + play + '}';
    }

}
