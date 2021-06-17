package lamph11.project.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConstraintViolation {

	private String key;
	private String errorCode;
	private Object params;
	private Object validatedValue;

	public ConstraintViolation(String key, String errorCode) {
		this.key = key;
		this.errorCode = errorCode;
	}
}
