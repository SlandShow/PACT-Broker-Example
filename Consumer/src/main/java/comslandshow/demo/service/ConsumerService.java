package comslandshow.demo.service;

import comslandshow.client.api.DefaultApi;
import comslandshow.client.invoker.ApiClient;

public class ConsumerService {

    /* This RestTemplate API invoker can call another microservice B */
    private final DefaultApi providerApi = new DefaultApi(
            new ApiClient().setBasePath(
                    "http://localhost:7073"
            )
    );

    public String[] getCommunication() {
        return providerApi.getTest().toArray(new String[0]);
    }

}
