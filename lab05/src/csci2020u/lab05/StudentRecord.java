package csci2020u.lab05;

public class StudentRecord {
	private String m_sid="";
	private float m_midterm=0.0f;
	private float m_assignments=0.0f;
	private float m_finalExam=0.0f;
	private float m_finalMark=0.0f;
	private char m_letterGrade='\0';
	
	public StudentRecord(String _sid, float _assignments, float _midterm, float _finalExam) {
		m_sid = _sid;
		m_assignments = _assignments;
		m_midterm = _midterm;
		m_finalExam = _finalExam;
		
		m_finalMark = 0.2f*m_assignments + 0.3f*m_midterm + 0.5f*m_finalExam;
		if (m_finalMark>=50) {
			if (m_finalMark>=60) {
				if (m_finalMark>=70) {
					if (m_finalMark>=80) {
						m_letterGrade = 'A';
					}
					else {
						m_letterGrade = 'B';
					}
				}
				else {
					m_letterGrade = 'C';
				}
			}
			else {
				m_letterGrade = 'D';
			}
		}
		else {
			m_letterGrade = 'F';
		}
	}
	
	public String getStudentID() 	{return m_sid;}
	public float getMidterm() 		{return m_midterm;}
	public float getAssignments() 	{return m_assignments;}
	public float getFinalExam() 	{return m_finalExam;}
	public float getFinalMark() 	{return m_finalMark;}
	public char getLetterGrade() 	{return m_letterGrade;}
}
