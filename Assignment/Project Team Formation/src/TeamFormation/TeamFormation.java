package TeamFormation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class TeamFormation {

	HashMap<String, Student> students = new HashMap<String, Student>();
	HashMap<String, Company> companies = new HashMap<String, Company>();
	HashMap<String, Owner> owners = new HashMap<String, Owner>();
	HashMap<String, Project> projects = new HashMap<String, Project>();	
	HashMap<String, TeamMember> team = new HashMap<String, TeamMember>();

	public HashMap<String, Student> getStudentHM(){
		return students;
	}
	public HashMap<String, TeamMember> getTeamHM(){
		return team;
	}
	public HashMap<String, Project> getProjectHM(){
		return projects;
	}
	
	public HashMap<String, Owner> getOwnerHM(){
		return owners;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, Exception {

		TeamFormation tf = new TeamFormation();
		tf.readCompany("companies.txt");
		tf.readOwner("owners.txt");
		tf.readProject("projects.txt");

		while (true) {
			switch (displayMenu()) {
			case "A":
				tf.addCompany();
				break;
			case "B":
				tf.addOwner();
				break;
			case "C":
				tf.addProject();
				break;
			case "D":
				tf.readStudent("students.txt");
				tf.addPersonalityAndConflicts("students.txt");
				break;
			case "E":
				tf.CaptureStudentPreferences();
				tf.countStudentPreferences();
				break;
			case "F":
				tf.removeProject();
				break;
			case "G":
				tf.FormTeam();
				break;
			case "H":
				tf.DisplayTeamFitnessMetrics();
				break;
			}

		}

	}

	public static String displayMenu() {
		Scanner sc = new Scanner(System.in);
		String input = null;
		System.out.println("******* Menu *******");
		System.out.println("A. Add Company");
		System.out.println("B. Add Project Owner");
		System.out.println("C. Add Project");
		System.out.println("D. Capture Student Personalities and Add Student Personality Type");
		System.out.println("E. Capture Student Preferences");
		System.out.println("F. Shortlist Projects");
		System.out.println("G. Form Team");
		System.out.println("H. Display Team Fitness Metrics");
		System.out.println("*** Notice: Before selecting G, you must select D, E, F.");
		System.out.println("            Before selecting E, you must select D.");
		System.out.println("Enter your choice:");
		input = sc.next();

		while (!(input.equals("A") || input.equals("B") || input.equals("C") || input.equals("D") || input.equals("E")
				|| input.equals("F") || input.equals("G") || input.equals("H"))) {
			System.err.println("Please enter again (A ~ F): ");
			input = sc.next();
		}
		return input;
	}

	public void readCompany(String data) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(data));
		while (sc.hasNextLine()) {
			String[] components = sc.nextLine().split(";");
			this.companies.put(components[0],
					new Company(components[0], components[1], components[2], components[3], components[4]));
		}

	}

	public void readOwner(String data) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(data));
		while (sc.hasNextLine()) {
			String[] components = sc.nextLine().split(";");
			this.owners.put(components[2], new Owner(components[0], components[1], components[2], components[3],
					components[4], components[5]));
		}

	}

	public void readProject(String data) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(data));

		while (sc.hasNextLine()) {
			HashMap<String, String> rankingSkill = new HashMap<String, String>();
			String line1 = sc.nextLine();
			String line2 = sc.nextLine();
			String line3 = sc.nextLine();
			String line4 = sc.nextLine();
			String line5 = sc.nextLine();

			String[] components = line5.split(" ");
			for (int i = 0; i < 8; i++) {
				rankingSkill.put(components[i], components[i + 1]);
				i++;
			}
			this.projects.put(line2, new Project(line1, line2, line3, line4, rankingSkill));
		}
	}

	public void readStudent(String data) throws FileNotFoundException {

		HashMap<String, Integer> grade = new HashMap<String, Integer>();
		Scanner sc = new Scanner(new File(data));

		while (sc.hasNextLine()) {
			String components[] = sc.nextLine().split(" ");
			for (int i = 1; i < components.length; i++) {
				grade.put(components[i], Integer.parseInt((components[i + 1])));
				i++;
			}
			this.students.put(components[0], new Student(components[0], grade));
			grade = new HashMap<String, Integer>();

		}
		System.out.println(this.students);
	}

	public void readStudentinfo(String data) throws Exception {
		Scanner sc = new Scanner(new File(data));

		while (sc.hasNextLine()) {
			HashMap<String, Integer> grade = new HashMap<String, Integer>();
			ArrayList<String> conflict = new ArrayList<String>();

			String components[] = sc.nextLine().split(" ");
			for (int i = 1; i < 9; i++) {
				grade.put(components[i], Integer.parseInt(components[i + 1]));
				i++;
			}

			conflict.add(components[10]);
			conflict.add(components[11]);
			students.put(components[0], new Student(components[0], grade, components[9], conflict));
		}
	}
	
	public void setPreferences(String data) throws Exception{
		Scanner sc = new Scanner(new File(data));
		while (sc.hasNextLine()) {
			String studID3 = sc.nextLine();
			if (sc.hasNextLine()) {
				String preferences = sc.nextLine();
				String[] components = preferences.split(" ");

				HashMap<String, Integer> splitPreferences = new HashMap<String, Integer>();
				for (int j = 0; j < components.length; j++) {

					if (this.projects.containsKey(components[j])) {
						splitPreferences.put(components[j], Integer.parseInt(components[j + 1]));
						j++;
					}
					else
						j++;

				}
				this.students.get(studID3).setPreferences(splitPreferences);

			}
		}
	}

	public void addCompany() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter company ID, name, ABN, URL and address");
		System.out.print("Company ID = ");
		String id = sc.nextLine();
		if (this.companies.containsKey(id)) {
			System.out.println(id + " already exists. The information related to the company will be covered!");
		}

		System.out.print("Company Name = ");
		String name = sc.nextLine();
		System.out.print("ABN Number = ");
		String abn = sc.nextLine();
		System.out.print("Company URL = ");
		String url = sc.nextLine();
		System.out.print("Company Address = ");
		String address = sc.nextLine();

		this.companies.put(id, new Company(id, name, abn, url, address));
		try {
			PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("companies.txt", true)));
			p.println(id + ";" + name + ";" + abn + ";" + url + ";" + address);
			System.out.println("The details related to the company is saved to file companies.txt\n");
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void addOwner() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter first name, surname, owner ID, role, email, company ID");
		System.out.print("First Name = ");
		String firstName = sc.nextLine();
		System.out.print("Surname = ");
		String surName = sc.nextLine();
		System.out.print("OwnerID = ");
		String ownerID = sc.nextLine();

		if (this.owners.containsKey(ownerID)) {
			System.out.println(ownerID + " already exists. The information related to the owner will be covered!");
		}

		System.out.print("Role = ");
		String role = sc.nextLine();
		System.out.print("Email = ");
		String email = sc.nextLine();

		System.out.print("Company ID = ");
		String companyID = sc.nextLine();
		if ((this.companies.containsKey(companyID))) {
			this.owners.put(ownerID, new Owner(firstName, surName, ownerID, role, email, companyID));
			try {
				PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("owners.txt", true)));
				p.println(firstName + ";" + surName + ";" + ownerID + ";" + role + ";" + email + ";" + companyID);
				System.out.println("The details related to the project owner is saved to file owners.txt\n");
				p.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else
			System.out.println(companyID + " does not exist in Company object. Fail to add project owner!");

	}

	public void addProject() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter project title, ID, owner ID, role, email, company ID");

		System.out.print("Project Title = ");
		String title = sc.nextLine();
		System.out.print("Project ID = ");
		String projectID = sc.nextLine();

		if (this.projects.containsKey(projectID)) {
			System.out.println(projectID + " already exists. The information related to the project will be covered!");
		}

		System.out.print("Description = ");
		String description = sc.nextLine();

		System.out.print("Project Owner ID = ");
		String projectOwnerID = sc.nextLine();

		if (this.owners.containsKey(projectOwnerID)) {

			boolean valid = true;
			HashMap<String, String> rankingSkill = new HashMap<String, String>();
			String components[] = null;
			while (valid) {
				System.out.print("Ranking of Skill (e.g. W 4 N 3 P 2 A 1, from top to down) = ");
				components = sc.nextLine().split(" ");

				for (int i = 0; i < components.length; i++) {
					if (!(components[i].equals("W") || components[i].equals("N") || components[i].equals("P")
							|| components[i].equals("A"))) {
						System.out.println("The format is incorrect. Please enter again.");
						valid = true;
						break;
					}
					if (!(components[1].equals("4") || components[3].equals("3") || components[5].equals("2")
							|| components[7].equals("1"))) {
						System.out
								.println("The ranking is between 1 and 4 and is from top to down. Please enter again.");
						valid = true;
						break;
					}
					i++;
					valid = false;
				}

			}
			for (int i = 0; i < components.length; i++) {
				rankingSkill.put(components[i], components[i + 1]);
				i++;
			}

			this.projects.put(projectID, new Project(title, projectID, description, projectOwnerID, rankingSkill));

			try {
				PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("projects.txt", true)));
				p.println(title + "\n" + projectID + "\n" + description + "\n" + projectOwnerID + "\n" + rankingSkill);
				System.out.println("The details related to the project is saved to projects.txt");
				p.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(projectOwnerID + " does not exist in Owner object. Fail to add project!");
		}
	}

	public void addPersonalityAndConflicts(String data) throws FileNotFoundException, IOException, Exception {

		HashMap<String, Integer> grade = new HashMap<String, Integer>();
		Scanner sc = new Scanner(new File(data));
		String s = null;
		while (sc.hasNextLine()) {
			s = sc.nextLine();
			System.out.print("s: " + s);
			String components[] = s.split(" ");
			for (int i = 1; i < components.length; i++) {
				grade.put(components[i], Integer.parseInt((components[i + 1])));
				i++;
			}
			this.students.put(components[0], new Student(components[0], grade));
			grade = new HashMap<String, Integer>();

		}
		System.out.println(this.students);
		System.out.println("Student data is serialized.\n");

		int countA = 0, countB = 0, countC = 0, countD = 0;
		System.out.print("Who would you like to assign the personality types?\nPlease enter the student ID: ");
		sc = new Scanner(System.in);
		String studID = sc.nextLine();
		while (!(this.students.containsKey(studID))) {
			System.out.print(studID + " does not exist in Student object.\nPlease enter again: ");
			studID = sc.nextLine();

		}

		System.out.println(
				studID + " student information as follow:\n" + studID + this.students.get(studID).getGrade() + "\n");
		String[] str;
		sc = new Scanner(new File("studentinfo.txt"));
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			str = line.split(" ");
			this.students.get(str[0]).setPersonalityType(str[9]);

			ArrayList<String> conflict = new ArrayList<String>();
			conflict.add(str[10]);
			conflict.add(str[11]);

			this.students.get(str[0]).setConflict(conflict);
		}

		Iterator it = this.students.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String personality = this.students.get(key).getPersonalityType();
			if (personality != null) {
				switch (personality) {
				case "A":
					countA++;
					break;
				case "B":
					countB++;
					break;
				case "C":
					countC++;
					break;
				case "D":
					countD++;
					break;
				default:
					break;
				}
			}
		}

		System.out.print("The number of personality type: ");
		System.out.println("A = " + countA + " B = " + countB + " C =" + countC + " D = " + countD + " (at most "
				+ this.students.size() / 4 + ")");
		System.out.print("Please enter " + studID + " student's personality type(A, B, C, D): ");
		sc = new Scanner(System.in);
		String personalityType;
		do {
			personalityType = sc.next();
			switch (personalityType) {
			case "A":
				if (countA >= (this.students.size() / 4)) {
					printCount(countA);
					personalityType = "Z";
				} else
					countA++;
				break;

			case "B":
				if (countB >= (this.students.size() / 4)) {
					printCount(countB);
					personalityType = "Z";
				} else
					countB++;
				break;

			case "C":
				if (countC >= (this.students.size() / 4)) {
					printCount(countC);
					personalityType = "Z";
				} else
					countC++;
				break;

			case "D":
				if (countD >= (this.students.size() / 4)) {
					printCount(countD);
					personalityType = "Z";
				} else
					countD++;
				break;

			default:
				System.err.println("Error Input!!!\nPlease enter student's personality type(A, B, C, D): ");
				break;

			}

		} while (!(personalityType.equals("A") || personalityType.equals("B") || personalityType.equals("C")
				|| personalityType.equals("D")));

		System.out.println("Please enter 2 student IDs that had prior conflict");
		String whitespace = sc.nextLine();

		String conflict = sc.nextLine();
		String components[] = conflict.split(" ");

		String filename = "studentinfo.txt";

		FileWriter fw = new FileWriter(filename, true);
		BufferedWriter out = new BufferedWriter(fw);

		String combineString = "";

		int score1 = this.students.get(studID).getGrade().get("P");
		combineString = combineString + "P " + String.valueOf(score1) + " ";
		int score2 = this.students.get(studID).getGrade().get("N");
		combineString = combineString + "N " + String.valueOf(score2) + " ";
		int score3 = this.students.get(studID).getGrade().get("A");
		combineString = combineString + "A " + String.valueOf(score3) + " ";
		int score4 = this.students.get(studID).getGrade().get("W");
		combineString = combineString + "W " + String.valueOf(score4);

		fw.write(studID + " " + combineString + " " + personalityType + " " + conflict + "\n");

		fw.close();

		System.out.println(studID + " " + combineString + " " + personalityType + " " + conflict);
		System.err.println("Successfully wrote to the " + filename);

		this.students.get(studID).setPersonalityType(personalityType);

		ArrayList<String> conf = new ArrayList<String>();
		conf.add(components[0]);
		conf.add(components[1]);
		this.students.get(studID).setConflict(conf);

		System.out.println(students);

	}

	public void CaptureStudentPreferences() throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Add studnt's preference...\nPlease enter student ID and preference.\nStudent ID = ");
		String studID = sc.nextLine();

		while (!(this.students.containsKey(studID))) {
			System.err.print(studID + " does not exist. Please enter student ID again, or enter E to exit. ");
			studID = sc.nextLine();
			if (studID.equals("E")) {
				System.exit(0);
			}
		}

		System.out.println(
				"Please enter preference(e.g.Pr6 2 Pr4 1 Pr3 2, with 4 for most preferred and 1 for the least): ");
		String preferences = sc.nextLine();

		boolean projectExist = true;
		String[] identifyProject = preferences.split(" ");
		for (int i = 0; i < identifyProject.length; i++) {
			if (this.projects.containsKey(identifyProject[i])
					&& (identifyProject[i + 1].equals("1") || identifyProject[i + 1].equals("2")
							|| identifyProject[i + 1].equals("3") || identifyProject[i + 1].equals("4"))) {
				i++;
			} 
			else if (!(this.projects.containsKey(identifyProject[i]))) {
				System.err.println(identifyProject[i] + " does not exist. Fail to add preferences!");
				projectExist = false;
				break;
			} else if (!(identifyProject[i + 1].equals("1") || identifyProject[i + 1].equals("2")
					|| identifyProject[i].equals("3") || identifyProject[i + 1].equals("4"))) {
				System.err.println("The score of preferences should be between 1 and 4. Fail to add preferences!");
				projectExist = false;
				break;

			}
		} 

		if (projectExist) {

			String filename2 = "preferences.txt";
			try {
				FileWriter fw = new FileWriter(filename2, true);
				BufferedWriter out = new BufferedWriter(fw);
				fw.write(studID + "\n" + preferences + "\n");
				fw.close();

				System.out.println("\n" + studID + "\n" + preferences);
				System.out.println("Successfully wrote to the " + filename2);
				System.out.println();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (NumberFormatException nfm) {
				nfm.printStackTrace();
			}

			sc = new Scanner(new File("preferences.txt"));
			while (sc.hasNextLine()) {
				String studID3 = sc.nextLine();
				if (sc.hasNextLine()) {
					preferences = sc.nextLine();
					String[] components = preferences.split(" ");

					HashMap<String, Integer> splitPreferences = new HashMap<String, Integer>();
					for (int j = 0; j < components.length; j++) {
						splitPreferences.put(components[j], Integer.parseInt(components[j + 1]));
						j++;
					}
					this.students.get(studID3).setPreferences(splitPreferences);

				}
			}

		}
	}

	public void countStudentPreferences() {
		for (String s : this.students.keySet()) {
			Student st = this.students.get(s);
			System.out.println("\nstudent info: " + st);
			for (String s2 : st.getPreferences().keySet()) {
				int preferenceGrade = st.getPreferences().get(s2);
				System.out.println("key: " + s2 + ", adding value: " + preferenceGrade);

				if (this.projects.containsKey(s2)) { 
					System.out.print("Before: " + this.projects.get(s2).getPreferencesCount());
					this.projects.get(s2).addPreferencesCount(preferenceGrade);
					System.out.println(". After adding " + preferenceGrade + ", it's "
							+ this.projects.get(s2).getPreferencesCount());
				}
			}

		}
		System.out.println("\nproject info " + projects);

	}

	public void removeProject() {
		System.out.println("Before selecting 'F', you should select D and E first.");

		for (int i = 0; i < 5; i++) {
			int minCount = 1000000;
			String minKey = "";
			for (String s : this.projects.keySet()) {
				Project p = this.projects.get(s);
				if (minCount > p.getPreferencesCount()) {
					minCount = p.getPreferencesCount();
					minKey = s;
				}

			}

			System.out.println("minKey: " + minKey + ", minCount: " + minCount);
			Project removeP = (Project) this.projects.get(minKey);
			this.projects.remove(minKey, removeP);
			if (this.projects.containsKey(minKey)) {
				System.out.print(minKey + "do exists");
			} else
				System.out.println(minKey + " is removed.");
		}

		System.out.println("The project info:\n" + projects);
	}
		
	public void mactching(String project) throws Exception{
		Scanner sc = new Scanner(System.in);
		String rankingIs4 = "", rankingIs3 = "", rankingIs2 = "", rankingIs1 = "";
				
		this.readStudentinfo("studentinfo.txt");
		this.readProject("projects.txt");
		this.setPreferences("preferences.txt");
		
		String projectID=project;
		HashMap<String, String> ranking = this.projects.get(projectID).getRankingSkill();
		for(String s: ranking.keySet()) {
			if(ranking.get(s).equals("4")) {
				rankingIs4 = s;
			}
			if(ranking.get(s).equals("3")) {
				rankingIs3 = s;
			}
			if(ranking.get(s).equals("2")) {
				rankingIs2 = s;
			}
			if(ranking.get(s).equals("1")) {
				rankingIs1 = s;
			}
		}
		
		ArrayList<String> matchingRanking4 = new ArrayList<String>();
		ArrayList<String> matchingRanking3 = new ArrayList<String>();
		ArrayList<String> matchingByRanking = new ArrayList<String>();
		for(String s: this.students.keySet()) {
			Student stud = this.students.get(s);
			for (String t : stud.getGrade().keySet()) {	
				if((t.equals(rankingIs4) && (stud.getGrade().get(t) == 4)) ) {
					matchingRanking4.add(s);
				}
				if(t.equals(rankingIs3) && (stud.getGrade().get(t)== 4 || stud.getGrade().get(t)== 3)  ) {
					matchingRanking3.add(s);
				}
			}
		}
		
		for(String s: matchingRanking4) {
			for(String t:matchingRanking3) {
				if(s.equals(t)) {
					matchingByRanking.add(t);
				}
			}
		}
		if(matchingByRanking.isEmpty()) {
			matchingByRanking= matchingRanking4;
		}
		
		
		ArrayList<String> matchingByPreferences = new ArrayList<String>();
		for(String s: this.students.keySet()) {
			Student stud = this.students.get(s);
			for(String t: stud.getPreferences().keySet()) {
				if(t.equals(project) && (stud.getPreferences().get(t) == 3 || stud.getPreferences().get(t) == 4)  ) {
					matchingByPreferences.add(s);
				}
			}
		}
		
		System.out.println("\n"+projectID+" Database Management System");
		
		System.out.print("Top matching students based on preferences: "); 
		for(String s:matchingByPreferences) {
			System.out.print(s+ " ");
		}
		System.out.print("\nTop matching students based on skillset rank: "); 
		for(String s:matchingByRanking) {
			System.out.print(s+ " ");
		}
		System.out.println();
		
	}
	
	public void DisplayTeamFitnessMetrics() throws Exception {

		System.out.println("Before displaying Team Fitness Metrics, all the teams are formed and the preferences.txt is completed.\n");
		
		this.readProject("projects.txt");
		this.readStudentinfo("studentinfo.txt");
		this.setPreferences("preferences.txt");
		
		HashMap<String,Student> members = new HashMap<String,Student>();
		members.put("S1", this.students.get("S1")); members.put("S2", this.students.get("S2")); members.put("S20", this.students.get("S20"));members.put("S10", this.students.get("S10"));
		team.put("Pr1", new TeamMember("Pr1",members));
		
		members = new HashMap<String,Student>();
		members.put("S5", this.students.get("S5")); members.put("S3", this.students.get("S3")); members.put("S6", this.students.get("S6"));members.put("S8", this.students.get("S8"));
		team.put("Pr2", new TeamMember("Pr2",members));
		
		members = new HashMap<String,Student>();
		members.put("S17", this.students.get("S17")); members.put("S19", this.students.get("S19")); members.put("S16", this.students.get("S16"));members.put("S11", this.students.get("S11"));
		team.put("Pr3", new TeamMember("Pr3",members));
		
		members = new HashMap<String,Student>();
		members.put("S13", this.students.get("S13")); members.put("S4", this.students.get("S4")); members.put("S15", this.students.get("S15"));members.put("S18", this.students.get("S18"));
		team.put("Pr4", new TeamMember("Pr4",members));
		
		members = new HashMap<String,Student>();
		members.put("S9", this.students.get("S9")); members.put("S7", this.students.get("S7")); members.put("S14", this.students.get("S14"));members.put("S12", this.students.get("S12"));
		team.put("Pr5", new TeamMember("Pr5",members));
		
		
		ArrayList<String> storeProject =new ArrayList<String>();
		
		for(String s : team.keySet()) {
			storeProject.add(s);
		}
	
		for(int i = 0;i<storeProject.size();i++) {
			team.get(storeProject.get(i)).averageSkillCompetency();
		}

		for (int i = 0; i < storeProject.size(); i++) {
			team.get(storeProject.get(i)).CalculatePercentage();
		}
		
		for (int i = 0; i < storeProject.size(); i++) {
			team.get(storeProject.get(i)).shortfall(this.projects);
		}

		for (String projectID : this.team.keySet()) {
			this.team.get(projectID).calculateSDinSkillCompetency();
		}
		
		System.out.print("\n\nTeam Fitness Metrics\n" + team);
		
		System.out.println(
				"\nStandard deviation for percentage of project members getting first and second project preferences: "
						+ this.calculateSDforPercentage());

		System.out.println("Standard deviation of shortfall: " + this.calculateSDofShortfall());
	}

	public void FormTeam() throws Exception {

		this.readStudentinfo("studentinfo.txt");
		this.readProject("projects.txt");

		String project;
		ArrayList<String> Atype = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);

		boolean ProjectNoExist = true;
		System.out.print("Forming team ... Please enter the project ID, including ");
		for (String s : this.projects.keySet()) {
			System.out.print(s + ", ");
		}
		System.out.print(":");
		do {
			project = sc.next();
			if(this.projects.containsKey(project)) {
				ProjectNoExist = false;
				this.mactching(project);
			}
			else
				System.out.print(project+" does not exist. Please enter project ID again: ");
			
		} while (ProjectNoExist);
		
		
		this.team.put(project, new TeamMember(project));
		this.printType();
		
		System.out.println("\nWhich student you want to add?");
		String member1 = sc.next();
		
		this.team.get(project).checkInvalidMember(member1, this.team);	
		this.team.get(project).checkRepeatedMember(member1);
		this.team.get(project).checkStudentConflict(this.students.get(member1));
		this.team.get(project).addMember(this.students.get(member1));
		
		System.out.println("\nWhich student you want to add?");
		String member2 = sc.next();
		this.team.get(project).checkInvalidMember(member2, this.team);
		this.team.get(project).checkRepeatedMember(member2);
		this.team.get(project).checkStudentConflict(this.students.get(member2));
		this.team.get(project).addMember(this.students.get(member2));
		
		System.out.println("\nWhich student you want to add?");
		String member3 = sc.next();
		this.team.get(project).checkInvalidMember(member3, this.team);
		this.team.get(project).checkRepeatedMember(member3);
		this.team.get(project).checkStudentConflict(this.students.get(member3));
		this.team.get(project).checkPersonalityIsBalance(this.students.get(member3), "type1");
		this.team.get(project).addMember(this.students.get(member3));
		
		System.out.println("\nWhich student you want to add?");
		String member4 = sc.next();
		this.team.get(project).checkInvalidMember(member4, this.team);
		this.team.get(project).checkRepeatedMember(member4);
		this.team.get(project).checkStudentConflict(this.students.get(member4));
		this.team.get(project).checkPersonalityIsBalance(this.students.get(member4), "type2");
		this.team.get(project).checkNoLeader();
		this.team.get(project).addMember(this.students.get(member4));
		
		for(String stud : this.team.keySet()) {
			System.out.print(stud+" ");
		}
		System.out.println("is assigned to "+project+ " project team\n");
	}



	public void save() {
		try {
			ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream("out.ser"));
			oOut.writeObject(this.companies);
			oOut.writeObject(this.owners);
			oOut.writeObject(this.projects);
			oOut.writeObject(this.students);
			oOut.writeObject(this.team);
			oOut.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void readStream() throws ClassNotFoundException, IOException {

		ObjectInputStream oIn = new ObjectInputStream(new FileInputStream("out.ser"));

		this.companies = (HashMap<String, Company>) oIn.readObject();
		this.owners = (HashMap<String, Owner>) oIn.readObject();
		this.projects = (HashMap<String, Project>) oIn.readObject();
		this.students = (HashMap<String, Student>) oIn.readObject();
		this.team = (HashMap<String, TeamMember>) oIn.readObject();

		System.out.println(this.companies);
		System.out.println(this.owners);
		System.out.println(this.projects);
		System.out.println(this.students);
	}

	public static void printCount(int count) {
		System.out.println("The number of personality type is " + count + ", which is not allowed to enter."
				+ "\nPlease enter another personality type:¡@");
	}
	
	public static ArrayList<String> addConflictsWithoutDuplicate(HashMap<String, Student> st, ArrayList<String> peopleWithConflicts, String mem) {
		if (peopleWithConflicts.isEmpty()) { 
			peopleWithConflicts.add(st.get(mem).getConflict().get(0)); 
			peopleWithConflicts.add(st.get(mem).getConflict().get(1)); 
		} else {
			if (peopleWithConflicts.contains(st.get(mem).getConflict().get(0))) {

			} else
				peopleWithConflicts.add(st.get(mem).getConflict().get(0));
			if (peopleWithConflicts.contains(st.get(mem).getConflict().get(1))) {

			} else
				peopleWithConflicts.add(st.get(mem).getConflict().get(1));
		}
		return peopleWithConflicts;
	}

	public double calculateSDforPercentage() {
		double SDpercentage =0;
		double mean = 0;
		
		for(String projectID : this.team.keySet()) {
			mean += this.team.get(projectID).getPercentage()/100;
		}
		mean/=5;
		for(String projectID : this.team.keySet()) {
			SDpercentage = SDpercentage + Math.pow(this.team.get(projectID).getPercentage()/100 - mean, 2);
		}
		SDpercentage = Math.pow(SDpercentage / 5, 0.5);
		return SDpercentage;
	}
	
	public double calculateSDofShortfall() {
		double SDshortfall =0;
		double mean = 0;
		
		for(String projectID : this.team.keySet()) {
			mean += this.team.get(projectID).getShortfall();
		}	
		mean/=5;
		for(String projectID : this.team.keySet()) {
			SDshortfall = SDshortfall + Math.pow(this.team.get(projectID).getShortfall() - mean, 2);
		}
		SDshortfall = Math.pow(SDshortfall /5, 0.5);
		return SDshortfall;
	}
	
	public void printType() {
		ArrayList<String> typeA = new ArrayList<String>();
		ArrayList<String> typeB = new ArrayList<String>();
		ArrayList<String> typeC = new ArrayList<String>();
		ArrayList<String> typeD = new ArrayList<String>();
		
		for(String s : this.students.keySet()) {
			Student studentID = this.students.get(s);
			if(studentID.getPersonalityType().equals("A")) {
				typeA.add(studentID.getStudentID());
			}
			if(studentID.getPersonalityType().equals("B")) {
				typeB.add(studentID.getStudentID());
			}
			if(studentID.getPersonalityType().equals("C")) {
				typeC.add(studentID.getStudentID());
			}
			if(studentID.getPersonalityType().equals("D")) {
				typeD.add(studentID.getStudentID());
			}
		}
		
		System.out.println("\nThe personality type of students: ");
		System.out.print("A type: ");
		for(String s:typeA) {
			System.out.print(s+" ");
		}
		System.out.print("\nB type: ");
		for(String s:typeB) {
			System.out.print(s+" ");
		}
		System.out.print("\nC type: ");
		for(String s:typeC) {
			System.out.print(s+" ");
		}
		System.out.print("\nD type: ");
		for(String s:typeD) {
			System.out.print(s+" ");
		}
		System.out.println();
	}
	
	
	

}
