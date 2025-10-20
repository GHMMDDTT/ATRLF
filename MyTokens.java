import another.tool.recognition.language.Results.Result;
import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.parser.LanguageParserInformation;
import another.tool.recognition.language.grammaticals.Grammatical;

public enum MyTokens implements Grammatical {
	// Classical:
	IdentifierToken,

	// Keywords Control:
	BreakKeywordControlToken,
	ContinueKeywordControlToken,

	// keywords:
	PackageKeywordToken,

	ImportKeywordToken,
	FromKeywordToken,

	ProtectedKeywordToken,
	PublicKeywordToken,
	PrivateKeywordToken,

	FunctionKeywordToken,

	SuperKeywordToken,
	ThisKeywordToken,
	ReturnKeywordToken,
	NewKeywordToken,

	// Symbols:
	AtSymbolToken,
	LowLineSymbolToken,
	TildeSymbolToken,

	EqualSymbolOperatorToken,
	AndSymbolOperatorToken,
	VerticalLineSymbolOperatorToken,
	NotSymbolOperatorToken,
	QuestionSymbolOperatorToken,
	LessThanSymbolOperatorToken,
	GreaterThanSymbolOperatorToken,

	PlusSymbolArithmeticalOperatorToken,
	MinusSymbolArithmeticalOperatorToken,
	StartSymbolArithmeticalOperatorToken,
	SlashSymbolArithmeticalOperatorToken,
	ModuleSymbolArithmeticalOperatorToken,
	ExponentSymbolArithmeticalOperatorToken,

	SemicolonSymbolDelimiterOperatorToken,
	ColonSymbolDelimiterOperatorToken,
	CommaSymbolDelimiterOperatorToken,
	DotSymbolDelimiterOperatorToken,

	ParenthesisLeftSymbolDelimiterSeparatorOperatorToken,
	ParenthesisRightSymbolDelimiterSeparatorOperatorToken,
	SquareLeftSymbolDelimiterSeparatorOperatorToken,
	SquareRightSymbolDelimiterSeparatorOperatorToken,
	CurlyLeftSymbolDelimiterSeparatorOperatorToken,
	CurlyRightSymbolDelimiterSeparatorOperatorToken,

	// Literals
	StringLiteralToken,
	CharacterLiteralToken,
	IntegerLiteralToken,
	LongLiteralToken,
	FloatLiteralToken,
	DoubleLiteralToken,

	TrueKeywordLiteralToken,
	FalseKeywordLiteralToken,
	NullKeywordLiteralToken, InterpolationLiteralToken;

	@Override
	public Result onGrammaticalExecute(LanguageParserInformation information) {
		for (Enum<?> type : information.getTarget().get(information.getPosition()).getType()) {
			if (type == this) {
				information.setNextPosition();
				return new Result(null);
			}
		}
		return null;
	}
}