public class User{
    private String name;
    private String email;
    private String password;
    private boolean isVIP;

    public User(String name, String email, String password, boolean isVIP){

        this.name = name;
        this.email = email;
        this.password = password;
        this.isVIP = isVIP;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }

    public boolean isVIP(){
        return isVIP;
    }
}