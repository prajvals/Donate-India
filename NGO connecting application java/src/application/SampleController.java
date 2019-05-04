package application;
import javafx.fxml.*;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

import javafx.event.*;
public class SampleController {
	@FXML
	TextField ngoOwnerIdInputBox;
	@FXML
	TextField ngoNameInputBox;
	@FXML
	TextField ngoStreetInputBox;
	@FXML
	TextField ngoCityInputBox;
	@FXML
	TextField ngoPincodeInputBox;
	@FXML
	TextField ngoKOFInputBox;
	@FXML
	TextField ngoCentralPhoneNumberInputBox;
	@FXML
	TextField ngoInfoInputBox;
	
	 @FXML
	 private TextField loginUserUserName;
	 @FXML
	 private TextField loginUserUserPassword;

	 @FXML
	 private TextField loginNgoName;
	 @FXML
	 private TextField loginNgoPassword;

     
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
    private TextField userIdInputBox;

    @FXML
    private TextField userPincode;

    @FXML
    private TextField editNgoPhoneNumber;

    @FXML
    private TextField editCentralPhoneNumber;
    @FXML
    private TextField ngoUpdateName;
    @FXML
    private TextField FirstNameform;

    @FXML
    private TextField Streetform;

    @FXML
    private TextField Cityform;

    @FXML
    private TextField PinCodeform;

    @FXML
    private TextField LastNameform;

    @FXML
    private TextField KindofRequestform;
    @FXML
    private TextField updatedOfficeNumberBoardOfDirectors;

    @FXML
    private TextField ngoNameBoardOfDirectors;

    @FXML
    private TextField updatedOfficeHeadNameBoardOfDirectors;
    @FXML
    private Label knowMoreAboutNGOLabel;
    @FXML
    private TextField customQuery;
    @FXML
    void runCustomQuery(ActionEvent event) throws SQLException {
    	CollectionFunctions cf = new CollectionFunctions();
    	cf.runCustomQuery(customQuery.getText()); 
    	}
    @FXML
    void openCustomQueryPage(ActionEvent event) throws IOException {
    	Parent welcomeScreen = FXMLLoader.load(getClass().getResource("CustomQueryPage.fxml"));
		Scene screen = new Scene(welcomeScreen);
		
		Stage ngoStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		ngoStage.setScene(screen);
		ngoStage.show();
    }

