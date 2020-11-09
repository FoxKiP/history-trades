package to;

import java.time.LocalDate;
import java.util.Objects;

public class HistoryTo {
    private String idOnExchange;
    private String regNumber;
    private String name;
    private String emitentTitle;

    private Integer historyId;
    private LocalDate tradeDate;
    private double numTrades;
    private double open;
    private double close;

    public HistoryTo() {

    }

    public HistoryTo(String idOnExchange, String regNumber, String name, String emitentTitle,
                     Integer historyId, LocalDate tradeDate, double numTrades, double open, double close) {
        this.idOnExchange = idOnExchange;
        this.regNumber = regNumber;
        this.name = name;
        this.emitentTitle = emitentTitle;
        this.historyId = historyId;
        this.tradeDate = tradeDate;
        this.numTrades = numTrades;
        this.open = open;
        this.close = close;
    }

    public String getIdOnExchange() {
        return idOnExchange;
    }

    public void setIdOnExchange(String idOnExchange) {
        this.idOnExchange = idOnExchange;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmitentTitle() {
        return emitentTitle;
    }

    public void setEmitentTitle(String emitentTitle) {
        this.emitentTitle = emitentTitle;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public double getNumTrades() {
        return numTrades;
    }

    public void setNumTrades(double numTrades) {
        this.numTrades = numTrades;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return "HistoryTo{" +
                "idOnExchange='" + idOnExchange + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", name='" + name + '\'' +
                ", emitentTitle='" + emitentTitle + '\'' +
                ", historyId=" + historyId +
                ", tradeDate=" + tradeDate +
                ", numTrades=" + numTrades +
                ", open=" + open +
                ", close=" + close +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryTo historyTo = (HistoryTo) o;
        return Double.compare(historyTo.numTrades, numTrades) == 0 &&
                Double.compare(historyTo.open, open) == 0 &&
                Double.compare(historyTo.close, close) == 0 &&
                idOnExchange.equals(historyTo.idOnExchange) &&
                Objects.equals(regNumber, historyTo.regNumber) &&
                Objects.equals(name, historyTo.name) &&
                Objects.equals(emitentTitle, historyTo.emitentTitle) &&
                historyId.equals(historyTo.historyId) &&
                tradeDate.equals(historyTo.tradeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOnExchange, regNumber, name, emitentTitle, historyId, tradeDate, numTrades, open, close);
    }
}
