package com.example.hyuna.techlab20;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentDaily extends DialogFragment{

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    public static ArrayList<Dictionary> list = new ArrayList<>();
    public static RecyclerView rv = null;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View viewDaily = inflater.inflate(R.layout.fragment_daily, container, false);
        //날짜 셋팅
        Bundle mArgs = getArguments();
        String date = mArgs.getString("date");
        String dateIs = mArgs.getString("dateIs");
        TextView tv_month = viewDaily.findViewById(R.id.tt_month);
        tv_month.setText(date);
        rv = viewDaily.findViewById(R.id.recycleView);
        getDailyData(date,dateIs);
        TextView tv_daily = viewDaily.findViewById(R.id.tt_daily);



        //x 버튼
        ImageButton xmark = viewDaily.findViewById(R.id.xmarker);
        xmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return viewDaily;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog!=null){
            int width = ConstraintLayout.LayoutParams.MATCH_PARENT;
            int height = ConstraintLayout.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    private void getDailyData(String date, String dateIs){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference root = databaseReference.child("customer");

        Query query =  root.child("001").orderByChild("year").equalTo(dateIs);
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();//일별 리스트 클리어
                if(dataSnapshot.getChildrenCount()==0){
                    //하나도 없을 경우
                    //즉, 해당 날짜에 거래 내역이 하나도 존재하지 않는 경우
                    //토스트팝업 띄우기
                    
                }else{
                    for(DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                        Dictionary dic;
                        int amount = fileSnapshot.child("balance").getValue(Integer.class);
                        String amountFormat = DataUtil.amountFormatter(amount);
                        String date = fileSnapshot.child("year").getValue(String.class);
                        String purpose = fileSnapshot.child("ref_ctt").getValue(String.class);
                        String result = fileSnapshot.child("ref_ctt2").getValue(String.class);
                        dic = new Dictionary(date, purpose, amountFormat, result);

                        list.add(dic);//해당 날짜에 대한 정보 저장
                    }
                }

                RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(list);

                rv.setAdapter(recycleViewAdapter);

                //선 넣기
                RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
                rv.addItemDecoration(decoration);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", "Failed to read value", databaseError.toException());
            }
        });
    }


    
}
