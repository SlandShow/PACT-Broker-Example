package com.slandshow.demo;


import au.com.dius.pact.provider.junit.*;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRestPactRunner.class)
@Consumer("A")
@Provider("B")
@PactBroker
@IgnoreNoPactsToVerify
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @State("test")
    public void testSuccessRequest() {
    }
}
