package com.e2i1.linkeepserver.domain.users.scheduler;

import com.e2i1.linkeepserver.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDeleteScheduler {

    private final UsersService usersService;

    @Scheduled(cron = "0 0 2 * * ?") //매일 2AM에 실행
    public void deleteUnregisteredUsers() {
        usersService.deleteUnregisteredUsersForOneYear();
    }

}
