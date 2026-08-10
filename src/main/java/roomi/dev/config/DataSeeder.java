package roomi.dev.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import roomi.dev.model.User;
import roomi.dev.repository.UserRepository;
import roomi.dev.util.PasswordHelper;

/**
 * Seed dữ liệu mẫu cho môi trường phát triển / demo.
 *
 * TÀI KHOẢN ĐĂNG NHẬP MẪU (mật khẩu chung: 123456)
 * ------------------------------------------------------------------
 * admin -> ADMIN Admin Hệ Thống
 * letan1 -> RECEPTIONIST Nguyễn Thị Lễ Tân
 * letan2 -> RECEPTIONIST Trần Văn Lễ Tân
 * buongphong1 -> HOUSEKEEPER Lê Thị Buồng Phòng
 * ketoan1 -> ACCOUNTANT Phạm Kế Toán
 * chusohuu -> OWNER Chủ Khách Sạn
 * ------------------------------------------------------------------
 * Username đặt theo mẫu "tên-vai-trò + số thứ tự" để dễ nhớ và dễ phân biệt
 * khi có nhiều nhân viên cùng vai trò (vd: letan1, letan2, ...).
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

        private final UserRepository userRepository;

        @Override
        @Transactional
        public void run(String... args) {
                if (userRepository.count() > 0) {
                        log.info("=== Users đã được seed, bỏ qua seeding ===");
                        return;
                }
                log.info("=== Bắt đầu seed Users ===");
                seedUsers();
                log.info("=== Hoàn thành seed Users ===");
        }

        // ================================================================== USERS
        private void seedUsers() {
                log.info("Seed Users...");
                userRepository.save(User.builder().fullName("Admin Hệ Thống").username("admin")
                                .passwordHash(PasswordHelper.encode("pass@1234")).role(User.Role.ADMIN)
                                .phone("0900000000").active(true).build());
                userRepository.save(User.builder().fullName("Nguyễn Thị Lễ Tân").username("letan1")
                                .passwordHash(PasswordHelper.encode("pass@1234")).role(User.Role.RECEPTIONIST)
                                .phone("0900000001").active(true).build());
                userRepository.save(User.builder().fullName("Trần Văn Lễ Tân").username("letan2")
                                .passwordHash(PasswordHelper.encode("pass@1234")).role(User.Role.RECEPTIONIST)
                                .phone("0900000002").active(true).build());
                userRepository.save(User.builder().fullName("Lê Thị Buồng Phòng").username("buongphong1")
                                .passwordHash(PasswordHelper.encode("pass@1234")).role(User.Role.HOUSEKEEPER)
                                .phone("0900000003").active(true).build());
                userRepository.save(User.builder().fullName("Phạm Kế Toán").username("ketoan1")
                                .passwordHash(PasswordHelper.encode("pass@1234")).role(User.Role.ACCOUNTANT)
                                .phone("0900000004").active(true).build());
                userRepository.save(User.builder().fullName("Chủ Khách Sạn").username("chusohuu")
                                .passwordHash(PasswordHelper.encode("pass@1234")).role(User.Role.OWNER)
                                .phone("0900000005").active(true).build());
                log.info("✓ 6 users");
        }
}