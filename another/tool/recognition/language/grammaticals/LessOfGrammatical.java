package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.parser.LanguageParserInformation;
import another.tool.recognition.language.trees.Node;

import java.util.ArrayList;

public final class LessOfGrammatical implements Grammatical {
	private final Grammatical grammatical;
	private int minimum;

	public LessOfGrammatical(Grammatical grammatical, int minimum) {
		this.grammatical = grammatical;
		this.minimum = minimum;
	}

	public Result onGrammaticalExecute(LanguageParserInformation information) {
		ArrayList<Node> arrayList = new ArrayList<>();
		int index = 0;

		int oldCursor;
		Result result;
		do {
			result = this.grammatical.onGrammaticalExecute(information);
			arrayList.add(result == null ? null : (Node) result.node);
			oldCursor = information.getPosition();
			this.minimum--;
		} while (arrayList.get(index++) == null);

		information.setPosition(oldCursor);

		if (this.minimum >= 0) {
			return new Result(arrayList);
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return '[' + this.grammatical.toString() + "]<" + this.minimum;
	}
}
