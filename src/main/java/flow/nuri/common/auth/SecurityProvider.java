package flow.nuri.common.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public interface SecurityProvider {
    String getCurrentUsername();
}
