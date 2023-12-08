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

package test.TACHE2;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;

public class tache2Test {
// Tâche 2

// Méthode currencyConverter.MainWindow.convert(String, String, ArrayList<Currency>, Double)


//Critère de couverture des arcs du graphe de flot de contrôle

//Test standard avec données valides et régulières

    //Tester avec valeurs valides et standards
    @Test
    public void testStandard() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "US Dollar";
        String curr2 = "Euro";
        Double amount = 100.0;
        Double expected=93.0;
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
            
        assertEquals(expected, result, 0.01);
    }

    //Tester avec la même devise
    @Test
    public void testMemeDevise() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "Japanese Yen";
        String curr2 = "Japanese Yen";
        Double amount = 100.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
            
        assertEquals(100.0, result, 0.01);
    }

    //Tester un résultat avec des valeurs après la virgule
    @Test
    public void testStandardVirgule() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "Japanese Yen";
        String curr2 = "Chinese Yuan Renminbi";
        Double amount = 1.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
            
        assertEquals(0.051, result, 0.01);
    }

//Test avec valeurs non valides

    //Tester une devise non valide (CAD)
    @Test
    public void testDeviseNV() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "US Dollar";
        String curr2 = "CAD Dollar";
        Double amount = 100.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
            
        // Si le résultat est nul, alors le code gère correctement les devises non valides
        assertNull(result); 
    }

    //Tester un montant non valide (valeur négative)
    @Test
    public void testMontantNVmin() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "US Dollar";
        String curr2 = "Euro";
        Double amount = -100.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
            
        // Si le résultat est nul, alors le code gère correctement les montants non valides
        assertNull(result); 
    }

    //Tester un montant non valide (valeur dépassant le maximum)
    @Test
    public void testMontantNVmax() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "US Dollar";
        String curr2 = "Euro";
        Double amount = 1500000.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
            
        // Si le résultat est nul, alors le code gère correctement les montants non valides
        assertNull(result); 
    }

//Tests avec valeurs nulles

    //Tester lorsque une devise est non spécifiée
    @Test
    public void testDeviseNull() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = null;
        String curr2 = "US Dollar";
        Double amount = 100.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
            
        // Si le résultat est nul, alors le code gère correctement les devises non spécifiées
        assertNull(result); 
    }

    //Tester lorsque les deux devises sont non spécifiées
    @Test
    public void testDeviseNull2() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = null;
        String curr2 = null;
        Double amount = 100.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
            
        // Si le résultat est nul, alors le code gère correctement les devises non spécifiées
        assertNull(result); 
    }
    
    //Tester le montant lorsqu'aucune valeur est insérée
    @Test
    public void testAmountNull() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "US Dollar";
        String curr2 = "Euro";
        Double amount = null;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);

        // Si le résultat est nul, alors le code gère correctement les valeurs nulles
        assertNull(result);
    }

//Critère de couverture des chemins indépendants du graphe de flot de contrôle

//Tests pour différents chemins

    @Test
    public void testChemin1() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "US Dollar";
        String curr2 = "British Pound";
        Double amount = 100.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
            
        assertEquals(66.0, result, 0.01);
    }

    @Test
    public void testChemin2() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "CHF";
        String curr2 = "JPY";
        Double amount = 100.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
            
        assertEquals(12284.0, result, 0.01);
    }

    @Test
    public void testChemin3() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "Chinese Yuan Renminbi";
        String curr2 = "Euro";
        Double amount = 100.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
            
        assertEquals(15.0, result, 0.01);
    }

//Tests avec valeurs extrèmes

    //Tester le montant accepté maximum (1000000.0) 
    @Test
    public void testMontantMax() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "US Dollar";
        String curr2 = "British Pound";
        Double amount = 1000000.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
        
         
        assertEquals(660000.0, result, 0.01);
    }

    //Tester le montant accepté minimum (0.0)
    @Test
    public void testMontantMin() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "USD";
        String curr2 = "GBP";
        Double amount = 0.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
            
        assertEquals(0.0, result, 0.01);
    }

    //Tester lorsque le résultat dépasse 1000000.0
    @Test
    public void testMontantMax2() {
        ArrayList<Currency> currencies = Currency.init(); 
        String curr1 = "US Dollar";
        String curr2 = "Japanese Yen";
        Double amount = 1000000.0;
    
        Double result = MainWindow.convert(curr1, curr2, currencies, amount);
        
        
        assertEquals(123540000.0, result, 0.01);
    }





// Méthode currencyConverter.Currency.convert(Double, Double)

// Critère de couverture des instructions

