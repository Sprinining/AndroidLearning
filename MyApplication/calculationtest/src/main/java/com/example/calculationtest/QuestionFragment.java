package com.example.calculationtest;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculationtest.databinding.FragmentQuestionBinding;

public class QuestionFragment extends Fragment {

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel myViewModel;
        myViewModel = ViewModelProviders.of(requireActivity(), new SavedStateViewModelFactory(requireActivity().getApplication(), this))
                .get(MyViewModel.class);
        myViewModel.generator();
        FragmentQuestionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setData(myViewModel);
        binding.setLifecycleOwner(requireActivity());

        final StringBuilder builder = new StringBuilder();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn0:
                        builder.append(0);
                        break;
                    case R.id.btn1:
                        builder.append(1);
                        break;
                    case R.id.btn2:
                        builder.append(2);
                        break;
                    case R.id.btn3:
                        builder.append(3);
                        break;
                    case R.id.btn4:
                        builder.append(4);
                        break;
                    case R.id.btn5:
                        builder.append(5);
                        break;
                    case R.id.btn6:
                        builder.append(6);
                        break;
                    case R.id.btn7:
                        builder.append(7);
                        break;
                    case R.id.btn8:
                        builder.append(8);
                        break;
                    case R.id.btn9:
                        builder.append(9);
                        break;
                    case R.id.btn_clear:
                        builder.setLength(0);
                        break;
                }
                if (builder.length() == 0){
                    binding.textView9.setText(getString(R.string.input_indicator));
                }else {
                    binding.textView9.setText(builder.toString());
                }
            }

        };

        binding.btn0.setOnClickListener(listener);
        binding.btn1.setOnClickListener(listener);
        binding.btn2.setOnClickListener(listener);
        binding.btn3.setOnClickListener(listener);
        binding.btn4.setOnClickListener(listener);
        binding.btn5.setOnClickListener(listener);
        binding.btn6.setOnClickListener(listener);
        binding.btn7.setOnClickListener(listener);
        binding.btn8.setOnClickListener(listener);
        binding.btn9.setOnClickListener(listener);
        binding.btnClear.setOnClickListener(listener);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.valueOf(builder.toString()).intValue() == myViewModel.getAnswer().getValue()){
                    myViewModel.answerCorrect();
                    builder.setLength(0);
                    binding.textView9.setText(R.string.input_indicator);
                }else{
                    // 答案错误后切换页面
                    NavController controller = Navigation.findNavController(view);
                    if (myViewModel.win_flag){
                       controller.navigate(R.id.action_questionFragment_to_winFragment);
                       myViewModel.win_flag = false;
                       myViewModel.save();
                    }else {
                        controller.navigate(R.id.action_questionFragment_to_loseFragment);
                    }
                }
            }
        });

        return binding.getRoot();
    }
}