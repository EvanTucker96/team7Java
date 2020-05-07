package com.oosd.team7agentsapp;

import org.json.JSONObject;

import java.io.Serializable;

/*
Generated class from the database.
Evan Tucker
OOSD Fall track
May 2020
 */


public class Agents implements Serializable {

    private Integer AgentId;
    private String AgtFirstName;
    private String AgtMiddleInitial;
    private String AgtLastName;
    private String AgtBusPhone;
    private String AgtEmail;
    private String AgtPosition;
    private Integer AgencyId;

    public Agents(Integer agentId, String agtFirstName, String actMiddleInitial, String agtLastName, String agtBusPhone, String agtEmail, String agtPosition, Integer agencyId) {
        AgentId = agentId;
        AgtFirstName = agtFirstName;
        AgtMiddleInitial = actMiddleInitial;
        AgtLastName = agtLastName;
        AgtBusPhone = agtBusPhone;
        AgtEmail = agtEmail;
        AgtPosition = agtPosition;
        AgencyId = agencyId;
    }

    public Agents() {

    }


    public Integer getAgentId() {
        return AgentId;
    }

    public void setAgentId(Integer agentId) {
        AgentId = agentId;
    }

    public String getAgtFirstName() {
        return AgtFirstName;
    }

    public void setAgtFirstName(String agtFirstName) {
        AgtFirstName = agtFirstName;
    }

    public String getAgtMiddleInitial() {
        return AgtMiddleInitial;
    }

    public void setAgtMiddleInitial(String actMiddleInitial) {
        AgtMiddleInitial = actMiddleInitial;
    }

    public String getAgtLastName() {
        return AgtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        AgtLastName = agtLastName;
    }

    public String getAgtBusPhone() {
        return AgtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        AgtBusPhone = agtBusPhone;
    }

    public String getAgtEmail() {
        return AgtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        AgtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return AgtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        AgtPosition = agtPosition;
    }

    public Integer getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(Integer agencyId) {
        AgencyId = agencyId;
    }

    @Override
    public String toString() {
        return AgentId + ", " + AgtFirstName + " " + AgtLastName;
    }
}
