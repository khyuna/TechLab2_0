package com.example.hyuna.techlab20;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentMain extends Fragment {
    PieChart customChart;
    String nowMonth=null;
    String nowYearMonth = null;
    int sum =0;
    int eat=0; //식비
    int culture=0;//문화비
    int extra=0;
    int auto=0;
    int fee =0;
    ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();
    int month=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View mainView = inflater.inflate(R.layout.fragment_main, container, false);

        long now = System.currentTimeMillis();
        Date today = new Date(now);
        SimpleDateFormat monthsdf = new SimpleDateFormat("MM");
        SimpleDateFormat yearmonth = new SimpleDateFormat("YYYYMM");
        nowMonth = monthsdf.format(today);
        month = Integer.parseInt(nowMonth);
        nowYearMonth = yearmonth.format(today);

        TextView tv_month = mainView.findViewById(R.id.mainMonth);
        tv_month.setText(String.valueOf(month)+"월");

        customChart = mainView.findViewById(R.id.piechart);
        drawPieChart();
        return mainView;
    }

    public void drawPieChart(){
        customChart.setUsePercentValues(true);
        customChart.getDescription().setEnabled(false);
        customChart.setExtraOffsets(5,10,5,5);

        customChart.setDragDecelerationFrictionCoef(0.95f);
        customChart.setTouchEnabled(false);
        customChart.setDrawHoleEnabled(false);
        customChart.setHoleColor(Color.WHITE);
        customChart.setTransparentCircleRadius(61f);
        calRatioChart();


    }

    //비율 구하는 함수
    public void calRatioChart() {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference root = databaseReference.child("customer");

        Query query = root.child("001").orderByChild("year").startAt(nowYearMonth).endAt(nowYearMonth+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                yValues.clear();//일별 리스트 클리어
                if (dataSnapshot.getChildrenCount() == 0) {
                    //하나도 없을 경우
                    //즉, 해당 날짜에 거래 내역이 하나도 존재하지 않는 경우
                    //토스트팝업 띄우기

                } else {
                    for (DataSnapshot fileSnapshot : dataSnapshot.getChildren())
                    {
                        if(fileSnapshot.child("ref_ctt").getValue(String.class).equals("MINUS")) {

                            String kind = fileSnapshot.child("ref_ctt2").getValue(String.class);
                            int amount = fileSnapshot.child("balance").getValue(Integer.class);

                            switch (kind) {
                                case "eat":
                                    sum += amount; //전체 금액 구하기
                                    eat += amount;
                                    break;
                                case "culture":
                                    sum += amount; //전체 금액 구하기
                                    culture += amount;
                                    break;
                                case "auto":
                                    auto += amount;
                                    break;
                                case "fee":
                                    fee += amount;
                                    break;
                                case "extra":
                                    sum += amount; //전체 금액 구하기
                                    extra += amount;
                                    break;

                            }
                        }
                    }
                    drawChart();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", "Failed to read value", databaseError.toException());
            }
        });
    }

    public void drawChart(){

        //값이 0인거는 아예 넣으면 안됨.
        if(eat > 0){
            yValues.add(new PieEntry((float)eat,"식비"));
        }
        if(culture > 0){
            yValues.add(new PieEntry((float)culture,"문화비"));
        }
        if(auto > 0){
            yValues.add(new PieEntry((float)auto,"자동이체"));
        }
        if(fee > 0){
            yValues.add(new PieEntry((float)fee,"공과금"));
        }
        if(extra > 0){
            yValues.add(new PieEntry((float)extra,"기타"));
        }

        customChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션

        PieDataSet dataSet = new PieDataSet(yValues,"");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(16f);
        data.setValueTextColor(Color.LTGRAY);


        customChart.setData(data);
    }

}

