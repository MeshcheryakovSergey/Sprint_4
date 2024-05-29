import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import project.EnvConfig;
import project.pages.OrderPage;
import project.pages.MainPage;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import project.pages.RentPage;


import java.time.Duration;

import static project.pages.MainPage.orderButtonTop;
import static project.pages.MainPage.orderButtonBody;


@RunWith(Parameterized.class)
public class makeOrder {

    private WebDriver driver;
    private static By orderButton;
    private static String name;
    private static String surname;
    private static String address;
    private static String metroStation;
    private static String phoneNumber;
    private static String dayWhatIWant;
    private static String comment;

    public makeOrder(By orderButton, String name, String surname, String address, String metroStation, String phoneNumber, String dayWhatIWant, String comment) {
        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.dayWhatIWant = dayWhatIWant;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object [][] getCredentials(){
        return new Object[][] {
                {orderButtonTop, "Иван", "Иванов", "ул.Пущкина", "Спортивная", "88005553535", "20.03.2024", "Без опозданий" },
                {orderButtonBody, "Самсон", "Самсонов", "ул.Фрунзенская", "Беговая", "88009379992", "02.02.2024", "" }
        };
    }
    @Before
    public void setUp(){
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT));
        MainPage mainPage = new MainPage(driver);
        driver.get(EnvConfig.BASE_URL);
        mainPage.clickCookieButton();
    }
    @Test
    public void mainFlowWithValidDataIsSuccessfully() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButton(orderButton);
        //раздел Для кого самокат
        OrderPage orderPage = new OrderPage(driver);
        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAddress(address);
        orderPage.clickMetroStation(metroStation);
        orderPage.enterPhoneNumber(phoneNumber);
        orderPage.clickNextButton();
        //раздел Про аренду
        RentPage rentPage = new RentPage(driver);
        rentPage.chooseDate(dayWhatIWant);
        rentPage.chooseRentalPeriod();
        rentPage.chooseColor();
        rentPage.writeComment(comment);
        rentPage.clickFinalOrderButton();
        rentPage.confirmOrder();
        rentPage.checkOrderComplete();
   }
    @After
    public void tearDown() {
        driver.quit();
    }

}