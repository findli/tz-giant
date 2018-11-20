package com.giant.boot.resolver;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.giant.boot.publisher.BlockDataPublisher;
import com.giant.boot.resolver.dto.BlockData;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
class Subscription implements GraphQLSubscriptionResolver {

    private BlockDataPublisher stockTickerPublisher;

    Subscription(BlockDataPublisher stockTickerPublisher) {
        this.stockTickerPublisher = stockTickerPublisher;
    }

    Publisher<BlockData> getLastBlockData() {
        return stockTickerPublisher.getPublisher();
    }

}
