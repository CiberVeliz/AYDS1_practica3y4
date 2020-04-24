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
import oracle.adf.model.BindingContext;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class Cuenta_crud {
    private RichPanelBox pb1;
    private RichPanelBox pb2;
    private RichPanelFormLayout pfl1;

    private RichTable t1;
    private RichPanelGroupLayout pgl1;
    private RichButton btnEditarC;
    private RichPopup puCRUDC;
    private RichOutputText etiqEliminarC;
    private final String taskflowId = "banco_rep_pdf_tf";
    private final String taskflowDocument = "/WEB-INF/banco_rep_pdf_tf.xml";
    private RichInputText dpi;


    /*Método que abre el pop up del CRUD*/
    public String mostrarPopUpCRUDC() {
        BindingContainer bindings = null;
        String accion = "";
        this.getEtiqEliminarC().setVisible(false);
        //this.getSorTipPer().setValue("N");
        //AdfFacesContext.getCurrentInstance().addPartialTarget(this.getSorTipPer());

        if (ADFContext.getCurrent().getViewScope().get("accion") != null)
            accion = ADFContext.getCurrent().getViewScope().get("accion").toString();
        if ("ingresar".equalsIgnoreCase(accion)) {
             bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("CreateWithParams");
            operationBinding.execute();

            RichPopup popup = this.getPuCRUDC();
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            //empty hints renders dialog in center of screen
            popup.show(hints);
        } else if ("editar".equalsIgnoreCase(accion)) {

             RichPopup popup = this.getPuCRUDC();
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            //empty hints renders dialog in center of screen
            popup.show(hints);
        } else if ("eliminar".equalsIgnoreCase(accion)) {

            RichPopup popup = this.getPuCRUDC();
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            //empty hints renders dialog in center of screen
            popup.show(hints);
            this.getEtiqEliminarC().setVisible(true);
        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getEtiqEliminarC());

        return null;
    }

    /*PopupCanceledListener (Popup)*/
    public void editPopupCancelListenerC(PopupCanceledEvent popupCanceledEvent) {
        BindingContainer bindings = getBindings();
       // OperationBinding operationBinding = bindings.getOperationBinding("Rollback");
       // operationBinding.execute();
    }

    /*DialogListener (Dialog)*/
    public void onDialogActionC(DialogEvent dialogEvent) {

        if (dialogEvent.getOutcome().name().equals("ok")) {

            BindingContainer bindings = null;
            OperationBinding operationBinding = null;

            if ("eliminar".equalsIgnoreCase(ADFContext.getCurrent().getViewScope().get("accion").toString())) {
                bindings = getBindings();
                operationBinding = bindings.getOperationBinding("Delete");
                operationBinding.execute();

               
            }
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t1);


        }

    }
    

    
    public String generarReporteCliente() {

         generarReporteCliente("pdf");

         return "";
     }
     
     public void generarReporteCliente(String tipoReporte) {
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
             //pathReporte = (String) ic.lookup("java:comp/env/urlReportes");

             //Creamos la Conexion
             //dataSourceName = (String) ic.lookup("java:comp/env/dataSourceName");

             ds = (DataSource) ic.lookup("java:comp/env/jdbc/Connection1DS");
             connection = ds.getConnection();

             FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
             HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

             //Valores que pasarán al objeto Map de Jasper Print
            // empresa = ADFContext.getCurrent().getSessionScope().get("EMPRESA").toString();
             //codPlanilla = this.codigoIt.getValue().toString();


             String parTipoReporte = tipoReporte; //Valor por defecto
             String parNombreReporteTitulo = "Reporte - Perfil de usuario";
             String nombreReporte = "preplanilla";

             //System.out.println("sqlString " + sqlString.toString());
             //Parámetros que recibe el objeto Jasper Print
             Map<String, Object> params = new HashMap<String, Object>();
             params.put("parDpi", this.dpi.getValue().toString());
            // params.put("parCodplanilla", codPlanilla);
            // params.put("SUBREPORT_DIR", pathReporte);

             //Parámetros objeto JasperPrint
             InputStream input =
                 new FileInputStream(new File("C:\\reportes\\cuenta_cliente.jrxml"));
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


    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }



    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }


    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }


    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }


    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }


    public void setBtnEditarC(RichButton btnEditarC) {
        this.btnEditarC = btnEditarC;
    }

    public RichButton getBtnEditarC() {
        return btnEditarC;
    }

    public void setPuCRUDC(RichPopup puCRUDC) {
        this.puCRUDC = puCRUDC;
    }

    public RichPopup getPuCRUDC() {
        return puCRUDC;
    }

    public void setEtiqEliminarC(RichOutputText etiqEliminarC) {
        this.etiqEliminarC = etiqEliminarC;
    }

    public RichOutputText getEtiqEliminarC() {
        return etiqEliminarC;
    }

    public String guardarCuenta() {
        // Add event code here...
        BindingContainer bindings = null;
        OperationBinding operationBinding = null;

        
        bindings = getBindings();
        operationBinding = bindings.getOperationBinding("Commit");
        operationBinding.execute();
        return "regresar";
    }

    public void setDpi(RichInputText dpi) {
        this.dpi = dpi;
    }

    public RichInputText getDpi() {
        return dpi;
    }
}
