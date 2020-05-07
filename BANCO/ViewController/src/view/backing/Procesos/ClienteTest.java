package view.backing.Procesos;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {
    Cliente fixture1 = new Cliente();
    
    public ClienteTest() {
    }

    public static void main(String[] args) {
        String[] args2 = { ClienteTest.class.getName() };
        org.junit.runner.JUnitCore.main(args2);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * @see Cliente#mostrarPopUpCRUD()
     */
    @Test
    public void testMostrarPopUpCRUD() {
        String salida = null;
        salida = fixture1.mostrarPopUpCRUD();
        assertEquals("no_ingresa", salida);
    }

    /**
     * @see Cliente#editPopupCancelListener(oracle.adf.view.rich.event.PopupCanceledEvent)
     */
    @Test
    public void testEditPopupCancelListener() {
        //fail("Unimplemented");
    }

    /**
     * @see Cliente#onDialogAction(oracle.adf.view.rich.event.DialogEvent)
     */
    @Test
    public void testOnDialogAction() {
        //fail("Unimplemented");
    }
}
