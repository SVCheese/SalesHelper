package cheese.soft.saleshelper.data.db;

public class DBTables {

    public static final String TABLE_GOODS = "goods";
    public static final String GOODS_ID = "_id";
    public static final String GOODS_INNER_CODE = "inner_code";
    //public static final String COL_GOODS_GROUP_ID = "group_id";
    public static final String GOODS_NAME = "name";
    public static final String GOODS_QUANTITY = "quantity";
    public static final String GOODS_PURCHASE_PRICE = "purchase_price";
    public static final String GOODS_PRICE = "price";

    public static final String DB_TABLE_GOODS = "CREATE TABLE " + TABLE_GOODS + "("
            + GOODS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + GOODS_INNER_CODE + " INTEGER, "
            + GOODS_NAME + " TEXT, "
            + GOODS_QUANTITY + " INTEGER, "
            + GOODS_PURCHASE_PRICE + " REAL, "
            + GOODS_PRICE + " REAL " + ");";
}
