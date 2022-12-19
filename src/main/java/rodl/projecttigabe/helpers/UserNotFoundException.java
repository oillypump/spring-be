package rodl.projecttigabe.helpers;

public class UserNotFoundException extends Exception {
    
    public UserNotFoundException() {
        super("user with this username not found in database");
    }

    public UserNotFoundException( String msg) {
        super(msg);
    }

    

}
