package com.matvey.trello.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BoardHelper extends HelperBase{


    public BoardHelper(WebDriver wd) {
        super(wd);
    }

    public void clickCreateNewBoard() {
        click(By.xpath("//div[@class='board-tile mod-add']"));
    }

    public void choosePrivateBoard() {
        click(By.xpath("//button[@class='subtle-chooser-trigger unstyled-button vis-chooser-trigger']"));
        click(By.cssSelector("//div[@class='pop-over mod-no-header is-shown']//li[1]"));
    }

    public void choosePublicBoard() throws InterruptedException {
        click(By.xpath("//button[@class='subtle-chooser-trigger unstyled-button vis-chooser-trigger']"));
        click(By.xpath("//div[@class='pop-over mod-no-header is-shown']//li[2]"));
        pause(2000);
        clickSubmitPublicBoard();
    }

    public void clickSubmitPublicBoard() {
        click(By.cssSelector(".js-confirm.full.primary"));
    }

    public void fillBoardName(String boardName) {
        click(By.xpath("//input[@placeholder='Add board title']"));
        type(By.xpath("//input[@placeholder='Add board title']"), boardName);
    }

    public void submitCreateBoard() {
        click(By.cssSelector("[data-test-id='create-board-submit-button']"));    }

    public void chooseAddPublicBoard() {
        click(By.cssSelector("._1Lkx3EjS3wCrs7"));
        click(By.xpath("//div[@id='layer-manager-popover']//li[2]//button[1]"));
        click(By.cssSelector("._3UeOvlU6B5KUnS._2MgouXHqRQDP_5._3ZPeWh5QQj47DA"));

    }

    public void addNewBoard() {
        click(By.xpath("//span[@name='add']"));
        click(By.xpath("//span[@name='board']/..//p"));
    }

    public int getBoardsCount(){
            return wd.findElements(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).size();
    }

    public void permanentlyDeleteBoard() throws InterruptedException {
        click(By.cssSelector(".board-menu-navigation-item-link.js-close-board"));
        click(By.cssSelector("[value='Close']"));
        pause(3000);
        click(By.cssSelector(".quiet.js-delete"));
        click(By.cssSelector("[value='Delete']"));
    }

    public void clickLastBoard() {
        wd.findElements(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).
                get(wd.findElements(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).size() - 2).click();
    }

    public void deleteLastBoard() throws InterruptedException {
        int before = getBoardsCount();
        if (before <= 1) {
            System.out.println("No boards found");
        } else {
            clickLastBoard();
            if(!menuButtonPresent()){
                clickMenuButton();
                moreButton();
            }else
            if (moreButtonPresent()) {
                moreButton();
                permanentlyDeleteBoard();
            }
            else
                goBack();
            moreButton();
            permanentlyDeleteBoard();
            pause(3000);
            returnToHomePage();
            int actualRes = getBoardsCount();
            int expectedRes = before - 1;
            Assert.assertEquals(actualRes, expectedRes);
        }
    }

    public void createBoardFromMainPage() throws InterruptedException {
        int before = getBoardsCount();
        clickCreateNewBoard();
        fillBoardName("Board from main page"+System.currentTimeMillis());
        choosePublicBoard();
        pause(2000);
        submitCreateBoard();
        pause(2000);
        returnToHomePage();
        int actualRes = getBoardsCount();
        int expectedRes = before+1;
        Assert.assertEquals(actualRes,expectedRes);
    }

}
