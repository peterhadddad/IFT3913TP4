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

                // Si le résultat n'est pas nul, alors le test a passé
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

                // Si le résultat n'est pas nul, alors le test a passé
                assertNotNull(convertedAmountNegatif);

                Double amountGrand = 99999999.0;
                Double convertedAmountGrand = MainWindow.convert(
                    currencyOne.getShortName(), currencyTwo.getShortName(), currencies,amountGrand
                );
                assertNotNull(convertedAmountGrand);

            }
        }
    }
}



@Test
public void testConvertInvalidCurrencyPair() {
    ArrayList<Currency> currencies= new ArrayList<>();
    // Ajoutez des devises valides dans votre liste 'currencies'

    Double amount = 100.0;

    // Assurez-vous que la paire de devises est invalide dans votre liste 'currencies'
    // Exemple: Currency1 = "EUR", Currency2 = "XYZ" (où XYZ n'existe pas dans vos données)

    Double convertedAmount = MainWindow.convert("EUR", "XYZ", currencies, amount);

    assertEquals(0.0, convertedAmount,0.0); // Vérifie si convertedAmount est égal à 0.0
}



@Test
public void testConvertInvalidFirstCurrency(){
    ArrayList<Currency> currencies = Currency.init(); 

    Double amount=100.0;

    Double convertedAmount=MainWindow.convert("Invalide", "USD", currencies, amount);
    assertEquals(0.0,convertedAmount,0.001);

}

}
