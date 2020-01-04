package com.matvey.trello;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if (!isAvatarPresent()) {
            trelloLoginButton();
            fillLoginForm("meliebling@gmail.com", "7Ig%20K8");
            pause(5000);
        }
    }

    @Test
    public void testDeleteBoard() throws InterruptedException {
        int before = getBoardsCount();
        if (before <= 1) {
            System.out.println("No boards found");
        } else {
            clickLastBoard();
            if (!moreButtonPresent()) {
                goBack();
                moreButton();
            } else
                moreButton();
            permanentlyDeleteBoard();
            pause(3000);
            returnToHomePage();
            int actualRes = getBoardsCount();
            int expectedRes = before - 1;
            Assert.assertEquals(actualRes, expectedRes);
        }
    }


}


// [title='Go back.']
// .board-menu-navigation-item-link.js-open-more
// .board-menu-navigation-item-link.js-close-board
// [value='Close']
// pause()
// .quiet.js-delete
// [value='Delete']
