package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class currencyTest {
    
    @Test
    public void testCurrencyValide() {
        ArrayList<Currency> currencies = Currency.init();

        for (Currency currencyTest : currencies) {
            for (Double exchangeValue : currencyTest.getExchangeValues().values()) {
                Double amount = 100.0;
                Double convertedAmount = Currency.convert(amount, exchangeValue);

                assertNotNull(convertedAmount);

                // On aurait voulu tester les résultats obtenus spécifiques (avec assertEquals), 
                // par contre,puisque nous traversons tous les taux d'échanges de devises possibles,
                // il serait inpertinent de faire cela.

            }
        }
    }

    @Test
    public void testCurrencyTauxInvalide() {
        Double amount = 100.0;
        Double exchangeRate = -0.82
        Double convertedAmount = Currency.convert(amount, exchangeRate);

        // Si le résultat est nul, alors le programme s'occupe des taux de changes négatifs correctement
        assertNull(convertedAmount);
    }

    @Test
    public void testCurrencyMontantValide() {
        ArrayList<Currency> currencies = Currency.init();

        for (Currency currencyTest : currencies) {
            for (Double exchangeValue : currencyTest.getExchangeValues().values()) {
                Double amountMin = 0;
                Double convertedAmountMin = Currency.convert(amountMin, exchangeValue);

                assertNotNull(convertedAmountMin);
                assertEquals(0, convvertedAmountMin, 0);

                Double amountMax = 1000000000;
                Double convertedAmountMax = Currency.convert(amountMax, exchangeValue);

                assertNotNull(convertedAmountMax);
            }
        }
    }

    @Test
    public void testCurrencyMontantInvalide() {
        ArrayList<Currency> currencies = Currency.init();

        for (Currency currencyTest : currencies) {
            for (Double exchangeValue : currencyTest.getExchangeValues().values()) {
                Double amountNeg = -100;
                Double convertedAmountNeg = Currency.convert(amountNeg, exchangeValue);
                // Si le résultat est nul, alors le code gère les montants non valides négatifs correctement
                assertNull(convertedAmountNeg);

                Double amountSur = 1500000;
                Double convertedAmountSur = Currency.convert(amountSur, exchangeValue);
                // Si le résultat est nul, alors le code gère correctement les montants non valides qui dépassent le max 
                assertNull(convertedAmountSur);
            }
        }
    }

    @Test
    public void testCurrencyMontantNonDefini() {
        ArrayList<Currency> currencies = Currency.init();

        for (Currency currencyTest : currencies) {
            for (Double exchangeValue : currencyTest.getExchangeValues().values()) {
                Double amountND = null;
                Double convertedAmountNND = Currency.convert(amountND, exchangeValue);

                // Si le résultat est nul, alors le code gère un montant non défini correctement
                assertNull(convertedAmountND);
            }
        }
    }
}
