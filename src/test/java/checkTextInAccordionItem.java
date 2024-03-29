import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import project.EnvConfig;
import project.pages.MainPage;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class checkTextInAccordionItem {
        public WebDriver driver;
        private final String id;
        private final String questionText;
        private final String answerText;

        public checkTextInAccordionItem(String id, String questionText,String answerText) {
            this.id=id;
            this.questionText = questionText;
            this.answerText = answerText;
        }

        @Parameterized.Parameters
        public static Object[][] getCredentials() {
            return new Object[][] {
                    {"0", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                    {"1", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                    {"2", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                    {"3", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                    {"4", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                    {"5", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                    {"6", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                    {"7", "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
            };
        }

        @Before
        public void setUp() {
            //driver = new ChromeDriver();
            driver = new FirefoxDriver();
            MainPage main = new MainPage(driver);
            driver.get(EnvConfig.BASE_URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT));
            main.clickCookieButton();
            main.scrollToElement();
        }

        //Тест для проверки текста в вопросе
        @Test
        public void checkTextInQuestion() {
            assertEquals(questionText, driver.findElement(By.id("accordion__heading-"+id)).getText());
        }

        //Тест для проверки текста в ответе
        @Test
        public void checkTextInAnswer() {
            MainPage main = new MainPage(driver);
            main.clickAccordionItem(id);
            String element = driver.findElement(By.id("accordion__panel-" + id)).getText();
            assertEquals(answerText, element);
        }

        @After
        public void tearDown() {
            driver.quit();
    }
}

