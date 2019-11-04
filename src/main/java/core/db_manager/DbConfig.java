package core.db_manager;

import core.constants.DBType;

public class DbConfig {
  private DBType dbType;
  private String dbHost;
  private int port;
  private String dbName;
  private String userName;
  private String password;

  public DBType getDbType() {
    return dbType;
  }

  public void setDbType(DBType dbType) {
    this.dbType = dbType;
  }

  public String getDbHost() {
    return dbHost;
  }

  public void setDbHost(String dbHost) {
    this.dbHost = dbHost;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "DbConfig{"
        + "dbHost='"
        + dbHost
        + '\''
        + ", port="
        + port
        + ", dbName='"
        + dbName
        + '\''
        + ", userName='"
        + userName
        + '\''
        + ", password='"
        + password
        + '\''
        + '}';
  }
}
