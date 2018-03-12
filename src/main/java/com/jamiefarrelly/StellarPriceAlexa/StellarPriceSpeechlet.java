package com.jamiefarrelly.StellarPriceAlexa;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.lucadev.coinmarketcap.CoinMarketCap;
import com.lucadev.coinmarketcap.model.CoinMarket;

/**
 * 
 * Based on https://github.com/amzn/alexa-skills-kit-java
 */
public class StellarPriceSpeechlet implements Speechlet {

    // PUBLIC ---------------------------------------------------------------------------------------------
    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;
        
        if ("StellarPriceIntent".equals(intentName)) {
            
            return getStellarPrice();
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            
            return getHelpResponse();
        } else if ("AMAZON.StopIntent".equals(intentName)) {
            
            return getCancelOrStopResponse();
        } else if ("AMAZON.CancelIntent".equals(intentName)) {
            
            return getCancelOrStopResponse();
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        
        return getWelcomeResponse();
    }

    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
        // TODO Auto-generated method stub
        
    }

    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        // TODO Auto-generated method stub
        
    }  
    
    // PRIVATE ---------------------------------------------------------------------------------------------
    /**
     * Creates a {@code SpeechletResponse} for the Stellar Price Checker intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getStellarPrice() {
        
        CoinMarket market = CoinMarketCap.ticker("stellar").get();

        String speechText = "The price of a Lumen is currently $" + market.getPriceUSD();

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("StellarPrice");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }
    
    /**
     * Creates and returns a {@code SpeechletResponse} with a welcome message.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getWelcomeResponse() {
        
        String speechText = "Welcome to the Stellar Price Checker, you can ask for the price by asking what's the price of Stellar";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("StellarPrice");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
    
    /**
     * Creates a {@code SpeechletResponse} for the help intent.
     *
     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private SpeechletResponse getHelpResponse() {
        
        String speechText = "You can say ask Stellar Price how's Stellar doing";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("StellarPrice");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
    
    /**
     * Says goodbye when you call the cancel or stop intent, it's required to get on to the Skill Store so we must handle it
     * 
     * @return SpeechletResponse
     */
    public SpeechletResponse getCancelOrStopResponse() {

        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("Goodbye");

        return SpeechletResponse.newTellResponse(outputSpeech);
    }

}