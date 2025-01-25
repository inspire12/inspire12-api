package sample.lib.rank;

//import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Rank <T> {
    int rank;
//    @JsonUnwrapped
    T content;
}