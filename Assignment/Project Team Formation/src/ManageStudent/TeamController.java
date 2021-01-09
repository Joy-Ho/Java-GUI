package ManageStudent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;


import TeamFormation.*;
import Undo.Action;
import Undo.CAction;
import Undo.CommandManager;
import model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import database.*;
import database.*;

public class TeamController {
	private TeamList tl;
	private String selectStudent = null;
	private String teamProjectID = null;
	
	CommandManager manager = CommandManager.getInstance();
	List<Action> actionList = new ArrayList<>();

	@FXML
	private BarChart<?, ?> bc1;
	@FXML
	private CategoryAxis xaxis1;
	@FXML
	private NumberAxis yaxis1;
	@FXML
	private BarChart<?, ?> bc2;
	@FXML
	private CategoryAxis xaxis2;
	@FXML
	private NumberAxis yaxis2;
	@FXML
	private BarChart<?, ?> bc3;
    @FXML
    private CategoryAxis xaxis3;
    @FXML
    private NumberAxis yaxis3;
	@FXML
	private Label labOverall;
    @FXML
    private TextField EnterStudentID;
    @FXML
    private TextField Team1Member1;
    @FXML
    private TextField Team1Member2;
    @FXML
    private TextField Team1Member3;
    @FXML
    private TextField Team1Member4;
    @FXML
    private Label getTeam1ProjectID;
    @FXML
    private Label getTeam2ProjectID;
    @FXML
    private TextField Team2Member1;
    @FXML
    private TextField Team2Member2;
    @FXML
    private TextField Team2Member3;
    @FXML
    private TextField Team2Member4;
    @FXML
    private CheckBox Team1CheckBox1;
    @FXML
    private CheckBox Team1CheckBox2;		
    @FXML
    private CheckBox Team1CheckBox3;
    @FXML
    private CheckBox Team1CheckBox4;
    @FXML
    private CheckBox Team2CheckBox1;
    @FXML
    private CheckBox Team2CheckBox2;
    @FXML
    private CheckBox Team2CheckBox3;
    @FXML
    private CheckBox Team2CheckBox4;
    @FXML
    private TextField Team3Member1;
    @FXML
    private TextField Team3Member2;
    @FXML
    private TextField Team3Member3;
    @FXML
    private TextField Team3Member4;
    @FXML
    private TextField Team4Member1;
    @FXML
    private TextField Team4Member2;
    @FXML
    private TextField Team4Member3;
    @FXML
    private TextField Team4Member4;
    @FXML
    private TextField Team5Member1;
    @FXML
    private TextField Team5Member2;
    @FXML
    private TextField Team5Member3;
    @FXML
    private TextField Team5Member4;
    @FXML
    private CheckBox Team3CheckBox1;
    @FXML
    private CheckBox Team3CheckBox2;
    @FXML
    private CheckBox Team3CheckBox3;
    @FXML
    private CheckBox Team3CheckBox4;
    @FXML
    private CheckBox Team4CheckBox1;
    @FXML
    private CheckBox Team4CheckBox2;
    @FXML
    private CheckBox Team4CheckBox3;
    @FXML
    private CheckBox Team4CheckBox4;
    @FXML
    private CheckBox Team5CheckBox1;
    @FXML
    private CheckBox Team5CheckBox2;
    @FXML
    private CheckBox Team5CheckBox3;
    @FXML
    private CheckBox Team5CheckBox4;
    @FXML
    private Label getTeam3ProjectID;
    @FXML
    private Label getTeam4ProjectID;
    @FXML
    private Label getTeam5ProjectID;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
	
