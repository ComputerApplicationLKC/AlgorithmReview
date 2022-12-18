package leekimcho.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 김승진 작성
 */

@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public GlobalFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Global Filter baseMessgae: {}", config.getBaseMessage());

            if (config.isPreLogger()){
                log.info("Global Filter Start: request id -> {}" , request.getId());
                log.info("Global Filter Start: request path -> {}" , request.getPath());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(()->{

                if (config.isPostLogger()){
                    log.info("Global Filter End: response statuscode -> {}" , response.getStatusCode());
                    String serviceName = Arrays.stream(request.getPath().toString().split("/")).filter(str -> str.contains("service")).collect(Collectors.joining());
                    log.info("Global Filter End: statuscode and service -> {} -> {}" , response.getStatusCode(), serviceName);
                }
            }));

        };
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

}
