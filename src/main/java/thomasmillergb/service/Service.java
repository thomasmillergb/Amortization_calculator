package thomasmillergb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thomasmillergb.client.Client;

@Component
public class Service {
    private final Client client;

    @Autowired
    public Service(final Client client) {
        this.client = client;
    }
}
