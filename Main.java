import another.tool.recognition.language.regexes.Regex;
import another.tool.recognition.language.regexes.RexRegex;
import another.tool.recognition.language.regexes.TokenRegex;
import another.tool.recognition.language.tokenizer.LanguageTokenizer;
import another.tool.recognition.language.tokenizer.RegexLanguageTokenizer;
import another.tool.recognition.language.tokenizer.Token;

import java.util.ArrayList;

void main() {
	long start = System.nanoTime();
	LanguageTokenizer language = new LanguageTokenizer("""
s"Hello"; """/*.repeat(6513)*/, new RegexLanguageTokenizer() {
		@Override
		public Regex onRegex() {
			return Alternatives(
					identifier(),
					string(),
					character(),
					symbols()
			);
		}

		private Regex string() {
			return Sequence(
					Character('"'),
					OneOrMore(Not(Character('"'))),
					Character('"')
			);
		}

		private Regex character() {
			return Token(
					Sequence(
							Character('\''),
							Not(Character('\'')),
							Character('\'')
					), MyTokens.CharacterLiteralToken
			);
		}

		private Regex symbols() {
			return Alternatives(
					// Symbols Delimiter Separator Operators
					Token(Character('('), MyTokens.ParenthesisLeftSymbolDelimiterSeparatorOperatorToken),
					Token(Character(')'), MyTokens.ParenthesisRightSymbolDelimiterSeparatorOperatorToken),
					Token(Character('['), MyTokens.SquareLeftSymbolDelimiterSeparatorOperatorToken),
					Token(Character(']'), MyTokens.SquareRightSymbolDelimiterSeparatorOperatorToken),
					Token(Character('{'), MyTokens.CurlyLeftSymbolDelimiterSeparatorOperatorToken),
					Token(Character('}'), MyTokens.CurlyRightSymbolDelimiterSeparatorOperatorToken),

					// Symbols Delimiters Operators;
					Token(Character(';'), MyTokens.SemicolonSymbolDelimiterOperatorToken),
					Token(Character(':'), MyTokens.ColonSymbolDelimiterOperatorToken),
					Token(Character(','), MyTokens.CommaSymbolDelimiterOperatorToken),
					Token(Character('.'), MyTokens.DotSymbolDelimiterOperatorToken),

					// Symbols Operators;
					Token(Character('='), MyTokens.EqualSymbolOperatorToken),
					Token(Character('&'), MyTokens.AndSymbolOperatorToken),
					Token(Character('|'), MyTokens.VerticalLineSymbolOperatorToken),
					Token(Character('!'), MyTokens.NotSymbolOperatorToken),
					Token(Character('?'), MyTokens.QuestionSymbolOperatorToken),
					Token(Character('<'), MyTokens.LessThanSymbolOperatorToken),
					Token(Character('>'), MyTokens.GreaterThanSymbolOperatorToken),

					// Symbols Arithmetical Operators:
					Token(Character('+'), MyTokens.PlusSymbolArithmeticalOperatorToken),
					Token(Character('-'), MyTokens.MinusSymbolArithmeticalOperatorToken),
					Token(Character('*'), MyTokens.StartSymbolArithmeticalOperatorToken),
					Token(Character('/'), MyTokens.SlashSymbolArithmeticalOperatorToken),
					Token(Character('%'), MyTokens.ModuleSymbolArithmeticalOperatorToken),
					Token(Character('^'), MyTokens.ExponentSymbolArithmeticalOperatorToken),

					// Symbols
					Token(Character('@'), MyTokens.AtSymbolToken),
					Token(Character('_'), MyTokens.LowLineSymbolToken),
					Token(Character('~'), MyTokens.TildeSymbolToken)
			);
		}

		private Regex identifier() {
			return Token(
					Rex(
							Sequence(
									Alternatives(
											CharacterRange('a', 'z'),
											CharacterRange('A', 'Z')
									)
							), new RexRegex.OnRexRegexListener() {
								@Override
								public Regex onRexRegex(String str) {
									if (str.equalsIgnoreCase("s")) return string();
									return OptionalOrMore(
											Alternatives(
													CharacterRange('a', 'z'),
													CharacterRange('A', 'Z'),
													CharacterRange('0', '9'),
													Character('_'),
													Character('$')
											)
									);
								}
							}
					), new TokenRegex.OnActionTokenSyntaxListener() {
						@Override
						public Enum<?>[] onActionTokenSyntax(String text) {
							return switch (text) {
								case "break" -> new Enum[] { MyTokens.BreakKeywordControlToken };
								case "continue" -> new Enum[] { MyTokens.ContinueKeywordControlToken };
								case "module" -> new Enum[] { MyTokens.ModuleKeywordToken };
								case "package" -> new Enum[] { MyTokens.PackageKeywordToken };
								case "import" -> new Enum[] { MyTokens.ImportKeywordToken };
								case "from" -> new Enum[] { MyTokens.FromKeywordToken };
								case "using" -> new Enum[] { MyTokens.UsingKeywordToken };
								case "as" -> new Enum[] { MyTokens.AsKeywordToken };
								case "protected" -> new Enum[] { MyTokens.ProtectedKeywordToken };
								case "public" -> new Enum[] { MyTokens.PublicKeywordToken };
								case "private" -> new Enum[] { MyTokens.PrivateKeywordToken };
								case "switch" -> new Enum[] { MyTokens.SwitchKeywordToken };
								case "case" -> new Enum[] { MyTokens.CaseKeywordToken };
								case "sealed" -> new Enum[] { MyTokens.SealedKeywordToken };
								case "external" -> new Enum[] { MyTokens.ExternalKeywordToken };
								case "internal" -> new Enum[] { MyTokens.InternalKeywordToken };
								case "inner" -> new Enum[] { MyTokens.InnerKeywordToken };
								case "exner" -> new Enum[] { MyTokens.ExnerKeywordToken };
								case "open" -> new Enum[] { MyTokens.OpenKeywordToken };
								case "close" -> new Enum[] { MyTokens.CloseKeywordToken };
								case "friend" -> new Enum[] { MyTokens.FriendKeywordToken };
								case "companion" -> new Enum[] { MyTokens.CompanionKeywordToken };
								case "final" -> new Enum[] { MyTokens.FinalKeywordToken };
								case "abstract" -> new Enum[] { MyTokens.AbstractKeywordToken };
								case "class" -> new Enum[] { MyTokens.ClassKeywordToken };
								case "enumerate" -> new Enum[] { MyTokens.EnumerateKeywordToken };
								case "structure" -> new Enum[] { MyTokens.StructureKeywordToken };
								case "record" -> new Enum[] { MyTokens.RecordKeywordToken };
								case "data" -> new Enum[] { MyTokens.DataKeywordToken };
								case "interface" -> new Enum[] { MyTokens.InterfaceKeywordToken };
								case "annotation" -> new Enum[] { MyTokens.AnnotationKeywordToken };
								case "attribute" -> new Enum[] { MyTokens.AttributeKeywordToken };
								case "trait" -> new Enum[] { MyTokens.TraitKeywordToken };
								case "extend" -> new Enum[] { MyTokens.ExtendKeywordToken };
								case "extends" -> new Enum[] { MyTokens.ExtendsKeywordToken };
								case "implement" -> new Enum[] { MyTokens.ImplementKeywordToken };
								case "implements" -> new Enum[] { MyTokens.ImplementsKeywordToken };
								case "companions" -> new Enum[] { MyTokens.CompanionsKeywordToken };
								case "friends" -> new Enum[] { MyTokens.FriendsKeywordToken };
								case "restriction" -> new Enum[] { MyTokens.RestrictionKeywordToken };
								case "restrictions" -> new Enum[] { MyTokens.RestrictionsKeywordToken };
								case "permit" -> new Enum[] { MyTokens.PermitKeywordToken };
								case "permits" -> new Enum[] { MyTokens.PermitsKeywordToken };
								case "with" -> new Enum[] { MyTokens.WithKeywordToken };
								case "withs" -> new Enum[] { MyTokens.WithsKeywordToken };
								case "set" -> new Enum[] { MyTokens.SetKeywordToken };
								case "get" -> new Enum[] { MyTokens.GetKeywordToken };
								case "local" -> new Enum[] { MyTokens.LocalKeywordToken };
								case "global" -> new Enum[] { MyTokens.GlobalKeywordToken };
								case "readonly" -> new Enum[] { MyTokens.ReadonlyKeywordToken };
								case "constant" -> new Enum[] { MyTokens.ConstantKeywordToken };
								case "writeonly" -> new Enum[] { MyTokens.WriteonlyKeywordToken };
								case "native" -> new Enum[] { MyTokens.NativeKeywordToken };
								case "overwrite" -> new Enum[] { MyTokens.OverwriteKeywordToken };
								case "write" -> new Enum[] { MyTokens.WriteKeywordToken };
								case "function" -> new Enum[] { MyTokens.FunctionKeywordToken };
								case "definition" -> new Enum[] { MyTokens.DefinitionKeywordToken };
								case "operator" -> new Enum[] { MyTokens.OperatorKeywordToken };
								case "prefix" -> new Enum[] { MyTokens.PrefixKeywordToken };
								case "suffix" -> new Enum[] { MyTokens.SuffixKeywordToken };
								case "control" -> new Enum[] { MyTokens.ControlKeywordToken };
								case "infix" -> new Enum[] { MyTokens.InfixKeywordToken };
								case "constructor" -> new Enum[] { MyTokens.ConstructorKeywordToken };
								case "throw" -> new Enum[] { MyTokens.ThrowKeywordToken };
								case "throws" -> new Enum[] { MyTokens.ThrowsKeywordToken };
								case "namespace" -> new Enum[] { MyTokens.NamespaceKeywordToken };
								case "in" -> new Enum[] { MyTokens.InKeywordToken };
								case "out" -> new Enum[] { MyTokens.OutKeywordToken };
								case "so" -> new Enum[] { MyTokens.SoKeywordToken };
								case "of" -> new Enum[] { MyTokens.OfKeywordToken };
								case "to" -> new Enum[] { MyTokens.ToKeywordToken };
								case "let" -> new Enum[] { MyTokens.LetKeywordToken };
								case "type" -> new Enum[] { MyTokens.TypeKeywordToken };
								case "super" -> new Enum[] { MyTokens.SuperKeywordToken };
								case "base" -> new Enum[] { MyTokens.BaseKeywordToken };
								case "this" -> new Enum[] { MyTokens.ThisKeywordToken };
								case "self" -> new Enum[] { MyTokens.SelfKeywordToken };
								case "next" -> new Enum[] { MyTokens.NextKeywordToken };
								case "previous" -> new Enum[] { MyTokens.PreviousKeywordToken };
								case "return" -> new Enum[] { MyTokens.ReturnKeywordToken };
								case "new" -> new Enum[] { MyTokens.NewKeywordToken };
								case "false" -> new Enum[] { MyTokens.FalseKeywordLiteralToken };
								case "true" -> new Enum[] { MyTokens.TrueKeywordLiteralToken };
								case "indeterminate" -> new Enum[] { MyTokens.IndeterminateKeywordLiteralToken };
								case "undefined" -> new Enum[] { MyTokens.UndefinedKeywordLiteralToken };
								case "NaN" -> new Enum[] { MyTokens.NaNKeywordLiteralToken };
								case "NA" -> new Enum[] { MyTokens.NAKeywordLiteralToken };
								case "null" -> new Enum[] { MyTokens.NullKeywordLiteralToken };
								case "none" -> new Enum[] { MyTokens.NoneKeywordLiteralToken };
								case "infinity" -> new Enum[] { MyTokens.InfinityKeywordLiteralToken };
								case "neutral" -> new Enum[] { MyTokens.NeutralKeywordLiteralToken };
								default -> {
									if (text.startsWith("s\"") && text.endsWith("\"")) yield new Enum[] { MyTokens.StringLiteralToken };
									yield  new Enum[] { MyTokens.IdentifierToken };
								}
							};
						}
					}
			);
		}

		@Override
		public Regex onRegexSkipped() {
			return OneOrMore(Alternatives(Character(' '), Character('\r'), Character('\n'), Character('\t')));
		}
	});
	ArrayList<Token> tokens = language.onTokenizer();
	long end = System.nanoTime();
	System.out.println("Time of Execute this is in: " + ((end - start) / 1_000_000) + "ms");
	System.out.println("Tokens: " + tokens);
}
