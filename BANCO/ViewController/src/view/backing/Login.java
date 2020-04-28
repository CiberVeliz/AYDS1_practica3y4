package view.backing;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlInputSecret;

import javax.faces.context.FacesContext;

import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import org.apache.myfaces.trinidad.context.RequestContext;

public class Login {
    private RichForm f1;
    private RichDocument d1;
    private RichInputText usuario;
    private HtmlInputSecret password;
    private RichPopup puCRUD;
    RequestContext requestContext = RequestContext.getCurrentInstance();
    FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
    private RichInputText puCodcli;
    private RichInputText puNombre;
    private RichInputText puContra;


    /*Método que abre el pop up del CRUD*/
    public String mostrarPopUpCRUD() {
        BindingContainer bindings = null;

        bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateWithParams");
        operationBinding.execute();
        
    
        RichPopup popup = this.getPuCRUD();
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        //empty hints renders dialog in center of screen
        popup.show(hints);

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

            bindings = getBindings();
            operationBinding = bindings.getOperationBinding("Commit");
            operationBinding.execute();
            
            requestContext.getPageFlowScope().put("USUARIO", this.puCodcli.getValue().toString());
            requestContext.getPageFlowScope().put("CONTRASENA", this.puContra.getValue().toString());
            session.setAttribute("NOMBRE", this.puNombre.getValue().toString());
            session.setAttribute("USUARIO", this.puCodcli.getValue().toString());
            
        }

    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }


    public void setUsuario(RichInputText usuario) {
        this.usuario = usuario;
    }

    public RichInputText getUsuario() {
        return usuario;
    }

    public void setPassword(HtmlInputSecret password) {
        this.password = password;
    }

    public HtmlInputSecret getPassword() {
        return password;
    }

    public void setPuCRUD(RichPopup puCRUD) {
        this.puCRUD = puCRUD;
    }

    public RichPopup getPuCRUD() {
        return puCRUD;
    }

    public String ingresar() {
        // Add event code here...
        try {
            String usu = AdfFacesContext.getCurrentInstance().getPageFlowScope().get("USUARIO").toString();
            String con = AdfFacesContext.getCurrentInstance().getPageFlowScope().get("CONTRASENA").toString();


            if (this.usuario.getValue().equals(usu) && this.password.getValue().equals(con)) {
                return "ir_menu";
            } else {
                FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Usuario o contraseña incorrecta");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }

        } catch (NullPointerException e) {
            System.out.print("NullPointerException Caught");
        }
        return null;
    }

    public void ingresoUsuario(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer) bindings;
        DCIteratorBinding iter = bindingsImpl.findIteratorBinding("ClienteView1Iterator");
        RowSetIterator row = iter.getRowSetIterator();
        row.reset();
        Row r = row.first();
       
        System.out.println(r.getAttribute("ClienteCodcli").toString());
        
        if (valueChangeEvent.getNewValue().equals(r.getAttribute("ClienteCodcli"))) {
            requestContext.getPageFlowScope().put("USUARIO", r.getAttribute("ClienteCodcli").toString());
            requestContext.getPageFlowScope().put("CONTRASENA", r.getAttribute("ClienteContrasena").toString());
            session.setAttribute("NOMBRE", r.getAttribute("ClienteNombre").toString());
            session.setAttribute("USUARIO", r.getAttribute("ClienteCodcli").toString());
            
        }
       
        while (row.hasNext()) {
            r = row.next();
            System.out.println(r.getAttribute("ClienteCodcli").toString());
            
            if (valueChangeEvent.getNewValue().equals(r.getAttribute("ClienteCodcli"))) {
                requestContext.getPageFlowScope().put("USUARIO", r.getAttribute("ClienteCodcli").toString());
                requestContext.getPageFlowScope().put("CONTRASENA", r.getAttribute("ClienteContrasena").toString());
                session.setAttribute("NOMBRE", r.getAttribute("ClienteNombre").toString());
                session.setAttribute("USUARIO", r.getAttribute("ClienteCodcli").toString());
                
            }
        }
    }

    public void setPuCodcli(RichInputText puCodcli) {
        this.puCodcli = puCodcli;
    }

    public RichInputText getPuCodcli() {
        return puCodcli;
    }

    public void setPuNombre(RichInputText puNombre) {
        this.puNombre = puNombre;
    }

    public RichInputText getPuNombre() {
        return puNombre;
    }

    public void setPuContra(RichInputText puContra) {
        this.puContra = puContra;
    }

    public RichInputText getPuContra() {
        return puContra;
    }

 
}
