package pl.haladyj.execute;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.haladyj.model.*;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Address address = new Address();
        address.setCity("Lublin");
        address.setPincode("20-950");
        address.setState("LublinState");
        address.setStreet("Ulica");

        Address address1 = new Address();
        address1.setCity("Lublin1");
        address1.setPincode("20-9501");
        address1.setState("LublinState1");
        address1.setStreet("Ulica1");

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("car");

        FourWeeler car = new FourWeeler();
        car.setVehicleName("fiat");
        car.setSteeringWheel("fiat steering wheel");

        TwoWeeler bike = new TwoWeeler();
        bike.setVehicleName("motorbike");
        bike.setSteeringHandle("motorbike steering handle");

        Building building = new Building();
        building.setName("building");
        Building building1 = new Building();
        building1.setName("building1");

        Job job = new Job();
        job.setJobTitle("clerk");
        Job job2 = new Job();
        job2.setJobTitle("boss");

        UserDetails userDetails = new UserDetails();
        //userDetails.setUserId(1);
        userDetails.setUserName("First User");
        //userDetails.setHomeAddress(address);
        userDetails.setJoinedDate(new Date());
        userDetails.setDescription("description");
        userDetails.getListOfAddresses().add(address);
        userDetails.getListOfAddresses().add(address1);
        userDetails.setVehicle(vehicle);
        userDetails.getBuilding().add(building);
        userDetails.getBuilding().add(building1);
        userDetails.getJobs().add(job);
        userDetails.getJobs().add(job2);
        job.getUsers().add(userDetails);


        UserDetails userDetails2 = new UserDetails();
        //userDetails2.setUserId(1);
        userDetails2.setUserName("Second User");
        //userDetails2.setHomeAddress(address);
        job.getUsers().add(userDetails2);

        userDetails2.setJoinedDate(new Date());
        userDetails2.setDescription("description 2");

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(building);
        session.save(building1);
        session.save(vehicle);
        session.save(userDetails);
        session.save(userDetails2);
        session.save(job);
        session.save(job2);
        session.save(car);
        session.save(bike);
        session.getTransaction().commit();
        session.close();

        userDetails = null;

        session = sessionFactory.openSession();
        session.beginTransaction();
        userDetails = session.get(UserDetails.class, 1);

    }
}
