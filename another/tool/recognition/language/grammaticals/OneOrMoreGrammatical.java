package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.parser.LanguageParserInformation;
import another.tool.recognition.language.trees.Node;

import java.util.ArrayList;

public final class OneOrMoreGrammatical implements Grammatical {
	private final Grammatical grammatical;

	public OneOrMoreGrammatical(Grammatical grammatical) { this.grammatical = grammatical; }

	public Result onGrammaticalExecute(LanguageParserInformation information) {
		ArrayList<Node> arrayList = new ArrayList<>();
		int index = 0;

		arrayList.add((Node) this.grammatical.onGrammaticalExecute(information).node);
		if (arrayList.get(index++) == null) {
			return null;
		}

		int oldCursor;
		Result result;
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
		return '[' + this.grammatical.toString() + "]+";
	}
}
