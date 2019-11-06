package core.connection_manager;

import core.constants.DBType;

import java.sql.Connection;
import java.util.HashMap;

public class ConnectionFactory {
  private Connection connection;
  private static HashMap<String, Connection> knownDBConnections = new HashMap<>();

  public Connection getConnection(
      DBType driver,
      String dbHost,
      int port,
      String dbName,
      boolean isSslEnabled,
      String userName,
      String password) {
    String url;
    switch (driver) {
      case MYSQL:
        url = "jdbc:mysql://" + dbHost + ":" + port + "/" + dbName + "?useSSL=" + isSslEnabled;
        if (knownDBConnections.containsKey(url)) {
          connection = knownDBConnections.get(url);
        } else {
          connection = new MySqlConnectionManager().getConnection(url, userName, password);
          knownDBConnections.putIfAbsent(url, connection);
        }
        break;
      case POSTGRES:
        url = "jdbc:postgresql://" + dbHost + ":" + port + "/" + dbName + "?useSSL=" + isSslEnabled;
        if (knownDBConnections.containsKey(url)) {
          connection = knownDBConnections.get(url);
        } else {
          connection = new PostgresConnectionManager().getConnection(url, userName, password);
          knownDBConnections.putIfAbsent(url, connection);
        }
        break;
      default:
        throw new RuntimeException("Unknown driver passed.");
    }

    return connection;
  }
}