    @FXML
    void updateOfficeHeadNameBoardOfDirectors(ActionEvent event) {
    	updateFunctions uf = new updateFunctions();
    	if(ngoNameBoardOfDirectors.getText() == null)
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setContentText("Please Enter a value for the NGO Name");
    		alert.setTitle("Value missing");
    		alert.show();
    	}
    	uf.update_office_head_name(ngoNameBoardOfDirectors.getText(), updatedOfficeHeadNameBoardOfDirectors.getText());
    }

    @FXML
    void updateOfficeNumberBoardOfDirectors(ActionEvent event) {
    	updateFunctions uf = new updateFunctions();
    	if(ngoNameBoardOfDirectors.getText() == null)
    	{
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setContentText("Please Enter a value for the NGO Name");
    		alert.setTitle("Value missing");
    		alert.show();
    	}
    	uf.update_office_phone_no(ngoNameBoardOfDirectors.getText(), updatedOfficeNumberBoardOfDirectors.getText());

    }
    @FXML
    void changeToBoardOfDirectorsTable(ActionEvent event) throws IOException {
    	Parent welcomeScreen = FXMLLoader.load(getClass().getResource("UpdateBoardOfDirectors.fxml"));
		Scene screen = new Scene(welcomeScreen);
		
		Stage ngoStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		ngoStage.setScene(screen);
		ngoStage.show();
    }
    @FXML
    void SubmitRequestForm(ActionEvent event) throws SQLException {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("Wrong values inserted");
    	boolean flag = true;
    	if(isNumeric(FirstNameform.getText()))
    	{	flag = false;
    		alert.setContentText("Enter a String value for first name");
    		alert.show();
    	}
    	if(isNumeric(LastNameform.getText()))
    	{
    		flag = false;
    		alert.setContentText("Enter a String value for last name");
    		alert.show();
    	}
    	if(isNumeric(Streetform.getText()))
    	{
    		flag = false;
    		alert.setContentText("Enter a String value for Street of thte user");
    		alert.show();
    	}
    	if(!isNumeric(PinCodeform.getText()) && PinCodeform.getText().length() != 6)
    	{
    		flag = false;
    		alert.setContentText("Enter a numeric value for pincode. Note that pincode should contain 6 letters");
    		alert.show();
    	}
    	if(isNumeric(KindofRequestform.getText()) )
    	{
    		flag = false;
    		alert.setContentText("Enter a String value for kind of request");
    		alert.show();
    	}
    	/*boolean check = true;
    	if(!KindofRequestform.getText().equalsIgnoreCase("money"))
    	{	check = false;
    	}
    	else
    		if(!KindofRequestform.getText().equalsIgnoreCase("brooms"))
    		{
    			check = false;
    		}
    		else if(!KindofRequestform.getText().equalsIgnoreCase("Med Supplies"))
    		{
    			check = false;
    		}
    		else if(!KindofRequestform.getText().equalsIgnoreCase("stationery"))
    		{
    			check = false;
    		}
    		else {
    			check = true;
    		}
    	if(!check)
    	{
    		alert.setContentText("Enter a valid Kind of Request form. The options are listed below");
    		alert.show();
    	}
    	*/
    	if(isNumeric(Cityform.getText()))
    	{
    		alert.setContentText("Enter a string value for the city.");
    		alert.show();
    	}
    	if(flag)
    	{
    	CollectionFunctions c=new CollectionFunctions();
    	c.user_ngo_search(Cityform.getText(), KindofRequestform.getText());
    	alert.setAlertType(Alert.AlertType.INFORMATION);
    	alert.setContentText("Donation Successful!!");
    	alert.show();
    	}
    	
    }


    @FXML
    void editCentralPhoneNumberButton(ActionEvent event) {
    	updateFunctions uf = new updateFunctions();
    	System.out.println(ngoUpdateName.getText());
    	if(editCentralPhoneNumber.getText() !=null)
    	uf.update_ngo_central_office_number(ngoUpdateName.getText(),editCentralPhoneNumber.getText());
    	System.out.println("updated");
    }
   
    
    
	
	@FXML
  void verifyCorrectnessOfUserForm(ActionEvent event) throws IOException {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Wrong Value Inserted");
		String s = "";
		boolean isCorrect = true;
		if(!isNumeric(userIdInputBox.getText()))
		{
			 isCorrect = false;
			 alert.setContentText(s + ".Please Enter a numerical value's for User Id");
			 alert.show();
			 userIdInputBox.setText(null);
		}
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
			InsertFunctions iF = new InsertFunctions();
			iF.insertUserDetails(Integer.parseInt(userIdInputBox.getText()), userFirstName.getText(), userLastName.getText(), userStreet.getText(), userCity.getText(), Integer.parseInt(userPincode.getText()));
		}
  }
	@FXML
    void changeToLoginScreenUser(ActionEvent event) throws IOException  {
		Parent welcomeScreen = FXMLLoader.load(getClass().getResource("DonationRequest.fxml"));
		Scene screen = new Scene(welcomeScreen);
		
		Stage ngoStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		ngoStage.setScene(screen);
		ngoStage.show();
    }
	
    @FXML
    void changeToSignUpUser(ActionEvent event) throws IOException {
    	Parent welcomeScreen = FXMLLoader.load(getClass().getResource("UserSignup.fxml"));
		Scene screen = new Scene(welcomeScreen);
		
		Stage ngoStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		ngoStage.setScene(screen);
		ngoStage.show();
    }
	@FXML
    void authoriseUserUserLogin(ActionEvent event) {
		boolean user = true;
		CollectionFunctions cf = new CollectionFunctions();
		if(!cf.login_window_userName(loginUserUserName.getText()))
				{
					user = false;
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("UserName not found in the database. Enter a correct userName");
					alert.setTitle("Wrong values inserted");
					alert.show();
				}		
		if(user)
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Login successful");
			alert.setTitle("Yaaay");
			alert.show();
		}
    }
	@FXML
    void authoriseNgoLogin(ActionEvent event) throws SQLException, IOException {
		boolean user = true;
		boolean password = true;
		CollectionFunctions cf = new CollectionFunctions();
		if(!cf.login_window_ngoUsername(loginNgoName.getText()))
				{
					user = false;
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("NGO Name not found in the database. Enter a correct NGO Name");
					alert.setTitle("Wrong values inserted");
					alert.show();
				}
		String username="\"" + loginNgoName.getText() + "\"";
		String password1="\"" + loginNgoPassword.getText() +"\"";
		if(!cf.login_window_ngoPassword(username,password1))
				{
					password = false;
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Password for NGO " + loginNgoName.getText()+" is wrong. Enter a correct password for the NGO");
					alert.setTitle("Wrong values inserted");
					alert.show();
				}		
		if(user && password)
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Login successful");
			alert.setTitle("Yaaay");
			alert.show();
			Parent welcomeScreen = FXMLLoader.load(getClass().getResource("EditOfficeDetails.fxml"));
			Scene screen = new Scene(welcomeScreen);
			
			Stage ngoStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			ngoStage.setScene(screen);
			ngoStage.show();
		}
    }
	    
	public  boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	@FXML
    void changeToNgoScreen(ActionEvent event) throws IOException {
		Parent welcomeScreen = FXMLLoader.load(getClass().getResource("EnterAsNGO.fxml"));
		Scene screen = new Scene(welcomeScreen);
		
		Stage ngoStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		ngoStage.setScene(screen);
		ngoStage.show();
	}
	@FXML
    void changeToUserScreen(ActionEvent event) throws IOException {
		Parent welcomeScreen = FXMLLoader.load(getClass().getResource("EnterAsUser.fxml"));
		Scene screen = new Scene(welcomeScreen);
		
		Stage userStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		userStage.setScene(screen);
		userStage.show();
	}
	@FXML
    void changeToLoginScreenNGO(ActionEvent event) {
		try {
		Parent welcomeScreen = FXMLLoader.load(getClass().getResource("LogInNGO.fxml"));
		Scene screen = new Scene(welcomeScreen);
		
		Stage userStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		userStage.setScene(screen);
		userStage.show();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
    @FXML
    void changeToSignUpNGO(ActionEvent event) {
    	try {
    	Parent welcomeScreen = FXMLLoader.load(getClass().getResource("NGOSignup.fxml"));
		Scene screen = new Scene(welcomeScreen);
		
		Stage userStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		userStage.setScene(screen);
		userStage.show();
    	}
    
    catch(IOException e)
    {
    	System.out.println(e.getMessage());
    }
    }
	@FXML
    void checkValidityOfDetailsNGOSignupForm(ActionEvent event) throws IOException {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Wrong Value Inserted");
		String s = "";
		boolean isCorrect = true;
		 if(!isNumeric(ngoOwnerIdInputBox.getText()))
		 {
			 isCorrect = false;
			 alert.setContentText(s + ".Please Enter a numeric value for ngoOwnerId");
			 alert.show();
			 ngoOwnerIdInputBox.setText(null);
		 }
		 if(isNumeric(ngoNameInputBox.getText()))
		 {
			 isCorrect = false;
			alert.setContentText(s+ ".Please Enter a string value for NGO name");
			alert.show();
			ngoNameInputBox.setText(null);
			
		 }
		 if(!isNumeric(ngoPincodeInputBox.getText()) || ngoPincodeInputBox.getText().length() !=6 )
		 {
			 isCorrect = false;
			 alert.setContentText(s + ".Please Enter a numeric value for Pincode. Note that pincode should be exactly 6 numbers");
			 alert.show();
			 ngoPincodeInputBox.setText(null);
		 }
		 if(isNumeric(ngoKOFInputBox.getText()))
		 {
			 isCorrect = false;
			 alert.setContentText(s +".Please Enter a String value for Kind of Request");
			 alert.show();
			 ngoKOFInputBox.setText(null);
		 }
		 if(!isNumeric(ngoCentralPhoneNumberInputBox.getText()) || ngoCentralPhoneNumberInputBox.getText().length() != 10)
		 {
			 isCorrect = false;
			 alert.setContentText("Please enter a number for Central Phone Number. Note that the Central Phone Office Number should contain 10 number ");
			 alert.show();
		 }
		 if(isNumeric(ngoInfoInputBox.getText()))
		 {
			 isCorrect = false;
			 alert.setContentText("Please Enter a String value for NGO Info Box");
			 alert.show();
			 ngoInfoInputBox.setText(null);
		 }
		if(isCorrect) {
			InsertFunctions iF = new InsertFunctions();
			iF.setConnection();
			iF.insertNGODetails(Integer.parseInt(ngoOwnerIdInputBox.getText()), ngoNameInputBox.getText(), ngoStreetInputBox.getText(), ngoCityInputBox.getText(), Integer.parseInt(ngoPincodeInputBox.getText()), ngoKOFInputBox.getText(), ngoCentralPhoneNumberInputBox.getText(), ngoInfoInputBox.getText());
			
		}
		
    }
}
	

