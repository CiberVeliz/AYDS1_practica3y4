package view;

import java.io.Serializable;

import oracle.adf.controller.TaskFlowId;

public class regionMenu implements Serializable {
    private String taskFlowId = "/WEB-INF/con_cliente_tf.xml#con_cliente_tf";

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
}
