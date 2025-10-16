package another.tool.recognition.language.tokenizer;

import another.tool.recognition.language.regexes.*;

public abstract class RegexLanguageTokenizer {
	public AnyRegex Any() { return new AnyRegex(); }
	public EmptyRegex Empty() { return new EmptyRegex(); }

	public CharacterRegex Character(char target) { return new CharacterRegex(target); }
	public CharacterRangeRegex CharacterRange(char minimum, char maximum) { return new CharacterRangeRegex(minimum, maximum); }

	public NotRegex Not(Regex syntaxes) { return new NotRegex(syntaxes); }
	public OneOrMoreRegex OneOrMore(Regex syntaxes) { return new OneOrMoreRegex(syntaxes); }
	public OptionalRegex Optional(Regex syntaxes) { return new OptionalRegex(syntaxes); }
	public OptionalOrMoreRegex OptionalOrMore(Regex syntaxes) { return new OptionalOrMoreRegex(syntaxes); }
	public RepeatRegex Repeat(Regex syntaxes, int repeat) { return new RepeatRegex(syntaxes, repeat); }
	public RangeRegex Range(Regex syntaxes, int minimum, int maximum) { return new RangeRegex(syntaxes, minimum, maximum); }
	public RexRegex Rex(Regex syntaxes, RexRegex.OnRexRegexListener listener) { return new RexRegex(syntaxes, listener); }

	public SequenceRegex Sequence(Regex... regexes) { return new SequenceRegex(regexes); }
	public AlternativesRegex Alternatives(Regex... regexes) { return new AlternativesRegex(regexes); }

	public TokenRegex Token(Regex regex, Enum<?> type) { return new TokenRegex(regex, type); }
	public TokenRegex Token(Regex regex, TokenRegex.OnActionTokenSyntaxListener type) { return new TokenRegex(regex, type); }

	public abstract Regex onRegex();
	public abstract Regex onRegexSkipped();
}
