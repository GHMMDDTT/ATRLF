import another.tool.recognition.language.trees.Node;

import java.util.ArrayList;

public class NodeCompilationUnit extends Node {
	private final ArrayList<Node> statements;

	public NodeCompilationUnit(ArrayList<Node> statements) {
		this.statements = statements;
	}

	public ArrayList<Node> getStatements() {
		return statements;
	}
}
