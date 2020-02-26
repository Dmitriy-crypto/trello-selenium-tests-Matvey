package com.matvey.trello.utils;

import com.matvey.trello.manager.ApplicationManager;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.matvey.trello.tests.TestBase.app;

public class Listener implements ITestListener {

    Logger logger = LoggerFactory.getLogger(Listener.class);

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getName()+" Passed successfully");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error(result.getName()+" Failed\n" +
     result.getThrowable().toString());
        app.takeScreenshot();


    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }



}
