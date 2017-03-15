package lexlink.app.com.lexlink.beans;

import java.io.Serializable;

/**
 * Created by Harmis on 15/03/17.
 */

public class LinkUserbean implements Serializable {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
