package dao;

public abstract class DAOFactory {

	// serves to GenerateID for Student, Lecturer and Course
	public static AutoGenerateIDDAO getGenerateIDDAO() {
		return new AutoGenerateIDDAOImpl();
	}

	public static StudentDAO getStudentDAO() {
		StudentDAO sdao = new StudentDAOImpl();
		return sdao;
	}
	
	public static LecturerDAO getLecturerDAO() {
		LecturerDAO ldao = new LecturerDAOImpl();
		return ldao;
	}
	
	public static CourseDAO getCourseDAO() {
		CourseDAO cdao = new CourseDAOImpl();
		return cdao;
	}
	
	public static EnrollmentDAO getEnrollmentDAO() {
		EnrollmentDAO edao = new EnrollmentDAOImpl();
		return edao;
	}
	
	public static UserDao getUserDao() {
		UserDao edao = new UserDaoImpl();
		return edao;
	}
	
}
