package java.com.alex.zhurylo.entity;

public class Main {
    private int id;
    private int id_chapter;
    private int id_reference;

    public Main(int id, int id_chapter, int id_reference) {
        this.id = id;
        this.id_chapter = id_chapter;
        this.id_reference = id_reference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_chapter() {
        return id_chapter;
    }

    public void setId_chapter(int id_chapter) {
        this.id_chapter = id_chapter;
    }

    public int getId_reference() {
        return id_reference;
    }

    public void setId_reference(int id_reference) {
        this.id_reference = id_reference;
    }
}