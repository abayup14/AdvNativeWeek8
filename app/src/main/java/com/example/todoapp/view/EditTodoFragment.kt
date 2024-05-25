package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentCreateTodoBinding
import com.example.todoapp.databinding.FragmentEditTodoBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment(), TodoSaveChangesClick ,RadioClick {
    private lateinit var binding: FragmentCreateTodoBinding
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding: FragmentEditTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
//        binding = FragmentCreateTodoBinding.inflate(inflater, container, false)
//        return binding.root
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        dataBinding.txtJudulTodo.text = "Edit Todo"
//        dataBinding.btnAdd.text = "Save Changes"
//
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
//        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
//        viewModel.fetch(uuid)

//        dataBinding.btnAdd.setOnClickListener {
//            val radio = view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)
////            viewModel.update(binding.txtTitle.text.toString(),
////                binding.txtNotes.text.toString(),
////                radio.tag.toString().toInt(),
////                uuid)
////            Toast.makeText(view.context, "Todo Updated", Toast.LENGTH_SHORT).show()
////            Navigation.findNavController(it).popBackStack()
//        }
        observeViewModel()
        dataBinding.radioListener = this
        dataBinding.saveListener = this
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
//            binding.txtTitle.setText(it.title)
//            binding.txtNotes.setText(it.notes)
//            when (it.priority) {
//                1 -> binding.radioLow.isChecked = true
//                2 -> binding.radioMedium.isChecked = true
//                else -> binding.radioHigh.isChecked = true
//            }
            dataBinding.todo = it
        })
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
    }
}