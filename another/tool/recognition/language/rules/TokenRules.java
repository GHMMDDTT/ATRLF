package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;
import another.tool.recognition.language.tokenizer.Token;

public final class TokenRules extends Rules {
	private final Rules rules;
	private final Enum<?>[] type;
	private final OnActionTokenSyntaxListener listener;

	public TokenRules(Rules rules, Enum<?>... type) {
		this.rules = rules;
		this.type = type;
		this.listener = null;
	}

	public TokenRules(Rules rules, OnActionTokenSyntaxListener listener) {
		if (listener == null) {
			System.err.println("Listener is null!!");
			System.exit(-1);
		}

		this.rules = rules;
		this.type = null;
		this.listener = listener;
	}

	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		int offset = information.getPosition();
		Result<?> result;
		if ((result = this.rules.onRegexExecute(information)).isFailed()) {
			return result;
		}
		String str = new String(information.getTarget(), offset, information.getPosition() - offset);
		return new Result<>(new Token(result, this.type == null ? this.listener.onActionTokenSyntax(result, str) : this.type, information.getLine()), true);
	}

	@Override
	public String toString() {
		return this.rules.toString();
	}

	public static abstract class OnActionTokenSyntaxListener {
		public abstract Enum<?>[] onActionTokenSyntax(Result<?> result, String text);
	}
}
