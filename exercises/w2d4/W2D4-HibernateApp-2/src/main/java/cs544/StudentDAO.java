package cs544;

// import java.util.ArrayList;
// import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;

public class StudentDAO {

	// private Collection<Student> studentlist = new ArrayList<Student>();

	public StudentDAO() {
		// Student student = new Student(12345, "Frank", "Brown");
		// Course course1 = new Course(1101, "Java", "A");
		// Course course2 = new Course(1102, "Math", "B-");
		// student.addCourse(course1);
		// student.addCourse(course2);
		// // studentlist.add(student);

		// EntityManager em = EntityManagerHelper.getCurrent();
		// em.getTransaction().begin();
		// em.persist(student);
		// em.getTransaction().commit();
	}

	public Student load(long studentid) {
		// for (Student student : studentlist) {
		// if (student.getStudentid() == studentid) {
		// return student;
		// }
		// }
		// return null;
		EntityManager em = EntityManagerHelper.getCurrent();

		EntityGraph<Student> graph = em.createEntityGraph(Student.class);
		graph.addSubgraph("courselist");

		Map<String, Object> properties = new HashMap<>();
		properties.put("javax.persistence.fetchgraph", graph);

		Student foundStudent = em.find(Student.class, studentid, properties);
		return foundStudent;
	}
}
