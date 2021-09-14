package com.liner.testing;

public class Market extends Model{
    private final String name;

    public Market(long id, String name) {
        super(id);
        this.name = name;
    }

    public static Market get(String data){
        return new Market(Long.parseLong(data.split(",")[0]), data.split(",")[1]);
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Market{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
