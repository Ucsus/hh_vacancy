package ru.testmepls;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    final SelenideElement
            mainHeader = $(".bloko-header-promo-3"),
            vacancyOfDay = $("[data-qa=vacancy-item-desktop]");

}
