package com.javatea.dao;


import java.io.Serializable;

public interface DBAccessDao <Repository> extends Serializable {
	public Repository getRepository();
}
