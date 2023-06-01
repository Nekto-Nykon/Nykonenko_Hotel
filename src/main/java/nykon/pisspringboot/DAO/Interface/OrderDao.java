package nykon.pisspringboot.DAO.Interface;

import nykon.pisspringboot.DAO.DAO;
import nykon.pisspringboot.Model.Order;
import nykon.pisspringboot.Model.Status;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao extends DAO<Order>{
    List<Integer> findAllIncorrectByDate(LocalDate startDate, LocalDate endDate);

    List<Order> findAllByIdAccount(Integer idAccount);

    List<Order> findAllByStatus(Status inProcess);
}
