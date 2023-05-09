package nykon;

import nykon.DAO.DaoFactory;
import nykon.DAO.RoleDAO;
import nykon.DAO.UsrDAO;
import nykon.Domain.Role;
import nykon.Domain.Usr;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelService");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        DaoFactory daoFactory = DaoFactory.getJpaInstance(em);
        try (RoleDAO dao = daoFactory.createRoleDao()) {
            Optional<Role> byId = dao.findById(1);
            if(byId.isPresent())
            System.out.println(byId.get());
        }
        System.out.println();
        try (RoleDAO dao = daoFactory.createRoleDao()) {
            List<Role> roles = dao.findAll();
            for(Role role: roles){
                System.out.println(role);
            }
        }
        System.out.println();
        try (RoleDAO dao = daoFactory.createRoleDao()){
            Role roleToCreate = Role.builder()
                    .name("Employee")
                    .build();
            dao.create(roleToCreate);
        }
        System.out.println();
        try (RoleDAO dao = daoFactory.createRoleDao()) {
            List<Role> roles = dao.findAll();
            for(Role role: roles){
                System.out.println(role);
            }
        }
        System.out.println();
        try (RoleDAO dao = daoFactory.createRoleDao()) {
            Role roleToUpdate = Role.builder()
                    .id(6)
                    .name("SuperEmployee")
                    .build();
            dao.update(roleToUpdate);
        }
        System.out.println();
        try (RoleDAO dao = daoFactory.createRoleDao()) {
            List<Role> roles = dao.findAll();
            for(Role role: roles){
                System.out.println(role);
            }
        }

//        }
//        System.out.println();
//        try (RoleDAO dao = daoFactory.createRoleDao()) {
//            List<Role> roles = dao.findAll();
//            for(Role role: roles){
//                System.out.println(role);
//            }
//        }
////        try (UsrDAO dao = daoFactory.createUsrDao();
////             RoleDAO roleDao = daoFactory.createRoleDao()){
////             Optional<Role> roleById = roleDao.findById(2);
////             if(roleById.isPresent()){
////                 Usr usrToCreate = Usr.builder()
////                         .name("Oleh")
////                         .email("oleh@kpi.ua")
////                         .password("oleh123")
////                         .role(roleById.get())
////                         .build();
////                        dao.create(usrToCreate);
////                    }
////        }
//                try (UsrDAO dao = daoFactory.createUsrDao()){
//            Usr usrToUpdate = Usr.builder()
//                    .id(1)
//                    .name("Oleg")
//                    .email("oleg@kpi.ua")
//                    .password("oleh123")
//                    .build();
//            dao.update(usrToUpdate);
//
//        }
//        System.out.println();
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}