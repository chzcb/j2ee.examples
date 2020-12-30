package org.dhp.examples.rpcdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dhp.common.rpc.IRpcHeader;
import org.dhp.common.rpc.IRpcRequest;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddRequest implements IRpcRequest {
    IRpcHeader header;
    Integer addition;
}
