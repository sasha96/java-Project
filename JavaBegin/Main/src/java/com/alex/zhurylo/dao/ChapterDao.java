package java.com.alex.zhurylo.dao;

import java.com.alex.zhurylo.entity.Chapters;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;


import java.sql.*;

public class ChapterDao implements Dao {
    Connection connection = null;
    private static final Logger logger = Logger.getLogger(Dao.class.getName());


    public void addChapter(Chapters chapters) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO chapters (id_chapter, name) VALUES(?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, chapters.getId_chapter());
            preparedStatement.setString(2, chapters.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("User successfully saved. User details: " + chapters);
    }

    public void updateChapter(Chapters chapters) {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE chapters SET id_chapters, name WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, chapters.getId_chapter());
            preparedStatement.setString(2, chapters.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("User successfully saved. User details: " + chapters);
    }

    public void removeChapter(int id) {
        PreparedStatement preparedStatement = null;
        Chapters chapters = new Chapters();
        String sql = "DELETE FROM EMPLOYEE WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, chapters.getId_chapter());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("User successfully saved. User details: " + chapters);
    }

    public Chapters getChapters(int id) {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM chapters WHERE ID=?";
        Chapters chapters = new Chapters();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            chapters.setId_chapter(resultSet.getInt("id_chapters"));
            chapters.setName(resultSet.getString("name"));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("User successfully saved. User details: " + chapters);
        return chapters;
    }

    public List<Chapters> listChapters() {

        List<Chapters> chaptersList = new ArrayList();
        String sql = "SELECT id_chapters, name FROM chapters";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Chapters chapters = new Chapters();
                chapters.setId_chapter(resultSet.getInt("id_chapters"));
                chapters.setName(resultSet.getString("name"));
                chaptersList.add(chapters);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("User successfully saved. User details: " + chaptersList);
        return chaptersList;
    }

}
