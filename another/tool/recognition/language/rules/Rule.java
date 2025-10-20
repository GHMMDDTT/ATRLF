package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public abstract class Rule {
	public abstract State onRuleExecute(LanguageTokenizerInformation information);
}
