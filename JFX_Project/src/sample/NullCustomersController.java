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
/*
BY BRANDON CUTHBERTSON
 */
public class NullCustomersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Customer> lvCustomer;

    @FXML
    private Button btnBack;

    @FXML
    private Text lbName;
    ObservableList<Customer> customerList = FXCollections.observableArrayList();
    Connection connection = DBConnection.connectToDB();
    private Agent selectedAgent;

    @FXML
    Agent setAgent (Agent agent){
        selectedAgent = agent;
        return selectedAgent;
    }
    @FXML
    void btnBackClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent ListViewParent = loader.load();

        Scene ListViewScene = new Scene(ListViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListViewScene);
        window.show();
    }

    @FXML
    void lvCustomersClick(MouseEvent event) throws IOException {
        //on menu click
        System.out.println("clicked on " + lvCustomer.getSelectionModel().getSelectedItem());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Information.fxml"));
        Parent ListViewParent = loader.load();

        Scene ListViewScene = new Scene(ListViewParent);

        InformationController controller = loader.getController();
        controller.getCustomer(lvCustomer.getSelectionModel().getSelectedItem(), true);
        controller.AgentID(selectedAgent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(ListViewScene);
        window.show();

    }

    @FXML
    void initialize() {
        assert lvCustomer != null : "fx:id=\"lvCustomer\" was not injected: check your FXML file 'NullCustomers.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'NullCustomers.fxml'.";
        assert lbName != null : "fx:id=\"lbName\" was not injected: check your FXML file 'NullCustomers.fxml'.";
        connection = DBConnection.connectToDB();

        try {
            System.out.println("Connected");
            //shows customers
            customerList = GetCustomer();
            System.out.println("Customer List: "+ customerList);
            lvCustomer.getItems().addAll(customerList);
            lvCustomer.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Not Connected");
        }

    }

    private ObservableList<Customer> GetCustomer() throws SQLException {
        String selectQuery = "SELECT * from customers where AgentId is NULL";
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

    public void setDataIntoFields(Agent selectedAgent) {
    }
}
