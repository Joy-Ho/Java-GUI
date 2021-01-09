package TeamFormation;

import java.util.HashMap;

public class Project {

	private String title;
	private String projectID;
	private String description;
	private String ownerID;
	private HashMap<String, String> rankingSkill = new HashMap<String, String>();
	private int preferencesCount;

	Project(String title, String projectID, String description, String ownerID, HashMap<String, String> ranking) {
		this.title = title;
		this.projectID = projectID;
		this.description = description;
		this.ownerID = ownerID;
		this.rankingSkill = ranking;
		this.preferencesCount = 0;
	}

	public String getTitle() {
		return title;
	}

	public String getProjectID() {
		return projectID;
	}

	public String getDescription() {
		return description;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public HashMap<String, String> getRankingSkill() {
		return rankingSkill;
	}

	public String toString() {
		return new String(
				" Title: " + title + " ProjectID: " + projectID + " Description: " + description + " OwnerID:¡@"
						+ ownerID + " RankingSkill:" + rankingSkill + " PreferencesCount: " + preferencesCount + "\n");
	}

	public int getPreferencesCount() {
		return preferencesCount;
	}

	public void addPreferencesCount(int score) {
		this.preferencesCount = this.preferencesCount + score;
	}

}
