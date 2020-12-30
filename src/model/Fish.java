package model;

public class Fish {

    private String fishId;
    private String fishName;
    private String fishType;
    private int fishPrice;
    private int fishStock;

    public Fish() {
    }

    public Fish(String fishId, String fishName, String fishType, int fishPrice, int fishStock) {
        this.fishId = fishId;
        this.fishName = fishName;
        this.fishType = fishType;
        this.fishPrice = fishPrice;
        this.fishStock = fishStock;
    }

    public String getFishId() {
        return fishId;
    }

    public void setFishId(String fishId) {
        this.fishId = fishId;
    }

    public String getFishName() {
        return fishName;
    }

    public void setFishName(String fishName) {
        this.fishName = fishName;
    }

    public String getFishType() {
        return fishType;
    }

    public void setFishType(String fishType) {
        this.fishType = fishType;
    }

    public int getFishPrice() {
        return fishPrice;
    }

    public void setFishPrice(int fishPrice) {
        this.fishPrice = fishPrice;
    }

    public int getFishStock() {
        return fishStock;
    }

    public void setFishStock(int fishStock) {
        this.fishStock = fishStock;
    }
}
