/*
Bilal Ahmad
Date: March 23, 2020
Purpose: Controller for the GUI
 */
package sample;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

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

    ObservableList<Agent> agentsList = FXCollections.observableArrayList();

    //initializes the GUI
    @FXML
    void initialize() throws SQLException {
        connection = connectToDB();
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

    //connection to db
    private Connection connectToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

        //user credentials
        String USER = "root";
        String PASS = "";

        //driver to connect to the MYSQL database
        try {
            return DriverManager.getConnection(connectionURL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public void listItemClicked(MouseEvent mouseEvent) {
        System.out.println("clicked on " + lvAgents.getSelectionModel().getSelectedItem());
    }
}
