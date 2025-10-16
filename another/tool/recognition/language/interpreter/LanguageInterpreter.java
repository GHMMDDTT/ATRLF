package another.tool.recognition.language.interpreter;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.syntaxes.Syntax;
import another.tool.recognition.language.tokenizer.LanguageTokenizer;
import another.tool.recognition.language.tokenizer.Token;

import java.util.ArrayList;

public class LanguageInterpreter {
	private final LanguageInterpreterInformation information;
	private final SyntaxLanguageInterpreter syntax;

	public LanguageInterpreter(ArrayList<Token> tokens, SyntaxLanguageInterpreter syntax) {
		this.information = new LanguageInterpreterInformation(tokens);
		this.syntax = syntax;
	}

	public LanguageInterpreter(LanguageTokenizer tokenizer, SyntaxLanguageInterpreter syntax) {
		this.information = new LanguageInterpreterInformation(tokenizer.onTokenizer());
		this.syntax = syntax;
	}

	public void onSyntax() {
		Syntax syntaxes = this.syntax.onSyntax();
		State state = syntaxes.onSyntaxExecute(this.information);

		if (state == State.FAILED) {
			throw new RuntimeException("Contains error the syntax: " + syntaxes + " in " + this.information.getTarget().get(this.information.getCursor()).getLine() + " of the input: '" + this.information.getTarget().get(this.information.getCursor()).getValue() + '\'');
		}
	}
}
