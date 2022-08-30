package ru.netologu.web;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void dateTest() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        LocalDate localDate = LocalDate.now();
        String expected = dtf.format(localDate.plusDays(3));
        String actual = $("[data-test-id='date'] input[placeholder]").getValue();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTest() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='name'] input").setValue("Андрей Иванов");
        $("[data-test-id='phone'] input").setValue("+79670789786");
        $("[data-test-id='agreement'] span").click();
        $x("//span[text()='Забронировать']").click();
        $("div[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }
}
