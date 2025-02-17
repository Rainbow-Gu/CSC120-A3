import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

class Conversation implements Chatbot {

  // Attributes 
  ArrayList<String> transcript;

  /**
   * Constructor 
   */
  Conversation() { 
    this.transcript = new ArrayList<String>(); 
  }

  /**
   * Starts and runs the conversation with the user
   */
  public void chat() {

    // To get user input
    Scanner input = new Scanner(System.in);

    // Print welcome and add to transcript
    System.out.println("\nHow many rounds would you like to chat?");

    // Take in number of rounds from the user
    int round = input.nextInt();
    
    // Don't skip
    input.nextLine();

    // Conversation starter for valid rounds input
    if (round > 0) {

      System.out.println("\nWelcome to chatbox! What would you like to talk about?");
      transcript.add("Welcome to chatbox! What would you like to talk about?");
   
    } 


    // Run user answer and save to transcript until reaches the amount of round
    for (int i = 0; i < round; i ++) {

      // Get user input for each round
      String userInput = input.nextLine();
        
      transcript.add(userInput);
        
      String response = respond(userInput); // save response to user input
      System.out.println(response);
      transcript.add(response);
    }
    
    // say byebye 
    System.out.println("Bye bye!");
    transcript.add("Bye bye!");

    // Close input scanner
    input.close();

  }

  /**
   * Prints transcript of conversation
   */
  public void printTranscript() {
    
    // Print header
    System.out.println("\nTranscript:");
    
    // Loop through transcript Array and print each element
    for (String line : transcript) {
      System.out.println(line);
    }

  }

  /**
   * Mirror Words
   * Used below source for creating dictionary in Java:
   * https://stackoverflow.com/questions/13543457/how-do-you-create-a-dictionary-in-java
   */
  private static Map<String, String> mirrorDict = new HashMap<String, String>();
  static {
    mirrorDict.put("i", "you");
    mirrorDict.put("me", "you");
    mirrorDict.put("am", "are");
    mirrorDict.put("you", "I");
    mirrorDict.put("my", "your");
    mirrorDict.put("your", "my");
  }

  /**
   * Static canned responses
   */
  private static String[] cannedResponses = {
    "Interesting!", 
    "Tell me more ~", 
    "Why do you think that?", 
    "Wow, I see.",
    "Mmm-hm...",
    "Really?!"
  };

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  public String respond(String userInput) {
    String returnString = ""; 

    // Clean punctuations and cases
    String cleanPunct = userInput.replaceAll("[.,;:\"\'-+!?/]", "");
    String cleanedInput = cleanPunct.toLowerCase();

    // Split strings to words by space
    String[] words = cleanedInput.split(" ");
   
    // Set flag for mirror words
    Boolean mirrorBoolean = false;

    // Check for mirror words in user input
    for (int i = 0; i < words.length; i++) {
      
      String word = words[i];
      
      // Flag boolean to true if it is mirror word
      if (mirrorDict.containsKey(word)) {
        
        // Change words if mirror words were found
        words[i] = mirrorDict.get(word);
        mirrorBoolean = true;
      
      }
    } 

    // Join the words if there is mirror word
    if (mirrorBoolean == true) {
      
      returnString = String.join(" ", words);
      
      // Capitalize first letter learnt from: https://stackoverflow.com/questions/3904579/how-to-capitalize-the-first-letter-of-a-string-in-java
      returnString = returnString.substring(0, 1).toUpperCase() + returnString.substring(1);

      // Add a question mark
      returnString = returnString + "?";
    
    } else {
      
      // Use random canned response if no mirror words were found
      Random random = new Random();
      returnString = cannedResponses[random.nextInt(cannedResponses.length)];

    }
    
    return returnString; 

  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
