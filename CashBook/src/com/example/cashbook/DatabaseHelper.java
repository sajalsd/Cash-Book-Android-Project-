package com.example.cashbook;

import java.util.ArrayList;

import com.example.cashbook.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "cash_management";
	public static final int DB_VERSION = 1;

	// TABLE for User Password and information
	// LOGIN TABLE
	public static final String LOGIN_TABLE = "login";
	public static final String NAME_FIELD = "name";
	public static final String PHONE_FIELD = "phone";
	public static final String USERNAME_FIELD = "username";
	public static final String PASSWORD_FIELD = "password";

	public static final String LOGIN_TABLE_SQL = "CREATE TABLE " + LOGIN_TABLE
			+ "(" + USERNAME_FIELD + " TEXT  PRIMARY KEY, " + NAME_FIELD
			+ " TEXT , " + PHONE_FIELD + " TEXT, " + PASSWORD_FIELD + " TEXT);";

	// TABLE FOR accounts information
	// ACCOUNT TABLE
	public static final String ACCOUNT_TABLE = "accounts";
	public static final String AC_NAME_FIELD = "name";
	public static final String AC_PHONE_FIELD = "phone";
	public static final String AC_TYPE_FIELD = "actype";

	public static final String ACCOUNT_TABLE_SQL = "CREATE TABLE "
			+ ACCOUNT_TABLE + " (" + AC_NAME_FIELD + " TEXT PRIMARY KEY, "
			+ AC_PHONE_FIELD + " TEXT, " + AC_TYPE_FIELD + " TEXT);";

	// TABLE FOR transactions of accounts
	// TRANSACTION TABLE
	public static final String TRANSACTION_TABLE = "transactions";
	public static final String TR_ID_FIELD = "key_id";
	public static final String TR_NAME_FIELD = "trname";
	public static final String TR_DATE_FIELD = "date";
	public static final String TR_TYPE_FIELD = "trtype";
	public static final String TR_AMOUNT_FIELD = "amount";

	public static final String TRANSACTION_TABLE_SQL = "CREATE TABLE "
			+ TRANSACTION_TABLE + " (" + TR_ID_FIELD
			+ " INTEGER PRIMARY KEY AUTOINCREMENT , " + TR_NAME_FIELD
			+ " TEXT, " + TR_DATE_FIELD + " TEXT, " + TR_TYPE_FIELD + " text, "
			+ TR_AMOUNT_FIELD + " TEXT, FOREIGN KEY (" + TR_NAME_FIELD
			+ ") REFERENCES " + ACCOUNT_TABLE + " (" + AC_NAME_FIELD + "));";

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// table creation
		db.execSQL(LOGIN_TABLE_SQL);
		Log.e("TABLE CREATE", LOGIN_TABLE_SQL);
		db.execSQL(ACCOUNT_TABLE_SQL);
		Log.e("TABLE CREATE", ACCOUNT_TABLE_SQL);
		db.execSQL(TRANSACTION_TABLE_SQL);
		Log.e("TABLE CREATE", TRANSACTION_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	// first time takes user info
	public long insertUser(User usr) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(NAME_FIELD, usr.getName());
		values.put(PHONE_FIELD, usr.getPhone());
		values.put(USERNAME_FIELD, usr.getUsername());
		values.put(PASSWORD_FIELD, usr.getPassword());

		long inserted = db.insert(LOGIN_TABLE, null, values);
		db.close();
		return inserted;
	}

	// return user password to check
	public String login_cheak() {
		String password = null;
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.query(LOGIN_TABLE, null, null, null, null, null, null);

		if ((c != null) && (c.getCount() > 0)) {
			c.moveToFirst();
			password = c.getString(c.getColumnIndex(PASSWORD_FIELD));
		}
		db.close();
		c.close();

		return password;
	}

	// get number of user
	public int getUser() {

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(LOGIN_TABLE, null, null, null, null, null, null);

		c.getCount();

		return c.getCount();
	}

	//

	public long insertAccount(Account ac) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(AC_NAME_FIELD, ac.getAcName());
		values.put(AC_PHONE_FIELD, ac.getAcPhone());
		values.put(AC_TYPE_FIELD, ac.getAcType());

		long inserted = db.insert(ACCOUNT_TABLE, null, values);
		db.close();
		return inserted;
	}

	public long insertTransaction(Transaction tr) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		String tr_type = tr.getTrType();
		
		
		if(tr_type.equals("Receivable")){
			tr_type = "Pay";
		}else if (tr_type.equals("Payable")){
			tr_type = "Receive";
		}else if (tr_type.equals("Receive")){
			tr_type = "Receive";
		}else{
			tr_type = "Pay";
		}
			
			
		
		
		values.put(TR_NAME_FIELD, tr.getTrName());
		values.put(TR_DATE_FIELD, tr.getTrDate());
		values.put(TR_TYPE_FIELD, tr_type);
		values.put(TR_AMOUNT_FIELD, tr.getTrAmount());

		long inserted = db.insert(TRANSACTION_TABLE, null, values);
		db.close();
		return inserted;
	}

	public ArrayList<Account> getAllAccount(String actype) {

		ArrayList<Account> allAccount = new ArrayList<Account>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c;
		if (actype.equals("Show All")) {
			c = db.query(ACCOUNT_TABLE, null, null, null, null, null,
					AC_NAME_FIELD);
		} else {
			c = db.query(ACCOUNT_TABLE, null, AC_TYPE_FIELD + " =?",
					new String[] { String.valueOf(actype) }, null, null,
					AC_NAME_FIELD);
		}

		if ((c != null) && (c.getCount() > 0)) {

			c.moveToFirst();

			for (int i = 0; i < c.getCount(); i++) {

				String name = c.getString(c.getColumnIndex(AC_NAME_FIELD));
				String phone = c.getString(c.getColumnIndex(AC_PHONE_FIELD));
				String type = c.getString(c.getColumnIndex(AC_TYPE_FIELD));

				Account ac = new Account(name, phone, type);

				allAccount.add(ac);

				c.moveToNext();

			}
		}
		db.close();
		c.close();

		return allAccount;
	}

	
	public ArrayList<Transaction> getAllTransaction(String contactName) {
		
		ArrayList<Transaction> alltr = new ArrayList<Transaction>();
		SQLiteDatabase db = this.getReadableDatabase();
		

		Cursor ctr = db.query(TRANSACTION_TABLE, null, TR_NAME_FIELD + " =?", new String[] { String.valueOf(contactName) }, null, null, null);
		
		
		Log.e("Number of Transaction found",String.valueOf(ctr.getCount()));
		Log.e("Number of Transaction found",contactName);
	
		if ((ctr != null) && (ctr.getCount() > 0)) {

			ctr.moveToFirst();

			for (int i = 0; i < ctr.getCount(); i++) {

				String name = ctr.getString(ctr.getColumnIndex(TR_NAME_FIELD));
				String date = ctr.getString(ctr.getColumnIndex(TR_DATE_FIELD));
				String amount = ctr.getString(ctr.getColumnIndex(TR_AMOUNT_FIELD));
				String trType = ctr.getString(ctr.getColumnIndex(TR_TYPE_FIELD));
				
				Transaction tr = new Transaction(name, date, amount, trType);

				alltr.add(tr);

				ctr.moveToNext();

			}
		}
		
		db.close();
		ctr.close();

		return alltr;

	}
	
	public Transaction getTotalTransactioninfo(String Contact_acName)
	{
		Transaction alltrinfo = new Transaction(null,null,null,null) ; 
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c;
		Log.e("Running Properly","........................");
		c = db.query(TRANSACTION_TABLE, null, TR_NAME_FIELD + " = ?",
					new String[] { String.valueOf(Contact_acName) }, null, null,
					null);
		int sum_receive=0, sum_pay = 0;

		if ((c != null) && (c.getCount() > 0)) {

			c.moveToFirst();

			for (int i = 0; i < c.getCount(); i++) {

				String tr_type = c.getString(c.getColumnIndex(TR_TYPE_FIELD));			
				int amount = Integer.parseInt(c.getString(c.getColumnIndex(TR_AMOUNT_FIELD)));
				
				if(tr_type.equals("Receive"))
				{
					sum_receive = sum_receive + amount; 
				}
				else
				{
					sum_pay = sum_pay+ amount;
				}
				
				c.moveToNext();

			}
			
			if(sum_pay < sum_receive)
			{
				alltrinfo.setTrType("Payable");
				int dummy = sum_receive - sum_pay;
				alltrinfo.setTrAmount(Integer.toString(dummy));
				alltrinfo.setTrName(Contact_acName);
				ContentValues args = new ContentValues();
			    args.put(AC_TYPE_FIELD, "Payable");
			    String whereArgs[] = new String[1];
			    whereArgs[0] = "" + Contact_acName;
			    db.update(ACCOUNT_TABLE, args, "name= ?", whereArgs);
			      
				//db.execSQL("AlTER TABLE "+ACCOUNT_TABLE+";"+ "\n UPDATE SET " +AC_NAME_FIELD+ "=  Payable where " +AC_NAME_FIELD+ "=" +Contact_acName+";");
			}
			else
			{
				alltrinfo.setTrType("Receivable");
				int dummy = sum_pay - sum_receive ;
				alltrinfo.setTrAmount(Integer.toString(dummy));
				alltrinfo.setTrName(Contact_acName);
				ContentValues args = new ContentValues();
			    args.put(AC_TYPE_FIELD, "Receivable");
			    String whereArgs[] = new String[1];
			    whereArgs[0] = "" + Contact_acName;
			    db.update(ACCOUNT_TABLE, args, "name= ?", whereArgs);
			}
			
		}
		Log.e("Running Properly","........................");
		db.close();
		c.close();
		
		
		return alltrinfo;
	}
}
