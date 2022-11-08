package be.abis.broodjeorder.service;

import be.abis.broodjeorder.model.Menu;
import be.abis.broodjeorder.model.Sandwich;

public interface MenuService {

    public void setMenuOfTheDay(Menu m);
    public Menu getMenuOfTheDay();

    public Sandwich findSandwichInMenuOfDay(String sandwichName);
    public Sandwich findSandwich(String sandwichName, String companyName);
    public Sandwich findSandwich(int id);
    public void addSandwich(Sandwich s);
    public void updateSandwichPrice(Sandwich s, Double newPrice);
    public void updateSandwichIngredients(Sandwich s, String newIngredients);
    public void deleteSandwich(Sandwich s);


}
