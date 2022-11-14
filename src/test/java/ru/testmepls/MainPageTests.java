package ru.testmepls;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.testmepls.data.Locale;
import ru.testmepls.pages.MainPageElements;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MainPageTests extends TestBase {
    private final MainPageElements mainPageElements = new MainPageElements();

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
            mainPageElements.mainHeader.shouldHave(exactText("Работа найдется для каждого"));
        });
        step("Проверяем блок Вакансии дня", () -> {
            mainPageElements.vacancyOfDay.shouldHave(text("Вакансии дня"));
        });
    }

    static Stream<Arguments> siteMenuText() {
        return Stream.of(
                Arguments.of(Locale.EN, List.of("About the company", " Our vacancies", " Advertising on the website", " Software requirements")),
                Arguments.of(Locale.RU, List.of("О компании", " Наши вакансии", " Реклама на сайте", " Требования к ПО"))
        );
    }

    @DisplayName("Проверка меню")
    @MethodSource
    @ParameterizedTest(name = "Проверка отображения названия меню для локали {0}")
    void siteMenuText(Locale locale, List<String> menuTexts) {
        step("Открываем главную страницу", () -> {
            open("/");
        });
        step("Скроллим вниз", () -> {
            mainPageElements.footer.scrollTo();
        });
        step("Меняем локаль", () -> {
            $("[data-qa=change-locale-" + locale.name() + "]").click();
        });
        step("Проверям первые 4 элемента меню", () -> {
            $$(".nav-items-item--nZiKRBLX4HL9V421NL_r a")
                    .filter(visible)
                    .first(4)
                    .shouldHave(texts(menuTexts));
        });

    }

    @DisplayName("Проверка меню второго уровня")
    @Test
    public void helpMenuTest() {
        step("Открываем главную страницу", () -> {
            open("/");
        });
        step("Кликаем на меню", () -> {
            mainPageElements.helpMenu.click();
        });
        step("Проверяем содержимое меню", () -> {
            mainPageElements.writeToUs.shouldHave(exactText("Пишите нам"));
        });
    }

    @DisplayName("Проверка блока мессенджеров")
    @Test
    public void chatButtonsTest() {
        step("Открываем главную страницу", () -> {
            open("/");
        });
        step("Скроллим вниз", () -> {
            mainPageElements.footer.scrollTo();
        });
        step("Проверяем количество кнопок", () -> {
            ElementsCollection chatButtonsSize = ($$(".chat-bot-messengers--oZ85ZYoAs5TD6MNYP7O5 a")
                    .filter(visible));
            chatButtonsSize.shouldHave(CollectionCondition.size(3));
        });
    }

    @DisplayName("Поиск вакансии")
    @Test
    public void findVacancyTest() {
        step("Открываем главную страницу", () -> {
            open("/");
        });
//        step("Меняем регион", () -> {
//            mainPageElements.regionButton.click();
//            mainPageElements.regionHeader.shouldHave(exactText("Укажите город, который требуется найти:"));
//            $(byText("Россия")).click();
//        });
        step("Ищем вакансию", () -> {
            mainPageElements.findVacancy.setValue("QA engineer");
            mainPageElements.findWorkButton.click();
        });
        step("Проверям отображение вакансий", () -> {
            mainPageElements.vacnciesCatalogHeader.shouldHave(text("Работа QA engineer"));
        });
    }
}