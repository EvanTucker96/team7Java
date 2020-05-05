package sample;
/*
Brandon Cuthbertson
Customer Class
 */
public class Customer {
    private int CustomerId;
    private String CustFirstName;
    private String CustLastName;
    private String CustAddress;
    private String CustCity;
    private String CustProv;
    private String CustPostal;
    private String CustCountry;
    private String CustHomePhone;
    private String CustBusPhone;
    private String CustEmail;
    private int AgentId;


    public Customer(int customerId, String custFirstName, String custLastName, String custAddress, String custCity,
                    String custProv, String custPostal, String custCountry, String custHomePhone, String custBusPhone,
                    String custEmail, int agentId) {
        CustomerId = customerId;
        CustFirstName = custFirstName;
        CustLastName = custLastName;
        CustAddress = custAddress;
        CustCity = custCity;
        CustProv = custProv;
        CustPostal = custPostal;
        CustCountry = custCountry;
        CustHomePhone = custHomePhone;
        CustBusPhone = custBusPhone;
        CustEmail = custEmail;
        AgentId = agentId;
    }
    @Override
    public String toString() {
        return CustFirstName + " "+ CustLastName;
    }
/* GETTERS */
    public int getCustomerId() {return CustomerId;}

    public String getCustFirstName() {return CustFirstName;}

    public String getCustLastName() {return CustLastName;}

    public String getCustAddress() {return CustAddress;}

    public String getCustCity() {return CustCity;}

    public String getCustProv() {return CustProv;}

    public String getCustPostal() {return CustPostal;}

    public String getCustCountry() {return CustCountry;}

    public String getCustHomePhone() {return CustHomePhone;}

    public String getCustBusPhone() {return CustBusPhone;}

    public String getCustEmail() {return CustEmail;}

    public int getAgentId() {return AgentId;}
/* SETTERS */

    public void setCustomerId(int customerId) {CustomerId = customerId;}

    public void setCustFirstName(String custFirstName) {CustFirstName = custFirstName;}

    public void setCustLastName(String custLastName) {CustLastName = custLastName;}

    public void setCustAddress(String custAddress) {CustAddress = custAddress;}

    public void setCustCity(String custCity) {CustCity = custCity;}

    public void setCustProv(String custProv) {CustProv = custProv;}

    public void setCustPostal(String custPostal) {CustPostal = custPostal;}

    public void setCustCountry(String custCountry) {CustCountry = custCountry;}

    public void setCustHomePhone(String custHomePhone) {CustHomePhone = custHomePhone;}

    public void setCustBusPhone(String custBusPhone) {CustBusPhone = custBusPhone;}

    public void setCustEmail(String custEmail) {CustEmail = custEmail;}

    public void setAgentId(int agentId) {AgentId = agentId;}

}