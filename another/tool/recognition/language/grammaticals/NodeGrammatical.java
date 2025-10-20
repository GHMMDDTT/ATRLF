package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.trees.Node;
import another.tool.recognition.language.parser.LanguageParserInformation;
import another.tool.recognition.language.tokenizer.Token;

import java.util.ArrayList;
import java.util.List;

public class NodeGrammatical implements Grammatical {
	private final Grammatical grammatical;
	private final OnActionListener listener;

	public NodeGrammatical(Grammatical grammatical, OnActionListener listener) {
		this.grammatical = grammatical;
		this.listener = listener;
	}

	@Override
	public Result onGrammaticalExecute(LanguageParserInformation information) {
		Result state;
		Node node = null;
		int oldPosition = information.getPosition();

		if ((state = grammatical.onGrammaticalExecute(information)) == null) return state;

		if (this.listener instanceof OnTreeActionListener) {
			node = ((OnTreeActionListener) this.listener).onTreeAction(state.node instanceof ArrayList<?> ? (ArrayList<Node>) state.node : new ArrayList<>(List.of((Node) state.node)));
			setParent(state.node, node);
		} else if (this.listener instanceof OnNodeActionListener) {
			node = ((OnNodeActionListener) this.listener).onNodeAction(new ArrayList<>(information.getTarget().subList(oldPosition, information.getPosition())), state.node instanceof ArrayList<?> ? (ArrayList<Node>) state.node : new ArrayList<>(List.of((Node) state.node)));
			setParent(state.node, node);
		} else if (this.listener instanceof OnSheetActionListener) {
			node = ((OnSheetActionListener) this.listener).onSheetAction(new ArrayList<>(information.getTarget().subList(oldPosition, information.getPosition())));
		}

		if (node != null) {
			node.setTag(this.listener.named);
		}

		return new Result(node);
	}

	private ArrayList<Node> setParent(Object object, Node parent) {
		if (object instanceof ArrayList<?> listNode) {
			for (Object o : listNode) {
				((Node) o).setParent(parent);
			}
			return (ArrayList<Node>) listNode;
		} else if (object instanceof Node node) {
			node.setParent(parent);
			return new ArrayList<>(List.of(node));
		}
		return null;
	}

	public sealed abstract static class OnActionListener permits OnNodeActionListener, OnSheetActionListener, OnTreeActionListener {
		private final String named;

		protected OnActionListener(String named) { this.named = named; }

		protected OnActionListener() { this.named = "Node"; }
	}

	public non-sealed static abstract class OnNodeActionListener extends OnActionListener {
		public OnNodeActionListener(String named) { super(named); }
		public OnNodeActionListener() { super(); }

		public abstract Node onNodeAction(ArrayList<Token> tokens, ArrayList<Node> nodes);
	}
	public non-sealed static abstract class OnSheetActionListener extends OnActionListener {
		public OnSheetActionListener(String named) { super(named); }
		public OnSheetActionListener() { super(); }

		public abstract Node onSheetAction(ArrayList<Token> tokens);
	}
	public non-sealed static abstract class OnTreeActionListener extends OnActionListener {
		public OnTreeActionListener(String named) { super(named); }
		public OnTreeActionListener() { super(); }

		public abstract Node onTreeAction(ArrayList<Node> nodes);
	}
}
