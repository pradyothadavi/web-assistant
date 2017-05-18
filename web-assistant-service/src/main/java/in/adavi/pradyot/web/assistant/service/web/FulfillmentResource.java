package in.adavi.pradyot.web.assistant.service.web;

import com.github.pradyothadavi.response.FulfillmentServiceResponse;
import com.github.pradyothadavi.response.QueryResponse;
import com.google.inject.Inject;
import in.adavi.pradyot.web.assistant.service.core.FulfillmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by pradyot.ha on 16/05/17.
 */
@Api(value = "Fulfillment")
@Path("/{api_version}/fulfillment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FulfillmentResource {
  
  private static final Logger logger = LoggerFactory.getLogger(FulfillmentResource.class);
  
  private FulfillmentService fulfillmentService;
  
  @Inject
  public FulfillmentResource(FulfillmentService fulfillmentService) {
    this.fulfillmentService = fulfillmentService;
  }
  
  @POST
  public Response fulfillRequest(@ApiParam(value = "API version", allowableValues = "v1", required = true) @PathParam("api_version") String apiVersion,
                                 @ApiParam(value = "X-Merchant-Id", required = true) @HeaderParam("X-Merchant-Id") String merchantId,
                                 @ApiParam(value = "X-Authorization", required = true) @HeaderParam("X-Authorization") String auth,
                                 @ApiParam(value = "X-Client-Id", required = true) @HeaderParam("X-Client-Id") String clientId,
                                 @Valid @NotNull QueryResponse queryResponse){
    
    logger.info("Query Response : {}",queryResponse);
    FulfillmentServiceResponse fulfillmentServiceResponse = fulfillmentService.processFulfillmentRequest(queryResponse);
    logger.info("FulfillmentServiceResponse : {}",fulfillmentServiceResponse);
    return Response.ok(fulfillmentServiceResponse).build();
  }
}
