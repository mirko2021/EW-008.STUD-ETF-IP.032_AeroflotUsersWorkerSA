package yatospace.worker.services.db;

/**
 * Општи контролер за конекционе пуле.
 * @author VM
 * @version 1.0
 */
public interface MySQLConnectionPool {
	 public String getJdbcURL();
	 public String getUsername();
	 public String getPassword();
	 public String getDatabase();
	 public int getPreconnectCount();
	 public int getMaxIdleConnections();
	 public int getMaxConnections();
}
