package ru.testmepls.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;


public class MainPageElements {
    public final SelenideElement
            mainHeader = $(".bloko-header-promo-3"),
            footer = $(".bloko-gap_bottom"),
            vacancyOfDay = $("[data-qa=vacancy-item-desktop]"),
            helpMenu = $("[data-qa=mainmenu_help]"),
            writeToUs = $("[data-qa=mainmenu_writeToUs]"),
            findVacancy = $("[data-qa=search-input]"),
            regionButton = $("[data-qa=region-clarification-clarify]"),
            regionHeader = $(".prosper-field__label"),
            vacnciesCatalogHeader = $("[data-qa=vacancies-catalog-header]"),
            findWorkButton = $("[data-qa=search-button]");

}
