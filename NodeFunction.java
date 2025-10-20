import another.tool.recognition.language.trees.Node;
import another.tool.recognition.language.tokenizer.Token;

public class NodeFunction extends Node {
	private final Token name;
	private final Token type;

	public NodeFunction(Token name, Token type) {
		this.name = name;
		this.type = type;
	}

	public Token getName() {
		return name;
	}

	public Token getType() {
		return type;
	}

	@Override
	public String toString() {
		return "NodeFunction{" +
				"name=" + name +
				", type=" + type +
				", parent=" + getParent() +
				'}';
	}
}
