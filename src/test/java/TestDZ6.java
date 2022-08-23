
import config.ServerConfig;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriverfactory.WebDriverFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestDZ6 {


    private String login;
    private String password;
    private String url;


    public ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private WebDriver driver;
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(TestDZ6.class);

    @Before
    public void setUp() {

        login = System.getProperty("login");
        password = System.getProperty("password");
        url = System.getProperty("url");
        driver = WebDriverFactory.getDriver();
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //открыть отус
        driver.get(url);
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

        By country = By.xpath("//input[@name='country']/following-sibling::div");
        getElementClickable(country).click();

        By russia = By.xpath("//button[contains(text(),'Россия')]");
        getElementClickable(russia).click();

        By city = By.xpath("//input[@name='city']/following-sibling::div/span");
        driver.findElement(city).click();

        By samara = By.xpath("//button[contains(text(),'Самара')]");
        getElementClickable(samara).click();

        By englishLvl = By.xpath("//input[@name='english_level']/following-sibling::div");
        getElementClickable(englishLvl).click();


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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        driver.findElement(By.cssSelector("button.header2__auth")).click();
        WebElement loginForm = driver.findElement(By.xpath("//form[@action = '/login/']"));
        loginForm.findElement(By.xpath(".//input[@name='email']")).sendKeys(login);
        loginForm.findElement(By.xpath(".//input[@name='password']")).sendKeys(password);
        loginForm.findElement(By.xpath(".//button[@type='submit']")).click();
    }

    private void enterToLK() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//p[contains(text(),'Artem')]"));
        builder.moveToElement(element).build().perform();
        driver.findElement(By.xpath("//a[contains(text(),'Личный кабинет')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'О себе')]")).click();
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


    private WebElement getElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


}
