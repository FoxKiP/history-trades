package utils;

import model.History;
import model.Security;
import org.springframework.mock.web.MockMultipartFile;
import to.HistoryTo;
import util.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

import static model.AbstractBaseEntity.START_SEQ;

public class HistoryTestData {
    public static final Matcher<History> MATCHER = Matcher.usingFieldsComparator(History.class, "security", "secId");
    public static final Matcher<HistoryTo> MATCHER_TO = Matcher.usingEquals(HistoryTo.class);


    public static final String XML_CONTENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<document>\n" +
            "<data id=\"history\">\n" +
            "<metadata>\n" +
            "<columns>\n" +
            "<column name=\"BOARDID\" type=\"string\" bytes=\"12\" max_size=\"0\" />\n" +
            "<column name=\"TRADEDATE\" type=\"date\" bytes=\"10\" max_size=\"0\" />\n" +
            "<column name=\"SHORTNAME\" type=\"string\" bytes=\"189\" max_size=\"0\" />\n" +
            "<column name=\"SECID\" type=\"string\" bytes=\"36\" max_size=\"0\" />\n" +
            "<column name=\"NUMTRADES\" type=\"double\" />\n" +
            "<column name=\"VALUE\" type=\"double\" />\n" +
            "<column name=\"OPEN\" type=\"double\" />\n" +
            "<column name=\"LOW\" type=\"double\" />\n" +
            "<column name=\"HIGH\" type=\"double\" />\n" +
            "<column name=\"LEGALCLOSEPRICE\" type=\"double\" />\n" +
            "<column name=\"WAPRICE\" type=\"double\" />\n" +
            "<column name=\"CLOSE\" type=\"double\" />\n" +
            "<column name=\"VOLUME\" type=\"double\" />\n" +
            "<column name=\"MARKETPRICE2\" type=\"double\" />\n" +
            "<column name=\"MARKETPRICE3\" type=\"double\" />\n" +
            "<column name=\"ADMITTEDQUOTE\" type=\"double\" />\n" +
            "<column name=\"MP2VALTRD\" type=\"double\" />\n" +
            "<column name=\"MARKETPRICE3TRADESVALUE\" type=\"double\" />\n" +
            "<column name=\"ADMITTEDVALUE\" type=\"double\" />\n" +
            "<column name=\"WAVAL\" type=\"double\" />\n" +
            "</columns>\n" +
            "</metadata>\n" +
            "<rows>\n" +
            "<row BOARDID=\"TQDE\" TRADEDATE=\"2020-04-14\" SHORTNAME=\"АСКО ао\" SECID=\"ACKO\" NUMTRADES=\"120\" VALUE=\"522978\" OPEN=\"4.1\" LOW=\"4.1\" HIGH=\"4.24\" LEGALCLOSEPRICE=\"4.12\" WAPRICE=\"4.14\" CLOSE=\"4.1\" VOLUME=\"126200\" MARKETPRICE2=\"4.14\" MARKETPRICE3=\"4.14\" ADMITTEDQUOTE=\"4.12\" MP2VALTRD=\"522978\" MARKETPRICE3TRADESVALUE=\"522978\" ADMITTEDVALUE=\"522978\" WAVAL=\"\" />\n" +
            "</rows>\n" +
            "</data>\n" +
            "<data id=\"history.cursor\">\n" +
            "<metadata>\n" +
            "<columns>\n" +
            "<column name=\"INDEX\" type=\"int64\" />\n" +
            "<column name=\"TOTAL\" type=\"int64\" />\n" +
            "<column name=\"PAGESIZE\" type=\"int64\" />\n" +
            "</columns>\n" +
            "</metadata>\n" +
            "<rows>\n" +
            "<row INDEX=\"0\" TOTAL=\"483\" PAGESIZE=\"1\" />\n" +
            "</rows>\n" +
            "</data>\n" +
            "</document>";

    private static final int BASE_ID = START_SEQ;

    public static final int SECURITY_ID = BASE_ID + 1;

    public static final int NEW_ID = BASE_ID + 6;

    public static final LocalDate TRADE_DATE = LocalDate.parse("2020-04-15");

    public static final History HISTORY_1 = new History(null, BASE_ID + 3, "TQDE", TRADE_DATE, 148, 497102, 4.04, 4.02, 3.94, 4.24, 121700);
    public static final History HISTORY_2 = new History(null, BASE_ID + 4, "SMAL", TRADE_DATE, 3, 203.61, 14.441, 13.776, 13.44, 14.441, 15);
    public static final History HISTORY_3 = new History(null, BASE_ID + 5, "SMAL", TRADE_DATE, 9, 1757.98, 77.36, 71.02, 71.02, 77.36, 24);

    public static final History HISTORY_UPLOADED = new History(null, NEW_ID, "TQDE", TRADE_DATE.minusDays(1), 120, 522978, 4.1, 4.24, 4.1, 4.1, 126200);
    public static final History HISTORY_PARSED = new History(null, null, "TQDE", TRADE_DATE.minusDays(1), 120, 522978, 4.1, 4.24, 4.1, 4.1, 126200);

    public static List<History> getHistories() {
        return List.of(HISTORY_1, HISTORY_2, HISTORY_3);
    }

    public static History getNew() {
        return new History(null, null, "TQDE", TRADE_DATE.plusDays(1), 841, 102497, 3.04, 3.02, 2.94, 3.24, 421700);
    }

    public static History getUpdated(History history) {
        History updated = new History(history);
        updated.setBoardId("UPD");
        return updated;
    }

    public static List<History> getHistoriesWithSecurities() {
        History history1 = new History(HISTORY_1);
        History history2 = new History(HISTORY_2);
        History history3 = new History(HISTORY_3);

        history1.setSecurity(SecurityTestData.SECURITY_1);
        history2.setSecurity(SecurityTestData.SECURITY_2);
        history3.setSecurity(SecurityTestData.SECURITY_3);

        return List.of(history1, history2, history3);
    }

    public static History getHistoryWithSecurity() {
        History history = new History(HISTORY_2);
        history.setSecurity(SecurityTestData.SECURITY_2);

        return history;
    }

    public static MockMultipartFile getTestMultipartFile() {
        return new MockMultipartFile("file", XML_CONTENT.getBytes());
    }
}
