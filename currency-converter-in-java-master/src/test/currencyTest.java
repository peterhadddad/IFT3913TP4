package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

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

    

}
