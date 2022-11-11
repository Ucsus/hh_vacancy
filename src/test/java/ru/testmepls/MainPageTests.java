package ru.testmepls;

import com.codeborne.selenide.CollectionCondition;
import com.sun.tools.javac.util.List;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.testmepls.data.Locale;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MainPageTests extends TestBase {
    private final MainPage mainPage = new MainPage();

    @Test
    @Feature("Тест главной страницы")
    @Story("Проверяем заголовок и блок Вакансии дня")
    @Owner("Ucsus")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "hh.ru", url = "https://hh.ru")
    @DisplayName("Проверка блоков")

    public void blocksTest() {
        step("Открываем главную страницу", () -> {
            open("/");
        });
        step("Проверяем заголовок", () -> {
            mainPage.mainHeader.shouldHave(exactText("Работа найдется для каждого"));
        });
        step("Проверяем блок Вакансии дня", () -> {
            mainPage.vacancyOfDay.shouldHave(exactText("Вакансии дня"));
        });
    }

    static Stream<Arguments> siteMenuText() {
        return Stream.of(
                Arguments.of(Locale.EN, List.of("About the company", "Our vacancies", "Advertising on the website", "Software requirements")),
                Arguments.of(Locale.RU, List.of("О компании", "Наши вакансии", "Реклама на сайте", "Требования к ПО"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка отображения названия меню для локали {0}")
    void siteMenuText(Locale locale, List<String> menuTexts) {
        open("/");
        $(".bloko-gap_bottom").scrollTo();
        $("[data-qa=change-locale-" + locale.name() + "]").click();
        $$(".nav-items-item--nZiKRBLX4HL9V421NL_r a").filter(visible).first(4).shouldHave(CollectionCondition.textsInAnyOrder(menuTexts));
    }

}
