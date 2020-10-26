package entities;

public class Nomenclature {
    private String name;
    private int inside_code;

    public Nomenclature(String name, int inside_code) {
        this.name = name;
        this.inside_code = inside_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInside_code() {
        return inside_code;
    }

    public void setInside_code(int inside_code) {
        this.inside_code = inside_code;
    }
}
