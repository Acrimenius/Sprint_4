import PageObject.OrderElements;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;


public class OrderElementsTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();

    }

    @Test

    public void createOrder1() {

// время ожидания 3 сек

        OrderElements objOrderElements = new OrderElements(driver);
        objOrderElements.open();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("App_CookieButton__3cvqF")));
        objOrderElements.clickCookieButton();
// клик на кнопку "Заказать"-------------------------------
        objOrderElements.clickTopOrderButton();
// заполнение первой страницы заказа
        objOrderElements.fillFirstPage(
                "Андрей",
                "Иванов",
                "Южная 15, 54",
                "+79173232323");
// заполнение второй страницы заказа
        objOrderElements.fillSecondPage(
                "Домофон не работает");
// подтверждение оформления заказа
        objOrderElements.clickYesButton();
        String actualOrderText = objOrderElements.checkOrderIsDone();
        String exceptedOrderText = "Заказ оформлен";
        MatcherAssert.assertThat(actualOrderText, containsString(exceptedOrderText));
    }
    @Test

    public void createOrder2() {
        OrderElements objOrderElements = new OrderElements(driver);
        objOrderElements.open();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("Home_FinishButton__1_cWm")));
        objOrderElements.clickCookieButton();
// Скролл вниз
        WebElement element = driver.findElement(By.id("accordion__heading-0"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
// Оформление заказа через нижнюю кнопку --------------------------
        objOrderElements.clickBottomOrderButton();
        objOrderElements.fillFirstPage(
                "Павел",
                "Андреивич",
                "Комсомольская 22, 5",
                "+79171212312");
        objOrderElements.fillSecondPage(
                "Communication is disabled");
        objOrderElements.clickYesButton();
        String actualOrderText = objOrderElements.checkOrderIsDone();
        String exceptedOrderText = "Заказ оформлен";
        MatcherAssert.assertThat(actualOrderText, containsString(exceptedOrderText));
    }

    @After
    public void teardown() {
        // Закрытие браузера
        driver.quit();
    }

}
