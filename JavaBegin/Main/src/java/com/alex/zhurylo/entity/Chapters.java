package java.com.alex.zhurylo.entity;

public class Chapters {
    private int id_chapter;
    private String name;

    public Chapters(int id_chapter, String name) {
        this.id_chapter = id_chapter;
        this.name = name;
    }
    public Chapters(){

    }

    public int getId_chapter() {
        return id_chapter;
    }

    public void setId_chapter(int id_chapter) {
        this.id_chapter = id_chapter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
