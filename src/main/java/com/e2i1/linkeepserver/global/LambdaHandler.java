package com.e2i1.linkeepserver.global;

import com.e2i1.linkeepserver.LinkeepServerApplication;
import org.springframework.cloud.function.adapter.aws.FunctionInvoker;

import java.io.InputStream;
import java.io.OutputStream;

public class LambdaHandler extends FunctionInvoker {

    public LambdaHandler() {
        super("linkeep");
    }
}
