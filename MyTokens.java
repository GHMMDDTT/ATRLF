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
	ModuleKeywordToken,
	PackageKeywordToken,

	ImportKeywordToken,
	FromKeywordToken,
	UsingKeywordToken,
	AsKeywordToken,

	ProtectedKeywordToken,
	PublicKeywordToken,
	PrivateKeywordToken,
	SwitchKeywordToken,
	CaseKeywordToken,
	SealedKeywordToken,
	ExternalKeywordToken,
	InternalKeywordToken,
	InnerKeywordToken,
	ExnerKeywordToken,
	OpenKeywordToken,
	CloseKeywordToken,
	FriendKeywordToken,
	CompanionKeywordToken,
	FinalKeywordToken,
	AbstractKeywordToken,
	ClassKeywordToken,
	EnumerateKeywordToken,
	StructureKeywordToken,
	RecordKeywordToken,
	DataKeywordToken,
	InterfaceKeywordToken,
	AnnotationKeywordToken,
	AttributeKeywordToken,
	TraitKeywordToken,
	ExtendKeywordToken,
	ExtendsKeywordToken,
	ImplementKeywordToken,
	ImplementsKeywordToken,
	CompanionsKeywordToken,
	FriendsKeywordToken,
	RestrictionKeywordToken,
	RestrictionsKeywordToken,
	PermitKeywordToken,
	PermitsKeywordToken,
	WithKeywordToken,
	WithsKeywordToken,

	SetKeywordToken,
	GetKeywordToken,
	LocalKeywordToken,
	GlobalKeywordToken,
	ReadonlyKeywordToken,
	ConstantKeywordToken,
	WriteonlyKeywordToken,
	NativeKeywordToken,
	OverwriteKeywordToken,
	WriteKeywordToken,
	FunctionKeywordToken,
	DefinitionKeywordToken,
	OperatorKeywordToken,
	PrefixKeywordToken,
	SuffixKeywordToken,
	ControlKeywordToken,
	InfixKeywordToken,
	ConstructorKeywordToken,
	ThrowKeywordToken,
	ThrowsKeywordToken,
	NamespaceKeywordToken,

	InKeywordToken,
	OutKeywordToken,
	SoKeywordToken,
	OfKeywordToken,
	ToKeywordToken,

	LetKeywordToken,
	TypeKeywordToken,

	SuperKeywordToken,
	BaseKeywordToken,
	ThisKeywordToken,
	SelfKeywordToken,
	NextKeywordToken,
	PreviousKeywordToken,
	ReturnKeywordToken,
	NewKeywordToken,

	// Symbols:
	AtSymbolToken,
	LowLineSymbolToken,
	TildeSymbolToken,
	QuotationSymbolToken,
	ApostropheSymbolToken,

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
	StringBlockLiteralToken,
	CharacterLiteralToken,
	DigitLiteralToken,
	NumberLiteralToken,
	ByteLiteralToken,
	ShortLiteralToken,
	IntegerLiteralToken,
	LongLiteralToken,
	FloatLiteralToken,
	DoubleLiteralToken,

	TrueKeywordLiteralToken,
	FalseKeywordLiteralToken,
	IndeterminateKeywordLiteralToken,
	UndefinedKeywordLiteralToken,
	NaNKeywordLiteralToken,
	NAKeywordLiteralToken,
	NullKeywordLiteralToken,
	NoneKeywordLiteralToken,
	InfinityKeywordLiteralToken,
	NeutralKeywordLiteralToken;

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