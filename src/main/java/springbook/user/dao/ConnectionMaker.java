package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
//    public Connection makeConnection() throws ClassNotFoundException, SQLException;
public Connection makeConnection() throws SQLException; // DataSource
}
