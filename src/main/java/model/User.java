package model;

public class User {
    private int id;
    private String name;
    private String pass;

    //空のコンストラクタ
    public void user() {
    }

    //コンストラクタ
    public void user(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    //getter & setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    //
    public String getErrorMessage() {
        String actual = "ユーザuserNameは必須項目です.";
        return actual;
    }
}
