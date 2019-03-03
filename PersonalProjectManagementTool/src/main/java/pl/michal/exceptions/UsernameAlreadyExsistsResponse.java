package pl.michal.exceptions;


public class UsernameAlreadyExsistsResponse {
    private String username;


    public UsernameAlreadyExsistsResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
