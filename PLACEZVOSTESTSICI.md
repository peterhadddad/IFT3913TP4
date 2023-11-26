
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
        ArrayList<Currency> currencies = Currency.init();

        // On test maintenant la conversion entre des pairs invalides
        Double amount = 100.0; 
        Double convertedAmount = MainWindow.convert("MXN", "THB", currencies, amount);

        // Si le résultat est nul, alors le test a passé car les devises sont invalides
        assertNull(convertedAmount); 
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

                     // Si le résultat n'est pas nul, alors le test a passé
                    assertNotNull(convertedAmountMax);
                }
            }
        }
    }

    @Test
    public void testMainWindowMontantInvalide() {
        ArrayList<Currency> currencies = Currency.init();
    
        // On teste la conversion avec des montants invalides
        for (Currency currencyOne : currencies) {
            for (Currency currencyTwo : currencies) {
                if (currencyOne != currencyTwo) {
                    Double amountNeg = -100;
                    Double convertedAmountNeg = MainWindow.convert(
                        currencyOne.getShortName(), currencyTwo.getShortName(), currencies, amountNeg
                    );

                    // Si le résultat est nul, alors le programme s'occupe des montants
                    // négatifs correctement
                    assertNull(convertedAmountNeg);
    
                    Double amountSur = 1500000;
                    Double convertedAmountSur = MainWindow.convert(
                        currencyOne.getShortName(), currencyTwo.getShortName(), currencies, amountSur
                    );
                    // Si le résultat est nul, alors le programme s'occupe des montants plus 
                    // grands que 1000000 correctement
                    assertNull(convertedAmountSur);
                }
            }
        }
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

    // Pas certain si ce test est pertinent, mais le programme n'est certainement pas
    // censé retourner un résultat négatif dans la conversion
    @Test
    public void testCurrencyInvalide() {
        Double amount = 100.0;
        Double exchangeRate = -0.82
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

                assertNull(convertedAmountNeg);

                Double amountSur = 1000000000;
                Double convertedAmountSur = Currency.convert(amountSur, exchangeValue);

                assertNull(convertedAmountSur);
            }
        }
    }

