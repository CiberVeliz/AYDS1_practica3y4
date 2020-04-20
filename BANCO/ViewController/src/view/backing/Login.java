package view.backing;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlInputSecret;

import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
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

public class Login {
    private RichForm f1;
    private RichDocument d1;
    private RichInputText usuario;
    private HtmlInputSecret password;
    private RichPopup puCRUD;


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
            if (this.usuario.getValue().equals("admin")) {
                if (this.password.getValue().equals("admin"))
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
}