    @FXML
    public void addButon(ActionEvent event) throws Exception  {
    	
    	clickChooseStudentButton();	
    	
    	String StudentID = EnterStudentID.getText();	
    	String ProjectID = this.teamProjectID;
    	
		if (!(tl.getTeamFormation().getStudentHM().containsKey(StudentID))) {
			showAlert("Error Dialog", "Student with this ID does not exist", "Enter an existing ID");
		}
		else if (tl.getTeamFormation().getTeamHM().get(ProjectID).checkInvalidMemberSceneBuilder(StudentID, tl.getTeamFormation().getTeamHM()))
		{
			showAlert("Error Dialog", "Student with this ID is already assigned to another project team", "Enter another ID") ;
		}
		else if(tl.getTeamFormation().getTeamHM().get(ProjectID).checkRepeatedMemberSceneBuilder(StudentID))
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Student with this ID is already assigned to this project team.");
			alert.setContentText("Enter another ID");
			alert.showAndWait();
		}
		else if(tl.getTeamFormation().getTeamHM().get(ProjectID).checkStudentConflictSceneBuilder(tl.getTeamFormation().getStudentHM().get(StudentID))) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Student with this ID has conflicts in this project team.");
			alert.setContentText("Enter another ID");
			alert.showAndWait();
		}
		else if ((tl.getTeamFormation().getTeamHM().get(ProjectID).getMember().size() == 2)
				&& (tl.getTeamFormation().getTeamHM().get(ProjectID).checkPersonalityIsBalanceSceneBuilder(
						tl.getTeamFormation().getStudentHM().get(StudentID), "type1"))) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Imbalance Personality happens in this project team.");
			alert.setContentText("Enter another ID");
			alert.showAndWait();
		}
		else if ((tl.getTeamFormation().getTeamHM().get(ProjectID).getMember().size() == 3)
				&& (tl.getTeamFormation().getTeamHM().get(ProjectID).checkPersonalityIsBalanceSceneBuilder(
						tl.getTeamFormation().getStudentHM().get(StudentID), "type2"))) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Imbalance Personality happens in this project team.");
			alert.setContentText("Enter another ID");
			alert.showAndWait();

		} 
		else if ((tl.getTeamFormation().getTeamHM().get(ProjectID).getMember().size() == 3)
				&& (tl.getTeamFormation().getTeamHM().get(ProjectID)
						.checkNoLeaderSceneBuilder(tl.getTeamFormation().getStudentHM().get(StudentID)))) 
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("There is no leader in this project team.");
			alert.setContentText("Add another ID with leader type in this team");
			alert.showAndWait();
		}
		else if(tl.getTeamFormation().getTeamHM().get(ProjectID).getMember().size() >= 4)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("The number of members must not be over 4.");
			alert.setContentText("Add the student to other teams.");
			alert.showAndWait();
		}
		
		else {
			tl.getTeamFormation().getTeamHM().get(ProjectID).addMember(tl.getTeamFormation().getStudentHM().get(StudentID));
			TextfieldforAddStudent(StudentID);
			if(tl.getTeamFormation().getTeamHM().get(ProjectID).getMember().size() == 4)
				InsertMultipleRows.insertTeamMember(tl.getTeamFormation().getTeamHM().get(ProjectID), ProjectID);
			update();

		}

	}
    
    public void clickChooseStudentButton() {
    	if(Team1CheckBox1.isSelected()) {
        	this.selectStudent = "Team1_CheckBox1";  	
        	this.teamProjectID = getTeam1ProjectID.getText();
    	}
    	if(Team1CheckBox2.isSelected()) {
    		this.selectStudent = "Team1_CheckBox2";
    		this.teamProjectID = getTeam1ProjectID.getText();
    	}
    	if(Team1CheckBox3.isSelected()) {
    		this.selectStudent = "Team1_CheckBox3";
        	this.teamProjectID = getTeam1ProjectID.getText();
    	}
    	if(Team1CheckBox4.isSelected()) {
        	this.selectStudent = "Team1_CheckBox4";
        	this.teamProjectID = getTeam1ProjectID.getText();
    	}
    	
    	if(Team2CheckBox1.isSelected()) {
        	this.selectStudent = "Team2_CheckBox1";  	
        	this.teamProjectID = getTeam2ProjectID.getText();
    	}
    	if(Team2CheckBox2.isSelected()) {
    		this.selectStudent = "Team2_CheckBox2";
    		this.teamProjectID = getTeam2ProjectID.getText();
    	}
    	if(Team2CheckBox3.isSelected()) {
    		this.selectStudent = "Team2_CheckBox3";
        	this.teamProjectID = getTeam2ProjectID.getText();
    	}
    	if(Team2CheckBox4.isSelected()) {
        	this.selectStudent = "Team2_CheckBox4";
        	this.teamProjectID = getTeam2ProjectID.getText();
    	}
    	
    	if(Team3CheckBox1.isSelected()) {
        	this.selectStudent = "Team3_CheckBox1";  	
        	this.teamProjectID = getTeam3ProjectID.getText();
    	}
    	if(Team3CheckBox2.isSelected()) {
    		this.selectStudent = "Team3_CheckBox2";
    		this.teamProjectID = getTeam3ProjectID.getText();
    	}
    	if(Team3CheckBox3.isSelected()) {
    		this.selectStudent = "Team3_CheckBox3";
        	this.teamProjectID = getTeam3ProjectID.getText();
    	}
    	if(Team3CheckBox4.isSelected()) {
        	this.selectStudent = "Team3_CheckBox4";
        	this.teamProjectID = getTeam3ProjectID.getText();
    	}
    	
    	if(Team4CheckBox1.isSelected()) {
        	this.selectStudent = "Team4_CheckBox1";  	
        	this.teamProjectID = getTeam4ProjectID.getText();
    	}
    	if(Team4CheckBox2.isSelected()) {
    		this.selectStudent = "Team4_CheckBox2";
    		this.teamProjectID = getTeam4ProjectID.getText();
    	}
    	if(Team4CheckBox3.isSelected()) {
    		this.selectStudent = "Team4_CheckBox3";
        	this.teamProjectID = getTeam4ProjectID.getText();
    	}
    	if(Team4CheckBox4.isSelected()) {
        	this.selectStudent = "Team4_CheckBox4";
        	this.teamProjectID = getTeam4ProjectID.getText();
    	}
    	
    	if(Team5CheckBox1.isSelected()) {
        	this.selectStudent = "Team5_CheckBox1";  	
        	this.teamProjectID = getTeam5ProjectID.getText();
    	}
    	if(Team5CheckBox2.isSelected()) {
    		this.selectStudent = "Team5_CheckBox2";
    		this.teamProjectID = getTeam5ProjectID.getText();
    	}
    	if(Team5CheckBox3.isSelected()) {
    		this.selectStudent = "Team5_CheckBox3";
        	this.teamProjectID = getTeam5ProjectID.getText();
    	}
    	if(Team5CheckBox4.isSelected()) {
        	this.selectStudent = "Team5_CheckBox4";
        	this.teamProjectID = getTeam5ProjectID.getText();
    	}	
    }

    
    @FXML
    public void swapButton(ActionEvent event) throws Exception {
    	
    	String swapStudent1="", swapStudent2="";
    	String swapStudent1ProjectID = "", swapStudent2ProjectID = "";
    	String swapStudent1Label = "", swapStudent2Label = "";
    	int countClick = 0;
    	if (Team1CheckBox1.isSelected()) {countClick++;}
    	if (Team1CheckBox2.isSelected()) {countClick++;}
    	if (Team1CheckBox3.isSelected()) {countClick++;}
    	if (Team1CheckBox4.isSelected()) {countClick++;}
    	if (Team2CheckBox1.isSelected()) {countClick++;}
    	if (Team2CheckBox2.isSelected()) {countClick++;}
    	if (Team2CheckBox3.isSelected()) {countClick++;}
    	if (Team2CheckBox4.isSelected()) {countClick++;}
    	if (Team3CheckBox1.isSelected()) {countClick++;}
    	if (Team3CheckBox2.isSelected()) {countClick++;}
    	if (Team3CheckBox3.isSelected()) {countClick++;}
    	if (Team3CheckBox4.isSelected()) {countClick++;}
    	if (Team4CheckBox1.isSelected()) {countClick++;}
    	if (Team4CheckBox2.isSelected()) {countClick++;}
    	if (Team4CheckBox3.isSelected()) {countClick++;}
    	if (Team4CheckBox4.isSelected()) {countClick++;}
    	if (Team5CheckBox1.isSelected()) {countClick++;}
    	if (Team5CheckBox2.isSelected()) {countClick++;}
    	if (Team5CheckBox3.isSelected()) {countClick++;}
    	if (Team5CheckBox4.isSelected()) {countClick++;}
    	
		if (countClick == 2) {
			if (Team1CheckBox1.isSelected()) {

				if (swapStudent1.equals("")) {
					swapStudent1 = Team1Member1.getText();
					swapStudent1ProjectID = getTeam1ProjectID.getText();
					swapStudent1Label = "Team1_Member1";
				} else {
					swapStudent2 = Team1Member1.getText();
					swapStudent2ProjectID = getTeam1ProjectID.getText();
					swapStudent2Label = "Team1_Member1";
				}
			}
			if (Team1CheckBox2.isSelected()) {

				if (swapStudent1.equals("")) {
					swapStudent1 = Team1Member2.getText();
					swapStudent1ProjectID = getTeam1ProjectID.getText();
					swapStudent1Label = "Team1_Member2";
				} else {
					swapStudent2 = Team1Member1.getText();
					swapStudent2ProjectID = getTeam1ProjectID.getText();
					swapStudent2Label = "Team1_Member2";
				}
			}
			if (Team1CheckBox3.isSelected()) {

				if (swapStudent1.equals("")) {
					swapStudent1 = Team1Member3.getText();
					swapStudent1ProjectID = getTeam1ProjectID.getText();
					swapStudent1Label = "Team1_Member3";
				} else {
					swapStudent2 = Team1Member3.getText();
					swapStudent2ProjectID = getTeam1ProjectID.getText();
					swapStudent2Label = "Team1_Member3";
				}
			}
			if (Team1CheckBox4.isSelected()) {

				if (swapStudent1.equals("")) {
					swapStudent1 = Team1Member4.getText();
					swapStudent1ProjectID = getTeam1ProjectID.getText();
					swapStudent1Label = "Team1_Member4";

				} else {
					swapStudent2 = Team1Member4.getText();
					swapStudent2ProjectID = getTeam1ProjectID.getText();
					swapStudent2Label = "Team1_Member4";
				}
			}

			if (Team2CheckBox1.isSelected()) {

				if (swapStudent1.equals("")) {
					swapStudent1 = Team2Member1.getText();
					swapStudent1ProjectID = getTeam2ProjectID.getText();
					swapStudent1Label = "Team2_Member1";

				} else {
					swapStudent2 = Team2Member1.getText();
					swapStudent2ProjectID = getTeam2ProjectID.getText();
					swapStudent2Label = "Team2_Member1";
				}
			}
			if (Team2CheckBox2.isSelected()) {

				if (swapStudent1.equals("")) {
					swapStudent1 = Team2Member2.getText();
					swapStudent1ProjectID = getTeam2ProjectID.getText();
					swapStudent1Label = "Team2_Member2";

				} else {
					swapStudent2 = Team2Member2.getText();
					swapStudent2ProjectID = getTeam2ProjectID.getText();
					swapStudent2Label = "Team2_Member2";
				}
			}
			if (Team2CheckBox3.isSelected()) {

				if (swapStudent1.equals("")) {
					swapStudent1 = Team2Member3.getText();
					swapStudent1ProjectID = getTeam2ProjectID.getText();
					swapStudent1Label = "Team2_Member3";

				} else {
					swapStudent2 = Team2Member3.getText();
					swapStudent2ProjectID = getTeam2ProjectID.getText();
					swapStudent2Label = "Team2_Member3";
				}
			}
			if (Team2CheckBox4.isSelected()) {

				if (swapStudent1.equals("")) {
					swapStudent1 = Team2Member4.getText();
					swapStudent1ProjectID = getTeam2ProjectID.getText();
					swapStudent1Label = "Team2_Member4";

				} else {
					swapStudent2 = Team2Member4.getText();
					swapStudent2ProjectID = getTeam2ProjectID.getText();
					swapStudent2Label = "Team2_Member4";
				}
			}
			if (Team3CheckBox1.isSelected()) {
				if (swapStudent1.equals("")) {
					swapStudent1 = Team3Member1.getText();
					swapStudent1ProjectID = getTeam3ProjectID.getText();
					swapStudent1Label = "Team3_Member1";
				} else {
					swapStudent2 = Team3Member1.getText();
					swapStudent2ProjectID = getTeam3ProjectID.getText();
					swapStudent2Label = "Team3_Member1";
				}
			}
			if (Team3CheckBox2.isSelected()) {
				if (swapStudent1.equals("")) {
					swapStudent1 = Team3Member2.getText();
					swapStudent1ProjectID = getTeam3ProjectID.getText();
					swapStudent1Label = "Team3_Member2";
				} else {
					swapStudent2 = Team3Member1.getText();
					swapStudent2ProjectID = getTeam3ProjectID.getText();
					swapStudent2Label = "Team3_Member2";
				}
			}
			if (Team3CheckBox3.isSelected()) {
				if (swapStudent1.equals("")) {
					swapStudent1 = Team3Member3.getText();
					swapStudent1ProjectID = getTeam3ProjectID.getText();
					swapStudent1Label = "Team3_Member3";
				} else {
					swapStudent2 = Team3Member3.getText();
					swapStudent2ProjectID = getTeam3ProjectID.getText();
					swapStudent2Label = "Team3_Member3";
				}
			}
			if (Team3CheckBox4.isSelected()) {
				if (swapStudent1.equals("")) {
					swapStudent1 = Team3Member4.getText();
					swapStudent1ProjectID = getTeam3ProjectID.getText();
					swapStudent1Label = "Team3_Member4";
				} else {
					swapStudent2 = Team3Member4.getText();
					swapStudent2ProjectID = getTeam3ProjectID.getText();
					swapStudent2Label = "Team3_Member4";
				}
			}

			if (Team4CheckBox1.isSelected()) {
				if (swapStudent1.equals("")) {
					swapStudent1 = Team4Member1.getText();
					swapStudent1ProjectID = getTeam4ProjectID.getText();
					swapStudent1Label = "Team4_Member1";
				} else {
					swapStudent2 = Team4Member1.getText();
					swapStudent2ProjectID = getTeam4ProjectID.getText();
					swapStudent2Label = "Team4_Member1";
				}
			}
			if (Team4CheckBox2.isSelected()) {
				if (swapStudent1.equals("")) {
					swapStudent1 = Team4Member2.getText();
					swapStudent1ProjectID = getTeam4ProjectID.getText();
					swapStudent1Label = "Team4_Member2";
				} else {
					swapStudent2 = Team4Member2.getText();
					swapStudent2ProjectID = getTeam4ProjectID.getText();
					swapStudent2Label = "Team4_Member2";
				}
			}
			if (Team4CheckBox3.isSelected()) {
				if (swapStudent1.equals("")) {
					swapStudent1 = Team4Member3.getText();
					swapStudent1ProjectID = getTeam4ProjectID.getText();
					swapStudent1Label = "Team4_Member3";
				} else {
					swapStudent2 = Team4Member3.getText();
					swapStudent2ProjectID = getTeam4ProjectID.getText();
					swapStudent2Label = "Team4_Member3";
				}
			}
			if (Team4CheckBox4.isSelected()) {
				if (swapStudent1.equals("")) {
					swapStudent1 = Team4Member4.getText();
					swapStudent1ProjectID = getTeam4ProjectID.getText();
					swapStudent1Label = "Team4_Member4";
				} else {
					swapStudent2 = Team4Member4.getText();
					swapStudent2ProjectID = getTeam4ProjectID.getText();
					swapStudent2Label = "Team4_Member4";
				}
			}
			if (Team5CheckBox1.isSelected()) {
				if (swapStudent1.equals("")) {
					swapStudent1 = Team5Member1.getText();
					swapStudent1ProjectID = getTeam5ProjectID.getText();
					swapStudent1Label = "Team5_Member1";
				} else {
					swapStudent2 = Team5Member1.getText();
					swapStudent2ProjectID = getTeam5ProjectID.getText();
					swapStudent2Label = "Team5_Member1";
				}
			}
			if (Team5CheckBox2.isSelected()) {
				if (swapStudent1.equals("")) {
					swapStudent1 = Team5Member2.getText();
					swapStudent1ProjectID = getTeam5ProjectID.getText();
					swapStudent1Label = "Team5_Member2";
				} else {
					swapStudent2 = Team5Member1.getText();
					swapStudent2ProjectID = getTeam5ProjectID.getText();
					swapStudent2Label = "Team5_Member2";
				}
			}
			if (Team5CheckBox3.isSelected()) {
				if (swapStudent1.equals("")) {
					swapStudent1 = Team5Member3.getText();
					swapStudent1ProjectID = getTeam5ProjectID.getText();
					swapStudent1Label = "Team5_Member3";
				} else {
					swapStudent2 = Team5Member3.getText();
					swapStudent2ProjectID = getTeam5ProjectID.getText();
					swapStudent2Label = "Team5_Member3";
				}
			}
			if (Team5CheckBox4.isSelected()) {
				if (swapStudent1.equals("")) {
					swapStudent1 = Team5Member4.getText();
					swapStudent1ProjectID = getTeam5ProjectID.getText();
					swapStudent1Label = "Team5_Member4";
				} else {
					swapStudent2 = Team5Member4.getText();
					swapStudent2ProjectID = getTeam5ProjectID.getText();
					swapStudent2Label = "Team5_Member4";
				}
			}

			Student swapStudent1Member = (Student) tl.getTeamFormation().getStudentHM().get(swapStudent1);
			tl.getTeamFormation().getTeamHM().get(swapStudent1ProjectID).getMember().remove(swapStudent1,
					swapStudent1Member);

			Student swapStudent2Member = (Student) tl.getTeamFormation().getStudentHM().get(swapStudent2);
			tl.getTeamFormation().getTeamHM().get(swapStudent2ProjectID).getMember().remove(swapStudent2,
					swapStudent2Member);

			Student Swap1StudentInfo = tl.getTeamFormation().getStudentHM().get(swapStudent1);
			Student Swap2StudentInfo = tl.getTeamFormation().getStudentHM().get(swapStudent2);
	
			if (tl.getTeamFormation().getTeamHM().get(swapStudent2ProjectID)
					.checkStudentConflictSceneBuilder(Swap1StudentInfo)) {
				tl.getTeamFormation().getTeamHM().get(swapStudent1ProjectID).getMember().put(swapStudent1,
						swapStudent1Member);
				tl.getTeamFormation().getTeamHM().get(swapStudent2ProjectID).getMember().put(swapStudent2,
						swapStudent2Member);
				showAlert("Error Dialog", "Conflicts happen. Fail to swap", "Try another swap");

			}
			else if (tl.getTeamFormation().getTeamHM().get(swapStudent1ProjectID)
					.checkStudentConflictSceneBuilder(Swap2StudentInfo)) {
				tl.getTeamFormation().getTeamHM().get(swapStudent1ProjectID).getMember().put(swapStudent1,
						swapStudent1Member);
				tl.getTeamFormation().getTeamHM().get(swapStudent2ProjectID).getMember().put(swapStudent2,
						swapStudent2Member);

				showAlert("Error Dialog", "Conflicts happen. Fail to swap", "Try another swap");
			}
			else if (tl.getTeamFormation().getTeamHM().get(swapStudent2ProjectID)
					.checkPersonalityIsBalanceSceneBuilder(Swap1StudentInfo, "type2")) {

				tl.getTeamFormation().getTeamHM().get(swapStudent1ProjectID).getMember().put(swapStudent1,
						swapStudent1Member);
				tl.getTeamFormation().getTeamHM().get(swapStudent2ProjectID).getMember().put(swapStudent2,
						swapStudent2Member);

				showAlert("Error Dialog", "Imbalance Personality happens. Fail to swap", "Try another swap");
			}		
			else if (tl.getTeamFormation().getTeamHM().get(swapStudent1ProjectID)
					.checkPersonalityIsBalanceSceneBuilder(Swap2StudentInfo, "type2")) {
				tl.getTeamFormation().getTeamHM().get(swapStudent1ProjectID).getMember().put(swapStudent1,
						swapStudent1Member);
				tl.getTeamFormation().getTeamHM().get(swapStudent2ProjectID).getMember().put(swapStudent2,
						swapStudent2Member);

				showAlert("Error Dialog", "Imbalance Personality happens. Fail to swap", "Try another swap");
			}
			else if (tl.getTeamFormation().getTeamHM().get(swapStudent2ProjectID)
					.checkNoLeaderSceneBuilder(Swap1StudentInfo)) {
				tl.getTeamFormation().getTeamHM().get(swapStudent1ProjectID).getMember().put(swapStudent1,
						swapStudent1Member);
				tl.getTeamFormation().getTeamHM().get(swapStudent2ProjectID).getMember().put(swapStudent2,
						swapStudent2Member);

				showAlert("Error Dialog", "There is no leader in one of the teams. Fail to swap", "Try another swap");
			}
			else if (tl.getTeamFormation().getTeamHM().get(swapStudent1ProjectID)
					.checkNoLeaderSceneBuilder(Swap2StudentInfo)) {
				tl.getTeamFormation().getTeamHM().get(swapStudent1ProjectID).getMember().put(swapStudent1,
						swapStudent1Member);
				tl.getTeamFormation().getTeamHM().get(swapStudent2ProjectID).getMember().put(swapStudent2,
						swapStudent2Member);

				showAlert("Error Dialog", "There is no leader in one of the teams. Fail to swap", "Try another swap");
			}
			else {
				tl.getTeamFormation().getTeamHM().get(swapStudent1ProjectID).getMember().put(swapStudent2,
						swapStudent2Member);
				tl.getTeamFormation().getTeamHM().get(swapStudent2ProjectID).getMember().put(swapStudent1,
						swapStudent1Member);
				modifyLabelforSwappingStudent(swapStudent1, swapStudent1Label, swapStudent2, swapStudent2Label);
				UpdateQuery.updateTeamMember(tl.getTeamFormation().getTeamHM().get(swapStudent1ProjectID), swapStudent1ProjectID);
				UpdateQuery.updateTeamMember(tl.getTeamFormation().getTeamHM().get(swapStudent2ProjectID), swapStudent2ProjectID);
				update();
				manager.execute(new CAction(swapStudent1Member.getStudentID(), swapStudent2Member.getStudentID(),
						swapStudent1ProjectID, swapStudent2ProjectID));
			}
		}
		else if(countClick > 2) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("It is not allowed to swap " + countClick + " elements at a time");
			alert.setContentText("Please click exactly two student IDs.");
			alert.showAndWait();
		}

    }
    
	@FXML
	void undoButton(ActionEvent event) throws Exception {

		if (manager.getStackNormalSize() <= 0) {
			label1.setText("    Fail  to");
			label2.setText("undo!");
		} else {
			label1.setText(" ");
			label2.setText(" ");

			String swapInformation = manager.getFirstStackNormal();
			String components[] = swapInformation.split(" ");
			String swap1 = components[0];
			String swap2 = components[1];
			String team2 = components[2];
			String team1 = components[3];

			Student swap1Info = (Student) tl.getTeamFormation().getStudentHM().get(swap1);
			Student swap2Info = (Student) tl.getTeamFormation().getStudentHM().get(swap2);

			tl.getTeamFormation().getTeamHM().get(team2).getMember().remove(swap2, swap2Info);
			tl.getTeamFormation().getTeamHM().get(team1).getMember().remove(swap1, swap1Info);
			tl.getTeamFormation().getTeamHM().get(team1).getMember().put(swap2, swap2Info);
			tl.getTeamFormation().getTeamHM().get(team2).getMember().put(swap1, swap1Info);

			String swap1Label = "", swap2Label = "";
			if (Team1Member1.getText().equals(swap1)) {
				swap1Label = "Team1_Member1";
			}
			if (Team1Member2.getText().equals(swap1)) {
				swap1Label = "Team1_Member2";
			}
			if (Team1Member3.getText().equals(swap1)) {
				swap1Label = "Team1_Member3";
			}
			if (Team1Member4.getText().equals(swap1)) {
				swap1Label = "Team1_Member4";
			}

			if (Team2Member1.getText().equals(swap1)) {
				swap1Label = "Team2_Member1";
			}
			if (Team2Member2.getText().equals(swap1)) {
				swap1Label = "Team2_Member2";
			}
			if (Team2Member3.getText().equals(swap1)) {
				swap1Label = "Team2_Member3";
			}
			if (Team2Member4.getText().equals(swap1)) {
				swap1Label = "Team2_Member4";
			}

			if (Team3Member1.getText().equals(swap1)) {
				swap1Label = "Team3_Member1";
			}
			if (Team3Member2.getText().equals(swap1)) {
				swap1Label = "Team3_Member2";
			}
			if (Team3Member3.getText().equals(swap1)) {
				swap1Label = "Team3_Member3";
			}
			if (Team3Member4.getText().equals(swap1)) {
				swap1Label = "Team3_Member4";
			}

			if (Team4Member1.getText().equals(swap1)) {
				swap1Label = "Team4_Member1";
			}
			if (Team4Member2.getText().equals(swap1)) {
				swap1Label = "Team4_Member2";
			}
			if (Team4Member3.getText().equals(swap1)) {
				swap1Label = "Team4_Member3";
			}
			if (Team4Member4.getText().equals(swap1)) {
				swap1Label = "Team4_Member4";
			}

			if (Team5Member1.getText().equals(swap1)) {
				swap1Label = "Team5_Member1";
			}
			if (Team5Member2.getText().equals(swap1)) {
				swap1Label = "Team5_Member2";
			}
			if (Team5Member3.getText().equals(swap1)) {
				swap1Label = "Team5_Member3";
			}
			if (Team5Member4.getText().equals(swap1)) {
				swap1Label = "Team5_Member4";
			}

			if (Team1Member1.getText().equals(swap2)) {
				swap2Label = "Team1_Member1";
			}
			if (Team1Member2.getText().equals(swap2)) {
				swap2Label = "Team1_Member2";
			}
			if (Team1Member3.getText().equals(swap2)) {
				swap2Label = "Team1_Member3";
			}
			if (Team1Member4.getText().equals(swap2)) {
				swap2Label = "Team1_Member4";
			}

			if (Team2Member1.getText().equals(swap2)) {
				swap2Label = "Team2_Member1";
			}
			if (Team2Member2.getText().equals(swap2)) {
				swap2Label = "Team2_Member2";
			}
			if (Team2Member3.getText().equals(swap2)) {
				swap2Label = "Team2_Member3";
			}
			if (Team2Member4.getText().equals(swap2)) {
				swap2Label = "Team2_Member4";
			}

			if (Team3Member1.getText().equals(swap2)) {
				swap2Label = "Team3_Member1";
			}
			if (Team3Member2.getText().equals(swap2)) {
				swap2Label = "Team3_Member2";
			}
			if (Team3Member3.getText().equals(swap2)) {
				swap2Label = "Team3_Member3";
			}
			if (Team3Member4.getText().equals(swap2)) {
				swap2Label = "Team3_Member4";
			}

			if (Team4Member1.getText().equals(swap2)) {
				swap2Label = "Team4_Member1";
			}
			if (Team4Member2.getText().equals(swap2)) {
				swap2Label = "Team4_Member2";
			}
			if (Team4Member3.getText().equals(swap2)) {
				swap2Label = "Team4_Member3";
			}
			if (Team4Member4.getText().equals(swap2)) {
				swap2Label = "Team4_Member4";
			}

			if (Team5Member1.getText().equals(swap2)) {
				swap2Label = "Team5_Member1";
			}
			if (Team5Member2.getText().equals(swap2)) {
				swap2Label = "Team5_Member2";
			}
			if (Team5Member3.getText().equals(swap2)) {
				swap2Label = "Team5_Member3";
			}
			if (Team5Member4.getText().equals(swap2)) {
				swap2Label = "Team5_Member4";
			}

			modifyLabelforSwappingStudent(swap1, swap1Label, swap2, swap2Label);
			UpdateQuery.updateTeamMember(tl.getTeamFormation().getTeamHM().get(team1), team1);
			UpdateQuery.updateTeamMember(tl.getTeamFormation().getTeamHM().get(team2), team2);
			update();
			manager.undo();
		}

	}

    public void TextfieldforAddStudent(String StudentID) {
    	if(selectStudent.equals("Team1_CheckBox1")) {
    		Team1Member1.setText(StudentID);
    	}
    	if(selectStudent.equals("Team1_CheckBox2")) {
    		Team1Member2.setText(StudentID);
    	}
    	if(selectStudent.equals("Team1_CheckBox3")) {
    		Team1Member3.setText(StudentID);
    	}
    	if(selectStudent.equals("Team1_CheckBox4")) {
    		Team1Member4.setText(StudentID);
    	}
    	if(selectStudent.equals("Team2_CheckBox1")) {
    		Team2Member1.setText(StudentID);
    	}
    	if(selectStudent.equals("Team2_CheckBox2")) {
    		Team2Member2.setText(StudentID);
    	}
    	if(selectStudent.equals("Team2_CheckBox3")) {
    		Team2Member3.setText(StudentID);
    	}
    	if(selectStudent.equals("Team2_CheckBox4")) {
    		Team2Member4.setText(StudentID);
    	}
    	if(selectStudent.equals("Team3_CheckBox1")) {
    		Team3Member1.setText(StudentID);
    	}
    	if(selectStudent.equals("Team3_CheckBox2")) {
    		Team3Member2.setText(StudentID);
    	}
    	if(selectStudent.equals("Team3_CheckBox3")) {
    		Team3Member3.setText(StudentID);
    	}
    	if(selectStudent.equals("Team3_CheckBox4")) {
    		Team3Member4.setText(StudentID);
    	}
    	if(selectStudent.equals("Team4_CheckBox1")) {
    		Team4Member1.setText(StudentID);
    	}
    	if(selectStudent.equals("Team4_CheckBox2")) {
    		Team4Member2.setText(StudentID);
    	}
    	if(selectStudent.equals("Team4_CheckBox3")) {
    		Team4Member3.setText(StudentID);
    	}
    	if(selectStudent.equals("Team4_CheckBox4")) {
    		Team4Member4.setText(StudentID);
    	}
    	if(selectStudent.equals("Team5_CheckBox1")) {
    		Team5Member1.setText(StudentID);
    	}
    	if(selectStudent.equals("Team5_CheckBox2")) {
    		Team5Member2.setText(StudentID);
    	}
    	if(selectStudent.equals("Team5_CheckBox3")) {
    		Team5Member3.setText(StudentID);
    	}
    	if(selectStudent.equals("Team5_CheckBox4")) {
    		Team5Member4.setText(StudentID);
    	}
    }
   
    public void modifyLabelforSwappingStudent(String swapStudent1ID, String swapStudent1Label, String swapStudent2ID, String swapStudent2Label) {
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team2_Member1")){
    		Team1Member1.setText(swapStudent2ID);
    		Team2Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team2_Member2")){
    		Team1Member1.setText(swapStudent2ID);
    		Team2Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team2_Member3")){
    		Team1Member1.setText(swapStudent2ID);
    		Team2Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team2_Member4")){
    		Team1Member1.setText(swapStudent2ID);
    		Team2Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team3_Member1")){
    		Team1Member1.setText(swapStudent2ID);
    		Team3Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team3_Member2")){
    		Team1Member1.setText(swapStudent2ID);
    		Team3Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team3_Member3")){
    		Team1Member1.setText(swapStudent2ID);
    		Team3Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team3_Member4")){
    		Team1Member1.setText(swapStudent2ID);
    		Team3Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team4_Member1")){
    		Team1Member1.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team4_Member2")){
    		Team1Member1.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team4_Member3")){
    		Team1Member1.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team4_Member4")){
    		Team1Member1.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team5_Member1")){
    		Team1Member1.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team5_Member2")){
    		Team1Member1.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team5_Member3")){
    		Team1Member1.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member1") && swapStudent2Label.equals("Team5_Member4")){
    		Team1Member1.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	} 	  	
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team2_Member1")){
    		Team1Member2.setText(swapStudent2ID);
    		Team2Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team2_Member2")){
    		Team1Member2.setText(swapStudent2ID);
    		Team2Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team2_Member3")){
    		Team1Member2.setText(swapStudent2ID);
    		Team2Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team2_Member4")){
    		Team1Member2.setText(swapStudent2ID);
    		Team2Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team3_Member1")){
    		Team1Member2.setText(swapStudent2ID);
    		Team3Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team3_Member2")){
    		Team1Member2.setText(swapStudent2ID);
    		Team3Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team3_Member3")){
    		Team1Member2.setText(swapStudent2ID);
    		Team3Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team3_Member4")){
    		Team1Member2.setText(swapStudent2ID);
    		Team3Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team4_Member1")){
    		Team1Member2.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team4_Member2")){
    		Team1Member2.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team4_Member3")){
    		Team1Member2.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team4_Member4")){
    		Team1Member2.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team5_Member1")){
    		Team1Member2.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team5_Member2")){
    		Team1Member2.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team5_Member3")){
    		Team1Member2.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member2") && swapStudent2Label.equals("Team5_Member4")){
    		Team1Member2.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	}    		
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team2_Member1")){
    		Team1Member3.setText(swapStudent2ID);
    		Team2Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team2_Member2")){
    		Team1Member3.setText(swapStudent2ID);
    		Team2Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team2_Member3")){
    		Team1Member3.setText(swapStudent2ID);
    		Team2Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team2_Member4")){
    		Team1Member3.setText(swapStudent2ID);
    		Team2Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team3_Member1")){
    		Team1Member3.setText(swapStudent2ID);
    		Team3Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team3_Member2")){
    		Team1Member3.setText(swapStudent2ID);
    		Team3Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team3_Member3")){
    		Team1Member3.setText(swapStudent2ID);
    		Team3Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team3_Member4")){
    		Team1Member3.setText(swapStudent2ID);
    		Team3Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team4_Member1")){
    		Team1Member3.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team4_Member2")){
    		Team1Member3.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team4_Member3")){
    		Team1Member3.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team4_Member4")){
    		Team1Member3.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team5_Member1")){
    		Team1Member3.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team5_Member2")){
    		Team1Member3.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team5_Member3")){
    		Team1Member3.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member3") && swapStudent2Label.equals("Team5_Member4")){
    		Team1Member3.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	}   	
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team2_Member1")){
    		Team1Member4.setText(swapStudent2ID);
    		Team2Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team2_Member2")){
    		Team1Member4.setText(swapStudent2ID);
    		Team2Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team2_Member3")){
    		Team1Member4.setText(swapStudent2ID);
    		Team2Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team2_Member4")){
    		Team1Member4.setText(swapStudent2ID);
    		Team2Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team3_Member1")){
    		Team1Member4.setText(swapStudent2ID);
    		Team3Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team3_Member2")){
    		Team1Member4.setText(swapStudent2ID);
    		Team3Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team3_Member3")){
    		Team1Member4.setText(swapStudent2ID);
    		Team3Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team3_Member4")){
    		Team1Member4.setText(swapStudent2ID);
    		Team3Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team4_Member1")){
    		Team1Member4.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team4_Member2")){
    		Team1Member4.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team4_Member3")){
    		Team1Member4.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team4_Member4")){
    		Team1Member4.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team5_Member1")){
    		Team1Member4.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team5_Member2")){
    		Team1Member4.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team5_Member3")){
    		Team1Member4.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team1_Member4") && swapStudent2Label.equals("Team5_Member4")){
    		Team1Member4.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	}   	  	
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team1_Member1")){
    		Team2Member1.setText(swapStudent2ID);
    		Team1Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team1_Member2")){
    		Team2Member1.setText(swapStudent2ID);
    		Team1Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team1_Member3")){
    		Team2Member1.setText(swapStudent2ID);
    		Team1Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team1_Member4")){
    		Team2Member1.setText(swapStudent2ID);
    		Team1Member4.setText(swapStudent1ID);
    	}
    	
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team3_Member1")){
    		Team2Member1.setText(swapStudent2ID);
    		Team3Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team3_Member2")){
    		Team2Member1.setText(swapStudent2ID);
    		Team3Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team3_Member3")){
    		Team2Member1.setText(swapStudent2ID);
    		Team3Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team3_Member4")){
    		Team2Member1.setText(swapStudent2ID);
    		Team3Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team4_Member1")){
    		Team2Member1.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team4_Member2")){
    		Team2Member1.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team4_Member3")){
    		Team2Member1.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team4_Member4")){
    		Team2Member1.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team5_Member1")){
    		Team2Member1.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team5_Member2")){
    		Team2Member1.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team5_Member3")){
    		Team2Member1.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member1") && swapStudent2Label.equals("Team5_Member4")){
    		Team2Member1.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	}   	
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team1_Member1")){
    		Team2Member2.setText(swapStudent2ID);
    		Team1Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team1_Member2")){
    		Team2Member2.setText(swapStudent2ID);
    		Team1Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team1_Member3")){
    		Team2Member2.setText(swapStudent2ID);
    		Team1Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team1_Member4")){
    		Team2Member2.setText(swapStudent2ID);
    		Team1Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team3_Member1")){
    		Team2Member2.setText(swapStudent2ID);
    		Team3Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team3_Member2")){
    		Team2Member2.setText(swapStudent2ID);
    		Team3Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team3_Member3")){
    		Team2Member2.setText(swapStudent2ID);
    		Team3Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team3_Member4")){
    		Team2Member2.setText(swapStudent2ID);
    		Team3Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team4_Member1")){
    		Team2Member2.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team4_Member2")){
    		Team2Member2.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team4_Member3")){
    		Team2Member2.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team4_Member4")){
    		Team2Member2.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team5_Member1")){
    		Team2Member2.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team5_Member2")){
    		Team2Member2.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team5_Member3")){
    		Team2Member2.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member2") && swapStudent2Label.equals("Team5_Member4")){
    		Team2Member2.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	}   
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team1_Member1")){
    		Team2Member3.setText(swapStudent2ID);
    		Team1Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team1_Member2")){
    		Team2Member3.setText(swapStudent2ID);
    		Team1Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team1_Member3")){
    		Team2Member3.setText(swapStudent2ID);
    		Team1Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team1_Member4")){
    		Team2Member3.setText(swapStudent2ID);
    		Team1Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team3_Member1")){
    		Team2Member3.setText(swapStudent2ID);
    		Team3Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team3_Member2")){
    		Team2Member3.setText(swapStudent2ID);
    		Team3Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team3_Member3")){
    		Team2Member3.setText(swapStudent2ID);
    		Team3Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team3_Member4")){
    		Team2Member3.setText(swapStudent2ID);
    		Team3Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team4_Member1")){
    		Team2Member3.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team4_Member2")){
    		Team2Member3.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team4_Member3")){
    		Team2Member3.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team4_Member4")){
    		Team2Member3.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team5_Member1")){
    		Team2Member3.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team5_Member2")){
    		Team2Member3.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team5_Member3")){
    		Team2Member3.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member3") && swapStudent2Label.equals("Team5_Member4")){
    		Team2Member3.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	} 
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team1_Member1")){
    		Team2Member4.setText(swapStudent2ID);
    		Team1Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team1_Member2")){
    		Team2Member4.setText(swapStudent2ID);
    		Team1Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team1_Member3")){
    		Team2Member4.setText(swapStudent2ID);
    		Team1Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team1_Member4")){
    		Team2Member4.setText(swapStudent2ID);
    		Team1Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team3_Member1")){
    		Team2Member4.setText(swapStudent2ID);
    		Team3Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team3_Member2")){
    		Team2Member4.setText(swapStudent2ID);
    		Team3Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team3_Member3")){
    		Team2Member4.setText(swapStudent2ID);
    		Team3Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team3_Member4")){
    		Team2Member4.setText(swapStudent2ID);
    		Team3Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team4_Member1")){
    		Team2Member4.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team4_Member2")){
    		Team2Member4.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team4_Member3")){
    		Team2Member4.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team4_Member4")){
    		Team2Member4.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team5_Member1")){
    		Team2Member4.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team5_Member2")){
    		Team2Member4.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team5_Member3")){
    		Team2Member4.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team2_Member4") && swapStudent2Label.equals("Team5_Member4")){
    		Team2Member4.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	} 
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team1_Member1")){
    		Team3Member1.setText(swapStudent2ID);
    		Team1Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team1_Member2")){
    		Team3Member1.setText(swapStudent2ID);
    		Team1Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team1_Member3")){
    		Team3Member1.setText(swapStudent2ID);
    		Team1Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team1_Member4")){
    		Team3Member1.setText(swapStudent2ID);
    		Team1Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team2_Member1")){
    		Team3Member1.setText(swapStudent2ID);
    		Team2Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team2_Member2")){
    		Team3Member1.setText(swapStudent2ID);
    		Team2Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team2_Member3")){
    		Team3Member1.setText(swapStudent2ID);
    		Team2Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team2_Member4")){
    		Team3Member1.setText(swapStudent2ID);
    		Team2Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team4_Member1")){
    		Team3Member1.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team4_Member2")){
    		Team3Member1.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team4_Member3")){
    		Team3Member1.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team4_Member4")){
    		Team3Member1.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team5_Member1")){
    		Team3Member1.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team5_Member2")){
    		Team3Member1.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team5_Member3")){
    		Team3Member1.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member1") && swapStudent2Label.equals("Team5_Member4")){
    		Team3Member1.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team1_Member1")){
    		Team3Member2.setText(swapStudent2ID);
    		Team1Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team1_Member2")){
    		Team3Member2.setText(swapStudent2ID);
    		Team1Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team1_Member3")){
    		Team3Member2.setText(swapStudent2ID);
    		Team1Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team1_Member4")){
    		Team3Member2.setText(swapStudent2ID);
    		Team1Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team2_Member1")){
    		Team3Member2.setText(swapStudent2ID);
    		Team2Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team2_Member2")){
    		Team3Member2.setText(swapStudent2ID);
    		Team2Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team2_Member3")){
    		Team3Member2.setText(swapStudent2ID);
    		Team2Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team2_Member4")){
    		Team3Member2.setText(swapStudent2ID);
    		Team2Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team4_Member1")){
    		Team3Member2.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team4_Member2")){
    		Team3Member2.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team4_Member3")){
    		Team3Member2.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team4_Member4")){
    		Team3Member2.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team5_Member1")){
    		Team3Member2.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team5_Member2")){
    		Team3Member2.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team5_Member3")){
    		Team3Member2.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member2") && swapStudent2Label.equals("Team5_Member4")){
    		Team3Member2.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team1_Member1")){
    		Team3Member3.setText(swapStudent2ID);
    		Team1Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team1_Member2")){
    		Team3Member3.setText(swapStudent2ID);
    		Team1Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team1_Member3")){
    		Team3Member3.setText(swapStudent2ID);
    		Team1Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team1_Member4")){
    		Team3Member3.setText(swapStudent2ID);
    		Team1Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team2_Member1")){
    		Team3Member3.setText(swapStudent2ID);
    		Team2Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team2_Member2")){
    		Team3Member3.setText(swapStudent2ID);
    		Team2Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team2_Member3")){
    		Team3Member3.setText(swapStudent2ID);
    		Team2Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team2_Member4")){
    		Team3Member3.setText(swapStudent2ID);
    		Team2Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team4_Member1")){
    		Team3Member3.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team4_Member2")){
    		Team3Member3.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team4_Member3")){
    		Team3Member3.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team4_Member4")){
    		Team3Member3.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team5_Member1")){
    		Team3Member3.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team5_Member2")){
    		Team3Member3.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team5_Member3")){
    		Team3Member3.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member3") && swapStudent2Label.equals("Team5_Member4")){
    		Team3Member3.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team1_Member1")){
    		Team3Member4.setText(swapStudent2ID);
    		Team1Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team1_Member2")){
    		Team3Member4.setText(swapStudent2ID);
    		Team1Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team1_Member3")){
    		Team3Member4.setText(swapStudent2ID);
    		Team1Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team1_Member4")){
    		Team3Member4.setText(swapStudent2ID);
    		Team1Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team2_Member1")){
    		Team3Member4.setText(swapStudent2ID);
    		Team2Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team2_Member2")){
    		Team3Member4.setText(swapStudent2ID);
    		Team2Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team2_Member3")){
    		Team3Member4.setText(swapStudent2ID);
    		Team2Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team2_Member4")){
    		Team3Member4.setText(swapStudent2ID);
    		Team2Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team4_Member1")){
    		Team3Member4.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team4_Member2")){
    		Team3Member4.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team4_Member3")){
    		Team3Member4.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team4_Member4")){
    		Team3Member4.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team5_Member1")){
    		Team3Member4.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team5_Member2")){
    		Team3Member4.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team5_Member3")){
    		Team3Member4.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team3_Member4") && swapStudent2Label.equals("Team5_Member4")){
    		Team3Member4.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team1_Member1")){
    		Team4Member1.setText(swapStudent2ID);
    		Team1Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team1_Member2")){
    		Team4Member1.setText(swapStudent2ID);
    		Team1Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team1_Member3")){
    		Team4Member1.setText(swapStudent2ID);
    		Team1Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team1_Member4")){
    		Team4Member1.setText(swapStudent2ID);
    		Team1Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team2_Member1")){
    		Team4Member1.setText(swapStudent2ID);
    		Team2Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team2_Member2")){
    		Team4Member1.setText(swapStudent2ID);
    		Team2Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team2_Member3")){
    		Team4Member1.setText(swapStudent2ID);
    		Team2Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team2_Member4")){
    		Team4Member1.setText(swapStudent2ID);
    		Team2Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team3_Member1")){
    		Team4Member1.setText(swapStudent2ID);
    		Team3Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team3_Member2")){
    		Team4Member1.setText(swapStudent2ID);
    		Team3Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team3_Member3")){
    		Team4Member1.setText(swapStudent2ID);
    		Team3Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team3_Member4")){
    		Team4Member1.setText(swapStudent2ID);
    		Team3Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team5_Member1")){
    		Team4Member1.setText(swapStudent2ID);
    		Team5Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team5_Member2")){
    		Team4Member1.setText(swapStudent2ID);
    		Team5Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team5_Member3")){
    		Team4Member1.setText(swapStudent2ID);
    		Team5Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team4_Member1") && swapStudent2Label.equals("Team5_Member4")){
    		Team4Member1.setText(swapStudent2ID);
    		Team5Member4.setText(swapStudent1ID);
    	}
    	if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team1_Member1")) {
			Team4Member2.setText(swapStudent2ID);
			Team1Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team1_Member2")) {
			Team4Member2.setText(swapStudent2ID);
			Team1Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team1_Member3")) {
			Team4Member2.setText(swapStudent2ID);
			Team1Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team1_Member4")) {
			Team4Member2.setText(swapStudent2ID);
			Team1Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team2_Member1")) {
			Team4Member2.setText(swapStudent2ID);
			Team2Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team2_Member2")) {
			Team4Member2.setText(swapStudent2ID);
			Team2Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team2_Member3")) {
			Team4Member2.setText(swapStudent2ID);
			Team2Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team2_Member4")) {
			Team4Member2.setText(swapStudent2ID);
			Team2Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team3_Member1")) {
			Team4Member2.setText(swapStudent2ID);
			Team3Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team3_Member2")) {
			Team4Member2.setText(swapStudent2ID);
			Team3Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team3_Member3")) {
			Team4Member2.setText(swapStudent2ID);
			Team3Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team3_Member4")) {
			Team4Member2.setText(swapStudent2ID);
			Team3Member4.setText(swapStudent1ID);
		}
    	if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team5_Member1")) {
			Team4Member2.setText(swapStudent2ID);
			Team5Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team5_Member2")) {
			Team4Member2.setText(swapStudent2ID);
			Team5Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team5_Member3")) {
			Team4Member2.setText(swapStudent2ID);
			Team5Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member2") && swapStudent2Label.equals("Team5_Member4")) {
			Team4Member2.setText(swapStudent2ID);
			Team5Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team1_Member1")) {
			Team4Member3.setText(swapStudent2ID);
			Team1Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team1_Member2")) {
			Team4Member3.setText(swapStudent2ID);
			Team1Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team1_Member3")) {
			Team4Member3.setText(swapStudent2ID);
			Team1Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team1_Member4")) {
			Team4Member3.setText(swapStudent2ID);
			Team1Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team2_Member1")) {
			Team4Member3.setText(swapStudent2ID);
			Team2Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team2_Member2")) {
			Team4Member3.setText(swapStudent2ID);
			Team2Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team2_Member3")) {
			Team4Member3.setText(swapStudent2ID);
			Team2Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team2_Member4")) {
			Team4Member3.setText(swapStudent2ID);
			Team2Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team3_Member1")) {
			Team4Member3.setText(swapStudent2ID);
			Team3Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team3_Member2")) {
			Team4Member3.setText(swapStudent2ID);
			Team3Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team3_Member3")) {
			Team4Member3.setText(swapStudent2ID);
			Team3Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team3_Member4")) {
			Team4Member3.setText(swapStudent2ID);
			Team3Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team5_Member1")) {
			Team4Member3.setText(swapStudent2ID);
			Team5Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team5_Member2")) {
			Team4Member3.setText(swapStudent2ID);
			Team5Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team5_Member3")) {
			Team4Member3.setText(swapStudent2ID);
			Team5Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member3") && swapStudent2Label.equals("Team5_Member4")) {
			Team4Member3.setText(swapStudent2ID);
			Team5Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team1_Member1")) {
			Team4Member4.setText(swapStudent2ID);
			Team1Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team1_Member2")) {
			Team4Member4.setText(swapStudent2ID);
			Team1Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team1_Member3")) {
			Team4Member4.setText(swapStudent2ID);
			Team1Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team1_Member4")) {
			Team4Member4.setText(swapStudent2ID);
			Team1Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team2_Member1")) {
			Team4Member4.setText(swapStudent2ID);
			Team2Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team2_Member2")) {
			Team4Member4.setText(swapStudent2ID);
			Team2Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team2_Member3")) {
			Team4Member4.setText(swapStudent2ID);
			Team2Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team2_Member4")) {
			Team4Member4.setText(swapStudent2ID);
			Team2Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team3_Member1")) {
			Team4Member4.setText(swapStudent2ID);
			Team3Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team3_Member2")) {
			Team4Member4.setText(swapStudent2ID);
			Team3Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team3_Member3")) {
			Team4Member4.setText(swapStudent2ID);
			Team3Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team3_Member4")) {
			Team4Member4.setText(swapStudent2ID);
			Team3Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team5_Member1")) {
			Team4Member4.setText(swapStudent2ID);
			Team5Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team5_Member2")) {
			Team4Member4.setText(swapStudent2ID);
			Team5Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team5_Member3")) {
			Team4Member4.setText(swapStudent2ID);
			Team5Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team4_Member4") && swapStudent2Label.equals("Team5_Member4")) {
			Team4Member4.setText(swapStudent2ID);
			Team5Member4.setText(swapStudent1ID);
		}
		if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team1_Member1")){
    		Team5Member1.setText(swapStudent2ID);
    		Team1Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team1_Member2")){
    		Team5Member1.setText(swapStudent2ID);
    		Team1Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team1_Member3")){
    		Team5Member1.setText(swapStudent2ID);
    		Team1Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team1_Member4")){
    		Team5Member1.setText(swapStudent2ID);
    		Team1Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team2_Member1")){
    		Team5Member1.setText(swapStudent2ID);
    		Team2Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team2_Member2")){
    		Team5Member1.setText(swapStudent2ID);
    		Team2Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team2_Member3")){
    		Team5Member1.setText(swapStudent2ID);
    		Team2Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team2_Member4")){
    		Team5Member1.setText(swapStudent2ID);
    		Team2Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team3_Member1")){
    		Team5Member1.setText(swapStudent2ID);
    		Team3Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team3_Member2")){
    		Team5Member1.setText(swapStudent2ID);
    		Team3Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team3_Member3")){
    		Team5Member1.setText(swapStudent2ID);
    		Team3Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team3_Member4")){
    		Team5Member1.setText(swapStudent2ID);
    		Team3Member4.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team4_Member1")){
    		Team5Member1.setText(swapStudent2ID);
    		Team4Member1.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team4_Member2")){
    		Team5Member1.setText(swapStudent2ID);
    		Team4Member2.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team4_Member3")){
    		Team5Member1.setText(swapStudent2ID);
    		Team4Member3.setText(swapStudent1ID);
    	}
    	if(swapStudent1Label.equals("Team5_Member1") && swapStudent2Label.equals("Team4_Member4")){
    		Team5Member1.setText(swapStudent2ID);
    		Team4Member4.setText(swapStudent1ID);
    	}
    	if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team1_Member1")) {
			Team5Member2.setText(swapStudent2ID);
			Team1Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team1_Member2")) {
			Team5Member2.setText(swapStudent2ID);
			Team1Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team1_Member3")) {
			Team5Member2.setText(swapStudent2ID);
			Team1Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team1_Member4")) {
			Team5Member2.setText(swapStudent2ID);
			Team1Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team2_Member1")) {
			Team5Member2.setText(swapStudent2ID);
			Team2Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team2_Member2")) {
			Team5Member2.setText(swapStudent2ID);
			Team2Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team2_Member3")) {
			Team5Member2.setText(swapStudent2ID);
			Team2Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team2_Member4")) {
			Team5Member2.setText(swapStudent2ID);
			Team2Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team3_Member1")) {
			Team5Member2.setText(swapStudent2ID);
			Team3Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team3_Member2")) {
			Team5Member2.setText(swapStudent2ID);
			Team3Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team3_Member3")) {
			Team5Member2.setText(swapStudent2ID);
			Team3Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team3_Member4")) {
			Team5Member2.setText(swapStudent2ID);
			Team3Member4.setText(swapStudent1ID);
		}
    	if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team4_Member1")) {
			Team5Member2.setText(swapStudent2ID);
			Team4Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team4_Member2")) {
			Team5Member2.setText(swapStudent2ID);
			Team4Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team4_Member3")) {
			Team5Member2.setText(swapStudent2ID);
			Team4Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member2") && swapStudent2Label.equals("Team4_Member4")) {
			Team5Member2.setText(swapStudent2ID);
			Team4Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team1_Member1")) {
			Team5Member3.setText(swapStudent2ID);
			Team1Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team1_Member2")) {
			Team5Member3.setText(swapStudent2ID);
			Team1Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team1_Member3")) {
			Team5Member3.setText(swapStudent2ID);
			Team1Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team1_Member4")) {
			Team5Member3.setText(swapStudent2ID);
			Team1Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team2_Member1")) {
			Team5Member3.setText(swapStudent2ID);
			Team2Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team2_Member2")) {
			Team5Member3.setText(swapStudent2ID);
			Team2Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team2_Member3")) {
			Team5Member3.setText(swapStudent2ID);
			Team2Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team2_Member4")) {
			Team5Member3.setText(swapStudent2ID);
			Team2Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team3_Member1")) {
			Team5Member3.setText(swapStudent2ID);
			Team3Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team3_Member2")) {
			Team5Member3.setText(swapStudent2ID);
			Team3Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team3_Member3")) {
			Team5Member3.setText(swapStudent2ID);
			Team3Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team3_Member4")) {
			Team5Member3.setText(swapStudent2ID);
			Team3Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team4_Member1")) {
			Team5Member3.setText(swapStudent2ID);
			Team4Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team4_Member2")) {
			Team5Member3.setText(swapStudent2ID);
			Team4Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team4_Member3")) {
			Team5Member3.setText(swapStudent2ID);
			Team4Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member3") && swapStudent2Label.equals("Team4_Member4")) {
			Team5Member3.setText(swapStudent2ID);
			Team4Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team1_Member1")) {
			Team5Member4.setText(swapStudent2ID);
			Team1Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team1_Member2")) {
			Team5Member4.setText(swapStudent2ID);
			Team1Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team1_Member3")) {
			Team5Member4.setText(swapStudent2ID);
			Team1Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team1_Member4")) {
			Team5Member4.setText(swapStudent2ID);
			Team1Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team2_Member1")) {
			Team5Member4.setText(swapStudent2ID);
			Team2Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team2_Member2")) {
			Team5Member4.setText(swapStudent2ID);
			Team2Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team2_Member3")) {
			Team5Member4.setText(swapStudent2ID);
			Team2Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team2_Member4")) {
			Team5Member4.setText(swapStudent2ID);
			Team2Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team3_Member1")) {
			Team5Member4.setText(swapStudent2ID);
			Team3Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team3_Member2")) {
			Team5Member4.setText(swapStudent2ID);
			Team3Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team3_Member3")) {
			Team5Member4.setText(swapStudent2ID);
			Team3Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team3_Member4")) {
			Team5Member4.setText(swapStudent2ID);
			Team3Member4.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team4_Member1")) {
			Team5Member4.setText(swapStudent2ID);
			Team4Member1.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team4_Member2")) {
			Team5Member4.setText(swapStudent2ID);
			Team4Member2.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team4_Member3")) {
			Team5Member4.setText(swapStudent2ID);
			Team4Member3.setText(swapStudent1ID);
		}
		if (swapStudent1Label.equals("Team5_Member4") && swapStudent2Label.equals("Team4_Member4")) {
			Team5Member4.setText(swapStudent2ID);
			Team4Member4.setText(swapStudent1ID);
		}
    }
	
	public void showAlert(String title, String headerText, String contentText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.showAndWait();
	}
	
	@FXML
	public void initialize() throws Exception {

		ConnectionTest.connection();
		tl = new TeamList();
		tl.setController(this);
		xaxis1.setLabel("Std Dev = ...");
		xaxis2.setLabel("Std Dev = ...");
		xaxis3.setLabel("Std Dev = ...");
		update();
		
		System.out.println("###################################################################");
		System.out.println("## After all the teams are formed, the suggestion will be shown! ##");
		System.out.println("###################################################################");
	}

	public void update() throws Exception {
		
		Map<String, Integer> series1 = new TreeMap<String, Integer>();
		series1.put("Team1", (int) tl.getPreferenceOf1stAnd2nd(getTeam1ProjectID.getText()));
		series1.put("Team2", (int) tl.getPreferenceOf1stAnd2nd(getTeam2ProjectID.getText()));
		series1.put("Team3", (int) tl.getPreferenceOf1stAnd2nd(getTeam3ProjectID.getText()));
		series1.put("Team4", (int) tl.getPreferenceOf1stAnd2nd(getTeam4ProjectID.getText()));
		series1.put("Team5", (int) tl.getPreferenceOf1stAnd2nd(getTeam5ProjectID.getText()));
	
		Map<String, Double> series2 = new TreeMap<String, Double>();
		series2.put("Team1",  tl.getAverageCompetencyLevel(getTeam1ProjectID.getText()));
		series2.put("Team2",  tl.getAverageCompetencyLevel(getTeam2ProjectID.getText()));
		series2.put("Team3",  tl.getAverageCompetencyLevel(getTeam3ProjectID.getText()));
		series2.put("Team4",  tl.getAverageCompetencyLevel(getTeam4ProjectID.getText()));
		series2.put("Team5",  tl.getAverageCompetencyLevel(getTeam5ProjectID.getText()));

		Map<String, Double> series3 = new TreeMap<String, Double>();
		series3.put("Team1",  tl.getSkillGaps(getTeam1ProjectID.getText()));
		series3.put("Team2",  tl.getSkillGaps(getTeam2ProjectID.getText()));
		series3.put("Team3",  tl.getSkillGaps(getTeam3ProjectID.getText()));
		series3.put("Team4",  tl.getSkillGaps(getTeam4ProjectID.getText()));
		series3.put("Team5",  tl.getSkillGaps(getTeam5ProjectID.getText()));

		XYChart.Series dataSeries1 = new XYChart.Series();
		for (String c : series1.keySet())
			dataSeries1.getData().add(new XYChart.Data(c, series1.get(c)));
		bc1.getData().clear();
		bc1.getData().add(dataSeries1);

		XYChart.Series dataSeries2 = new XYChart.Series();
		for (String c : series2.keySet())
			dataSeries2.getData().add(new XYChart.Data(c, series2.get(c)));
		bc2.getData().clear();
		bc2.getData().add(dataSeries2);
		
		XYChart.Series dataSeries3 = new XYChart.Series();
		for (String c : series3.keySet())
			dataSeries3.getData().add(new XYChart.Data(c, series3.get(c)));
		bc3.getData().clear();
		bc3.getData().add(dataSeries3);
		xaxis1.setLabel("Std Dev = "+tl.getStandardDeviationForPercentage());
		xaxis2.setLabel("Std Dev = "+tl.getStandardDeviationForAverageCompetencyLevel());
		xaxis3.setLabel("Std Dev = "+tl.getStandardDeviationForSkillGap());
		
		ArrayList<String> teamProjectID = new ArrayList<String> ();
		
		teamProjectID.add(getTeam1ProjectID.getText());
		teamProjectID.add(getTeam2ProjectID.getText());
		teamProjectID.add(getTeam3ProjectID.getText());
		teamProjectID.add(getTeam4ProjectID.getText());
		teamProjectID.add(getTeam5ProjectID.getText());
	
		MyThread thread = new MyThread(tl, teamProjectID);
		thread.start();
	}
}



