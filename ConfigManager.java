import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class ConfigManager {
  
  String file_path;
  Map<String, String> data;

  
  
  public ConfigManager() {
  
  }

  public ConfigManager(String file_path) {
    this.file_path = file_path;
    this.data = new HashMap<>();
    read();
  }


  public static void createConfigFile(String file_name) {
      try {
        FileWriter file_writer = new FileWriter(file_name);
        System.out.println("Configuration file created successfully!");
      } catch (IOException e) {
        System.out.println(e);
      }
  }

  public void read() {
    try (BufferedReader buffer_reader = Files.newBufferedReader(Path.of(file_path), StandardCharsets.UTF_8)) {
      String line;
      while ((line = buffer_reader.readLine()) != null) {
        int eqIndex = line.indexOf('=');
        if (eqIndex > 0) {
          String key = line.substring(0, eqIndex).strip();
          String value = line.substring(eqIndex + 1).strip();
          data.put(key, value);
        }
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public void save() {
    try (BufferedWriter buffer_writer = Files.newBufferedWriter(Path.of(file_path), StandardCharsets.UTF_8)) {
      for (Map.Entry<String, String> entry: data.entrySet()) {
        buffer_writer.write(entry.getKey() + "=" + entry.getValue());
        buffer_writer.newLine();
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }


  public String get(String key) {
    return data.get(key);
  }

  public int getInteger(String key) {
    return Integer.parseInt(data.get(key));
  }

  public float getFloat(String key) {
    return Float.parseFloat(data.get(key));
  }

  public boolean getBoolean(String key) {
    return Boolean.parseBoolean(key);
  }


  public void write(String key, String value) {
    data.put(key, value);
    save();
  }

  public void write(String key, int value) {
    data.put(key, "" + value);
    save();
  }

  public void write(String key, float  value) {
    data.put(key, "" + value);
    save();
  }

  public void write(String key, boolean value) {
    data.put(key, "" + value);
    save();
  }

  

  
  

}
