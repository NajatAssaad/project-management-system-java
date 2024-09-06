package nfa035.project;

import java.util.Observable;
import java.io.*;
import java.util.HashSet;
import nfa035.project.Project;
import nfa035.project.Task;
import nfa035.project.Processus;
import nfa035.project.TaskType;
import nfa035.project.Materiel;
import nfa035.project.Employee;
import nfa035.project.Fonction;
import nfa035.project.Client;
import nfa035.project.Specialite;

public class DataModule extends Observable {

    private static HashSet<Project> projectsSet = new HashSet<>();
    private static HashSet<Task> tasksSet = new HashSet<>();
    private static HashSet<Processus> processusSet = new HashSet<>();
    private static HashSet<TaskType> taskTypesSet = new HashSet<>();
    private static HashSet<Materiel> materielsSet = new HashSet<>();
    private static HashSet<Employee> employeesSet = new HashSet<>();
    private static HashSet<Fonction> fonctionsSet = new HashSet<>();
    private static HashSet<Client> clientsSet = new HashSet<>();
    private static HashSet<Specialite> specialitesSet = new HashSet<>();

    // Constructor
    public DataModule() {
    }

    // Getters
    public HashSet<Project> getProjectsSet() {
        return projectsSet;
    }

    public HashSet<Task> getTasksSet() {
        return tasksSet;
    }

    public HashSet<Processus> getProcessusSet() {
        return processusSet;
    }

    public HashSet<TaskType> getTaskTypeSet() {
        return taskTypesSet;
    }

    public HashSet<Materiel> getMaterielsSet() {
        return materielsSet;
    }

    public HashSet<Employee> getEmployeesSet() {
        return employeesSet;
    }

    public HashSet<Fonction> getFonctionsSet() {
        return fonctionsSet;
    }

    public HashSet<Client> getClientsSet() {
        return clientsSet;
    }

    public HashSet<Specialite> getSpecialitesSet() {
        return specialitesSet;
    }

    // Methods to populate the sets from files
    public void loadProjects() {
        projectsSet = loadData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\projects.obj");
        //projectsSet = loadData("C:\\JanFiles\\projects.obj");
    }

    public void loadTasks() {
        tasksSet = loadData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\tasks.obj");
    }

    public void loadProcessus() {
        processusSet = loadData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\processus.obj");
    }

    public void loadMateriels() {
        materielsSet = loadData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\materiels.obj");
    }

    public void loadEmployees() {
        employeesSet = loadData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\employees.obj");
    }

    public void loadFonctions() {
        fonctionsSet = loadData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\fonctions.obj");
    }

    public void loadTaskTypes() {
        taskTypesSet = loadData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\taskTypes.obj");
    }

    public void loadClients() {
        clientsSet = loadData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\clients.obj");
    }

    public void loadSpecialites() {
        specialitesSet = loadData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\specialites.obj");
    }

