package view.backing.Procesos;


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

public class Cuenta_crud {
    private RichPanelBox pb1;
    private RichPanelBox pb2;
    private RichPanelFormLayout pfl1;

    private RichTable t1;
    private RichPanelGroupLayout pgl1;
    private RichButton btnEditarC;
    private RichPopup puCRUDC;
    private RichOutputText etiqEliminarC;


    
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
}
