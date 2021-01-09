package TeamFormation;
import java.util.ArrayList;
import java.util.HashMap;

public class TeamMember {

	String projectID;
	HashMap<String, Student> member = new HashMap<String, Student>();
	HashMap<String, Double> average = new HashMap<String, Double>();
	double shortfall;
	double percentage;
	double totalAverageCompetencyLevel;
	double standardDeviationInSkillCompetency;   
	double standardDeviationforPercentage;
	
	public TeamMember(String project, HashMap<String, Student> members) {
		this.projectID = project;
		this.member = members;
	}

	public TeamMember(String project) {
		this.projectID = project;
	}

	public void addMember(Student member) {
		this.member.put(member.getStudentID(), member);
	}

	public String toString() {
		return "  ProjectID: " + projectID + "\nMembers:" + member + " Average skill:" + average + " Shortfall:"
				+ shortfall + " Percentage: " + percentage + "%" + " standardDeviationInSkillCompetency: "
				+  "\n\n";
	}

	public String getProject() {
		return this.projectID;
	}
	
	public HashMap<String, Double> getAverage() {
		return this.average;
	}

	public HashMap<String, Student> getMember() {
		return member;
	}

	public void addAverage(String skill, double grade) {
		average.put(skill, grade);
	}

	public double getShortfall() {
		return shortfall;
	}

	public double getPercentage() {
		return percentage;
	}
	
	public double getTotalAverageCompetencyLevel() {
		return totalAverageCompetencyLevel;
	}
	
	public double getStandardDeviationInSkillCompetency() {
		return standardDeviationInSkillCompetency;
	}
	
	public double getStandardDeviationforPercentage() {
		return standardDeviationforPercentage;
	}


	public void averageSkillCompetency() {
		double totalP = 0, totalN = 0, totalA = 0, totalW = 0;
		for (String studentID : this.member.keySet()) {
			Student s = this.member.get(studentID);
			for (String skillCategory : s.getGrade().keySet()) {
				if (skillCategory.equals("P")) {
					totalP += s.getGrade().get(skillCategory);
				}
				if (skillCategory.equals("N")) {
					totalN += s.getGrade().get(skillCategory);
				}
				if (skillCategory.equals("A")) {
					totalA += s.getGrade().get(skillCategory);
				}
				if (skillCategory.equals("W")) {
					totalW += s.getGrade().get(skillCategory);
				}
			}
		}

		average.put("P", totalP / 4);
		average.put("N", totalN / 4);
		average.put("A", totalA / 4);
		average.put("W", totalW / 4);
	}

	public void shortfall(HashMap<String, Project> projects) {
		double shortfall = 0, ProjectMinusTeam = 0;
		for (String rankingCategory : projects.get(this.projectID).getRankingSkill().keySet()) {
			if (rankingCategory.equals("P")) {
				ProjectMinusTeam = Integer.parseInt(projects.get(this.projectID).getRankingSkill().get("P"))
						- this.getAverage().get("P");
				if (ProjectMinusTeam < 0)
					ProjectMinusTeam = 0;
				shortfall += ProjectMinusTeam;
			}

			if (rankingCategory.equals("A")) {
				ProjectMinusTeam = Integer.parseInt(projects.get(this.projectID).getRankingSkill().get("A"))
						- this.getAverage().get("A");
				if (ProjectMinusTeam < 0)
					ProjectMinusTeam = 0;
				shortfall += ProjectMinusTeam;
			}

			if (rankingCategory.equals("W")) {
				ProjectMinusTeam = Integer.parseInt(projects.get(this.projectID).getRankingSkill().get("W"))
						- this.getAverage().get("W");
				if (ProjectMinusTeam < 0)
					ProjectMinusTeam = 0;
				shortfall += ProjectMinusTeam;
			}

			if (rankingCategory.equals("N")) {
				ProjectMinusTeam = Integer.parseInt(projects.get(this.projectID).getRankingSkill().get("N"))
						- this.getAverage().get("N");
				if (ProjectMinusTeam < 0)
					ProjectMinusTeam = 0;
				shortfall += ProjectMinusTeam;
			}
		}
		this.shortfall = shortfall;
	}

	public void CalculatePercentage() {
		double percentage = 0;
		for (String studentID : this.member.keySet()) {
			Student s = this.member.get(studentID);
			for (String project : s.getPreferences().keySet()) {
				if (this.projectID.equals(project)
						&& (s.getPreferences().get(projectID) == 3 || s.getPreferences().get(projectID) == 4)) {
					percentage++;
				}
			}
		}

		this.percentage = percentage / 4 * 100;
	}

