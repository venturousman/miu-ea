package cs544;

// import java.util.ArrayList;
// import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class StudentDAO {

	@PersistenceContext
	private EntityManager em;

	public StudentDAO() {
	}

	public Student load(long studentid) {
		// EntityGraph<Student> graph = em.createEntityGraph(Student.class);
		// graph.addSubgraph("courselist");

		// Map<String, Object> properties = new HashMap<>();
		// properties.put("javax.persistence.fetchgraph", graph);

		// Student foundStudent = em.find(Student.class, studentid, properties);
		Student foundStudent = em.find(Student.class, studentid);
		return foundStudent;
	}
}
