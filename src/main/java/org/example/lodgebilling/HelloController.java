package org.example.lodgebilling;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    @FXML private TextField guestNameField, nightsField, extrasField, paidField, notesField;
    @FXML private ComboBox<String> roomTypeBox;
    @FXML private Label statusLabel;
    @FXML private TableView<Bill> billsTable;
    @FXML private TableColumn<Bill, String> billCol, guestCol, roomCol;
    @FXML private TableColumn<Bill, Integer> nightsCol;
    @FXML private TableColumn<Bill, Double> totalCol, paidCol, balanceCol;

    private ObservableList<Bill> bills = FXCollections.observableArrayList();
    private int billCounter = 2;
    private double lastTotal = 0;

    @FXML
    public void initialize() {
        roomTypeBox.setItems(FXCollections.observableArrayList("Standard", "Deluxe", "Family"));
        roomTypeBox.setValue("Deluxe");
        billCol.setCellValueFactory(new PropertyValueFactory<>("billNo"));
        guestCol.setCellValueFactory(new PropertyValueFactory<>("guest"));
        roomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
        nightsCol.setCellValueFactory(new PropertyValueFactory<>("nights"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        paidCol.setCellValueFactory(new PropertyValueFactory<>("paid"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
        billsTable.setItems(bills);
        // Sample data like photo
        bills.add(new Bill("B002", "Mary Banda", "Standard", 2, 800.00, 500.00, 300.00));
    }

    @FXML private void handleCalculate() {
        try {
            int nights = Integer.parseInt(nightsField.getText().trim());
            double extras = extrasField.getText().isEmpty() ? 0 : Double.parseDouble(extrasField.getText());
            String room = roomTypeBox.getValue();
            double price = room.equals("Standard") ? 350 : room.equals("Deluxe") ? 550 : 750;
            double roomCharge = price * nights;
            if (nights >= 5) roomCharge *= 0.95; // 5% discount
            lastTotal = roomCharge + extras;
            statusLabel.setText(String.format("Total: K %.2f (Room K %.2f + Extras K %.2f)", lastTotal, roomCharge, extras));
        } catch (Exception e) { statusLabel.setText("Error: Check Nights, Extras are numbers"); }
    }

    @FXML private void handleSave() {
        try {
            if (guestNameField.getText().isEmpty()) { statusLabel.setText("Enter Guest name"); return; }
            if (lastTotal == 0) handleCalculate();
            int nights = Integer.parseInt(nightsField.getText().trim());
            double paid = paidField.getText().isEmpty() ? 0 : Double.parseDouble(paidField.getText());
            double balance = lastTotal - paid;
            String billNo = String.format("B%03d", ++billCounter);
            bills.add(new Bill(billNo, guestNameField.getText(), roomTypeBox.getValue(), nights, lastTotal, paid, balance));
            statusLabel.setText("Saved " + billNo + ". Balance: K " + String.format("%.2f", balance));
            lastTotal = 0;
        } catch (Exception e) { statusLabel.setText("Error saving - check fields"); }
    }
    @FXML private void handleNewGuest() { guestNameField.clear(); nightsField.clear(); extrasField.clear(); paidField.clear(); notesField.clear(); statusLabel.setText(""); lastTotal=0; }
    @FXML private void handleViewReceipt() { Bill b = billsTable.getSelectionModel().getSelectedItem(); if(b!=null) statusLabel.setText("Receipt "+b.getBillNo()+" Guest "+b.getGuest()+" Total K "+b.getTotal()); }
    @FXML private void handleDelete() { Bill b = billsTable.getSelectionModel().getSelectedItem(); if(b!=null) bills.remove(b); }
}