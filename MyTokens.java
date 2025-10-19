import another.tool.recognition.language.Results.State;
import another.tool.recognition.language.interpreter.LanguageInterpreterInformation;
import another.tool.recognition.language.syntaxes.Syntax;

public enum MyTokens implements Syntax {
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
	public State onSyntaxExecute(LanguageInterpreterInformation information) {
		for (Enum<?> type : information.getTarget().get(information.getCursor()).getType()) {
			if (type == this) {
				information.advanceCursor();
				return State.SUCCESS;
			}
		}
		return State.FAILED;
	}
}