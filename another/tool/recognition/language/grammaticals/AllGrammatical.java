package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.parser.LanguageParserInformation;
import another.tool.recognition.language.trees.Node;

public final class AllGrammatical implements Grammatical {
	public Result onGrammaticalExecute(LanguageParserInformation information) {
		information.setNextPosition();
		return new Result(null);
	}

	@Override
	public String toString() {
		return ".";
	}
}
