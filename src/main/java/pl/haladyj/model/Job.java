package pl.haladyj.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String jobTitle;
    @ManyToMany(mappedBy = "jobs")
    private Collection<UserDetails> users=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Collection<UserDetails> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserDetails> users) {
        this.users = users;
    }
}
