package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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

    }
}
