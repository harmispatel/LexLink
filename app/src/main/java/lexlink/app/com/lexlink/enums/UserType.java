package lexlink.app.com.lexlink.enums;

/**
 * Created by Harmis on 15/03/17.
 */

public enum UserType {

    client("client"), lawyer("lawyer");

    String userType = null;

    UserType(String type) {
        userType = type;
    }

    String getUserType() {
        return userType;
    }

}
