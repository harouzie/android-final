package tdtu.fit.hrz.flashcards.objects;

public class UserAccount {
    private String username, password, displayName;

    public UserAccount() {};

    public UserAccount(String username, String password, String displayName) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
    }

    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
    public String getDisplayName() {
        return this.displayName;
    }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
