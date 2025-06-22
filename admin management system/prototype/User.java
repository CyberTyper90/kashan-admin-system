public class User {
    private String name;
    private String username;
    private String password;
    private String role;
    private boolean banned;

    public User(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.banned = false;
    }

    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public boolean isBanned() { return banned; }

    public void setBanned(boolean banned) { this.banned = banned; }
}
