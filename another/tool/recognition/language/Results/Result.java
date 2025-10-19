package another.tool.recognition.language.Results;

public class Result<T> {
	private final T value;
	private final boolean success;

	public Result(T value, boolean success) {
		this.value = value;
		this.success = success;
	}

	public T getValue() {
		return value;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public boolean isFailed() {
		return !this.success;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
