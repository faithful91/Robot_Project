import com.cyberbotics.webots.controller.Supervisor;
import com.cyberbotics.webots.controller.Camera;
import com.cyberbotics.webots.controller.Receiver;
import com.cyberbotics.webots.controller.Field;


public class Camera_controller extends Supervisor{
	
	private Camera camera;
	private static final int timeStep = 32;
  private Receiver receiver;
  
  private enum Mode {
    LEFT, RIGHT, UP, DOWN, RESET, UPZ, DOWNZ, STOP
  }
  
  private Mode mode = Mode.RESET;
  private Field translationField;
	
	public Camera_controller(){
		super();
		camera = this.getCamera("camera1");
		camera.enable(timeStep);
    receiver = getReceiver("receiver");
    receiver.enable(timeStep);
    translationField = getFromDef("Pyramide").getField("translation");
    
	}
	
	public void run (){
		while (true) {
      
      // Read sensors, particularly the order of the supervisor
      if (receiver.getQueueLength()>0){
        String message = new String(receiver.getData());
        receiver.nextPacket();
        System.out.println("I should "+message+"!");
        if (message.equals("go Left"))
          mode = Mode.LEFT;
        else if (message.equals("go Right"))
          mode = Mode.RIGHT;
        else if (message.equals("Up"))
          mode = Mode.UP;
        else if (message.equals("Down"))
          mode = Mode.DOWN;
        else if (message.equals("Reset"))
          mode = Mode.RESET;
        else if(message.equals("UP z"))
          mode= Mode.UPZ;
        else if(message.equals("Down z"))
          mode= Mode.DOWNZ;
        else if(message.equals("Stop"))
          mode= Mode.STOP;
      }
      
      
      double translation[] = translationField.getSFVec3f();
      
      // send actuators commands according to the mode
      switch (mode){
        case UP:
          // Axe Y
          translation[0] = translation[0] -0.001;
          translation[1] = translation[1] + 0;
          translation[2] = translation[2] + 0; 
          this.getFromDef("Pyramide").getField("translation").setSFVec3f(translation);
      break;
        case DOWN:
          translation[0] = translation[0] +0.001;
          translation[1] = translation[1] + 0;
          translation[2] = translation[2] + 0; 
          this.getFromDef("Pyramide").getField("translation").setSFVec3f(translation);
          break;
        case LEFT:
          translation[0] = translation[0] + 0;
          translation[1] = translation[1] + 0;
          translation[2] = translation[2] + 0.001; 
          this.getFromDef("Pyramide").getField("translation").setSFVec3f(translation);
          break;
        case RIGHT:
          translation[0] = translation[0] + 0;
          translation[1] = translation[1] + 0;
          translation[2] = translation[2] - 0.001; 
          this.getFromDef("Pyramide").getField("translation").setSFVec3f(translation);
          break;
        case UPZ:
          translation[0] = translation[0] + 0;
          translation[1] = translation[1] + 0.001;
          translation[2] = translation[2] + 0; 
          this.getFromDef("Pyramide").getField("translation").setSFVec3f(translation);
          break;
        case DOWNZ:
          translation[0] = translation[0] + 0;
          translation[1] = translation[1] - 0.001;
          translation[2] = translation[2] + 0; 
          this.getFromDef("Pyramide").getField("translation").setSFVec3f(translation);
          break;
        case STOP:
          /*translation[0] = translation[0] + 0;
          translation[1] = translation[1] - 0.01;
          translation[2] = translation[2] + 0; */
          this.getFromDef("Pyramide").getField("translation").setSFVec3f(translation);
          break;
        case RESET:
          translation[0] = 0.046;
          translation[1] = 0.278;
          translation[2] = 0;
          this.getFromDef("Pyramide").getField("translation").setSFVec3f(translation);
          break;
        default:
        break;
          
      
      // perform a simulation step, leave the loop when
      // the controller has been killed
      }
      if (step(timeStep) == -1) break;
    }
  }
	
	public static void main(String[] args){
		Camera_controller controller = new Camera_controller();
		controller.run();
	}
}