// Test standard avec valeurs valides

        //Test standard
        @Test
        public void test2Standard() {
            Double amount = 100.0;
            Double exchangeValue = 1.5;
    
            Double result = Currency.convert(amount, exchangeValue);
            
            assertEquals(150.0, result, 0.01);
        }

// Test avec valeurs limites

        //Test avec amount maximum
        @Test
        public void test2MontantMaximum() {
            Double amount = 1000000.0;
            Double exchangeValue = 1.5;
    
            Double result = Currency.convert(amount, exchangeValue);
            
            assertEquals(1500000.0, result, 0.01);
        }

        //Test avec amount minimum
        @Test
        public void test2MontantMinimum() {
            Double amount = 0.0;
            Double exchangeValue = 1.5;
    
            Double result = Currency.convert(amount, exchangeValue);
            
            assertEquals(0.0, result, 0.01);
        }


//Test avec valeurs nulles

        //Test où le montant est nul (non spécifié)
        @Test
        public void test2MontantNul() {
            Double amount = null;
            Double exchangeValue = 1.5;
    
            Double result = Currency.convert(amount, exchangeValue);
            
            // Si le résultat est nul, alors le code gère correctement un montant nul
            assertNull(result);
        }

        //Test où le taux de change est nul (non spécifié)
        @Test
        public void test2ExchangeNul() {
            Double amount = 100.0;
            Double exchangeValue = null;
    
            Double result = Currency.convert(amount, exchangeValue);
            
            // Si le résultat est nul, alors le code gère correctement un montant nul
            assertNull(result);
        }

        //Test où les deux valeurs sont nulles (non spécifiées)
        @Test
        public void test2Nulles() {
            Double amount = null;
            Double exchangeValue = null;
    
            Double result = Currency.convert(amount, exchangeValue);
            
            // Si le résultat est nul, alors le code gère correctement des valeurs nulles
            assertNull(result);
        }

// Critère de couverture des chemins indépendants du graphe de flot de contrôle

//Test pour différents chemins

        //Test de chemin 1
        @Test
        public void test2Chemin1() {
            Double amount = 100.0;
            Double exchangeValue = 186.41; //GBP --> JPY
    
            Double result = Currency.convert(amount, exchangeValue);
            
            assertEquals(18641.0, result, 0.01);
        }

        //Test de chemin 2
        @Test
        public void test2Chemin2() {
            Double amount = 100.0;
            Double exchangeValue = 6.83; //EUR --> CNY
    
            Double result = Currency.convert(amount, exchangeValue);
            
            assertEquals(683.0, result, 0.01);
        }

        //Test de chemin 3
        @Test
        public void test2Chemin3() {
            Double amount = 100.0;
            Double exchangeValue = 0.66; //USD --> GBP
    
            Double result = Currency.convert(amount, exchangeValue);
            
            assertEquals(66.0, result, 0.01);
        }

//Test des précision avec valeurs décimales (tester l'arrondissement)

        //Test pour un résultat avec une conversion qui contient des décimales
        @Test
        public void test2TauxVirgule() {
            Double amount = 1.0;
            Double exchangeValue = 0.051;
    
            Double result = Currency.convert(amount, exchangeValue);
            
            assertEquals(0.051, result, 0.01);
        }

        //Test pour un résultat avec un montant qui contient des décimales
        @Test
        public void test2MontantVirgule() {
            Double amount = 1.0759;
            Double exchangeValue = 0.051;
    
            Double result = Currency.convert(amount, exchangeValue);
            
            assertEquals(0.055, result, 0.01);
        }


//Test avec valeurs non valides

        //Test où le montant est non valide (négatif)
        @Test
        public void test2MontantNVmin() {
            Double amount = -100.0;
            Double exchangeValue = 1.5;
    
            Double result = Currency.convert(amount, exchangeValue);
            
            // Si le résultat est nul, alors le code gère correctement un montant non valide négatif
            assertNull(result);
        }

        //Test où le montant est non valide (dépasse le maximum)
        @Test
        public void test2MontantNVmax() {
            Double amount = 1500000.0;
            Double exchangeValue = 1.5;
    
            Double result = Currency.convert(amount, exchangeValue);
            
            // Si le résultat est nul, alors le code gère correctement un montant non valide qui dépasse le max
            assertNull(result);
        }

        //Test où le taux de change est non valide (négatif)
        @Test
        public void test2TauxNV() {
            Double amount = 100.0;
            Double exchangeValue = -1.5;
    
            Double result = Currency.convert(amount, exchangeValue);
            
            // Si le résultat est nul, alors le code gère correctement un taux de change non valide négatif
            assertNull(result);
        }
}

