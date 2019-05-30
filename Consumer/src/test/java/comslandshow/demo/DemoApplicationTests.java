package comslandshow.demo;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import comslandshow.client.api.DefaultApi;
import comslandshow.client.invoker.ApiClient;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource(properties = "server.port=1377")
public class DemoApplicationTests {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("B", "localhost", 8707, this);

    private DefaultApi defaultApi;

    @Before
    public void setUp() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(mockProvider.getUrl());
        defaultApi = new DefaultApi(apiClient);
    }

    @Pact(consumer = "A")
    public RequestResponsePact createPactForTest(PactDslWithProvider builder) {
        return builder
                .given("test")
                .uponReceiving("test request")
                .path("/test")
                .method("GET")
                .willRespondWith()
                .status(200)
                .toPact();
    }

    @Test
    @PactVerification(fragment = "createPactForTest")
    public void test() {
        defaultApi.getTest();
    }

}
