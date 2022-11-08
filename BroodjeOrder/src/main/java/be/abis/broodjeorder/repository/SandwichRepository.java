package be.abis.broodjeorder.repository;

import be.abis.broodjeorder.exceptions.SandwichAlreadyExistsException;
import be.abis.broodjeorder.exceptions.SandwichNotFoundException;
import be.abis.broodjeorder.model.Sandwich;

import java.io.IOException;
import java.util.List;

public interface SandwichRepository {

    public List<Sandwich> getSandwichList();

    public List<Sandwich> findSandwichesByRestaurant(String name);
    public Sandwich findSandwich(int id) throws SandwichNotFoundException;
    public Sandwich findSandwich(String sandwichName, String companyName) throws SandwichNotFoundException;
    public void addSandwich(Sandwich sandwich) throws SandwichAlreadyExistsException, SandwichNotFoundException;
    public void deleteSandwich(Sandwich sandwich) throws SandwichNotFoundException;
    public void updateSandwichPrice(Sandwich s, Double newPrice) throws SandwichNotFoundException;
    public void updateSandwichIngredients(Sandwich s, String newIngredients) throws SandwichNotFoundException;
}
