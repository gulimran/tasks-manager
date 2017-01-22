package imran.tasks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public abstract class BaseController {

    @Autowired
    protected RestTemplate restTemplate;

    @Value("${service.host:localhost}")
    protected String hostname;

    @Value("${service.port:8080}")
    protected String port;

    @Autowired
    protected ObjectMapper objectMapper;
}
