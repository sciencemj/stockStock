package com.sciencemj.stock.service;

import com.sciencemj.stock.domain.user.User;
import com.sciencemj.stock.domain.user.UserRepository;
import com.sciencemj.stock.web.dto.UserResponseDto;
import com.sciencemj.stock.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long save(User user){
        return userRepository.save(user).getId();
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new UserResponseDto(entity);
    }
    @Transactional(readOnly = true)
    public UserResponseDto findByName(String name) {
        User entity = userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. name=" + name));
        return new UserResponseDto(entity);
    }

}
