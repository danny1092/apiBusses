package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DBConnection {
  // Local (tu máquina)
  private static final String LOCAL_URL  =  "jdbc:mysql://mysql-mm-nlb-3334d9e3ecaf69b9.elb.us-east-2.amazonaws.com:3306/busses_db_test"
          + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&connectTimeout=2000&socketTimeout=5000"; //"jdbc:mysql://localhost:3306/busses_db_test?useSSL=false&serverTimezone=UTC";
  private static final String LOCAL_USER = "app"; //"root";
  private static final String LOCAL_PASS = "AppP@ss_2025!"; //"";

  // Remoto (NLB MySQL en AWS)
  private static final String REMOTE_URL  = "jdbc:mysql://mysql-mm-nlb-3334d9e3ecaf69b9.elb.us-east-2.amazonaws.com:3306/busses_db_test"
      + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&connectTimeout=2000&socketTimeout=5000";
  private static final String REMOTE_USER = "app";
  private static final String REMOTE_PASS = "AppP@ss_2025!";

  // Timeout de sonda rápida al puerto local 3306
  private static final int PROBE_TIMEOUT_MS = 700;

  private static boolean isLocalMySQLUp() {
    try (Socket s = new Socket()) {
      s.connect(new InetSocketAddress("127.0.0.1", 3306), PROBE_TIMEOUT_MS);
      return true;
    } catch (Exception ignore) {
      return false;
    }
  }

  public static Connection getConnection() throws SQLException {
    DriverManager.setLoginTimeout(3); // evita que se quede colgado en entornos caídos
    if (isLocalMySQLUp()) {
      return DriverManager.getConnection(LOCAL_URL, LOCAL_USER, LOCAL_PASS);
    }
    return DriverManager.getConnection(REMOTE_URL, REMOTE_USER, REMOTE_PASS);
  }
}
