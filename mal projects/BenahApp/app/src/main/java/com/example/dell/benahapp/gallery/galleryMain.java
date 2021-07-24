package com.example.dell.benahapp.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dell.benahapp.R;

import java.io.Serializable;
import java.util.ArrayList;


public class galleryMain extends AppCompatActivity implements Serializable{

    RecyclerView rvItem;
    CardView cvItem;

    String[] texts = {
            "الإعداد للملتقى العلمى العربى الاول لمركز تنمية القدرات بجامعة بنها 2017/02/23",
            "منتدى الحوار الأول للجامعات الحكومية والخاصة 2017/02/22",
            "جلسات إستماع لتطوير التعليم بجامعة بنها 2017/02/06",
            "قطاع الدراسات العليا والبحوث بجامعة بنها ينظم ورش عمل عن بنك المعرفة المصري 2016/11/16",
            "اتفاقية تعاون بين جامعتى بنها وسارى بالمملكة المتحدة 2016/10/31",
            "جامعة بنها تنظم احتفالية كبرى بذكرى إنتصارات حرب أكتوبر وتكرم أسر الشهداء 2016/10/06",
            "حفل افطار جماعي بمناسبة حصول كلية الزراعة على اﻻعتماد من الهيئة القومية لضمان جودة التعليم واﻻعتماد 2016/06/21",
            "انطلاق فاعليات مشروع الخدمة العامة مدينتي احلى واحلى بالمدينة الجامعية للطالبات بكفر سعد 2015/05/11",
            "زيارة وفد جامعة بنها لجامعة SHANGHAI JIAO TONG بالصين 2015/05/04",
            "طلاب جامعة بنها يحصدون 14 جائزة في مسابقة إبداع الشارقة الثقافية 2015/04/17"
    };

    String[] images = {
            "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/18951064_1300792430019734_3187577168079824109_n.jpg?oh=fa2eb9eebaa629d005a9fef773832463&oe=59E1A2FA",
            "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/19029426_1300792396686404_5637664542886673631_n.jpg?oh=80fb3591f591957ec79513f8d823175b&oe=59E0F49D",
            "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/18921683_1300792386686405_40649008052566764_n.jpg?oh=45ceb54964438c3b293ca5e21a9f18fc&oe=59A2C06A",
            "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/18951253_1300792453353065_7557961326199245170_n.jpg?oh=aeab4ce1500a46d1e8b71e2e9a39e9c3&oe=59A643BD",
            "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/18921681_1300792510019726_1407364617544021038_n.jpg?oh=62c14260cd3014c6edf432f2df81dc6f&oe=599E5426",
            "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/18839030_1300792640019713_7200364132837688111_n.jpg?oh=de7e9b9ea30203d6d3e78a41f0094430&oe=59E6F3AE",
            "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/19029649_1300792533353057_6551856870535604028_n.jpg?oh=91cfc05201c7b37eb288c142fdc45524&oe=59A9E7E5",
            "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/19059048_1300792666686377_1253139450091449438_n.jpg?oh=dd068f70ad7eb7d96344604616578e50&oe=59D6DF4B",
            "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/18921983_1300792673353043_8723769314598141439_n.jpg?oh=13c1f5da34ddad1a93d94c30256c9457&oe=59A55238",
            "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/19029523_1300792583353052_8686327473223210820_n.jpg?oh=8fe90c1681173c458f9668fcc73b9d77&oe=59E007F5"
    };

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_main);


        rvItem = (RecyclerView) findViewById(R.id.rvItem);
        rvItem.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvItem.setLayoutManager(manager);

        cvItem = (CardView) findViewById(R.id.cvItem);

        ArrayList<Item> itemList = dummy();

        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), itemList);
        rvItem.setAdapter(adapter);

    }

    private ArrayList<Item> dummy(){
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.id = i;
            item.img = images[i-1];
            item.text = texts[i-1];
            item.loves = 0;
            item.hates = 0;
            list.add(item);
        }

        return list;
    }
}
