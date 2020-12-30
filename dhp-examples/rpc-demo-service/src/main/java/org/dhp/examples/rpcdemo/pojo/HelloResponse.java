package org.dhp.examples.rpcdemo.pojo;

import lombok.Builder;
import lombok.Data;
import org.dhp.common.rpc.IRpcResponse;

@Data
@Builder
public class HelloResponse implements IRpcResponse {
    int code;
    String message;
    String content;
}
