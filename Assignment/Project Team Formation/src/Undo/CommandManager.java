package Undo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandManager {
	private static CommandManager instance = null;
	private Stack<Action> stackNormal;
	private List<String> actionHistory;

	public static CommandManager getInstance() {
		if (instance != null)
			return instance;
		return new CommandManager();
	}

	private CommandManager() {
		stackNormal = new Stack<>();
		actionHistory = new ArrayList<>();
	}

	public void execute(Action action) {
		stackNormal.push(action);
		actionHistory.add(action.getName());
	}

	public void undo() {
		Action a;
		if (stackNormal.size() > 0) {
			a = stackNormal.pop();
			actionHistory.add(a.getName() + " - undo");
		}
	}

	public void clearNormal() {
		stackNormal.clear();
	}

	public List<String> getActionHistory() {
		return actionHistory;
	}

	public String getFirstStackNormal() {
		return stackNormal.getTopElement().getName();
	}

	public int getStackNormalSize() {
		return stackNormal.size();
	}
}