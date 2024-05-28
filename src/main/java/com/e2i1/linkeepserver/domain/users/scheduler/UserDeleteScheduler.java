package com.e2i1.linkeepserver.domain.users.scheduler;

import com.e2i1.linkeepserver.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDeleteScheduler {

    private final UsersService usersService;

    @Scheduled(cron = "0 0 2 * * ?") //매일 2AM에 실행
    public void deleteUnregisteredUsers() {
        log.info("========== {} 탈퇴 유저 삭제 스케쥴링 시작.", LocalDateTime.now());
        int result = usersService.deleteUnregisteredUsersForOneYear();

        if (result == 0) {
            log.info("========== 삭제할 유저가 없습니다.");
        } else {
            log.info("========== {}명의 탈퇴 유저를 삭제했습니다.", result);
        }

        log.info("========== {} 탈퇴 유저 삭제 스케쥴링 종료.", LocalDateTime.now());

    }

}
