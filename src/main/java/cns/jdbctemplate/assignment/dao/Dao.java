package cns.jdbctemplate.assignment.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

List<T> list();

T create(T t);

Optional<T> get(long id);

T update (T t);

String delete(long id);


List<T> findByTitleContaining(String title);

String deleteAll();





}
