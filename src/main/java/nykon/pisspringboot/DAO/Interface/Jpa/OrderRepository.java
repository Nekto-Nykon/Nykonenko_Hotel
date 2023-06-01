package nykon.pisspringboot.DAO.Interface.Jpa;

import nykon.pisspringboot.DAO.Interface.OrderDao;
import nykon.pisspringboot.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository
        extends
        OrderDao,
        JpaRepository<Order, Integer> {
    @Query("SELECT o.idAccount from Order o" +
            " WHERE "+
            "((o.checkIn < :startDate AND o.checkOut > :startDate )"+
            "OR (o.checkIn < :endDate AND o.checkOut > :endDate) "+
            "OR (o.checkIn < :startDate AND o.checkOut > :endDate))"+
            " AND o.status <> nykon.pisspringboot.Model.Status.REJECTED")
    List<Integer> findAllIncorrectByDate(LocalDate startDate, LocalDate endDate);

}
