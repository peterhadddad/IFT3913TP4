public void testMainWindowValide() {
    // On crée une liste de devises afin de naviguer à travers pour les tests
    ArrayList<Currency> currencies = Currency.init(); 

    // On teste la conversion entre toutes les paires de devises valides en sélectionnant
    // 2 devises différentes
    for (Currency currencyOne : currencies) {           // On traverse la liste pour la première devise que nous allons utiliser
        for (Currency currencyTwo : currencies) {       // Deuxième devise
            if (currencyOne != currencyTwo) {           // On s'assure que les 2 devises sélectionner ne sont pas les mêmes
                Double amount = 100.0;
                Double convertedAmount = MainWindow.convert(
                    currencyOne.getShortName(), currencyTwo.getShortName(), currencies, amount
                );

                // Si le résultat n'est pas nul, alors la méthode fonctionne correctement.
                assertNotNull(convertedAmount);

            }
        }
    }
}

@Test
public void testMainWindowInvalide() {
    // On crée une liste de devises afin de naviguer à travers pour les tests
    ArrayList<Currency> currencies = Currency.init(); 

    // On teste la conversion entre toutes les paires de devises valides en sélectionnant
    // 2 devises différentes
    for (Currency currencyOne : currencies) {           // On traverse la liste pour la première devise que nous allons utiliser
        for (Currency currencyTwo : currencies) {       // Deuxième devise
            if (currencyOne != currencyTwo) {           // On s'assure que les 2 devises sélectionner ne sont pas les mêmes
                Double amountNegatif = -50.0;
                Double convertedAmountNegatif = MainWindow.convert(
                    currencyOne.getShortName(), currencyTwo.getShortName(), currencies, amountNegatif
                );

                // Si le résultat est nul, alors la méthode gère bien les valeurs non valides
                assertNull(convertedAmountNegatif);

                Double amountGrand = 99999999.0;
                Double convertedAmountGrand = MainWindow.convert(
                    currencyOne.getShortName(), currencyTwo.getShortName(), currencies,amountGrand
                );

                // Si le résultat est nul, alors la méthode gère bien les valeurs non valides
                assertNull(convertedAmountGrand);

            }
        }
    }
}

@Test
public void testMainWindowMontantValide() {
    ArrayList<Currency> currencies = Currency.init(); 

    // On teste si les montants à convertir sont valides
    for (Currency currencyOne : currencies) {
        for (Currency currencyTwo : currencies) {
            if (currencyOne != currencyTwo) {
                Double amountMin = 0;
                Double convertedAmountMin = MainWindow.convert(
                    currencyOne.getShortName(), currencyTwo.getShortName(), currencies, amountMin
                );

                // On vérifie si le résultat n'est pas nul ET on s'attend à ce que le
                // résultat de la conversion soit 0.
                assertNotNull(convertedAmountMin);
                assertEquals(0, convertedAmountMin, 0);

                Double amountMax = 1000000;
                Double convertedAmountMax = MainWindow.convert(
                    currencyOne.getShortName(), currencyTwo.getShortName(), currencies, amountMax
                );

                // Si le résultat n'est pas nul, alors la méthode gère bien la valeur maximale
                assertNotNull(convertedAmountMax);
            }
        }
    }
}

@Test
public void touteDeviseExistantes(){
    ArrayList<Currency> currencies = Currency.init(); 
    String[] currenciesUtilisee={"USD","CAD","GBP","EUR","CHF","AUD"};

    for(String currencyCode:currenciesUtilisee){
        boolean currencyExist=checkIfCurrencyExists(currencies,currencyCode);
        assertTrue("Currency " + currencyCode + " does not exist", currencyExist);
    }
}

private boolean checkIfCurrencyExists(ArrayList<Currency> currencies, String currencyCode) {
    for(Currency currency:currencies){
        if(currency.getShortName().equals(currencyCode)){
            return true;
        }
    }
    return false;
}


@Test
public void testMainWindowDevisesInvalide() {
    ArrayList<Currency> currencies = Currency.init();

    // On test maintenant la conversion entre des pairs invalides
    Double amount = 100.0; 
    Double convertedAmount = MainWindow.convert("Canadian Dollar", "Australian Dollar", currencies, amount);

    // Si le résultat est nul, alors le code gère correctement les devises non valides
    assertNull(convertedAmount); 
}
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import currencyConverter.Currency;

public class currencyTest {
    @Test

    public void testConvertValidExchangeValue(){
        Double amount=100.0;
        Double exchangeValue=1.5;
        Double convertedAmount=currencyConverter.Currency.convert(amount, exchangeValue);
        assertEquals(150,convertedAmount,0.001);
    }

    @Test
        public void testConvertInvalidExchangeValue(){
        Double amount=100.0;
        Double exchangeValue=null;
        Double convertedAmount=null;

        try{
            convertedAmount=currencyConverter.Currency.convert(amount, exchangeValue);

        }catch(NullPointerException e){
            e.printStackTrace();

        }
        assertNull(convertedAmount);
    }
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
    public void testCurrencyInvalide() {
        Double amount = 100.0;
        Double exchangeRate = -0.82;
        Double convertedAmount = Currency.convert(amount, exchangeRate);

        // Si le résultat est nul, alors le programme s'occupe des taux d'échanges 
        // négatifs correctement
        assertNull(convertedAmount);
    }
    @Test
    public void testCurrencyMontantValide() {
        ArrayList<Currency> currencies = Currency.init();

        for (Currency currencyTest : currencies) {
            for (Double exchangeValue : currencyTest.getExchangeValues().values()) {
                Double amountMin = 0.0;
                Double convertedAmountMin = Currency.convert(amountMin, exchangeValue);

                assertNotNull(convertedAmountMin);
                assertEquals(0, convertedAmountMin, 0);

                Double amountMax = 1000000000.0;
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
                Double amountNeg = -100.0;
                Double convertedAmountNeg = Currency.convert(amountNeg, exchangeValue);

                assertNull(convertedAmountNeg);

                Double amountSur = 1500000.0;
                Double convertedAmountSur = Currency.convert(amountSur, exchangeValue);

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
                assertNull(convertedAmountNND);
            }
        }
    }




    

}
