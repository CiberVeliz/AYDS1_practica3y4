package view.backing.Procesos;

import javax.faces.event.ValueChangeEvent;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import view.backing.Login;

public class AsesorTest {
    Asesor fixture1 = new Asesor();

    
    
    public AsesorTest() {
    }

    public static void main(String[] args) {
        String[] args2 = { AsesorTest.class.getName() };
        JUnitCore.main(args2);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * @see Asesor#mostrarPopUpCRUD()
     */
    @Test
    public void testMostrarPopUpCRUD() {
        String salida = null;
        salida = fixture1.mostrarPopUpCRUD();
        assertEquals("no_ingresa", salida);
    }

    /**
     * @see Asesor#onDialogAction(oracle.adf.view.rich.event.DialogEvent)
     */
    @Test
    public void testOnDialogAction() {        
        ValueChangeEvent valueChangeEvent;
       
    }
}
