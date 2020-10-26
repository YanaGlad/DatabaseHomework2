package entities;

import java.sql.Date;

public class Invoice {
    private int id;
    private Date organization_date;
    private String organization_name;

    public Invoice(int id, Date organization_date, String organization_name) {
        this.id = id;
        this.organization_date = organization_date;
        this.organization_name = organization_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrganization_date() {
        return organization_date;
    }

    public void setOrganization_date(Date organization_date) {
        this.organization_date = organization_date;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }
}
