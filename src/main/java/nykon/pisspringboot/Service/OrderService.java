package nykon.pisspringboot.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nykon.pisspringboot.DAO.Interface.OrderDao;
import nykon.pisspringboot.DAO.Interface.UserDao;
import nykon.pisspringboot.Model.ComfortLevel;
import nykon.pisspringboot.Model.Order;
import nykon.pisspringboot.Model.Status;
import nykon.pisspringboot.Model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {
    @Qualifier("orderRepository")
    private final OrderDao orderDao;
    @Qualifier("userDao")
    private final UserDao userDao;
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }
    @Transactional(readOnly = true)
    public List<Order> findAllByEmail(String email) {
        Optional<User> maybeUser = userDao.findByEmail(email);
        if(maybeUser.isPresent())
            return orderDao.findAllByIdAccount(maybeUser.get().getId());
        return new ArrayList<>();
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrdersToChangeStatus() {
        return orderDao.findAllByStatus(Status.IN_PROCESS);
    }

    public Order createOrder(Integer idRoom, String email, LocalDate checkIn, LocalDate checkOut){
        Integer userId = userDao.findByEmail(email).map(User::getId).get();
        Order order = Order.builder().
                idRoom(idRoom).
                checkIn(checkIn).
                checkOut(checkOut).
                idAccount(userId).
                adultCapacityRequirement(1).
                childCapacityRequirement(0).
                status(Status.IN_PROCESS).
                comfortLevelRequirement(ComfortLevel.ECONOM).
                build();

        return orderDao.save(order);

    }

    public Order updateStatus(Integer id, Status status) {
        Optional<Order> maybeOrder =  orderDao.findById(id);
        if(maybeOrder.isEmpty()){
            throw new RuntimeException("Some problem on update status");
        }
        Order order = maybeOrder.get();
        order.setStatus(status);
        return orderDao.save(order);
    }
}
