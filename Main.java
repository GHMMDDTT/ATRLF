import another.tool.recognition.language.trees.Node;
import another.tool.recognition.language.grammaticals.NodeGrammatical;
import another.tool.recognition.language.parser.LanguageParser;
import another.tool.recognition.language.parser.SyntaxLanguageParser;
import another.tool.recognition.language.grammaticals.Grammatical;
import another.tool.recognition.language.tokenizer.LanguageTokenizer;
import another.tool.recognition.language.tokenizer.Token;

import java.io.File;
import java.util.ArrayList;

void main() {
	long start = System.nanoTime();
	LanguageTokenizer tokenizer = new LanguageTokenizer("function Lexer : main\n".repeat((49_932) * 12), new MainTokenizer());
	LanguageParser interpreter = new LanguageParser(tokenizer, new SyntaxLanguageParser() {
		@Override
		public Grammatical onGrammatical() {
			return Node(
					OneOrMore(function()),
					new NodeGrammatical.OnTreeActionListener() {
						@Override
						public Node onTreeAction(ArrayList<Node> nodes) {
							return new NodeCompilationUnit(nodes);
						}
					}
			);
		}

		public Grammatical function() {
			return Node(
					Sequence(Text("function"), MyTokens.IdentifierToken, Text(":"), MyTokens.IdentifierToken),
					new NodeGrammatical.OnSheetActionListener("Function") {
						@Override
						public Node onSheetAction(ArrayList<Token> tokens) {
							return new NodeFunction(tokens.get(1), tokens.get(3));
						}
					}
			);
		}
	});


	NodeCompilationUnit n = interpreter.onParser();
	long end = System.nanoTime();
	System.out.println("Time of Execute this is in: " + ((end - start) / 1_000_000) + "ms"); // Time of Execute this is in: 722ms
	// System.out.println(n.getStatements());
}

/*
 */