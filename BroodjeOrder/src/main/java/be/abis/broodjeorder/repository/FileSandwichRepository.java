package be.abis.broodjeorder.repository;


import be.abis.broodjeorder.model.Person;
import be.abis.broodjeorder.model.Sandwich;
import be.abis.broodjeorder.model.SandwichCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FileSandwichRepository implements SandwichRepository{

    private List<Sandwich> sandwichList;

   // @Value("/temp/broodjes/sandwiches.csv")
    private static final String fileLoc = "/temp/broodjes/sandwiches.csv";

    @PostConstruct
    public void init() {
        this.readFile();

    }

    public FileSandwichRepository() {
        sandwichList = new ArrayList<>();

    }

    public void readFile(){

        List<String> stringLines = null;
        try {
            stringLines = Files.readAllLines(Paths.get(fileLoc));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String s : stringLines) {
            String[] vals = s.split(";");
            if (!vals[0].equals("")){
                Sandwich sa = new Sandwich();
                sa.setId(!vals[0].equals("null") ? Integer.valueOf(vals[0]):null);
                sa.setSandwichName(!vals[1].equals("null") ? vals[1]:null);
                if(!vals[2].equals("null")) {
                    switch (vals[2]) {
                        case "Pinky's":
                            sa.setSandwichCompany(SandwichCompany.PINKYS);
                        case "Vleugels":
                            sa.setSandwichCompany(SandwichCompany.VLEUGELS);
                        default:
                            sa.setSandwichCompany(null);
                    }
                }
                sa.setCategory(!vals[3].equals("null") ? vals[3]:null);
                sa.setPrice(!vals[4].equals("null") ? Double.valueOf(vals[4]):null);
                sa.setIngredients(vals[5]);

                sandwichList.add(sa);
            }
        }
    }

    public List<Sandwich> findSandwichesByRestaurant(String name) {
        return getSandwichList().stream()
                .filter(s -> s.getSandwichCompany().getCompanyName().equals(name))
                .collect(Collectors.toList());
    }

    public Sandwich findSandwich(int id){
        return getSandwichList().stream()
                .filter(s -> s.getId() == id)
                .findFirst().get();
    }

    public List<Sandwich> getSandwichList() {
        return sandwichList;
    }

    public void setSandwichList(ArrayList<Sandwich> sandwichList) {
        this.sandwichList = sandwichList;
    }


    public void addSandwich(Sandwich sandwich) throws IOException {
        sandwichList.add(sandwich);
        this.writeToFile();
    }

    public void deleteSandwich(Sandwich sandwich) throws IOException {
        sandwichList.remove(sandwich);
        this.writeToFile();
    }

    public StringBuilder parseSandwich(Sandwich sandwich){
        StringBuilder sb = new StringBuilder();
        sb.append(sandwich.getId()).append(";")
                .append(sandwich.getSandwichName()).append(";")
                .append(sandwich.getSandwichCompany().getCompanyName()).append(";")
                .append(sandwich.getCategory()).append(";")
                .append(sandwich.getPrice()).append(";")
                .append(sandwich.getIngredients());
        return sb;
    }

    private void writeToFile() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(fileLoc));

        for (Sandwich s : sandwichList) {
            StringBuilder sb = this.parseSandwich(s);
            pw.println(s);
        }
        pw.close();
    }

    public void updateSandwichPrice(Double newPrice){

    }

}
