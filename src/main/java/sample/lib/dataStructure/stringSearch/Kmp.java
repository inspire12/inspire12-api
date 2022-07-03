package sample.lib.dataStructure.stringSearch;

import java.util.ArrayList;
import java.util.List;

public class Kmp {

    public static List<Integer> run(String match, String fullText) {
        match = "ABCDABE";
        fullText = "ABCDABCDABEE";
        List<Integer> indexes = new ArrayList<>();
        List<Integer> piTable = makePiTable(match);

        int piIndex = 0;
        for (int i = 0; i < fullText.length(); i++) {
            char c = fullText.charAt(i);

            while (piIndex > 0 && c != match.charAt(piIndex)) {
                piIndex = piTable.get(piIndex - 1);
            }

            if (c == match.charAt(piIndex)) {
                if (match.length() - 1 <= piIndex) {
                    indexes.add(i - match.length() + 1); // 시작 지점을 저장
                    piIndex = piTable.get(piIndex); // 겹친게 또 겹칠 수도 있어
                } else {
                    piIndex++;
                }
            }
        }
        return indexes;
    }

    private static List<Integer> makePiTable(String match) {
        int matchIndex = 0;
        List<Integer> pi = new ArrayList<>();
        pi.add(0);
        for (int subStrLength = 1; subStrLength < match.length(); subStrLength++) {
            char c = match.charAt(matchIndex);
            while(matchIndex > 0 && (c != match.charAt(subStrLength))) {
                matchIndex = pi.get(matchIndex - 1);
            }
            if (match.charAt(subStrLength) == match.charAt(matchIndex)) {
                matchIndex++;
                pi.add(matchIndex);
            } else {
                pi.add(0);
            }
        }
        return pi;
    }
//        List<Integer> pi = new ArrayList<>();
//        int index = 0;
//        for (int i = 1; i < match.length(); i++) {
//            while(index > 0 && (pi.get(i) != pi.get(index))) {
//                index = pi.get(index - 1);
//            }
//            if (pi.get(i).equals(pi.get(index))) {
//                pi.set(i, index++);
//            }
//        }
//
//        return pi;
//    }

//    private static int calPi() {
//
//    }
}
