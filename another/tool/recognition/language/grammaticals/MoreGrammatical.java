package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.parser.LanguageParserInformation;
import another.tool.recognition.language.trees.Node;

import java.util.ArrayList;

public final class MoreGrammatical implements Grammatical {
	private final Grammatical rules;

	public MoreGrammatical(Grammatical rules) { this.rules = rules; }

	public Result onGrammaticalExecute(LanguageParserInformation information) {
		ArrayList<Node> arrayList = new ArrayList<>();
		Result result;
		int index = 0;

		result = this.rules.onGrammaticalExecute(information);
		if (result == null) {
			return null;
		}
		arrayList.add((Node) result.node);

		result = this.rules.onGrammaticalExecute(information);
		if (result == null) {
			return null;
		}
		arrayList.add((Node) result.node);

		int oldCursor;
		do {
			oldCursor = information.getPosition();
			result = this.rules.onGrammaticalExecute(information);
			arrayList.add(result == null ? null : (Node) result.node);
		} while (arrayList.get(index++) != null);

		if (arrayList.getLast() == null) arrayList.removeLast();

		information.setPosition(oldCursor);

		return new Result(arrayList);
	}

	@Override
	public String toString() {
		return '[' + this.rules.toString() + "]++";
	}
}
