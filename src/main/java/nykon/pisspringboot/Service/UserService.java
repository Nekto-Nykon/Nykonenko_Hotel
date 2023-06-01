package nykon.pisspringboot.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nykon.pisspringboot.DAO.Interface.RoleDao;
import nykon.pisspringboot.DAO.Interface.UserDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {
    @Qualifier("userRepository")
    private static UserDao userRepository;
    @Qualifier("roleRepository")
    private static RoleDao roleRepository;
}
