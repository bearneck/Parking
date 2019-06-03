package com.bearneck.parking.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bearneck.parking.BookingParking;
import com.bearneck.parking.LoginActivity;
import com.bearneck.parking.MainActivity;
import com.bearneck.parking.R;


public class BookingParkingFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BookingParkingFragment() {

    }

    public static BookingParkingFragment newInstance(String param1, String param2) {
        BookingParkingFragment fragment = new BookingParkingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking_parking, container, false);
        Button bookingButton = (Button) view.findViewById(R.id.bookingButton);
        Button historyButton = (Button) view.findViewById(R.id.historyButton);
        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(MainActivity.UserId,"测试id");
                bundle.putString(MainActivity.RealName,"李狗蛋");
                intent.putExtras(bundle);
                intent.setClass(getActivity(),BookingParking.class);
                startActivity(intent);
            }
        });
        return view;
    }





}
