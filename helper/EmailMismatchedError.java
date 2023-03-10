package helper;
//Creating the userdefined throws function to throw the error when the username and email not match with the database
public class EmailMismatchedError extends RuntimeException{
public EmailMismatchedError(String errorMessage) {
	super(errorMessage);
}
}
