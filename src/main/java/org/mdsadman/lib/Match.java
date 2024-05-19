package org.mdsadman.lib;

public class Match {
    public interface IOkayMatch<T> {
        void ok(T value);
    }

    public interface IErrorMatch<E extends Throwable> {
        void err(E error);
    }

    public interface IOkayMatchRet<T, R> {
        R ok(T value);
    }

    public interface IErrorMatchRet<E extends Throwable, R> {
        R err(E error);
    }

    // FOR RESULT MATCHING
    public static <T, E extends Throwable> void result(Result<T, E> result, IOkayMatch<T> iOkayMatch, IErrorMatch<E> iErrorMatch) {
        if (result.isHasError()) iErrorMatch.err(result.getError());
        else iOkayMatch.ok(result.getValue());
    }

    public static <T, E extends Throwable, R> R result(Result<T, E> result, IOkayMatchRet<T, R> iOkayMatchRet, IErrorMatchRet<E, R> iErrorMatchRet) {
        return result.isHasError() ? iErrorMatchRet.err(result.getError()) : iOkayMatchRet.ok(result.getValue());
    }
}
