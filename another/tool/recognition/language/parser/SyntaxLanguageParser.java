package another.tool.recognition.language.parser;

import another.tool.recognition.language.grammaticals.*;

public abstract class SyntaxLanguageParser {
	public final AllGrammatical All() { return new AllGrammatical(); }

	public final AnyOfGrammatical AnyOf(Enum<?>... targets) { return new AnyOfGrammatical(targets); }
	public final NoneOfGrammatical NoneOf(Enum<?>... targets) { return new NoneOfGrammatical(targets); }

	public final NotGrammatical Not(Grammatical rules) { return new NotGrammatical(rules); }
	public final OptionalGrammatical Optional(Grammatical rules) { return new OptionalGrammatical(rules); }
	public final RepeatGrammatical Repeat(Grammatical rules, int repeat) { return new RepeatGrammatical(rules, repeat); }
	public final RangeGrammatical Range(Grammatical rules, int minimum, int maximum) { return new RangeGrammatical(rules, minimum, maximum); }

	public final OneOrMoreGrammatical OneOrMore(Grammatical rules) { return new OneOrMoreGrammatical(rules); }
	public final OptionalOrMoreGrammatical OptionalOrMore(Grammatical rules) { return new OptionalOrMoreGrammatical(rules); }
	public final MoreGrammatical More(Grammatical rules) { return new MoreGrammatical(rules); }
	public final MoreOfGrammatical MoreOf(Grammatical rules, int minimum) { return new MoreOfGrammatical(rules, minimum); }
	public final LessOfGrammatical LessOf(Grammatical rules, int maximum) { return new LessOfGrammatical(rules, maximum); }

	public final GrammaGrammatical Syn(Grammatical rules, GrammaGrammatical.OnGrammaGrammaticalListener listener) { return new GrammaGrammatical(rules, listener); }

	public final SequenceGrammatical Sequence(Grammatical... rules) { return new SequenceGrammatical(rules); }
	public final FirstOfGrammatical FirstOf(Grammatical... rules) { return new FirstOfGrammatical(rules); }

	public final TextGrammatical Text(String str) { return new TextGrammatical(str); }

	public final NodeGrammatical Node(Grammatical grammatical, NodeGrammatical.OnTreeActionListener listener) { return new NodeGrammatical(grammatical, listener); }
	public final NodeGrammatical Node(Grammatical grammatical, NodeGrammatical.OnNodeActionListener listener) { return new NodeGrammatical(grammatical, listener); }
	public final NodeGrammatical Node(Grammatical grammatical, NodeGrammatical.OnSheetActionListener listener) { return new NodeGrammatical(grammatical, listener); }

	public abstract Grammatical onGrammatical();
}
