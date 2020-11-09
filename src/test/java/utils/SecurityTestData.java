package utils;

import model.Security;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static model.AbstractBaseEntity.START_SEQ;

public class SecurityTestData {
    public static final Matcher<Security> MATCHER = Matcher.usingFieldsComparator(Security.class, "histories");
    public static final Matcher<Security> MATCHER_TO = Matcher.usingEquals(Security.class);

    public static final String XML_CONTENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<document>\n" +
            "<data id=\"securities\">\n" +
            "<metadata>\n" +
            "<columns>\n" +
            "<column name=\"id\" type=\"int32\" />\n" +
            "<column name=\"secid\" type=\"string\" bytes=\"36\" max_size=\"0\" />\n" +
            "<column name=\"shortname\" type=\"string\" bytes=\"189\" max_size=\"0\" />\n" +
            "<column name=\"regnumber\" type=\"string\" bytes=\"189\" max_size=\"0\" />\n" +
            "<column name=\"name\" type=\"string\" bytes=\"765\" max_size=\"0\" />\n" +
            "<column name=\"isin\" type=\"string\" bytes=\"765\" max_size=\"0\" />\n" +
            "<column name=\"is_traded\" type=\"int32\" />\n" +
            "<column name=\"emitent_id\" type=\"int32\" />\n" +
            "<column name=\"emitent_title\" type=\"string\" bytes=\"765\" max_size=\"0\" />\n" +
            "<column name=\"emitent_inn\" type=\"string\" bytes=\"30\" max_size=\"0\" />\n" +
            "<column name=\"emitent_okpo\" type=\"string\" bytes=\"24\" max_size=\"0\" />\n" +
            "<column name=\"gosreg\" type=\"string\" bytes=\"189\" max_size=\"0\" />\n" +
            "<column name=\"type\" type=\"string\" bytes=\"93\" max_size=\"0\" />\n" +
            "<column name=\"group\" type=\"string\" bytes=\"93\" max_size=\"0\" />\n" +
            "<column name=\"primary_boardid\" type=\"string\" bytes=\"12\" max_size=\"0\" />\n" +
            "<column name=\"marketprice_boardid\" type=\"string\" bytes=\"12\" max_size=\"0\" />\n" +
            "</columns>\n" +
            "</metadata>\n" +
            "<rows>\n" +
            "<row id=\"12441\" secid=\"ABRD\" shortname=\"АбрауДюрсо\" regnumber=\"1-02-12500-A\" name=\"Абрау-Дюрсо ПАО ао\" isin=\"RU000A0JS5T7\" is_traded=\"1\" emitent_id=\"2556\" emitent_title=\"Публичное акционерное общество &quot;Абрау – Дюрсо&quot;\" emitent_inn=\"7727620673\" emitent_okpo=\"81521198\" gosreg=\"1-02-12500-A\" type=\"common_share\" group=\"stock_shares\" primary_boardid=\"TQBR\" marketprice_boardid=\"TQBR\" />\n" +
            "</rows>\n" +
            "</data>\n" +
            "</document>";

    private static final int BASE_ID = START_SEQ;
    public static final int NEW_ID = BASE_ID + 6;

    public static final Security SECURITY_1 = new Security(BASE_ID, "ACKO", "1-01-52065-Z", "АСКО-СТРАХОВАНИЕ ПАО ао", "RU000A0JXS91", "Публичное акционерное общество \"АСКО-СТРАХОВАНИЕ\"");
    public static final Security SECURITY_2 = new Security(BASE_ID + 1, "AFKS", "1-05-01669-A", "АФК \"Система\" ПАО ао", "RU000A0DQZE3", "ПУБЛИЧНОЕ АКЦИОНЕРНОЕ ОБЩЕСТВО \"АКЦИОНЕРНАЯ ФИНАНСОВАЯ КОРПОРАЦИЯ \"СИСТЕМА\"");
    public static final Security SECURITY_3 = new Security(BASE_ID + 2, "AFLT", "1-01-00010-A", "Аэрофлот-росс.авиалин(ПАО)ао", "RU0009062285", "Публичное акционерное общество \"Аэрофлот – российские авиалинии\"");

    public static final Security SECURITY_UPLOADED = new Security(NEW_ID, "ABRD", "1-02-12500-A", "Абрау-Дюрсо ПАО ао", "RU000A0JS5T7", "Публичное акционерное общество \"Абрау – Дюрсо\"");
    public static final Security SECURITY_PARSED = new Security(null, "ABRD", "1-02-12500-A", "Абрау-Дюрсо ПАО ао", "RU000A0JS5T7", "Публичное акционерное общество \"Абрау – Дюрсо\"");


    public static List<Security> getSecurities() {
        return List.of(SECURITY_1, SECURITY_2, SECURITY_3);
    }

    public static Security getNew() {
        return new Security(null, "NEW", "1-01-52065-NEW", "СТРАХОВАНИЕ ПАО ао", "NEW000A0JXS91", "Публичное акционерное общество \"NEW-СТРАХОВАНИЕ\"");
    }

    public static Security getUpdated(Security security) {
        Security updated = new Security(security);
        updated.setIdOnExchange("UPD");
        return updated;
    }

    public static MockMultipartFile getTestMultipartFile() {
        return new MockMultipartFile("file", XML_CONTENT.getBytes());
    }
}
