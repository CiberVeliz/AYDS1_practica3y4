package view.backing.Procesos;

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


public class Sucursal {
    private RichButton btnEditar;
    private RichPopup puCRUD;
    private RichOutputText etiqEliminar;
    

    
    /*Método que abre el pop up del CRUD*/
    public String mostrarPopUpCRUD() {
        BindingContainer bindings = null;
        String accion = "";
        try{
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
            return"ingresosucursal";
        }catch(NullPointerException e){
            return "noingresosucursal";
        }
        
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
