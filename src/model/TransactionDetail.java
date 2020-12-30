package model;

public class TransactionDetail {

    private String transactionId;
    private String fishId;
    private int qty;

    public TransactionDetail() {
    }

    public TransactionDetail(String transactionId, String fishId, int qty) {
        this.transactionId = transactionId;
        this.fishId = fishId;
        this.qty = qty;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFishId() {
        return fishId;
    }

    public void setFishId(String fishId) {
        this.fishId = fishId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
