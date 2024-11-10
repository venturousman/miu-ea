package cs544;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// import jakarta.persistence.EntityManager;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class StudentService {
	@Autowired
	private StudentDAO studentdao;

	// public StudentService() {
	// studentdao = new StudentDAO();
	// }

	public Student getStudent(long studentid) {
		// EntityManager em = EntityManagerHelper.getCurrent();
		// em.getTransaction().begin();
		Student foundStudent = studentdao.load(studentid);
		// em.getTransaction().commit();
		// em.close(); // testing
		return foundStudent;
	}
}
