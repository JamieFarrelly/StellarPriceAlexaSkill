package com.jamiefarrelly.StellarPriceAlexa;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * This class could be the handler for an AWS Lambda function powering an Alexa Skills Kit
 * experience. To do this, simply set the handler field in the AWS Lambda console to
 * "com.jamiefarrelly.StellarPriceAlexa.StellarPriceSpeechletRequestStreamHandler" For this to work, you'll also need to build
 * this project using the {@code lambda-compile} Ant task and upload the resulting zip file to power
 * your function.
 * 
 * Based on https://github.com/amzn/alexa-skills-kit-java
 */
public final class StellarPriceSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    
    private static final Set<String> supportedApplicationIds = new HashSet<String>();
    
    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds.add("amzn1.echo-sdk-ams.app.[unique-value-here]");
    }

    public StellarPriceSpeechletRequestStreamHandler() {
        super(new StellarPriceSpeechlet(), supportedApplicationIds);
    }
}
