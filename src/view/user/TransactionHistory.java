package view.user;

import dao.TransactionDAO;
import dao.singleton.UserSingleton;
import model.User;
import style.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class TransactionHistory extends JFrame implements MouseListener {

    JTextField selectedIdField, totalField;
    JTable headerTable, detailTable;
    DefaultTableModel tableModel;
    TransactionDAO transactionDAO;
    User user;
    Vector<String> columns;


    public TransactionHistory() {
        user = UserSingleton.getInstance();
        transactionDAO = new TransactionDAO();
        initFrame();
    }

    private void initFrame() {
        setTitle("Transaction History");
        setSize(1200, 720);
        getContentPane().setBackground(Color.CYAN);
        GridLayout layout = new GridLayout(5, 1);
        setLayout(layout);
        initTitle();
        initHeaderTable();
        initSelectedField();
        initDetailTable();
        initTotalField();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initTitle() {
        JLabel titleLabel = new JLabel("Transaction History", SwingConstants.CENTER);
        titleLabel.setFont(Font.setFont(17));
        add(titleLabel);
    }

    private void initHeaderTable() {
        Vector<String> columns = new Vector<>();
        columns.add("TransactionID");
        columns.add("UserID");
        columns.add("TransactionDate");
        DefaultTableModel tableModel = new DefaultTableModel(transactionDAO.getTransactionData(user.getId()), columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        headerTable = new JTable(tableModel);
        headerTable.addMouseListener(this);
        JScrollPane sp = new JScrollPane(headerTable);
        add(sp);
    }

    private void initSelectedField() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 10));
        panel.setBackground(Color.CYAN);
        panel.setPreferredSize(new Dimension(this.getWidth(), 60));
        selectedIdField = new JTextField();
        selectedIdField.setEnabled(false);
        selectedIdField.setPreferredSize(new Dimension(200, 40));
        JLabel label = new JLabel("Selected ID");
        panel.add(label);
        panel.add(selectedIdField);
        add(panel);
    }

    private void initDetailTable() {
        Vector<Vector<String>> dataDummy = new Vector<>();
        columns = new Vector<>();
        columns.add("TransactionID");
        columns.add("FishID");
        columns.add("FishName");
        columns.add("FishType");
        columns.add("FishPrice");
        columns.add("Quantity");
        columns.add("Subtotal");
        tableModel = new DefaultTableModel(dataDummy, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        detailTable = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(detailTable);
        add(sp);
    }

    private void initTotalField() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 10, 10));
        panel.setBackground(Color.CYAN);
        JLabel label = new JLabel("Grand Total");
        totalField = new JTextField();
        totalField.setEnabled(false);
        totalField.setPreferredSize(new Dimension(200, 40));
        panel.add(label);
        panel.add(totalField);
        add(panel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedIndex = headerTable.getSelectedRow();
        selectedIdField.setText(headerTable.getValueAt(selectedIndex, 0).toString());
        tableModel = new DefaultTableModel(transactionDAO.getTransactionDetailData(selectedIdField.getText()), columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        detailTable.setModel(tableModel);
        totalField.setText(String.valueOf(TransactionDAO.grandTotal));
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
}
