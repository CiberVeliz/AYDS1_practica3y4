package view.backing.Procesos;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class SucursalTest {
   Sucursal fixture1 = new Sucursal();
    
    public SucursalTest() {
    }

    public static void main(String[] args) {
        String[] args2 = { SucursalTest.class.getName() };
        JUnitCore.main(args2);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * @see Sucursal#mostrarPopUpCRUD()
     */
    @Test
    public void testMostrarPopUpCRUD() {
        String salida = null;
        salida = fixture1.mostrarPopUpCRUD();
        assertEquals("noingresosucursal", salida);
        
       
    }

    /**
     * @see Sucursal#onDialogAction(oracle.adf.view.rich.event.DialogEvent)
     */
    @Test
    public void testOnDialogAction() {
       // fail("Unimplemented");
    }
}
