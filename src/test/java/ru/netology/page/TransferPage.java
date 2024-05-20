package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement headerTransfer = $(byText("Пополнение карты"));
    private SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");

    public TransferPage() {
        headerTransfer.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer,cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer(String amountToTransfer,DataHelper.CardInfo cardInfo) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void findErrorMessage(String expectedText) {
        errorNotification.shouldHave(text(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }

}
