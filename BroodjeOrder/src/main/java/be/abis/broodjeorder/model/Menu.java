package be.abis.broodjeorder.model;

import java.util.List;

public class Menu {

    private SandwichCompany sandwichCompany;
    private List<Sandwich> sandwichList;

    public Menu(SandwichCompany sandwichCompany, List<Sandwich> sandwichList) {
        this.sandwichCompany = sandwichCompany;
        this.sandwichList = sandwichList;
    }

    public SandwichCompany getSandwichCompany() {
        return sandwichCompany;
    }

    public void setSandwichCompany(SandwichCompany sandwichCompany) {
        this.sandwichCompany = sandwichCompany;
    }

    public List<Sandwich> getSandwichList() {
        return sandwichList;
    }

    public void setSandwichList(List<Sandwich> sandwichList) {
        this.sandwichList = sandwichList;
    }
}
