/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nfa035.project;


import javax.swing.table.DefaultTableModel;

// my own table model to make the cells not editable

class NonEditableTableModel extends DefaultTableModel {

    public NonEditableTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

  
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Return false to make all cells non-editable
    }
}
