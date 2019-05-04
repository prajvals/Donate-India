package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UserFormControllerClass {

    @FXML
    private TextField userFirstName;

    @FXML
    private TextField userStreet;

    @FXML
    private TextField userCity;

    @FXML
    private Button userSubmitButton;

    @FXML
    private TextField userLastName;

    @FXML
    private TextField userPincode;
    
    public  boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
	@FXML
  void verifyCorrectnessOfUserForm(ActionEvent event) throws IOException {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Wrong Value Inserted");
		String s = "";
		boolean isCorrect = true;
		 if(isNumeric(userFirstName.getText()))
		 {
			 isCorrect = false;
			 alert.setContentText(s + ".Please Enter a String value for User's First Name");
			 alert.show();
			 userFirstName.setText(null);
		 }
		 if(isNumeric(userStreet.getText()))
		 {
			 isCorrect = false;
			alert.setContentText(s+ ".Please Enter a string value for User's First Street");
			alert.show();
			userStreet.setText(null);
			
		 }
		 if(isNumeric(userCity.getText()) )
		 {
			 isCorrect = false;
			 alert.setContentText(s + ".Please Enter a String Value for User City");
			 alert.show();
			 userCity.setText(null);
		 }
		 if(isNumeric(userLastName.getText()))
		 {
			 isCorrect = false;
			 alert.setContentText(s +".Please Enter a String value for User's Last Name");
			 alert.show();
			 userLastName.setText(null);
		 }
		 if(!isNumeric(userPincode.getText()) || userPincode.getText().length() != 6)
		 {
			 isCorrect = false;
			 alert.setContentText(s + "Please Enter a Numerical value for User's Pincode. Note that pincode should contain only 6 numbers");
			 alert.show();
			 userPincode.setText(null);
		 }
		if(isCorrect)
		{
			
		}
  }

}
