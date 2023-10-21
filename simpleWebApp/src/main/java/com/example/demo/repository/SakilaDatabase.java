package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SakilaDatabase {
    private Connection connection;

    public SakilaDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/sakila", "root", "1234567890");
        } catch (SQLException e) {
            System.out.println("SQLException=" + e.getMessage());
        }
    }

    public List<String> findFilms(String input) {
        try {
            PreparedStatement statement = connection.prepareStatement("select title from film where title like ?");
            statement.setString(1, "%" + input + "%");
            ResultSet results = statement.executeQuery();

            ArrayList<String> output = new ArrayList<>();

            while (results.next()) {
                String title = results.getString(1);
                output.add(title);
            }

            statement.close();
            return output;
        } catch (SQLException e) {
            System.out.println("SQLException=" + e.getMessage());
        }

        return null;
    }

    public List<SalesDataYearMonth> getSalesData(int year, int month) {
        try {
            PreparedStatement statement = connection.prepareStatement("select rental.rental_id,\n" +
                    "    customer.first_name,\n" +
                    "    customer.last_name,\n" +
                    "    payment.amount,\n" +
                    "    payment.payment_date\n" +
                    "from rental\n" +
                    "join payment on rental.rental_id = payment.rental_id\n" +
                    "join customer on payment.customer_id = customer.customer_id\n" +
                    "where year(payment.payment_date) = ?\n " +
                    "  and month(payment.payment_date) = ?");
            statement.setInt(1, year);
            statement.setInt(2, month);
            ResultSet results = statement.executeQuery();

            ArrayList<SalesDataYearMonth> output = new ArrayList<>();

            while (results.next()) {
                int rentalId = results.getInt(1);
                String firstname = results.getString(2);
                String lastname = results.getString(3);
                BigDecimal amount = results.getBigDecimal(4);
                LocalDateTime time = results.getTimestamp(5).toLocalDateTime();

                SalesDataYearMonth data = new SalesDataYearMonth(rentalId, firstname + " " + lastname, amount, time);

                output.add(data);
            }

            statement.close();
            return output;
        } catch (SQLException e) {
            System.out.println("SQLException=" + e.getMessage());
        }

        return null;
    }

    public List<CustomerRentalFilmData> getRentalByYearMonthEmail(int year, int month, String email) {
        try {
            PreparedStatement statement = connection.prepareStatement("select rental.rental_id, rental.rental_date, film.title \n" +
                    "from customer\n" +
                    "join rental on rental.customer_id = customer.customer_id\n" +
                    "join inventory on inventory.inventory_id = rental.inventory_id\n" +
                    "join film on film.film_id = inventory.film_id\n" +
                    "where year(rental.rental_date) = ?\n" +
                    "  and month(rental.rental_date) = ?\n" +
                    "  and customer.email = ?");
            statement.setInt(1, year);
            statement.setInt(2, month);
            statement.setString(3, email);
            ResultSet results = statement.executeQuery();

            ArrayList<CustomerRentalFilmData> output = new ArrayList<>();

            while (results.next()) {
                int rentalId = results.getInt(1);
                LocalDateTime time = results.getTimestamp(2).toLocalDateTime();
                String title = results.getString(3);
                CustomerRentalFilmData data = new CustomerRentalFilmData(rentalId, time, title);
                output.add(data);
            }

            statement.close();
            return output;
        } catch (SQLException e) {
            System.out.println("SQLException=" + e.getMessage());
        }

        return null;
    }

    public List<CountryCityCustomer> getCountryCityCustomer() {
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("select country.country_id,\n" +
                    "    country.country,\n" +
                    "    city.city_id,\n" +
                    "    city.city,\n" +
                    "    customer.customer_id,\n" +
                    "    customer.email\n" +
                    "from customer\n" +
                    "join address on customer.address_id = address.address_id\n" +
                    "join city on city.city_id = address.city_id\n" +
                    "join country on country.country_id = city.country_id\n");

            ArrayList<CountryCityCustomer> output = new ArrayList<>();

            while (results.next()) {
                int countryId = results.getInt(1);
                String countryName = results.getString(2);
                int cityId = results.getInt(3);
                String cityName = results.getString(4);
                int customerId = results.getInt(5);
                String email = results.getString(6);
                CountryCityCustomer data = new CountryCityCustomer(countryId, countryName, cityId, cityName, customerId, email);
                output.add(data);
            }

            statement.close();
            return output;
        } catch (SQLException e) {
            System.out.println("SQLException=" + e.getMessage());
        }

        return null;
    }

    public List<StoreSalesAmount> getStoreSalesAmount(int year, int month) {
        try {
            PreparedStatement statement = connection.prepareStatement(" select store.store_id,\n" +
                    " sum(payment.amount)\n" +
                    " from store \n" +
                    " join inventory on store.store_id = inventory.store_id\n" +
                    " join rental on rental.inventory_id = inventory.inventory_id\n" +
                    " join payment on rental.rental_id = payment.rental_id\n" +
                    " where year(rental.rental_date) = ?\n" +
                    "  and month(rental.rental_date) = ?\n" +
                    "  group by store.store_id\n");
            statement.setInt(1, year);
            statement.setInt(2, month);
            ResultSet results = statement.executeQuery();

            ArrayList<StoreSalesAmount> output = new ArrayList<>();

            while (results.next()) {
                int storeId = results.getInt(1);
                BigDecimal amount = results.getBigDecimal(2);
                StoreSalesAmount data = new StoreSalesAmount(storeId, amount);
                output.add(data);
            }

            statement.close();
            return output;
        } catch (SQLException e) {
            System.out.println("SQLException=" + e.getMessage());
        }

        return null;
    }

    public List<CustomerRentalFilmData2> getCutomerRentals(int customerId) {
        try {
            PreparedStatement statement = connection.prepareStatement("select rental.rental_id," +
                    " rental.rental_date,\n" +
                    "    rental.return_date,\n" +
                    "    film.title\n" +
                    "from customer \n" +
                    "join rental on customer.customer_id = rental.customer_id\n" +
                    "join inventory on rental.inventory_id = inventory.inventory_id\n" +
                    "join film on inventory.film_id = film.film_id\n" +
                    "where customer.customer_id= ? ");
            statement.setInt(1, customerId);
            ResultSet results = statement.executeQuery();

            ArrayList<CustomerRentalFilmData2> output = new ArrayList<>();

            while (results.next()) {
                int rentalId = results.getInt(1);
                LocalDateTime time = results.getTimestamp(2).toLocalDateTime();
                LocalDateTime returnDate = results.getTimestamp(3).toLocalDateTime();
                String title = results.getString(4);
                CustomerRentalFilmData2 data = new CustomerRentalFilmData2(rentalId, time, returnDate, title);
                output.add(data);
            }

            statement.close();
            return output;
        } catch (SQLException e) {
            System.out.println("SQLException=" + e.getMessage());
        }

        return null;
    }

    public List<StaffData> getStaffRentals(int year, int month)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("select rental.staff_id, staff.first_name, staff.last_name " +
                    " from rental\n" +
                    " join staff on rental.staff_id = staff.staff_id\n" +
                    " where Year(rental.rental_date) = ? " +
                    " and month(rental.rental_date) = ? ");
            statement.setInt(1, year);
            statement.setInt(2, month);
            ResultSet results = statement.executeQuery();

            ArrayList<StaffData> output = new ArrayList<>();

            while (results.next()) {
                int staffId = results.getInt(1);
                String firstname = results.getString(2);
                String lastname = results.getString(3);
                StaffData data = new StaffData(staffId, firstname + " " + lastname);
                output.add(data);
            }

            statement.close();
            return output;
        } catch (SQLException e) {
            System.out.println("SQLException=" + e.getMessage());
        }

        return null;
    }

    public List<ActorData> getActorCount(int year)
    {
        try {
            PreparedStatement statement = connection.prepareStatement("select rental_id, actor.actor_id, actor.first_name, actor.last_name\n" +
                    " from rental \n" +
                    " join inventory on rental.inventory_id = inventory.inventory_id\n" +
                    " join film on inventory.film_id = film.film_id\n" +
                    " join film_actor on film_actor.film_id = film.film_id\n" +
                    " join actor on actor.actor_id = film_actor.actor_id\n" +
                    " where year(rental.rental_date) = ? ");
            statement.setInt(1, year);
            ResultSet results = statement.executeQuery();

            ArrayList<ActorData> output = new ArrayList<>();

            while (results.next()) {
                int rentalId = results.getInt(1);
                int actorId  = results.getInt(2);
                String firstname = results.getString(3);
                String lastname = results.getString(4);
                ActorData d = new ActorData(actorId, firstname + " " + lastname, rentalId);
                output.add(d);
            }

            statement.close();
            return output;
        } catch (SQLException e) {
            System.out.println("SQLException=" + e.getMessage());
        }

        return null;
    }
}

