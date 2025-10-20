package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.parser.LanguageParserInformation;
import another.tool.recognition.language.trees.Node;

import java.util.ArrayList;

public final class OptionalOrMoreGrammatical implements Grammatical {
	private final Grammatical grammatical;

	public OptionalOrMoreGrammatical(Grammatical grammatical) { this.grammatical = grammatical; }

	public Result onGrammaticalExecute(LanguageParserInformation information) {
		ArrayList<Node> arrayList = new ArrayList<>();
		Result result;
		int index = 0;
		int oldCursor = information.getPosition();

		result = this.grammatical.onGrammaticalExecute(information);
		if (result == null) {
			information.setPosition(oldCursor);
			return null;
		}
		arrayList.add((Node) result.node);

		do {
			oldCursor = information.getPosition();
			result = this.grammatical.onGrammaticalExecute(information);
			arrayList.add(result == null ? null : (Node) result.node);
		} while (arrayList.get(index++) != null);

		if (arrayList.getLast() == null) arrayList.removeLast();

		information.setPosition(oldCursor);

		return new Result(arrayList);
	}

	@Override
	public String toString() {
		return "[" + this.grammatical.toString() + "]?+";
	}
}
