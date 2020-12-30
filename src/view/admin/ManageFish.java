package view.admin;

import dao.FishDAO;
import helper.RandomString;
import style.Font;
import view.validation.FishValidation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.Vector;

public class ManageFish extends JFrame implements ActionListener, MouseListener {

    GridBagConstraints gc = new GridBagConstraints();
    //Update Fields
    JTextField fishIdField, fishNameField ,fishPriceField, fishStockField;
    //insert fields
    JTextField newIdField, newNameField,
            newPriceField;
    JPanel containerPanel;
    JButton updateButton, removeButton, addStockButton,
            insertButton, clearButton;
    JSpinner stockField, newStockField;
    JComboBox<String> cmbType, cmbTypeNew;
    SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, 100, 1);
    DefaultTableModel fishTableModel;
    JTable fishTable;
    FishDAO fishDAO;

    Vector<String> columns;
    String selectedType = "", newType = "";
    String fishId = "FH-" + RandomString.generateAlphanum();

    public ManageFish() {
        fishDAO = new FishDAO();
        initFrame();
    }

    private void initFrame() {
        setTitle("Manage Fish");
        setSize(1200, 750);
        getContentPane().setBackground(Color.cyan);
        setLayout(new GridBagLayout());
        initTitle();
        initFishTable();
        initFormPanel();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initTitle() {
        JLabel titleLabel = new JLabel("Manage Fish", SwingConstants.CENTER);
        titleLabel.setFont(Font.setFont(20));
        gc.insets = new Insets(20, 0,0,0);
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.NORTH;
        gc.weighty = 0.02;
        add(titleLabel, gc);
    }

    private void initFishTable() {
        columns = new Vector<>();
        columns.add("FishID");
        columns.add("FishName");
        columns.add("FishType");
        columns.add("FishPrice");
        columns.add("FishStock");
        fishTableModel = new DefaultTableModel(fishDAO.getAllFish(), columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
         fishTable = new JTable(fishTableModel) {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(1150, 300);
            }
        };
        fishTable.addMouseListener(this);
        JScrollPane sp = new JScrollPane(fishTable);
        sp.setSize(this.getWidth(), 200);
        gc.fill = GridBagConstraints.HORIZONTAL;
        setBackground(Color.cyan);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weighty = 1;
        add(sp, gc);
    }

    private void initFormPanel() {
        GridLayout layout = new GridLayout(2, 2);
        layout.setHgap(10);
        containerPanel = new JPanel(layout);
        containerPanel.setBackground(Color.CYAN);
        containerPanel.add(initUpdateForm());
        containerPanel.add(initInsertForm());
        containerPanel.add(initUpdateButtons());
        containerPanel.add(initInsertButtons());
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridy = 2;
        gc.gridx = 0;
        gc.weighty = 3;
        add(containerPanel, gc);
    }

    private JPanel initUpdateForm() {
        JPanel updateFormPanel = new JPanel(new GridLayout(5, 2));
        updateFormPanel.setBackground(Color.CYAN);
        JLabel fishIdLabel = new JLabel("Fish Id");
        fishIdField = new JTextField();
        fishIdField.setEnabled(false);
        JLabel fishNameLabel = new JLabel("Fish Name");
        fishNameField = new JTextField();
        JLabel fishTypeLabel = new JLabel("Fish Type");
        cmbType = new JComboBox<>();
        cmbType.addItem("---- Choose Type ----");
        cmbType.addItem("Freshwater");
        cmbType.addItem("Seawater");
        cmbType.addItem("Brackish");
        cmbType.addItemListener(e -> {
            if(cmbType.getSelectedIndex() > 0) {
                selectedType = Objects.requireNonNull(cmbType.getSelectedItem()).toString();
            }
        });
        JLabel fishPriceLabel = new JLabel("Fish Price");
        fishPriceField = new JTextField();
        JLabel fishStockLabel = new JLabel("Fish Stock");
        fishStockField = new JTextField();
        fishStockField.setEnabled(false);
        updateFormPanel.add(fishIdLabel);
        updateFormPanel.add(fishIdField);
        updateFormPanel.add(fishNameLabel);
        updateFormPanel.add(fishNameField);
        updateFormPanel.add(fishTypeLabel);
        updateFormPanel.add(cmbType);
        updateFormPanel.add(fishPriceLabel);
        updateFormPanel.add(fishPriceField);
        updateFormPanel.add(fishStockLabel);
        updateFormPanel.add(fishStockField);
        return updateFormPanel;
    }

    private JPanel initInsertForm() {
        JPanel insertFormPanel = new JPanel(new GridLayout(5, 2));
        insertFormPanel.setBackground(Color.CYAN);
        JLabel newIdLabel = new JLabel("New Fish Id");
        newIdField = new JTextField();
        newIdField.setText(fishId);
        newIdField.setEnabled(false);
        JLabel newNameLabel = new JLabel("New Fish Name");
        newNameField = new JTextField();
        JLabel newTypeLabel = new JLabel("New Fish Type");
        cmbTypeNew = new JComboBox<>();
        cmbTypeNew.addItem("---- Choose Type ----");
        cmbTypeNew.addItem("Freshwater");
        cmbTypeNew.addItem("Seawater");
        cmbTypeNew.addItem("Brackish");
        cmbTypeNew.addItemListener(e -> {
            if(cmbTypeNew.getSelectedIndex() > 0) {
                newType = Objects.requireNonNull(cmbTypeNew.getSelectedItem()).toString();
            }
        });
        JLabel newPriceLabel = new JLabel("New Fish Price");
        newPriceField = new JTextField();
        JLabel newStockLabel = new JLabel("New Fish Stock");
        newStockField = new JSpinner(spinnerNumberModel);
        insertFormPanel.add(newIdLabel);
        insertFormPanel.add(newIdField);
        insertFormPanel.add(newNameLabel);
        insertFormPanel.add(newNameField);
        insertFormPanel.add(newTypeLabel);
        insertFormPanel.add(cmbTypeNew);
        insertFormPanel.add(newPriceLabel);
        insertFormPanel.add(newPriceField);
        insertFormPanel.add(newStockLabel);
        insertFormPanel.add(newStockField);
        return insertFormPanel;
    }

    private JPanel initUpdateButtons() {
        JPanel updateButtonPanel = new JPanel(new GridBagLayout());
        updateButtonPanel.setBackground(Color.CYAN);
        GridBagConstraints c = new GridBagConstraints();
        updateButton = new JButton("Update Fish");
        updateButton.addActionListener(this);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        updateButtonPanel.add(updateButton, c);

        removeButton = new JButton("Remove Fish");
        removeButton.addActionListener(this);
        c.gridwidth = 1;
        c.weightx = 1;
        c.gridx = 1;
        updateButtonPanel.add(removeButton, c);

        JLabel addStockLabel = new JLabel("Add Stock");
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 1;
        updateButtonPanel.add(addStockLabel, c);
        stockField = new JSpinner(spinnerNumberModel);
        c.weightx = 1;
        c.gridx = 1;
        updateButtonPanel.add(stockField, c);

        addStockButton = new JButton("Add Stock");
        addStockButton.addActionListener(this);
        c.weightx = 1;
        c.gridx = 2;
        updateButtonPanel.add(addStockButton, c);
        return updateButtonPanel;
    }

    private JPanel initInsertButtons() {
        JPanel insertButtonPanel = new JPanel(new GridLayout(2, 1));
        insertButtonPanel.setBackground(Color.CYAN);
        insertButton = new JButton("Insert Fish");
        insertButton.addActionListener(this);
        clearButton = new JButton("Reset");
        clearButton.addActionListener(this);
        insertButtonPanel.add(insertButton);
        insertButtonPanel.add(clearButton);
        return insertButtonPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(updateButton)) {
            int price = generatePrice(fishPriceField.getText());
            FishValidation.validateFish(fishIdField.getText(), fishNameField.getText(), selectedType, price);
        } else if(e.getSource().equals(insertButton)) {
            int price = generatePrice(newPriceField.getText());
            FishValidation.validateFish(newIdField.getText(), newNameField.getText(),
                    newType, price,(Integer)newStockField.getValue());
        } else if(e.getSource().equals(clearButton)) {
            clearForms();
        } else if(e.getSource().equals(removeButton)) {
           FishValidation.validateRemove(fishIdField.getText());
        } else if(e.getSource().equals(addStockButton)) {
            FishValidation.validateStock(fishIdField.getText(), (Integer)stockField.getValue());
        }
        refreshTable();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedIndex = fishTable.getSelectedRow();
        fishIdField.setText(fishTable.getValueAt(selectedIndex, 0).toString());
        fishNameField.setText(fishTable.getValueAt(selectedIndex, 1).toString());
        fishPriceField.setText(fishTable.getValueAt(selectedIndex, 3).toString());
        fishStockField.setText(fishTable.getValueAt(selectedIndex, 4).toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private int generatePrice(String source) {
        int price;
        try {
            price = Integer.parseInt(source);
        } catch (NumberFormatException error) {
            price = 0;
        }
        return price;
    }

    private void clearForms() {
        fishIdField.setText("");
        fishNameField.setText("");
        cmbType.setSelectedIndex(0);
        fishPriceField.setText("");
        fishStockField.setText("");
        selectedType = "";

        fishId = "FH-"+ RandomString.generateAlphanum();
        newIdField.setText(fishId);
        newNameField.setText("");
        cmbTypeNew.setSelectedIndex(0);
        newType = "";
        newPriceField.setText("");
        newStockField.setValue(0);
    }

    private void refreshTable() {
        fishTableModel = new DefaultTableModel(fishDAO.getAllFish(), columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        fishTable.setModel(fishTableModel);
    }

}
