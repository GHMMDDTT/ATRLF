package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;
import another.tool.recognition.language.tokenizer.Token;

public final class TokenRule extends Rule {
	private final Rule rule;
	private final Enum<?>[] type;
	private final OnActionTokenSyntaxListener listener;

	public TokenRule(Rule rule, Enum<?>... type) {
		this.rule = rule;
		this.type = type;
		this.listener = null;
	}

	public TokenRule(Rule rule, OnActionTokenSyntaxListener listener) {
		this.rule = rule;
		this.type = null;
		this.listener = listener;
	}

	public State onRuleExecute(LanguageTokenizerInformation information) {
		int offset = information.getPosition();
		State state;
		if ((state = this.rule.onRuleExecute(information)) != State.SUCCESS) {
			return state;
		}
		String str = new String(information.getTarget(), offset, information.getPosition() - offset);
		information.addToken(new Token(str, this.type == null && this.listener != null ? this.listener.onActionTokenSyntax(str) : type, information.getLine()));
		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return this.rule.toString();
	}

	public static abstract class OnActionTokenSyntaxListener {
		public abstract Enum<?>[] onActionTokenSyntax(String text);
	}
}
