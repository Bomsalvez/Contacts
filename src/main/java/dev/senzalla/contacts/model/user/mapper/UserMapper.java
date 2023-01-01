package dev.senzalla.contacts.model.user.mapper;

import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.model.user.module.UserDto;
import org.modelmapper.ModelMapper;

public class UserMapper {
    private UserMapper() {
    }

    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
    }

    public static User toUser(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    public static UserCreated toUserDto(User user) {
        return mapper.map(user, UserCreated.class);
    }
}
