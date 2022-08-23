package webdriverfactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.Locale;


public class WebDriverFactory {
    public static WebDriver getDriver(){
        //-Dbrowser = chrome/opera/mozila/edge
      String browser =  System.getProperty("browser");
      //-Dheadless=true, false

        if(browser == null){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }

        switch (browser.trim().toLowerCase(Locale.ROOT)) {
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
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }

            default -> System.out.println("Доступные браузеры для запуска -Dbrowser = chrome/opera/mozila/edge");
        }
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

}

