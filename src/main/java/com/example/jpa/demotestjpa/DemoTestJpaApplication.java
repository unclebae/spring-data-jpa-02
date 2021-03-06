package com.example.jpa.demotestjpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@SpringBootApplication
public class DemoTestJpaApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoTestJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = User.builder()
                .name("KIDO")
                .joinedAt(LocalDateTime.now())
                .build();

        user1.addAddress(Address.builder()
                .name("OFFICE")
                .sido("서울시")
                .doro("을지로큰길")
                .detail("큰길빌딩 101")
                .build());

        user1.addAddress(Address.builder()
                .name("HOME")
                .sido("서울시")
                .doro("을지로큰길")
                .detail("큰 아파트 101동 105호")
                .build());

        user1.addAddress(Address.builder()
                .name("FAVORITE")
                .sido("서울시")
                .doro("을지로작은길")
                .detail("직은길빌딩 101")
                .build());

        log.info("new user: " + user1);
        final User save = userRepository.save(user1);

        log.info("Save User:" + save);

        final Optional<User> userInfos = userRepository.findById(save.getId());
        if (userInfos.isPresent()) {
            log.info("Get User: " + userInfos.get());
//            log.info("Get Address of 0: " + userInfos.get().getAddress().get(0)); // 에러가 난다.
        }


    }
}
