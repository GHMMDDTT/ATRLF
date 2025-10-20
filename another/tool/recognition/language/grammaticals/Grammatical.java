package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.parser.LanguageParserInformation;

public interface Grammatical {
	public abstract Result onGrammaticalExecute(LanguageParserInformation information);
}
