package pl.haladyj.model;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_name")
    //@Basic
    //@Transient
    private String userName;
    @Temporal(TemporalType.TIME)
    private Date joinedDate;

    public void setListOfAddresses(Collection<Address> listOfAddresses) {
        this.listOfAddresses = listOfAddresses;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name="USER_ADDRESS", joinColumns = @JoinColumn(name="user_id"))
    @GenericGenerator(name="sequencegen",strategy="sequence")
    @CollectionId(columns = { @Column(name = "address_id") }, type = @Type(type = "long"), generator = "sequencegen")
    private Collection<Address> listOfAddresses = new ArrayList<>();


    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private  Vehicle vehicle;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_building", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name = "building_id"))
    private Collection<Building> buildings = new ArrayList<Building>();

    @ManyToMany
    private Collection<Job> jobs = new ArrayList<>();

    public Collection<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Collection<Building> buildings) {
        this.buildings = buildings;
    }

    public Collection<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Collection<Job> jobs) {
        this.jobs = jobs;
    }

    public Collection<Building> getBuilding() {
        return buildings;
    }

    public void setBuilding(Collection<Building> buildings) {
        this.buildings = buildings;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Collection<Address> getListOfAddresses() {
        return listOfAddresses;
    }

    /*
                @Embedded
                @AttributeOverrides({
                        @AttributeOverride(name = "street", column = @Column(name="home_street_name")),
                        @AttributeOverride(name = "city", column = @Column(name="home_city_name")),
                        @AttributeOverride(name = "state", column = @Column(name="home_state_name")),
                        @AttributeOverride(name = "pincode", column = @Column(name="home_pincode_name"))
                })
                private Address homeAddress;
                @Embedded
                @AttributeOverrides({
                        @AttributeOverride(name = "street", column = @Column(name="office_street_name")),
                        @AttributeOverride(name = "city", column = @Column(name="office_city_name")),
                        @AttributeOverride(name = "state", column = @Column(name="office_state_name")),
                        @AttributeOverride(name = "pincode", column = @Column(name="office_pincode_name"))
                })
                private Address officeAddress;


                 */
    @Lob
    private String description;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    /*
    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }


     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
