package project.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;


public class MainPage {
    private final WebDriver driver;
    //Часть названия класса аккардиона
    final String accordionItem = "accordion__heading-";

    //Нахождение кнопка для принятия куки("Да все привыкли")
    public static final By cookieButton = By.xpath(".//*[@id='rcc-confirm-button']");

    //Кнопка Заказать в хедере
    public static final By orderButtonTop = By.xpath(".//div[contains(@class,'Header_Nav')]//button[text()='Заказать']");

    //Кнопка Заказать на странице
    public static final By orderButtonBody = By.xpath(".//button[contains(@class,'Button_Middle')]");

    //Второй элемент в аккардионе вопросов
    public static final By secondAccordionItem = By.xpath("(.//div[@class='accordion__item'])[2]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Нажатие на кнопку "Да все привыкли"
    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    //Нажатие на вопрос в аккардионе
    public void clickAccordionItem(String id) {
        driver.findElement(By.id(accordionItem+id)).click();
    }

    //Нажатие на кнопку заказа
    public void clickOrderButton(By orderButton) {
        driver.findElement(orderButton).click();
    }

    //Скролл к второму вопросу в скролеере(В ФФ первый элемент перекрывался кнопкой Заказать)
    public void scrollToElement() {
        WebElement element = driver.findElement(secondAccordionItem);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }








}