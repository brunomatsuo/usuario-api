package br.com.userapi.request;

import br.com.userapi.model.enums.UserRole;

public record UserRequest(String login, String password, UserRole role) {
}
