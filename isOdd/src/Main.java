public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.isOdd(3);
        main.isOdd(4);
    }


    private int var;

    public void isOdd(int var) {
        this.var = var;
        if (var % 2 == 0) {
            System.out.println("ODD");
        } else {
            System.out.println("EVEN");
        }

    }
}
