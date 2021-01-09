package model;
import java.io.FileNotFoundException;
import java.util.*;

import ManageStudent.*;
import TeamFormation.*;
import database.*;


public class TeamList {
   private TeamController control = null;
   private TeamFormation teamformation = new TeamFormation();
   
   public TeamList() throws Exception
   {	   
	  teamformation.readCompany("companies.txt");
	  
	  DeleteTable.deleteOwnerTable();
	  DeleteTable.deleteProjectTable();
	  DeleteTable.deleteStudentTable(); 
	  DeleteTable.deleteTeamTable();
	  
	  
	  teamformation.readOwner("owners.txt"); 
	  CreateTable.createOwnerTable();
	  InsertMultipleRows.insertOwner(teamformation.getOwnerHM());

	  teamformation.readProject("projects.txt");
	  CreateTable.createProjectTable();
	  InsertMultipleRows.insertProject(teamformation.getProjectHM());
	  
	  teamformation.readStudentinfo("studentinfo.txt");
	  CreateTable.createStudentTable();
	  InsertMultipleRows.insertStudent(teamformation.getStudentHM());
	  
	  CreateTable.createTeamTable();
	  
	  teamformation.setPreferences("preferences.txt");
	  
	  HashMap<String, Student> teamMembers1 = new HashMap<String, Student>();
	  HashMap<String, Student> teamMembers2 = new HashMap<String, Student>();
	  HashMap<String, Student> teamMembers3 = new HashMap<String, Student>();
	  HashMap<String, Student> teamMembers4 = new HashMap<String, Student>();
	  HashMap<String, Student> teamMembers5 = new HashMap<String, Student>();
	  
	  put("Pr1", new TeamMember("Pr1", teamMembers1));
	  put("Pr2", new TeamMember("Pr2", teamMembers2));
	  put("Pr3", new TeamMember("Pr3", teamMembers3));
	  put("Pr4", new TeamMember("Pr4", teamMembers4));
	  put("Pr5", new TeamMember("Pr5", teamMembers5));
	  
   }
   
   public void put(String projectID, TeamMember tm) throws Exception {
		teamformation.getTeamHM().put(projectID, tm);
		if (control != null)
			control.update();
	}
   
   public TeamFormation getTeamFormation() {
	   return teamformation;
   }

   
	public double getPreferenceOf1stAnd2nd(String projectID) {
		
		double numberOFMember = teamformation.getTeamHM().get(projectID).getMember().size();
		double count = 0;

		if (teamformation.getTeamHM().get(projectID).getMember().size() > 0) {
			for (String studentID : teamformation.getTeamHM().get(projectID).getMember().keySet()) {
				Student s = teamformation.getTeamHM().get(projectID).getMember().get(studentID);
				if (s.getPreferences().get(projectID) != null) {
					if (s.getPreferences().get(projectID) >= 3) {
						count++;
					}
				}
			}
			teamformation.getTeamHM().get(projectID).setPercentage(count / numberOFMember * 100);
			return count / numberOFMember * 100;
		}
		else 
			return 0;
		
		
		
	}
	
	public double getAverageCompetencyLevel(String projectID) {

		double numberOFMember = teamformation.getTeamHM().get(projectID).getMember().size();
		double SumCompetencyForP = 0, SumCompetencyForN = 0, SumCompetencyForA = 0, SumCompetencyForW = 0;
		double totalCompetencyLevel = 0;

		
		if (teamformation.getTeamHM().get(projectID).getMember().size() > 0 ) {

			for (String studentID : teamformation.getTeamHM().get(projectID).getMember().keySet()) {
				Student s = teamformation.getTeamHM().get(projectID).getMember().get(studentID);
				SumCompetencyForP += s.getGrade().get("P");
				SumCompetencyForN += s.getGrade().get("N");
				SumCompetencyForA += s.getGrade().get("A");
				SumCompetencyForW += s.getGrade().get("W");
				
			}

			
			double AverageCompetencyForP = SumCompetencyForP / numberOFMember;
			double AverageCompetencyForN = SumCompetencyForN / numberOFMember;
			double AverageCompetencyForA = SumCompetencyForA / numberOFMember;
			double AverageCompetencyForW = SumCompetencyForW / numberOFMember;
	
			teamformation.getTeamHM().get(projectID).setAverage("P", AverageCompetencyForP);
			teamformation.getTeamHM().get(projectID).setAverage("N", AverageCompetencyForN);
			teamformation.getTeamHM().get(projectID).setAverage("A", AverageCompetencyForA);
			teamformation.getTeamHM().get(projectID).setAverage("W", AverageCompetencyForW);
			
			totalCompetencyLevel = AverageCompetencyForP + AverageCompetencyForN
					+ AverageCompetencyForA + AverageCompetencyForW;
			
			
			teamformation.getTeamHM().get(projectID).setTotalAverageCompetencyLevel(totalCompetencyLevel/4);
			return totalCompetencyLevel/4;
		} 
		else
			return 0;

	}
	
	
	public double getSkillGaps(String projectID) {
		
		double shortfall = 0, ProjectMinusTeam = 0;
		
		Project p = teamformation.getProjectHM().get(projectID);
		
		if (teamformation.getTeamHM().get(projectID).getMember().size() > 0) {

			for (String rankingCategory : teamformation.getProjectHM().get(projectID).getRankingSkill().keySet()) {
				if (rankingCategory.equals("P")) {
					ProjectMinusTeam = Integer.parseInt(p.getRankingSkill().get("P"))
							- teamformation.getTeamHM().get(projectID).getAverage().get("P");
					if (ProjectMinusTeam < 0) {
						ProjectMinusTeam = 0;
					}
					shortfall += ProjectMinusTeam;
				}

				if (rankingCategory.equals("N")) {
					ProjectMinusTeam = Integer.parseInt(p.getRankingSkill().get("N"))
							- teamformation.getTeamHM().get(projectID).getAverage().get("N");
					if (ProjectMinusTeam < 0) {
						ProjectMinusTeam = 0;
					}
					shortfall += ProjectMinusTeam;	
				}

				if (rankingCategory.equals("A")) {
					ProjectMinusTeam = Integer.parseInt(p.getRankingSkill().get("A"))
							- teamformation.getTeamHM().get(projectID).getAverage().get("A");
					if (ProjectMinusTeam < 0) {
						ProjectMinusTeam = 0;
					}
					shortfall += ProjectMinusTeam;
				}

				if (rankingCategory.equals("W")) {
					ProjectMinusTeam = Integer.parseInt(p.getRankingSkill().get("W"))
							- teamformation.getTeamHM().get(projectID).getAverage().get("W");
					if (ProjectMinusTeam < 0) {
						ProjectMinusTeam = 0;
					}
					shortfall += ProjectMinusTeam;
				}

			}
			
			teamformation.getTeamHM().get(projectID).setShortfall(shortfall);
			return shortfall;
		}
		else
			return 0;

		
	}
   
	
	public double getStandardDeviationForPercentage() {

		double standardDeviationPercentage = 0;
		double mean = 0;
		double countTeam = 0;

	
		for (String projectID : teamformation.getTeamHM().keySet()) {
			if (teamformation.getTeamHM().get(projectID).getMember().size() > 0) {
				countTeam++;
			}
		}

		for (String projectID : teamformation.getTeamHM().keySet()) {

			mean += teamformation.getTeamHM().get(projectID).getPercentage() / 100;
//			numberOFMember = teamformation.getTeamHM().get(projectID).getMember().size();
		}

		mean /= countTeam;
//		mean /= 5;

		for (String projectID : teamformation.getTeamHM().keySet()) {
			standardDeviationPercentage = standardDeviationPercentage
					+ Math.pow(teamformation.getTeamHM().get(projectID).getPercentage() / 100 - mean, 2);
		}

		standardDeviationPercentage = Math.pow(standardDeviationPercentage / countTeam, 0.5);	
		return standardDeviationPercentage;
	}
	
	
	
