package tests;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {

    @Test
    public void blockedUser() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void usernameWithACapitalLetter() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void emptyUsername() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Username is required");
    }

    @Test
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");

        assertTrue(loginPage.errorMsgVisible());
        assertEquals(loginPage.getErrorMsgText(), "Epic sadface: Password is required");
    }

    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertEquals(productsPage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        assertTrue(productsPage.getCurrentUrl().contains("inventory.html"));

        assertTrue(productsPage.pageTitleVisible());
        assertEquals(productsPage.getTitleText(), "Products");
    }
}
