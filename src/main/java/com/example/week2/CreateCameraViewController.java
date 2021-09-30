package com.example.week2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CreateCameraViewController implements Initializable {

    @FXML
    private ComboBox<String> makeComboBox;

    @FXML
    private TextField modelTextField;

    @FXML
    private Spinner<Integer> megapixelsSpinner;


    @FXML
    private CheckBox digitalCheckBox;

    @FXML
    private CheckBox mirrorlessCheckBox;

    @FXML
    private Slider priceSlider;

    @FXML
    private Label priceLabel;

    @FXML
    private Label msgLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> cameraBrands = Arrays.asList("Canon","Nikon","Sony");
        makeComboBox.getItems().addAll(cameraBrands);

        msgLabel.setText("");

        //configure the price slider
        priceSlider.setMin(300);
        priceSlider.setMax(3000);
        priceSlider.setValue(500);
        priceLabel.setText((String.format("$%.2f", priceSlider.getValue())));

        // using a Lamda expression
        priceSlider.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
            priceLabel.setText((String.format("$%.2f", newValue)));
        }));

        //configure the spinner to work with Integers in the range of 5-75
        //the arguments are min, max, default and increment
        SpinnerValueFactory<Integer> spinnerValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(5,75,18,5);
        megapixelsSpinner.setValueFactory(spinnerValueFactory);
        megapixelsSpinner.setEditable(true);
        TextField spinnerTextField = megapixelsSpinner.getEditor();
        spinnerTextField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
            } catch(Exception e)
            {
                spinnerTextField.setText(oldValue);
            }
        }));
    }

    @FXML
    private void submitButton()
    {
        try
        {
            String make = this.makeComboBox.getSelectionModel().getSelectedItem();
            String model = this.modelTextField.getText();
            ArrayList<String> lenses = new ArrayList<>();
            lenses.addAll(Arrays.asList("70-200 F2.8", "15-50 F1.8", "100-400 F4.5"));
            int mp = megapixelsSpinner.getValue();
            double price = priceSlider.getValue();
            boolean digital = this.digitalCheckBox.isSelected();
            boolean mirrorless = this.mirrorlessCheckBox.isSelected();

            Camera camera = new Camera(make,model,lenses,mp,price,digital,mirrorless);
            msgLabel.setText("Created camera: " + camera);
        }catch (Exception e)
        {
            this.msgLabel.setText(e.getMessage());
        }
    }
}

