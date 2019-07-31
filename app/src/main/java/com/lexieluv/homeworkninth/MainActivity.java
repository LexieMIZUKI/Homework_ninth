package com.lexieluv.homeworkninth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private EditText et_reader,et_time;
    private TextView tv_time;
    private RadioGroup rg_gender;
    private RadioButton rb_male,rb_female;
    private boolean isHis,isSus,isLite;
    private CheckBox history,suspect,literature;
    private SeekBar sb_age;
    private TextView tv_sb;
    private int f_age = 18;
    private ImageView iv_book;
    private TextView name;
    private TextView type;
    private TextView age;
    private TextView tv_find;
    private Button find,next;
    Book book;
    Person person;
    List<Book> bookList ;
    List<Book> books;
    Iterator<Book> it;
    private int count  = 0;

    private radioListener rl;
    private checkListener cl;
    private seekListener sl;
    private buttonListener bl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniView();
        iniData();
        setListener();
    }

    private void setListener() {
        rl = new radioListener();
        rg_gender.setOnCheckedChangeListener(rl);

        cl = new checkListener();
        history.setOnCheckedChangeListener(cl);
        suspect.setOnCheckedChangeListener(cl);
        literature.setOnCheckedChangeListener(cl);

        sl = new seekListener();
        sb_age.setOnSeekBarChangeListener(sl);

        bl = new buttonListener();
        find.setOnClickListener(bl);
        next.setOnClickListener(bl);
    }

    private void iniView() {
        et_reader = findViewById(R.id.et_reader);
        et_time = findViewById(R.id.et_time);
        tv_time = findViewById(R.id.tv_time);

        rg_gender = findViewById(R.id.rg_gender);
        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);

        history = findViewById(R.id.cb_history);
        suspect = findViewById(R.id.cb_suspect);
        literature = findViewById(R.id.cb_literature);

        sb_age = findViewById(R.id.sb_age);
        tv_sb = findViewById(R.id.tv_sb);

        iv_book = findViewById(R.id.iv_book);

        name = findViewById(R.id.tv_name);
        type = findViewById(R.id.tv_type);
        age = findViewById(R.id.tv_age);

        tv_find = findViewById(R.id.tv_find);
        find = findViewById(R.id.bt_find);
        next = findViewById(R.id.bt_next);
    }

    private void iniData() {
        person = new Person();
        book = new Book();
        bookList = new ArrayList<Book>();
        books = new ArrayList<Book>();
        bookList.add(new Book("人生感悟",true,false,false,30,R.drawable.aa));
        bookList.add(new Book("边城",true,false,true,36,R.drawable.bb));
        bookList.add(new Book("saplr",false,false,false,20,R.drawable.cc));
        bookList.add(new Book("光辉岁月",false,false,true,50,R.drawable.dd));
        bookList.add(new Book("宋词三百首",false,false,true,18,R.drawable.ee));
        bookList.add(new Book("中国古代文学",true,false,true,45,R.drawable.ff));
        bookList.add(new Book("无花果",false,true,true,22,R.drawable.gg));
        bookList.add(new Book("古镇记忆",true,true,false,29,R.drawable.hh));
    }

    //选择性别
    class radioListener implements OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_female:
                    person.setGender("女");
                    break;
                case R.id.rb_male:
                    person.setGender("男");
                    break;
            }
        }
    }

    //选择爱好
    class checkListener implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            CheckBox cb = (CheckBox) buttonView;
            switch (cb.getId()){
                case R.id.cb_history:
                    if(isChecked)
                        isHis = true;
                    else
                        isHis =false;
                    break;
                case R.id.cb_suspect:
                    if(isChecked)
                        isSus = true;
                    else
                        isSus = false;
                    break;
                case R.id.cb_literature:
                    if(isChecked)
                        isLite = true;
                    else
                        isLite = false;
                    break;
            }
        }
    }

    //选择年龄
    class seekListener implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            f_age = seekBar.getProgress();
            tv_sb.setText(f_age+"");
        }
    }

    //点击查找和下一个
    class buttonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_find:
                    books.clear();
                    count = 0;
                    checkData();
                    //先进行符合条件的寻找
                    if(checkTime(et_time.getText().toString(),tv_time.getText().toString())){
                        Toast.makeText(MainActivity.this,"借书时间晚于归还时间！程序将退出！",Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        if(books.size() == 0) {
                            iv_book.setImageResource(R.drawable.f);
                            name.setText("书名");
                            type.setText("类型");
                            age.setText("适用年龄");
                            Toast.makeText(MainActivity.this, "未找到任何书籍！请重新选择！", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            showPic(count);
                            String n = name.getText().toString();
                            String t = et_time.getText().toString();
                            person.setName(n);
                            person.setTime(t);
                            person.setBook(books.get(count));
                            Toast.makeText(MainActivity.this, "个人信息为：" + person, Toast.LENGTH_SHORT).show();
                            count++;
                        }

                    }
                    break;
                case R.id.bt_next:
                    //进行切换
                    //首先若无数据直接点击可能发生的异常处理
                    try{
                    if(books.size() == 0) {
                        Toast.makeText(MainActivity.this, "未找到任何书籍！请重新选择！", Toast.LENGTH_SHORT).show();
                    }}catch (IndexOutOfBoundsException e){
                       e.printStackTrace();
                    }
                    if(count < books.size()) {
                        showPic(count);
                        String n = name.getText().toString();
                        String t = et_time.getText().toString();
                        person.setName(n);
                        person.setTime(t);
                        person.setBook(books.get(count));
                        Toast.makeText(MainActivity.this, "个人信息为：" + person, Toast.LENGTH_SHORT).show();
                        count++;
                    }else {
                        Toast.makeText(MainActivity.this, "由于已经是最后一个书籍，从头显示！", Toast.LENGTH_SHORT).show();
                        count = 0;
                        showPic(count);
                        count++;
                    }
            }
        }
    }

    private void checkData() {
        for(int i = 0;i < bookList.size();i++){
            Book b = bookList.get(i);
            if((b.getAge() <=  f_age)&&(b.isHis() == isHis & b.isSus() == isSus & b.isLite() == isLite)){
                books.add(b);
            }
        }
        tv_find.setText("符合条件的书有"+books.size()+"本");
        System.out.println("*****************   "+books.size()+"   "+bookList.size());
    }

    private void showPic(int count){
        if(count >= books.size()){
            count = 0;
        }
        iv_book.setImageResource(books.get(count).getPic());
        System.out.println("**************  "+count);
        //改变图片旁的信息
        while(!books.isEmpty()) {
            if (books.get(count).getPic() == R.drawable.aa) {
                name.setText("人生感悟");
                type.setText("历史");
                age.setText("30");
                break;
            }
            if (books.get(count).getPic() == R.drawable.bb) {
                name.setText("边城");
                type.setText("历史，文学");
                age.setText("36");
                break;
            }
            if (books.get(count).getPic() == R.drawable.cc) {
                name.setText("saplr");
                type.setText("科学");
                age.setText("20");
                break;
            }
            if (books.get(count).getPic() == R.drawable.dd) {
                name.setText("光辉岁月");
                type.setText("文学");
                age.setText("50");
                break;
            }
            if (books.get(count).getPic() == R.drawable.ee) {
                name.setText("宋词三百首");
                type.setText("文学");
                age.setText("18");
                break;
            }
            if (books.get(count).getPic() == R.drawable.ff) {
                name.setText("中国古代文学");
                type.setText("历史，文学");
                age.setText("45");
                break;
            }
            if (books.get(count).getPic() == R.drawable.gg) {
                name.setText("无花果");
                type.setText("悬疑，文学");
                age.setText("22");
                break;
            }
            if (books.get(count).getPic() == R.drawable.hh) {
                name.setText("古镇记忆");
                type.setText("历史，悬疑");
                age.setText("29");
                break;
            }
        }
    }
    //修改时间格式
    private boolean checkTime(String str1,String str2){
        boolean FisBigger = false;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = sdf.parse(str1);
            d2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        if(d1.getTime() > d2.getTime())
//            FisBigger = true;
//        if(d1.getTime() <= d2.getTime())
//            FisBigger = false;
        if(d1.after(d2))
            FisBigger = true;
        if(d1.before(d2))
            FisBigger = false;
        return FisBigger;


    }

}
