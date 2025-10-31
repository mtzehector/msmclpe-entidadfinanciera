/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.entidadfinancieraback.rule;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author antonio
 */
public class CalculoEdadRuleTest {
  
  public CalculoEdadRuleTest() {
  }

  @Test
  public void testSomeMethod() {
    
      try {
          RNCalculaEdad rule = new RNCalculaEdad();
          
          
          RNCalculaEdad.Input input = rule.new Input(new SimpleDateFormat("yyyy-MM-dd").parse("1939-07-27"));
          RNCalculaEdad.Output output = rule.apply(input);
          System.out.println( output.getEdad() );
      } catch (ParseException ex) {
          Logger.getLogger(CalculoEdadRuleTest.class.getName()).log(Level.SEVERE, null, ex);
      }
    
  }
  
}
