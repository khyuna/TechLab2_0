package com.example.hyuna.techlab20;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ViewPager viewPager= null;
    Handler handler = null;
    int pagenum = 0; //페이지 번호

    ImageView bottomBarButton1 ;
    ImageView bottomBarButton2 ;
    ImageView bottomBarButton3 ;

    final Context context = this;
    //final LoadingDialog loadingDialog=new LoadingDialog(MainActivity.this);;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBarButton1 =(ImageView)findViewById(R.id.bottomBarButton1);
        bottomBarButton2 = (ImageView)findViewById(R.id.bottomBarButton2);
        bottomBarButton3 = (ImageView)findViewById(R.id.bottomBarButton3);

        bottomBarButton1.setSelected(true);
        bottomBarButton2.setSelected(false);
        bottomBarButton3.setSelected(false);

        viewPager = (ViewPager)findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    bottomBarButton1.setSelected(true);
                    bottomBarButton2.setSelected(false);
                    bottomBarButton3.setSelected(false);
                }else if(position ==1){
                    bottomBarButton1.setSelected(false);
                    bottomBarButton2.setSelected(true);
                    bottomBarButton3.setSelected(false);
                }else if(position == 2){
                    bottomBarButton1.setSelected(false);
                    bottomBarButton2.setSelected(false);
                    bottomBarButton3.setSelected(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bottomBarButton1:
                //fragment_main
                viewPager.setCurrentItem(0);
                bottomBarButton1.setSelected(true);
                bottomBarButton2.setSelected(false);
                bottomBarButton3.setSelected(false);
                break;
            case R.id.bottomBarButton2:
                //fragment_month
                viewPager.setCurrentItem(1);
                bottomBarButton1.setSelected(false);
                bottomBarButton2.setSelected(true);
                bottomBarButton3.setSelected(false);
                break;

            case R.id.bottomBarButton3:
                //fragment_list
                viewPager.setCurrentItem(2);
                bottomBarButton1.setSelected(false);
                bottomBarButton2.setSelected(false);
                bottomBarButton3.setSelected(true);
                break;
        }
    }

    /*
            뒤로가기 버튼 눌렀을 경우의 함수 override
         */
    @Override
    public void onBackPressed() {
        //뒤로가기 버튼 누르면 프로그램 종료 다이어로그 나옴
        //확인 -> 종료 / 취소 -> 그냥 가만히 있음.
        if(getFragmentManager().getBackStackEntryCount() > 0){

            getFragmentManager().findFragmentById(0).onStop();
        }else{
            AlertDialog.Builder exitAlertDialogBuilder = new AlertDialog.Builder(
                    context);

            // 제목셋팅
            exitAlertDialogBuilder.setTitle("프로그램 종료");

            // AlertDialog 셋팅
            exitAlertDialogBuilder
                    .setMessage("프로그램을 종료하시겠어요?")
                    .setCancelable(false)
                    .setPositiveButton("종료",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    // 프로그램을 종료한다
                                    MainActivity.this.finish();
                                }
                            })

                    .setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    // 다이얼로그를 취소한다
                                    dialog.cancel();
                                }
                            });
            // 다이얼로그 생성
            AlertDialog alertDialog = exitAlertDialogBuilder.create();

            // 다이얼로그 보여주기
            alertDialog.show();
        }


    }



}
