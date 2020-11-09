package util;

import model.History;
import model.Security;
import to.HistoryTo;

import java.util.ArrayList;
import java.util.List;

public class HistoryUtil {
    public static List<HistoryTo> getTos(List<History> histories) {
        List<HistoryTo> historyTos = new ArrayList<>();
        Security security;
        for(History history : histories) {
            security = history.getSecurity();
            historyTos.add(new HistoryTo(
                    security.getIdOnExchange(),
                    security.getRegNumber(),
                    security.getName(),
                    security.getEmitentTitle(),

                    history.getId(),
                    history.getTradeDate(),
                    history.getNumTrades(),
                    history.getOpen(),
                    history.getClose()
            ));
        }
        return historyTos;
    }
}
