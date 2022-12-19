package rodl.projecttigabe.entities;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private String authority;

    

    public Authority(String authority) {
        this.authority = authority;
    }


    //method
    @Override
    public String getAuthority() {
        return this.authority;
    }

}
