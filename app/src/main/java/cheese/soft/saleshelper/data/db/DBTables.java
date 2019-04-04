package cheese.soft.saleshelper.data.db;

public class DBTables {

    private static final String IPKA = " INTEGER PRIMARY KEY AUTOINCREMENT, ";

    // ---------- TABLE GOODS -----------------------------------------------------
    public static final String TABLE_GOODS = "goods";
    public static final String GOODS_ID = "_id";
    public static final String GOODS_INNER_CODE = "inner_code";
    public static final String GOODS_GROUP_ID = "group_id";
    public static final String GOODS_NAME = "name";
    public static final String GOODS_QUANTITY = "quantity";
    public static final String GOODS_PURCHASE_PRICE = "purchase_price";
    public static final String GOODS_PRICE = "price";

    public static final String DB_TABLE_GOODS = "CREATE TABLE " + TABLE_GOODS + "("
            + GOODS_ID + IPKA
            + GOODS_INNER_CODE + " INTEGER, "
            + GOODS_GROUP_ID + " INTEGER, "
            + GOODS_NAME + " TEXT, "
            + GOODS_QUANTITY + " INTEGER, "
            + GOODS_PURCHASE_PRICE + " REAL, "
            + GOODS_PRICE + " REAL " + ");";

    // ---------- TABLE GROUPS -----------------------------------------------------
    public static final String TABLE_GROUPS = "groups";
    public static final String GROUP_ID = "_id";
    public static final String GROUP_NAME = "name";

    public static final String DB_TABLE_GROUPS = "CREATE TABLE " + TABLE_GROUPS + "("
            + GROUP_ID + IPKA
            + GROUP_NAME + " TEXT " + ");";
}
