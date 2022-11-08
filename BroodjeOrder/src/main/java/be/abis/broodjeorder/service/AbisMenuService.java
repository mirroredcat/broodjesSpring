package be.abis.broodjeorder.service;

import be.abis.broodjeorder.exceptions.SandwichAlreadyExistsException;
import be.abis.broodjeorder.exceptions.SandwichNotFoundException;
import be.abis.broodjeorder.model.Menu;
import be.abis.broodjeorder.model.Sandwich;
import be.abis.broodjeorder.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AbisMenuService implements MenuService{

    @Autowired
    SandwichRepository sr;

    private Menu menuOfTheDay;

    @Override
    public void setMenuOfTheDay(Menu m) {
        menuOfTheDay = m;
    }

    @Override
    public Menu getMenuOfTheDay(){return menuOfTheDay;}

    @Override
    public Sandwich findSandwichInMenuOfDay(String sandwichName) {
        return menuOfTheDay.getSandwichList().stream().filter(s -> s.getSandwichName().equals(sandwichName))
                .findFirst().get();
    }

    @Override
    public Sandwich findSandwich(String sandwichName, String companyName){
        try {
            return sr.findSandwich(sandwichName, companyName);
        } catch (SandwichNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Sandwich findSandwich(int id){
        try {
            return sr.findSandwich(id);
        } catch (SandwichNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addSandwich(Sandwich s) {
        try {
            sr.addSandwich(s);
        } catch (SandwichAlreadyExistsException | SandwichNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateSandwichPrice(Sandwich s, Double newPrice) {
        try {
            sr.updateSandwichPrice(s, newPrice);
        } catch (SandwichNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateSandwichIngredients(Sandwich s, String newIngredients) {
        try {
            sr.updateSandwichIngredients(s, newIngredients);
        } catch (SandwichNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteSandwich(Sandwich s) {
        try {
            sr.deleteSandwich(s);
        } catch (SandwichNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
