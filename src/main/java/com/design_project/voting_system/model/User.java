package com.design_project.voting_system.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {

    @Id
    private String id;
    private String fullName;
    private String address;
    private String nicNumber;
    private String ele_vol;
    private String ele_district;
    private String fingerPrint;
    private boolean hasVoted;

    public User(){

    }


}
