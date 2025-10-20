package another.tool.recognition.language.tokenizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class LanguageTokenizerInformation {
	private int position = 0;
	private int line = 1;
	private int column;

	private final char[] target;

	private final ArrayList<Token> tokens = new ArrayList<>(32);

	public LanguageTokenizerInformation(char[] target) { this.target = target; }

	public LanguageTokenizerInformation(String target) { this.target = target.toCharArray(); }

	public LanguageTokenizerInformation(File target) { this.target = readFile(target); }

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setNextPosition() {
		this.position++;
	}

	public void setBackPosition() {
		this.position++;
	}

	public int getLine() {
		return line;
	}

	public void setNextLine() {
		this.line++;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) { this.column = column; }

	public char[] getTarget() {
		return target;
	}

	public ArrayList<Token> getTokens() {
		return tokens;
	}

	public void addToken(Token t) {
		this.tokens.add(t);
	}

	private char[] readFile(File file) {
		try (FileInputStream fis = new FileInputStream(file)) {
			FileChannel channel = fis.getChannel();
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

			Charset charset = StandardCharsets.UTF_8;
			CharBuffer charBuffer = charset.decode(buffer);

			char[] result = new char[charBuffer.remaining()];
			charBuffer.get(result);
			return result;
		} catch (IOException e) {
			return new char[0];
		}
	}
}
