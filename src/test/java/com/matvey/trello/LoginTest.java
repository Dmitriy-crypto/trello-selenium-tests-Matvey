package com.matvey.trello;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @Test
    public void testLogInWithAtlassianAcc() throws InterruptedException {
        clickLoginLink("[href='/login']");
    }


    public void clickLoginLink(String selector) throws InterruptedException {
        click(selector);
        fillLoginForm("meliebling@gmail.com", "7Ig%20K8");

        }

    public void fillLoginForm(String login, String password) throws InterruptedException {
        typeLogin(login);
        pause(5000);
        ifClause(password);
    }

}


//trello buttons to create a board:

//.board-tile mod-add - Create new board button or //span[@name='add'] and then ???

////input[@placeholder='Add board title'] - Add board title


////button[@class='subtle-chooser-trigger unstyled-button vis-chooser-trigger'] -
//Dropdown menu to choose public or private

//.icon-sm.icon-public - Public button

////input[@type='submit'] - Yes, make board public

////button[@type='submit'] - Create board
