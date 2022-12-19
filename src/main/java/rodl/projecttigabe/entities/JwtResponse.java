package rodl.projecttigabe.entities;

public class JwtResponse {
    String Token;

    public JwtResponse() {
    }

    public JwtResponse(String token) {
        Token = token;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    
}
