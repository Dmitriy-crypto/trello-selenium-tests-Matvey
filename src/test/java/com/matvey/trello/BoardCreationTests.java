package com.matvey.trello;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!isAvatarPresent()) {
            trelloLoginButton();
            fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            pause(5000);}
    }

    @Test//(priority = 1)
    public void testCreateBoardFromMainPage() throws InterruptedException {


        createBoardFromMainPage();
//        int boardsCount = getBoardsCount()
//        while(boardsCount>5){deleteBoards
//
//        }
    }

    @Test//(priority = 2)
    public void testCreateBoardFromHeader() throws InterruptedException {
        int before = getBoardsCount();
        addNewBoard();
        fillBoardName("Board from header"+System.currentTimeMillis());
        chooseAddPublicBoard();
        submitCreateBoard();
        pause(5000);
        returnToHomePage();
        int after = getBoardsCount();
        Assert.assertEquals(after,before+1);

    }
}

// Tried to cut logout() method and paste it into Afterclass in TestBase - test drops at the very end


