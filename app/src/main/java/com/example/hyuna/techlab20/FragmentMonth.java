package com.example.hyuna.techlab20;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentMonth extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_monthly, container, false);
        CalendarView calendarView = null;
        calendarView=(CalendarView)view.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                String date = year+"년 "+ (month+1) +"월 "+ dayOfMonth+"일";
                String dateIs =null;
                if(month <9){
                    if(dayOfMonth<9){
                        dateIs = year +"0"+ (month+1)+"0"+dayOfMonth;
                    }else{
                        dateIs = year +"0"+ (month+1)+""+dayOfMonth;
                    }
                }else{
                    if(dayOfMonth<9){
                        dateIs = year +""+ (month+1)+"0"+dayOfMonth;
                    }else{
                        dateIs = year +""+ (month+1)+""+dayOfMonth;
                    }

                }


                Bundle args = new Bundle();
                args.putString("date", date);
                args.putString("dateIs", dateIs);
                FragmentDaily dialogFragment = new FragmentDaily();
                dialogFragment.setArguments(args);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                dialogFragment.show(ft,"daily fragment");

                //fragmentDaily.setText(date);
            }
        });
        return view;
    }


}
