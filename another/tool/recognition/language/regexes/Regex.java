package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public abstract class Regex {
	public abstract State onRegexExecute(LanguageTokenizerInformation information);

}
