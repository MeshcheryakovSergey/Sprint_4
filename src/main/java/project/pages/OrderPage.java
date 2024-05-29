package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {

    //поле Имя
    private final By nameField = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    //поле Фамилия
    private final By surnameField = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    //поле с адресом заказа
    private final By addressField = By.xpath(".//input[contains(@placeholder, 'куда привезти заказ')]");
    //поле с выбором станции метро
    private final By metroField = By.xpath(".//input[@class='select-search__input']");
    //поля ввода номера телефона
    private final By telephoneField = By.xpath(".//div[5]/input[contains(@class, 'Input_Input__1iN_Z')]");
    //кнопка Далее
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //ввести имя
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    //ввести фамилию
    public void enterSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }
    //ввести адрес
    public void enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    //выбрать станцию метро
    public void clickMetroStation(String metroStation) {
        driver.findElement(metroField ).click();
        driver.findElement(By.xpath(".//div[@class='select-search__select']//*[text()='"+metroStation+"']")).click();
    }
    //ввести номер телефона
    public void enterPhoneNumber(String number) {
        driver.findElement(telephoneField).sendKeys(number);
    }
    //нажать на кнопку далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

}