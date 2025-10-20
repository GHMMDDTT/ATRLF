package another.tool.recognition.language.tokenizer;

import another.tool.recognition.language.rules.*;

public abstract class RuleLanguageTokenizer {
	public final AllRule All() { return new AllRule(); }
	public final EmptyRule Empty() { return new EmptyRule(); }

	public final CharacterRule Character(char target) { return new CharacterRule(target); }
	public final CharacterRangeRule CharacterRange(char minimum, char maximum) { return new CharacterRangeRule(minimum, maximum); }
	public final AnyOfRule AnyOf(char... targets) { return new AnyOfRule(targets); }
	public final NoneOfRule NoneOf(char... targets) { return new NoneOfRule(targets); }

	public final NotRule Not(Rule rules) { return new NotRule(rules); }
	public final OptionalRule Optional(Rule rules) { return new OptionalRule(rules); }
	public final RepeatRule Repeat(Rule rules, int repeat) { return new RepeatRule(rules, repeat); }
	public final RangeRule Range(Rule rules, int minimum, int maximum) { return new RangeRule(rules, minimum, maximum); }

	public final OneOrMoreRule OneOrMore(Rule rules) { return new OneOrMoreRule(rules); }
	public final OptionalOrMoreRule OptionalOrMore(Rule rules) { return new OptionalOrMoreRule(rules); }
	public final MoreRule More(Rule rules) { return new MoreRule(rules); }
	public final MoreOfRule MoreOf(Rule rules, int minimum) { return new MoreOfRule(rules, minimum); }
	public final LessOfRule LessOf(Rule rules, int maximum) { return new LessOfRule(rules, maximum); }

	public final RulRule Rul(Rule rules, RulRule.OnRulRuleListener listener) { return new RulRule(rules, listener); }

	public final SequenceRule Sequence(Rule... rules) { return new SequenceRule(rules); }
	public final FirstOfRule FirstOf(Rule... rules) { return new FirstOfRule(rules); }

	public final TokenRule Token(Rule rules, Enum<?> type) { return new TokenRule(rules, type); }
	public final TokenRule Token(Rule rules, TokenRule.OnActionTokenSyntaxListener type) { return new TokenRule(rules, type); }

	public abstract Rule onRegex();
	public abstract Rule onRegexSkipped();
}
