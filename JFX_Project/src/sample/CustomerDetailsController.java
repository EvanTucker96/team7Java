/*Brandon Cuthbertson
Member of the Team 7, the luckiest of the teams
 */
package sample;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerDetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Customer> lvCustomer;
    @FXML
    private Text lblName;



    @FXML
    private Button btnBack;


    ObservableList<Customer> customerList = FXCollections.observableArrayList();
    Connection connection;
    private Agent selectedAgent;
    private Integer agentId;


    public void setDataIntoFields(Agent agent) {
            selectedAgent = agent;
            agentId = selectedAgent.getAgentId();
            lblName.setText(selectedAgent.toString() + "'s Customers");

        }


    @FXML
    void btnBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent ListViewParent = loader.load();

        Scene ListViewScene = new Scene(ListViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListViewScene);
        window.show();
    }


    @FXML
    void initialize() {
        assert lvCustomer != null : "fx:id=\"lvCustomer\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert lblName != null : "fx:id=\"lblName\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
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
            String selectQuery;
        if (agentId != null)
        {
             selectQuery = "SELECT * from customers where AgentId = " + agentId;

        }
        else {
             selectQuery = "Select * from customers";
        }
            PreparedStatement selectCustomers = null;
            Customer customer;
        try {
            selectCustomers = connection.prepareStatement(selectQuery);
            ResultSet rs = selectCustomers.executeQuery(selectQuery);
            while (rs.next()) {
                customer = new Customer(
                        rs.getInt("CustomerId"),
                        rs.getString("CustFirstName"),
                        rs.getString("CustLastName"),
                        rs.getString("CustAddress"),
                        rs.getString("CustCity"),
                        rs.getString("CustProv"),
                        rs.getString("CustPostal"),
                        rs.getString("CustCountry"),
                        rs.getString("CustHomePhone"),
                        rs.getString("CustBusPhone"),
                        rs.getString("CustEmail"),
                        rs.getInt("AgentId")
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