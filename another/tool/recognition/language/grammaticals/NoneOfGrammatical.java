package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.parser.LanguageParserInformation;

import java.util.Arrays;

public final class NoneOfGrammatical implements Grammatical {
	private final Enum<?>[] target;

	public NoneOfGrammatical(Enum<?>[] target) { this.target = target; }

	@Override
	public String toString() {
		return Arrays.toString(this.target);
	}

	@Override
	public Result onGrammaticalExecute(LanguageParserInformation information) {
		for (Enum<?> Char : this.target) {
			for (Enum<?> subtarget : information.getTarget().get(information.getPosition()).getType()) {
				if (subtarget == Char){
					information.setNextPosition();
					return new Result(null);
				}
			}
		}
		return null;
	}
}
