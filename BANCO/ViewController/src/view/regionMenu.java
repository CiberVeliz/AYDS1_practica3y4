package view;

import java.io.Serializable;

import oracle.adf.controller.TaskFlowId;

public class regionMenu implements Serializable {
    private String taskFlowId = "/WEB-INF/bienvenida.xml#bienvenida";

    public regionMenu() {
    }

    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setDynamicTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }

    public String con_cliente_tf() {
        setDynamicTaskFlowId("/WEB-INF/con_cliente_tf.xml#con_cliente_tf");
        return null;
    }

    public String con_asesor_tf() {
        setDynamicTaskFlowId("/WEB-INF/con_asesor_tf.xml#con_asesor_tf");
        return null;
    }

    public String pro_prestamo_tf() {
        setDynamicTaskFlowId("/WEB-INF/pro_prestamo_tf.xml#pro_prestamo_tf");
        return null;
    }

    public String con_sucursal_tf() {
        setDynamicTaskFlowId("/WEB-INF/con_sucursal_tf.xml#con_sucursal_tf");
        return null;
    }

    public String con_cuenta_tf() {
        setDynamicTaskFlowId("/WEB-INF/con_cuenta_tf.xml#con_cuenta_tf");
        return null;
    }

    public String pro_transferencia() {
        setDynamicTaskFlowId("/WEB-INF/pro_transferencia.xml#pro_transferencia");
        return null;
    }

    public String perfil_tf() {
        setDynamicTaskFlowId("/WEB-INF/perfil_tf.xml#perfil_tf");
        return null;
    }

    public String perfil_usuario_tf() {
        setDynamicTaskFlowId("/WEB-INF/perfil_usuario_tf.xml#perfil_usuario_tf");
        return null;
    }

    public String con_tipocambio_tf() {
        setDynamicTaskFlowId("/WEB-INF/con_tipocambio_tf.xml#con_tipocambio_tf");
        // Add event code here...
        return null;
    }
}
