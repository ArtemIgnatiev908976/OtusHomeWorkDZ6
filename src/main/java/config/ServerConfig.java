package config;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")

public interface  ServerConfig extends Config {
    @Key("url")
    String url();

    @Key("login")
    String login();

    @Key("password")
    String password();

    @Key("dateOfBirthday")
    String  dateOfBirthday();
    @Key("skypeLogin")
    String  skypeLogin();
    @Key("telegramPhone")
    String  telegramPhone();

}
