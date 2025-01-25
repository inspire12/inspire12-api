package sample.lib.dataStructure;

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
