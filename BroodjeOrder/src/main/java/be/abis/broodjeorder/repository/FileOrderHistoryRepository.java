package be.abis.broodjeorder.repository;

import be.abis.broodjeorder.model.DayOrder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileOrderHistoryRepository implements OrderHistoryRepository{

    private List<DayOrder> dayOrderHistoryList = new ArrayList<>();
    private String fileLocation = "orderhistory.csv";

    public FileOrderHistoryRepository() {
    }




}
