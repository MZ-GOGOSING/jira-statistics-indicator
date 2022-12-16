package kr.co.mz.jira.support.code;

public interface DescriptionCode {

	default String getCode() {
		if (this instanceof Enum) {
			return ((Enum) this).name();
		} else {
			return null;
		}
	}

	String getDescription();
}
