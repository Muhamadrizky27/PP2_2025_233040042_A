package id.ac.unpas.modul09;

import java.io.Serializable;

public class UserConfig implements Serializable {
    private String username;
    private int fontSize;

    // Constructor
    public UserConfig(String username, int fontSize) {
        this.username = username;
        this.fontSize = fontSize;
    }

    // Getter dan Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public String toString() {
        return "UserConfig{username='" + username + "', fontSize=" + fontSize + "}";
    }
}