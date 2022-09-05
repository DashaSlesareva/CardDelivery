package ru.netologu.web;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class CardDeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void positiveTest() {

        String planningDate = generateDate(4);
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] .input__control").sendKeys(BACK_SPACE);
        $("[data-test-id='date'] .input__control").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Андрей Иванов");
        $("[data-test-id='phone'] input").setValue("+79670789786");
        $("[data-test-id='agreement'] span").click();
        $x("//span[text()='Забронировать']").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

}
