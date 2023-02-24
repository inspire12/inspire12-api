package com.inspire12.practice.api.sample.lib.ds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Edge {
    int from;
    int to;
    int weight;
}
