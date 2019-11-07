package core.db_manager;

import core.connection_manager.ConnectionFactory;
import core.constants.DBType;
import core.interfaces.IDBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DBManager implements IDBManager {

  public List<LinkedHashMap<String, String>> doSelect(
      DBType driver,
      String dbHost,
      int port,
      String dbName,
      boolean isSslEnabled,
      String userName,
      String password,
      String query)
      throws SQLException {
    List<LinkedHashMap<String, String>> results = new ArrayList<>();
    Connection connection =
        new ConnectionFactory().getConnection(driver, dbHost, port, dbName, isSslEnabled, userName, password);

    try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {

      while (resultSet.next()) {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        LinkedHashMap<String, String> rowData = new LinkedHashMap<>();

        for (int col = 1; col <= resultSetMetaData.getColumnCount(); col++) {
          rowData.put(resultSetMetaData.getColumnLabel(col), resultSet.getString(col));
        }

        results.add(rowData);
      }
    }
    return results;
  }

  public List<LinkedHashMap<String, String>> doSelect(DBConfig dbConfig, String query) throws SQLException {

    return doSelect(
        dbConfig.getDbType(),
        dbConfig.getDbHost(),
        dbConfig.getPort(),
        dbConfig.getDbName(),
        dbConfig.isSslEnabled(),
        dbConfig.getUserName(),
        dbConfig.getPassword(),
        query);
  }

  public int executeUpdate(
      DBType driver,
      String dbHost,
      int port,
      String dbName,
      boolean isSslEnabled,
      String userName,
      String password,
      String query)
      throws SQLException {

    Connection connection =
        new ConnectionFactory().getConnection(driver, dbHost, port, dbName, isSslEnabled, userName, password);
    int noOfRows;
    try (Statement st = connection.createStatement()) {
      noOfRows = st.executeUpdate(query);
    }
    System.out.println("No. of rows affected after running " + query + " is: " + noOfRows);
    return noOfRows;
  }

  public int executeUpdate(DBConfig dbConfig, String query) throws SQLException {

    return executeUpdate(
        dbConfig.getDbType(),
        dbConfig.getDbHost(),
        dbConfig.getPort(),
        dbConfig.getDbName(),
        dbConfig.isSslEnabled(),
        dbConfig.getUserName(),
        dbConfig.getPassword(),
        query);
  }
}
