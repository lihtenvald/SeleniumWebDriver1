package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class AvtomationPracticeForm {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {

        String browser = System.getProperty("browser");
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        assert driver != null;
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        try {
            driver.get("https://demoqa.com/automation-practice-form");
        } catch (Exception ignored) {
        }
    }

    @Test
    public void Test1() {

        Assertions.assertEquals("DEMOQA", driver.getTitle());

        driver.findElement(By.id("firstName")).sendKeys("Valya");
        driver.findElement(By.id("lastName")).sendKeys("Lihtenvald");
        driver.findElement(By.id("userEmail")).sendKeys("lihtenvald@mail.ru");
        WebElement gender = driver.findElement(By.xpath("//div[@id='genterWrapper']//div[2]//div[2]/label"));
        gender.click();
        driver.findElement(By.id("userNumber")).sendKeys("8923898989");

        driver.findElement(By.id("dateOfBirthInput")).click();
        WebElement MonthBirth = driver.findElement(By.cssSelector("[class='react-datepicker__month-select']"));
        Select selectMonthBirth = new Select(MonthBirth);
        selectMonthBirth.selectByVisibleText("June");
        WebElement YearBirth = driver.findElement(By.cssSelector("[class='react-datepicker__year-select']"));
        Select selectYearBirth = new Select(YearBirth);
        selectYearBirth.selectByVisibleText("1995");
        WebElement dayBirth = driver.findElement(By.cssSelector("[class='react-datepicker__day react-datepicker__day--010 react-datepicker__day--weekend']"));
        dayBirth.click();

        WebElement subjeck = driver.findElement(By.xpath("//*[@id='subjectsContainer']//div[1]//div[1]//div[1]//input"));
        subjeck.sendKeys("Maths");
        subjeck.sendKeys(Keys.RETURN);

        driver.findElement(By.xpath("//div[@id='hobbiesWrapper']//div[2]//div[2]/label")).click();
        driver.findElement(By.xpath("//div[@id='hobbiesWrapper']//div[2]//div[1]/label")).click();

        File file = new File("src/file.txt");
        String filePath = file.getAbsolutePath();
        WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
        fileInput.sendKeys(filePath);

        driver.findElement(By.id("currentAddress")).sendKeys("Obskaya 111");
        driver.findElement(By.xpath("//div[@id='stateCity-wrapper']/div[2]//div[@class=' css-tlfecz-indicatorContainer']")).click();

        driver.findElement(By.xpath("//div[@id='state']")).click();
        WebElement elementState = driver.findElement(By.xpath("//div[@id='state']//div[1]//div[1]//div[2]//div[1]//input"));
        elementState.sendKeys("Haryana");
        elementState.sendKeys(Keys.RETURN);

        driver.findElement(By.xpath("//div[@id='city']")).click();
        WebElement elementCity = driver.findElement(By.xpath("//div[@id='city']//div[1]//div[1]//div[2]//div[1]//input"));
        elementCity.sendKeys("Panipat");
        elementCity.sendKeys(Keys.RETURN);

        driver.findElement(By.id("submit")).click();

        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(driver.findElement(By.xpath("//tr[1]//td[2]")).getText()).isEqualTo("Valya Lihtenvald");
        softAssert.assertThat(driver.findElement(By.xpath("//tr[2]//td[2]")).getText()).isEqualTo("lihtenvald@mail.ru");
        softAssert.assertThat(driver.findElement(By.xpath("//tr[3]//td[2]")).getText()).isEqualTo("Female");
        softAssert.assertThat(driver.findElement(By.xpath("//tr[4]//td[2]")).getText()).isEqualTo("8923898989");
        softAssert.assertThat(driver.findElement(By.xpath("//tr[5]//td[2]")).getText()).isEqualTo("10 June,1995");
        softAssert.assertThat(driver.findElement(By.xpath("//tr[6]//td[2]")).getText()).isEqualTo("Maths");
        softAssert.assertThat(driver.findElement(By.xpath("//tr[7]//td[2]")).getText()).isEqualTo("Reading, Sports");
        softAssert.assertThat(driver.findElement(By.xpath("//tr[8]//td[2]")).getText()).isEqualTo("file.txt");
        softAssert.assertThat(driver.findElement(By.xpath("//tr[9]//td[2]")).getText()).isEqualTo("Obskaya 111");
        softAssert.assertThat(driver.findElement(By.xpath("//tr[10]//td[2]")).getText()).isEqualTo("Haryana Panipat");

        softAssert.assertAll();
    }

    @AfterEach

    public void tearDown() {
        driver.quit();
    }
}

