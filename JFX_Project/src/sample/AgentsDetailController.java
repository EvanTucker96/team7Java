/*
Bilal Ahmad
Date: April 17, 2020
Purpose: Details to be modified or used for inserting a new agent
 */
package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class AgentsDetailController {


    public String mode = "update";
    Connection connection = DBConnection.connectToDB();
    private Agent selectedAgent;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAgentId;

    @FXML
    private TextField txtAgentFirstName;

    @FXML
    private TextField txtAgentMiddleInitial;

    @FXML
    private TextField txtAgentLastName;

    @FXML
    private TextField txtAgentBusinessPhone;

    @FXML
    private TextField txtAgentEmail;

    @FXML
    private TextField txtAgentPosition;

    @FXML
    private TextField txtAgentAgencyId;

    @FXML
    private Button btnAgentSave;

    @FXML
    private Button btnAgentEdit;

    @FXML
    private Button btnAgentAdd;

    @FXML
    private Button btnAgentCancel;

    @FXML
    private Button btnBackToList;

    @FXML
    void initialize() {
        if(mode =="update"){
            btnAgentAdd.setVisible(false);
        }


    }

    void setDataIntoFields(Agent agent) {
        selectedAgent = agent;
        txtAgentId.setText(String.valueOf(selectedAgent.getAgentId()));
        txtAgentFirstName.setText(selectedAgent.getAgtFirstName());
        txtAgentMiddleInitial.setText(selectedAgent.getAgtMiddleInitial());
        txtAgentLastName.setText(selectedAgent.getAgtLastName());
        txtAgentBusinessPhone.setText(selectedAgent.getAgtBusPhone());
        txtAgentEmail.setText(selectedAgent.getAgtEmail());
        txtAgentPosition.setText(selectedAgent.getAgtPosition());
        txtAgentAgencyId.setText(String.valueOf(selectedAgent.getAgencyId()));
        disableAllFields();
    }

    public void btnAgentSaveClicked(ActionEvent actionEvent) throws SQLException {

            boolean canUpdate = true;

            if ((Validator.nullTextField(txtAgentFirstName) == true)||(Validator.alphabetOnly(txtAgentFirstName) == false)) {
                txtAgentFirstName.setStyle("-fx-control-inner-background: RED");
                canUpdate = false;
                System.out.println(txtAgentFirstName.getText() + " is inValid");

            }
            else { txtAgentFirstName.setStyle("-fx-control-inner-background: white"); }

            if ((Validator.nullTextField(txtAgentMiddleInitial) == false) && (Validator.alphabetOnly(txtAgentMiddleInitial) == false)){
                txtAgentMiddleInitial.setStyle("-fx-control-inner-background: RED");
                System.out.println(txtAgentLastName.getText() + " is inValid");
                canUpdate = false;


            }
            else { txtAgentMiddleInitial.setStyle("-fx-control-inner-background: white"); }

            if ((Validator.nullTextField(txtAgentLastName) == true)||(Validator.alphabetOnly(txtAgentLastName) == false)) {
                txtAgentLastName.setStyle("-fx-control-inner-background: RED");
                System.out.println(txtAgentLastName.getText() + " is inValid");


            }
            else { txtAgentLastName.setStyle("-fx-control-inner-background: white"); }

            if((Validator.nullTextField(txtAgentBusinessPhone) == true) || (Validator.isPhone(txtAgentBusinessPhone) == false)){
                txtAgentBusinessPhone.setStyle("-fx-control-inner-background: RED");
                System.out.println(txtAgentBusinessPhone.getText() + " is inValid");
                canUpdate = false;

            }
            else { txtAgentBusinessPhone.setStyle("-fx-control-inner-background: white"); }

            if((Validator.isEmail(txtAgentEmail) == false) || (Validator.nullTextField(txtAgentEmail) == true)) {
                txtAgentEmail.setStyle("-fx-control-inner-background: RED");
                System.out.println(txtAgentEmail.getText() + " is inValid");
                canUpdate = false;

            }
            else { txtAgentEmail.setStyle("-fx-control-inner-background: white"); }

            if ((Validator.alphaNumeric(txtAgentPosition) == false)){
                txtAgentPosition.setStyle("-fx-control-inner-background: RED");
                System.out.println(txtAgentPosition.getText() + " is inValid");
                canUpdate = false;
            }
            else { txtAgentPosition.setStyle("-fx-control-inner-background: white"); }

            if ((Validator.nullTextField(txtAgentAgencyId) == true)||(Validator.numbersOnly(txtAgentAgencyId) == false)){
                txtAgentAgencyId.setStyle("-fx-control-inner-background: RED");
                System.out.println(txtAgentAgencyId.getText() + " is inValid");
                canUpdate = false;
                ;
            }
            else { txtAgentAgencyId.setStyle("-fx-control-inner-background: white"); }



            if (canUpdate == true)//updates
            {
                disableAllFields();
                PreparedStatement saveData = null;
                ResultSet resultSet = null;
                try {
                    String UpdateQuery = "UPDATE Agents set " +
                            "AgtFirstName = ?," +
                            "AgtMiddleInitial = ?," +
                            "AgtLastName = ?," +
                            "AgtBusPhone = ?," +
                            "AgtEmail = ?," +
                            "AgtPosition = ?," +
                            "AgencyId = ? " +
                            "where AgentId = ?";

                    saveData = connection.prepareStatement(UpdateQuery);

                    saveData.setString(1, txtAgentFirstName.getText());
                    saveData.setString(2, txtAgentMiddleInitial.getText());
                    saveData.setString(3, txtAgentLastName.getText());
                    saveData.setString(4, txtAgentBusinessPhone.getText());
                    saveData.setString(5, txtAgentEmail.getText());
                    saveData.setString(6, txtAgentPosition.getText());
                    saveData.setInt(7, Integer.parseInt(txtAgentAgencyId.getText()));
                    saveData.setInt(8, Integer.parseInt(txtAgentId.getText()));

                    saveData.executeUpdate();
                    System.out.println("Record Updated for Agent: (ID: " + Integer.parseInt(txtAgentId.getText()) + ") " + txtAgentFirstName.getText() + " " + txtAgentLastName.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    saveData.close();
                }

            }
            else{
                System.out.println("===========\nNOT UPDATED\n===========\n");

            }

    }
    private void Update() throws SQLException {

    }
    private void whiteOurTextBoxes()
    {
        txtAgentFirstName.setStyle("-fx-control-inner-background: White");
        txtAgentMiddleInitial.setStyle("-fx-control-inner-background: White");
        txtAgentLastName.setStyle("-fx-control-inner-background: White");
        txtAgentBusinessPhone.setStyle("-fx-control-inner-background: White");
        txtAgentEmail.setStyle("-fx-control-inner-background: White");
        txtAgentPosition.setStyle("-fx-control-inner-background: White");
        txtAgentAgencyId.setStyle("-fx-control-inner-background: White");

    }

    //disables all fields
    private void disableAllFields() {

        txtAgentId.setDisable(true);
        txtAgentFirstName.setDisable(true);
        txtAgentMiddleInitial.setDisable(true);
        txtAgentLastName.setDisable(true);
        txtAgentBusinessPhone.setDisable(true);
        txtAgentEmail.setDisable(true);
        txtAgentPosition.setDisable(true);
        txtAgentAgencyId.setDisable(true);
        btnAgentSave.setVisible(false);
        btnAgentCancel.setVisible(false);
        btnAgentEdit.setVisible(true);


    }

    //enables all fields
    private void enableAllFields() {
        txtAgentId.setDisable(true);
        txtAgentFirstName.setDisable(false);
        txtAgentMiddleInitial.setDisable(false);
        txtAgentLastName.setDisable(false);
        txtAgentBusinessPhone.setDisable(false);
        txtAgentEmail.setDisable(false);
        txtAgentPosition.setDisable(false);
        txtAgentAgencyId.setDisable(false);
        btnAgentSave.setVisible(true);
        btnAgentCancel.setVisible(true);
        btnAgentEdit.setVisible(false);
    }

    void insertMode() {
        txtAgentId.setDisable(true);
        btnAgentAdd.setVisible(true);
        btnAgentSave.setVisible(false);
        btnAgentEdit.setVisible(false);

    }

    public void btnAgentCancelClicked(ActionEvent actionEvent) throws IOException {
        if (mode == "update") {
            disableAllFields();
            setDataIntoFields(selectedAgent);
        } else {
            btnBackToListClicked(actionEvent);
        }

        whiteOurTextBoxes();


    }

    public void btnAgentEditClicked(ActionEvent actionEvent) {
        enableAllFields();
    }



    public void btnBackToListClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent ListViewParent = loader.load();

        Scene ListViewScene = new Scene(ListViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListViewScene);
        window.show();
    }

    public void btnAgentAddClicked(ActionEvent event) throws SQLException {

        boolean canUpdate = true;

        if ((Validator.nullTextField(txtAgentFirstName) == true)||(Validator.alphabetOnly(txtAgentFirstName) == false)) {
            txtAgentFirstName.setStyle("-fx-control-inner-background: RED");
            canUpdate = false;
            System.out.println(txtAgentFirstName + " is inValid");
        }
        else { txtAgentFirstName.setStyle("-fx-control-inner-background: white"); }

        if ((Validator.nullTextField(txtAgentMiddleInitial) == false) && (Validator.alphabetOnly(txtAgentMiddleInitial) == false)){
            txtAgentMiddleInitial.setStyle("-fx-control-inner-background: RED");
            System.out.println(txtAgentLastName+ " is inValid");
            canUpdate = false;
        }
        else { txtAgentMiddleInitial.setStyle("-fx-control-inner-background: white"); }

        if ((Validator.nullTextField(txtAgentLastName) == true)||(Validator.alphabetOnly(txtAgentLastName) == false)) {
            txtAgentLastName.setStyle("-fx-control-inner-background: RED");
            System.out.println(txtAgentLastName + " is inValid");
        }
        else { txtAgentLastName.setStyle("-fx-control-inner-background: white"); }

        if((Validator.nullTextField(txtAgentBusinessPhone) == true) || (Validator.isPhone(txtAgentBusinessPhone) == false)){
            txtAgentBusinessPhone.setStyle("-fx-control-inner-background: RED");
            System.out.println(txtAgentBusinessPhone + " is inValid");
            canUpdate = false;

        }
        else { txtAgentBusinessPhone.setStyle("-fx-control-inner-background: white"); }

        if((Validator.isEmail(txtAgentEmail) == false) || (Validator.nullTextField(txtAgentEmail) == true)) {
            txtAgentEmail.setStyle("-fx-control-inner-background: RED");
            System.out.println(txtAgentEmail + " is inValid");
            canUpdate = false;

        }
        else { txtAgentEmail.setStyle("-fx-control-inner-background: white"); }

        if ((Validator.alphaNumeric(txtAgentPosition) == false)){
            txtAgentPosition.setStyle("-fx-control-inner-background: RED");
            System.out.println(txtAgentPosition + " is inValid");
            canUpdate = false;
        }
        else { txtAgentPosition.setStyle("-fx-control-inner-background: white"); }

        if ((Validator.nullTextField(txtAgentAgencyId) == true)||(Validator.numbersOnly(txtAgentAgencyId) == false)){
            txtAgentAgencyId.setStyle("-fx-control-inner-background: RED");
            System.out.println(txtAgentAgencyId + " is inValid");
            canUpdate = false;
            ;
        }
        else { txtAgentAgencyId.setStyle("-fx-control-inner-background: white"); }



        if (canUpdate == true)//updates
        {
            PreparedStatement insertData = null;

            ResultSet resultSet = null;
            try {
                String insertQuery = "INSERT INTO agents(AgtFirstName,AgtMiddleInitial,AgtLastName,AgtBusPhone,AgtEmail,AgtPosition,AgencyId) " +
                        "VALUES(?,?,?,?,?,?,?)";

                insertData = connection.prepareStatement(insertQuery);

                insertData.setString(1, txtAgentFirstName.getText());
                insertData.setString(2, txtAgentMiddleInitial.getText());
                insertData.setString(3, txtAgentLastName.getText());
                insertData.setString(4, txtAgentBusinessPhone.getText());
                insertData.setString(5, txtAgentEmail.getText());
                insertData.setString(6, txtAgentPosition.getText());
                insertData.setInt(7, Integer.parseInt(txtAgentAgencyId.getText()));

                insertData.executeUpdate();
                System.out.println("Record Inserted for Agent: " + txtAgentFirstName.getText() + " " + txtAgentLastName.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                insertData.close();
            }
        }
        else{ System.out.println("===========\nNOT UPDATED\n===========\n"); }

    }
}
