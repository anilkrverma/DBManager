package core.connection_manager;

import core.constants.DBType;

import java.sql.Connection;
import java.util.HashMap;

public class ConnectionFactory {
  private Connection connection;
  private static HashMap<String, Connection> knownDBConnections = new HashMap<>();

  public Connection getConnection(
      DBType driver, String dbHost, int port, String dbName, String userName, String password) {
    switch (driver) {
      case MYSQL:
        String url = "jdbc:mysql://" + dbHost + ":" + port + "/" + dbName;
        if (knownDBConnections.containsKey(url)) {
          connection = knownDBConnections.get(url);
        } else {
          connection = new MySqlConnectionManager().getConnection(url, userName, password);
          knownDBConnections.putIfAbsent(url, connection);
        }
        break;
      case POSTGRES:
        url = "jdbc:postgresql://" + dbHost + ":" + port + "/" + dbName;
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
