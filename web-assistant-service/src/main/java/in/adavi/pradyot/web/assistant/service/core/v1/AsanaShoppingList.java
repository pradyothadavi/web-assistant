package in.adavi.pradyot.web.assistant.service.core.v1;

import com.asana.models.Project;
import com.asana.models.Task;
import com.asana.models.Workspace;
import com.github.pradyothadavi.api.ai.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.api.ai.response.QueryResponse;
import com.github.pradyothadavi.google.action.model.*;
import com.github.pradyothadavi.google.action.response.AppResponse;
import com.google.inject.Inject;
import in.adavi.pradyot.web.assistant.api.model.wtm.WTMLeadRequest;
import in.adavi.pradyot.web.assistant.api.model.wtm.WTMLeadResponse;
import in.adavi.pradyot.web.assistant.api.response.GoogleActionResponse;
import in.adavi.pradyot.web.assistant.service.core.SaveShoppingListStrategy;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @author Pradyot H Adavi 22/10/17
 */
public class AsanaShoppingList extends SaveShoppingListStrategy {

    private static final Logger logger = LoggerFactory.getLogger(AsanaShoppingList.class);

    private static final String GREETING_MSG_DISPLAY_TEXT = "Hey, How may I assist you ?";
    private static final String GREETING_MSG_TEXT_TO_SPEECH = "Hey, I am your personal web assistant. How may I assist you ?";

    private Client client;
    private WebTarget webTarget;

    @Inject
    private String personalAccessToken;

    public String getPersonalAccessToken() {
        return personalAccessToken;
    }

    public void setPersonalAccessToken(String personalAccessToken) {
        this.personalAccessToken = personalAccessToken;
    }

    @Override
    protected FulfillmentServiceResponse ask(QueryResponse queryResponse) {

        logger.info("Personal Access Token : ",personalAccessToken);
        com.asana.Client client1 = com.asana.Client.accessToken("0/cb98fa887a02bbb9d183bbf582a694ae");

        for (Workspace workspace : client1.workspaces.findAll()) {
            logger.info("Project : {}",workspace.name);
            if("Family".equals(workspace.name))
            {
                for (Project project:client1.projects.findByWorkspace(workspace.id)) {
                    if("B414".equals(project.name))
                    {
                        try {
                            // create a task in the project
                            Task demoTask = client1.tasks.createInWorkspace(workspace.id)
                                    .data("name", "demo task created at " + new Date())
                                    .data("projects", Arrays.asList(project.id))
                                    .execute();
                            logger.info("Task " + demoTask.id + " created.");
                            break;
                        } catch (IOException e) {
                            logger.info("Exception : {}",e);
                            e.printStackTrace();
                        }
                    }
                }
            }
        }


//        client = ClientBuilder.newClient();
//        client.property(ClientProperties.CONNECT_TIMEOUT, 10000);
//        client.property(ClientProperties.READ_TIMEOUT, 10000);
//
//        this.webTarget = client.target("https://app.asana.com");
//
//        Response response = null;
//
//        WTMLeadRequest wtmLeadRequest = new WTMLeadRequest();
//        wtmLeadRequest.setChapterName("GDG Bangalore");
//        try
//        {
//            response = webTarget.path("/api/1.0/workspaces").request(MediaType.APPLICATION_JSON)
//                    .header("Authorization","Bearer 0/cb98fa887a02bbb9d183bbf582a694ae")
//                    .get();
////                    .post(Entity.entity(wtmLeadRequest, MediaType.APPLICATION_JSON));
//            logger.info("Status : {}",response.getStatus());
//            if (Response.Status.OK.getStatusCode() == response.getStatus())
//            {
////                WTMLeadResponse wtmLeadResponse = response.readEntity(WTMLeadResponse.class);
//                logger.info("Response : {}",response.readEntity(String.class));
//            }
//        }
//        catch (Exception e)
//        {
//            if (null != response)
//            {
//                response.close();
//            }
//            logger.info("Exception : ",e);
//        }

        SimpleResponse simpleResponse = SimpleResponse.builder().textToSpeech(GREETING_MSG_TEXT_TO_SPEECH).displayText(GREETING_MSG_DISPLAY_TEXT).build();
        Item simpleItem = Item.builder().simpleResponse(simpleResponse).build();
        RichResponse richResponse = RichResponse.builder().item(simpleItem).build();

        InputPrompt inputPrompt = new InputPrompt();
        inputPrompt.setRichInitialPrompt(richResponse);

        ExpectedIntent expectedIntent = new ExpectedIntent();
        expectedIntent.setIntent("actions.intent.TEXT");

        ExpectedInput expectedInput = ExpectedInput.builder().inputPrompt(inputPrompt).possibleIntent(expectedIntent).build();
        AppResponse appResponse = AppResponse.builder().expectUserResponse(false).expectedInput(expectedInput).build();

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
