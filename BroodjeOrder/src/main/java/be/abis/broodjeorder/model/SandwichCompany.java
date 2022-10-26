package be.abis.broodjeorder.model;

public enum SandwichCompany {

    VLEUGELS (1,"Vleugels"),
    PINKYS (2,"Pinky's");

    private int id;
    private String companyName;


    SandwichCompany(int id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
