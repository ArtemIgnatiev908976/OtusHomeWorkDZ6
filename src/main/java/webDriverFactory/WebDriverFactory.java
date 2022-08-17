package webDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.Locale;


public class WebDriverFactory {
    public static WebDriver getDriver(){
        //-Dbrowser=chrome, opera, safari, mozila
      String browser =  System.getProperty("browser").trim().toLowerCase(Locale.ROOT);
      //-Dheadless=true, false
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case "opera" -> {
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            }
            case "mozila" -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            default -> System.out.println("Запустить с параметром -Dbrowser = chrome/opera/safari/mozila");
        }
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

}

