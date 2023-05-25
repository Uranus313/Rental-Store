package Ure;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Gson gson = new Gson();
        Reader reader = new FileReader("C:/Users/MEHRBOD/OneDrive/Documents/GitHub/Rental-Store/demo/src/test/TestYourFork.json");
       AllData allData = gson.fromJson(reader, new TypeToken<AllData>(){}.getType());
       allData.getCustomers().get(0).initialize();
       allData.getCustomers().get(1).initialize();
       allData.getCustomers().get(2).initialize();
       RentalStore rentalStore = new RentalStore();
       for(Customer i: allData.getCustomers()){
        rentalStore.register(i);
       }
    //    for(Item i : allData.getBooks()){
    //     rentalStore.addItem(i);
    //     if(i.getid()==3 || i.getid()==2){
    //         rentalStore.rentItem(i, allData.getCustomers().get(0));
    //     }
    //     if(i.getid()==1 || i.getid()==7){
    //         rentalStore.rentItem(i, allData.getCustomers().get(1));
    //     }
    //     if(i.getid()==9){
    //         rentalStore.rentItem(i, allData.getCustomers().get(2));
    //     }
    //    }
    // for(Item i : allData.getGames()){
    //     rentalStore.addItem(i);
    //     if(i.getid()==4){
    //         rentalStore.rentItem(i, allData.getCustomers().get(2));
    //     }
    // }
       new Rental(allData.getBooks().get(2), allData.getCustomers().get(0),1);
       new Rental(allData.getBooks().get(1), allData.getCustomers().get(0),2);
       new Rental(allData.getBooks().get(0), allData.getCustomers().get(1),3);
       new Rental(allData.getBooks().get(6), allData.getCustomers().get(1),4);
       new Rental(allData.getBooks().get(8), allData.getCustomers().get(2),5);
       new Rental(allData.getGames().get(3), allData.getCustomers().get(2),6);
       String json = gson.toJson(allData);
       try{
        FileWriter writer = new FileWriter("C:/Users/MEHRBOD/OneDrive/Documents/GitHub/Rental-Store/demo/src/test/TestYourFork.json");
        writer.write(json);
        writer.close();
       }catch(Exception e){
        e.printStackTrace();

       } 

    }
}
