package view.backing;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.event.DialogEvent;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class LoginTest {
    Login fixture1 = new Login();

    public LoginTest() {
    }

    public static void main(String[] args) {
        String[] args2 = { LoginTest.class.getName() };
        JUnitCore.main(args2);
    }

    /**
     * @see Login#mostrarPopUpCRUD()
     */
    @Test
    public void testMostrarPopUpCRUD() {
        String salida = null;
        salida = fixture1.mostrarPopUpCRUD();
        assertEquals("ingresar", salida);
    }

    /**
     * @see Login#onDialogAction(oracle.adf.view.rich.event.DialogEvent)
     */
    @Test
    public void testOnDialogAction() {
        //DialogEvent dialogEvent = new DialogEvent();
        //assertTrue(fixture1.onDialogAction(dialogEvent));
    }

    /**
     * @see Login#ingresar()
     */
    @Test
    public void testIngresar() {        
        String salida = null;
        salida = fixture1.ingresar();
        assertEquals("no_ingresa", salida);
    }

    /**
     * @see Login#ingresoUsuario(javax.faces.event.ValueChangeEvent)
     */
    @Test
    public void testIngresoUsuario() {
        ValueChangeEvent valueChangeEvent;
       // assertTrue(fixture1.ingresoUsuario(valueChangeEvent);)
        
    }
}
