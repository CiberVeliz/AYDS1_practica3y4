package view.backing.Procesos;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class Cuenta_crudTest {

    Cuenta_crud fixture1 = new Cuenta_crud();
    
    public Cuenta_crudTest() {
    }

    public static void main(String[] args) {
        String[] args2 = { Cuenta_crudTest.class.getName() };
        JUnitCore.main(args2);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * @see Cuenta_crud#mostrarPopUpCRUDC()
     */
    @Test
    public void testMostrarPopUpCRUDC() {
        String salida = null;
        salida = fixture1.mostrarPopUpCRUDC();
        assertEquals("no_ingresa", salida);
    }

    /**
     * @see Cuenta_crud#onDialogActionC(oracle.adf.view.rich.event.DialogEvent)
     */
    @Test
    public void testOnDialogActionC() {
       // fail("Unimplemented");
    }

    /**
     * @see Cuenta_crud#generarReporteCliente(String)
     */
    @Test
    public void testGenerarReporteCliente() {
       // fail("Unimplemented");
    }
}