class  MyThread extends Thread {
	
	TeamList tl;
	ArrayList<String> teamProjectID;
	public MyThread(TeamList teamList, ArrayList<String> teamProjectID) throws Exception{
		this.tl = teamList;
		this.teamProjectID = teamProjectID;
	}
	
	public void run(){
		try {
			recommendation();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void recommendation() throws Exception{
		
		double min_SDforPreference = 0, min_SDforCompetency = 0, min_SDforSkillGap = 0, min_sum = 0;
		TeamList new_tl = tl;
		String min_change1="", min_change2="", min_team1="", min_team2="";
		int passFirstTime = 0;
		
		String getTeam1ProjectID = teamProjectID.get(0);
		String getTeam2ProjectID = teamProjectID.get(1);
		String getTeam3ProjectID = teamProjectID.get(2);
		String getTeam4ProjectID = teamProjectID.get(3);
		String getTeam5ProjectID = teamProjectID.get(4);

		if (tl.getTeamFormation().getTeamHM().get("Pr1").getMember().size() == 4
				&& tl.getTeamFormation().getTeamHM().get("Pr2").getMember().size() == 4
				&& tl.getTeamFormation().getTeamHM().get("Pr3").getMember().size() == 4
				&& tl.getTeamFormation().getTeamHM().get("Pr4").getMember().size() == 4
				&& tl.getTeamFormation().getTeamHM().get("Pr5").getMember().size() == 4) {
			ArrayList<String> team1Member = new ArrayList<String>();
			ArrayList<String> team2Member = new ArrayList<String>();
			ArrayList<String> team3Member = new ArrayList<String>();
			ArrayList<String> team4Member = new ArrayList<String>();
			ArrayList<String> team5Member = new ArrayList<String>();
			for (String t : tl.getTeamFormation().getTeamHM().get("Pr1").getMember().keySet()) {
				team1Member.add(t);
			}
			for (String t : tl.getTeamFormation().getTeamHM().get("Pr2").getMember().keySet()) {
				team2Member.add(t);
			}
			for (String t : tl.getTeamFormation().getTeamHM().get("Pr3").getMember().keySet()) {
				team3Member.add(t);
			}
			for (String t : tl.getTeamFormation().getTeamHM().get("Pr4").getMember().keySet()) {
				team4Member.add(t);
			}
			for (String t : tl.getTeamFormation().getTeamHM().get("Pr5").getMember().keySet()) {
				team5Member.add(t);
			}
			
			for (int i = 0; i < 4; i++) {
				Student swap1 = (Student) tl.getTeamFormation().getStudentHM().get(team1Member.get(i)); 
				
				for (int j = 0; j < 4; j++) {
					new_tl.getTeamFormation().getTeamHM().get("Pr1").getMember().remove(swap1.getStudentID(), swap1);
					
					Student swap2 = (Student) tl.getTeamFormation().getStudentHM().get(team2Member.get(j)); 
					new_tl.getTeamFormation().getTeamHM().get("Pr2").getMember().remove(swap2.getStudentID(), swap2);

					if (new_tl.getTeamFormation().getTeamHM().get("Pr2").checkStudentConflictSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get("Pr1").checkStudentConflictSceneBuilder(swap2)) {

						new_tl.getTeamFormation().getTeamHM().get("Pr1").getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get("Pr2").getMember().put(swap2.getStudentID(), swap2);

					}

					else if(new_tl.getTeamFormation().getTeamHM().get("Pr2").checkPersonalityIsBalanceSceneBuilder(swap1, "type2")
							|| new_tl.getTeamFormation().getTeamHM().get("Pr1").checkPersonalityIsBalanceSceneBuilder(swap2, "type2")) {
						
						new_tl.getTeamFormation().getTeamHM().get("Pr1").getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get("Pr2").getMember().put(swap2.getStudentID(), swap2);
						
					}
					else if (new_tl.getTeamFormation().getTeamHM().get("Pr2").checkNoLeaderSceneBuilder(swap1) 
							|| new_tl.getTeamFormation().getTeamHM().get("Pr1").checkNoLeaderSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get("Pr1").getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get("Pr2").getMember().put(swap2.getStudentID(), swap2);
					}
					else {					
						new_tl.getTeamFormation().getTeamHM().get("Pr1").getMember().put(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get("Pr2").getMember().put(swap1.getStudentID(), swap1);

						double replace1 = tl.getStandardDeviationForPercentage();
						double replace2 = tl.getStandardDeviationForAverageCompetencyLevel();
						double replace3 = tl.getStandardDeviationForSkillGap();
						
						new_tl.getPreferenceOf1stAnd2nd(getTeam1ProjectID);
						new_tl.getPreferenceOf1stAnd2nd(getTeam2ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam1ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam2ProjectID);
						new_tl.getSkillGaps(getTeam1ProjectID);
						new_tl.getSkillGaps(getTeam2ProjectID);
						
						HashMap<String, Student> team1members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get("Pr1").getMember().keySet()) {
							team1members.put(s, new_tl.getTeamFormation().getTeamHM().get("Pr1").getMember().get(s));
						}
						HashMap<String, Student> team2members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get("Pr2").getMember().keySet()) {
							team2members.put(s, new_tl.getTeamFormation().getTeamHM().get("Pr2").getMember().get(s));
						}
						if (passFirstTime == 0 ) {	
							min_team1 = "Pr1";
							min_team2 = "Pr2";

							min_SDforPreference = replace1;
							min_SDforCompetency = replace2;
							min_SDforSkillGap = replace3;
							min_sum = min_SDforPreference + min_SDforCompetency + min_SDforSkillGap;
								
							double sumOfThreeSD = new_tl.getStandardDeviationForPercentage() +
									new_tl.getStandardDeviationForAverageCompetencyLevel() +
									new_tl.getStandardDeviationForSkillGap();
							
							if(sumOfThreeSD  < min_sum) {	
								min_team1 = "Pr1";
								min_team2 = "Pr2";
								min_change1 = swap1.getStudentID();
								min_change2 = swap2.getStudentID();
								
								min_SDforPreference = new_tl.getStandardDeviationForPercentage();
								min_SDforCompetency = new_tl.getStandardDeviationForAverageCompetencyLevel();
								min_SDforSkillGap = new_tl.getStandardDeviationForSkillGap();
								min_sum = min_SDforPreference + min_SDforCompetency + min_SDforSkillGap;
							}
							
							passFirstTime++;
						}
						else {
							double sumOfThreeSD = new_tl.getStandardDeviationForPercentage() +
									new_tl.getStandardDeviationForAverageCompetencyLevel() +
									new_tl.getStandardDeviationForSkillGap();
							if(sumOfThreeSD  < min_sum) {
								
								min_team1 = "Pr1";
								min_team2 = "Pr2";
								min_change1 = swap1.getStudentID();
								min_change2 = swap2.getStudentID();
								
								min_SDforPreference = new_tl.getStandardDeviationForPercentage();
								min_SDforCompetency = new_tl.getStandardDeviationForAverageCompetencyLevel();
								min_SDforSkillGap = new_tl.getStandardDeviationForSkillGap();
								min_sum = min_SDforPreference + min_SDforCompetency + min_SDforSkillGap;
							}
						}
						new_tl.getTeamFormation().getTeamHM().get("Pr1").getMember().remove(swap2.getStudentID(),swap2);
						new_tl.getTeamFormation().getTeamHM().get("Pr2").getMember().remove(swap1.getStudentID(),swap1);
						new_tl.getTeamFormation().getTeamHM().get("Pr1").getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get("Pr2").getMember().put(swap2.getStudentID(), swap2);
					}

				}
			}

			for (int i = 0; i < 4; i++) {
				String T1 = "Pr1";
				String T2 = "Pr3";
				Student swap1 = (Student) tl.getTeamFormation().getStudentHM().get(team1Member.get(i)); 

				for (int j = 0; j < 4; j++) {
					new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap1.getStudentID(), swap1);

					Student swap2 = (Student) tl.getTeamFormation().getStudentHM().get(team3Member.get(j)); 
					new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap2.getStudentID(), swap2);

					if (new_tl.getTeamFormation().getTeamHM().get(T2).checkStudentConflictSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkStudentConflictSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}	
					else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkPersonalityIsBalanceSceneBuilder(swap1,"type2")
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkPersonalityIsBalanceSceneBuilder(swap2, "type2")) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
					else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkNoLeaderSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkNoLeaderSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
					else {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap1.getStudentID(), swap1);

						new_tl.getPreferenceOf1stAnd2nd(getTeam1ProjectID);
						new_tl.getPreferenceOf1stAnd2nd(getTeam3ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam1ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam3ProjectID);
						new_tl.getSkillGaps(getTeam1ProjectID);
						new_tl.getSkillGaps(getTeam3ProjectID);

						HashMap<String, Student> team1members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T1).getMember().keySet()) {
							team1members.put(s, new_tl.getTeamFormation().getTeamHM().get(T1).getMember().get(s));
						}
						HashMap<String, Student> team2members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T2).getMember().keySet()) {
							team2members.put(s, new_tl.getTeamFormation().getTeamHM().get(T2).getMember().get(s));
						}
						double sumOfThreeSD = new_tl.getStandardDeviationForPercentage()
								+ new_tl.getStandardDeviationForAverageCompetencyLevel()
								+ new_tl.getStandardDeviationForSkillGap();
						if (sumOfThreeSD < min_sum) {

							min_team1 = T1;
							min_team2 = T2;
							min_change1 = swap1.getStudentID();
							min_change2 = swap2.getStudentID();

							min_SDforPreference = new_tl.getStandardDeviationForPercentage();
							min_SDforCompetency = new_tl.getStandardDeviationForAverageCompetencyLevel();
							min_SDforSkillGap = new_tl.getStandardDeviationForSkillGap();
							min_sum = min_SDforPreference + min_SDforCompetency + min_SDforSkillGap;
						}
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
				}
			}
			for (int i = 0; i < 4; i++) {
				String T1 = "Pr1";
				String T2 = "Pr4";

				Student swap1 = (Student) tl.getTeamFormation().getStudentHM().get(team1Member.get(i)); 

				for (int j = 0; j < 4; j++) {
					new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap1.getStudentID(), swap1);
					Student swap2 = (Student) tl.getTeamFormation().getStudentHM().get(team4Member.get(j)); 
					new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap2.getStudentID(), swap2);

					if (new_tl.getTeamFormation().getTeamHM().get(T2).checkStudentConflictSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkStudentConflictSceneBuilder(swap2)) {

						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);

					}
					else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkPersonalityIsBalanceSceneBuilder(swap1,"type2")
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkPersonalityIsBalanceSceneBuilder(swap2, "type2")) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
					else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkNoLeaderSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkNoLeaderSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
					else {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap1.getStudentID(), swap1);

						new_tl.getPreferenceOf1stAnd2nd(getTeam1ProjectID);
						new_tl.getPreferenceOf1stAnd2nd(getTeam4ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam1ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam4ProjectID);
						new_tl.getSkillGaps(getTeam1ProjectID);
						new_tl.getSkillGaps(getTeam4ProjectID);

						HashMap<String, Student> team1members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T1).getMember().keySet()) {
							team1members.put(s, new_tl.getTeamFormation().getTeamHM().get(T1).getMember().get(s));
						}
						HashMap<String, Student> team2members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T2).getMember().keySet()) {
							team2members.put(s, new_tl.getTeamFormation().getTeamHM().get(T2).getMember().get(s));
						}

						double sumOfThreeSD = new_tl.getStandardDeviationForPercentage()
								+ new_tl.getStandardDeviationForAverageCompetencyLevel()
								+ new_tl.getStandardDeviationForSkillGap();
						if (sumOfThreeSD < min_sum) {

							min_team1 = T1;
							min_team2 = T2;
							min_change1 = swap1.getStudentID();
							min_change2 = swap2.getStudentID();

							min_SDforPreference = new_tl.getStandardDeviationForPercentage();
							min_SDforCompetency = new_tl.getStandardDeviationForAverageCompetencyLevel();
							min_SDforSkillGap = new_tl.getStandardDeviationForSkillGap();
							min_sum = min_SDforPreference + min_SDforCompetency + min_SDforSkillGap;
						}
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
				}
			}
			
			for (int i = 0; i < 4; i++) {
				String T1 = "Pr1";
				String T2 = "Pr5";
				Student swap1 = (Student) tl.getTeamFormation().getStudentHM().get(team1Member.get(i)); 

				for (int j = 0; j < 4; j++) {
					new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap1.getStudentID(), swap1);

					Student swap2 = (Student) tl.getTeamFormation().getStudentHM().get(team5Member.get(j)); 
					new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap2.getStudentID(), swap2);
					if (new_tl.getTeamFormation().getTeamHM().get(T2).checkStudentConflictSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkStudentConflictSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}	
					else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkPersonalityIsBalanceSceneBuilder(swap1,"type2")
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkPersonalityIsBalanceSceneBuilder(swap2, "type2")) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
					else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkNoLeaderSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkNoLeaderSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
					else {	
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap1.getStudentID(), swap1);

						new_tl.getPreferenceOf1stAnd2nd(getTeam1ProjectID);
						new_tl.getPreferenceOf1stAnd2nd(getTeam5ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam1ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam5ProjectID);
						new_tl.getSkillGaps(getTeam1ProjectID);
						new_tl.getSkillGaps(getTeam5ProjectID);

						HashMap<String, Student> team1members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T1).getMember().keySet()) {
							team1members.put(s, new_tl.getTeamFormation().getTeamHM().get(T1).getMember().get(s));
						}
						HashMap<String, Student> team2members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T2).getMember().keySet()) {
							team2members.put(s, new_tl.getTeamFormation().getTeamHM().get(T2).getMember().get(s));
						}

						double sumOfThreeSD = new_tl.getStandardDeviationForPercentage()
								+ new_tl.getStandardDeviationForAverageCompetencyLevel()
								+ new_tl.getStandardDeviationForSkillGap();
						if (sumOfThreeSD < min_sum) {

							min_team1 = T1;
							min_team2 = T2;
							min_change1 = swap1.getStudentID();
							min_change2 = swap2.getStudentID();

							min_SDforPreference = new_tl.getStandardDeviationForPercentage();
							min_SDforCompetency = new_tl.getStandardDeviationForAverageCompetencyLevel();
							min_SDforSkillGap = new_tl.getStandardDeviationForSkillGap();
							min_sum = min_SDforPreference + min_SDforCompetency + min_SDforSkillGap;
						}

						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
				}
			}
			for (int i = 0; i < 4; i++) {
				String T1 = "Pr2";
				String T2 = "Pr3";
				Student swap1 = (Student) tl.getTeamFormation().getStudentHM().get(team2Member.get(i)); 

				for (int j = 0; j < 4; j++) {
					new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap1.getStudentID(), swap1);

					Student swap2 = (Student) tl.getTeamFormation().getStudentHM().get(team3Member.get(j)); 
					new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap2.getStudentID(), swap2);

					if (new_tl.getTeamFormation().getTeamHM().get(T2).checkStudentConflictSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkStudentConflictSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
					else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkPersonalityIsBalanceSceneBuilder(swap1,"type2")
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkPersonalityIsBalanceSceneBuilder(swap2, "type2")) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
					else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkNoLeaderSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkNoLeaderSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
					else {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap1.getStudentID(), swap1);

						new_tl.getPreferenceOf1stAnd2nd(getTeam2ProjectID);
						new_tl.getPreferenceOf1stAnd2nd(getTeam3ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam2ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam3ProjectID);
						new_tl.getSkillGaps(getTeam2ProjectID);
						new_tl.getSkillGaps(getTeam3ProjectID);

						HashMap<String, Student> team1members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T1).getMember().keySet()) {
							team1members.put(s, new_tl.getTeamFormation().getTeamHM().get(T1).getMember().get(s));
						}
						HashMap<String, Student> team2members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T2).getMember().keySet()) {
							team2members.put(s, new_tl.getTeamFormation().getTeamHM().get(T2).getMember().get(s));
						}

						double sumOfThreeSD = new_tl.getStandardDeviationForPercentage()
								+ new_tl.getStandardDeviationForAverageCompetencyLevel()
								+ new_tl.getStandardDeviationForSkillGap();
						if (sumOfThreeSD < min_sum) {

							min_team1 = T1;
							min_team2 = T2;
							min_change1 = swap1.getStudentID();
							min_change2 = swap2.getStudentID();

							min_SDforPreference = new_tl.getStandardDeviationForPercentage();
							min_SDforCompetency = new_tl.getStandardDeviationForAverageCompetencyLevel();
							min_SDforSkillGap = new_tl.getStandardDeviationForSkillGap();
							min_sum = min_SDforPreference + min_SDforCompetency + min_SDforSkillGap;
						}
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
				}
			}
			for (int i = 0; i < 4; i++) {
				String T1 = "Pr2";
				String T2 = "Pr4";
				Student swap1 = (Student) tl.getTeamFormation().getStudentHM().get(team2Member.get(i));

				for (int j = 0; j < 4; j++) {
					new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap1.getStudentID(), swap1);

					Student swap2 = (Student) tl.getTeamFormation().getStudentHM().get(team4Member.get(j));
					new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap2.getStudentID(), swap2);

					if (new_tl.getTeamFormation().getTeamHM().get(T2).checkStudentConflictSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkStudentConflictSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else if (new_tl.getTeamFormation().getTeamHM().get(T2)
							.checkPersonalityIsBalanceSceneBuilder(swap1, "type2")
							|| new_tl.getTeamFormation().getTeamHM().get(T1)
									.checkPersonalityIsBalanceSceneBuilder(swap2, "type2")) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkNoLeaderSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkNoLeaderSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap1.getStudentID(), swap1);

						new_tl.getPreferenceOf1stAnd2nd(getTeam2ProjectID);
						new_tl.getPreferenceOf1stAnd2nd(getTeam4ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam2ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam4ProjectID);
						new_tl.getSkillGaps(getTeam2ProjectID);
						new_tl.getSkillGaps(getTeam4ProjectID);

						HashMap<String, Student> team1members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T1).getMember().keySet()) {
							team1members.put(s, new_tl.getTeamFormation().getTeamHM().get(T1).getMember().get(s));
						}
						HashMap<String, Student> team2members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T2).getMember().keySet()) {
							team2members.put(s, new_tl.getTeamFormation().getTeamHM().get(T2).getMember().get(s));
						}

						double sumOfThreeSD = new_tl.getStandardDeviationForPercentage()
								+ new_tl.getStandardDeviationForAverageCompetencyLevel()
								+ new_tl.getStandardDeviationForSkillGap();
						if (sumOfThreeSD < min_sum) {

							min_team1 = T1;
							min_team2 = T2;
							min_change1 = swap1.getStudentID();
							min_change2 = swap2.getStudentID();

							min_SDforPreference = new_tl.getStandardDeviationForPercentage();
							min_SDforCompetency = new_tl.getStandardDeviationForAverageCompetencyLevel();
							min_SDforSkillGap = new_tl.getStandardDeviationForSkillGap();
							min_sum = min_SDforPreference + min_SDforCompetency + min_SDforSkillGap;
						}
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
				}
			}
			for (int i = 0; i < 4; i++) {
				String T1 = "Pr2";
				String T2 = "Pr5";
				Student swap1 = (Student) tl.getTeamFormation().getStudentHM().get(team2Member.get(i)); 

				for (int j = 0; j < 4; j++) {
					new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap1.getStudentID(), swap1);

					Student swap2 = (Student) tl.getTeamFormation().getStudentHM().get(team5Member.get(j)); 
					new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap2.getStudentID(), swap2);

					if (new_tl.getTeamFormation().getTeamHM().get(T2).checkStudentConflictSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkStudentConflictSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else if (new_tl.getTeamFormation().getTeamHM().get(T2)
							.checkPersonalityIsBalanceSceneBuilder(swap1, "type2")
							|| new_tl.getTeamFormation().getTeamHM().get(T1)
									.checkPersonalityIsBalanceSceneBuilder(swap2, "type2")) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkNoLeaderSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkNoLeaderSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap1.getStudentID(), swap1);

						new_tl.getPreferenceOf1stAnd2nd(getTeam2ProjectID);
						new_tl.getPreferenceOf1stAnd2nd(getTeam5ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam2ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam5ProjectID);
						new_tl.getSkillGaps(getTeam2ProjectID);
						new_tl.getSkillGaps(getTeam5ProjectID);

						HashMap<String, Student> team1members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T1).getMember().keySet()) {
							team1members.put(s, new_tl.getTeamFormation().getTeamHM().get(T1).getMember().get(s));
						}
						HashMap<String, Student> team2members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T2).getMember().keySet()) {
							team2members.put(s, new_tl.getTeamFormation().getTeamHM().get(T2).getMember().get(s));
						}

						double sumOfThreeSD = new_tl.getStandardDeviationForPercentage()
								+ new_tl.getStandardDeviationForAverageCompetencyLevel()
								+ new_tl.getStandardDeviationForSkillGap();
						if (sumOfThreeSD < min_sum) {

							min_team1 = T1;
							min_team2 = T2;
							min_change1 = swap1.getStudentID();
							min_change2 = swap2.getStudentID();

							min_SDforPreference = new_tl.getStandardDeviationForPercentage();
							min_SDforCompetency = new_tl.getStandardDeviationForAverageCompetencyLevel();
							min_SDforSkillGap = new_tl.getStandardDeviationForSkillGap();
							min_sum = min_SDforPreference + min_SDforCompetency + min_SDforSkillGap;
						}

						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				String T1 = "Pr3";
				String T2 = "Pr4";
				Student swap1 = (Student) tl.getTeamFormation().getStudentHM().get(team3Member.get(i)); 

				for (int j = 0; j < 4; j++) {
					new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap1.getStudentID(), swap1);

					Student swap2 = (Student) tl.getTeamFormation().getStudentHM().get(team4Member.get(j)); 
					new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap2.getStudentID(), swap2);

					if (new_tl.getTeamFormation().getTeamHM().get(T2).checkStudentConflictSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkStudentConflictSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);

					} else if (new_tl.getTeamFormation().getTeamHM().get(T2)
							.checkPersonalityIsBalanceSceneBuilder(swap1, "type2")
							|| new_tl.getTeamFormation().getTeamHM().get(T1)
									.checkPersonalityIsBalanceSceneBuilder(swap2, "type2")) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkNoLeaderSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkNoLeaderSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getPreferenceOf1stAnd2nd(getTeam3ProjectID);
						new_tl.getPreferenceOf1stAnd2nd(getTeam4ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam3ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam4ProjectID);
						new_tl.getSkillGaps(getTeam3ProjectID);
						new_tl.getSkillGaps(getTeam4ProjectID);

						HashMap<String, Student> team1members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T1).getMember().keySet()) {
							team1members.put(s, new_tl.getTeamFormation().getTeamHM().get(T1).getMember().get(s));
						}
						HashMap<String, Student> team2members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T2).getMember().keySet()) {
							team2members.put(s, new_tl.getTeamFormation().getTeamHM().get(T2).getMember().get(s));
						}

						double sumOfThreeSD = new_tl.getStandardDeviationForPercentage()
								+ new_tl.getStandardDeviationForAverageCompetencyLevel()
								+ new_tl.getStandardDeviationForSkillGap();
						if (sumOfThreeSD < min_sum) {

							min_team1 = T1;
							min_team2 = T2;
							min_change1 = swap1.getStudentID();
							min_change2 = swap2.getStudentID();

							min_SDforPreference = new_tl.getStandardDeviationForPercentage();
							min_SDforCompetency = new_tl.getStandardDeviationForAverageCompetencyLevel();
							min_SDforSkillGap = new_tl.getStandardDeviationForSkillGap();
							min_sum = min_SDforPreference + min_SDforCompetency + min_SDforSkillGap;
						}

						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				String T1 = "Pr3";
				String T2 = "Pr5";

				Student swap1 = (Student) tl.getTeamFormation().getStudentHM().get(team3Member.get(i)); 

				for (int j = 0; j < 4; j++) {
					new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap1.getStudentID(), swap1);

					Student swap2 = (Student) tl.getTeamFormation().getStudentHM().get(team5Member.get(j)); 
					new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap2.getStudentID(), swap2);

					if (new_tl.getTeamFormation().getTeamHM().get(T2).checkStudentConflictSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkStudentConflictSceneBuilder(swap2)) {

						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else if (new_tl.getTeamFormation().getTeamHM().get(T2)
							.checkPersonalityIsBalanceSceneBuilder(swap1, "type2")
							|| new_tl.getTeamFormation().getTeamHM().get(T1)
									.checkPersonalityIsBalanceSceneBuilder(swap2, "type2")) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkNoLeaderSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkNoLeaderSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap1.getStudentID(), swap1);

						new_tl.getPreferenceOf1stAnd2nd(getTeam3ProjectID);
						new_tl.getPreferenceOf1stAnd2nd(getTeam5ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam3ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam5ProjectID);
						new_tl.getSkillGaps(getTeam3ProjectID);
						new_tl.getSkillGaps(getTeam5ProjectID);

						HashMap<String, Student> team1members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T1).getMember().keySet()) {
							team1members.put(s, new_tl.getTeamFormation().getTeamHM().get(T1).getMember().get(s));
						}
						HashMap<String, Student> team2members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T2).getMember().keySet()) {
							team2members.put(s, new_tl.getTeamFormation().getTeamHM().get(T2).getMember().get(s));
						}

						double sumOfThreeSD = new_tl.getStandardDeviationForPercentage()
								+ new_tl.getStandardDeviationForAverageCompetencyLevel()
								+ new_tl.getStandardDeviationForSkillGap();
						if (sumOfThreeSD < min_sum) {

							min_team1 = T1;
							min_team2 = T2;
							min_change1 = swap1.getStudentID();
							min_change2 = swap2.getStudentID();

							min_SDforPreference = new_tl.getStandardDeviationForPercentage();
							min_SDforCompetency = new_tl.getStandardDeviationForAverageCompetencyLevel();
							min_SDforSkillGap = new_tl.getStandardDeviationForSkillGap();
							min_sum = min_SDforPreference + min_SDforCompetency + min_SDforSkillGap;
						}
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
				}
			}
			for (int i = 0; i < 4; i++) {
				String T1 = "Pr4";
				String T2 = "Pr5";
				Student swap1 = (Student) tl.getTeamFormation().getStudentHM().get(team4Member.get(i));

				for (int j = 0; j < 4; j++) {
					new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap1.getStudentID(), swap1);

					Student swap2 = (Student) tl.getTeamFormation().getStudentHM().get(team5Member.get(j));
					new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap2.getStudentID(), swap2);

					if (new_tl.getTeamFormation().getTeamHM().get(T2).checkStudentConflictSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkStudentConflictSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else if (new_tl.getTeamFormation().getTeamHM().get(T2)
							.checkPersonalityIsBalanceSceneBuilder(swap1, "type2")
							|| new_tl.getTeamFormation().getTeamHM().get(T1)
									.checkPersonalityIsBalanceSceneBuilder(swap2, "type2")) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else if (new_tl.getTeamFormation().getTeamHM().get(T2).checkNoLeaderSceneBuilder(swap1)
							|| new_tl.getTeamFormation().getTeamHM().get(T1).checkNoLeaderSceneBuilder(swap2)) {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					} else {
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getPreferenceOf1stAnd2nd(getTeam4ProjectID);
						new_tl.getPreferenceOf1stAnd2nd(getTeam5ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam4ProjectID);
						new_tl.getAverageCompetencyLevel(getTeam5ProjectID);
						new_tl.getSkillGaps(getTeam4ProjectID);
						new_tl.getSkillGaps(getTeam5ProjectID);

						HashMap<String, Student> team1members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T1).getMember().keySet()) {
							team1members.put(s, new_tl.getTeamFormation().getTeamHM().get(T1).getMember().get(s));
						}
						HashMap<String, Student> team2members = new HashMap<String, Student>();
						for (String s : new_tl.getTeamFormation().getTeamHM().get(T2).getMember().keySet()) {
							team2members.put(s, new_tl.getTeamFormation().getTeamHM().get(T2).getMember().get(s));
						}

						double sumOfThreeSD = new_tl.getStandardDeviationForPercentage()
								+ new_tl.getStandardDeviationForAverageCompetencyLevel()
								+ new_tl.getStandardDeviationForSkillGap();
						if (sumOfThreeSD < min_sum) {

							min_team1 = T1;
							min_team2 = T2;
							min_change1 = swap1.getStudentID();
							min_change2 = swap2.getStudentID();

							min_SDforPreference = new_tl.getStandardDeviationForPercentage();
							min_SDforCompetency = new_tl.getStandardDeviationForAverageCompetencyLevel();
							min_SDforSkillGap = new_tl.getStandardDeviationForSkillGap();
							min_sum = min_SDforPreference + min_SDforCompetency + min_SDforSkillGap;
						}

						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().remove(swap2.getStudentID(), swap2);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().remove(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T1).getMember().put(swap1.getStudentID(), swap1);
						new_tl.getTeamFormation().getTeamHM().get(T2).getMember().put(swap2.getStudentID(), swap2);
					}
				}
			}

			if (passFirstTime == 0) {
				System.out.println("The team formation is optimal. Do not need to change.");
			}
			System.out.println(
					"###########################################################################################");
			System.out.print("## Suggestion: Swap " + min_change1 + " in " + min_team1 + " Team with " + min_change2
					+ " in " + min_team2 + " Team");

			System.out.println(", Total Std Dev = " + min_sum);
			System.out.println(
					"###########################################################################################");
		} else {
		}

	}

}







