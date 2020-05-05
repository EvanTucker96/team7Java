/*Brandon Cuthbertson
Member of the Team 7, the luckiest of the teams
 */
package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

public class CustomerDetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Customer> lvCustomer;

    @FXML
    private Button btnAddCustomer;

    @FXML
    private Button btnBack;


    ObservableList<Customer> customerList = FXCollections.observableArrayList();
    Connection connection;
    int AgtID; //id the Agent ID will be passed into

    @FXML
    void btnAddAgentClicked(ActionEvent event) {

    }

    @FXML
    void listItemClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert lvCustomer != null : "fx:id=\"lvCustomer\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert btnAddCustomer != null : "fx:id=\"btnAddCustomer\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'CustomerDetails.fxml'.";

        connection = DBConnection.connectToDB();

        try {
            System.out.println("Connected");
            //shows customers
            customerList = GetCustomer();
            System.out.println("agentsList: "+ customerList);
            lvCustomer.getItems().addAll(customerList);
            lvCustomer.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Not Connected");
        }

    }

    private ObservableList<Customer> GetCustomer() throws SQLException {
    //Need to pull in a variable later from agents so it only views there customers

        String selectQuery = "SELECT * from customer where AgentId="+ AgtID;
        PreparedStatement selectCustomers = null;
        Customer customer;
        try {
            selectCustomers = connection.prepareStatement(selectQuery);
            ResultSet rset = selectCustomers.executeQuery(selectQuery);
            while (rset.next()) {
                customer = new Customer(
                        rset.getInt("CustomerId"),
                        rset.getString("CustFirstName"),
                        rset.getString("CustLastName"),
                        rset.getString("CustAddress"),
                        rset.getString("CustCity"),
                        rset.getString("CustProv"),
                        rset.getString("CustPostal"),
                        rset.getString("CustCountry"),
                        rset.getString("CustHomePhone"),
                        rset.getString("CustBusPhone"),
                        rset.getString("CustEmail"),
                        rset.getInt("AgencyId")
                );
                System.out.println(customer);
                customerList.add(customer);
            }
            System.out.println("Agents loaded to the ObservableList");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            selectCustomers.close();
        }

        return customerList;
    }
}






/*No Leprechauns down here */