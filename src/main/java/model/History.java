package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "history", uniqueConstraints = {@UniqueConstraint(columnNames = {"security_id", "board_id", "trade_date"}, name = "history_security_id_board_id_trade_date_idx")})
public class History extends AbstractBaseEntity {

    @Transient
    private String secId;

    @Column(name = "board_id")
    private String boardId;

    @Column(name = "trade_date", nullable = false)
    private LocalDate tradeDate;

    @Column(name = "num_trades")
    private double numTrades;

    @Column(name = "value")
    private double value;

    @Column(name = "low")
    private double low;

    @Column(name = "high")
    private double high;

    @Column(name = "open")
    private double open;

    @Column(name = "close")
    private double close;

    @Column(name = "volume")
    private double volume;

    @ManyToOne
    @JoinColumn(name = "security_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Security security;

    public History() {

    }

    public History(String secId, String boardId, LocalDate tradeDate, double numTrades, double value, double low,
                   double high, double open, double close, double volume) {
        this(secId,null,  boardId, tradeDate, numTrades, value, low, high, open, close, volume);
    }

    public History(String secId, Integer id,  String boardId, LocalDate tradeDate, double numTrades, double value, double low,
                   double high, double open, double close, double volume) {
        super(id);
        this.secId = secId;
        this.boardId = boardId;
        this.tradeDate = tradeDate;
        this.numTrades = numTrades;
        this.value = value;
        this.low = low;
        this.high = high;
        this.open = open;
        this.close = close;
        this.volume = volume;
    }

    public History(History history) {
        this(
                history.getSecId(),
                history.getId(),
                history.getBoardId(),
                history.getTradeDate(),
                history.getNumTrades(),
                history.getValue(),
                history.getLow(),
                history.getHigh(),
                history.getOpen(),
                history.getClose(),
                history.getVolume()
        );
    }

    public String getSecId() {
        return secId;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
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

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    @Override
    public String toString() {
        return "History{" +
                "secId='" + secId + '\'' +
                ", boardId='" + boardId + '\'' +
                ", tradeDate=" + tradeDate +
                ", numTrades=" + numTrades +
                ", value=" + value +
                ", low=" + low +
                ", high=" + high +
                ", open=" + open +
                ", close=" + close +
                ", volume=" + volume +
                '}';
    }
}
