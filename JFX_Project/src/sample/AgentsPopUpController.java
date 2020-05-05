/*
Bilal Ahmad
Date: April 17, 2020
Purpose: popup when agent is clicked, for options
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    Agent setAgent (Agent agent){
        selectedAgent = agent;
        return selectedAgent;
    }

    @FXML
    void btnDeleteAgentClicked(ActionEvent event) throws SQLException {
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerDetails.fxml"));
        Parent ListViewParent = loader.load();

        Scene ListViewScene = new Scene(ListViewParent);
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