	public void calculateSDinSkillCompetency() {
		double mean = 0;
		double sd = 0;

		for (String skillCategory : this.average.keySet()) {
			mean += this.average.get(skillCategory);
		}
		mean /= 4;
		for (String skillCategory : this.average.keySet()) {
			sd = sd + Math.pow((this.average.get(skillCategory) - mean), 2);
		}

		sd = Math.pow(sd / 4, 0.5);
		this.standardDeviationInSkillCompetency = sd;
	}
	
	
	public void setAverage(String technicalSkill, double grade) {
		this.average.put(technicalSkill, grade);
	}
	
	public void setShortfall(double sf) {
		this.shortfall = sf;
	}
	
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	public void setTotalAverageCompetencyLevel(double totalAverageCompetencyLevel) {
		this.totalAverageCompetencyLevel = totalAverageCompetencyLevel;
	}
	
	public void standardDeviationforPercentage(double value) {
		this.standardDeviationforPercentage = value;
	}
	

	

	public void checkInvalidMember(String newMember, HashMap<String, TeamMember> team) throws Exception {

		for (String project : team.keySet()) {
			if (project.equals(this.projectID)) {

			} else {
				TeamMember t = team.get(project);
				for (String student : t.getMember().keySet()) {
					if (student.equals(newMember)) {
						throw new InvalidMemberException("The student is already assigned to another project team.");
					}
				}
			}

		}
	}
	
	public boolean checkInvalidMemberSceneBuilder(String newMember, HashMap<String, TeamMember> team) throws Exception  {

		for (String project : team.keySet()) {
			if (project.equals(this.projectID)) {

			} else {
				TeamMember t = team.get(project);
				for (String student : t.getMember().keySet()) {
					if (student.equals(newMember)) {
						return true;
					}
				}
			}

		}
		return false; 
	}

	public void checkRepeatedMember(String newMember) throws StudentConflictException, Exception {
		for (String student : member.keySet()) {
			if (student.equals(newMember))
				throw new RepeatedMemberException("A student is added twice to the same team"); 
		}
	}
	
	public boolean checkRepeatedMemberSceneBuilder(String newMember) throws Exception{
		for (String student : member.keySet()) {
			if (student.equals(newMember)) {
				return true;
			}
		}
		return false;
	}

	public void checkStudentConflict(Student AddNewMember) throws Exception {
		String conflict1, conflict2;
		conflict1 = AddNewMember.getConflict().get(0);
		conflict2 = AddNewMember.getConflict().get(1);

		for (String s : this.member.keySet()) {
			Student stud = this.member.get(s);
			if (stud.getStudentID().equals(conflict1) || stud.getStudentID().equals(conflict1)
					|| stud.getConflict().get(0).equals(AddNewMember.getStudentID())
					|| stud.getConflict().get(1).equals(AddNewMember.getStudentID())) {
				throw new StudentConflictException("students members who have indicated prior conflicts are assigned to the same team");
			}
			
		}
	}
	
	public boolean checkStudentConflictSceneBuilder(Student AddNewMember) throws Exception  {
		String conflict1, conflict2;
		conflict1 = AddNewMember.getConflict().get(0);
		conflict2 = AddNewMember.getConflict().get(1);

		for (String s : this.member.keySet()) {
			Student stud = this.member.get(s);
			if (stud.getStudentID().equals(conflict1) || stud.getStudentID().equals(conflict1)
					|| stud.getConflict().get(0).equals(AddNewMember.getStudentID())
					|| stud.getConflict().get(1).equals(AddNewMember.getStudentID())) {
				return true;
			}
			
		}
		return false;
	}

