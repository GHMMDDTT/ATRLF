package another.tool.recognition.language.trees;

public abstract class Node {
	private String tag;
	private State stateName = State.NO;

	private Node parent;
	private State stateParent = State.NO;

	public final void setParent(Node parent) {
		if (this.stateParent == State.NO) {
			this.parent = parent;
			this.stateParent = State.YES;
			return;
		}
		System.err.println("Cannot reassigned the parent!!!");
		System.exit(-1);
	}

	public final void setTag(String tag) {
		if (this.stateName == State.NO) {
			this.tag = tag;
			this.stateName = State.YES;
			return;
		}
		System.err.println("Cannot reassigned the name!!!");
		System.exit(-1);
	}

	public final String getTag() {
		return this.tag;
	}

	public final Node getParent() {
		return this.parent;
	}

	private enum State { YES, NO }
}
