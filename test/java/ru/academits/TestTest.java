package ru.academits;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestTest {

    @Test
    public void Test1() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        try {
            driver.get("https://demoqa.com/automation-practice-form");
        } catch (Exception ignored){

        }
        Assertions.assertEquals("DEMOQA", driver.getTitle());

        driver.quit();
    }


}
