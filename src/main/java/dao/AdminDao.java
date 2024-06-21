package dao;

import java.util.List;

import domain.Users;

public interface AdminDao {
List<Users> findAll() throws Exception;
Users findById(Integer id) throws Exception;
public void insert(Users user) throws Exception;
public void update(Users user) throws Exception;
Users findByUserNameAndUserPass(Users user) throws Exception;
void FAUserUpdate(Users user) throws Exception;
void FAUserDelete(Users user) throws Exception;

}
