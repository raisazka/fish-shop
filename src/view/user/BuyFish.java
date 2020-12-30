package view.user;

import dao.CartDAO;
import dao.FishDAO;
import dao.singleton.UserSingleton;
import model.User;
import style.Font;
import view.validation.CartValidation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class BuyFish extends JFrame implements ActionListener, MouseListener {

    JTextField fishIdField, fishNameField, fishTypeField,
               removeIdField, fishPriceField, fishStockField;
    JSpinner qtyField;
    JButton addCartButton, removeFishButton,
            clearCartButton, checkoutButton;
    FishDAO fishDAO;
    CartDAO cartDAO;
    User user;
    DefaultTableModel fishTableModel, cartTableModel;
    JTable fishTable, cartTable;
    Vector<String> columnsFish, columnsCart;
    int selectedCartQty = 0;

    public BuyFish() {
        user = UserSingleton.getInstance();
        fishDAO = new FishDAO();
        cartDAO = new CartDAO();
        initFrame();
    }

    private void initFrame() {
        setTitle("Buy Fish");
        setSize(1200, 720);
        getContentPane().setBackground(Color.CYAN);
        GridLayout layout = new GridLayout(5, 1);
        layout.setVgap(10);
        setLayout(layout);
        initTitle();
        initFishTable();
        initForms();
        initCartTable();
        initButtons();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initTitle() {
        JLabel titleLabel = new JLabel("Buy Fish", SwingConstants.CENTER);
        titleLabel.setFont(Font.setFont(17));
        add(titleLabel);
    }

    private void refreshFishTable() {
        columnsFish = new Vector<>();
        columnsFish.add("FishID");
        columnsFish.add("FishName");
        columnsFish.add("FishType");
        columnsFish.add("FishPrice");
        columnsFish.add("FishStock");
        fishTableModel = new DefaultTableModel(fishDAO.getAllFish(), columnsFish) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        fishTable = new JTable(fishTableModel);
    }

    private void initFishTable() {
        refreshFishTable();
        fishTable.addMouseListener(this);
        JScrollPane sp = new JScrollPane(fishTable);
        add(sp);
    }


    private void initForms() {
        GridLayout containerLayout = new GridLayout(1, 2);
        containerLayout.setHgap(10);
        JPanel containerPanel = new JPanel(containerLayout);
        containerPanel.setBackground(Color.CYAN);
        JPanel firstPanel = new JPanel(new GridLayout(4, 2));
        firstPanel.setBackground(Color.CYAN);
        firstPanel.setBorder(new EmptyBorder(0, 10, 0,0));
        JPanel secondPanel = new JPanel(new GridLayout(4, 2));
        secondPanel.setBackground(Color.CYAN);
        secondPanel.setBorder(new EmptyBorder(0, 0,0,10));

        JLabel fishIdLabel = new JLabel("Fish Id");
        fishIdField = new JTextField();
        fishIdField.setEnabled(false);
        JLabel fishNameLabel = new JLabel("Fish Name");
        fishNameField = new JTextField();
        fishNameField.setEnabled(false);
        JLabel fishTypeLabel = new JLabel("Fish Type");
        fishTypeField = new JTextField();
        fishTypeField.setEnabled(false);
        JLabel removeLabel = new JLabel("Remove ID");
        removeIdField = new JTextField();
        removeIdField.setEnabled(false);

        firstPanel.add(fishIdLabel);
        firstPanel.add(fishIdField);
        firstPanel.add(fishNameLabel);
        firstPanel.add(fishNameField);
        firstPanel.add(fishTypeLabel);
        firstPanel.add(fishTypeField);
        firstPanel.add(removeLabel);
        firstPanel.add(removeIdField);

        JLabel fishPriceLabel = new JLabel("Fish Price");
        fishPriceField = new JTextField();
        fishPriceField.setEnabled(false);
        JLabel fishStockLabel = new JLabel("Fish Stock");
        fishStockField = new JTextField();
        fishStockField.setEnabled(false);
        JLabel fishQtyLabel = new JLabel("Quantity");
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, 100, 1);
        qtyField = new JSpinner(spinnerNumberModel);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBackground(Color.CYAN);
        addCartButton = new JButton("Add to Cart");
        addCartButton.addActionListener(this);
        btnPanel.add(addCartButton);

        secondPanel.add(fishPriceLabel);
        secondPanel.add(fishPriceField);
        secondPanel.add(fishStockLabel);
        secondPanel.add(fishStockField);
        secondPanel.add(fishQtyLabel);
        secondPanel.add(qtyField);
        secondPanel.add(btnPanel);

        containerPanel.add(firstPanel);
        containerPanel.add(secondPanel);

        add(containerPanel);
    }

    private void refreshCartTable() {
        columnsCart = new Vector<>();
        columnsCart.add("FishID");
        columnsCart.add("FishName");
        columnsCart.add("FishType");
        columnsCart.add("FishPrice");
        columnsCart.add("FishStock");
        columnsCart.add("Quantity");
        columnsCart.add("SubTotal");
        cartTableModel = new DefaultTableModel(cartDAO.getUserCartData(user.getId()), columnsCart) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cartTable = new JTable(cartTableModel);
        cartTable.addMouseListener(this);
    }

    private void initCartTable() {
        refreshCartTable();
        JScrollPane sp = new JScrollPane(cartTable);
        add(sp);
    }

    private void initButtons() {
        GridLayout layout = new GridLayout(1, 3);
        layout.setHgap(20);
        layout.setVgap(20);
        JPanel panel = new JPanel(layout);
        panel.setBackground(Color.CYAN);
        removeFishButton = new JButton("Remove Fish");
        removeFishButton.addActionListener(this);
        clearCartButton = new JButton("Clear Cart");
        clearCartButton.addActionListener(this);
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(this);
        panel.add(removeFishButton);
        panel.add(clearCartButton);
        panel.add(checkoutButton);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(addCartButton)) {
            int qty = (Integer) qtyField.getValue();
            int stock = fishStockField.getText().isEmpty() ? 0 : Integer.parseInt(fishStockField.getText());
            CartValidation.validateAddToCart(fishIdField.getText(), qty, stock);
        } else if(e.getSource().equals(removeFishButton)) {
            CartValidation.validateDeleteCart(removeIdField.getText(), selectedCartQty);
        } else if(e.getSource().equals(clearCartButton)) {
            int dialog = JOptionPane.showConfirmDialog(null, "Are you sure want to clear cart?",
                    null, JOptionPane.YES_NO_OPTION);
            if(dialog == JOptionPane.YES_OPTION) {
                cartDAO.deleteAllCart(user.getId());
            }
        } else if(e.getSource().equals(checkoutButton)) {
            int dialog = JOptionPane.showConfirmDialog(null, "Are you sure want to checkout?",
                    null, JOptionPane.YES_NO_OPTION);
            if(dialog == JOptionPane.YES_OPTION) {
                CartValidation.validateTransaction();
            }
        }
        refreshFishTableModel();
        refreshCartTableModel();
        fishTableModel.fireTableDataChanged();
        cartTableModel.fireTableDataChanged();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(fishTable)) {
            int selectedRowIndex = fishTable.getSelectedRow();
            fishIdField.setText(fishTableModel.getValueAt(selectedRowIndex, 0).toString());
            fishNameField.setText(fishTableModel.getValueAt(selectedRowIndex, 1).toString());
            fishTypeField.setText(fishTableModel.getValueAt(selectedRowIndex, 2).toString());
            fishPriceField.setText(fishTableModel.getValueAt(selectedRowIndex, 3).toString());
            fishStockField.setText(fishTableModel.getValueAt(selectedRowIndex, 4).toString());
        } else if(e.getSource().equals(cartTable)) {
            int selectedIndex = cartTable.getSelectedRow();
            removeIdField.setText(cartTableModel.getValueAt(selectedIndex, 0).toString());
            selectedCartQty = Integer.parseInt(cartTableModel.getValueAt(selectedIndex, 5).toString());
        }
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

    private void refreshFishTableModel() {
        fishTableModel = new DefaultTableModel(fishDAO.getAllFish(), columnsFish) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        fishTable.setModel(fishTableModel);
    }

    private void refreshCartTableModel() {
        cartTableModel = new DefaultTableModel(cartDAO.getUserCartData(user.getId()), columnsCart) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        cartTable.setModel(cartTableModel);
    }

}
