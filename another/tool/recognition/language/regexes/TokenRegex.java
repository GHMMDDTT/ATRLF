package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;
import another.tool.recognition.language.tokenizer.Token;

public final class TokenRegex extends Regex {
	private final Regex regex;
	private final Enum<?>[] type;
	private final OnActionTokenSyntaxListener listener;

	public TokenRegex(Regex regex, Enum<?>... type) {
		this.regex = regex;
		this.type = type;
		this.listener = null;
	}

	public TokenRegex(Regex regex, OnActionTokenSyntaxListener listener) {
		this.regex = regex;
		this.type = null;
		this.listener = listener;
	}

	public State onRegexExecute(LanguageTokenizerInformation information) {
		int offset = information.getCursor();
		State state;
		if ((state = this.regex.onRegexExecute(information)) != State.SUCCESS) {
			return state;
		}
		String str = new String(information.getTarget(), offset, information.getCursor() - offset);
		information.addToken(new Token(str, this.type == null && this.listener != null ? this.listener.onActionTokenSyntax(str) : type, information.getLine()));
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return this.regex.toString();
	}

	public static abstract class OnActionTokenSyntaxListener {
		public abstract Enum<?>[] onActionTokenSyntax(String text);
	}
}
