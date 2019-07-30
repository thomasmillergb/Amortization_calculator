package thomasmillergb.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import thomasmillergb.client.Client;

class ServiceTest {

    @Mock
    private Client client;

    private Service underTest;

    @BeforeEach
    void setup(){
        underTest = new Service(client);
    }


}