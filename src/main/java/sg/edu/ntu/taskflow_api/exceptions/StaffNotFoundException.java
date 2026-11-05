package sg.edu.ntu.taskflow_api.exceptions;
public class StaffNotFoundException extends RuntimeException {
  public StaffNotFoundException(Long id) {
    super("Could not find staff with id: " + id);
  }

  
}