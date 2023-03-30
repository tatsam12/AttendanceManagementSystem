package org.exmaple;

import org.exmaple.models.ModelClass;
import org.exmaple.models.ModelUsers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHandler {

    public static final String TABLE_USER = "user";
    public static final String TABLE_CLASS = "class";

    public static final String TABLE_ATTENDANCE = "attendance";

    //Column Names
    public static final String COLUMN_USER_ID = ("user_id");
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CLASS_ID = "class_id";
    public static final String COLUMN_CLASS_NAME = "class_name";
    private static final String COLUMN_ID = "id";


    public static Connection connect() {

        Connection conn = null;

        String url = "jdbc:sqlite:src/main/resources/Database/AMS.db";

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void insert_user(Connection conn, ModelUsers user) throws SQLException {

        String sql = "INSERT INTO " + TABLE_USER + " (username,password)" + "VALUES (?,?)";


        PreparedStatement statement = conn.prepareStatement(sql);
        try {
            statement = null;
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void insert_class(Connection conn, ModelClass classes) {

        String sql = "INSERT INTO " + TABLE_CLASS + " (classname)" + "VALUES (?)";


        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            statement.setString(1, classes.getClass_name());
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void insert_Attendance(Connection connection, ModelUsers us, ModelClass cs) {
        String sql = "INSERT INTO attendance (user_id,class_id)VALUES (?,?)";


        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, us.getUser_id());
            statement.setInt(2, cs.getClass_id());
            statement.execute();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<ModelUsers> display_user(Connection conn) {

        String sql = "SELECT * FROM " + TABLE_USER;

        List<ModelUsers> users_lists = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                int us_user_id = resultset.getInt(COLUMN_USER_ID);
                String us_username = resultset.getString(COLUMN_USERNAME);
                String us_password = resultset.getString(COLUMN_PASSWORD);
                ModelUsers user_data = new ModelUsers(us_user_id, us_username, us_password);
                users_lists.add(user_data);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users_lists;
    }

    public static List<ModelClass> display_class(Connection connection) {

        String sql = "SELECT * FROM " + TABLE_CLASS;

        List<ModelClass> classes_listz = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                int cs_class_id = resultset.getInt(COLUMN_CLASS_ID);
                String cs_classname = resultset.getString(COLUMN_CLASS_NAME);
                ModelClass classes_data = new ModelClass(cs_class_id, cs_classname);
                classes_listz.add(classes_data);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return classes_listz;
    }
}