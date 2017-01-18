package com.javatea.spotchserver.db;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	final DBConnector CONNECTOR = DBConnector.getInstance();

	public T find(long id);
	public List<T> findAll();
	public void update(T object);
	public void delete(T object);
	public void insert(T object) throws SQLException;
}
