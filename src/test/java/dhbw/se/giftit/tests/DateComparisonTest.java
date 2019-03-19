/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.tests;

import dhbw.se.giftit.exc.DateComparisonFailedException;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author spfli
 */
public class DateComparisonTest {
        private Date date1;
        private Date date2;
        
        public DateComparisonTest(){
            //22.10.2003
            this.date1 = new Date(1066773600000L);
            
            //27.10.2003
            this.date2 = new Date(1067209200000L);
            
            
        }
        
        @Test
        public void testDateComparison()throws DateComparisonFailedException{
            long diff = date2.getTime() - date1.getTime();
            long expResult = diff / (1000*60*60*24);
            long daysBetween = 5;
            assertEquals(expResult, daysBetween, "Der Algorithmus zum Vergleichen zweier Daten ging schief!");
        }
        
}
