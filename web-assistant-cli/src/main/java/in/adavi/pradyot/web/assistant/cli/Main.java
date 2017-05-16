package in.adavi.pradyot.web.assistant.cli;

import in.adavi.pradyot.web.assistant.client.WebAssistantClient;
import in.adavi.pradyot.web.assistant.client.WebAssistantClientException;
import in.adavi.pradyot.web.assistant.client.WebAssistantClientFactory;

/**
 * @author pradyot.ha
 */
public class Main {

  public static void main(String args[]) throws WebAssistantClientException {
    if (args.length < 1) {
      System.err.printf("Usage: web-assistant-cli [server-endpoint]");
    }
  
    WebAssistantClient stdClient = WebAssistantClientFactory.getStandardClient(args[0],"cli");
    CommandLineProcessor clp = new CommandLineProcessor(stdClient);
    clp.start();
  }
}
