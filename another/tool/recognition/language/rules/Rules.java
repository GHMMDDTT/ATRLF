package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public abstract class Rules {
	public abstract Result<?> onRegexExecute(LanguageTokenizerInformation information);
}
