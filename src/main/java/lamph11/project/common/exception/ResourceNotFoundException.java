package lamph11.project.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResourceNotFoundException extends ServiceException {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String identifer) {
		super("");
	}

	public ResourceNotFoundException(String resourceType, String identifer) {
		super(String.format("%s: %s Notfound", resourceType, identifer));
	}

}
