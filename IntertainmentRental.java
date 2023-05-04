import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
class Item{
    protected String title;
    protected String genre;
    protected String releaseDate;
    protected String ID;
    protected double rentalFee;
    protected boolean isAvailable = true;
    public Item(String title, String genre, String releaseDate, String iD, double rentalFee) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        ID = iD;
        this.rentalFee = rentalFee;
    }
    public String getTitle() {
        return title;
    }
    public String getGenre() {
        return genre;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public String getID() {
        return ID;
    }
    public double getRentalFee() {
        return rentalFee;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }
    
    
}
class Movie extends Item{
    private ArrayList<String> cast;
    private String Director;
    public Movie(String title, String genre, ArrayList<String> cast, String releaseDate, String ID,double rentalFee,String Director) {
        super(title, genre, releaseDate, ID, rentalFee);
        this.cast = new ArrayList<>(cast);
        this.Director = Director;
    }
    public ArrayList<String>  getCast() {
        return cast;
    }
    public String getDirector() {
        return Director;
    }
}
class Game extends Item{
    private String platform;
    private String publisher;
    
    public Game(String title, String genre,String Platform , String releaseDate, String ID,double rentalFee,String Publisher) {
        super(title, genre, releaseDate, ID, rentalFee);
        this.platform = Platform;
        this.publisher = Publisher;
    }
    public String getID() {
        return ID;
    }
    public String getPlatform() {
        return platform;
    }
    public String getPublisher() {
        return publisher;
    }
}
class Book extends Item{
    private String author;
    private String publisher;
    
    public Book(String title, String genre,String author , String releaseDate, String ID,double rentalFee,String Publisher) {
        super(title, genre, releaseDate, ID, rentalFee);
        this.author = author;
        this.publisher = Publisher;
    }
    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
    }
}
class Customer{
    private String name;
    private String email;
    private int phone;
    private String address;
    private String ID;
    private ArrayList<Rental> rentals;
    public Customer(String name, String email, int phone, String address, String id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.ID = id;
        rentals = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public int getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public String getId() {
        return ID;
    }
    public ArrayList<Rental> getRentals() {
        return rentals;
    }
}
class Rental{
    private Item item;
    private Customer customer;
    private String ID;
    private Date rentalDate;
    private Date returnDate;
    public Rental(Item item, Customer customer, String iD) {
        this.item = item;
        this.customer = customer;
        ID = iD;
        item.setAvailable(false);
        this.rentalDate = new Date();
        customer.getRentals().add(this);
    }
    public Item getItem() {
        return item;
    }
    public Customer getCustomer() {
        return customer;
    }
    public String getID() {
        return ID;
    }
    public Date getRentalDate() {
        return rentalDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
        item.setAvailable(true);
    }
    public double calculateLateFee(){
        long timeDiff = Math.abs(returnDate.getTime() - rentalDate.getTime());
        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        return daysDiff*item.getRentalFee();
    }
    

}
class RentalStore{
    private ArrayList<Customer> customers;
    private ArrayList<Item> items;
    RentalStore(){
        customers= new ArrayList<>();
        items = new ArrayList<>();
    }
    public void register(Customer customer){
        customers.add(customer);
    }
    public void addItem(Item item){
        items.add(item);
    }
    public void removeItem(Item item){
        items.remove(item);
    }
    public ArrayList<Item> getAvailableItems(){
        ArrayList<Item> AvailableItems= new ArrayList<>();
        for(int i =0;i<items.size();i++){
            if(items.get(i).isAvailable()){
                AvailableItems.add(items.get(i));
            }
        }
        return AvailableItems;
    }
    public void rentItem(Item item,Customer customer){
        for(Item i: items){
        if(i.equals(item)){
        if(i.isAvailable()){
            new Rental(item, customer, customer.getId() + item.getID());
        }
    }        
    }
    }
    public void returnItem(Rental rental){
        rental.setReturnDate(new Date());
    }
    public Customer getCustomerByID(String id){
        for(int i =0;i<customers.size();i++){
            if(customers.get(i).getId().equals(id)){
                return customers.get(i);
            }
        }
        return null;
    }
    public Item getItemByID(String id){
        for(int i =0;i<items.size();i++){
            if(items.get(i).getID().equals(id)){
                return items.get(i);
            }
        }
        return null;
    }
}

public class IntertainmentRental {
}
