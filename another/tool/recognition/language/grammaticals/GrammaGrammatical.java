package another.tool.recognition.language.grammaticals;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.parser.LanguageParserInformation;
import another.tool.recognition.language.tokenizer.Token;
import another.tool.recognition.language.trees.Node;

import java.util.ArrayList;

public final class GrammaGrammatical implements Grammatical {
	private final Grammatical grammatical;
	private final OnGrammaGrammaticalListener listener;

	public GrammaGrammatical(Grammatical grammatical, OnGrammaGrammaticalListener listener) {
		this.grammatical = grammatical;
		this.listener = listener;
	}

	public Result onGrammaticalExecute(LanguageParserInformation information) {
		int oldCursor = information.getPosition();
		if (information.getPosition() >= information.getTarget().size()) return null;

		Result state;
		if ((state = this.grammatical.onGrammaticalExecute(information)) == null) return state;

		Grammatical grammatical = this.listener.onGrammaGrammatical((ArrayList<Token>) information.getTarget().subList(oldCursor, information.getPosition()));
		if (grammatical != null) if ((state = grammatical.onGrammaticalExecute(information)) != null) return state;

		return state;
	}

	@Override
	public String toString() {
		return this.grammatical.toString() + " -> {" + this.listener.toString() + '}';
	}

	public static abstract class OnGrammaGrammaticalListener {
		public abstract Grammatical onGrammaGrammatical(ArrayList<Token> str);
	}
}
