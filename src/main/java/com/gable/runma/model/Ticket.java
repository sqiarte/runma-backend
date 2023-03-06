package com.gable.runma.model;
import java.util.*;

import javax.persistence.*;

import lombok.Data;

/**
 * 
 */
@Data
@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    private Status status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date paidDate;
    private String bankname;
    private Integer amount;
    private String imageProof;
    
    @ManyToOne
    private User userID;

    @ManyToOne
    private RaceType raceType;
    
    

}