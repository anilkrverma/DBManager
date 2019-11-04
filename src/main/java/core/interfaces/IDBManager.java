package core.interfaces;

import core.constants.DBType;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IDBManager {
    List<Map<String, String>> doSelect(
          DBType driver,
          String dbHost,
          int port,
          String dbName,
          String userName,
          String password,
          String query)
      throws SQLException;

  int executeUpdate(
      DBType driver,
      String dbHost,
      int port,
      String dbName,
      String userName,
      String password,
      String query)
      throws SQLException;
}
