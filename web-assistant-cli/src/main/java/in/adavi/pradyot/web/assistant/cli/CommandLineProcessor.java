package in.adavi.pradyot.web.assistant.cli;

import in.adavi.pradyot.web.assistant.api.model.Chat;
import in.adavi.pradyot.web.assistant.api.model.User;
import in.adavi.pradyot.web.assistant.client.WebAssistantClient;
import in.adavi.pradyot.web.assistant.client.WebAssistantClientException;

import java.util.Scanner;

/**
 * Created by pradyot.ha on 16/05/17.
 */
public class CommandLineProcessor {
  
  private static final String PROMPT = "> ";
  private final WebAssistantClient webAssistantClient;
  
  public CommandLineProcessor(WebAssistantClient webAssistantClient) {
    if(null == webAssistantClient)
      throw new IllegalArgumentException("WebAssistantClient cannot be null.");
    this.webAssistantClient = webAssistantClient;
  }
  
  public void start() throws WebAssistantClientException{
    Scanner scanner = new Scanner(System.in);
    displayHelp();
    System.out.print(PROMPT);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      long start = System.currentTimeMillis();
      processLine(line);
      long end = System.currentTimeMillis();
      System.out.printf("Time taken = %dms\n\n", end - start);
      System.out.print(PROMPT);
    }
  }
  
  private void processLine(String line) throws WebAssistantClientException {
    String[] tokens = line.split("\\s+");
    
    if (tokens.length < 1) {
      displayHelp();
      return;
    }
    
    String command = tokens[0];
    String[] args = new String[tokens.length - 1];
    System.arraycopy(tokens, 1, args, 0, args.length);
    
    switch (command) {
      case "shop-with":
        Scanner scanner = new Scanner(System.in);
        
        User user = new User();
        user.setUserId(tokens[2]);
        user.setFirstName(tokens[2]);
        
        Chat chat = new Chat();
        chat.setUser(user);
        
        Chat recepientResponse = new Chat();
        
        String input = null;
        do{
          recepientResponse = webAssistantClient.shop(chat,tokens[1]);
          System.out.printf("%-13s%s%n","",tokens[1]+" : "+recepientResponse.getRecipientResponse());
          System.out.print(tokens[2]+" : ");
          input = scanner.nextLine();
          chat.setMessage(input);
          chat.setSessionId(recepientResponse.getSessionId());
        } while (!"exit".equals(input));
        break;
        
      default:
        System.out.printf("Unknown command %s\n", command);
        displayHelp();
        break;
    }
  }
  private void displayHelp() {
    System.out.println("Available commands:");
    System.out.println("\tshop-with");
  }
}
