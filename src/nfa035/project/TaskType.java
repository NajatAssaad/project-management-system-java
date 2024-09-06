package nfa035.project;

import java.io.Serializable;

public class TaskType implements Serializable {

    private int typeid;
    private String desc;
    private static final long serialVersionUID = 3934091180647457067L;

    public TaskType(String desc) {
        this.desc = desc;
        typeid = IDManager.getNextId(TaskType.class.getSimpleName());
    }

    public int getTypeid() {
        return typeid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "TaskType{" + "typeid=" + typeid + ", desc=" + desc + '}';
    }

}
