package ru.testmepls.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public final SelenideElement
            mainHeader = $(".bloko-header-promo-3");
    public final SelenideElement vacancyOfDay = $("[data-qa=vacancy-item-desktop]");

}
