package me.thesevenq.facebook.utils.menu;

import java.io.Serializable;

public interface TypeCallback<T> extends Serializable {

	void callback(T data);

}