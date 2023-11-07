package tdtu.fit.hrz.flashcards.objects;

public class UserAccount {
    String username, password;

    public UserAccount(String input_username, String input_password) {
        this.username = input_username;
        this.password = input_password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
