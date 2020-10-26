package entities;

import java.util.Comparator;

public class InvoicePosition implements Comparable<InvoicePosition> {
    private int id;
    private long price;
    private String nomenclature_name;
    private int count;

    public InvoicePosition(int id, long price, String nomenclature_name, int count) {
        this.id = id;
        this.price = price;
        this.nomenclature_name = nomenclature_name;
        this.count = count;
    }


    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getNomenclature_name() {
        return nomenclature_name;
    }

    public void setNomenclature_name(String nomenclature_name) {
        this.nomenclature_name = nomenclature_name;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public int compareTo(InvoicePosition pos) {
        if (this.getCount() > pos.getCount())
            return -1;
        return 1;
    }
}