    public void addProject(Project project) throws AlreadyExistsException {
        if (projectsSet.contains(project)) {
            throw new AlreadyExistsException("Project already exists.");
        }
        projectsSet.add(project);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void addTask(Task task) throws AlreadyExistsException {
        if (tasksSet.contains(task)) {
            throw new AlreadyExistsException("Task already exists.");
        }
        tasksSet.add(task);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void addProcessus(Processus processus) throws AlreadyExistsException {
        if (processusSet.contains(processus)) {
            throw new AlreadyExistsException("Processus already exists.");
        }
        processusSet.add(processus);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void addMateriel(Materiel materiel) throws AlreadyExistsException {
        if (materielsSet.contains(materiel)) {
            throw new AlreadyExistsException("Materiel already exists.");
        }
        materielsSet.add(materiel);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void addEmployee(Employee employee) throws AlreadyExistsException {
        if (employeesSet.contains(employee)) {
            throw new AlreadyExistsException("Employee already exists.");
        }
        employeesSet.add(employee);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void addFonction(Fonction fonction) throws AlreadyExistsException {
        if (fonctionsSet.contains(fonction)) {
            throw new AlreadyExistsException("Fonction already exists.");
        }
        fonctionsSet.add(fonction);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void addTaskType(TaskType taskType) throws AlreadyExistsException {
        if (taskTypesSet.contains(taskType)) {
            throw new AlreadyExistsException("TaskType already exists.");
        }
        taskTypesSet.add(taskType);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void addClient(Client client) throws AlreadyExistsException {
        if (clientsSet.contains(client)) {
            throw new AlreadyExistsException("Client already exists.");
        }
        clientsSet.add(client);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void addSpecialite(Specialite specialite) throws AlreadyExistsException {
        if (specialitesSet.contains(specialite)) {
            throw new AlreadyExistsException("Specialite already exists.");
        }
        specialitesSet.add(specialite);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void removeProjectById(int id) throws NotFoundException {
        Project found = null;
        for (Project project : projectsSet) {
            if (project.getProject_id() == id) {
                found = project;
                break;
            }
        }
        if (found == null) {
            throw new NotFoundException("Project with ID " + id + " not found.");
        }
        projectsSet.remove(found);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void removeTaskById(int id) throws NotFoundException {
        Task found = null;
        for (Task task : tasksSet) {
            if (task.getTask_id() == id) {
                found = task;
                break;
            }
        }
        if (found == null) {
            throw new NotFoundException("Task with ID " + id + " not found.");
        }
        tasksSet.remove(found);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void removeProcessusById(int id) throws NotFoundException {
        Processus found = null;
        for (Processus processus : processusSet) {
            if (processus.getProcessus_id() == id) {
                found = processus;
                break;
            }
        }
        if (found == null) {
            throw new NotFoundException("Processus with ID " + id + " not found.");
        }
        processusSet.remove(found);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void removeMaterielById(int id) throws NotFoundException {
        Materiel found = null;
        for (Materiel materiel : materielsSet) {
            if (materiel.getId() == id) {
                found = materiel;
                break;
            }
        }
        if (found == null) {
            throw new NotFoundException("Materiel with ID " + id + " not found.");
        }
        materielsSet.remove(found);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void removeEmployeeById(int id) throws NotFoundException {
        Employee found = null;
        for (Employee employee : employeesSet) {
            if (employee.getId() == id) {
                found = employee;
                break;
            }
        }
        if (found == null) {
            throw new NotFoundException("Employee with ID " + id + " not found.");
        }
        employeesSet.remove(found);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void removeFonctionById(int id) throws NotFoundException {
        Fonction found = null;
        for (Fonction fonction : fonctionsSet) {
            if (fonction.getFId() == id) {
                found = fonction;
                break;
            }
        }
        if (found == null) {
            throw new NotFoundException("Fonction with ID " + id + " not found.");
        }
        fonctionsSet.remove(found);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void removeTaskTypeById(int id) throws NotFoundException {
        TaskType found = null;
        for (TaskType taskType : taskTypesSet) {
            if (taskType.getTypeid() == id) {
                found = taskType;
                break;
            }
        }
        if (found == null) {
            throw new NotFoundException("TaskType with ID " + id + " not found.");
        }
        taskTypesSet.remove(found);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void removeClientById(int id) throws NotFoundException {
        Client found = null;
        for (Client client : clientsSet) {
            if (client.getId() == id) {
                found = client;
                break;
            }
        }
        if (found == null) {
            throw new NotFoundException("Client with ID " + id + " not found.");
        }
        clientsSet.remove(found);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void removeSpecialiteById(int id) throws NotFoundException {
        Specialite found = null;
        for (Specialite specialite : specialitesSet) {
            if (specialite.getId() == id) {
                found = specialite;
                break;
            }
        }
        if (found == null) {
            throw new NotFoundException("Specialite with ID " + id + " not found.");
        }
        specialitesSet.remove(found);
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public Client getClientById(int id) {
        Client found = null;
        for (Client client : clientsSet) {
            if (client.getId() == id) {
                found = client;

                break;
            }
        }

        return found;
    }

    public Employee getEmployeeById(int id) {
        Employee found = null;
        for (Employee emp : employeesSet) {
            if (emp.getId() == id) {
                found = emp;
                break;
            }
        }

        return found;
    }

    public Project getProjectById(int id) {
        Project found = null;
        for (Project project : projectsSet) {
            if (project.getProject_id() == id) {
                found = project;
                break;
            }
        }

        return found;
    }

    public TaskType getTaskTypeById(int id) {
        TaskType found = null;
        for (TaskType taskt : taskTypesSet) {
            if (taskt.getTypeid() == id) {
                found = taskt;

                break;
            }
        }

        return found;
    }

    public Task getTaskById(int id) {
        Task found = null;
        for (Task task : tasksSet) {
            if (task.getTask_id() == id) {
                found = task;

                break;
            }
        }

        return found;
    }

    public Fonction getFonctionById(int id) {
        Fonction found = null;
        for (Fonction fon : fonctionsSet) {
            if (fon.getFId() == id) {
                found = fon;

                break;
            }
        }

        return found;
    }

    public Specialite getSpecialiteById(int id) {
        Specialite found = null;
        for (Specialite spec : specialitesSet) {
            if (spec.getId() == id) {
                found = spec;

                break;
            }
        }

        return found;
    }

    public Processus getProcessusByid(int id) {
        Processus found = null;
        for (Processus pross : processusSet) {
            if (pross.getProcessus_id() == id) {
                found = pross;

                break;
            }
        }

        return found;
    }

    public Materiel getMaterielById(int id) {
        Materiel found = null;
        for (Materiel mat : materielsSet) {
            if (mat.getId() == id) {
                found = mat;

                break;
            }
        }

        return found;
    }

    public Project getprojectOfTask(Task t) {
        Project p = null;
        for (Project project : projectsSet) {
            HashSet<Task> tasksofp = project.getTasks();

            for (Task task : tasksofp) {
                if (task.getTask_id() == t.getTask_id()) {
                    p = project;
                }

            }
        }
        return p;

    }

    public Task getTaskOfProcessus(Processus p) {
        Task t = null;
        for (Task task : tasksSet) {
            HashSet<Processus> processusoft = task.getProcessus();

            for (Processus process : processusoft) {
                if (process.getProcessus_id() == p.getProcessus_id()) {
                    t = task;
                }

            }
        }
        return t;

    }

    public void thereIsChange() {
        saveAllData();
        setChanged();
        notifyObservers();
    }

    public void loadAllData() {
        loadProjects();
        loadTasks();
        loadProcessus();
        loadMateriels();
        loadEmployees();
        loadFonctions();
        loadTaskTypes();
        loadClients();
        loadSpecialites();
    }

    public void saveProjects() {
        saveData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\projects.obj", projectsSet);
        //saveData("C:\\JanFiles\\projects.obj", projectsSet);
    }

    public void saveTasks() {
        saveData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\tasks.obj", tasksSet);
    }

    public void saveProcessus() {
        saveData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\processus.obj", processusSet);

    }

    public void saveTaskTypes() {
        saveData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\taskTypes.obj", taskTypesSet);

    }

    public void saveMateriels() {
        saveData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\materiels.obj", materielsSet);
    }

    public void saveEmployees() {
        saveData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\employees.obj", employeesSet);
    }

    public void saveFonctions() {
        saveData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\fonctions.obj", fonctionsSet);
    }

    public void saveClients() {
        saveData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\clients.obj", clientsSet);
    }

    public void saveSpecialites() {
        saveData("C:\\JanFiles\\CNAM\\Projects\\NFA035_CODE_GR84\\build\\classes\\nfa035\\project\\Data\\specialites.obj", specialitesSet);
    }

    public void saveAllData() {
        saveProjects();
        saveTasks();
        saveProcessus();
        saveMateriels();
        saveEmployees();
        saveFonctions();
        saveTaskTypes();
        saveClients();
        saveSpecialites();
    }

    // Generic method to load data from a file to use it to load data in each set
    private <T extends Serializable> HashSet<T> loadData(String filePath) {

        HashSet<T> objSet = new HashSet<>();

        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object obj;
            while ((obj = ois.readObject()) != null) {

                objSet.add((T) obj);

            }

        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error " + e.getMessage());
        }
        return objSet;
    }

    private <T extends Serializable> void saveData(String filePath, HashSet<T> objset) {
        try {
            File file = new File(filePath);

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // move the elements to the file
            for (T element : objset) {
                oos.writeObject(element);
            }

            // close the ObjectOutputStream 
            oos.close();

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    class AlreadyExistsException extends Exception {

        public AlreadyExistsException(String message) {
            super(message);
        }

    }

    class NotFoundException extends Exception {

        public NotFoundException(String message) {
            super(message);
        }
    }

}
