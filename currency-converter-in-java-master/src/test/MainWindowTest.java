package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;

public class MainWindowTest{

@Test
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

}
