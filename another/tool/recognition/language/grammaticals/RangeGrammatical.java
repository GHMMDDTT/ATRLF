package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.parser.LanguageParserInformation;

import java.util.ArrayList;

public final class RangeGrammatical implements Grammatical {
	private final Grammatical grammatical;
	private final int minimum;
	private final int maximum;

	public RangeGrammatical(Grammatical grammatical, int minimum, int maximum) {
		this.grammatical = grammatical;
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public Result onGrammaticalExecute(LanguageParserInformation information) {
		ArrayList<Result> arrayList = new ArrayList<>();

		if (information.getPosition() >= information.getTarget().size()) return null;

		int index = 0;
		int oldCursor = information.getPosition();
		while (index <= maximum) {
			arrayList.add(grammatical.onGrammaticalExecute(information));
			if (arrayList.get(index) == null) {
				if (index >= minimum) {
					information.setPosition(oldCursor);
					break;
				}
				else return null;
			}
			index++;
			oldCursor = information.getPosition();
		}
		return new Result(arrayList);
	}

	@Override
	public String toString() {
		return this.grammatical.toString() + '{' + this.minimum + '-' + this.maximum + '}';
	}
}
