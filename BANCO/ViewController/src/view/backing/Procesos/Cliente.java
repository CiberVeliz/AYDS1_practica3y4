package view.backing.Procesos;


import javax.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;

import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;




import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.naming.InitialContext;

import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.TaskFlowId;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.fragment.RichPageTemplate;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;
import javax.sql.DataSource;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Key;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.DisclosureEvent;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class Cliente {
    private RichButton b1;
    private RichButton btnEditar;
    private RichPopup puCRUD;
    private RichOutputText etiqEliminar;
     
    
    /*Método que abre el pop up del CRUD*/
    public String mostrarPopUpCRUD() {
        BindingContainer bindings = null;
        String accion = "";
        this.getEtiqEliminar().setVisible(false);
        //this.getSorTipPer().setValue("N");
        //AdfFacesContext.getCurrentInstance().addPartialTarget(this.getSorTipPer());

        if (ADFContext.getCurrent().getViewScope().get("accion") != null)
            accion = ADFContext.getCurrent().getViewScope().get("accion").toString();
        if ("ingresar".equalsIgnoreCase(accion)) {
             bindings = getBindings();
            OperationBinding operationBinding = bindings.getOperationBinding("CreateWithParams");
            operationBinding.execute();

            RichPopup popup = this.getPuCRUD();
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            //empty hints renders dialog in center of screen
            popup.show(hints);
        } else if ("editar".equalsIgnoreCase(accion)) {

             RichPopup popup = this.getPuCRUD();
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            //empty hints renders dialog in center of screen
            popup.show(hints);
        } else if ("eliminar".equalsIgnoreCase(accion)) {

            RichPopup popup = this.getPuCRUD();
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            //empty hints renders dialog in center of screen
            popup.show(hints);
            this.getEtiqEliminar().setVisible(true);
        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(this.getEtiqEliminar());

        return null;
    }

    /*PopupCanceledListener (Popup)*/
    public void editPopupCancelListener(PopupCanceledEvent popupCanceledEvent) {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Rollback");
        operationBinding.execute();
    }

    /*DialogListener (Dialog)*/
    public void onDialogAction(DialogEvent dialogEvent) {

        if (dialogEvent.getOutcome().name().equals("ok")) {

            BindingContainer bindings = null;
            OperationBinding operationBinding = null;

            if ("eliminar".equalsIgnoreCase(ADFContext.getCurrent().getViewScope().get("accion").toString())) {
                bindings = getBindings();
                operationBinding = bindings.getOperationBinding("Delete");
                operationBinding.execute();

                bindings = getBindings();
                operationBinding = bindings.getOperationBinding("Commit");
                operationBinding.execute();
            }

            bindings = getBindings();
            operationBinding = bindings.getOperationBinding("Commit");
            operationBinding.execute();
        }

    }
    
   

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setB1(RichButton b1) {
        this.b1 = b1;
    }

    public RichButton getB1() {
        return b1;
    }

    public void setBtnEditar(RichButton btnEditar) {
        this.btnEditar = btnEditar;
    }

    public RichButton getBtnEditar() {
        return btnEditar;
    }

    public void setPuCRUD(RichPopup puCRUD) {
        this.puCRUD = puCRUD;
    }

    public RichPopup getPuCRUD() {
        return puCRUD;
    }

    public void setEtiqEliminar(RichOutputText etiqEliminar) {
        this.etiqEliminar = etiqEliminar;
    }

    public RichOutputText getEtiqEliminar() {
        return etiqEliminar;
    }
}
