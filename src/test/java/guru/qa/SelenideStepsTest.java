package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideStepsTest extends TestBase {
    private static final String repository = "eroshenkoam/allure-example";
    private static final String nameIssue = "С Новым Годом (2022)";

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу GitHub", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий" + repository, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(repository);
            $(".header-search-input").pressEnter();
        });
        step("Кликаем по ссылке репозитория" + repository, () -> {
            $(By.linkText(repository)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с названием" + nameIssue, () -> {
            $(withText(nameIssue)).should(Condition.exist);
        });
    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Создание Issue")
    @Owner("chekulaevda")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание Issue пользователем")
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(repository);
        steps.clickOnRepository(repository);
        steps.openIssueTab();
        steps.searchNameIssue(nameIssue);

    }
}
