package com.example.xpathtest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.youtube.com/");
        System.out.println("Начало проверки");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        System.out.println("Проверка окончена");
    }

    @Test
    @DisplayName("Поиск видео")
    public void search() {
        WebElement searchField = driver.findElement(By.xpath("//input[@id='search']"));
        searchField.sendKeys("Never Gonna Give You Up");
        WebElement searchButton = driver.findElement(By.xpath("//button[@id='search-icon-legacy']"));
        searchButton.click();
        assertTrue(driver.findElement(By.xpath("//a[@title='Rick Astley - Never Gonna Give You Up (Official Music Video)']")).isDisplayed(),
                "Запрос не обнаружен в выдаче");
    }
    @Test
    @DisplayName("Отображение названия при проигрывании")
    public void videoTitle() {
        WebElement searchField = driver.findElement(By.xpath("//input[@id='search']"));
        searchField.sendKeys("Never Gonna Give You Up");
        WebElement searchButton = driver.findElement(By.xpath("//button[@id='search-icon-legacy']"));
        searchButton.click();
        WebElement video = driver.findElement(By.xpath("//yt-formatted-string[@class='style-scope ytd-video-renderer'][1]"));
        video.click();
        WebElement title = driver.findElement(By.xpath("//*[contains(text(),'Rick Astley - Never Gonna Give You Up (Official Music Video)')][1]"));
        assertTrue(title.isDisplayed(),"Название не отображается");
    }
}
