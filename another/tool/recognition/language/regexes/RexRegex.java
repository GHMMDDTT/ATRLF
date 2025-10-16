package another.tool.recognition.language.regexes;

import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;

public final class RexRegex extends Regex {
	private final Regex regex;
	private final OnRexRegexListener listener;

	public RexRegex(Regex regex, OnRexRegexListener listener) {
		this.regex = regex;
		this.listener = listener;
	}

	public State onRegexExecute(LanguageTokenizerInformation information) {
		int oldCursor = information.getCursor();
		if (information.getCursor() >= information.getTarget().length) return State.FAILED;

		State state;
		if ((state = regex.onRegexExecute(information)) != State.FAILED) return state;

		Regex regex = this.listener.onRexRegex(new String(information.getTarget(), oldCursor - 1, information.getCursor() - oldCursor));
		if (regex != null) if ((state = regex.onRegexExecute(information)) != State.FAILED) return state;

		return State.SUCCESS;
	}

	@Override
	public String toString() {
		return this.regex.toString() + " -> {" + this.listener.toString() + '}';
	}

	public static abstract class OnRexRegexListener {
		public abstract Regex onRexRegex(String str);
	}
}
