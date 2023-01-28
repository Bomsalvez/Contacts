package dev.senzalla.contacts.model.user.mapper;

import dev.senzalla.contacts.model.user.entity.User;
import dev.senzalla.contacts.model.user.module.UserCreated;
import dev.senzalla.contacts.model.user.module.UserSummarize;
import dev.senzalla.contacts.model.user.module.UserToBeCreated;
import org.modelmapper.ModelMapper;

public class UserMapper {
    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
    }

    private UserMapper() {
    }

    public static User toUser(UserToBeCreated userToBeCreated) {
        return mapper.map(userToBeCreated, User.class);
    }

    public static UserCreated toUserCreated(User user) {
        return mapper.map(user, UserCreated.class);
    }

    public static UserSummarize toUserSummarize(User user) {
        return mapper.map(user, UserSummarize.class);
    }
}
