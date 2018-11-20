package com.giant.boot.resolver.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BlockData {

    private final int num;
    private final double difficulty;

    public BlockData(int num, double difficulty) {
        this.num = num;
        this.difficulty = difficulty;
    }
}
