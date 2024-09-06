package nfa035.project;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Filter extends JPanel implements Observer {

    private JComboBox<String> projectComboBox;
    private JComboBox<String> taskComboBox;
    private JComboBox<String> processComboBox;
    private JComboBox<String> matierePremiereComboBox;
    private JComboBox<String> autreMatiereComboBox;
    private JComboBox<String> employeeComboBox;
    private JTree resourceTree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode rootNode;
    private JButton okButton;

    private Map<String, String[]> projectToTasks;
    private Map<String, String[]> taskToProcesses;
    private Map<String, String[]> processToResources;
    JPanel statistPanel;
    JTextArea descTA;
    JScrollPane descSp;
    JLabel pnameLBL = new JLabel("");
    JTextField costTF;
    JTextField durationTF;
    JProgressBar statusProgress;

    public Filter() {
        setLayout(new BorderLayout());
        InputInterface.data.loadAllData();
        InputInterface.data.addObserver(this);

        initUIComponents();
        loadData(InputInterface.data);
        addEventListeners();

        resetComboBoxes();

        okButton.setEnabled(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof DataModule) {
            loadData(InputInterface.data);
            resetComboBoxes();
            updateTaskComboBox();
            updateProcessComboBox();
            updateResourceComboBoxes();
            okButton.setEnabled(false);

            okButton.setEnabled(false);
        }
    }

    private void initUIComponents() {
        projectComboBox = new JComboBox<>();
        taskComboBox = new JComboBox<>();
        processComboBox = new JComboBox<>();
        matierePremiereComboBox = new JComboBox<>();
        autreMatiereComboBox = new JComboBox<>();
        employeeComboBox = new JComboBox<>();
        rootNode = new DefaultMutableTreeNode("Resources");
        treeModel = new DefaultTreeModel(rootNode);
        resourceTree = new JTree(treeModel);
        okButton = new JButton("OK");
        statistPanel = new JPanel();
        statistPanel.setLayout(new BoxLayout(statistPanel, BoxLayout.Y_AXIS));
        statistPanel.setBackground(new Color(249, 232, 206));

        // Project Name Label
        pnameLBL.setBackground(new Color(249, 232, 206));
        pnameLBL.setFont(new Font("Arial", Font.BOLD, 16));
        pnameLBL.setAlignmentX(Component.LEFT_ALIGNMENT);
        statistPanel.add(pnameLBL);

        // Description Panel
        JLabel descLabel = new JLabel("Description:");
        descTA = new JTextArea(4, 20);
        descTA.setEditable(false);
        descSp = new JScrollPane(descTA);
        JPanel descPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        descPanel.setBackground(new Color(249, 232, 206));
        descPanel.add(descLabel);
        descPanel.add(descSp);
        statistPanel.add(descPanel);

        // Cost Panel
        JLabel costLabel = new JLabel("Cost:");
        costTF = new JTextField(10);
        costTF.setEditable(false);
        JPanel costPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        costPanel.setBackground(new Color(249, 232, 206));
        costPanel.add(costLabel);
        costPanel.add(costTF);
        statistPanel.add(costPanel);

        // Duration Panel
        JLabel durationLabel = new JLabel("Duration:");
        durationTF = new JTextField(10);
        durationTF.setEditable(false);
        JPanel durationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        durationPanel.setBackground(new Color(249, 232, 206));
        durationPanel.add(durationLabel);
        durationPanel.add(durationTF);
        statistPanel.add(durationPanel);

        // Status Panel with Progress Bar
        JLabel statusLabel = new JLabel("Status:");
        statusProgress = new JProgressBar(0, 100);
        statusProgress.setStringPainted(true);
        statusProgress.setPreferredSize(new Dimension(200, 30));
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(new Color(249, 232, 206));
        statusPanel.add(statusLabel);
        statusPanel.add(statusProgress);
        statistPanel.add(statusPanel);
        JPanel topPanel = new JPanel();

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        topPanel.add(createLabeledPanel("Select Project:", projectComboBox));
        topPanel.add(createLabeledPanel("Select Task:", taskComboBox));
        topPanel.add(createLabeledPanel("Select Processus:", processComboBox));
        topPanel.add(createLabeledPanel("Select Matiere Premiere:", matierePremiereComboBox));
        topPanel.add(createLabeledPanel("Select Autre Matiere:", autreMatiereComboBox));
        topPanel.add(createLabeledPanel("Select Employee:", employeeComboBox));
        topPanel.add(statistPanel);
        JScrollPane scrollPane = new JScrollPane(resourceTree);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(okButton, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(topPanel);
        centerPanel.add(bottomPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel createLabeledPanel(String labelText, Component comp) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panel.add(new JLabel(labelText));
        panel.add(comp);
        return panel;
    }

    private void loadData(DataModule data) {
        projectComboBox.removeAllItems();
        taskComboBox.removeAllItems();
        processComboBox.removeAllItems();
        matierePremiereComboBox.removeAllItems();
        autreMatiereComboBox.removeAllItems();
        employeeComboBox.removeAllItems();
        statistPanel.setVisible(false);
        durationTF.setText("");
        costTF.setText("");
        descTA.setText("");
        pnameLBL.setText("");

        projectToTasks = new HashMap<>();
        taskToProcesses = new HashMap<>();
        processToResources = new HashMap<>();
        projectComboBox.addItem("");
        for (Project project : data.getProjectsSet()) {
            List<String> tasks = new ArrayList<>();
            for (Task task : project.getTasks()) {
                tasks.add(task.getTask_id() + "-" + task.getName());
            }
            projectToTasks.put(project.getProject_id() + "-" + project.getName(), tasks.toArray(new String[0]));

            projectComboBox.addItem(project.getProject_id() + "-" + project.getName());
        }

        for (Task task : data.getTasksSet()) {
            List<String> processes = new ArrayList<>();
            for (Processus process : task.getProcessus()) {
                processes.add(process.getProcessus_id() + "-" + process.getName());
            }
            taskToProcesses.put(task.getTask_id() + "-" + task.getName(), processes.toArray(new String[0]));
        }

        for (Processus process : data.getProcessusSet()) {
            List<String> resources = new ArrayList<>();
            for (Map.Entry<MatierePremiere, ArrayList<Number>> entry : process.getMatierePremiere().entrySet()) {
                resources.add(entry.getKey().getName());
            }
            for (Map.Entry<AutreMatiere, ArrayList<Number>> entry : process.getAutreMatiere().entrySet()) {
                resources.add(entry.getKey().getName());
            }
            for (Map.Entry<Employee, Double> entry : process.getEmployee().entrySet()) {
                resources.add(entry.getKey().getFirstName());
            }
            processToResources.put(process.getName(), resources.toArray(new String[0]));
        }
    }

    private void addEventListeners() {

        projectComboBox.addActionListener(event -> updateTaskComboBox());
        taskComboBox.addActionListener(ev -> updateProcessComboBox());
        processComboBox.addActionListener(e -> updateResourceComboBoxes());
        okButton.addActionListener(e -> displayResourcesForSelectedProject());

    }

    private void resetComboBoxes() {
        taskComboBox.setEnabled(false);
        processComboBox.setEnabled(false);
        matierePremiereComboBox.setEnabled(false);
        autreMatiereComboBox.setEnabled(false);
        employeeComboBox.setEnabled(false);
    }

    private void updateTaskComboBox() {
        String selectedProject = (String) projectComboBox.getSelectedItem();
        taskComboBox.removeAllItems();
        taskComboBox.addItem("");
        processComboBox.removeAllItems();
        displayResourcesForSelectedProject();
        if (selectedProject != null && !selectedProject.isEmpty()) {
            taskComboBox.setEnabled(true);
            okButton.setEnabled(true);
            if (projectToTasks.containsKey(selectedProject)) {
                for (String task : projectToTasks.get(selectedProject)) {
                    taskComboBox.addItem(task);
                }
            }
        } else {
            taskComboBox.setEnabled(false);
            processComboBox.setEnabled(false);
            okButton.setEnabled(false);
            statistPanel.setVisible(false);

        }
    }

    private void updateProcessComboBox() {
        String selectedTask = (String) taskComboBox.getSelectedItem();

        processComboBox.removeAllItems();
        processComboBox.addItem("");
        matierePremiereComboBox.removeAllItems();
        autreMatiereComboBox.removeAllItems();
        employeeComboBox.removeAllItems();

        if (selectedTask != null && !selectedTask.equals("")) {
            processComboBox.setEnabled(true);
            matierePremiereComboBox.setEnabled(false);
            autreMatiereComboBox.setEnabled(false);
            employeeComboBox.setEnabled(false);

            if (taskToProcesses.containsKey(selectedTask)) {
                for (String process : taskToProcesses.get(selectedTask)) {
                    processComboBox.addItem(process);
                }
            }
        } else {
            processComboBox.setEnabled(false);
        }
    }

    private void updateResourceComboBoxes() {
        String selectedProcess = (String) processComboBox.getSelectedItem();

        matierePremiereComboBox.removeAllItems();
        autreMatiereComboBox.removeAllItems();
        employeeComboBox.removeAllItems();

        if (selectedProcess != null && !selectedProcess.equals("")) {
            matierePremiereComboBox.setEnabled(true);
            autreMatiereComboBox.setEnabled(true);
            employeeComboBox.setEnabled(true);

            matierePremiereComboBox.addItem("");
            autreMatiereComboBox.addItem("");
            employeeComboBox.addItem("");

            populateMatierePremiereComboBox(selectedProcess);
            populateAutreMatiereComboBox(selectedProcess);
            populateEmployeeComboBox(selectedProcess);
        } else {
            matierePremiereComboBox.setEnabled(false);
            autreMatiereComboBox.setEnabled(false);
            employeeComboBox.setEnabled(false);
        }
    }

    private void populateMatierePremiereComboBox(String selectedProcess) {
        int id = Integer.parseInt(selectedProcess.substring(0, selectedProcess.indexOf('-')));
        Processus p = InputInterface.data.getProcessusByid(id);
        for (Map.Entry<MatierePremiere, ArrayList<Number>> entry : p.getMatierePremiere().entrySet()) {
            matierePremiereComboBox.addItem(entry.getKey().getName());
        }

    }

    private void populateAutreMatiereComboBox(String selectedProcess) {
        int id = Integer.parseInt(selectedProcess.substring(0, selectedProcess.indexOf('-')));
        Processus p = InputInterface.data.getProcessusByid(id);
        for (Map.Entry<AutreMatiere, ArrayList<Number>> entry : p.getAutreMatiere().entrySet()) {
            autreMatiereComboBox.addItem(entry.getKey().getName());
        }

    }

    private void populateEmployeeComboBox(String selectedProcess) {
        int id = Integer.parseInt(selectedProcess.substring(0, selectedProcess.indexOf('-')));
        Processus p = InputInterface.data.getProcessusByid(id);
        for (Map.Entry<Employee, Double> entry : p.getEmployee().entrySet()) {
            employeeComboBox.addItem(entry.getKey().getFirstName() + " " + entry.getKey().getLastName());
        }

    }

    private void displayResourcesForSelectedProject() {
        String selectedProject = (String) projectComboBox.getSelectedItem();
        String selectedTask = (String) taskComboBox.getSelectedItem();
        String selectedProcess = (String) processComboBox.getSelectedItem();
        String selectedMatierePremiere = (String) matierePremiereComboBox.getSelectedItem();
        String selectedAutreMatiere = (String) autreMatiereComboBox.getSelectedItem();
        String selectedEmployee = (String) employeeComboBox.getSelectedItem();

        rootNode.removeAllChildren();
        treeModel.reload();
        statistPanel.setVisible(false);

        InputInterface.data.loadAllData();

        if (selectedProject != null && !selectedProject.isEmpty()) {
            statistPanel.setVisible(true);
            Project project = InputInterface.data.getProjectById(Integer.parseInt(selectedProject.substring(0, selectedProject.indexOf('-'))));
            pnameLBL.setText(project.getName());
            descTA.setText(project.getDescription());
            String cost = String.valueOf(project.calculeCoutTotal()) + " $";
            costTF.setText(cost);
            String duree = String.valueOf(project.getDuree()) + " hours";
            durationTF.setText(duree);

            statusProgress.setValue(70);
            DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode("Project: " + project.getName() + ", Description: " + project.getDescription() + ", Cost: " + project.calculeCoutTotal() + "$" + " Duration : " + project.getDuree() + " hours , status :" + project.getStatus() + " %");
            rootNode.add(projectNode);

            if (selectedTask == null || selectedTask.isEmpty()) {
                displayProjectResources(project, projectNode);
            } else {
                for (Task task : project.getTasks()) {
                    if (Integer.parseInt(selectedTask.substring(0, selectedTask.indexOf('-'))) == task.getTask_id()) {
                        DefaultMutableTreeNode taskNode = new DefaultMutableTreeNode("Task: " + task.getName() + ", Duration: " + task.getDuree() + ", Status: " + task.getStatus() + "%" + ", Cost:" + task.calculeCoutTotal() + "$" + ", Type: " + task.getType().getDesc() + ", Description: " + task.getDescription());
                        projectNode.add(taskNode);

                        if (selectedProcess == null || selectedProcess.isEmpty()) {
                            displayTaskResources(task, taskNode);
                        } else {
                            for (Processus process : task.getProcessus()) {
                                if (process.getProcessus_id() == Integer.parseInt(selectedProcess.substring(0, selectedProcess.indexOf('-')))) {
                                    DefaultMutableTreeNode processNode = new DefaultMutableTreeNode("Processus: " + process.getName() + ", Description: " + process.getDescriptionProcessus() + ", Duration: " + process.getDuree() + " h " + ", Status: " + process.getStatus() + ", Cost: " + process.calculeCoutUsed() + " $");
                                    taskNode.add(processNode);

                                    if (selectedMatierePremiere == null && selectedAutreMatiere == null && selectedEmployee == null) {
                                        displayProcessResources(process, processNode);
                                    } else {
                                        if (selectedMatierePremiere != null && !selectedMatierePremiere.isEmpty()) {
                                            displaySelectedMatierePremiereResources(process, processNode, selectedMatierePremiere);
                                        }
                                        if (selectedAutreMatiere != null && !selectedAutreMatiere.isEmpty()) {
                                            displaySelectedAutreMatiereResources(process, processNode, selectedAutreMatiere);
                                        }
                                        if (selectedEmployee != null && !selectedEmployee.isEmpty()) {
                                            displaySelectedEmployeeResources(process, processNode, selectedEmployee);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        treeModel.reload();
    }

    private void displayProjectResources(Project project, DefaultMutableTreeNode parent) {
        for (Task task : project.getTasks()) {
            DefaultMutableTreeNode taskNode = new DefaultMutableTreeNode("Task: " + task.getName() + ", Duration: " + task.getDuree() + ", Status: " + task.getStatus() + "%" + ", Cost:" + task.calculeCoutTotal() + "$" + ", Type: " + task.getType().getDesc() + ", Description: " + task.getDescription());
            parent.add(taskNode);
            displayTaskResources(task, taskNode);
        }
    }

    private void displayTaskResources(Task task, DefaultMutableTreeNode parent) {
        for (Processus process : task.getProcessus()) {
            DefaultMutableTreeNode processNode = new DefaultMutableTreeNode("Processus: " + process.getName() + ", Description: " + process.getDescriptionProcessus() + ", Duration: " + process.getDuree() + " h " + ", Status: " + process.getStatus() + ", Cost: " + process.calculeCoutUsed() + " $");
            parent.add(processNode);
            displayProcessResources(process, processNode);
        }
    }

    private void displayProcessResources(Processus process, DefaultMutableTreeNode parent) {
        displayMatierePremiereResources(process, parent);
        displayAutreMatiereResources(process, parent);
        displayEmployeeResources(process, parent);
    }

    private void displayMatierePremiereResources(Processus process, DefaultMutableTreeNode parent) {
        DefaultMutableTreeNode matp = new DefaultMutableTreeNode("Matieres Premieres :");
        parent.add(matp);
        for (Map.Entry<MatierePremiere, ArrayList<Number>> mpEntry : process.getMatierePremiere().entrySet()) {
            MatierePremiere mp = mpEntry.getKey();
            ArrayList<Number> details = mpEntry.getValue();
            DefaultMutableTreeNode mpNode = new DefaultMutableTreeNode(mp.getName() + " Quantity: " + details.get(0) + ", Prix unitaire " + mp.getPrixUnitaire() + "$" + ", total cout: " + ((double) details.get(0) * mp.getPrixUnitaire()) + "$");
            matp.add(mpNode);
        }
    }

    private void displayAutreMatiereResources(Processus process, DefaultMutableTreeNode parent) {
        DefaultMutableTreeNode autmat = new DefaultMutableTreeNode("Autres matieres :");
        parent.add(autmat);
        for (Map.Entry<AutreMatiere, ArrayList<Number>> amEntry : process.getAutreMatiere().entrySet()) {
            AutreMatiere am = amEntry.getKey();
            ArrayList<Number> details = amEntry.getValue();
            DefaultMutableTreeNode amNode = new DefaultMutableTreeNode(am.getName() + " Quantity " + details.get(0) + ", Prix unitaire " + am.getPrixUnitaire() + "$ ," + " total cout: " + ((double) details.get(0) * am.getPrixUnitaire()) + "$");
            autmat.add(amNode);
        }
    }

    private void displayEmployeeResources(Processus process, DefaultMutableTreeNode parent) {
        DefaultMutableTreeNode empnode = new DefaultMutableTreeNode("Employees :");
        parent.add(empnode);
        for (Map.Entry<Employee, Double> empEntry : process.getEmployee().entrySet()) {
            Employee emp = empEntry.getKey();
            Double nbHours = empEntry.getValue();
            DefaultMutableTreeNode empNode = new DefaultMutableTreeNode(emp.getFirstName() + " " + emp.getLastName() + ", Tarrif Heure: " + emp.getFonction().getTarrifHeure() + "$ , " + " nb heurs :" + nbHours + ", cout total :" + (nbHours * emp.getFonction().getTarrifHeure()) + "$");
            empnode.add(empNode);
        }
    }

    private void displaySelectedMatierePremiereResources(Processus process, DefaultMutableTreeNode parent, String selectedMatierePremiere) {
        DefaultMutableTreeNode matp = new DefaultMutableTreeNode("Matieres Premieres :");
        parent.add(matp);
        for (Map.Entry<MatierePremiere, ArrayList<Number>> mpEntry : process.getMatierePremiere().entrySet()) {
            MatierePremiere mp = mpEntry.getKey();
            if (mp.getName().equals(selectedMatierePremiere)) {
                ArrayList<Number> details = mpEntry.getValue();
                DefaultMutableTreeNode mpNode = new DefaultMutableTreeNode(mp.getName() + " Quantity: " + details.get(0) + ", Prix unitaire " + mp.getPrixUnitaire() + "$" + ", total cout: " + ((double) details.get(0) * mp.getPrixUnitaire()) + "$");
                matp.add(mpNode);
            }
        }
    }

    private void displaySelectedAutreMatiereResources(Processus process, DefaultMutableTreeNode parent, String selectedAutreMatiere) {
        DefaultMutableTreeNode autmat = new DefaultMutableTreeNode("Autres matieres :");
        parent.add(autmat);
        for (Map.Entry<AutreMatiere, ArrayList<Number>> amEntry : process.getAutreMatiere().entrySet()) {
            AutreMatiere am = amEntry.getKey();
            if (am.getName().equals(selectedAutreMatiere)) {
                ArrayList<Number> details = amEntry.getValue();
                DefaultMutableTreeNode amNode = new DefaultMutableTreeNode(am.getName() + " Quantity " + details.get(0) + ", Prix unitaire " + am.getPrixUnitaire() + "$ ," + " total cout: " + ((double) details.get(0) * am.getPrixUnitaire()) + "$");
                autmat.add(amNode);
            }
        }
    }

    private void displaySelectedEmployeeResources(Processus process, DefaultMutableTreeNode parent, String selectedEmployee) {
        DefaultMutableTreeNode empnode = new DefaultMutableTreeNode("Employees");
        parent.add(empnode);
        for (Map.Entry<Employee, Double> empEntry : process.getEmployee().entrySet()) {
            Employee emp = empEntry.getKey();
            if ((emp.getFirstName() + " " + emp.getLastName()).equals(selectedEmployee)) {
                Double nbHours = empEntry.getValue();
                DefaultMutableTreeNode empNode = new DefaultMutableTreeNode(emp.getFirstName() + " " + emp.getLastName() + ", Tarrif Heure: " + emp.getFonction().getTarrifHeure() + "$ , " + " nb heurs :" + nbHours + ", cout total :" + (nbHours * emp.getFonction().getTarrifHeure()) + "$");
                empnode.add(empNode);
            }
        }
    }
}