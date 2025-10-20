package another.tool.recognition.language.parser;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.grammaticals.Grammatical;
import another.tool.recognition.language.tokenizer.LanguageTokenizer;
import another.tool.recognition.language.tokenizer.Token;
import another.tool.recognition.language.trees.Node;

import java.util.ArrayList;

public class LanguageParser {
	private final LanguageParserInformation information;
	private final SyntaxLanguageParser syntax;

	public LanguageParser(ArrayList<Token> tokens, SyntaxLanguageParser syntax) {
		this.information = new LanguageParserInformation(tokens);
		this.syntax = syntax;
	}

	public LanguageParser(LanguageTokenizer tokenizer, SyntaxLanguageParser syntax) {
		this.information = new LanguageParserInformation(tokenizer.onTokenizer());
		this.syntax = syntax;
	}

	public <T extends Node> T onParser() {
		Grammatical syntaxes = this.syntax.onGrammatical();
		Result state = syntaxes.onGrammaticalExecute(this.information);

		if (state == null) {
			throw new RuntimeException("Contains error the syntax: " + syntaxes + " in " + this.information.getTarget().get(this.information.getPosition()).getLine() + " of the input: '" + this.information.getTarget().get(this.information.getPosition()).getValue() + '\'');
		}

		return (T) state.node;
	}
}
