package model;

public class Cart {

    private String userId;
    private String fishId;
    private int qty;

    public Cart() {
    }

    public Cart(String userId, String fishId, int qty) {
        this.userId = userId;
        this.fishId = fishId;
        this.qty = qty;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
