package com.example.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ResetPasswordRequest {
	@NotBlank(message = "Reset token is required")
	public  String token;

    @NotBlank(message = "New password cannot be blank")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    // At least 1 uppercase, 1 lowercase, 1 digit, 1 special char
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
        message = "Password must contain at least one uppercase, one lowercase, one digit, and one special character"
    )
    public String newPassword;
		public ResetPasswordRequest(String token, String newPassword) {
			
			this.token = token;
			this.newPassword = newPassword;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getNewPassword() {
			return newPassword;
		}
		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}
	

}
