
public class Cat {
    private int age;
    private String name;
    private boolean play;

    public Cat(int age, String name, boolean play) {
        this.age = age;
        this.name = name;
        this.play = play;
    }

    @Override
    public String toString() {
        return "Cat{" + "age=" + age + ", name='" + name + '\'' + ", play=" + play + '}';
    }

    public Cat() {
    }

}
