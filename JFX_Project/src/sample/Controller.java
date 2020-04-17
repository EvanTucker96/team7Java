/*
Bilal Ahmad
Date: April 17, 2020
Purpose: Controller for the GUI
 */
package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller {

    //connection string for the database
    String connectionURL = "jdbc:mysql://localhost:3306/travelexperts";
    Connection connection;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Agent> lvAgents;


    @FXML
    private Button txtAddAgent;

    ObservableList<Agent> agentsList = FXCollections.observableArrayList();




    //initializes the GUI
    @FXML
    void initialize() throws SQLException {
        connection = DBConnection.connectToDB();
        if (connection != null) {
            System.out.println("Connection Established");

            //shows the agents in the listview
            agentsList = GetAgents();
            System.out.println("agentsList: "+ agentsList);
            lvAgents.getItems().addAll(agentsList);
            lvAgents.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        }else{
            System.out.println("Connection not established");
        }
    }

    //returns a list of agents
    private ObservableList<Agent> GetAgents() throws SQLException{
        String selectQuery = "SELECT * from agents";
        PreparedStatement selectAgents = null;
        Agent agent;
        try {
            selectAgents = connection.prepareStatement(selectQuery);
            ResultSet rset = selectAgents.executeQuery(selectQuery);
            while (rset.next()) {
                agent = new Agent(
                        rset.getInt("AgentId"),
                        rset.getString("AgtFirstName"),
                        rset.getString("AgtMiddleInitial"),
                        rset.getString("AgtLastName"),
                        rset.getString("AgtBusPhone"),
                        rset.getString("AgtEmail"),
                        rset.getString("AgtPosition"),
                        rset.getInt("AgencyId")
                );
                System.out.println(agent);
                agentsList.add(agent);
            }
            System.out.println("Agents loaded to the ObservableList");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            selectAgents.close();
        }

        return agentsList;
    }



    public void listItemClicked(MouseEvent event) throws IOException {
        System.out.println("clicked on " + lvAgents.getSelectionModel().getSelectedItem());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AgentsPopUp.fxml"));
        Parent ListViewParent = loader.load();

        Scene ListViewScene = new Scene(ListViewParent);

        AgentsPopUpController controller = loader.getController();
        controller.setAgent(lvAgents.getSelectionModel().getSelectedItem());

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListViewScene);
        window.show();



    }

    public void btnAddAgentClicked(ActionEvent event) throws IOException {
        System.out.println("clicked on Add Agent Button");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AgentsDetail.fxml"));
        Parent ListViewParent = loader.load();

        Scene ListViewScene = new Scene(ListViewParent);

        AgentsDetailController controller = loader.getController();
        controller.insertMode();
        controller.mode = "insert";

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListViewScene);
        window.show();
        /*Parent stage = FXMLLoader.load(getClass().getResource("AgentsDetail.fxml"));

        Scene scene = new Scene(stage);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);*/
    }
}
