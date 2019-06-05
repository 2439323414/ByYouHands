package com.example.com.example.familynumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import android.widget.Toast;

import com.example.R;


public class FamilyPhone extends AppCompatActivity {
    private MySQLiteOpenHelper dbHelper = null;

    private ListView lv_main;
    private SimpleAdapter adapter = null;
    private List<Map<String, Object>> totalList = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_phone);
        Toolbar toolbar =(Toolbar)findViewById(R.id.title2);
        setSupportActionBar(toolbar);
        dbHelper = new MySQLiteOpenHelper(this);
        lv_main = (ListView) findViewById(R.id.listView_main);

        totalList = getcontent();

        adapter = new SimpleAdapter(this, totalList, R.layout.item_listview_main,
                new String[] { "phonenumber", "username" },
                new int[] { R.id.textView_item_phonenumber, R.id.textView_item_username });
        lv_main.setAdapter(adapter);
        registerForContextMenu(lv_main);
        lv_main.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                final String number = totalList.get(arg2).get("phonenumber").toString();
                Builder builder = createAlertDialog(android.R.drawable.stat_sys_phone_call, "确定要拨打: " + number + " ？");
                builder.setPositiveButton("拨打", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" +number));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }


    private List<Map<String, Object>> getcontent() {
        return dbHelper.selectList("select * from tb_mycontacts", null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert:
                Builder builder_insert = createAlertDialog(android.R.drawable.ic_dialog_alert, "添加联系人信息");
                View view = getLayoutInflater().inflate(R.layout.dialog_insert, null);
                final EditText et_name = (EditText) view.findViewById(R.id.editText_dialog_name);
                final EditText et_number = (EditText) view.findViewById(R.id.editText_dialog_number);

                builder_insert.setView(view);
                builder_insert.setPositiveButton("确定", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = et_name.getText() + "";
                        String number = et_number.getText() + "";
                        if (name.equals("") || number.equals("")) {
                            toast("whta are you 弄啥咧？");
                        } else {
                            String sql = "insert into tb_mycontacts(username, phonenumber)values(?,?)";
                            boolean flag = dbHelper.execData(sql, new Object[] { name, number });
                            if (flag) {
                                toast("插入成功！");
                                reloadView();
                            } else {
                                toast("插入失败！");
                            }
                        }
                    }
                });
                builder_insert.show();
                break;
            case R.id.action_deleteAll:
                Builder builder_delete = createAlertDialog(android.R.drawable.ic_menu_delete, "确定要删除所有数据？");
                builder_delete.setPositiveButton("删除", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql = "delete from tb_mycontacts";
//                        boolean flag = dbHelper.deleteData(sql);
                        if (false) {
                            toast("删除所有数据成功！");
                            reloadView();
                        } else {
                            toast("删除所有数据失败！");
                        }
                    }
                });
                builder_delete.show();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        menu.setHeaderIcon(android.R.drawable.btn_dialog);
        String name = totalList.get(info.position).get("username").toString();
        String number = totalList.get(info.position).get("phonenumber").toString();
        menu.setHeaderTitle(name + "|" + number);
        getMenuInflater().inflate(R.menu.contextmenu_listview_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        String name = totalList.get(info.position).get("username").toString();
        String number = totalList.get(info.position).get("phonenumber").toString();
        final String _id = totalList.get(info.position).get("_id").toString();
        switch (item.getItemId()) {
            case R.id.action_delete:
                Builder builder_dele = createAlertDialog(android.R.drawable.ic_delete, "确定要删除？");
                builder_dele.setPositiveButton("删除", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql = "delete from tb_mycontacts where _id=?";
                        boolean flag = dbHelper.execData(sql, new Object[] { _id });
                        if (flag) {
                            toast("删除数据成功！");
                            reloadView();
                        } else {
                            toast("删除数据失败！");
                        }
                    }
                });
                builder_dele.show();
                break;

            case R.id.action_update:
                Builder builder_update = createAlertDialog(android.R.drawable.ic_dialog_alert, "修改联系人信息");
                View view = getLayoutInflater().inflate(R.layout.dialog_update, null);
                final EditText editText_name = (EditText) view.findViewById(R.id.editText_dialog_name);
                final EditText editText_number = (EditText) view.findViewById(R.id.editText_dialog_number);

                // 因为是更新，所以两个控件里应该有初始值
                editText_name.setText(name);
                editText_number.setText(number);

                builder_update.setView(view);

                builder_update.setPositiveButton("确认", new OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name_sure = editText_name.getText() + "";
                        String number_sure = editText_number.getText() + "";
                        String sql = "update tb_mycontacts set username=? , phonenumber=? where _id=?";
                        boolean flag = dbHelper.execData(sql, new Object[] { name_sure, number_sure, _id });
                        if (flag) {
                            toast("更新数据成功！");
                            reloadView();
                        } else {
                            toast("更新数据失败！");
                        }
                    }
                });
                builder_update.show();
                break;

            case R.id.action_sendsms:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:" + number));
                intent.putExtra("sms_body", "请抓紧打给我!");
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    protected void reloadView() {
        totalList.clear();
        totalList.addAll(getcontent());
        adapter.notifyDataSetChanged();
    }

    public void clickButton(View view) {
        lv_main.setSelection(0);
    }

    protected void toast(String string) {
        Toast.makeText(FamilyPhone.this, string, Toast.LENGTH_LONG).show();
    }

    private Builder createAlertDialog(int icDialogAlert, String string) {
        Builder builder = new Builder(this);
        builder.setIcon(icDialogAlert);
        builder.setTitle(string);
        builder.setNegativeButton("取消", null);
        return builder;
    }
}

