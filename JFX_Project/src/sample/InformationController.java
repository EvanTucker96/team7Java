package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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

/*
BY BRANDON CUTHBERTSON
 */
public class InformationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtPostal;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtHomePhone;

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
    private Button btnAddCustomers;

    @FXML
    private TextField txtBusinessPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtAgentID;

    @FXML
    private Label lblOldID;

    @FXML
    private TextField txtOldID;

    @FXML
    private TextField txtProvince;

    //Variables
    private Customer selectedCustomer;
    private Agent selectedAgent;

    ///VARIABLE FROM OUT OF CONTROLLER
    public void setCustomer(Customer customer ) {
        selectedCustomer = customer;
        Customer sc = selectedCustomer;
        Integer AgentID = selectedCustomer.getAgentId();

        //hide Save and cancel until edit is pushed
          btnSave.setVisible(false);
        btnCancel.setVisible(false);
        //inserting into boxes
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
        if (AgentID != null)
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
        setCustomer(selectedCustomer);
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
    void btnSaveClick(ActionEvent event) {

    }

    @FXML
    void btnUpdateClicked(ActionEvent event) {

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

    }


    //SWITCHES
    private void ToggleSwitch(boolean isNull,boolean notNull)
    {   //Null - True/False
        btnEdit.setVisible(isNull);
        btnBackToList.setVisible(isNull);
        //Not Null -False/true
        lblOldID.setVisible(notNull);
        txtOldID.setVisible(notNull);
        btnUpdate.setVisible(notNull);
        btnBackToList.setVisible(notNull);

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

}
