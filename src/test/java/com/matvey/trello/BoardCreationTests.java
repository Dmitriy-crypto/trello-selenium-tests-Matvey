package com.matvey.trello;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!isAvatarPresent()) {
            loginTrello("mebelyakov@gmail.com","7Ig%20K8");
        }
    }

    @Test//(priority = 1) не фурычит :) запускает сначала header
    public void testCreateBoardFromMainPage() throws InterruptedException {
        trelloLoginButton();
        fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
        clickCreateNewBoard();
        fillBoardName("Board from main page"+System.currentTimeMillis());
        choosePublicBoard();
        submitCreateBoard();

    }

    @Test//(priority = 2)
    public void testCreateBoardFromHeader() throws InterruptedException {
        trelloLoginButton();
        fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
        addNewBoard();
        fillBoardName("Board from header"+System.currentTimeMillis());
        chooseAddPublicBoard();
        submitCreateBoard();

    }

}

// Tried to cut logout() method and paste it into Afterclass in TestBase - test drops at the very end


