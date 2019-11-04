package core.db_manager;

import core.connection_manager.ConnectionFactory;
import core.constants.DBType;
import core.interfaces.IDBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBManager implements IDBManager {

  public List<Map<String, String>> doSelect(
      DBType driver,
      String dbHost,
      int port,
      String dbName,
      String userName,
      String password,
      String query)
      throws SQLException {
    List<Map<String, String>> results = new ArrayList<>();
    Connection connection =
        new ConnectionFactory().getConnection(driver, dbHost, port, dbName, userName, password);

    Statement statement = null;
    ResultSet resultSet = null;
    try {
      statement = connection.createStatement();
      resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        Map<String, String> rowData = new LinkedHashMap<>();

        for (int col = 1; col <= resultSetMetaData.getColumnCount(); col++) {
          rowData.put(resultSetMetaData.getColumnLabel(col), resultSet.getString(col));
        }

        results.add(rowData);
      }

    } finally {
      if (resultSet != null) {
        resultSet.close();
      }
      if (statement != null) {
        statement.close();
      }
    }
    return results;
  }

  public List<Map<String, String>> doSelect(DbConfig dbConfig, String query) throws SQLException {

    return doSelect(
        dbConfig.getDbType(),
        dbConfig.getDbHost(),
        dbConfig.getPort(),
        dbConfig.getDbName(),
        dbConfig.getUserName(),
        dbConfig.getPassword(),
        query);
  }

  public int executeUpdate(
      DBType driver,
      String dbHost,
      int port,
      String dbName,
      String userName,
      String password,
      String query)
      throws SQLException {

    Connection connection =
        new ConnectionFactory().getConnection(driver, dbHost, port, dbName, userName, password);
    Statement st = null;
    int noOfRows = 0;
    try {
      st = connection.createStatement();
      noOfRows = st.executeUpdate(query);
    } finally {
      if (st != null) {
        st.close();
      }
    }
    System.out.println("No. of rows affected after running " + query + " is: " + noOfRows);
    return noOfRows;
  }

  public int executeUpdate(DbConfig dbConfig, String query) throws SQLException {

    return executeUpdate(
        dbConfig.getDbType(),
        dbConfig.getDbHost(),
        dbConfig.getPort(),
        dbConfig.getDbName(),
        dbConfig.getUserName(),
        dbConfig.getPassword(),
        query);
  }
}
