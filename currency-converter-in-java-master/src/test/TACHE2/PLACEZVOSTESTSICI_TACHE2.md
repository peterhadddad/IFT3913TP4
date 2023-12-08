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