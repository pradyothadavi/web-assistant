package in.adavi.pradyot.web.assistant.service.core.v1;

import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import com.github.pradyothadavi.google.action.model.*;
import com.github.pradyothadavi.google.action.response.AppResponse;
import in.adavi.pradyot.web.assistant.api.response.GoogleActionResponse;
import in.adavi.pradyot.web.assistant.service.core.WelcomeStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Pradyot H Adavi 22/10/17
 */
public class WebAssistantWelcome extends WelcomeStrategy {

    private static final Logger logger = LoggerFactory.getLogger(WebAssistantWelcome.class);

    private static final String GREETING_MSG_DISPLAY_TEXT = "Hey, How may I assist you ?";
    private static final String GREETING_MSG_TEXT_TO_SPEECH = "Hey, I am your personal web assistant. How may I assist you ?";

    @Override
    public FulfillmentServiceResponse greetUser(QueryResponse queryResponse) {

        SimpleResponse simpleResponse = SimpleResponse.builder().textToSpeech(GREETING_MSG_TEXT_TO_SPEECH).displayText(GREETING_MSG_DISPLAY_TEXT).build();
        Item simpleItem = Item.builder().simpleResponse(simpleResponse).build();
        RichResponse richResponse = RichResponse.builder().item(simpleItem).build();

        InputPrompt inputPrompt = new InputPrompt();
        inputPrompt.setRichInitialPrompt(richResponse);

        ExpectedIntent expectedIntent = new ExpectedIntent();
        expectedIntent.setIntent("actions.intent.TEXT");

        ExpectedInput expectedInput = ExpectedInput.builder().inputPrompt(inputPrompt).possibleIntent(expectedIntent).build();
        AppResponse appResponse = AppResponse.builder().expectUserResponse(true).expectedInput(expectedInput).build();

        GoogleActionResponse googleActionResponse = new GoogleActionResponse();
        googleActionResponse.setData(appResponse);

        FulfillmentServiceResponse fulfillmentServiceResponse;
        fulfillmentServiceResponse = new FulfillmentServiceResponse();
        fulfillmentServiceResponse.setSpeech(simpleResponse.getTextToSpeech());
        fulfillmentServiceResponse.setDisplayText(simpleResponse.getDisplayText());
        fulfillmentServiceResponse.setSource("web-assistant-fulfillment");
        fulfillmentServiceResponse.setData(googleActionResponse);

        return fulfillmentServiceResponse;
    }
}
