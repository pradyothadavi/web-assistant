package in.adavi.pradyot.web.assistant.service.web;

import com.github.pradyothadavi.core.AiConversationService;
import com.github.pradyothadavi.model.Context;
import com.github.pradyothadavi.model.Event;
import com.github.pradyothadavi.model.QueryRequest;
import com.github.pradyothadavi.model.QueryResponse;
import com.google.inject.Inject;
import in.adavi.pradyot.web.assistant.api.model.Chat;
import in.adavi.pradyot.web.assistant.service.application.Constant;
import in.adavi.pradyot.web.assistant.service.application.context.KiranaStoreContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pradyot.ha on 16/05/17.
 */
@Api(value = "Kirana Store")
@Path("/{api_version}/kiranastore")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class KiranaStoreResource {
  
  private static final Logger logger = LoggerFactory.getLogger(KiranaStoreResource.class);
  
  private AiConversationService aiConversationService;
  
  @Inject
  public KiranaStoreResource(AiConversationService aiConversationService) {
    this.aiConversationService = aiConversationService;
  }
  
  @POST
  @Path("/shop")
  public Response shop(@ApiParam(value = "API version", allowableValues = "v1", required = true) @PathParam("api_version") String apiVersion,
                       @ApiParam(value = "X-Merchant-Id", required = true) @HeaderParam("X-Merchant-Id") String merchantId,
                       @ApiParam(value = "X-Authorization", required = true) @HeaderParam("X-Authorization") String auth,
                       @ApiParam(value = "X-Client-Id", required = true) @HeaderParam("X-Client-Id") String clientId,
                       @Valid @NotNull Chat chat) {
    
    logger.info("Chat : {}", chat);
    QueryRequest queryRequest = new QueryRequest();
    if(StringUtils.isEmpty(chat.getMessage()) && StringUtils.isEmpty(chat.getSessionId()))
    {
      Event event = new Event();
      event.setName("Welcome");
      
      queryRequest.setEvent(event);
    } else {
      queryRequest.setQuery(chat.getMessage());
    }
    String sessionId = null != chat.getSessionId() ? chat.getSessionId() : KiranaStoreContext.getHeaderValues(Constant.X_REQUEST_ID).get(0);
    queryRequest.setSessionId(sessionId);
  
    Map<String,Object> userContextParams = new HashMap<>();
    userContextParams.put("userId",chat.getUser().getUserId());
    userContextParams.put("firstName",chat.getUser().getFirstName());
    Context context = new Context();
    context.setName("user-context");
    context.setParameters(userContextParams);
    context.setLifespan(100);
    queryRequest.setContexts(Arrays.asList(context));
    
    QueryResponse queryResponse = aiConversationService.processConversation(queryRequest);
    if(null != queryResponse)
    {
      chat.setRecipientResponse(queryResponse.getResult().getFulfillment().getSpeech());
      chat.setSessionId(queryResponse.getSessionId());
    } else {
      chat.setRecipientResponse("Oops! Something went wrong.");
      chat.setSessionId(sessionId);
    }
    return Response.ok(chat).build();
  }
}
