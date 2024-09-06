package nfa035.project;


import java.util.Observer;
import java.util.Observable;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputInterface extends JFrame implements Observer {

    static DataModule data;

    String[] projectsColumns = {"Project ID", "Name", "Status", "Cost", "duration", "Client"};
    String[] tasksColumns = {"Task ID", "Name", "Status", "Cost", "Duration", "Assigned to"};
    String[] processusColumns = {"Processus ID", "Name", "Status", "Duration", "Assigned to"};
    String[] clientsColumns = {"Client ID", "Name"};
    String[] employeesColumns = {"Employee ID", "First Name", "Last Name", "Fonction", "tarrif Heure"};
    String[] matieresColumns = {"Matiere ID", "Name", "Prix Unitaire"};
    String[] autresColumns = {"Matiere ID", "Name", "Prix Unitaire"};
    String[] taskTypesColumns = {"Type ID", "Name"};
    String[] specialiteColumns = {"Specialite ID", "Description"};
    String[] fonctionColumns = {"Fonction ID", "Description", "tarrif heure"};

    DefaultTableModel projectsTableModel = new NonEditableTableModel(null, projectsColumns);
    DefaultTableModel tasksTableModel = new NonEditableTableModel(null, tasksColumns);
    DefaultTableModel processusTableModel = new NonEditableTableModel(null, processusColumns);
    DefaultTableModel clientsTableModel = new NonEditableTableModel(null, clientsColumns);
    DefaultTableModel employeesTableModel = new NonEditableTableModel(null, employeesColumns);
    DefaultTableModel matieresTableModel = new NonEditableTableModel(null, matieresColumns);
    DefaultTableModel autresTableModel = new NonEditableTableModel(null, autresColumns);
    DefaultTableModel taskTypesTableModel = new NonEditableTableModel(null, taskTypesColumns);
    DefaultTableModel specialiteTableModel = new NonEditableTableModel(null, specialiteColumns);
    DefaultTableModel fonctionTableModel = new NonEditableTableModel(null, fonctionColumns);
    JComboBox<String> clientsComboBox = new JComboBox<>();
    JComboBox<String> TaskTypeCombo = new JComboBox<>();
    JComboBox<String> ProjectsCombo = new JComboBox<>();
    JComboBox<String> TaskCombo = new JComboBox<>();
    JComboBox<String> EmployeeFonctionComboBox = new JComboBox<>();
    JComboBox<String> specialiteEmployeeCombobox = new JComboBox<>();
    JComboBox<String> employeeComboBox = new JComboBox<>();
    JComboBox<String> autreMatiereComboBox = new JComboBox<>();
    JComboBox<String> matierePremiereComboBox = new JComboBox<>();

    public InputInterface() {
        data = new DataModule();
        data.addObserver(this);
        data.loadAllData();
        JTabbedPane tabbedPane = new JTabbedPane(); // creation the tabbedPane
        tabbedPane.setBackground(new Color(213, 225, 206));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel9 = new JPanel();
        JPanel panel10 = new JPanel();

        // add the panes as tabs in the tabbedPane
        tabbedPane.addTab("Projects", panel1);
        tabbedPane.addTab("Tasks", panel2);
        tabbedPane.addTab("Processus", panel3);
        tabbedPane.addTab("Clients", panel4);
        tabbedPane.addTab("Employees", panel5);
        tabbedPane.addTab("Matieres Premieres", panel6);
        tabbedPane.addTab("Autres Matieres", panel7);
        tabbedPane.addTab("Task Types", panel8);
        tabbedPane.addTab("Specialite", panel9);
        tabbedPane.addTab("Fonction", panel10);
        // arrays of columns name for each table

        //create the default empty table model for each table
        // creating the Jtable for each Table Model
        JTable projectsTable = new JTable(projectsTableModel);
        JTable tasksTable = new JTable(tasksTableModel);
        JTable processusTable = new JTable(processusTableModel);
        JTable clientsTable = new JTable(clientsTableModel);
        JTable employeesTable = new JTable(employeesTableModel);
        JTable matieresTable = new JTable(matieresTableModel);
        JTable autresTable = new JTable(autresTableModel);
        JTable taskTypesTable = new JTable(taskTypesTableModel);
        JTable specialiteTable = new JTable(specialiteTableModel);
        JTable fonctionTable = new JTable(fonctionTableModel);

        //change the background
        employeesTable.setBackground(new Color(249, 232, 206));
        matieresTable.setBackground(new Color(249, 232, 206));
        autresTable.setBackground(new Color(249, 232, 206));
        taskTypesTable.setBackground(new Color(249, 232, 206));
        specialiteTable.setBackground(new Color(249, 232, 206));
        fonctionTable.setBackground(new Color(249, 232, 206));
        // set single selection
        projectsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tasksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        processusTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        matieresTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        autresTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskTypesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        specialiteTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fonctionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // fill the projects table
        fillTheProjectsTableModel();
        // project panel
        JPanel addProjectPanel = new JPanel();
        addProjectPanel.setLayout(null);

        JLabel HeaderADDProjectlable = new JLabel("ADD PROJECT");
        JLabel projNameLable = new JLabel("Name:");
        JTextField projNameTextF = new JTextField();
        projNameTextF.setBackground(new Color(249, 232, 206));
        JLabel projDescLable = new JLabel("Description:");
        JTextArea projDescTA = new JTextArea(5, 30);
        projDescTA.setBackground(new Color(249, 232, 206));
        JScrollPane projDescTAsp = new JScrollPane(projDescTA);
        clientsComboBox.setBackground(new Color(249, 232, 206));
        JLabel clientLabel = new JLabel("Client:");

        JButton addProjectBtn = new JButton("ADD PROJECT");
        addProjectBtn.setBackground(new Color(249, 232, 206));
        JButton removeProjectBtn = new JButton("Remove Selected PROJECT");
        removeProjectBtn.setBackground(new Color(249, 232, 206));
        fillClientsCombobox();
        addProjectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Handle add project action
                String name = projNameTextF.getText().trim();
                String description = projDescTA.getText().trim();
                String client = (String) clientsComboBox.getSelectedItem();
                int firstSpaceIndex = client.indexOf(" ");
                String clientIdString = client.substring(0, firstSpaceIndex);
                int clientId = Integer.parseInt(clientIdString);

                Client newClient = data.getClientById(clientId);

                // Create a new Project object
                Project newProject = new Project(name, description, newClient); //create the new project

                try {
                    // Add the new project to the DataModule
                    data.addProject(newProject);

                    

                    //  clear input fields after successful addition
                    projNameTextF.setText("");
                    projDescTA.setText("");

                    JOptionPane.showMessageDialog(addProjectPanel,
                            "Project added successfully.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (DataModule.AlreadyExistsException ex) {
                    JOptionPane.showMessageDialog(addProjectPanel,
                            "Project already exists.",
                            "Duplicate Project",
                            JOptionPane.ERROR_MESSAGE);

                }

            }
        });

        removeProjectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = projectsTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a project to remove.");
                    return;
                }

                int projectId = (int) projectsTable.getValueAt(selectedRow, 0);

                try {
                    Project p = data.getProjectById(projectId);
                    
                    HashSet<Task> tasksproj = p.getTasks();
                    for (Task task : tasksproj) {

                        HashSet<Processus> process = task.getProcessus();
                        for (Processus pro : process) {
                            data.removeProcessusById(pro.getProcessus_id());
                        }
                        data.removeTaskById(task.getTask_id());
                    }
                    data.removeProjectById(projectId);

                    JOptionPane.showMessageDialog(null, "Project removed successfully!");

                    // Update the projects table model
                } catch (DataModule.NotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Project not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ////////////////////////////////////////////////////////////////////////
        HeaderADDProjectlable.setBounds(
                140, 10, 200, 30);
        HeaderADDProjectlable.setFont(
                new Font("Arial", Font.PLAIN, 22));
        HeaderADDProjectlable.setForeground(
                new Color(249, 232, 206));

        projNameLable.setBounds(
                20, 50, 80, 20);
        projDescLable.setBounds(
                20, 80, 80, 20);
        projNameTextF.setBounds(
                120, 50, 200, 20);
        projDescTAsp.setBounds(
                120, 80, 200, 100);

        clientLabel.setBounds(
                20, 190, 80, 20);
        clientsComboBox.setBounds(
                120, 190, 200, 20);

        addProjectBtn.setBounds(
                120, 230, 200, 30);
        removeProjectBtn.setBounds(
                120, 270, 200, 30);

// Add elements to the panel
        addProjectPanel.add(HeaderADDProjectlable);

        addProjectPanel.add(projNameLable);

        addProjectPanel.add(projDescLable);

        addProjectPanel.add(projNameTextF);

        addProjectPanel.add(projDescTAsp);

        addProjectPanel.add(clientLabel);

        addProjectPanel.add(clientsComboBox);

        addProjectPanel.add(addProjectBtn);

        addProjectPanel.add(removeProjectBtn);

        addProjectPanel.setBackground(
                new Color(139, 168, 136));
        addProjectPanel.setBounds(
                600, 50, 400, 320); // Adjusted bounds to fit all components

// Assuming panel1 is your main panel
        panel1.setLayout(
                null);
        panel1.add(addProjectPanel);

// Adjust the bounds of projectsTable and its scroll pane as needed
        JScrollPane projSC = new JScrollPane(projectsTable);

        projSC.setBackground(new Color(249, 232, 206));
        projectsTable.setBackground(new Color(249, 232, 206));
        projSC.setBounds(
                25, 50, 500, 300);
        panel1.add(projSC);

        // give the add button the action 
        /////////////////////////////////////////////////////////////////////////
        // Task panel
        panel2.setLayout(
                null);
        JScrollPane tasksSc = new JScrollPane(tasksTable);

        tasksSc.setBackground(
                new Color(249, 232, 206));
        tasksTable.setBackground(
                new Color(249, 232, 206));
        tasksSc.setBounds(
                25, 50, 500, 300);

// Add task panel
        JPanel addTaskPanel = new JPanel();
        addTaskPanel.setBackground(new Color(139, 168, 136));
        addTaskPanel.setBounds(600, 50, 400, 350); // Adjusted height to accommodate all components
        addTaskPanel.setLayout(null);

        JLabel HeaderADDTasklabel = new JLabel("ADD Task");
        JLabel TaskNameLabel = new JLabel("Name:");
        JTextField TaskNameTextF = new JTextField();
        JLabel TaskDescLabel = new JLabel("Description:");
        JTextArea TaskDescTA = new JTextArea(5, 30);
        JScrollPane textAreaTaskDescSC = new JScrollPane(TaskDescTA);
        JButton addTaskBtn = new JButton("ADD TASK");
        JButton removeTaskBtn = new JButton("Remove Selected TASK");
        JLabel TaskTypeLabel = new JLabel("Type:");
        JLabel SelectProjectLabel = new JLabel("Select a Project:");
        fillTaskTypeComboBox();
        fillProjectscomboBox();
        fillTheTasksTableModel();
        fillEmployeesCombobox();
        fillMatierspremierscombobox();
        fillAutreMatieresComboBox();
        ///////////////////////////////////////////
        addTaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if all fields are filled
                if (TaskNameTextF.getText().isEmpty()
                        || TaskDescTA.getText().isEmpty()
                        || TaskTypeCombo.getSelectedItem() == null
                        || ProjectsCombo.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Please enter all fields.", "Incomplete Data", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {  // Create Task object
                    String taskName = TaskNameTextF.getText();
                    String taskDescription = TaskDescTA.getText();
                    String selectedTaskType = (String) TaskTypeCombo.getSelectedItem();
                    String selectedProject = (String) ProjectsCombo.getSelectedItem();
                    //get the id 
                    int firstSpaceIndex = selectedTaskType.indexOf(" ");
                    String typeIdString = selectedTaskType.substring(0, firstSpaceIndex);
                    int typeid = Integer.parseInt(typeIdString);
                    TaskType taskType = data.getTaskTypeById(typeid);
                    Task newTask = new Task(taskName, taskDescription, taskType);

                    try {
                        data.addTask(newTask);

                    } catch (DataModule.AlreadyExistsException aex) {
                    }

                    fillTheTasksTableModel();

                    //get the project id
                    int firstSpaceIndex2 = selectedProject.indexOf(" ");
                    String projectIdString = selectedProject.substring(0, firstSpaceIndex2);
                    int projectid = Integer.parseInt(projectIdString);
                    Project proj = data.getProjectById(projectid);
                    try {
                        proj.addTask(newTask);
                        reloadData();
                        fillTheTasksTableModel();

                    } catch (Exception aeex) {
                    }

                    // Reset fields after adding task
                    TaskNameTextF.setText("");
                    TaskDescTA.setText("");
                    TaskTypeCombo.setSelectedIndex(0);
                    ProjectsCombo.setSelectedIndex(0);

                    JOptionPane.showMessageDialog(addTaskPanel, "new task added successfully", "add task", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        );

        removeTaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tasksTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a task to remove.");
                    return;
                }

                int taskId = (int) tasksTable.getValueAt(selectedRow, 0);

                try {

                    Task t = data.getTaskById(taskId);
                    Project p = data.getprojectOfTask(t);

                    p.removeTask(t.getTask_id());

                    HashSet<Processus> process = t.getProcessus();
                    for (Processus pro : process) {
                        data.removeProcessusById(pro.getProcessus_id());
                    }
                    data.removeTaskById(taskId);

                    data.saveTasks();
                    data.saveProjects();
                    JOptionPane.showMessageDialog(null, "task removed successfully!");

                    // Update the projects table model
                    fillTheTasksTableModel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Project not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ///////////////////////////////////////////
// Set bounds and style for components
        HeaderADDTasklabel.setBounds(
                160, 10, 200, 30);
        HeaderADDTasklabel.setFont(
                new Font("Ariel", Font.PLAIN, 22));
        HeaderADDTasklabel.setForeground(
                new Color(249, 232, 206));

        TaskNameLabel.setBounds(
                20, 50, 80, 20);
        TaskNameTextF.setBounds(
                120, 50, 200, 20);
        TaskNameTextF.setBackground(
                new Color(249, 232, 206));

        TaskDescLabel.setBounds(
                20, 80, 80, 20);
        textAreaTaskDescSC.setBounds(
                120, 80, 200, 100);
        TaskDescTA.setBackground(
                new Color(249, 232, 206));

        TaskTypeLabel.setBounds(
                20, 190, 80, 20);
        TaskTypeCombo.setBounds(
                120, 190, 200, 20);
        TaskTypeCombo.setBackground(
                new Color(249, 232, 206));

        SelectProjectLabel.setBounds(
                20, 220, 110, 20);
        ProjectsCombo.setBounds(
                120, 220, 200, 20);
        ProjectsCombo.setBackground(
                new Color(249, 232, 206));

        addTaskBtn.setBounds(
                120, 250, 200, 30);
        addTaskBtn.setBackground(
                new Color(249, 232, 206));

        removeTaskBtn.setBounds(
                120, 290, 200, 30);
        removeTaskBtn.setBackground(
                new Color(249, 232, 206));

// Add components to the add task panel
        addTaskPanel.add(HeaderADDTasklabel);

        addTaskPanel.add(TaskNameLabel);

        addTaskPanel.add(TaskNameTextF);

        addTaskPanel.add(TaskDescLabel);

        addTaskPanel.add(textAreaTaskDescSC);

        addTaskPanel.add(TaskTypeLabel);

        addTaskPanel.add(TaskTypeCombo);

        addTaskPanel.add(SelectProjectLabel);

        addTaskPanel.add(ProjectsCombo);

        addTaskPanel.add(addTaskBtn);

        addTaskPanel.add(removeTaskBtn);

// Add components to the main task panel
        panel2.add(tasksSc);

        panel2.add(addTaskPanel);
        // add processus
        ////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        panel3.setLayout(null);
        JScrollPane ProcessusSc = new JScrollPane(processusTable);

        ProcessusSc.setBackground(new Color(249, 232, 206));
        processusTable.setBackground(new Color(249, 232, 206));
        ProcessusSc.setBounds(25, 50, 500, 300);
        panel3.add(ProcessusSc);

// add processus panel
        JPanel addProcessusPanel = new JPanel();
        addProcessusPanel.setBackground(new Color(139, 168, 136));
        addProcessusPanel.setLayout(null);

        JButton addProcessusBtn = new JButton("ADD PROCESSUS");
        JButton removeProcessusBtn = new JButton("Remove Selected PROCESSUS");
        JLabel HeaderADDProcessuslabel = new JLabel("ADD PROCESSUS");
        JLabel ProcessusNameLabel = new JLabel("Name :");
        JTextField ProcessusNameTextF = new JTextField();
        JLabel ProcssusDurationLabel = new JLabel("Duration :");
        JTextField ProcessusDurationTextF = new JTextField();
        JLabel SelectTaskLabel = new JLabel("Select a Task");

        JTextArea ProcessusDescTA = new JTextArea(5, 30);
        JScrollPane textAreaProcessusDescSC = new JScrollPane(ProcessusDescTA);
        JLabel ProcessusDescLabel = new JLabel("description :");

        fillTheProcessusTableModel();
        fillTasksComboBox();

        addProcessusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = ProcessusNameTextF.getText();
                String duration = ProcessusDurationTextF.getText();
                String description = ProcessusDescTA.getText();

                if (name.isEmpty() || duration.isEmpty() || description.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double durationD;
                try {
                    durationD = Double.parseDouble(duration);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Duration must be a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (Double.isNaN(durationD)) {
                    JOptionPane.showMessageDialog(null, "Duration must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String task = (String) TaskCombo.getSelectedItem();
                if (task != null) {
                    int firstSpaceIndex = task.indexOf(" ");
                    String TaskIdString = task.substring(0, firstSpaceIndex);
                    int taskId = Integer.parseInt(TaskIdString);

                    Processus newProcessus = new Processus(description, durationD, name);
                    try {
                        Task ta = data.getTaskById(taskId);
                        Project proj = data.getprojectOfTask(ta);
                        Task projtask = proj.getTaskById(taskId);
                        projtask.addProcessus(newProcessus);
                        ta.addProcessus(newProcessus);
                        data.addProcessus(newProcessus);
                        reloadData();
                        JOptionPane.showMessageDialog(null, "New Processus added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                        // Clear input fields after adding
                        ProcessusNameTextF.setText("");
                        ProcessusDurationTextF.setText("");
                        ProcessusDescTA.setText("");
                    } catch (Exception exp) {
                        JOptionPane.showMessageDialog(null, "Please try again", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a task", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        removeProcessusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = processusTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select processus to remove.");
                    return;
                }

                int processusid = (int) processusTable.getValueAt(selectedRow, 0);

                try {
                    Processus p = data.getProcessusByid(processusid);
                    if (p == null) {
                        throw new Exception("Processus not found in data module.");
                    }

                    Task task = data.getTaskOfProcessus(p);
                    if (task == null) {
                        throw new Exception("Task not found for processus.");
                    }

                    task.removeProcessus(processusid);
                    Project prog = data.getprojectOfTask(task);
                    if (prog == null) {
                        throw new Exception("Project not found for task.");
                    }

                    Task tas = prog.getTaskById(task.getTask_id());
                    if (tas == null) {
                        throw new Exception("Task not found in project.");
                    }

                    tas.removeProcessus(processusid);
                    data.removeProcessusById(processusid);

                    JOptionPane.showMessageDialog(null, "Processus id : " + processusid + " removed successfully!");

                } catch (DataModule.NotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Processus not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Something went wrong, please try again: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        HeaderADDProcessuslabel.setBounds(120, 10, 200, 30);
        HeaderADDProcessuslabel.setFont(new Font("Ariel", Font.PLAIN, 22));
        HeaderADDProcessuslabel.setForeground(new Color(249, 232, 206));
        ProcessusNameLabel.setBounds(20, 50, 80, 20);
        ProcessusDescLabel.setBounds(20, 140, 80, 20);
        ProcessusNameTextF.setBounds(120, 50, 200, 20);
        ProcessusNameTextF.setBackground(new Color(249, 232, 206));

        textAreaProcessusDescSC.setBounds(120, 140, 200, 100);
        ProcessusDescTA.setBackground(new Color(249, 232, 206));
        ProcssusDurationLabel.setBounds(20, 80, 80, 20);
        ProcessusDurationTextF.setBounds(120, 80, 200, 20);
        ProcessusDurationTextF.setBackground(new Color(249, 232, 206));
        SelectTaskLabel.setBounds(20, 110, 100, 20);
        TaskCombo.setBounds(120, 110, 200, 20);
        TaskCombo.setBackground(new Color(249, 232, 206));
        addProcessusBtn.setBounds(120, 250, 200, 30);
        addProcessusBtn.setBackground(new Color(249, 232, 206));
        removeProcessusBtn.setBounds(120, 290, 200, 30);
        removeProcessusBtn.setBackground(new Color(249, 232, 206));
// put the components in the panel
        addProcessusPanel.add(HeaderADDProcessuslabel);

        addProcessusPanel.add(ProcessusNameLabel);

        addProcessusPanel.add(ProcessusNameTextF);

        addProcessusPanel.add(ProcessusDescLabel);

        addProcessusPanel.add(ProcssusDurationLabel);

        addProcessusPanel.add(ProcessusDurationTextF);

        addProcessusPanel.add(textAreaProcessusDescSC);

        addProcessusPanel.add(TaskCombo);

        addProcessusPanel.add(addProcessusBtn);

        addProcessusPanel.add(removeProcessusBtn);

        addProcessusPanel.add(SelectTaskLabel);

// add the addprocessuspanel to the panel3
        addProcessusPanel.setBounds(600, 50, 400, 360);
        panel3.add(addProcessusPanel);
///////////////////////////////////////////////////////////////////////////
// New panel for additional functionalities
        JPanel additionalPanel = new JPanel();
        additionalPanel.setBackground(new Color(139, 168, 136));
        additionalPanel.setLayout(null);

// Label for "Add to Processus"
        JLabel addToProcessusLabel = new JLabel("Add to Processus");
        addToProcessusLabel.setBounds(20, 10, 150, 20);
        addToProcessusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        addToProcessusLabel.setForeground(Color.WHITE);
        additionalPanel.add(addToProcessusLabel);

// Labels and components for status and employee selection
        JLabel addStatusLabel = new JLabel("Status:");
        addStatusLabel.setBounds(20, 40, 100, 20);
        additionalPanel.add(addStatusLabel);

        JRadioButton notStartedRadio = new JRadioButton("Not Started");
        notStartedRadio.setBounds(120, 40, 100, 20);
        additionalPanel.add(notStartedRadio);

        JRadioButton inProgressRadio = new JRadioButton("In Progress");
        inProgressRadio.setBounds(220, 40, 100, 20);
        additionalPanel.add(inProgressRadio);

        JRadioButton completedRadio = new JRadioButton("Completed");
        completedRadio.setBounds(320, 40, 100, 20);
        additionalPanel.add(completedRadio);

        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(notStartedRadio);
        statusGroup.add(inProgressRadio);
        statusGroup.add(completedRadio);

        JLabel addEmployeeLabel = new JLabel("Add Employee:");
        addEmployeeLabel.setBounds(20, 70, 100, 20);
        additionalPanel.add(addEmployeeLabel);

        employeeComboBox.setBounds(120, 70, 200, 20);
        additionalPanel.add(employeeComboBox);

        JLabel numberOfHoursLabel = new JLabel("Number of Hours:");
        numberOfHoursLabel.setBounds(350, 70, 120, 20);
        additionalPanel.add(numberOfHoursLabel);

        JTextField numberOfHoursField = new JTextField();
        numberOfHoursField.setBounds(470, 70, 100, 20);
        additionalPanel.add(numberOfHoursField);

// Labels and components for adding materials
        JLabel addMatierePremiereLabel = new JLabel("Add Matiere Premiere:");
        addMatierePremiereLabel.setBounds(20, 100, 150, 20);
        additionalPanel.add(addMatierePremiereLabel);

        matierePremiereComboBox.setBounds(170, 100, 150, 20);
        additionalPanel.add(matierePremiereComboBox);

        JLabel matierePremiereQtyLabel = new JLabel("Quantity:");
        matierePremiereQtyLabel.setBounds(350, 100, 60, 20);
        additionalPanel.add(matierePremiereQtyLabel);

        JTextField matierePremiereQtyField = new JTextField();
        matierePremiereQtyField.setBounds(420, 100, 50, 20);
        additionalPanel.add(matierePremiereQtyField);

        JLabel addAutreMatiereLabel = new JLabel("Add Autre Matiere:");
        addAutreMatiereLabel.setBounds(20, 130, 150, 20);
        additionalPanel.add(addAutreMatiereLabel);

        autreMatiereComboBox.setBounds(170, 130, 150, 20);
        additionalPanel.add(autreMatiereComboBox);

        JLabel autreMatiereQtyLabel = new JLabel("Quantity:");
        autreMatiereQtyLabel.setBounds(350, 130, 60, 20);
        additionalPanel.add(autreMatiereQtyLabel);

        JTextField autreMatiereQtyField = new JTextField();
        autreMatiereQtyField.setBounds(420, 130, 50, 20);
        additionalPanel.add(autreMatiereQtyField);

// New buttons
        JButton changeStatusBtn = new JButton("Change Status");
        changeStatusBtn.setBounds(590, 40, 150, 20);
        additionalPanel.add(changeStatusBtn);

        JButton addEmployeeProcessBtn = new JButton("Add Employee");
        addEmployeeProcessBtn.setBounds(590, 70, 150, 20);
        additionalPanel.add(addEmployeeProcessBtn);

        JButton addMatierePremiereProcessBtn = new JButton("Add Matiere Premiere");
        addMatierePremiereProcessBtn.setBounds(590, 100, 150, 20);
        additionalPanel.add(addMatierePremiereProcessBtn);

        JButton addAutreMatiereProcessBtn = new JButton("Add Autre Matiere");
        addAutreMatiereProcessBtn.setBounds(590, 130, 150, 20);
        additionalPanel.add(addAutreMatiereProcessBtn);

// Set bounds for additional panel
        additionalPanel.setBounds(25, 370, 975, 240);
        panel3.add(additionalPanel);

        // Add the new ActionListener for changeStatusBtn
        changeStatusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = processusTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a processus to change status.");
                    return;
                }

                int processusId = (int) processusTable.getValueAt(selectedRow, 0);

                // Check if a status is selected
                if (!notStartedRadio.isSelected() && !inProgressRadio.isSelected() && !completedRadio.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please select a status.");
                    return;
                }

                String newStatus;
                if (notStartedRadio.isSelected()) {
                    newStatus = "Not Started";
                } else if (inProgressRadio.isSelected()) {
                    newStatus = "In Progress";
                } else {
                    newStatus = "Completed";
                }

                try {
                    Processus processus = data.getProcessusByid(processusId);
                    if (processus == null) {
                        throw new Exception("Processus not found in data module.");
                    }

                    //change the status in the data processus set 
                    Processus p = data.getProcessusByid(processusId);
                    p.setStatus(newStatus);

                    //change the status of the processus in its task
                    Task t = data.getTaskOfProcessus(p);
                    t.getOneProcessus(processusId).setStatus(newStatus);
                    //change the status of the processus in its task in its project
                    Project prog = data.getprojectOfTask(t);
                    Task projtask = prog.getTaskById(t.getTask_id());
                    Processus projtaskprocess = projtask.getOneProcessus(processusId);
                    projtaskprocess.setStatus(newStatus);
                    data.thereIsChange();

                    JOptionPane.showMessageDialog(null, "Status changed to " + newStatus + " successfully!");

                    // Optionally, reload the table or update the UI as needed
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Something went wrong, please try again: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addEmployeeProcessBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = processusTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a processus to add an employee.");
                    return;
                }

                String employeeSelection = (String) employeeComboBox.getSelectedItem();
                if (employeeSelection == null || employeeSelection.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select an employee.");
                    return;
                }

                // Extract employee ID from the selected item in employeeComboBox
                int firstSpaceIndex = employeeSelection.indexOf(" ");
                String employeeIdString = employeeSelection.substring(0, firstSpaceIndex);
                int employeeId = Integer.parseInt(employeeIdString);

                String hoursText = numberOfHoursField.getText();
                if (hoursText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the number of hours.");
                    return;
                }

                double hours;
                try {
                    hours = Double.parseDouble(hoursText);
                    if (hours <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Number of hours must be a positive number.");
                    return;
                }

                int processusId = (int) processusTable.getValueAt(selectedRow, 0);

                try {
                    Processus processus = data.getProcessusByid(processusId);
                    if (processus == null) {
                        throw new Exception("Processus not found in data module.");
                    }

                    Employee emp = data.getEmployeeById(employeeId);
                    if (emp == null) {
                        throw new Exception("Employee not found in data module.");
                    }

                    // Add employee and hours to Processus
                    processus.addEmployee(emp, hours);

                    // Update Task and Project
                    Task task = data.getTaskOfProcessus(processus);
                    if (task != null) {
                        task.getOneProcessus(processusId).addEmployee(emp, hours);
                        Project project = data.getprojectOfTask(task);
                        if (project != null) {
                            Task projectTask = project.getTaskById(task.getTask_id());
                            projectTask.getOneProcessus(processusId).addEmployee(emp, hours);
                            data.thereIsChange();
                            numberOfHoursField.setText("");
                            matierePremiereQtyField.setText("");
                            autreMatiereQtyField.setText("");
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Employee added to processus successfully!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Something went wrong, please try again: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addMatierePremiereProcessBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = processusTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a processus to add matiere premiere.");
                    return;
                }

                String matiereSelection = (String) matierePremiereComboBox.getSelectedItem();
                if (matiereSelection == null || matiereSelection.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select matiere premiere.");
                    return;
                }

                // Extract matiere premiere ID from the selected item in metiere premiere combobox
                int firstSpaceIndex = matiereSelection.indexOf(" ");
                String matierepIdString = matiereSelection.substring(0, firstSpaceIndex);
                int matierepId = Integer.parseInt(matierepIdString);

                String quantiteText = matierePremiereQtyField.getText();
                if (quantiteText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the quantity.");
                    return;
                }

                double quantity;
                try {
                    quantity = Double.parseDouble(quantiteText);
                    if (quantity <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "quantity must be a positive number.");
                    return;
                }

                int processusId = (int) processusTable.getValueAt(selectedRow, 0);

                try {
                    Processus processus = data.getProcessusByid(processusId);
                    if (processus == null) {
                        throw new Exception("Processus not found in data module.");
                    }

                    MatierePremiere matierep = (MatierePremiere) data.getMaterielById(matierepId);
                    if (matierep == null) {
                        throw new Exception("matiere premiere not found in data module.");
                    }

                    // Add the matiere premiere to Processus
                    processus.addMatierePremiere(matierep, quantity, 1);

                    // Update Task and Project
                    Task task = data.getTaskOfProcessus(processus);
                    if (task != null) {
                        task.getOneProcessus(processusId).addMatierePremiere(matierep, quantity, 1);
                        Project project = data.getprojectOfTask(task);
                        if (project != null) {
                            Task projectTask = project.getTaskById(task.getTask_id());
                            projectTask.getOneProcessus(processusId).addMatierePremiere(matierep, quantity, 1);
                            data.thereIsChange();
                        }
                    }

                    JOptionPane.showMessageDialog(null, "this matiere premiere added to processus successfully!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Something went wrong, please try again: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addAutreMatiereProcessBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = processusTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a processus to add autre matiere.");
                    return;
                }
                // Extractautre matiere ID from the selected item in autre matieres combobox

                String matiereSelection = (String) autreMatiereComboBox.getSelectedItem();
                if (matiereSelection == null || matiereSelection.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select autre matiere.");
                    return;
                }

                int firstSpaceIndex = matiereSelection.indexOf(" ");
                String autremIdIdString = matiereSelection.substring(0, firstSpaceIndex);
                int autremId = Integer.parseInt(autremIdIdString);

                String quantiteText = autreMatiereQtyField.getText();
                if (quantiteText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the quantity.");
                    return;
                }

                double quantity;
                try {
                    quantity = Double.parseDouble(quantiteText);
                    if (quantity <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "quantity must be a positive number.");
                    return;
                }

                int processusId = (int) processusTable.getValueAt(selectedRow, 0);

                try {
                    Processus processus = data.getProcessusByid(processusId);
                    if (processus == null) {
                        throw new Exception("Processus not found in data module.");
                    }

                    AutreMatiere autreMat = (AutreMatiere) data.getMaterielById(autremId);
                    if (autreMat == null) {
                        throw new Exception("autre matiere not found in data module.");
                    }

                    // Add the autre matiere to Processus
                    processus.addAutreMatiere(autreMat, quantity, 1);

                    // Update Task and Project
                    Task task = data.getTaskOfProcessus(processus);
                    if (task != null) {
                        task.getOneProcessus(processusId).addAutreMatiere(autreMat, quantity, 1);
                        Project project = data.getprojectOfTask(task);
                        if (project != null) {
                            Task projectTask = project.getTaskById(task.getTask_id());
                            projectTask.getOneProcessus(processusId).addAutreMatiere(autreMat, quantity, 1);
                            data.thereIsChange();
                        }
                    }

                    JOptionPane.showMessageDialog(null, "this autre matiere added to processus successfully!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Something went wrong, please try again: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        /////////////////////////////////////////////////////////////////////////////
        //client panel
        panel4.setLayout(
                null);
        JScrollPane ClientsSc = new JScrollPane(clientsTable);

        ClientsSc.setBackground(
                new Color(249, 232, 206));
        clientsTable.setBackground(
                new Color(249, 232, 206));
        ClientsSc.setBounds(
                25, 50, 500, 300);
        panel4.add(ClientsSc);

// define the components
        JButton addClientBtn = new JButton("ADD CLIENT");
        JButton removeClientBtn = new JButton("Remove Selected CLIENT");
        JLabel HeaderADDClientlabel = new JLabel("ADD CLIENT");
        JLabel ClientNameLabel = new JLabel("Name :");
        JTextField ClientNameTextF = new JTextField();
        JPanel addClientPanel = new JPanel();
        fillTheClientsTableModel();

        addClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = ClientNameTextF.getText();

                if (!name.isEmpty()) {
                    Client newClient = new Client(name);
                    try {
                        data.addClient(newClient);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "some thing went wrong please again", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(null, "new client added successfully", "client added", JOptionPane.INFORMATION_MESSAGE);
                    // Clear input fields after adding
                    ClientNameTextF.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the name ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clientsTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select client to remove.", "", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int clientid = (int) clientsTable.getValueAt(selectedRow, 0);

                try {

                    data.removeClientById(clientid);

                    JOptionPane.showMessageDialog(null, " client removed successfully!");

                } catch (DataModule.NotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "client not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addClientPanel.setLayout(
                null);
        addClientPanel.setBackground(
                new Color(139, 168, 136));

// style
        HeaderADDClientlabel.setBounds(
                130, 20, 200, 30);
        HeaderADDClientlabel.setFont(
                new Font("Ariel", Font.PLAIN, 22));
        HeaderADDClientlabel.setForeground(
                new Color(249, 232, 206));

        ClientNameLabel.setBounds(
                20, 70, 80, 20);
        ClientNameTextF.setBounds(
                100, 70, 200, 20);
        ClientNameTextF.setBackground(
                new Color(249, 232, 206));

        addClientBtn.setBounds(
                100, 130, 200, 30);
        addClientBtn.setBackground(
                new Color(249, 232, 206));

        removeClientBtn.setBounds(
                100, 180, 200, 30);
        removeClientBtn.setBackground(
                new Color(249, 232, 206));

// add the addclientpanel to the main panel 4
        panel4.add(addClientPanel);

// set bounds for the client panel
        addClientPanel.setBounds(
                650, 50, 330, 270);

// add components to the panel
        addClientPanel.add(HeaderADDClientlabel);

        addClientPanel.add(ClientNameLabel);

        addClientPanel.add(ClientNameTextF);

        addClientPanel.add(addClientBtn);

        addClientPanel.add(removeClientBtn);

////////////////////////////////////////////////////////////////////////////////
        panel5.setLayout(
                null);
        JScrollPane EmployeesSc = new JScrollPane(employeesTable);

        EmployeesSc.setBackground(
                new Color(249, 232, 206));
        EmployeesSc.setBounds(
                25, 50, 500, 300);
        panel5.add(EmployeesSc);

// define the components for  
        JButton addEmployeeBtn = new JButton("ADD EMPLOYEE");
        JButton removeEmployeeBtn = new JButton("Remove Selected EMPLOYEE");
        JLabel HeaderAddEmployeeLabel = new JLabel("ADD EMPLOYEE");
        JLabel EmployeeFirstNameLabel = new JLabel("First Name:");
        JLabel EmployeeLastNameLabel = new JLabel("Last Name:");
        JLabel EmployeeFonctionLabel = new JLabel("Fonction:");
        JLabel EmployeeSpecialiteLabel = new JLabel("Specialite:");
        JTextField EmployeeFirstNameTextF = new JTextField();
        JTextField EmployeeLastNameTextF = new JTextField();

        JPanel addEmployeePanel = new JPanel();
        filltheEmployeesTableModel();
        fillEmployeeFonctionComboBox();
        fillEmployeeSpecialiteComboBox();

        addEmployeeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = EmployeeFirstNameTextF.getText();
                String lastName = EmployeeLastNameTextF.getText();
                String selectedFonction = (String) EmployeeFonctionComboBox.getSelectedItem();
                String selectedSpecialite = (String) specialiteEmployeeCombobox.getSelectedItem();

                // Validate input fields
                if (firstName.isEmpty()
                        || lastName.isEmpty()
                        || selectedFonction == null
                        || selectedSpecialite == null) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {

                    int firstSpaceIndex = selectedFonction.indexOf(" ");
                    String fonctionIdString = selectedFonction.substring(0, firstSpaceIndex);
                    int fonctionid = Integer.parseInt(fonctionIdString);
                    Fonction fonction = data.getFonctionById(fonctionid);

                    int firstSpaceIndex2 = selectedSpecialite.indexOf(" ");
                    String specialiteIdString = selectedSpecialite.substring(0, firstSpaceIndex2);
                    int specialiteid = Integer.parseInt(specialiteIdString);
                    Specialite specialite = data.getSpecialiteById(specialiteid);
                    
                    Employee newEmployee = new Employee(firstName, lastName, specialite, fonction);

                    data.addEmployee(newEmployee); // Add the new employee to your data source

                    JOptionPane.showMessageDialog(null, "New employee added successfully", "New Employee", JOptionPane.INFORMATION_MESSAGE);

                    // Clear input fields after adding
                    EmployeeFirstNameTextF.setText("");
                    EmployeeLastNameTextF.setText("");
                    EmployeeFonctionComboBox.setSelectedIndex(-1);
                    specialiteEmployeeCombobox.setSelectedIndex(-1);

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Error, please try again", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeEmployeeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = employeesTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select an employee to remove.");
                    return;
                }

                int employeeid = (int) employeesTable.getValueAt(selectedRow, 0);

                try {

                    data.removeEmployeeById(employeeid);

                    JOptionPane.showMessageDialog(null, " employee removed successfully!");

                } catch (DataModule.NotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "matiere premiere not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addEmployeePanel.setLayout(null);

// style
        HeaderAddEmployeeLabel.setBounds(
                130, 20, 200, 30);
        HeaderAddEmployeeLabel.setFont(
                new Font("Ariel", Font.PLAIN, 22));
        HeaderAddEmployeeLabel.setForeground(
                new Color(249, 232, 206));

        EmployeeFirstNameLabel.setBounds(
                20, 70, 80, 20);
        EmployeeFirstNameTextF.setBounds(
                120, 70, 200, 20);
        EmployeeFirstNameTextF.setBackground(
                new Color(249, 232, 206));

        EmployeeLastNameLabel.setBounds(
                20, 100, 80, 20);
        EmployeeLastNameTextF.setBounds(
                120, 100, 200, 20);
        EmployeeLastNameTextF.setBackground(
                new Color(249, 232, 206));

        EmployeeFonctionLabel.setBounds(
                20, 130, 80, 20);
        EmployeeFonctionComboBox.setBounds(
                120, 130, 200, 20);
        EmployeeFonctionComboBox.setBackground(
                new Color(249, 232, 206));

        EmployeeSpecialiteLabel.setBounds(
                20, 160, 80, 20);
        specialiteEmployeeCombobox.setBounds(
                120, 160, 200, 20);
        specialiteEmployeeCombobox.setBackground(
                new Color(249, 232, 206));

        addEmployeeBtn.setBounds(
                120, 200, 200, 30);
        addEmployeeBtn.setBackground(
                new Color(249, 232, 206));

        removeEmployeeBtn.setBounds(
                120, 240, 200, 30);
        removeEmployeeBtn.setBackground(
                new Color(249, 232, 206));

        // set bounds for the employee panel
        addEmployeePanel.setBounds(
                600, 50, 400, 300);
        addEmployeePanel.setBackground(
                new Color(139, 168, 136));
// add the addEmployeePanel to the main panel 4
        panel5.add(addEmployeePanel);

// add components to the panel
        addEmployeePanel.add(HeaderAddEmployeeLabel);

        addEmployeePanel.add(EmployeeFirstNameLabel);

        addEmployeePanel.add(EmployeeFirstNameTextF);

        addEmployeePanel.add(EmployeeLastNameLabel);

        addEmployeePanel.add(EmployeeLastNameTextF);

        addEmployeePanel.add(EmployeeFonctionLabel);

        addEmployeePanel.add(EmployeeFonctionComboBox);

        addEmployeePanel.add(EmployeeSpecialiteLabel);

        addEmployeePanel.add(specialiteEmployeeCombobox);

        addEmployeePanel.add(addEmployeeBtn);

        addEmployeePanel.add(removeEmployeeBtn);

////////////////////////////////////////////////////////////////////////////////
        panel6.setLayout(
                null);
        JScrollPane MatierePremiereSc = new JScrollPane(matieresTable);

        MatierePremiereSc.setBackground(
                new Color(249, 232, 206));
        MatierePremiereSc.setBounds(
                25, 50, 500, 300);
        panel6.add(MatierePremiereSc);

        // Define the components for Matière Première panel
        JPanel addMatierePremierePanel = new JPanel();

        addMatierePremierePanel.setLayout(
                null);
        addMatierePremierePanel.setBackground(
                new Color(139, 168, 136));

        JLabel HeaderADDMatierePremiereLabel = new JLabel("ADD MATIERE PREMIERE");
        JLabel MatierePremiereNameLabel = new JLabel("Name :");
        JTextField MatierePremiereNameTextF = new JTextField();
        JLabel MPPrixUnitaireLabel = new JLabel("Prix Unitaire :");
        JTextField MPPrixUnitaireTextF = new JTextField();
        JButton addMatierePremiereBtn = new JButton("ADD MATIERE PREMIERE");
        JButton removeMatierePremiereBtn = new JButton("Remove Selected MATIERE PREMIERE");
        fillMatieresPremieresTableModel();
        fillAutreMatieresComboBox();

        addMatierePremiereBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = MatierePremiereNameTextF.getText();
                String prix = MPPrixUnitaireTextF.getText();

                // Validate input fields
                if (nom.isEmpty() || prix.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

                }

                // Validate and parse tarrif
                double prixD;
                try {
                    prixD = Double.parseDouble(prix);
                    if (prixD <= 0) {
                        JOptionPane.showMessageDialog(null, "prix unitaire must be positive", "Error", JOptionPane.ERROR_MESSAGE);

                    }

                    MatierePremiere mp = new MatierePremiere(nom, prixD);
                    data.addMateriel(mp);
                    JOptionPane.showMessageDialog(null, "New matiere premiere added successfully", "New Fonction", JOptionPane.INFORMATION_MESSAGE);

                    // Clear input fields after adding
                    MatierePremiereNameTextF.setText("");
                    MPPrixUnitaireTextF.setText("");
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "prix unitaire must be a valid number", "Error", JOptionPane.ERROR_MESSAGE);

                } catch (Fonction.InvalidTarrifHeurException ithe) {
                    JOptionPane.showMessageDialog(null, "prix unitaire must be positive: " + ithe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Error, please try again", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeMatierePremiereBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = matieresTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select matiere premiere to remove.");
                    return;
                }

                int matierep = (int) matieresTable.getValueAt(selectedRow, 0);

                try {

                    data.removeMaterielById(matierep);

                    JOptionPane.showMessageDialog(null, "matiere premiere removed successfully!");

                } catch (DataModule.NotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "matiere premiere not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

// Style the components
        HeaderADDMatierePremiereLabel.setBounds(
                70, 20, 300, 30);
        HeaderADDMatierePremiereLabel.setFont(
                new Font("Ariel", Font.PLAIN, 22));
        HeaderADDMatierePremiereLabel.setForeground(
                new Color(249, 232, 206));

        MatierePremiereNameLabel.setBounds(
                20, 70, 100, 20);
        MatierePremiereNameTextF.setBounds(
                120, 70, 200, 20);
        MatierePremiereNameTextF.setBackground(
                new Color(249, 232, 206));

        MPPrixUnitaireLabel.setBounds(
                20, 110, 100, 20);
        MPPrixUnitaireTextF.setBounds(
                120, 110, 200, 20);
        MPPrixUnitaireTextF.setBackground(
                new Color(249, 232, 206));

        addMatierePremiereBtn.setBounds(
                120, 150, 200, 30);
        addMatierePremiereBtn.setBackground(
                new Color(249, 232, 206));

        removeMatierePremiereBtn.setBounds(
                120, 200, 200, 30);
        removeMatierePremiereBtn.setBackground(
                new Color(249, 232, 206));

// Add components to the Matière Première panel
        addMatierePremierePanel.add(HeaderADDMatierePremiereLabel);

        addMatierePremierePanel.add(MatierePremiereNameLabel);

        addMatierePremierePanel.add(MatierePremiereNameTextF);

        addMatierePremierePanel.add(MPPrixUnitaireLabel);

        addMatierePremierePanel.add(MPPrixUnitaireTextF);

        addMatierePremierePanel.add(addMatierePremiereBtn);

        addMatierePremierePanel.add(removeMatierePremiereBtn);

// Set bounds for the Matière Première panel and add it to the main panel
        addMatierePremierePanel.setBounds(
                600, 50, 400, 300);
        panel6.add(addMatierePremierePanel);
/////////////////////////////////////////////////////////////////////////////////

        panel7.setLayout(
                null);
        JScrollPane AutreMatiereSc = new JScrollPane(autresTable);

        AutreMatiereSc.setBackground(
                new Color(249, 232, 206));
        AutreMatiereSc.setBounds(
                25, 50, 500, 300);
        panel7.add(AutreMatiereSc);
// Define the components for Autre Matière panel
        JPanel addAutreMatierePanel = new JPanel();

        addAutreMatierePanel.setLayout(
                null);
        addAutreMatierePanel.setBackground(
                new Color(139, 168, 136));

        JLabel HeaderADDAutreMatiereLabel = new JLabel("ADD AUTRE MATIERE");
        JLabel AutreMatiereNameLabel = new JLabel("Name :");
        JTextField AutreMatiereNameTextF = new JTextField();
        JLabel AMPrixUnitaireLabel = new JLabel("Prix Unitaire :");
        JTextField AMPrixUnitaireTextF = new JTextField();
        JButton addAutreMatiereBtn = new JButton("ADD AUTRE MATIERE");
        JButton removeAutreMatiereBtn = new JButton("Remove Selected AUTRE MATIERE");
        fillAutresMatieresTableModel();

        addAutreMatiereBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = AutreMatiereNameTextF.getText();
                String prix = AMPrixUnitaireTextF.getText();

                // Validate input fields
                if (nom.isEmpty() || prix.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

                }

                // Validate and parse tarrif
                double prixD;
                try {
                    prixD = Double.parseDouble(prix);
                    if (prixD <= 0) {
                        JOptionPane.showMessageDialog(null, "prix unitaire must be positive", "Error", JOptionPane.ERROR_MESSAGE);

                    }

                    AutreMatiere amat = new AutreMatiere(nom, prixD);
                    data.addMateriel(amat);
                    JOptionPane.showMessageDialog(null, "New autre matiere added successfully", "New Fonction", JOptionPane.INFORMATION_MESSAGE);

                    // Clear input fields after adding
                    AutreMatiereNameTextF.setText("");
                    AMPrixUnitaireTextF.setText("");
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "prix unitaire must be a valid number", "Error", JOptionPane.ERROR_MESSAGE);

                } catch (Fonction.InvalidTarrifHeurException ithe) {
                    JOptionPane.showMessageDialog(null, "prix unitaire must be positive: " + ithe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Error, please try again", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeAutreMatiereBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = autresTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a Autre matiere to remove.");
                    return;
                }

                int autreMid = (int) autresTable.getValueAt(selectedRow, 0);

                try {

                    data.removeMaterielById(autreMid);

                    JOptionPane.showMessageDialog(null, "autre matiere  removed successfully!");

                } catch (DataModule.NotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "autre matiere not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

// Style the components
        HeaderADDAutreMatiereLabel.setBounds(
                100, 20, 300, 30);
        HeaderADDAutreMatiereLabel.setFont(
                new Font("Ariel", Font.PLAIN, 22));
        HeaderADDAutreMatiereLabel.setForeground(
                new Color(249, 232, 206));

        AutreMatiereNameLabel.setBounds(
                20, 70, 100, 20);
        AutreMatiereNameTextF.setBounds(
                120, 70, 200, 20);
        AutreMatiereNameTextF.setBackground(
                new Color(249, 232, 206));

        AMPrixUnitaireLabel.setBounds(
                20, 110, 100, 20);
        AMPrixUnitaireTextF.setBounds(
                120, 110, 200, 20);
        AMPrixUnitaireTextF.setBackground(
                new Color(249, 232, 206));

        addAutreMatiereBtn.setBounds(
                120, 150, 200, 30);
        addAutreMatiereBtn.setBackground(
                new Color(249, 232, 206));

        removeAutreMatiereBtn.setBounds(
                120, 200, 200, 30);
        removeAutreMatiereBtn.setBackground(
                new Color(249, 232, 206));

// Add components to the Autre Matière panel
        addAutreMatierePanel.add(HeaderADDAutreMatiereLabel);

        addAutreMatierePanel.add(AutreMatiereNameLabel);

        addAutreMatierePanel.add(AutreMatiereNameTextF);

        addAutreMatierePanel.add(AMPrixUnitaireLabel);

        addAutreMatierePanel.add(AMPrixUnitaireTextF);

        addAutreMatierePanel.add(addAutreMatiereBtn);

        addAutreMatierePanel.add(removeAutreMatiereBtn);

// Set bounds for the Autre Matière panel and add it to the main panel
        addAutreMatierePanel.setBounds(
                600, 50, 400, 300);
        panel7.add(addAutreMatierePanel);

////////////////////////////////////////////////////////////////////////////////
        panel8.setLayout(
                null);
        JScrollPane TaskTypeSc = new JScrollPane(taskTypesTable);

        TaskTypeSc.setBackground(
                new Color(249, 232, 206));
        TaskTypeSc.setBounds(
                25, 50, 500, 300);
        panel8.add(TaskTypeSc);
// Define the components for Task Type panel
        JPanel addTaskTypePanel = new JPanel();

        addTaskTypePanel.setLayout(
                null);
        addTaskTypePanel.setBackground(
                new Color(139, 168, 136));

        JLabel HeaderADDTaskTypeLabel = new JLabel("ADD TASK TYPE");
        JLabel TaskTypeNameLabel = new JLabel("Name :");
        JTextField TaskTypeNameTextF = new JTextField();
        JButton addTaskTypeBtn = new JButton("ADD TASK TYPE");
        JButton removeTaskTypeBtn = new JButton("Remove Selected TASK TYPE");
        fillTaskTypeTableModel();
        addTaskTypeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = TaskTypeNameTextF.getText();

                if (!name.isEmpty()) {
                    TaskType newType = new TaskType(name);
                    try {
                        data.addTaskType(newType);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "some thing went wrong please again", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(null, "new Task type added successfully", "client added", JOptionPane.INFORMATION_MESSAGE);
                    // Clear input fields after adding
                    TaskTypeNameTextF.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the name ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeTaskTypeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskTypesTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a task type to remove.");
                    return;
                }

                int Tasktypeid = (int) taskTypesTable.getValueAt(selectedRow, 0);

                try {

                    data.removeTaskTypeById(Tasktypeid);

                    JOptionPane.showMessageDialog(null, "task type  removed successfully!");

                } catch (DataModule.NotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "task type not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
// Style the components
        HeaderADDTaskTypeLabel.setBounds(
                120, 20, 300, 30);
        HeaderADDTaskTypeLabel.setFont(
                new Font("Ariel", Font.PLAIN, 22));
        HeaderADDTaskTypeLabel.setForeground(
                new Color(249, 232, 206));

        TaskTypeNameLabel.setBounds(
                20, 70, 100, 20);
        TaskTypeNameTextF.setBounds(
                120, 70, 200, 20);
        TaskTypeNameTextF.setBackground(
                new Color(249, 232, 206));

        addTaskTypeBtn.setBounds(
                120, 110, 200, 30);
        addTaskTypeBtn.setBackground(
                new Color(249, 232, 206));

        removeTaskTypeBtn.setBounds(
                120, 150, 200, 30);
        removeTaskTypeBtn.setBackground(
                new Color(249, 232, 206));

// Add components to the Task Type panel
        addTaskTypePanel.add(HeaderADDTaskTypeLabel);

        addTaskTypePanel.add(TaskTypeNameLabel);

        addTaskTypePanel.add(TaskTypeNameTextF);

        addTaskTypePanel.add(addTaskTypeBtn);

        addTaskTypePanel.add(removeTaskTypeBtn);

// Set bounds for the Task Type panel and add it to the main panel
        addTaskTypePanel.setBounds(600, 50, 400, 300);
        panel8.add(addTaskTypePanel);

////////////////////////////////////////////////////////////////////////////////
        panel9.setLayout(null);
        JScrollPane SpecialiteSc = new JScrollPane(specialiteTable);

        SpecialiteSc.setBackground(new Color(249, 232, 206));
        SpecialiteSc.setBounds(25, 50, 500, 300);
        panel9.add(SpecialiteSc);

        // Define the components for Specialité panel
        JPanel addSpecialitePanel = new JPanel();

        addSpecialitePanel.setLayout(null);
        addSpecialitePanel.setBackground(new Color(139, 168, 136)
        );

        JLabel HeaderADDSpecialiteLabel = new JLabel("ADD SPECIALITE");
        JLabel SpecialiteNomLabel = new JLabel("Nom :");
        JTextField SpecialiteNomTextF = new JTextField();
        JButton addSpecialiteBtn = new JButton("ADD SPECIALITE");
        JButton removeSpecialiteBtn = new JButton("Remove Selected SPECIALITE");
        filltheSpecialiteTableModel();

        addSpecialiteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = SpecialiteNomTextF.getText();

                if (!nom.isEmpty()) {
                    Specialite newSpecialite = new Specialite(nom);
                    try {
                        data.addSpecialite(newSpecialite);
                    } catch (DataModule.AlreadyExistsException aex) {
                        JOptionPane.showMessageDialog(null, "something went wrong please try again", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    // Clear input field after adding
                    SpecialiteNomTextF.setText("");
                    JOptionPane.showMessageDialog(null, "New Specialite added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a name for the Specialite", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        removeSpecialiteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = specialiteTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a specialite to remove.");
                    return;
                }

                int Specialiteid = (int) specialiteTable.getValueAt(selectedRow, 0);

                try {
                    for (Employee emp : data.getEmployeesSet()) {
                        if (emp.getSpecialite().getId() == Specialiteid) {
                            emp.setSpecialite(null);
                        }

                    }

                    data.removeSpecialiteById(Specialiteid);

                    JOptionPane.showMessageDialog(null, "specialite removed successfully!");

                } catch (DataModule.NotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "specialite not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

// Style the components
        HeaderADDSpecialiteLabel.setBounds(
                120, 20, 300, 30);
        HeaderADDSpecialiteLabel.setFont(
                new Font("Ariel", Font.PLAIN, 22));
        HeaderADDSpecialiteLabel.setForeground(
                new Color(249, 232, 206));

        SpecialiteNomLabel.setBounds(
                20, 70, 100, 20);
        SpecialiteNomTextF.setBounds(
                120, 70, 200, 20);
        SpecialiteNomTextF.setBackground(
                new Color(249, 232, 206));

        addSpecialiteBtn.setBounds(
                120, 110, 200, 30);
        addSpecialiteBtn.setBackground(
                new Color(249, 232, 206));

        removeSpecialiteBtn.setBounds(
                120, 150, 200, 30);
        removeSpecialiteBtn.setBackground(
                new Color(249, 232, 206));

// Add components to the Specialité panel
        addSpecialitePanel.add(HeaderADDSpecialiteLabel);

        addSpecialitePanel.add(SpecialiteNomLabel);

        addSpecialitePanel.add(SpecialiteNomTextF);

        addSpecialitePanel.add(addSpecialiteBtn);

        addSpecialitePanel.add(removeSpecialiteBtn);

// Set bounds for the Specialité panel and add it to the main panel
        addSpecialitePanel.setBounds(
                600, 50, 400, 300);
        panel9.add(addSpecialitePanel);
////////////////////////////////////////////////////////////////////////////////

        panel10.setLayout(
                null);
        JScrollPane FonctionSc = new JScrollPane(fonctionTable);

        FonctionSc.setBackground(
                new Color(249, 232, 206));
        FonctionSc.setBounds(
                25, 50, 500, 300);
        panel10.add(FonctionSc);

        // Define the components for Fonction panel
        JPanel addFonctionPanel = new JPanel();

        addFonctionPanel.setLayout(
                null);
        addFonctionPanel.setBackground(
                new Color(139, 168, 136));

        JLabel HeaderADDFonctionLabel = new JLabel("ADD FONCTION");
        JLabel FonctionDescriptionLabel = new JLabel("Description :");
        JTextField FonctionDescriptionTextF = new JTextField();
        JLabel FonctionTarifHeurLabel = new JLabel("Tarif Heure :");
        JTextField FonctionTarifHeurTextF = new JTextField();
        JButton addFonctionBtn = new JButton("ADD FONCTION");
        JButton removeFonctionBtn = new JButton("Remove Selected FONCTION");
        fillTheFonctionTableModel();
        addFonctionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = FonctionDescriptionTextF.getText();
                String tarrif = FonctionTarifHeurTextF.getText();

                // Validate input fields
                if (description.isEmpty() || tarrif.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);

                }

                // Validate and parse tarrif
                double tarrifD;
                try {
                    tarrifD = Double.parseDouble(tarrif);
                    if (tarrifD <= 0) {
                        JOptionPane.showMessageDialog(null, "Tarrif heur must be positive", "Error", JOptionPane.ERROR_MESSAGE);

                    }

                    Fonction newF = new Fonction(description, tarrifD);
                    data.addFonction(newF);
                    JOptionPane.showMessageDialog(null, "New fonction added successfully", "New Fonction", JOptionPane.INFORMATION_MESSAGE);

                    // Clear input fields after adding
                    FonctionDescriptionTextF.setText("");
                    FonctionTarifHeurTextF.setText("");
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Tarrif heur must be a valid number", "Error", JOptionPane.ERROR_MESSAGE);

                } catch (Fonction.InvalidTarrifHeurException ithe) {
                    JOptionPane.showMessageDialog(null, "Tarrif heur must be positive: " + ithe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Error, please try again", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeFonctionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = fonctionTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a fonction to remove.");
                    return;
                }

                int FonctiontId = (int) fonctionTable.getValueAt(selectedRow, 0);

                try {
                    for (Employee emp : data.getEmployeesSet()) {
                        if (emp.getFonction().getFId() == FonctiontId) {
                            emp.setFonction(null);
                        }

                    }

                    data.removeFonctionById(FonctiontId);

                    JOptionPane.showMessageDialog(null, "fonction removed successfully!");

                    // Update the projects table model
                } catch (DataModule.NotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Project not found: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
// Style the components
        HeaderADDFonctionLabel.setBounds(
                135, 20, 300, 30);
        HeaderADDFonctionLabel.setFont(
                new Font("Ariel", Font.PLAIN, 22));
        HeaderADDFonctionLabel.setForeground(
                new Color(249, 232, 206));

        FonctionDescriptionLabel.setBounds(
                20, 70, 100, 20);
        FonctionDescriptionTextF.setBounds(
                120, 70, 200, 20);
        FonctionDescriptionTextF.setBackground(
                new Color(249, 232, 206));

        FonctionTarifHeurLabel.setBounds(
                20, 110, 100, 20);
        FonctionTarifHeurTextF.setBounds(
                120, 110, 200, 20);
        FonctionTarifHeurTextF.setBackground(
                new Color(249, 232, 206));

        addFonctionBtn.setBounds(
                120, 150, 200, 30);
        addFonctionBtn.setBackground(
                new Color(249, 232, 206));

        removeFonctionBtn.setBounds(
                120, 190, 200, 30);
        removeFonctionBtn.setBackground(
                new Color(249, 232, 206));

// Add components to the Fonction panel
        addFonctionPanel.add(HeaderADDFonctionLabel);

        addFonctionPanel.add(FonctionDescriptionLabel);

        addFonctionPanel.add(FonctionDescriptionTextF);

        addFonctionPanel.add(FonctionTarifHeurLabel);

        addFonctionPanel.add(FonctionTarifHeurTextF);

        addFonctionPanel.add(addFonctionBtn);

        addFonctionPanel.add(removeFonctionBtn);

// Set bounds for the Fonction panel and add it to the main panel
        addFonctionPanel.setBounds(600, 50, 400, 300);
        panel10.add(addFonctionPanel);

////////////////////////////////////////////////////////////////////////////////
// Add tabbedPane to JFrame
        this.add(tabbedPane);

        // Set frame properties
        this.setTitle("Data Entry");

        this.setSize(1000, 630); // Set size of the frame

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 7ata iza sakarna l frame ma ytsakar kl lprogram ma3u

        this.setLocationRelativeTo(
                null); // Center the frame on the screen

    }

    private void reloadData() {
        data.saveAllData();
        data.loadAllData();
    }

    private void clearTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
    }

    public void fillTheProjectsTableModel() {
        clearTable(projectsTableModel);

        for (Project project : data.getProjectsSet()) {
            Object[] projectRow = {
                project.getProject_id(),
                project.getName(),
                Double.isNaN(project.getStatus()) ? "0.0 %" : project.getStatus() + " %",
                project.calculeCoutTotal() + " $",
                project.getDuree() + " h",
                project.getClient().getId() + "-" + project.getClient().getName()
            };
            projectsTableModel.addRow(projectRow);
        }
    }

    public void fillClientsCombobox() {
        clientsComboBox.removeAllItems();
        for (Client client : data.getClientsSet()) {
            clientsComboBox.addItem(client.getId() + " - " + client.getName());
        }
    }

    public void fillEmployeeFonctionComboBox() {
        EmployeeFonctionComboBox.removeAllItems();
        for (Fonction fon : data.getFonctionsSet()) {
            EmployeeFonctionComboBox.addItem(fon.getFId() + " - " + fon.getDescription());
        }

    }

    public void fillEmployeeSpecialiteComboBox() {
        specialiteEmployeeCombobox.removeAllItems();
        for (Specialite spe : data.getSpecialitesSet()) {
            specialiteEmployeeCombobox.addItem(spe.getId() + " - " + spe.getDescription());
        }

    }

    public void fillTheTasksTableModel() {
        clearTable(tasksTableModel);
        for (Task task : data.getTasksSet()) {
            Project p = data.getprojectOfTask(task);
            Object[] taskRow = {
                task.getTask_id(),
                task.getName(),
                Double.isNaN(task.getStatus()) ? "0.0 %" : task.getStatus() + " %",
                task.calculeCoutTotal() + " $",
                task.getDuree() + " h",
                p != null ? p.getProject_id() + "-" + p.getName() : "null"
            };
            tasksTableModel.addRow(taskRow);
        }
    }

    public void fillTheClientsTableModel() {
        clearTable(clientsTableModel);
        for (Client c : data.getClientsSet()) {
            Object[] clientRow = {
                c.getId(),
                c.getName()
            };
            clientsTableModel.addRow(clientRow);
        }
    }

    public void fillTheProcessusTableModel() {
        clearTable(processusTableModel);
        for (Processus p : data.getProcessusSet()) {
            Task task = data.getTaskOfProcessus(p);
            Object[] processusRow = {
                p.getProcessus_id(),
                p.getName(),
                p.getStatus(),
                p.getDuree(),
                task != null ? task.getTask_id() + "-" + task.getName() : "null"
            };
            processusTableModel.addRow(processusRow);
        }
    }

    public void filltheEmployeesTableModel() {
        clearTable(employeesTableModel);
        for (Employee emp : data.getEmployeesSet()) {
            Object[] employeeRow = {
                emp.getId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getFonction().getDescription(),
                emp.getFonction().getTarrifHeure()
            };
            employeesTableModel.addRow(employeeRow);
        }
    }

    public void fillMatieresPremieresTableModel() {
        clearTable(matieresTableModel);
        for (Materiel mat : data.getMaterielsSet()) {
            if (mat instanceof MatierePremiere) {
                Object[] matpRow = {
                    mat.getId(),
                    mat.getName(),
                    mat.getPrixUnitaire() + " $"
                };
                matieresTableModel.addRow(matpRow);
            }
        }
    }

    public void fillAutresMatieresTableModel() {
        clearTable(autresTableModel);
        for (Materiel mat : data.getMaterielsSet()) {
            if (mat instanceof AutreMatiere) {
                Object[] autrematRow = {
                    mat.getId(),
                    mat.getName(),
                    mat.getPrixUnitaire() + " $"
                };
                autresTableModel.addRow(autrematRow);
            }
        }
    }

    public void fillTaskTypeTableModel() {
        clearTable(taskTypesTableModel);
        for (TaskType taskt : data.getTaskTypeSet()) {
            Object[] tasktypeRow = {
                taskt.getTypeid(),
                taskt.getDesc()
            };
            taskTypesTableModel.addRow(tasktypeRow);
        }
    }

    public void filltheSpecialiteTableModel() {
        clearTable(specialiteTableModel);
        for (Specialite s : data.getSpecialitesSet()) {
            Object[] specialiteRow = {
                s.getId(),
                s.getDescription()
            };
            specialiteTableModel.addRow(specialiteRow);
        }
    }

    public void fillTheFonctionTableModel() {
        clearTable(fonctionTableModel);
        for (Fonction fon : data.getFonctionsSet()) {
            Object[] fonctionRow = {
                fon.getFId(),
                fon.getDescription(),
                fon.getTarrifHeure() + " $"
            };
            fonctionTableModel.addRow(fonctionRow);
        }
    }

    public void fillTaskTypeComboBox() {
        TaskTypeCombo.removeAllItems();
        for (TaskType taskType : data.getTaskTypeSet()) {
            TaskTypeCombo.addItem(taskType.getTypeid() + " - " + taskType.getDesc());
        }
    }

    public void fillProjectscomboBox() {
        ProjectsCombo.removeAllItems();
        for (Project proj : data.getProjectsSet()) {
            ProjectsCombo.addItem(proj.getProject_id() + " - " + proj.getName());
        }
    }

    public void fillTasksComboBox() {
        TaskCombo.removeAllItems();
        for (Task task : data.getTasksSet()) {

            TaskCombo.addItem(task.getTask_id() + " - " + task.getName());
        }
    }

    public void fillEmployeesCombobox() {

        employeeComboBox.removeAllItems();
        for (Employee emp : data.getEmployeesSet()) {
            employeeComboBox.addItem(emp.getId() + " - " + emp.getFirstName() + "  " + emp.getLastName());
        }
    }

    public void fillMatierspremierscombobox() {
        matierePremiereComboBox.removeAllItems();

        for (Materiel mat : data.getMaterielsSet()) {
            if (mat instanceof MatierePremiere) {
                matierePremiereComboBox.addItem(mat.getId() + " - " + mat.getName());
            }
        }
    }

    public void fillAutreMatieresComboBox() {
        autreMatiereComboBox.removeAllItems();

        for (Materiel mat : data.getMaterielsSet()) {
            if (mat instanceof AutreMatiere) {
                autreMatiereComboBox.addItem(mat.getId() + " - " + mat.getName());
            }
        }
    }

    @Override
    public void update(Observable observable, Object object) {
        if (observable instanceof DataModule) {
            // write the new data in the file then load them to the sets of data
            reloadData();
            // fill combo box
            fillClientsCombobox();
            fillProjectscomboBox();
            fillTaskTypeComboBox();
            fillTasksComboBox();
            fillEmployeeFonctionComboBox();
            fillEmployeeSpecialiteComboBox();
            fillEmployeesCombobox();
            fillMatierspremierscombobox();
            fillAutreMatieresComboBox();

            // fill the tables models
            fillTheProjectsTableModel();
            fillTheTasksTableModel();
            fillTheProcessusTableModel();
            fillTheClientsTableModel();
            filltheEmployeesTableModel();
            fillMatieresPremieresTableModel();
            fillAutresMatieresTableModel();
            fillTaskTypeTableModel();
            filltheSpecialiteTableModel();
            fillTheFonctionTableModel();

        }
    }

    public static void main(String[] args) {

        InputInterface in = new InputInterface();
        in.setVisible(true);
        JFrame frame = new JFrame("Project Management System");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new Filter());
        frame.setVisible(true);
        //DataModule d = new DataModule();
       
    }

}
