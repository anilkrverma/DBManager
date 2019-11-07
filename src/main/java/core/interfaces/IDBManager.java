package core.interfaces;

import core.constants.DBType;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

public interface IDBManager {
    List<LinkedHashMap<String, String>> doSelect(
          DBType driver,
          String dbHost,
          int port,
          String dbName,
          boolean isSslEnabled,
          String userName,
          String password,
          String query)
      throws SQLException;

  int executeUpdate(
      DBType driver,
      String dbHost,
      int port,
      String dbName,
      boolean isSslEnabled,
      String userName,
      String password,
      String query)
      throws SQLException;
}
