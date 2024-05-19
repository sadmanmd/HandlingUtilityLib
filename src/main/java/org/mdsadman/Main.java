package org.mdsadman;

import org.mdsadman.lib.Match;
import org.mdsadman.lib.Result;
import org.mdsadman.lib.TryCatch;

public class Main {

    public static void run() throws Exception {
        // ANY FUNCTION WITH THROW EXCEPTION
    }

    public static Result<String, NullPointerException> test() {
        Result<String, NullPointerException> result = new Result<>();

        if (!TryCatch.tryOnly(Main::run)) {
            return result.Err(new NullPointerException("THIS IS EXCEPTION"));
        }

        TryCatch.Ref tryRef = TryCatch.Ref.getInstance();
        TryCatch.tryCatchRef(() -> {
            // TRY
        }, (error) -> {
            // CATCH
        }, tryRef);

        if (tryRef.get()) {
            // DO TRY
            System.out.println("DO TRY SUCCESS");
        }

        Throwable t = TryCatch.tryCatchRet(() -> {
            // DO TRY
        });

        if (t != null) {
            return result.Err(new NullPointerException(t.getMessage()));
        }

        return result.Ok("RUN PROPERLY");
    }

    public static void matchTestVoid() {
        Result<String, NullPointerException> result = test();
        Match.result(result, (value) -> {
            // OKAY HANDLING
        }, (error) -> {
            // ERROR HANDLING
        });
    }

    public static Result<String, NullPointerException> matchTestRetResult() {
        Result<String, NullPointerException> result = test();
        return Match.result(result, (value) -> {
            // OKAY HANDLING

            return result;
        }, (error) -> {
            // ERROR HANDLING

            return result;
        });
    }

    public static String matchTestRetString() {
        Result<String, NullPointerException> result = test();
        return Match.result(result, (value) -> {
            // OKAY HANDLING

            return "";
        }, (error) -> {
            // ERROR HANDLING

            return null;
        });
    }

    public static void main(String[] args) {
        matchTestVoid();
        Result<String, NullPointerException> value = matchTestRetResult();
        String item = matchTestRetString();
    }
}