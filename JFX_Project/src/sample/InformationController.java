package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

/*
BY BRANDON CUTHBERTSON
 */
public class InformationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    //TEXT FIELDS
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtProvince;
    @FXML
    private TextField txtPostal;
    @FXML
    private TextField txtCountry;
    @FXML
    private TextField txtHomePhone;
    @FXML
    private TextField txtBusinessPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAgentID;
    @FXML
    private TextField txtOldID;
    @FXML
    private TextField txtCustomerID; //always invisable
    //Buttons
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnBackToList;
    @FXML
    private Button btnAddCustomers; //HIDDEN BY DEFAULT -- ONLY UNHIDE IF WE ADD being able to insert a customer
    //Labels
    @FXML
    private Label lblOldID;

    //Variables
    private Customer selectedCustomer;
    private Agent selectedAgent;
    Connection connection = DBConnection.connectToDB();

    ///VARIABLE FROM OUT OF CONTROLLER
    public void getCustomer(Customer customer, boolean isItNull) {
        selectedCustomer = customer;
        Customer sc = selectedCustomer; //REDUNDANT I KNOW BUT IT HELPS ME SEE HOW IT WORKS IN MY HEAD


        //hide Save and cancel until edit is pushed
          btnSave.setVisible(false);
        btnCancel.setVisible(false);
        //inserting into boxes
        txtCustomerID.setText(String.valueOf(sc.getCustomerId()));
        txtFirstName.setText(sc.getCustFirstName());
        txtLastName.setText(sc.getCustLastName());
        txtAddress.setText(sc.getCustAddress());
        txtCity.setText(sc.getCustCity());
        txtPostal.setText(sc.getCustPostal());
        txtCountry.setText(sc.getCustCountry());
        txtHomePhone.setText(sc.getCustHomePhone());
        txtBusinessPhone.setText(sc.getCustBusPhone());
        txtEmail.setText(sc.getCustEmail());
        //button toggle and agent id on off
        if (isItNull == false)
        {
            ToggleSwitch(true,false);
            //sets edit and Back to List Button Visible

        }
        else //IF NULL
        {
            ToggleSwitch(false,true);
            //sets New# update and back to visible;
            txtOldID.setText(String.valueOf(sc.getAgentId()));

        }
        disableAllFields();

    }
    public void AgentID(Agent agent) {
        //sets Agent id based off of selected
        selectedAgent = agent;
        txtAgentID.setText(String.valueOf(selectedAgent.getAgentId()));

    }
    //Buttons
    @FXML
    void btnAddCustomerClicked(ActionEvent event) {
        /*EMPTY UNLESS YOU GUYS WANNA ADD CUSTOMERS - BC*/
    }

    @FXML
    void btnBackToListClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent ListViewParent = loader.load();

        Scene ListViewScene = new Scene(ListViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListViewScene);
        window.show();
    }

    @FXML
    void btnCancelClick(ActionEvent event) {
        whiteOutErrors();
        getCustomer(selectedCustomer, false);
        disableAllFields();
        btnEdit.setVisible(true);
        btnBackToList.setVisible(true);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

    }

    @FXML
    void btnEditClicked(ActionEvent event) {
        setFieldsToEdit();
        btnEdit.setVisible(false);
        btnBackToList.setVisible(false);
        btnSave.setVisible(true);
        btnCancel.setVisible(true);


    }

    @FXML
    void btnSaveClick(ActionEvent event) throws SQLException {// For Customers who are already Assigned a agent
        boolean canUpdate = true;

        if ((Validator.nullTextField(txtFirstName) == true)||(Validator.alphabetOnly(txtFirstName) == false)) {
            txtFirstName.setStyle("-fx-control-inner-background: RED");
            canUpdate = false;
            System.out.println(txtFirstName + " is inValid");
        }
        else { txtLastName.setStyle("-fx-control-inner-background: white"); }

        if ((Validator.nullTextField(txtLastName) == true)||(Validator.alphabetOnly(txtLastName) == false)) {
            txtLastName.setStyle("-fx-control-inner-background: RED");
            canUpdate = false;
            System.out.println(txtLastName + " is inValid");
        }
        else { txtLastName.setStyle("-fx-control-inner-background: white"); }

        if ((Validator.alphaNumeric(txtAddress) == false)){
            txtAddress.setStyle("-fx-control-inner-background: RED");
            canUpdate = false;
            System.out.println(txtAddress + " is inValid");
        }
        else { txtAddress.setStyle("-fx-control-inner-background: white"); }

        if ((Validator.nullTextField(txtCity) == false) && (Validator.alphabetOnly(txtCity) == false)){
            txtCity.setStyle("-fx-control-inner-background: RED");
            canUpdate = false;
            System.out.println(txtCity + " is inValid");
        }
        else { txtCity.setStyle("-fx-control-inner-background: white"); }

        if ((Validator.nullTextField(txtProvince) == false) && (Validator.alphabetOnly(txtProvince) == false)){
            txtProvince.setStyle("-fx-control-inner-background: RED");
            canUpdate = false;
            System.out.println(txtProvince + " is inValid");
        }
        else { txtProvince.setStyle("-fx-control-inner-background: white"); }

        if ((Validator.nullTextField(txtPostal) == false) && (Validator.alphaNumeric(txtPostal) == false)){
            //NOTE COULDNT GET Validator.isPostal(txtPostal) to work
            txtPostal.setStyle("-fx-control-inner-background: RED");
            canUpdate = false;
            System.out.println(txtPostal + " is inValid");
        }
        else { txtPostal.setStyle("-fx-control-inner-background: white"); }

        if ((Validator.nullTextField(txtCountry) == false) && (Validator.alphabetOnly(txtCountry) == false)){
            //NOTE COULDNT GET Validator.isPostal(txtPostal) to work
            txtCountry.setStyle("-fx-control-inner-background: RED");
            canUpdate = false;
            System.out.println(txtCountry + " is inValid");
        }
        else { txtCountry.setStyle("-fx-control-inner-background: white"); }

        if((Validator.nullTextField(txtHomePhone) == false) && (Validator.isPhone(txtHomePhone) == false)){
            txtHomePhone.setStyle("-fx-control-inner-background: RED");
            System.out.println(txtHomePhone + " is inValid");
            canUpdate = false;

        }
        else { txtHomePhone.setStyle("-fx-control-inner-background: white"); }

        if((Validator.nullTextField(txtBusinessPhone) == false) && (Validator.isPhone(txtBusinessPhone) == false)){
            txtBusinessPhone.setStyle("-fx-control-inner-background: RED");
            System.out.println(txtBusinessPhone + " is inValid");
            canUpdate = false;

        }
        else { txtBusinessPhone.setStyle("-fx-control-inner-background: white"); }

        if((Validator.isEmail(txtEmail) == false) || (Validator.nullTextField(txtEmail) == true)) {
            txtEmail.setStyle("-fx-control-inner-background: RED");
            System.out.println(txtEmail + " is inValid");
            canUpdate = false;

        }
        else { txtEmail.setStyle("-fx-control-inner-background: white"); }

        if (canUpdate == true )
        {
            UpdateCustomer();
        }
        else{ System.out.println("===========\nNOT UPDATED\n===========\n"); }
    }

    @FXML
    void btnUpdateClicked(ActionEvent event) throws SQLException { //for Customers who arent assigned an agent
        int p = JOptionPane.showConfirmDialog(null,"Are You Sure you want to add this Customer?",
                "Confirm",JOptionPane.YES_NO_OPTION);
        if (p == 0)//yes
        {
            UpdateCustomer();
            getCustomer(selectedCustomer, false);
        }
    }

    @FXML
    void initialize() {
        assert txtFirstName != null : "fx:id=\"txtFirstName\" was not injected: check your FXML file 'Information.fxml'.";
        assert txtLastName != null : "fx:id=\"txtLastName\" was not injected: check your FXML file 'Information.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'Information.fxml'.";
        assert txtCity != null : "fx:id=\"txtCity\" was not injected: check your FXML file 'Information.fxml'.";
        assert txtPostal != null : "fx:id=\"txtPostal\" was not injected: check your FXML file 'Information.fxml'.";
        assert txtCountry != null : "fx:id=\"txtCountry\" was not injected: check your FXML file 'Information.fxml'.";
        assert txtHomePhone != null : "fx:id=\"txtHomePhone\" was not injected: check your FXML file 'Information.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'Information.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Information.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'Information.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'Information.fxml'.";
        assert btnBackToList != null : "fx:id=\"btnBackToList\" was not injected: check your FXML file 'Information.fxml'.";
        assert btnAddCustomers != null : "fx:id=\"btnAddCustomers\" was not injected: check your FXML file 'Information.fxml'.";
        assert txtBusinessPhone != null : "fx:id=\"txtBusinessPhone\" was not injected: check your FXML file 'Information.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'Information.fxml'.";
        assert txtAgentID != null : "fx:id=\"txtAgentID\" was not injected: check your FXML file 'Information.fxml'.";
        assert lblOldID != null : "fx:id=\"lblOldID\" was not injected: check your FXML file 'Information.fxml'.";
        assert txtOldID != null : "fx:id=\"txtOldID\" was not injected: check your FXML file 'Information.fxml'.";
        assert txtProvince !=null : "fx:id\"txtProvince\"was not injected: check your FXML file 'Information.fxml'.";
        assert txtCustomerID !=null : "fx:id\"txtCustomerID\"was not injected: check your FXML file 'Information.fxml'.";

    }


    //SWITCHES
    private void ToggleSwitch(boolean isNull,boolean notNull)
    {   //Null - True/False
        btnEdit.setVisible(isNull);
        /////btnBackToList.setVisible(isNull);
        //Not Null -False/true
        lblOldID.setVisible(notNull);
        txtOldID.setVisible(notNull);
        btnUpdate.setVisible(notNull);
        /////btnBackToList.setVisible(notNull);

    }
    private void disableAllFields() {
        txtFirstName.setDisable(true);
        txtLastName.setDisable(true);
        txtAddress.setDisable(true);
        txtCity.setDisable(true);
        txtProvince.setDisable(true);
        txtPostal.setDisable(true);
        txtCountry.setDisable(true);
        txtHomePhone.setDisable(true);
        txtBusinessPhone.setDisable(true);
        txtEmail.setDisable(true);
        txtAgentID.setDisable(true);
        txtOldID.setDisable(true);
    }
    private void setFieldsToEdit(){
        txtFirstName.setDisable(false);
        txtLastName.setDisable(false);
        txtAddress.setDisable(false);
        txtCity.setDisable(false);
        txtProvince.setDisable(false);
        txtPostal.setDisable(false);
        txtCountry.setDisable(false);
        txtHomePhone.setDisable(false);
        txtBusinessPhone.setDisable(false);
        txtEmail.setDisable(false);
    }
    private void UpdateCustomer() throws SQLException {
        PreparedStatement insertData = null;
        ResultSet resultSet = null;
        try {
            String insertQuery = "UPDATE customers SET CustomerId=?,CustFirstName=?,CustLastName=?," +
                    "CustAddress=?,CustCity=?,CustProv=?,CustPostal=?,CustCountry=?," +
                    "CustHomePhone=?,CustBusPhone=?,CustEmail=?,AgentId=? " +
                    "WHERE CustomerId = " + txtCustomerID.getText();

            insertData = connection.prepareStatement(insertQuery);

            insertData.setInt(1, Integer.parseInt(txtCustomerID.getText()));
            insertData.setString(2, txtFirstName.getText());
            insertData.setString(3, txtLastName.getText());
            insertData.setString(4, txtAddress.getText());
            insertData.setString(5, txtCity.getText());
            insertData.setString(6, txtProvince.getText());
            insertData.setString(7, txtPostal.getText());
            insertData.setString(8, txtCountry.getText());
            insertData.setString(9, txtHomePhone.getText());
            insertData.setString(10, txtBusinessPhone.getText());
            insertData.setString(11, txtEmail.getText());
            insertData.setInt(12, Integer.parseInt(txtAgentID.getText()));

            insertData.executeUpdate();
            System.out.println("Record Inserted for Customer: " + txtFirstName.getText() + " " + txtLastName.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            insertData.close();
        }
        //at end disable all fields
        disableAllFields();
        btnEdit.setVisible(true);
        btnBackToList.setVisible(true);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

    }

    private void whiteOutErrors() {
        txtFirstName.setStyle("-fx-control-inner-background: white");
        txtLastName.setStyle("-fx-control-inner-background: white");
        txtAddress.setStyle("-fx-control-inner-background: white");
        txtCity.setStyle("-fx-control-inner-background: white");
        txtProvince.setStyle("-fx-control-inner-background: white");
        txtPostal.setStyle("-fx-control-inner-background: white");
        txtCountry.setStyle("-fx-control-inner-background: white");
        txtHomePhone.setStyle("-fx-control-inner-background: white");
        txtBusinessPhone.setStyle("-fx-control-inner-background: white");
        txtEmail.setStyle("-fx-control-inner-background: white");


    }

}





//NO SECRETS DOWN HERE