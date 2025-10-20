package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.parser.LanguageParserInformation;

import java.util.ArrayList;
import java.util.Arrays;

public final class SequenceGrammatical implements Grammatical {
	private final Grammatical[] grammaticals;

	public SequenceGrammatical(Grammatical[] grammaticals) { this.grammaticals = grammaticals; }

	public Result onGrammaticalExecute(LanguageParserInformation information) {
		ArrayList<Result> state = new ArrayList<>();
		int index = 0;

		for (Grammatical grammatical : this.grammaticals) {
			state.add(grammatical.onGrammaticalExecute(information));
			if (state.get(index++) == null) return null;
		}
		return new Result(state);
	}

	@Override
	public String toString() {
		return Arrays.toString(this.grammaticals);
	}
}
