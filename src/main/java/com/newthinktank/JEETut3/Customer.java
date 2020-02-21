package com.newthinktank.JEETut3;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "custID", unique = true)
    private int id;
    @Column(name = "firstname", nullable = false)
    private String fName;
    @Column(name = "lastname", nullable = false)
    private String lName;
    public int getId() {
        return id;
    }
    public String getfName() {
        return fName;
    }
    public String getlName() {
        return lName;
    }
    public void setfName(String fName) {
        this.fName = fName;
    }
    public void setlName(String lName) {
        this.lName = lName;
    }
    public void setId(int id) {
        this.id = id;
    }
}
