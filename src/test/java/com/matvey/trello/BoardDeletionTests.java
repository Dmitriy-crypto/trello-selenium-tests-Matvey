package com.matvey.trello;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!isAvatarPresent()) {
            trelloLoginButton();
            fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            pause(8000);

        }
    }

    @Test
    public void testDeleteLastBoard() throws InterruptedException {
        createBoardFromMainPage();
        deleteLastBoard();
    }

    @AfterClass
    public void postActions() throws InterruptedException {
        int boardsCount = getBoardsCount();
        while(boardsCount>4){
            deleteLastBoard();
            boardsCount = getBoardsCount();
        }
}

}


