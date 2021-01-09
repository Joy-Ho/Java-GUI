package Undo;

public class CAction implements Action {
	private String swap1;
	private String swap2;
	private String team1;
	private String team2;

	public CAction(String swap1, String swap2, String team1, String team2) {
		this.swap1 = swap1;
		this.swap2 = swap2;
		this.team1 = team1;
		this.team2 = team2;
	}

	@Override
	public void execute() {
		System.out.println("Executing Action " + swap1 + " & " + swap2);
	}

	@Override
	public void undo() {
		System.out.println("Undo Action " + swap1 + " & " + swap2);
	}

	@Override
	public String getName() {

		String swapInformation = swap1 + " " + swap2 + " " + team1 + " " + team2;

		return swapInformation;
	}

}
