import com.cyberbotics.webots.controller.Supervisor;
import com.cyberbotics.webots.controller.Emitter;

public class KeyEvent extends Supervisor {
  
  private Emitter emitter;
  private final int timeStep = 128;

  
  public void initialization() {
    emitter = getEmitter("emitter");
    keyboardEnable(timeStep);
  }
    
  
  // MyController constructor
  public KeyEvent() {
    super();
  }
  
  public void run() {
    
    String message="";
    String previous_message="";
    int k;
    byte[] formated;
    displayHelp();
    
    while (true) {
      k=keyboardGetKey();
      switch (k){
        case KEYBOARD_LEFT:
          message = "go Left";
          break;
        case KEYBOARD_RIGHT:
          message = "go Right";
          break;
        case KEYBOARD_UP:
          message = "Up";
          break;
        case KEYBOARD_DOWN:
          message = "Down";
          break;
        case 'H':
          message = "UP z";
          break;
        case 'N':
          message = "Down z";
          break;
        case ('I'):
          displayHelp();
          break;
        case 'S':
          message = "Stop";
          break;
        case 'D':
          message = "Reset";
          break;
        default:
          message = "";
      }
      if (!message.equals("") && !message.equals(previous_message)) {
        previous_message=message;
        System.out.println("Please, "+message);
        formated = message.getBytes();
        emitter.send(formated);
      }
      
      // perform a simulation step, quit the loop when
      // the controller has been killed
      if (step(timeStep) == -1) break;
    }
    
    // Enter here exit cleanup code
  }
  private void displayHelp(){
    System.out.println("Commands:\n"+
                   " I for displaying the commands\n"+
                   " LEFT for going Left\n"+
                   " RIGHT for going  Right\n"+
                   " UP for going Up\n"+
                   " DOWN for going Down\n" +
                   " N for going UP in the Z axis\n" +
                   " H for going Down in the Z axis\n" +
                   " S To Stop \n" +
                   " D for reseting the position of Pyramide");
  }


  public static void main(String[] args) {
    KeyEvent controller = new KeyEvent();
    controller.initialization();
    controller.run();
  }
}
