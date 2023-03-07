package com.gable.runma.model;
import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

/**
 * 
 */
@Data
@Entity
@JsonIdentityInfo(scope = Ticket.class,
generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id")
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
    @JoinColumn(name = "userTicketId")
    private User userID;

    @ManyToOne
    private RaceType raceType;
}