package another.tool.recognition.language.tokenizer;

import another.tool.recognition.language.rules.*;

public abstract class RuleLanguageTokenizer {
	public AllRules All() { return new AllRules(); }
	public EmptyRules Empty() { return new EmptyRules(); }

	public CharacterRules Character(char target) { return new CharacterRules(target); }
	public CharacterRangeRules CharacterRange(char minimum, char maximum) { return new CharacterRangeRules(minimum, maximum); }
	public AnyOfRules AnyOf(char... targets) { return new AnyOfRules(targets); }
	public NoneOfRules NoneOf(char... targets) { return new NoneOfRules(targets); }

	public NotRules Not(Rules rules) { return new NotRules(rules); }
	public OptionalRules Optional(Rules rules) { return new OptionalRules(rules); }
	public RepeatRules Repeat(Rules rules, int repeat) { return new RepeatRules(rules, repeat); }
	public RangeRules Range(Rules rules, int minimum, int maximum) { return new RangeRules(rules, minimum, maximum); }

	public OneOrMoreRules OneOrMore(Rules rules) { return new OneOrMoreRules(rules); }
	public OptionalOrMoreRules OptionalOrMore(Rules rules) { return new OptionalOrMoreRules(rules); }
	public MoreRules More(Rules rules) { return new MoreRules(rules); }
	public MoreOfRules MoreOf(Rules rules, int minimum) { return new MoreOfRules(rules, minimum); }
	public LessOfRules LessOf(Rules rules, int maximum) { return new LessOfRules(rules, maximum); }

	public GroupRules Group(Rules rules) { return new GroupRules(rules); }
	public SkipRules Skip(Rules rules) { return new SkipRules(rules); }

	public RulRules Rul(Rules rules, RulRules.OnRulRulesListener listener) { return new RulRules(rules, listener); }

	public SequenceRules Sequence(Rules... rules) { return new SequenceRules(rules); }
	public FirstOfRules FirstOf(Rules... rules) { return new FirstOfRules(rules); }

	public TokenRules Token(Rules rules, Enum<?> type) { return new TokenRules(rules, type); }
	public TokenRules Token(Rules rules, TokenRules.OnActionTokenSyntaxListener type) { return new TokenRules(rules, type); }

	public abstract Rules onRegex();
	public abstract Rules onRegexSkipped();
}
