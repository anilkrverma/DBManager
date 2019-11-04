package core.connection_manager;

import core.constants.DBType;
import core.interfaces.IConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionManager implements IConnectionManager {
  @Override
  public Connection getConnection(String url, String userName, String password) {

    Connection connection = null;

    try {
      Class.forName(DBType.MYSQL.getDriver()).newInstance();
      connection = DriverManager.getConnection(url, userName, password);

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    }

    return connection;
  }
}
