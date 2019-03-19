package dhbw.se.giftit.tests;


import dhbw.se.giftit.exc.PasswordHashFailedException;
import dhbw.se.giftit.web.HashGenerator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author spfli
 */
public class HashGeneratorTest {
    
    private HashGenerator hashGenerator;
    
    public HashGeneratorTest(){
        this.hashGenerator = new HashGenerator();
    }
    
    @Test
    public void testHashPassword() throws PasswordHashFailedException{
        String password = "qwertzu";
        String expectedPassword = "69D19280CEDBFDA50065D1F6EFFB2753A3A12A18A39F2BF8D9B4E52802DC3BDE";
        String result = HashGenerator.hashPassword(password);
        assertEquals(expectedPassword, result, "Der HashGenerator hat ein falsches Ergebnis geliefert!");
    }
}
