package controller;

import model.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoginTest {

    @Test
    public void userNameが空の時にエラーメセージを取得できる() {
        User user = new User();
        user.setName("");
        user.setPass("pass");
        String expected = "ユーザuserNameは必須項目です.";
        String actual = user.getErrorMessage();
        assertThat(actual, is(expected));
    }

}
