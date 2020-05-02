package view.backing.Procesos;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import javax.naming.InitialContext;

import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.TaskFlowId;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class Perfil_usuario {
    private final String taskflowId = "banco_rep_pdf_tf";
    private final String taskflowDocument = "/WEB-INF/banco_rep_pdf_tf.xml";
    private RichInputText codDpi;


    public String generarReportePerfil() {

        generarReportePerfil("pdf");

        return "";
    }

    public void generarReportePerfil(String tipoReporte) {
        System.out.println("GENERANDO REPORTE...");
        Connection connection = null;
        String dataSourceName = null;
        InitialContext ic = null;
        String pathReporte = null;
        DataSource ds = null;

        String empresa = "";
        String codPlanilla = "";


        StringBuilder sqlString = new StringBuilder();
        sqlString.append("");

        try {

            //Asignamos el path
            ic = new InitialContext();
        
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/Connection1DS");
            connection = ds.getConnection();

            FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

        
            String parTipoReporte = tipoReporte; //Valor por defecto
            String parNombreReporteTitulo = "Reporte - Perfil de usuario";
          
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("parDpi", this.codDpi.getValue().toString());
        
            //Parámetros objeto JasperPrint
            InputStream input = new FileInputStream(new File("C:\\reportes\\perfil_usuario.jrxml"));
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport report = JasperCompileManager.compileReport(design);

            //Creamos el objeto de tipo JasperPrint
            JasperPrint reporte = JasperFillManager.fillReport(report, params, connection);

            //Creamos la variable de sesión que contendrá el reporte
            session.setAttribute("reporte", reporte);

            //Creamos el objeto Map para los parámetros del TaskFlow
            Map<String, Object> paramsTF = new HashMap<String, Object>();
            paramsTF.put("parTipoReporte", parTipoReporte);
            paramsTF.put("parNombreReporteTitulo", parNombreReporteTitulo);

            //Asignamos la URL del TF a la variable String
            String taskflowURL =
                ControllerContext.getInstance().getTaskFlowURL(false, new TaskFlowId(taskflowDocument, taskflowId),
                                                               paramsTF);
            System.out.println("INDEPENDIENTE");
            //Abrimos el TF en una ventana independiente
            ExtendedRenderKitService erks = Service.getRenderKitService(context, ExtendedRenderKitService.class);
            StringBuilder script = new StringBuilder();
            script.append("window.open(\"" + taskflowURL + "\");"); //Abrirá en ventana independiente el TF
            erks.addScript(FacesContext.getCurrentInstance(), script.toString());
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error iniciar Reporte " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public void setCodDpi(RichInputText codDpi) {
        this.codDpi = codDpi;
    }

    public RichInputText getCodDpi() {
        return codDpi;
    }
}
