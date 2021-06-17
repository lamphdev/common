package lamph11.project.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

	public static Authentication getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
