import another.tool.recognition.language.rules.Rule;
import another.tool.recognition.language.rules.TokenRule;
import another.tool.recognition.language.tokenizer.RuleLanguageTokenizer;

public class MainTokenizer extends RuleLanguageTokenizer {
	@Override
	public Rule onRegex() {
		return FirstOf(
				identifier(),
				string(),
				character(),
				number(),
				symbols()
		);
	}

	private Rule string() {
		return Token(
				Sequence(
						Character('"'),
						OptionalOrMore(Not(Character('"'))),
						Character('"')
				), MyTokens.StringLiteralToken
		);
	}

	private Rule character() {
		return Token(
				Sequence(
						Character('\''),
						Not(Character('\'')),
						Character('\'')
				),MyTokens.CharacterLiteralToken
		);
	}

	private Rule symbols() {
		return FirstOf(
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

	private Rule number() {
		return Token(
				Sequence(
						CharacterRange('1', '9'),
						OptionalOrMore(
								CharacterRange('0', '9')
						), Optional(
								FirstOf(
										Sequence(
												Character('.'),
												OneOrMore(
														CharacterRange('0', '9')
												), Optional(
														FirstOf(
																Character('F'), Character('f'),
																Character('D'), Character('d')
														)
												)
										),
										Character('F'), Character('f'),
										Character('D'), Character('d'),
										Character('L'), Character('l')
								)
						)
				), new TokenRule.OnActionTokenSyntaxListener() {
					@Override
					public Enum<?>[] onActionTokenSyntax(String text) {
						if (text.contains(".")) {
							if (text.endsWith("f") || text.endsWith("F")) return new Enum[] { MyTokens.FloatLiteralToken };
							else if (text.endsWith("d") || text.endsWith("D")) return new Enum[] { MyTokens.DoubleLiteralToken };
							return new Enum[] { MyTokens.DoubleLiteralToken };
						} else if (text.endsWith("f") || text.endsWith("F")) return new Enum[] { MyTokens.FloatLiteralToken };
						else if (text.endsWith("d") || text.endsWith("D")) return new Enum[] { MyTokens.DoubleLiteralToken };
						else if (text.endsWith("l") || text.endsWith("L")) return new Enum[] { MyTokens.LongLiteralToken };
						return new Enum[] { MyTokens.IntegerLiteralToken };
					}
				}
		);
	}

	private Rule identifier() {
		return Token(
				Sequence(
						FirstOf(
								CharacterRange('a', 'z'),
								CharacterRange('A', 'Z')
						),
						OptionalOrMore(
								FirstOf(
										CharacterRange('a', 'z'),
										CharacterRange('A', 'Z'),
										CharacterRange('0', '9'),
										Character('_'),
										Character('$')
								)
						)
				), new TokenRule.OnActionTokenSyntaxListener() {
					@Override
					public Enum<?>[] onActionTokenSyntax(String text) {
						return switch (text) {
							case "break" -> new Enum[] { MyTokens.BreakKeywordControlToken };
							case "continue" -> new Enum[] { MyTokens.ContinueKeywordControlToken };

							case "package" -> new Enum[] { MyTokens.PackageKeywordToken };

							case "import" -> new Enum[] { MyTokens.ImportKeywordToken };
							case "from" -> new Enum[] { MyTokens.FromKeywordToken };

							case "protected" -> new Enum[] { MyTokens.ProtectedKeywordToken };
							case "public" -> new Enum[] { MyTokens.PublicKeywordToken };
							case "private" -> new Enum[] { MyTokens.PrivateKeywordToken };

							case "function" -> new Enum[] { MyTokens.FunctionKeywordToken };

							case "super" -> new Enum[] { MyTokens.SuperKeywordToken };
							case "this" -> new Enum[] { MyTokens.ThisKeywordToken };

							case "return" -> new Enum[] { MyTokens.ReturnKeywordToken };
							case "new" -> new Enum[] { MyTokens.NewKeywordToken };
							case "false" -> new Enum[] { MyTokens.FalseKeywordLiteralToken };
							case "true" -> new Enum[] { MyTokens.TrueKeywordLiteralToken };
							case "null" -> new Enum[] { MyTokens.NullKeywordLiteralToken };
							default -> new Enum[] { MyTokens.IdentifierToken };
						};
					}
				}
		);
	}

	@Override
	public Rule onRegexSkipped() {
		return OptionalOrMore(FirstOf(Character(' '), Character('\r'), Character('\n'), Character('\t')));
	}
}