	public void checkPersonalityIsBalance(Student AddNewMember, String type) throws Exception {
		if (type.equals("type1")) {
			int Atype = 0, Btype = 0, Ctype = 0, Dtype = 0;

			if (AddNewMember.getPersonalityType().equals("A")) {
				Atype++;
			}
			if (AddNewMember.getPersonalityType().equals("B")) {
				Btype++;
			}
			if (AddNewMember.getPersonalityType().equals("C")) {
				Ctype++;
			}
			if (AddNewMember.getPersonalityType().equals("D")) {
				Dtype++;
			}
			for (String s : this.member.keySet()) {

				if (this.member.get(s).getPersonalityType().equals("A")) {
					Atype++;
				}
				if (this.member.get(s).getPersonalityType().equals("B")) {
					Btype++;
				}
				if (this.member.get(s).getPersonalityType().equals("C")) {
					Ctype++;
				}
				if (this.member.get(s).getPersonalityType().equals("D")) {
					Dtype++;
				}

				if (Atype == 3 || Btype == 3 || Ctype == 3 || Dtype == 3) {
					System.out.println(
							"PersonalityImbalance exception: A team is not allowed to have less than three different personality types, "
									+ "so the number of every personality type is at most 2.");
					System.out.println("There are 3 personality type of " + AddNewMember.getPersonalityType());
					throw new PersonalityImbalanceException(" A team has less than three different personality types");
				}
			}
		}

		if (type.equals("type2")) {
			int Atype = 0, Btype = 0, Ctype = 0, Dtype = 0;
			for (String s : this.member.keySet()) {

				if (this.member.get(s).getPersonalityType().equals("A")
						|| AddNewMember.getPersonalityType().equals("A")) {
					Atype = 1;
				}
				if (this.member.get(s).getPersonalityType().equals("B")
						|| AddNewMember.getPersonalityType().equals("B")) {
					Btype = 1;
				}
				if (this.member.get(s).getPersonalityType().equals("C")
						|| AddNewMember.getPersonalityType().equals("C")) {
					Ctype = 1;
				}
				if (this.member.get(s).getPersonalityType().equals("D")
						|| AddNewMember.getPersonalityType().equals("D")) {
					Dtype = 1;

				}
			}

			if ((Atype + Btype + Ctype + Dtype) < 3) {
				System.out.println("PersonalityImbalance exception");
				throw new PersonalityImbalanceException("A team has less than three different personality types");
			}

		}
	}

	

	public boolean checkPersonalityIsBalanceSceneBuilder(Student AddNewMember, String type) throws Exception {
		if (type.equals("type1")) {
			int Atype = 0, Btype = 0, Ctype = 0, Dtype = 0;

			if (AddNewMember.getPersonalityType().equals("A")) {
				Atype++;
			}
			if (AddNewMember.getPersonalityType().equals("B")) {
				Btype++;
			}
			if (AddNewMember.getPersonalityType().equals("C")) {
				Ctype++;
			}
			if (AddNewMember.getPersonalityType().equals("D")) {
				Dtype++;
			}
			for (String s : this.member.keySet()) {

				if (this.member.get(s).getPersonalityType().equals("A")) {
					Atype++;
				}
				if (this.member.get(s).getPersonalityType().equals("B")) {
					Btype++;
				}
				if (this.member.get(s).getPersonalityType().equals("C")) {
					Ctype++;
				}
				if (this.member.get(s).getPersonalityType().equals("D")) {
					Dtype++;
				}

				if (Atype == 3 || Btype == 3 || Ctype == 3 || Dtype == 3) {
					return true;
				}
			}
		}

		if (type.equals("type2")) {
			int Atype = 0, Btype = 0, Ctype = 0, Dtype = 0;
			for (String s : this.member.keySet()) {

				if (this.member.get(s).getPersonalityType().equals("A")
						|| AddNewMember.getPersonalityType().equals("A")) {
					Atype = 1;
				}
				if (this.member.get(s).getPersonalityType().equals("B")
						|| AddNewMember.getPersonalityType().equals("B")) {
					Btype = 1;
				}
				if (this.member.get(s).getPersonalityType().equals("C")
						|| AddNewMember.getPersonalityType().equals("C")) {
					Ctype = 1;
				}
				if (this.member.get(s).getPersonalityType().equals("D")
						|| AddNewMember.getPersonalityType().equals("D")) {
					Dtype = 1;

				}
			}

			if ((Atype + Btype + Ctype + Dtype) < 3) {
				return true;
			}

		}
		return false;
	}

	
	
	public void checkNoLeader() throws Exception {
		boolean Atype = false;
		for (String s : member.keySet()) {
			if (member.get(s).getPersonalityType().equals("A"))
				Atype = true;
		}

		if (Atype) {

		} else {
			throw new NoLeaderException("A team does not have a leader personality type. ");
		}
	}
	
	public boolean checkNoLeaderSceneBuilder(Student stu) throws Exception {
		boolean Atype = false;
		for (String s : member.keySet()) {
			if (member.get(s).getPersonalityType().equals("A"))
				Atype = true;
		}

		if (Atype) {
			return false;
		} else {
			if(stu.getPersonalityType().equals("A")) {
				return false;
			}
			return true;
		}
	}

}



class InvalidMemberException extends Exception{
	public InvalidMemberException(String errorMessage) {
		super(errorMessage);
	}
}

class StudentConflictException extends Exception{
	public StudentConflictException (String errorMessage) {
		super(errorMessage);
	}
}

class RepeatedMemberException extends Exception{
	public RepeatedMemberException (String errorMessage) {
		super(errorMessage);
	}
}

class PersonalityImbalanceException extends Exception{
	public PersonalityImbalanceException(String errorMessage) {
		super(errorMessage);
	}
}

class NoLeaderException extends Exception{
	public NoLeaderException(String errorMessage) {
		super(errorMessage);
	}
}






