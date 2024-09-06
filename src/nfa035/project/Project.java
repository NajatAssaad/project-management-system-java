package nfa035.project;

import java.io.Serializable;
import java.util.HashSet;
import java.util.ArrayList;
import nfa035.project.IDManager;

public class Project implements Serializable {

    private int project_id;
    private String name;
    private String description;
    Client client;

    private HashSet<Task> tasks;
    private static final long serialVersionUID = -3485612679762510575L;

    public Project(String name, String desc, Client c) {
        this.project_id = IDManager.getNextId(Project.class.getSimpleName());
        this.name = name;
        this.description = desc;
        this.client = c;
        this.tasks = new HashSet<>();
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getProject_id() {
        return project_id;
    }

    public Client getClient() {
        return client;
    }

    public HashSet<Task> getTasks() {
        return tasks;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTask(Task t) throws Exception {
        if (tasks.contains(t)) {
            throw new TaskAlreadyFound("La tâche est déjà appliquée");
        }
        tasks.add(t);
    }

    public void removeTask(Task t) throws Exception {
        if (tasks.contains(t)) {
            tasks.remove(t);
        } else {
            throw new TaskNotFound("Ce task n'appartient pas à ce projet");
        }
    }

    public void removeTask(int idt) throws Exception {
        Task tas= this.getTaskById(idt);
        if (tasks.contains(tas)) {
            tasks.remove(tas);
        } else {
            throw new TaskNotFound("Ce task n'appartient pas à ce projet");
        }
    }

    public double calculeCoutTotal() {
        double cout = 0.0;
        for (Task t : tasks) {
            cout += t.calculeCoutTotal();
        }
        return cout;
    }

    public double getDuree() {
        double duree = 0.0;
        for (Task t : tasks) {

            duree += t.getDuree();
        }
        return duree;
    }

    public double getStatus() {
        double status = 0.0;
        for (Task t : tasks) {
            status += t.getStatus();
        }
        status = (status / tasks.size());
        return Math.round(status);
    }

    public Task getTaskById(int id) throws Exception {
        for (Task t : tasks) {
            if (t.getTask_id() == id) {
                return t;
            }
        }
        throw new TaskNotFound("Task with id " + id + " not found in this project");
    }

    @Override
    public String toString() {
        return "Project{" + "project_id=" + project_id + ", name=" + name + ", description=" + description + ", client=" + client + '}';
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
