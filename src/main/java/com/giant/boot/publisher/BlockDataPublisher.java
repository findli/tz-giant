package com.giant.boot.publisher;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giant.boot.AppConfig;
import com.giant.boot.helper.ExecuteShellCommand;
import com.giant.boot.resolver.dto.BlockData;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class BlockDataPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(BlockDataPublisher.class);

    private Flowable<BlockData> publisher;
    @Autowired
    private AppConfig config;

    @PostConstruct
    private void init() {
        Observable<BlockData> stockPriceUpdateObservable = Observable.create(emitter -> {

            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
            executorService.scheduleAtFixedRate(newBlockData(emitter), 0, 5, TimeUnit.SECONDS);

        });

        ConnectableObservable<BlockData> connectableObservable = stockPriceUpdateObservable.share().publish();
        connectableObservable.connect();

        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    private Runnable newBlockData(ObservableEmitter<BlockData> emitter) {
        return () -> {
            String bashRes;
            BlockData blockData = null;
            try {
                final String command = config.getGiantCliPath() + "/giant-cli -conf=" + config.getGiantConfFilePath() + "  getblockcount | xargs " + config.getGiantCliPath() + "/giant-cli getblockhash | xargs " + config.getGiantCliPath() + "/giant-cli getblock";
                bashRes = ExecuteShellCommand.executeCommand(command);
                final JsonNode node = new ObjectMapper().readTree(bashRes.getBytes());
                final JsonNode num = node.path("height");
                final JsonNode difficulty = node.path("difficulty");
                blockData = new BlockData(num.asInt(), difficulty.asDouble());
            } catch (IOException e) {
                LOG.trace(e.getMessage(), e);
            }

            if (blockData != null) {
                try {
                    emitter.onNext(blockData);
                } catch (RuntimeException e) {
                    LOG.error("Cannot send block data", e);
                }
            }
        };
    }

    public Flowable<BlockData> getPublisher() {
        return publisher;
    }
}