package another.tool.recognition.language.interpreter;

import another.tool.recognition.language.syntaxes.*;

public abstract class SyntaxLanguageInterpreter {
	public AnySyntax Any() { return new AnySyntax(); }

	public NotSyntax Not(Syntax syntaxes) { return new NotSyntax(syntaxes); }
	public OneOrMoreSyntax OneOrMore(Syntax syntaxes) { return new OneOrMoreSyntax(syntaxes); }
	public OptionalSyntax Optional(Syntax syntaxes) { return new OptionalSyntax(syntaxes); }
	public OptionalOrMoreSyntax OptionalOrMore(Syntax syntaxes) { return new OptionalOrMoreSyntax(syntaxes); }
	public RepeatSyntax Repeat(Syntax syntaxes, int repeat) { return new RepeatSyntax(syntaxes, repeat); }
	public RangeSyntax Range(Syntax syntaxes, int minimum, int maximum) { return new RangeSyntax(syntaxes, minimum, maximum); }

	public SequenceSyntax Sequence(Syntax... syntaxes) { return new SequenceSyntax(syntaxes); }
	public AlternativesSyntax Alternatives(Syntax... syntaxes) { return new AlternativesSyntax(syntaxes); }

	public abstract Syntax onSyntax();
}
