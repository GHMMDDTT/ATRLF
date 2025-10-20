package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.parser.LanguageParserInformation;

import java.util.ArrayList;

public final class RepeatGrammatical implements Grammatical {
	private final Grammatical grammatical;
	private final int repeat;

	public RepeatGrammatical(Grammatical grammatical, int repeat) {
		this.grammatical = grammatical;
		this.repeat = repeat;
	}

	public Result onGrammaticalExecute(LanguageParserInformation information) {
		ArrayList<Result> state = new ArrayList<>();

		if (information.getPosition() >= information.getTarget().size()) return null;

		int index = 0;
		while (index != repeat) {
			state.add(grammatical.onGrammaticalExecute(information));
			if (state.get(index) == null) return null;
			index++;
		}
		return new Result(state);
	}

	@Override
	public String toString() {
		return this.grammatical.toString() + '{' + this.repeat + '}';
	}
}
