package cs544;

import jakarta.persistence.EntityManager;

public class StudentService {
	private StudentDAO studentdao;

	public StudentService() {
		studentdao = new StudentDAO();
	}

	public Student getStudent(long studentid) {
		EntityManager em = EntityManagerHelper.getCurrent();
		em.getTransaction().begin();
		Student foundStudent = studentdao.load(studentid);
		em.getTransaction().commit();
		em.close(); // testing
		return foundStudent;
	}
}
