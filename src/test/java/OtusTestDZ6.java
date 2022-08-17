import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import webDriverFactory.WebDriverFactory;

import java.util.concurrent.TimeUnit;


public class OtusTestDZ6 {

    public ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private WebDriver driver;
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(OtusTestDZ6.class);

    @Before
    public void setUp() {

//        driver = WebDriverFactory.getDriver();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void setDow() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void otusTestFill() throws InterruptedException {
        //открыть отус
        driver.get(cfg.url());
        //авторизоваться на сайте
        auth();
        //войти в личный кабинет
        enterToLK();
        //зайти в раздел о себе заполнить данными +2 контакта
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_fname']")), "Артём");
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_fname_latin']")), "Artem");
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_lname']")), "Игнатьев");
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_lname_latin']")), "Ignatev");
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_blog_name']")), "АртемБлог");
        enterTextArea(driver.findElement(By.xpath("//input[@name='date_of_birth']")), cfg.dateOfBirthday());
        driver.findElement(By.xpath("//input[@name='country']/following-sibling::div")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Россия')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='city']/following-sibling::div")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Самара')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='english_level']/following-sibling::div")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Средний (Intermediate)')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Да')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Способ связи')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Skype')]")).click();
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_contact-0-value']")), cfg.skypeLogin());
        driver.findElement(By.xpath("//button[contains(text(),'Добавить')]")).click();
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_contact-1-value']")), cfg.telegramPhone());
        driver.findElement(By.xpath("//span[contains(text(),'Способ связи')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),' Тelegram')]")).click();
        driver.findElement(By.xpath("//select[@id='id_gender']")).click();
        driver.findElement(By.xpath("//option[contains(text(),'Мужской')]")).click();
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_company']")), "ООО 42");
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_work']")), "Инженер");
        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и продолжить')]")).submit();
        String cookiesGet = String.valueOf(driver.manage().getCookies());
        logger.info(cookiesGet);
        logger.info("Кейс пройден");
    }

    @Test
    public void otusTestCheck() throws InterruptedException {

        driver = new ChromeDriver();
        driver.get(cfg.url());
        //авторизоваться на сайте
        auth();
        //войти в личный кабинет
        enterToLK();
        //проверить чт ов разделе о себе указываются правильные данные
        checkTextValueArea(driver.findElement(By.xpath("//input[@id='id_fname']")), "Артём");
        checkTextValueArea(driver.findElement(By.xpath("//input[@id='id_fname_latin']")), "Artem");
        checkTextValueArea(driver.findElement(By.xpath("//input[@id='id_lname']")), "Игнатьев");
        checkTextValueArea(driver.findElement(By.xpath("//input[@id='id_lname_latin']")), "Ignatev");
        checkTextValueArea(driver.findElement(By.xpath("//input[@id='id_blog_name']")), "АртемБлог");
        checkTextValueArea(driver.findElement(By.xpath("//input[@name='date_of_birth']")), "21.09.1995");
        checkTextTextArea(driver.findElement(By.xpath("//div[contains(text(),'Россия')]")), "Россия");
        checkTextTextArea(driver.findElement(By.xpath("//div[contains(text(),'Самара')]")), "Самара");
        checkTextTextArea(driver.findElement(By.xpath("//div[contains(text(),'Средний (Intermediate)')]")), "Средний (Intermediate)");
        checkTextTextArea(driver.findElement(By.xpath("//div[contains(text(),'Skype')]")), "Skype");
        checkTextValueArea(driver.findElement(By.xpath("//input[@id='id_contact-0-value']")), "artline51");
        checkTextValueArea(driver.findElement(By.xpath("//input[@id='id_contact-1-value']")), "+79091321234");
        checkTextValueArea(driver.findElement(By.xpath("//input[@id='id_company']")), "ООО 42");
        checkTextValueArea(driver.findElement(By.xpath("//input[@id='id_work']")), "Инженер");
        String cookiesGet = String.valueOf(driver.manage().getCookies());
        logger.info(cookiesGet);
        logger.info("Кейс пройден");

    }


    private void auth() {
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        driver.findElement(By.cssSelector("button.header2__auth")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath("//form[@action = '/login/']//input[@name='email']")).sendKeys(cfg.login());
        driver.findElement(By.xpath("//form[@action = '/login/']//input[@name='password']")).sendKeys(cfg.password());
        driver.findElement(By.xpath("//form[@action = '/login/']//button[@type='submit']")).click();
    }

    private void enterToLK() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.get("https://otus.ru/lk/biography/personal/");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void enterTextArea(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    private void checkTextValueArea(WebElement element, String expectedText) {
        Assert.assertEquals(expectedText, element.getAttribute("value"));
    }

    private void checkTextTextArea(WebElement element, String expectedText) {
        Assert.assertEquals(expectedText, element.getText());
    }

}
