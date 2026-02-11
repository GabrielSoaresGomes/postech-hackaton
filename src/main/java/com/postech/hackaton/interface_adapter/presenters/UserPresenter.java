package com.postech.hackaton.interface_adapter.presenters;

import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.domain.User;
import com.postech.hackaton.dtos.responses.UserResponse;
import com.postech.hackaton.dtos.responses.medical_care.MedicalCareResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPresenter {
    public static UserResponse userToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUserType(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getLastModifiedDateTime()
        );
    }

    public static List<UserResponse> userListToUserResponseDTOList(List<User> users) {
        return Optional.ofNullable(users)
                .map(
                        mcs -> mcs.stream()
                                .map(UserPresenter::userToUserResponse)
                                .toList()
                )
                .orElse(null);
    }
}
