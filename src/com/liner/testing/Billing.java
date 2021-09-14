package com.liner.testing;

public class Billing extends Model {
    private final long shopId;
    private final long cost;

    public Billing(long id, long shopId, long cost) {
        super(id);
        this.shopId = shopId;
        this.cost = cost;
    }


    public static Billing get(String data) {
        return new Billing(Long.parseLong(data.split(",")[0]), Long.parseLong(data.split(",")[1]), Long.parseLong(data.split(",")[2]));
    }

    public long getId() {
        return id;
    }

    public long getShopId() {
        return shopId;
    }

    public long getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Billing{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", cost=" + cost +
                '}';
    }
}
