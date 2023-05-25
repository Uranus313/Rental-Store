package Ure;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
class Item{
    protected String title;
    protected String genre;
    protected String releaseDate;
    protected int id;
    protected double rentalFee;
    protected boolean isAvailable ;
    public Item(String title, String genre, String releaseDate, int id, boolean isAvailable) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.id = id;
        this.isAvailable = isAvailable;
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
    public int getid() {
        return id;
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
    public void setTitle(String title) {
        this.title = title;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    
}
class Movie extends Item{
    private String cast;
    private String director;
    public Movie(String title, String genre, String cast, String releaseDate, int id,boolean isAvailable,String director) {
        super(title, genre, releaseDate, id, isAvailable);
        this.cast = cast;
        this.director = director;
    }
    public String  getCast() {
        return cast;
    }
    public String getdirector() {
        return director;
    }
    public void setCast(String cast) {
        this.cast = cast;
    }
    public void setDirector(String director) {
        this.director = director;
    }
}
class Game extends Item{
    private String manufacturer;
    private String director;
    
    public Game(String title, String genre,String manufacturer , String releaseDate, int id,boolean isAvailable,String director) {
        super(title, genre, releaseDate, id, isAvailable);
        this.manufacturer = manufacturer;
        this.director = director;
    }
    public int getid() {
        return id;
    }
    public String getmanufacturer() {
        return manufacturer;
    }
    public String getdirector() {
        return director;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    public void setDirector(String director) {
        this.director = director;
    }
}
class Book extends Item{
    private String writer;
    private String publisher;
    
    public Book(String title, String genre,String writer , String releaseDate, int id,boolean isAvailable,String Publisher) {
        super(title, genre, releaseDate, id, isAvailable);
        this.writer = writer;
        this.publisher = Publisher;
    }
    public String getwriter() {
        return writer;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
class Customer{
    private String name;
    private String email;
    private String phone;
    private String address;
    private int id;
    private ArrayList<Rental> rentals;
    public Customer(String name, String email, String phone, String address, int id,ArrayList<Rental> rentals) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.id = id;
        this.rentals = new ArrayList<>();

    }
    public void initialize(){
        this.rentals = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public int getid() {
        return id;
    }
    public ArrayList<Rental> getRentals() {
        return rentals;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setRentals(ArrayList<Rental> rentals) {
        this.rentals = rentals;
    }
    
}
class Rental{
    private Item item;
    private int customerid;
    private int id;
    private Date rentalDate;
    private Date returnDate;
    public Rental(Item item, Customer customer, int i) {
        this.item = item;
        this.customerid = customer.getid();
        id = i;
        item.setAvailable(false);
        this.rentalDate = new Date();
        customer.getRentals().add(this);
    }
    public Item getItem() {
        return item;
    }
    public int getCustomerid() {
        return customerid;
    }
    public int getid() {
        return id;
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
        System.out.println(1);
        for(Item i: items){
        if(i.equals(item)){
        if(i.isAvailable()){
            System.out.println(8);
            new Rental(item, customer, customer.getid() + item.getid());
        }
    }        
    }
    }
    public void returnItem(Rental rental){
        rental.setReturnDate(new Date());
    }
    public Customer getCustomerByid(int id){
        for(int i =0;i<customers.size();i++){
            if(customers.get(i).getid()==id){
                return customers.get(i);
            }
        }
        return null;
    }
    public Item getItemByid(int id){
        for(int i =0;i<items.size();i++){
            if(items.get(i).getid()==id){
                return items.get(i);
            }
        }
        return null;
    }
}

public class IntertainmentRental {
}
