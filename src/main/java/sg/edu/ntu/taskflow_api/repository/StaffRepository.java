package sg.edu.ntu.taskflow_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.ntu.taskflow_api.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    
}