	public double getStandardDeviationForSkillGap() {

		double standardDeviationSkillGap = 0;
		double mean = 0;
		double countTeam = 0;
		
		
		for (String projectID : teamformation.getTeamHM().keySet()) {
			if (teamformation.getTeamHM().get(projectID).getMember().size() > 0) {
				countTeam++;
			}
		}

		for (String projectID : teamformation.getTeamHM().keySet()) {

			mean += teamformation.getTeamHM().get(projectID).getShortfall();
//			numberOFMember = teamformation.getTeamHM().get(projectID).getMember().size();
		}

		mean /= countTeam;
//		mean /= 5;

		for (String projectID : teamformation.getTeamHM().keySet()) {
			standardDeviationSkillGap = standardDeviationSkillGap
					+ Math.pow(teamformation.getTeamHM().get(projectID).getShortfall() - mean, 2);
		}

		standardDeviationSkillGap = Math.pow(standardDeviationSkillGap / countTeam, 0.5);
//		standardDeviationSkillGap = Math.pow(standardDeviationSkillGap / 5, 0.5);
		return standardDeviationSkillGap;
	}
	
	
	public double getStandardDeviationForAverageCompetencyLevel() {

		double standardDeviationAverageCompetencyLevel = 0;
		double mean = 0;
		double countTeam = 0;

		
		
		for (String projectID : teamformation.getTeamHM().keySet()) {
			if (teamformation.getTeamHM().get(projectID).getMember().size() > 0) {
				countTeam++;
			}
		}

		for (String projectID : teamformation.getTeamHM().keySet()) {

			mean += teamformation.getTeamHM().get(projectID).getTotalAverageCompetencyLevel();
			
//			System.out.println("project: "+ projectID+" : "+teamformation.getTeamHM().get(projectID).getTotalAverageCompetencyLevel());
			
//			numberOFMember = teamformation.getTeamHM().get(projectID).getMember().size();
		}

		mean /= countTeam;
//		mean /= 5;

		for (String projectID : teamformation.getTeamHM().keySet()) {
			standardDeviationAverageCompetencyLevel = standardDeviationAverageCompetencyLevel
					+ Math.pow(teamformation.getTeamHM().get(projectID).getTotalAverageCompetencyLevel() - mean, 2);
			
//			System.out.print("Math.pow(teamformation.getTeamHM().get(projectID).getTotalAverageCompetencyLevel(): ");
//			System.out.print(teamformation.getTeamHM().get(projectID).getTotalAverageCompetencyLevel());
//			System.out.print(", "+ "mean: " + mean);
//			
//			System.out.println("Math.pow(teamformation.getTeamHM().get("+projectID+").getTotalAverageCompetencyLevel() - mean, 2): "+Math.pow(teamformation.getTeamHM().get(projectID).getTotalAverageCompetencyLevel() - mean, 2));
		}
		
//		System.out.println("standardDeviationAverageCompetencyLevel: "+standardDeviationAverageCompetencyLevel);

		standardDeviationAverageCompetencyLevel = Math.pow(standardDeviationAverageCompetencyLevel / countTeam, 0.5);
//		standardDeviationSkillGap = Math.pow(standardDeviationSkillGap / 5, 0.5);
		return standardDeviationAverageCompetencyLevel;
	}
	

   
   public void setController(TeamController control) // A handle to the controller
   {	   
	   this.control = control;
   }
   
 
   
}