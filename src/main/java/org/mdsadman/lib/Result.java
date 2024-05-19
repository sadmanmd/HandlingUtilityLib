package org.mdsadman.lib;

public class Result<T, E extends Throwable> {
    private T value;
    private E error;
    private boolean hasError = false;

    public Result<T, E> Ok(T t) {
        value = t;
        return this;
    }

    public Result<T, E> Err(E e) {
        error = e;
        hasError = true;
        return this;
    }

    public T getValue() {
        return value;
    }

    public E getError() {
        return error;
    }

    public boolean isHasError() {
        return hasError;
    }
}