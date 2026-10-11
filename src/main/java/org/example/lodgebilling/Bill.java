package org.example.lodgebilling;

public class Bill {
    private String billNo;
    private String guest;
    private String room;
    private int nights;
    private double total;
    private double paid;
    private double balance;

    public Bill(String billNo, String guest, String room, int nights, double total, double paid, double balance) {
        this.billNo = billNo;
        this.guest = guest;
        this.room = room;
        this.nights = nights;
        this.total = total;
        this.paid = paid;
        this.balance = balance;
    }
    public String getBillNo() { return billNo; }
    public String getGuest() { return guest; }
    public String getRoom() { return room; }
    public int getNights() { return nights; }
    public double getTotal() { return total; }
    public double getPaid() { return paid; }
    public double getBalance() { return balance; }
}