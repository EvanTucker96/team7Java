/*
Bilal Ahmad
Date: April 17, 2020
Purpose: popup when agent is clicked, for options
 */
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.*;

public class AgentsPopUpController {

    Connection connection = DBConnection.connectToDB();
    private Agent selectedAgent;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnUpdateAgent;

    @FXML
    private Button btnDeleteAgent;

    @FXML
    private Button btnViewCustomers;

    @FXML
    private Button btnBackToList;
	
    @FXML
    private  Button btnAddCustomers;

    @FXML
    Agent setAgent (Agent agent){
        selectedAgent = agent;
        return selectedAgent;
    }

    @FXML
    void btnDeleteAgentClicked(ActionEvent event) throws SQLException {

        //Are you sure added by Brandon

        int p = JOptionPane.showConfirmDialog(null,"Are You Sure you want to Delete this Agent?",
                "Delete",JOptionPane.YES_NO_OPTION);


        if (p == 0)//yes
        {   ConvertCustomersToNull();
            DeleteAgent();
            setPopupButtonsDisabled(true);


        }
        else if (p == 1) {
            setPopupButtonsDisabled(false);
        }
    }

    private void setPopupButtonsDisabled(boolean toggleOnOFF) {
        //added by brandon
        btnDeleteAgent.setDisable(toggleOnOFF);
        btnUpdateAgent.setDisable(toggleOnOFF);
        btnViewCustomers.setDisable(toggleOnOFF);
        btnAddCustomers.setDisable(toggleOnOFF);
    }

    public void DeleteAgent() throws SQLException {
        //By Bilal

        PreparedStatement deleteData = null;
        ResultSet resultSet = null;
        try {
            String deleteQuery = "DELETE FROM agents where AgentId = ? and AgtFirstName =? and AgtMiddleInitial =? and AgtLastName =?";

            deleteData = connection.prepareStatement(deleteQuery);

            deleteData.setInt(1, selectedAgent.getAgentId());
            deleteData.setString(2, selectedAgent.getAgtFirstName());
            deleteData.setString(3, selectedAgent.getAgtMiddleInitial());
            deleteData.setString(4, selectedAgent.getAgtLastName());
            deleteData.executeUpdate();
            System.out.println("Record Deleted for Agent: (ID: " + selectedAgent.getAgentId() + ") " + selectedAgent.getAgtFirstName() + " " + selectedAgent.getAgtLastName());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            deleteData.close();
        }

    }
    private void ConvertCustomersToNull() throws SQLException {
        //BY BRANDON CUTHBERTSON MAKES IT SO THE CUSTOMERS DONT GET LOST
        PreparedStatement NullData = null;
        ResultSet resultSet = null;
        try {
            String insertQuery = "UPDATE customers SET AgentId=NULL " +
                    "WHERE AgentId = " + selectedAgent.getAgentId();
            NullData = connection.prepareStatement(insertQuery);
            //NullData.setInt(1, Types.NULL);
            NullData.executeUpdate();
            System.out.println(selectedAgent.getAgtFirstName() + "" + selectedAgent.getAgtLastName() + "'s: Customers Have been made null ");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            NullData.close();
        }
    }



    @FXML
    void btnUpdateAgentClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AgentsDetail.fxml"));
        Parent ListViewParent = loader.load();

        Scene ListViewScene = new Scene(ListViewParent);

        AgentsDetailController controller = loader.getController();
        controller.setDataIntoFields(selectedAgent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListViewScene);
        window.show();

    }

    @FXML
    void btnViewCustomersClicked(ActionEvent event) throws IOException {
        //added by Brandon Cuthbertson
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerDetails.fxml"));
        Parent ListViewParent = loader.load();

        Scene ListViewScene = new Scene(ListViewParent);

        CustomerDetailsController controller = loader.getController();
        controller.setDataIntoFields(selectedAgent);
        controller.setAgents(selectedAgent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListViewScene);
        window.show();
    }
    @FXML
    void btnAddCustomersClicked(ActionEvent event) throws IOException {
        //added by Brandon Cuthbertson
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NullCustomers.fxml"));
        Parent ListViewParent = loader.load();

        Scene ListViewScene = new Scene(ListViewParent);

        NullCustomersController controller = loader.getController();
        controller.setAgent(selectedAgent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListViewScene);
        window.show();

    }

    @FXML
    void initialize() {
        btnDeleteAgent.setStyle("-fx-background-color: #FF0000");
        btnDeleteAgent.setTextFill(Color.WHITE);


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

}
