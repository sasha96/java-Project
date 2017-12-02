package java.com.alex.zhurylo.dao;


import java.com.alex.zhurylo.entity.Chapters;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Dao {

        public void addChapter(Chapters chapters) throws SQLException, ClassNotFoundException, IOException;

        public void updateChapter(Chapters chapters);

        public void removeChapter(int id);

        public Chapters getChapters(int id);

        public List<Chapters> listChapters();

}
