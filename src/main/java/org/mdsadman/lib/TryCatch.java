package org.mdsadman.lib;

public class TryCatch {
    public interface ITry {
        void tried() throws Throwable;
    }

    public interface ICatch {
        void error(Throwable t);
    }

    public static class Ref {
        private boolean isTrySuccess = false;

        public static Ref getInstance() {
            return new Ref();
        }

        protected void set(boolean isHasError) {
            this.isTrySuccess = isHasError;
        }

        public boolean get() {
            return isTrySuccess;
        }
    }

    public static boolean tryOnly(ITry iTry) {
        try {
            iTry.tried();
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean tryCatch(ITry iTry, ICatch iCatch) {
        try {
            iTry.tried();
            return true;
        } catch (Throwable t) {
            iCatch.error(t);
            return false;
        }
    }

    public static void tryOnlyRef(ITry iTry, Ref ref) {
        try {
            iTry.tried();
            ref.set(true);
        } catch (Throwable t) {
            ref.set(false);
        }
    }

    public static void tryCatchRef(ITry iTry, ICatch iCatch, Ref ref) {
        try {
            iTry.tried();
            ref.set(true);
        } catch (Throwable t) {
            iCatch.error(t);
            ref.set(false);
        }
    }

    public static Throwable tryCatchRet(ITry iTry) {
        try {
            iTry.tried();
            return null;
        } catch (Throwable t) {
            return t;
        }
    }
}
