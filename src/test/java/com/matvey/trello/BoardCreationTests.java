package com.matvey.trello;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (isAvatarPresent()) {
            logout();
        }
    }

    @Test//(priority = 1) doesn't work
    public void testCreateBoardFromMainPage() throws InterruptedException {
        trelloLoginButton();
        fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
        clickCreateNewBoard();
        fillBoardName("Board from main page");
        choosePublicBoard();
        submitCreateBoard();

    }

    @Test//(priority = 2) doesn't work
    public void testCreateBoardFromHeader() throws InterruptedException {
        trelloLoginButton();
        fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
        addNewBoard();
        fillBoardName("Board from header");
        chooseAddPublicBoard();
        submitCreateBoard();

    }

}




