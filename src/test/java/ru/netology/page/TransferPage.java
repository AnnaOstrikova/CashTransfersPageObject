package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement from = $("[data-test-id='from'] input");
    private SelenideElement to = $("[data-test-id='to'] input");
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement errorMessage = $("[data-test-id='error-notification']");

    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    public void makeTransfer(String amountToTransfer, DataHelper.Card card) {
        amount.setValue(amountToTransfer);
        from.setValue(card.getNumber());
        transferButton.click();
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.Card card) {
        makeTransfer(amountToTransfer, card);
        return new DashboardPage();
    }

    public void findErrorMessage(String expectedText) {
        errorMessage.shouldHave(text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }


}
