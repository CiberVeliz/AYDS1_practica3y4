package view.backing.Procesos;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class Perfil_usuarioTest {
    Perfil_usuario fixture1 = new Perfil_usuario();

    public Perfil_usuarioTest() {
    }

    public static void main(String[] args) {
        String[] args2 = { Perfil_usuarioTest.class.getName() };
        JUnitCore.main(args2);
    }


    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(Perfil_usuarioTest.class);
    }

    /**
     * @see Perfil_usuario#generarReportePerfil()
     */
    @Test
    public void testGenerarReportePerfil() {
        String salida = null;
        salida = fixture1.generarReportePerfil("pdf");
        assertEquals("pdf", salida);
    }
}
