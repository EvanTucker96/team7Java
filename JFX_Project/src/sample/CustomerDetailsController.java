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
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CustomerDetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Customer> lvCustomer;


    @FXML
    private Text lbName;


    @FXML
    private Button btnBack;


    ObservableList<Customer> customerList = FXCollections.observableArrayList();
    Connection connection;
    private Agent selectedAgent;
    private Agent choosenAgent;


    public void setDataIntoFields(Agent agent) {
    selectedAgent = agent;

    lbName.setText(selectedAgent.toString() + " Customer's");
    ///RUN CONNECTION
    connection = DBConnection.connectToDB();

        try {
            System.out.println("Connected");
            //shows customers
            customerList = GetCustomer(selectedAgent.getAgentId());
            System.out.println("CustomerList: "+ customerList);
            lvCustomer.getItems().addAll(customerList);
            lvCustomer.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Not Connected");
        }
    }
    @FXML
    Agent setAgents (Agent agent){
        choosenAgent = agent;
        return choosenAgent;
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
        assert lbName != null : "fx:id=\"lblName\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'CustomerDetails.fxml'.";
    }

    private ObservableList<Customer> GetCustomer(int agentId) throws SQLException {
    //Need to pull in a variable later from agents so it only views there customers

        String selectQuery = "SELECT * from customers where AgentId = " + agentId;
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
                        rset.getInt("AgentId")
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

    @FXML
    void lvCustomersClicked(MouseEvent event) throws IOException {
        //on menu click
        System.out.println("clicked on " + lvCustomer.getSelectionModel().getSelectedItem());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Information.fxml"));
        Parent ListViewParent = loader.load();

        Scene ListViewScene = new Scene(ListViewParent);

        InformationController controller = loader.getController();
        controller.getCustomer(lvCustomer.getSelectionModel().getSelectedItem(), false);
        controller.AgentID(choosenAgent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListViewScene);
        window.show();

    }



}






/*No Leprechauns down here */