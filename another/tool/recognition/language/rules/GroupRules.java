package another.tool.recognition.language.rules;

import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.tokenizer.LanguageTokenizerInformation;
import another.tool.recognition.language.tokenizer.Token;

import java.util.ArrayList;

public class GroupRules extends Rules {
	private final Rules rules;

	public GroupRules(Rules rules) { this.rules = rules; }

	@Override
	public Result<?> onRegexExecute(LanguageTokenizerInformation information) {
		Result<?> state;

		if ((state = this.rules.onRegexExecute(information)).isFailed()) return state;

		return new Result<>(toStringGroup(state), true);
	}

	@Override
	public String toString() {
		return this.rules.toString();
	}

	public ArrayList<Object> toStringGroup(Result<?> state) {
		ArrayList<Object> resultList = new ArrayList<>();
		Object value = state.getValue();

		switch (value) {
			case ArrayList<?> list -> {
				StringBuilder sb = new StringBuilder();
				for (Object item : list) {
					switch (item) {
						case Token token -> {
							if (!sb.isEmpty()) {
								resultList.add(sb.toString());
								sb.setLength(0);
							}
							resultList.add(token);
						}
						case Result<?> nestedResult -> sb.append(toString(toStringGroup(nestedResult)));
						case ArrayList<?> nestedList -> sb.append(toString(nestedList));
						case Character ch -> sb.append(ch);
						default -> sb.append(item);
					}
				}
				if (!sb.isEmpty()) {
					resultList.add(sb.toString());
				}
			}
			case Character ch -> resultList.add(ch);
			case Result<?> nestedResult -> resultList.add(toStringGroup(nestedResult));
			default -> resultList.add(value.toString());
		}

		return resultList;
	}

	public String toString(ArrayList<?> list) {
		StringBuilder sb = new StringBuilder();

		for (Object item : list) {
			if (item instanceof Character ch) {
				sb.append(ch);
			} else if (item instanceof Result<?> nestedResult) {
				sb.append(toString(toStringGroup(nestedResult)));
			} else if (item instanceof ArrayList<?> nestedList) {
				sb.append(toString(nestedList));
			} else if (item instanceof Token token) {
				sb.append(token);
			} else if (item != null) {
				sb.append(item);
			}
		}

		return sb.toString();
	}
}
