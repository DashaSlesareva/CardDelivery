package ru.netologu.web;


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


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void positiveTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        LocalDate localDate = LocalDate.now();
        String meetingDate = dtf.format(localDate.plusDays(5));
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] .input__control").sendKeys(BACK_SPACE);
        $("[data-test-id='date'] .input__control").setValue(meetingDate);
        $("[data-test-id='name'] input").setValue("Андрей Иванов");
        $("[data-test-id='phone'] input").setValue("+79670789786");
        $("[data-test-id='agreement'] span").click();
        $x("//span[text()='Забронировать']").click();
        $("div[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }

}
