public abstract class User {
    protected String username;
    protected String password;
    protected UserType userType;

    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    // Геттер для имени пользователя
    public String getUsername() {
        return username;
    }

    // Геттер для пароля
    public String getPassword() {
        return password;
    }

    // Геттер для типа пользователя
    public UserType getUserType() {
        return userType;
    }

    // Здесь могут быть другие методы, специфичные для класса User
}