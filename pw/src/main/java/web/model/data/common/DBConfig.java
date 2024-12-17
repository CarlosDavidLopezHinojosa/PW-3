package web.model.data.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
  private String url;
  private String username;
  private String password;

  public DBConfig(InputStream input) {
    Properties properties = new Properties();
    try {
      if (input == null) {
        System.out.println("Sorry, unable to find the configuration file.");
        return;
      }
      properties.load(input);
      this.url = properties.getProperty("db.url");
      this.username = properties.getProperty("db.username");
      this.password = properties.getProperty("db.password");
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public String getUrl() {
    return url;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}