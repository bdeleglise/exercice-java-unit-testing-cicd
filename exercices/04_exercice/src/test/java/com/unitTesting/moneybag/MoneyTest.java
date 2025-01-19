package com.unitTesting.moneybag;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoneyTest {

  @Test
  public void testSimpleAdd() {
    Money m12CHF = new Money(12, "CHF"); // création de données
    Money m14CHF = new Money(14, "CHF");
    Money expected = new Money(26, "CHF");
    Money result = m12CHF.add(m14CHF); // exécution de la méthode testée
    assertTrue(expected.equals(result)); // comparaison
  }

  //hello
}
