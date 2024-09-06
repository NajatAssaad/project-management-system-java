package nfa035.project;

import java.io.Serializable;
import java.util.HashSet;

public class Task implements Serializable {

    private int task_id;
    private String name;
    private TaskType type;
    private String description;
    private HashSet<Processus> processus;

    public Task(String n, String desc, TaskType tt) {
        this.task_id = IDManager.getNextId(Task.class.getSimpleName());
        this.name = n;
        this.description = desc;
        type = tt;
        this.processus = new HashSet<>();
    }

    public int getTask_id() {
        return task_id;
    }

    public TaskType getType() {
        return type;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProcessus(Processus pro) throws Exception {
        if (processus.contains(pro)) {
            throw new ProcessAlreadyFound("Le processus déjà appliqué");
        }
        processus.add(pro);
    }

    public HashSet<Processus> getProcessus() {
        return processus;
    }

    public Processus getOneProcessus(int id) throws Exception {
        for (Processus p : processus) {
            if (p.getProcessus_id() == id) {
                return p;
            }
        }
        throw new ProcessNotFound("Le processus avec l id : " + id + " n'a pas été trouvé");
    }

    public void removeProcessus(Processus pro) throws Exception {
        if (processus.contains(pro)) {
            processus.remove(pro);
        } else {
            throw new ProcessNotFound("Ce processus n'appartient pas à ce task");
        }
    }

    public void removeProcessus(int idpro) throws Exception {
        Processus p = getOneProcessus(idpro);
        if (processus.contains(p)) {
            processus.remove(p);
        } else {
            throw new ProcessNotFound("Ce processus n'appartient pas à ce task");
        }
    }

    public double getStatus() {
        int size = processus.size();
        int count = 0;
        for (Processus p : processus) {
            if ("Completed".equals(p.getStatus())) {
                count++;
            }
        }
        double statusprct = (size > 0) ? (count * 100.0) / size : 0.0;
        return Math.round(statusprct);
    }

    public double getDuree() {
        double duree = 0.0;
        for (Processus p : processus) {
            duree += p.getDuree();
        }
        return duree;
    }

    public double calculeCoutUsed() {
        double cout = 0.0;
        for (Processus p : processus) {
            cout += p.calculeCoutUsed();
        }
        return cout;
    }

    public double calculeCoutTotal() {
        double cout = 0.0;
        for (Processus p : processus) {
            cout += p.calculeCout();
        }
        return cout;
    }

    @Override
    public String toString() {
        return "Task{" + "task_id=" + task_id + ", name=" + name + ", type=" + type.toString() + 
                     ", description=" + description + "duration :" + getDuree() + '}';
    }

    public class ProcessAlreadyFound extends Exception {

        public ProcessAlreadyFound(String message) {
            super(message);
        }
    }

    public class ProcessNotFound extends Exception {

        public ProcessNotFound(String message) {
            super(message);
        }
    }

    public class TaskAlreadyFound extends Exception {

        public TaskAlreadyFound(String message) {
            super(message);
        }
    }

    public class TaskNotFound extends Exception {

        public TaskNotFound(String message) {
            super(message);
        }
    }
}
